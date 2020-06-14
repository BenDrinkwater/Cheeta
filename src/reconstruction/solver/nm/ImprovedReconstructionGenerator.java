package reconstruction.solver.nm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import association.Association;
import reconstruction.LooseParasiteMapping;
import reconstruction.LooseParasiteMappingList;
import reconstruction.MappingPoint;
import reconstruction.ParasiteMappingList;
import reconstruction.LooseParasiteMapping.Event;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.Node;
import util.Pair;
import util.comparators.DepthComparator;
import util.reconstruction.ImprovedReconstructionSet;
import util.rmq.RangeMinimumQuery;
import util.rmq.RangeMinimumQuerySparseTable;

public class ImprovedReconstructionGenerator {

	protected CoevolutionaryHistory history;
	
	protected int costOfCodivergenceEvent;
	protected int costOfDuplicationEvent;
	protected int costOfSortingEvent;
	protected int costOfSwitchEvent;

	protected LooseParasiteMappingList parasiteMappingList;
	
	public ImprovedReconstructionGenerator(CoevolutionaryHistory history, int[] costs) {
		this.history = history;
		this.costOfCodivergenceEvent = costs[0];
		this.costOfDuplicationEvent = costs[1];
		this.costOfSortingEvent = costs[2];
		this.costOfSwitchEvent = costs[3];
		this.initLooseParasiteMappingList();
	}
	
	protected void initLooseParasiteMappingList() {
		this.parasiteMappingList = new LooseParasiteMappingList();
	}
	
	public ParasiteMappingList getParasiteMappingList() {
		this.generateReconstruction();
		return this.parasiteMappingList.generatorMappingList(this.history);
	}
	
	protected void generateReconstruction() {
		this.history.getParasiteTree().setRandomUniqueNodeTimings();			
		
		this.mapLeafNodes();
		
		Set<Node> toProcess = this.getListOfInternalParasiteNodesToProcess();
		while (toProcess.size() > 0) {
			Node currentlyProcessing = toProcess.iterator().next();
			toProcess.remove(currentlyProcessing);
			
			Set<LooseParasiteMapping> solutions = this.addToSolutions(currentlyProcessing);
			
			if (null != solutions && solutions.size() > 0) {
				this.parasiteMappingList.add(currentlyProcessing, solutions);
			}
		}
	}
	
	protected Set<LooseParasiteMapping> addToSolutions(Node currentlyProcessing) {
		Set<LooseParasiteMapping> solutions = getReconstructionSet();
		
		solutions.addAll(this.preprocessingStep(currentlyProcessing));
		
		return this.returnReconstructionSet(solutions);
	}
	
	protected Set<LooseParasiteMapping> preprocessingStep(Node currentlyProcessing) {
		Set<LooseParasiteMapping> mappings = this.getReconstructionSet();
		
		Node leftChild = currentlyProcessing.getLeftChild();
		Node rightChild = currentlyProcessing.getRightChild();
		
		Set<LooseParasiteMapping> leftChildrenMappingSites = this.parasiteMappingList.getLooseParasiteMappings(leftChild);
		Set<LooseParasiteMapping> rightChildrenMappingSites = this.parasiteMappingList.getLooseParasiteMappings(rightChild);
		
		Set<LooseParasiteMapping> allMappingSites = new LinkedHashSet<LooseParasiteMapping>();
		allMappingSites.addAll(leftChildrenMappingSites);
		allMappingSites.addAll(rightChildrenMappingSites);
		
		// Step 1
		// find lowest common ancestor for all mapping sites (this ancestor may be the floating edge). Takes O(n) preprocessing and O(n) execution.
 		Node lowestCommonAncestor = this.lowestCommonAncestor(allMappingSites);
 		
		// Step 2
		// label tree with minimum at each node below lowest common ancestor (this is to compute codivergence and duplication)
		// compute min at each node. There will be O(n) min calculations but we only consider 3n numbers as we only consider the min of the child nodes at each step
		
 		// TODO turn these into an array (each node has a label based on their height)
 		Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts = this.getCostsForEachNode(lowestCommonAncestor, currentlyProcessing.getLeftChild());
		Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> rightCosts = this.getCostsForEachNode(lowestCommonAncestor, currentlyProcessing.getRightChild());
		
		NodeCosts[] leftCosts2 = this.getCostsForEachNode2(lowestCommonAncestor, currentlyProcessing.getLeftChild());
		NodeCosts[] rightCosts2 = this.getCostsForEachNode2(lowestCommonAncestor, currentlyProcessing.getRightChild());		
		
		// Step 3
		// Minimum cost at each level for left and right. This is generated using the minimum costs and is derived from the root (LCA) down to the leaves.
		// Going from the root to the leaves ensures linear time processing.
		
		LevelCosts[] leftCostsForEachLevel = this.getCostsForEachLevel(lowestCommonAncestor, currentlyProcessing.getLeftChild(), leftCosts);
		LevelCosts[] rightCostsForEachLevel = this.getCostsForEachLevel(lowestCommonAncestor, currentlyProcessing.getRightChild(), rightCosts);		
		
		LevelCosts[] leftCostsForEachLevel2 = this.getCostsForEachLevel(lowestCommonAncestor, currentlyProcessing.getLeftChild(), leftCosts2);
		LevelCosts[] rightCostsForEachLevel2 = this.getCostsForEachLevel(lowestCommonAncestor, currentlyProcessing.getRightChild(), rightCosts2);		
				
		// Step 4
		// process each host node. Compute the cheapest codivergence, duplication and host switch for each host node. 3n		
		mappings.addAll(this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor, leftCosts, rightCosts));
		mappings.addAll(this.getOptimalDuplicationEventsForEachHostNode(lowestCommonAncestor, leftCosts, rightCosts));
		mappings.addAll(this.getOptimalHostSwitchEventsForEachHostNode2(lowestCommonAncestor, leftCosts, rightCosts, leftCostsForEachLevel, rightCostsForEachLevel));
		
