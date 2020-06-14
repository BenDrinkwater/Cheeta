package reconstruction.solver.nm;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import association.Association;
import reconstruction.LooseParasiteMapping;
import reconstruction.LooseParasiteMappingList;
import reconstruction.MappingPoint;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.tc.TreeCollapseUtil;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.Node;
import tree.RootedBifurcatingTree;
import util.NoNullLinkedList;
import util.Pair;
import util.comparators.DepthComparator;
import util.reconstruction.ImprovedReconstructionSet;

public class ReconstructionGenerator extends TreeCollapseUtil {

	public LooseParasiteMappingList parasiteMappingList;
	protected CoevolutionaryHistory history;
	
	protected int costOfCodivergenceEvent;
	protected int costOfDuplicationEvent;
	protected int costOfSortingEvent;
	protected int costOfSwitchEvent;
	
	protected int[] costScheme;
	
	protected Node currentlyProcessing;
	
	protected int totalNumberOfMaps;
	
	protected void initLooseParasiteMappingList() {
		this.parasiteMappingList = new LooseParasiteMappingList();
	}
	
	public ReconstructionGenerator(CoevolutionaryHistory history, int[] costs) {
		this.history = history;
		this.costOfCodivergenceEvent = costs[0];
		this.costOfDuplicationEvent = costs[1];
		this.costOfSortingEvent = costs[2];
		this.costOfSwitchEvent = costs[3];
		this.costScheme = costs;
		this.totalNumberOfMaps = 0;
		this.initLooseParasiteMappingList();
	}
	
	public ParasiteMappingList getParasiteMappingList() {
		this.generateReconstruction();
		return this.parasiteMappingList.generatorMappingList(this.history);
	}
	
	public void getReconstruction() {
		this.generateReconstruction();
	}
	
	protected void generateReconstruction() {
		this.history.getParasiteTree().setRandomUniqueNodeTimings();
		this.mapLeafNodes();
		Set<Node> toProcess = this.getListOfInternalParasiteNodesToProcess();
		while (toProcess.size() > 0) {
			this.currentlyProcessing = toProcess.iterator().next();
			toProcess.remove(currentlyProcessing);
			
			Set<LooseParasiteMapping> solutions = this.addToSolutions(this.currentlyProcessing);
			
			this.totalNumberOfMaps += solutions.size();
			
			if (null != solutions && solutions.size() > 0) {
				this.parasiteMappingList.add(this.currentlyProcessing, solutions);
			}
		}
	}
	
	protected Set<LooseParasiteMapping> addToSolutions(Node currentlyProcessing) {
		Set<LooseParasiteMapping> solutions = this.getReconstructionSet();
		solutions.addAll(this.getMapping(currentlyProcessing, LooseParasiteMapping.Event.Codivergence));
		solutions.addAll(this.getMapping(currentlyProcessing, LooseParasiteMapping.Event.Duplication));	
		solutions.addAll(this.getMapping(currentlyProcessing, LooseParasiteMapping.Event.HostSwitchDirection1));		
		solutions.addAll(this.getMapping(currentlyProcessing, LooseParasiteMapping.Event.HostSwitchDirection2));
		
		return this.returnReconstructionSet(solutions);
	}
	
