package reconstruction.solver.tc;

import util.Pair;

import java.util.Set;
import java.util.TreeSet;

import association.Association;

import reconstruction.MappingPoint;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.tc.TreeCollapser.EdgeResolver;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.LeafNodePair;
import tree.Node;

public class ReconstructionGenerator extends TreeCollapseUtil {

	private CoevolutionaryHistory history;
	private int maxNumberOfIterations;
	private ParasiteMappingList parasiteMappingList;
	private Set<LeafNodePair> queue;

	private ReconstructionGenerator() {
		this.maxNumberOfIterations = 0;
		this.parasiteMappingList = new ParasiteMappingList();
	}
	
	public ReconstructionGenerator(CoevolutionaryHistory history) {
		this();
		if (null != history) {
			this.history = history.cloneHistory();
			this.maxNumberOfIterations = this.history.getHostTree().size() + this.history.getParasiteTree().size();
		}
		
		this.mapLeafNodes();
		
		queue = this.getInitalListOfValidLeafNodePairs();
	}	
	
	public ParasiteMappingList generatePartialReconstruction() {
		int iterations = 0;
		
		while (++iterations <= this.maxNumberOfIterations && this.continueReconstruction()) {			
			TreeShapeDetector treeShapeDetector = new TreeShapeDetector(this.history);
			
			LeafNodePair toProcess = null; 
			
			if (queue.iterator().hasNext()) {
				toProcess = queue.iterator().next();
			}
			
			if (null == toProcess && 1 == this.history.getHostTree().size()) {
				this.processParasoteCircleForVirtualEdge();
			}
			else if (null == toProcess) {
				continue;
			}
			else {
				switch (treeShapeDetector.treeShapeType(toProcess)) {
					case ParasiteCircle: {
							this.processParasiteCircle(treeShapeDetector);
						}
						break;
					case HostSwitchAndStrongLink: {
							this.prcoessSwitchPath(treeShapeDetector);
						}
						break;
					case StrongLink: {
							LeafNodePair pair = this.processStrongLink(treeShapeDetector, toProcess);
							if (null != pair && pair.isValidLeafNodePair()) {
								queue.add(pair);
							}
							queue.remove(toProcess);
						}
						break;
					case DuplicationSwitch: {
							this.prcoessSwitchPath(treeShapeDetector);						
						}
						break;	
					case DoubleSwitchPathWithCommonLandingSite : {						
							this.prcoessDoubleSwitchWithCommonLandingSite(treeShapeDetector);						
						}
						break;				
					case DoubleSwitchPath : {
							this.processDoubleSwitchPath(treeShapeDetector);
						}
						break;
					case SwitchPath: {
							this.prcoessSwitchPath(treeShapeDetector);
						}
						break;
					default: {
							LeafNodePair pair = this.processLossEvent(toProcess);
							if (null != pair && pair.isValidLeafNodePair()) {
								queue.add(pair);
							}
							queue.remove(toProcess);
						}
						break;
				}
			}
		}
		return this.parasiteMappingList;
	}

	private void mapLeafNodes() {
		for(Association association : this.history.getAssociations()) {
			this.parasiteMappingList.addLeafNode(association.getSecondNode(), new MappingPoint(association.getFirstNode()));
		}
	}

	protected Set<LeafNodePair> getInitalListOfValidLeafNodePairs() {
		Set<LeafNodePair> leafNodePairs = new TreeSet<LeafNodePair>();
		for (Node leafNode : this.history.getHostTree().getLeafNodes()) {
			Node node = super.alternateNodeInNodePair(leafNode);
			if (null != node) {
				LeafNodePair leafNodePair = new LeafNodePair(leafNode, node);
				if (null != leafNodePair && leafNodePair.isValidLeafNodePair()) {
					leafNodePairs.add(leafNodePair);
				}
			}
		}		
		return leafNodePairs;
	}
	
	protected void processParasiteCircle(TreeShapeDetector treeShapeDetector) {
		this.parasiteMappingList.add((Node) treeShapeDetector.getShapeSpecificFeatures().get(2), new MappingPoint((Edge) treeShapeDetector.getShapeSpecificFeatures().get(1)));
		new TreeCollapser(this.history).collapseParasiteTree((Node) treeShapeDetector.getShapeSpecificFeatures().get(0), EdgeResolver.resolveBoth);
	}
	
	protected void processParasoteCircleForVirtualEdge() {
		Node hostNode = this.history.getHostTree().getRootNode();
		Node parasiteLeafNode = this.history.getAssociations().getAssociationsForHostNode(hostNode).iterator().next().getSecondNode();
		this.parasiteMappingList.add(parasiteLeafNode.getParent(), new MappingPoint(hostNode.getParentEdge()));
		new TreeCollapser(this.history).collapseParasiteTree(parasiteLeafNode, EdgeResolver.resolveBoth);
	}
	