		// The final running time is O(n) for each parasite node. Which comprises of 10 linear time steps. 
		
		// As a result the expected running time is O(mn).
		
		return mappings;
	}
	
	private boolean comapareLevelCosts(LevelCosts[] first, LevelCosts[] second) {
		for (int i = 0; i < first.length; ++i) {
			if (!(first[i].getCost().equals(second[i].getCost()))) {
				return false;
			}
		}
		return true;
	}
	
	// ################################################################################################
	// Step 1

	protected Node lowestCommonAncestor(Set<LooseParasiteMapping> allMappingSites) {
		Node lowestCommonAncestor = allMappingSites.iterator().next().getCurrentMappingPoint().getMappedPointAsNode();
		for (LooseParasiteMapping mapping : allMappingSites) {
			lowestCommonAncestor = this.history.getHostTree().getLowestCommonAncestor(lowestCommonAncestor, mapping.getCurrentMappingPoint().getMappedPointAsNode());
		}
		return lowestCommonAncestor;
	}

	// ################################################################################################
	// Step 2
	
	protected Map<Node, Pair<Integer,Pair<Node, LooseParasiteMapping>>> getCostsForEachNode(Node lowestCommonAncestor, Node node) {
		Map<Node, Pair<Integer,Pair<Node, LooseParasiteMapping>>> costsAtEachLevel = this.assignMappingLocationsACost2(node);
		
		Set<Node> leavesOfSubTree = new HashSet<Node>(costsAtEachLevel.keySet());
		
		while(leavesOfSubTree.size() > 0) {
			Node next = leavesOfSubTree.iterator().next();
			leavesOfSubTree.remove(next);
			if (next.equals(lowestCommonAncestor)) {
				continue;
			}
			Node parent = next.getParent();
			Node alternateChild = next.equals(next.getParent().getLeftChild()) ? next.getParent().getRightChild() : next.getParent().getLeftChild();
			
			// not quite need to check there is no mapped location at the parent as well. Add in a sec.			
			
			int minimumParentCost = Integer.MAX_VALUE;
			LooseParasiteMapping cheapestParentMapping = null;
			Node currentPosition = null;
			
			if (costsAtEachLevel.get(next).getFirst() 
					< (costsAtEachLevel.containsKey(alternateChild) ? costsAtEachLevel.get(alternateChild).getFirst() : Integer.MAX_VALUE)) {
				minimumParentCost = costsAtEachLevel.get(next).getFirst() + this.costOfSortingEvent;
				currentPosition = costsAtEachLevel.get(next).getSecond().getFirst().getParent();
				cheapestParentMapping = costsAtEachLevel.get(next).getSecond().getSecond();
			}
			else {
				minimumParentCost = costsAtEachLevel.get(alternateChild).getFirst() + this.costOfSortingEvent;
				currentPosition = costsAtEachLevel.get(alternateChild).getSecond().getFirst().getParent();
				cheapestParentMapping = costsAtEachLevel.get(alternateChild).getSecond().getSecond();			
			}
			
			if (minimumParentCost + this.costOfSortingEvent
					> (costsAtEachLevel.containsKey(parent) ? costsAtEachLevel.get(parent).getFirst() : Integer.MAX_VALUE)) {
				minimumParentCost = costsAtEachLevel.get(parent).getFirst();
				currentPosition = costsAtEachLevel.get(parent).getSecond().getFirst();		
				cheapestParentMapping = costsAtEachLevel.get(parent).getSecond().getSecond();				
			}
			
			leavesOfSubTree.add(parent);
			costsAtEachLevel.put(parent, new Pair<Integer, Pair<Node, LooseParasiteMapping>>(minimumParentCost, new Pair<Node, LooseParasiteMapping>(currentPosition, cheapestParentMapping)));
		}
		
		return costsAtEachLevel;	
	}
	
	protected Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> assignMappingLocationsACost2(Node parasiteNode) {
		Map<Node, Pair<Integer,Pair<Node, LooseParasiteMapping>>> costsBasedOnMaps = new HashMap<Node, Pair<Integer,Pair<Node, LooseParasiteMapping>>>();
		
		Set<LooseParasiteMapping> mappings = this.parasiteMappingList.getLooseParasiteMappings(parasiteNode);
		for (LooseParasiteMapping mapping : mappings) {
			costsBasedOnMaps.put(mapping.getCurrentMappingPoint().getMappedPointAsNode(), 
					new Pair<Integer,Pair<Node, LooseParasiteMapping>>(mapping.getCost(), 
							new Pair<Node, LooseParasiteMapping>(mapping.getCurrentMappingPoint().getMappedPointAsNode(), mapping)));
		}
		
		return costsBasedOnMaps;
	}
	

	private NodeCosts[] getCostsForEachNode2(Node lowestCommonAncestor, Node child) {
		NodeCosts[] nodeCosts = assignMappingLocationsACost(child);
		
		LinkedList<Node> leavesOfSubTree = this.getListOfNodes(nodeCosts);
		
		while(leavesOfSubTree.size() > 0) { 
			Node next = leavesOfSubTree.removeFirst();
			if (next.equals(lowestCommonAncestor)) {
				continue;
			}
			Node parent = next.getParent();
			Node alternateChild = next.equals(next.getParent().getLeftChild()) ? next.getParent().getRightChild() : next.getParent().getLeftChild();
			
			// not quite need to check there is no mapped location at the parent as well. Add in a sec.			
			
			int minimumParentCost = Integer.MAX_VALUE;
			LooseParasiteMapping cheapestParentMapping = null;
			Node currentPosition = null;
			
			int indexOfCurrentNode = next.getHeight();
			int indexOfParent = parent.getHeight();
			int indexOfAlternateChild = alternateChild.getHeight();
			
			if (nodeCosts[indexOfCurrentNode].getCost()	< (null != nodeCosts[indexOfAlternateChild] ? nodeCosts[indexOfAlternateChild].getCost() : Integer.MAX_VALUE)) {
				minimumParentCost = nodeCosts[indexOfCurrentNode].getCost() + this.costOfSortingEvent;
				currentPosition = nodeCosts[indexOfCurrentNode].getNode().getParent();
				cheapestParentMapping = nodeCosts[indexOfCurrentNode].getMapping();
			}
			else {
				minimumParentCost = nodeCosts[indexOfAlternateChild].getCost() + this.costOfSortingEvent;
				currentPosition = nodeCosts[indexOfAlternateChild].getNode().getParent();
				cheapestParentMapping = nodeCosts[indexOfAlternateChild].getMapping();
			}
			
			if (minimumParentCost + this.costOfSortingEvent > (null != nodeCosts[indexOfParent] ? nodeCosts[indexOfParent].getCost() : Integer.MAX_VALUE)) {
				minimumParentCost = nodeCosts[indexOfParent].getCost();
				currentPosition = nodeCosts[indexOfParent].getNode();
				cheapestParentMapping = nodeCosts[indexOfParent].getMapping();			
			}
			
			leavesOfSubTree.addLast(parent);
			nodeCosts[indexOfParent] = new NodeCosts(new Pair<Integer, Pair<Node, LooseParasiteMapping>>(minimumParentCost, new Pair<Node, LooseParasiteMapping>(currentPosition, cheapestParentMapping)), parent);
		}
		
		return nodeCosts;		
	}
	
	private LinkedList<Node> getListOfNodes(NodeCosts[] nodeCosts) {
		LinkedList<Node> nodes = new LinkedList<Node>();
		for (int i = 0; i < nodeCosts.length; ++i) {
			if (null != nodeCosts[i]) {
				nodes.addLast(nodeCosts[i].getMappedLocation());
			}
		}
		return nodes;
	}

	protected NodeCosts[] assignMappingLocationsACost(Node child) {
		NodeCosts[] ndoeCosts = new NodeCosts[this.history.getHostTree().size()];
		
		Set<LooseParasiteMapping> mappings = this.parasiteMappingList.getLooseParasiteMappings(child);
		for (LooseParasiteMapping mapping : mappings) {
			int index = mapping.getCurrentMappingPoint().getMappedPointAsNode().getHeight();
			NodeCosts costs = new NodeCosts(new Pair<Integer,Pair<Node, LooseParasiteMapping>>(mapping.getCost(), 
					new Pair<Node, LooseParasiteMapping>(mapping.getCurrentMappingPoint().getMappedPointAsNode(), mapping)), mapping.getCurrentMappingPoint().getMappedPointAsNode());
			ndoeCosts[index] = costs;
		}
		
		return ndoeCosts;
	}
	
	// ################################################################################################
	// Step 3
	
	private LevelCosts[] getCostsForEachLevel(Node lowestCommonAncestor, Node parasiteNode, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> costs) {
		LevelCosts[] map = new LevelCosts[this.history.getHostTree().numberOfInteranlNodes()+1];
		map = this.getCostsForEachLevel(lowestCommonAncestor, parasiteNode, costs, map);
		return this.completeMap(map);
	}

	private LevelCosts[] getCostsForEachLevel(Node lowestCommonAncestor, 
			Node parasiteNode, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> costs, LevelCosts[] map) {

		if (null == lowestCommonAncestor) {
			return map;
		}
		else if (!lowestCommonAncestor.isRootNode()){
			Node alternateNode = lowestCommonAncestor.getParent().getLeftChild().equals(lowestCommonAncestor) ? lowestCommonAncestor.getParent().getRightChild() : lowestCommonAncestor.getParent().getLeftChild();
			Pair<Integer, Pair<Node, LooseParasiteMapping>> myCost = costs.get(lowestCommonAncestor);
			Pair<Integer, Pair<Node, LooseParasiteMapping>> alteranteNodeCost = costs.get(alternateNode);
			
			if (null == myCost && null == alteranteNodeCost) {
				
			}
			else if (null == myCost || null == myCost.getFirst()) {
				addToMap(alternateNode.getHeight(), map, alteranteNodeCost);
			}
			else if (null == alteranteNodeCost || null == alteranteNodeCost.getFirst()) {
				addToMap(lowestCommonAncestor.getHeight(), map, myCost);
			}
			else {
				if (myCost.getFirst().equals(alteranteNodeCost.getFirst())) {
					if (this.history.getHostTree().getNodeHeight(lowestCommonAncestor) >= this.history.getHostTree().getNodeHeight(alternateNode)) {
						addToMap(lowestCommonAncestor.getHeight(), map, myCost);
					}
					else {
						addToMap(alternateNode.getHeight(), map, alteranteNodeCost);
					}
				}
				else if (myCost.getFirst() < alteranteNodeCost.getFirst()) {
					addToMap(lowestCommonAncestor.getHeight(), map, myCost);
				}
				else {
					addToMap(alternateNode.getHeight(), map, alteranteNodeCost);
				}
			}
		}
		
		this.getCostsForEachLevel(lowestCommonAncestor.getLeftChild(), parasiteNode, costs, map);
		this.getCostsForEachLevel(lowestCommonAncestor.getRightChild(), parasiteNode, costs, map);
		
		return map;		
	}
	
	private LevelCosts[] completeMap(LevelCosts[] map) {
		
		Integer maxDepth = 0;
		
		for (int i = map.length - 1; i > 0; --i) {
			if (null == map[i]) {
				continue;
			}
			else {
				maxDepth = i;
				break;
			}
		}
		
		for (int i = maxDepth; i >= 0; --i) {
			if (null != map[i]) {
				continue;
			}
			else {
				Pair<Integer, Pair<Node, LooseParasiteMapping>> previousEntry = map[i+1].getLevelCosts();
				if (null == previousEntry) {
					continue;
				}
				if (!previousEntry.getSecond().getFirst().isRootNode()) {
					Integer parentHeight = previousEntry.getSecond().getFirst().getParent().getHeight();
					if (parentHeight < i) {
						map[i] = new LevelCosts(previousEntry);
					}
					else {
						Pair<Integer, Pair<Node, LooseParasiteMapping>> newEntry = 
								new Pair<Integer, Pair<Node, LooseParasiteMapping>>(previousEntry.getFirst() + this.costOfSortingEvent, 
										new Pair<Node, LooseParasiteMapping>(previousEntry.getSecond().getFirst().getParent(), previousEntry.getSecond().getSecond()));
						map[i] = new LevelCosts(newEntry);
					}
				}
			}
		}
		
		return map;
	}
	
	private void addToMap(Integer key, LevelCosts[] map, Pair<Integer, Pair<Node, LooseParasiteMapping>> value) {
		if (null != map[key]) {	
			if (map[key].getCost() < value.getFirst()) {
				map[key] = new LevelCosts(value);
			}
			else if (map[key].getCost() == value.getFirst()) {
				if (value.getSecond().getFirst().getParent().getHeight() <= map[key].getNode().getParent().getHeight()) {
					map[key] = new LevelCosts(value);
				}
			}
		}
		else {
			map[key] = new LevelCosts(value);
		}
	}
	
	private LevelCosts[] getCostsForEachLevel(Node lowestCommonAncestor, Node parasiteNode, NodeCosts[] costs) {
		LevelCosts[] map = new LevelCosts[this.history.getHostTree().numberOfInteranlNodes()+1];
		map = this.getCostsForEachLevel(lowestCommonAncestor, parasiteNode, costs, map);
		return this.completeMap(map);
	}
	
	private LevelCosts[] getCostsForEachLevel(Node lowestCommonAncestor, Node parasiteNode, NodeCosts[] costs, LevelCosts[] map) {
		if (null == lowestCommonAncestor) {
			return map;
		}
		else if (!lowestCommonAncestor.isRootNode()){
			Node alternateNode = lowestCommonAncestor.getParent().getLeftChild().equals(lowestCommonAncestor) ? lowestCommonAncestor.getParent().getRightChild() : lowestCommonAncestor.getParent().getLeftChild();
			Pair<Integer, Pair<Node, LooseParasiteMapping>> myCost = null != costs[lowestCommonAncestor.getHeight()] ? costs[lowestCommonAncestor.getHeight()].nodeCost : null;
			Pair<Integer, Pair<Node, LooseParasiteMapping>> alteranteNodeCost = null != costs[alternateNode.getHeight()] ? costs[alternateNode.getHeight()].nodeCost : null;
			
			if (null == myCost && null == alteranteNodeCost) {
				
			}
			else if (null == myCost || null == myCost.getFirst()) {
				addToMap(alternateNode.getHeight(), map, alteranteNodeCost);
			}
			else if (null == alteranteNodeCost || null == alteranteNodeCost.getFirst()) {
				addToMap(lowestCommonAncestor.getHeight(), map, myCost);
			}
			else {
				if (myCost.getFirst().equals(alteranteNodeCost.getFirst())) {
					if (this.history.getHostTree().getNodeHeight(lowestCommonAncestor) >= this.history.getHostTree().getNodeHeight(alternateNode)) {
						addToMap(lowestCommonAncestor.getHeight(), map, myCost);
					}
					else {
						addToMap(alternateNode.getHeight(), map, alteranteNodeCost);
					}
				}
				else if (myCost.getFirst() < alteranteNodeCost.getFirst()) {
					addToMap(lowestCommonAncestor.getHeight(), map, myCost);
				}
				else {
					addToMap(alternateNode.getHeight(), map, alteranteNodeCost);
				}
			}
		}
		
		this.getCostsForEachLevel(lowestCommonAncestor.getLeftChild(), parasiteNode, costs, map);
		this.getCostsForEachLevel(lowestCommonAncestor.getRightChild(), parasiteNode, costs, map);
		
		return map;		
	}

	// ################################################################################################
	// Step 4	

	private Set<LooseParasiteMapping> getOptimalCodivergenceEventsForEachHostNode(Node lowestCommonAncestor, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> rightCosts) {
		Set<LooseParasiteMapping> mappings = this.getReconstructionSet();
		return this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor, leftCosts, rightCosts, mappings);
	}	
	
	private Set<LooseParasiteMapping> getOptimalCodivergenceEventsForEachHostNode(Node lowestCommonAncestor, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> rightCosts, Set<LooseParasiteMapping> mappings) {
		Node left = lowestCommonAncestor.getLeftChild();
		Node right = lowestCommonAncestor.getRightChild();
		
		if (null == left || null == right) {
			return mappings;
		}
		
		if (null != leftCosts.get(left) && null != rightCosts.get(right)) {
			MappingPoint mappingPoint = new MappingPoint(lowestCommonAncestor);
			LooseParasiteMapping leftParasiteMapping = leftCosts.get(left).getSecond().getSecond();
			LooseParasiteMapping rightParasiteMapping = rightCosts.get(right).getSecond().getSecond();
			LooseParasiteMapping thisMappingPoint = new LooseParasiteMapping(mappingPoint, Event.Codivergence, leftParasiteMapping, rightParasiteMapping);
			
			int numberOfLossEvents = this.history.getHostTree().distanceBetweenNodes(lowestCommonAncestor, leftParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode()) 
					+ this.history.getHostTree().distanceBetweenNodes(lowestCommonAncestor, rightParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode());
			
			thisMappingPoint.addCost(this.costOfCodivergenceEvent + this.costOfSortingEvent * numberOfLossEvents);
			
			this.setBestForwardPosition(thisMappingPoint, leftParasiteMapping, rightParasiteMapping);
			
			mappings.add(thisMappingPoint);
			this.setBestForwardPosition(thisMappingPoint, leftParasiteMapping, rightParasiteMapping);	
			this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor.getLeftChild(), leftCosts, rightCosts, mappings);
			this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor.getRightChild(), leftCosts, rightCosts, mappings);
		}
		else if (null != leftCosts.get(right) && null != rightCosts.get(left)) {
			MappingPoint mappingPoint = new MappingPoint(lowestCommonAncestor);
			LooseParasiteMapping leftParasiteMapping = leftCosts.get(right).getSecond().getSecond();
			LooseParasiteMapping rightParasiteMapping = rightCosts.get(left).getSecond().getSecond();
			LooseParasiteMapping thisMappingPoint = new LooseParasiteMapping(mappingPoint, Event.Codivergence, leftParasiteMapping, rightParasiteMapping);
			
			int numberOfLossEvents = this.history.getHostTree().distanceBetweenNodes(lowestCommonAncestor, leftParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode()) 
					+ this.history.getHostTree().distanceBetweenNodes(lowestCommonAncestor, rightParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode());
			
			thisMappingPoint.addCost(this.costOfCodivergenceEvent + this.costOfSortingEvent * numberOfLossEvents);
			mappings.add(thisMappingPoint);
			this.setBestForwardPosition(thisMappingPoint, leftParasiteMapping, rightParasiteMapping);	
			this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor.getLeftChild(), leftCosts, rightCosts, mappings);
			this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor.getRightChild(), leftCosts, rightCosts, mappings);			
		}
		else if (null == leftCosts.get(left)) {
			this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor.getRightChild(), leftCosts, rightCosts, mappings);
		}
		else if (null == rightCosts.get(right)) {
			this.getOptimalCodivergenceEventsForEachHostNode(lowestCommonAncestor.getLeftChild(), leftCosts, rightCosts, mappings);
		}
		
		return mappings;
	}
	
	private Set<LooseParasiteMapping> getOptimalDuplicationEventsForEachHostNode(Node lowestCommonAncestor, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> rightCosts) {
		Set<LooseParasiteMapping> mappings = this.getReconstructionSet();
		return this.getOptimalDuplicationEventsForEachHostNode(lowestCommonAncestor, leftCosts, rightCosts, mappings);		
	}

	private Set<LooseParasiteMapping> getOptimalDuplicationEventsForEachHostNode(Node lowestCommonAncestor, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> rightCosts, Set<LooseParasiteMapping> mappings) {
		if (null == lowestCommonAncestor) {
			return mappings;
		}
		
		Node left = lowestCommonAncestor.getLeftChild();
		Node right = lowestCommonAncestor.getRightChild();
		
		if (null != leftCosts.get(lowestCommonAncestor) && null != rightCosts.get(lowestCommonAncestor)) {

			MappingPoint mappingPoint = new MappingPoint(lowestCommonAncestor.getParentEdge());
			LooseParasiteMapping leftParasiteMapping = leftCosts.get(lowestCommonAncestor).getSecond().getSecond();
			LooseParasiteMapping rightParasiteMapping = rightCosts.get(lowestCommonAncestor).getSecond().getSecond();
			LooseParasiteMapping mapping = new LooseParasiteMapping(mappingPoint, LooseParasiteMapping.Event.Duplication, leftParasiteMapping, rightParasiteMapping);
			
			int leftLoss = leftCosts.get(lowestCommonAncestor).getFirst() - leftParasiteMapping.getCost();
			int rightLoss = rightCosts.get(lowestCommonAncestor).getFirst() - rightParasiteMapping.getCost();
			
			mapping.addCost(this.costOfDuplicationEvent + this.costOfSortingEvent * (leftLoss + rightLoss));
			
			this.setBestForwardPosition(mapping, leftParasiteMapping, rightParasiteMapping);
			
			mappings.add(mapping);	
			this.getOptimalDuplicationEventsForEachHostNode(lowestCommonAncestor.getLeftChild(), leftCosts, rightCosts, mappings);
			this.getOptimalDuplicationEventsForEachHostNode(lowestCommonAncestor.getRightChild(), leftCosts, rightCosts, mappings);	
		}
		else if (null == leftCosts.get(left)) {
			this.getOptimalDuplicationEventsForEachHostNode(lowestCommonAncestor.getLeftChild(), leftCosts, rightCosts, mappings);
		}
		else if (null == rightCosts.get(right)) {
			this.getOptimalDuplicationEventsForEachHostNode(lowestCommonAncestor.getRightChild(), leftCosts, rightCosts, mappings);
		}
		
		return mappings;
	}
	
	private Set<LooseParasiteMapping> getOptimalHostSwitchEventsForEachHostNode2(Node lowestCommonAncestor, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts,
			Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> rightCosts, LevelCosts[] leftCostsForEachLevel, LevelCosts[] rightCostsForEachLevel) {
		Set<LooseParasiteMapping> mappings = this.getReconstructionSet();
		
		Set<Node> nodesToConsider = this.history.getHostTree().getAllNodes(lowestCommonAncestor);

		int[] leftLevelCosts = this.getLevelCosts(leftCostsForEachLevel);
		int[] rightLevelCosts = this.getLevelCosts(rightCostsForEachLevel);
		
		RangeMinimumQuery leftRMQ = new RangeMinimumQuerySparseTable(leftLevelCosts);
		RangeMinimumQuery rightRMQ = new RangeMinimumQuerySparseTable(rightLevelCosts);
		
		for (Node hostNode : nodesToConsider) {
 			if (hostNode.equals(lowestCommonAncestor)) {
 				continue;
 			}
		
			Integer firstIndex = hostNode.getParent().getHeight();
			Integer lastIndex = hostNode.getHeight();
			
			Pair<Integer, Pair<LooseParasiteMapping, LooseParasiteMapping>> hostSwitch = 
					this.recoverCheapestHostSwitch(hostNode, firstIndex, lastIndex, leftCosts, rightCosts, leftRMQ, rightRMQ, leftCostsForEachLevel, rightCostsForEachLevel);
 			
			if (null != hostSwitch.getSecond()) {				
				Pair<Edge, Edge> mappingPoint = this.getEdgePair(hostSwitch, leftCosts, rightCosts, leftCostsForEachLevel, rightCostsForEachLevel,  LooseParasiteMapping.Event.HostSwitchDirection1);
				
				if (null == mappingPoint) {
					continue;
				}
 				
				LooseParasiteMapping mapping = new LooseParasiteMapping(new MappingPoint(mappingPoint), LooseParasiteMapping.Event.HostSwitchDirection1, hostSwitch.getSecond().getFirst(), hostSwitch.getSecond().getSecond());

				Node firstEdgeMappedHostNode = mapping.getCurrentMappingPoint().getMappedPointAsNode();
				Node secondEdgeMappedHostNode = mapping.getCurrentMappingPoint().getSecondHostNode();
				
				Node hostNodeMappedToLeftChild = hostSwitch.getSecond().getFirst().getCurrentMappingPoint().getMappedPointAsNode();
				Node hostNodeMappedToRightChild = hostSwitch.getSecond().getSecond().getCurrentMappingPoint().getMappedPointAsNode();
				
				if (!(leftCosts.containsKey(firstEdgeMappedHostNode) && leftCosts.containsKey(hostNodeMappedToLeftChild) &&
						rightCosts.containsKey(secondEdgeMappedHostNode) && rightCosts.containsKey(hostNodeMappedToRightChild))) {
					return mappings;
				}
				
				int loss = leftCosts.get(firstEdgeMappedHostNode).getFirst() - leftCosts.get(hostNodeMappedToLeftChild).getFirst() +
						rightCosts.get(secondEdgeMappedHostNode).getFirst() - rightCosts.get(hostNodeMappedToRightChild).getFirst();
				
				mapping.addCost(this.costOfSwitchEvent + this.costOfSortingEvent * loss);
				mappings.add(mapping);
				this.setBestForwardPosition(mapping, hostSwitch.getSecond().getFirst(), hostSwitch.getSecond().getSecond());
				
				Pair<Edge, Edge> mappingPointReverse = this.getEdgePair(hostSwitch, leftCosts, rightCosts, leftCostsForEachLevel, rightCostsForEachLevel,  LooseParasiteMapping.Event.HostSwitchDirection2);
				
				if (null == mappingPointReverse) {
					continue;
				}
				
				LooseParasiteMapping mappingReverse = new LooseParasiteMapping(new MappingPoint(mappingPointReverse), LooseParasiteMapping.Event.HostSwitchDirection2, hostSwitch.getSecond().getFirst(), hostSwitch.getSecond().getSecond());
				
				mappingReverse.addCost(this.costOfSwitchEvent + this.costOfSortingEvent * loss);
				mappings.add(mappingReverse);
				this.setBestForwardPosition(mappingReverse, hostSwitch.getSecond().getSecond(), hostSwitch.getSecond().getFirst());
			}
		}
		
		return mappings;
	}

	private Pair<Integer, Pair<LooseParasiteMapping, LooseParasiteMapping>> recoverCheapestHostSwitch(Node hostNode, Integer firstIndex, Integer lastIndex,
			Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts, Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> rightCosts,
			RangeMinimumQuery leftRMQ, RangeMinimumQuery rightRMQ, LevelCosts[] leftCostsForEachLevel, LevelCosts[] rightCostsForEachLevel) {

		Pair<Integer, Pair<LooseParasiteMapping, LooseParasiteMapping>> cheapestPair = new Pair<Integer, Pair<LooseParasiteMapping, LooseParasiteMapping>>(Integer.MAX_VALUE, null);
		
		Pair<Integer, LooseParasiteMapping> reverseLeftPair = leftCosts.containsKey(hostNode) 
				? new Pair<Integer, LooseParasiteMapping>(leftCosts.get(hostNode).getFirst(), leftCosts.get(hostNode).getSecond().getSecond()) : null;
		
		Pair<Integer, LooseParasiteMapping> rightPair = rightCosts.containsKey(hostNode) ?
				new Pair<Integer, LooseParasiteMapping>(rightCosts.get(hostNode).getFirst(), rightCosts.get(hostNode).getSecond().getSecond()) : null;
				
		int leftIndex = leftRMQ.query(firstIndex, lastIndex);
		int rightIndex = rightRMQ.query(firstIndex, lastIndex);
		
		Pair<Integer, LooseParasiteMapping> leftPair = null != leftCostsForEachLevel[leftIndex] ? 
				new Pair<Integer, LooseParasiteMapping>(leftCostsForEachLevel[leftIndex].getCost(), leftCostsForEachLevel[leftIndex].getMapping()) : null;
		
		if (null != leftPair && null != rightPair) {
			if (leftPair.getFirst() + rightPair.getFirst() < cheapestPair.getFirst()) {
				cheapestPair = new Pair<Integer, Pair<LooseParasiteMapping, LooseParasiteMapping>>
				(leftPair.getFirst() + rightPair.getFirst(), 
						new Pair<LooseParasiteMapping, LooseParasiteMapping>(leftPair.getSecond(), rightPair.getSecond()));
			}
		}
		
		Pair<Integer, LooseParasiteMapping> reverseRightPair = null != rightCostsForEachLevel[rightIndex] ? 
				new Pair<Integer, LooseParasiteMapping>(rightCostsForEachLevel[rightIndex].getCost(), rightCostsForEachLevel[rightIndex].getMapping()) : null;
		
		if (null != reverseLeftPair && null != reverseRightPair) {
			if (reverseLeftPair.getFirst() + reverseRightPair.getFirst() < cheapestPair.getFirst()) {
				cheapestPair = new Pair<Integer, Pair<LooseParasiteMapping, LooseParasiteMapping>>
				(reverseLeftPair.getFirst() + reverseRightPair.getFirst(), 
						new Pair<LooseParasiteMapping, LooseParasiteMapping>(reverseLeftPair.getSecond(), reverseRightPair.getSecond()));
			}
		}			
		
		return cheapestPair;
	}

	private int[] getLevelCosts(LevelCosts[] costsForEachLevel) {
		int[] levelCosts = new int[costsForEachLevel.length];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < costsForEachLevel.length; ++i) {
			levelCosts[i] = (null == costsForEachLevel[i]) ? Integer.MAX_VALUE : costsForEachLevel[i].getCost();
			max = Math.max(max, levelCosts[i]);
		}
		max = Math.max(levelCosts.length, max);
		int minCounter = 0;
		for (int i = costsForEachLevel.length-1; i >=0; --i) {
			levelCosts[i] = levelCosts[i] * max + ++minCounter;
		}
		return levelCosts;
	}
	
	private Pair<Edge, Edge> getEdgePair(Pair<Integer, Pair<LooseParasiteMapping, LooseParasiteMapping>> hostSwitch,
			Map<Node, Pair<Integer, Pair<Node, LooseParasiteMapping>>> leftCosts, Map<Node, Pair<Integer, 
			Pair<Node, LooseParasiteMapping>>> rightCosts, LevelCosts[] leftCostsForEachLevel,
			LevelCosts[] rightCostsForEachLevel, Event hostswitchdirection) {
		
		LooseParasiteMapping leftLooseParasiteMapping = Event.HostSwitchDirection1 == hostswitchdirection ? hostSwitch.getSecond().getFirst() : hostSwitch.getSecond().getSecond();
		LooseParasiteMapping rightLooseParasiteMapping = Event.HostSwitchDirection1 == hostswitchdirection ? hostSwitch.getSecond().getSecond() : hostSwitch.getSecond().getFirst();
	
		Pair<Edge, Edge> pair = null;
		
		int position = Math.min(leftLooseParasiteMapping.getBestForwardPosition(), rightLooseParasiteMapping.getBestForwardPosition());
		
		if (leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParent().getHeight() < position &&
				rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParent().getHeight() < position) {
			pair = new Pair<Edge, Edge>(leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge(), rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge());
		}		
		else if (leftLooseParasiteMapping.getBestForwardPosition() == rightLooseParasiteMapping.getBestForwardPosition()) {
			pair = new Pair<Edge, Edge>(leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge(), rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge());
		}
		else if (leftLooseParasiteMapping.getBestForwardPosition() < rightLooseParasiteMapping.getBestForwardPosition()) {
			LevelCosts rightTest = rightCostsForEachLevel[leftLooseParasiteMapping.getBestForwardPosition()];	
			if (null != rightTest) {
				pair = new Pair<Edge, Edge>(leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge(), rightTest.getNode().getParentEdge());
			}
		}
		else {
			LevelCosts leftTest = leftCostsForEachLevel[rightLooseParasiteMapping.getBestForwardPosition()];
			if (null != leftTest) {
				pair = new Pair<Edge, Edge>(leftTest.getNode().getParentEdge(), rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge());
			}
		}
		
		return pair;			
	}

	// ################################################################################################
	// Helper Methods

	protected Set<LooseParasiteMapping> returnReconstructionSet(Set<LooseParasiteMapping> currentSet) {
		return currentSet;
	}
	
	protected Set<LooseParasiteMapping> getReconstructionSet() {
		return new ImprovedReconstructionSet(this.history.getHostTree());	
	}
	
	protected Set<Node> getListOfInternalParasiteNodesToProcess() {
		Set<Node> nodes = new TreeSet<Node>(new DepthComparator());
		for (Node node : this.history.getParasiteTree().getAllNodes()) {
			if (null != node && !node.isLeafNode()) {
				nodes.add(node);
			}
		}		
		return nodes;
	}
	
	protected void mapLeafNodes() {
		for(Association association : this.history.getAssociations()) {
			this.parasiteMappingList.addLeafNode(association.getSecondNode(), association.getFirstNode());
		}
	}
	
	private void setBestForwardPosition(LooseParasiteMapping mapping, LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping) {
		int minimum = Math.min(this.history.getHostTree().getNodeHeight(mapping.getCurrentMappingPoint().getMappedPointAsNode()), Math.min(leftLooseParasiteMapping.getBestForwardPosition(), rightLooseParasiteMapping.getBestForwardPosition()));
		mapping.setBestForwardPosition(minimum);		
	}
	
}