	public int getNumberOfMaps() {
		return this.totalNumberOfMaps;
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
			this.totalNumberOfMaps++;
		}
	}
	
	protected Set<LooseParasiteMapping> getMapping(Node parasiteNode, LooseParasiteMapping.Event event) {
		Set<LooseParasiteMapping> bestSolutions = getReconstructionSet();
		if (this.parasiteMappingList.looseParasiteMappingIsLeafNode(parasiteNode.getLeftChild()) && this.parasiteMappingList.looseParasiteMappingIsLeafNode(parasiteNode.getRightChild())) {
			LooseParasiteMapping leftLooseParasiteMapping = this.parasiteMappingList.getLooseParasiteMapping(parasiteNode.getLeftChild(), LooseParasiteMapping.Event.LeafNodeMapping);
			LooseParasiteMapping rightLooseParasiteMapping = this.parasiteMappingList.getLooseParasiteMapping(parasiteNode.getRightChild(), LooseParasiteMapping.Event.LeafNodeMapping);
			LooseParasiteMapping mapping = this.getMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping, event);
			if (null != mapping) {
				bestSolutions.add(mapping);
			}
		}
		else {
			List<LooseParasiteMapping> solutions = new NoNullLinkedList<LooseParasiteMapping>();
			if (this.parasiteMappingList.looseParasiteMappingIsLeafNode(parasiteNode.getLeftChild())) {
				LooseParasiteMapping leftLooseParasiteMapping = this.parasiteMappingList.getLooseParasiteMapping(parasiteNode.getLeftChild(), LooseParasiteMapping.Event.LeafNodeMapping);	
				for (LooseParasiteMapping rightLooseParasiteMapping : this.parasiteMappingList.getLooseParasiteMappings(parasiteNode.getRightChild())) {
					if (null == rightLooseParasiteMapping) {
						continue;
					}
					LooseParasiteMapping mapping = this.getMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping, event);
					solutions.add(mapping);
				}			
			}
			else if (this.parasiteMappingList.looseParasiteMappingIsLeafNode(parasiteNode.getRightChild())) {
				LooseParasiteMapping rightLooseParasiteMapping = this.parasiteMappingList.getLooseParasiteMapping(parasiteNode.getRightChild(), LooseParasiteMapping.Event.LeafNodeMapping);
				for (LooseParasiteMapping leftLooseParasiteMapping : this.parasiteMappingList.getLooseParasiteMappings(parasiteNode.getLeftChild())) {
					if (null == leftLooseParasiteMapping) {
						continue;
					}
					LooseParasiteMapping mapping = this.getMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping, event);
					solutions.add(mapping);
				}			
			}
			else {
				for (LooseParasiteMapping leftLooseParasiteMapping : this.parasiteMappingList.getLooseParasiteMappings(parasiteNode.getLeftChild())) {
					if (null == leftLooseParasiteMapping) {
						continue;
					}			
					for (LooseParasiteMapping rightLooseParasiteMapping : this.parasiteMappingList.getLooseParasiteMappings(parasiteNode.getRightChild())) {
						if (null == rightLooseParasiteMapping) {
							continue;
						}
						LooseParasiteMapping mapping = this.getMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping, event);
						solutions.add(mapping);
					}	
				}
			}
			bestSolutions.addAll(solutions);			
		}
		return returnReconstructionSet(bestSolutions);
	}
	
	protected Set<LooseParasiteMapping> getReconstructionSet() {
		return new ImprovedReconstructionSet(this.history.getHostTree());	
	}
	
	protected Set<LooseParasiteMapping> returnReconstructionSet(Set<LooseParasiteMapping> currentSet) {
		return currentSet;
	}
	
	protected LooseParasiteMapping getMappingPoint(LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping, LooseParasiteMapping.Event event) {
		if (leftLooseParasiteMapping.getCost() == Integer.MAX_VALUE || rightLooseParasiteMapping.getCost() == Integer.MAX_VALUE) {
			return null;
		}
		switch(event) {
			case Codivergence : return this.codivergenceMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping);
			case Duplication : return this.duplicationMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping);
			case HostSwitchDirection1 : return this.hostSwitchMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping, event);
			case HostSwitchDirection2 : return this.hostSwitchMappingPoint(leftLooseParasiteMapping, rightLooseParasiteMapping, event);
			default: return null;
		}
	}
	
	protected LooseParasiteMapping codivergenceMappingPoint(LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping) {
		Node left = leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		Node right = rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		
		Node mappingPoint = this.history.getHostTree().getLowestCommonAncestor(left, right);
		
		int mappingPointIndex = mappingPoint.isLeafNode() ? mappingPoint.getLeafLabel() : mappingPoint.getHeight();
		int leftIndex = left.isLeafNode() ? left.getLeafLabel() : left.getHeight();
		int rightIndex = right.isLeafNode() ? right.getLeafLabel() : right.getHeight();
		
		if (mappingPointIndex == leftIndex || mappingPointIndex == rightIndex) {
			return null;
		}
		
		LooseParasiteMapping mapping = new LooseParasiteMapping(new MappingPoint(mappingPoint), LooseParasiteMapping.Event.Codivergence, leftLooseParasiteMapping, rightLooseParasiteMapping);			
		int numberOfLossEvents = this.history.getHostTree().distanceBetweenNodes(mappingPoint, left) 
				+ this.history.getHostTree().distanceBetweenNodes(mappingPoint, right);
		mapping.addCost(this.costOfCodivergenceEvent + this.costOfSortingEvent * numberOfLossEvents);
		this.setBestForwardPosition(mapping, leftLooseParasiteMapping, rightLooseParasiteMapping);			
		return mapping;
	}
	
	protected LooseParasiteMapping duplicationMappingPoint(LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping) {
		Node leftChid = leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		Node rightChild = rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		
		Node commonAncestor = this.history.getHostTree().getLowestCommonAncestor(leftChid, rightChild);	
		Edge mappingPoint = commonAncestor.getParentEdge();
		LooseParasiteMapping mapping = new LooseParasiteMapping(new MappingPoint(mappingPoint), LooseParasiteMapping.Event.Duplication, leftLooseParasiteMapping, rightLooseParasiteMapping);
		
		int numberOfLossEvents = this.numberOfLossEventsForDuplicationMapping(mapping, leftLooseParasiteMapping, rightLooseParasiteMapping);
		mapping.addCost(this.costOfDuplicationEvent + this.costOfSortingEvent * numberOfLossEvents);
		this.setBestForwardPosition(mapping, leftLooseParasiteMapping, rightLooseParasiteMapping);
		return mapping;
	}
	
	private int numberOfLossEventsForDuplicationMapping(LooseParasiteMapping mapping, LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping) {
		int numberOfLossEvents = 0;
		
		Node mappingPointAsNode = mapping.getCurrentMappingPoint().getMappedPointAsNode();
		Node left = leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		Node right = rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		
		Integer numberOfNodesBetweenParentAndLeftChild = this.history.getHostTree().distanceBetweenNodes(mappingPointAsNode, left);
		Integer numberOfNodesBetweenParentAndRightChild = this.history.getHostTree().distanceBetweenNodes(mappingPointAsNode, right);
		
		int mappingPointIndex = mappingPointAsNode.isLeafNode() ? mappingPointAsNode.getLeafLabel() : mappingPointAsNode.getHeight();
		int leftIndex = left.isLeafNode() ? left.getLeafLabel() : left.getHeight();
		int rightIndex = right.isLeafNode() ? right.getLeafLabel() : right.getHeight();
		
		if (null != numberOfNodesBetweenParentAndLeftChild && numberOfNodesBetweenParentAndLeftChild > 0) {
			numberOfLossEvents += numberOfNodesBetweenParentAndLeftChild;
		}
		
		if (null != numberOfNodesBetweenParentAndRightChild && numberOfNodesBetweenParentAndRightChild > 0) {
			numberOfLossEvents += numberOfNodesBetweenParentAndRightChild;
		}
		
		if (mappingPointIndex != leftIndex) {
			numberOfLossEvents += 1;
		}
		if (mappingPointIndex != rightIndex) {
			numberOfLossEvents += 1;
		}	
		return numberOfLossEvents;
	}
	
	protected LooseParasiteMapping hostSwitchMappingPoint(LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping, LooseParasiteMapping.Event switchType) {
		Pair<Edge, Edge> edgePair = this.getEdgePair(leftLooseParasiteMapping, rightLooseParasiteMapping);
		
		if (null == edgePair || null == edgePair.getFirst() || null == edgePair.getSecond()) {
			return null;
		}
		
		Node firstEdgeFirstNode = edgePair.getFirst().getParent();
		Node firstEdgeSecondNode = edgePair.getFirst().getChild();
		Node secondEdgeFirstNode = edgePair.getSecond().getParent();
		Node secondEdgeSecondNode = edgePair.getSecond().getChild();
		
		int firstEdgeFirstNodeIndex = firstEdgeFirstNode.isLeafNode() ? firstEdgeFirstNode.getLeafLabel() : firstEdgeFirstNode.getHeight();
		int firstEdgeSecondNodeIndex = firstEdgeSecondNode.isLeafNode() ? firstEdgeSecondNode.getLeafLabel() : firstEdgeSecondNode.getHeight();
		int secondEdgeFirstNodeIndex = secondEdgeFirstNode.isLeafNode() ? secondEdgeFirstNode.getLeafLabel() : secondEdgeFirstNode.getHeight();
		int secondEdgeSecondNodeIndex = secondEdgeSecondNode.isLeafNode() ? secondEdgeSecondNode.getLeafLabel() : secondEdgeSecondNode.getHeight();
		
		if (firstEdgeFirstNodeIndex == secondEdgeFirstNodeIndex && firstEdgeSecondNodeIndex == secondEdgeSecondNodeIndex) {
			return null;
		}
		
		Pair<Edge, Edge> mappingPoint = (LooseParasiteMapping.Event.HostSwitchDirection1.equals(switchType)) ? new Pair<Edge, Edge>(edgePair.getFirst(), edgePair.getSecond()) : new Pair<Edge, Edge>(edgePair.getSecond(), edgePair.getFirst());
		LooseParasiteMapping mapping = new LooseParasiteMapping(new MappingPoint(mappingPoint), switchType, leftLooseParasiteMapping, rightLooseParasiteMapping);
		int numberOfLossEvents = this.numberOfLossEventsForHostSwitch(mapping, leftLooseParasiteMapping, rightLooseParasiteMapping);
		mapping.addCost(this.costOfSwitchEvent + this.costOfSortingEvent * numberOfLossEvents);
		this.setBestForwardPosition(mapping, leftLooseParasiteMapping, rightLooseParasiteMapping);
		return mapping;
	}
	
	// cache this
	protected Pair<Edge, Edge> getEdgePair(LooseParasiteMapping leftLooseParasiteMapping, 
			LooseParasiteMapping rightLooseParasiteMapping) {	
		Edge startingEdge = leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge();
		Edge landingEdge = rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge();
		
		Map<Edge, Edge[]> edgeRef = ((RootedBifurcatingTree) this.history.getHostTree()).getEdgeReference();
		
		if (null == edgeRef) {		
			if (0 == leftLooseParasiteMapping.getBestForwardPosition() || 0 == rightLooseParasiteMapping.getBestForwardPosition()) {
				return null;
			}
			
			if (startingEdge.isFloatingEdge() || landingEdge.isFloatingEdge()) {
				return null;
			}
			if (leftLooseParasiteMapping.getBestForwardPosition() == rightLooseParasiteMapping.getBestForwardPosition()) {
				return new Pair<Edge, Edge> (startingEdge, landingEdge);
			}
			int maximumForwardPosition = Math.min(leftLooseParasiteMapping.getBestForwardPosition(), 
					rightLooseParasiteMapping.getBestForwardPosition());
			if (startingEdge.getParent().distanceToRoot() >= maximumForwardPosition) {
				while(startingEdge.getParent().distanceToRoot() >= maximumForwardPosition) {
					startingEdge = startingEdge.getParent().getParentEdge();
				}
			}
			if (landingEdge.getParent().distanceToRoot() >= maximumForwardPosition) {
				while(landingEdge.getParent().distanceToRoot() >= maximumForwardPosition) {
					landingEdge = landingEdge.getParent().getParentEdge();
				}			
			}
			if (startingEdge.getChild().distanceToRoot() <= landingEdge.getParent().distanceToRoot()) {
				while(startingEdge.getChild().distanceToRoot() <= landingEdge.getParent().distanceToRoot()) {
					landingEdge = landingEdge.getParent().getParentEdge();
				}
			}
			else if (startingEdge.getParent().distanceToRoot() >= landingEdge.getChild().distanceToRoot()) {
				while(startingEdge.getParent().distanceToRoot() >= landingEdge.getChild().distanceToRoot()) {
					startingEdge = startingEdge.getParent().getParentEdge();
				}
			}
			return new Pair<Edge, Edge> (startingEdge, landingEdge);
		}
		else {
			if (0 == leftLooseParasiteMapping.getBestForwardPosition() || 0 == rightLooseParasiteMapping.getBestForwardPosition()) {
				return null;
			}
			
			if (startingEdge.isFloatingEdge() || landingEdge.isFloatingEdge()) {
				return null;
			}
			if (leftLooseParasiteMapping.getBestForwardPosition() == rightLooseParasiteMapping.getBestForwardPosition()) {
				return new Pair<Edge, Edge> (startingEdge, landingEdge);
			}
			int maximumForwardPosition = Math.min(leftLooseParasiteMapping.getBestForwardPosition(), 
					rightLooseParasiteMapping.getBestForwardPosition());
			if (startingEdge.getParent().distanceToRoot() >= maximumForwardPosition) {
				startingEdge = edgeRef.get(startingEdge)[maximumForwardPosition];
			}
			else {
				landingEdge = edgeRef.get(landingEdge)[maximumForwardPosition];
			}
			return new Pair<Edge, Edge> (startingEdge, landingEdge);
		}
	}
	
	protected int numberOfLossEventsForHostSwitch(LooseParasiteMapping mapping, LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping) {
		int numberOfLossEvents = 0;
		Node firstEdgeMappedHostNode = mapping.getCurrentMappingPoint().getMappedPointAsNode();
		Node secondEdgeMappedHostNode = mapping.getCurrentMappingPoint().getSecondHostNode();
		
		Node hostNodeMappedToLeftChild = leftLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		Node hostNodeMappedToRightChild = rightLooseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode();
		
		numberOfLossEvents += this.processFirstHostSwitchPair(firstEdgeMappedHostNode, hostNodeMappedToLeftChild, hostNodeMappedToRightChild);	
		numberOfLossEvents += this.processFirstHostSwitchPair(secondEdgeMappedHostNode, hostNodeMappedToLeftChild, hostNodeMappedToRightChild);		
		return numberOfLossEvents;
	}
	
	protected int processFirstHostSwitchPair(Node parent, Node leftChild, Node rightChild) {
		int numberOfLossEvents = 0;
		Integer numberOfNodesBetweenParentAndLeftChild = this.history.getHostTree().distanceBetweenNodes(parent, leftChild);
		Integer numberOfNodesBetweenParentAndRightChild = this.history.getHostTree().distanceBetweenNodes(parent, rightChild);
		
		int parentIndex = parent.isLeafNode() ? parent.getLeafLabel() : parent.getHeight();
		int leftIndex = leftChild.isLeafNode() ? leftChild.getLeafLabel() : leftChild.getHeight();
		int rightIndex = rightChild.isLeafNode() ? rightChild.getLeafLabel() : rightChild.getHeight();
		
		if (null != numberOfNodesBetweenParentAndLeftChild) {
			numberOfLossEvents += numberOfNodesBetweenParentAndLeftChild;
		}
		else if (!(parentIndex == rightIndex)) {
			numberOfLossEvents++;
		}
		
		if (null != numberOfNodesBetweenParentAndRightChild) {
			numberOfLossEvents += numberOfNodesBetweenParentAndRightChild;
		}
		else if (!(parentIndex == leftIndex)) {
			numberOfLossEvents++;
		}
		return numberOfLossEvents;

	}
	
	protected void setBestForwardPosition(LooseParasiteMapping mapping, LooseParasiteMapping leftLooseParasiteMapping, LooseParasiteMapping rightLooseParasiteMapping) {
		int minimum = Math.min(mapping.getCurrentMappingPoint().getMappedPointAsNode().distanceToRoot(), Math.min(leftLooseParasiteMapping.getBestForwardPosition(), rightLooseParasiteMapping.getBestForwardPosition()));
		mapping.setBestForwardPosition(minimum);
	}
}