	protected LeafNodePair processStrongLink(TreeShapeDetector treeShapeDetector, LeafNodePair leafNodePair) {
		Node hostNode = leafNodePair.getFirst();
		Node parent = leafNodePair.getParent();
		for (int i = 0; i < treeShapeDetector.getShapeSpecificFeatures().size(); i += 3) {	
			Node parasiteNode = (Node) treeShapeDetector.getShapeSpecificFeatures().get(i);
			new TreeCollapser(this.history).collapseParasiteTree(parasiteNode, EdgeResolver.resolveBoth); 
			this.parasiteMappingList.add((Node) treeShapeDetector.getShapeSpecificFeatures().get(i+2), new MappingPoint((Node) treeShapeDetector.getShapeSpecificFeatures().get(i+1)));
		}
		// need to update this to check for other possible strong links first
		new TreeCollapser(this.history).collapseHostTree(hostNode);
		
		return new LeafNodePair(parent, super.alternateNodeInNodePair(parent));
	}
	
	@SuppressWarnings("unchecked")
	private void prcoessDoubleSwitchWithCommonLandingSite(TreeShapeDetector treeShapeDetector) {
		Node collapsePoint1 = (Node) treeShapeDetector.getShapeSpecificFeatures().get(0);
		Node collapsePoint2 = (Node) treeShapeDetector.getShapeSpecificFeatures().get(3);
		
		this.parasiteMappingList.add((Node) treeShapeDetector.getShapeSpecificFeatures().get(1), new MappingPoint((Pair<Edge, Edge>) treeShapeDetector.getShapeSpecificFeatures().get(2)));
		this.parasiteMappingList.add((Node) treeShapeDetector.getShapeSpecificFeatures().get(4), new MappingPoint((Pair<Edge, Edge>) treeShapeDetector.getShapeSpecificFeatures().get(5)));
		
		new TreeCollapser(this.history).collapseParasiteTree(collapsePoint1, EdgeResolver.onlyResolveFirst); 
		new TreeCollapser(this.history).collapseParasiteTree(collapsePoint2, EdgeResolver.onlyResolveFirst); 
	}
	
	private void processDoubleSwitchPath(TreeShapeDetector treeShapeDetector) {
		Node firstCollapsePoint = (Node) treeShapeDetector.getShapeSpecificFeatures().get(0);
		Node secondCollapsePoint = (Node) treeShapeDetector.getShapeSpecificFeatures().get(1);
		
		Node firstMappingPoint = (Node) treeShapeDetector.getShapeSpecificFeatures().get(2);
		Node secondMappingPoint = (Node) treeShapeDetector.getShapeSpecificFeatures().get(3);
		
		Pair<Edge, Edge> firstHostSwitch = new Pair<Edge, Edge>((Edge) treeShapeDetector.getShapeSpecificFeatures().get(4), (Edge) treeShapeDetector.getShapeSpecificFeatures().get(5));
		Pair<Edge, Edge> secondHostSwitch = new Pair<Edge, Edge>((Edge) treeShapeDetector.getShapeSpecificFeatures().get(5), (Edge) treeShapeDetector.getShapeSpecificFeatures().get(6));

		this.parasiteMappingList.add(secondMappingPoint, new MappingPoint(secondHostSwitch));
		this.parasiteMappingList.add(firstMappingPoint, new MappingPoint(firstHostSwitch));
		
		new TreeCollapser(this.history).collapseParasiteTree(firstCollapsePoint, EdgeResolver.onlyResolveSecond); 
		new TreeCollapser(this.history).collapseParasiteTree(secondCollapsePoint, EdgeResolver.onlyResolveSecond); 
	}	
	
	@SuppressWarnings("unchecked")
	protected void prcoessSwitchPath(TreeShapeDetector treeShapeDetector) {
		Node parasiteNode = (Node) treeShapeDetector.getShapeSpecificFeatures().get(0);
		this.parasiteMappingList.add((Node) treeShapeDetector.getShapeSpecificFeatures().get(1), new MappingPoint((Pair<Edge, Edge>) treeShapeDetector.getShapeSpecificFeatures().get(2)));
		new TreeCollapser(this.history).collapseParasiteTree(parasiteNode, EdgeResolver.onlyResolveFirst); 
	}
	
	protected LeafNodePair processLossEvent(LeafNodePair leafNodePair) {
		Node hostNode = leafNodePair.getFirst();
		Node parent = leafNodePair.getParent();
		new TreeCollapser(this.history).collapseHostTree(hostNode);
		return new LeafNodePair(parent, super.alternateNodeInNodePair(parent));
	}
	
	protected boolean continueReconstruction() {
		return (!(1 == this.history.getHostTree().size() &&
				1 == this.history.getParasiteTree().size()));
	}
}
