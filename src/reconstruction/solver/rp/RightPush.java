package reconstruction.solver.rp;

import java.util.LinkedHashMap;
import java.util.Map;

import reconstruction.MappingPoint;
import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.Node;
import util.Pair;

public class RightPush {

	protected enum Child {
		Left,
		Right
	}
	
	protected ParasiteMappingList parasiteMapping;
	protected CoevolutionaryHistory history;
	private Map<Node, Integer> bestForwardPosition;
	
	private RightPush() {
		this.bestForwardPosition = new LinkedHashMap<Node, Integer>();
	}
	
	public RightPush(CoevolutionaryHistory history, ParasiteMappingList parasiteMapping) {
		this();
		this.history = history;
		this.parasiteMapping = parasiteMapping;
	}
	
	public ParasiteMappingList getLossMinimisedParasiteMapping() {
		for (Node node : this.parasiteMapping) {
			if (this.parasiteMapping.getMappedPoint(node).mappingPointIsHostSwitchPair()) {				
				@SuppressWarnings("unchecked")
				Pair<Edge, Edge> hostSwitch = (Pair<Edge, Edge>) this.parasiteMapping.getMappedPoint(node).getMappedPoint();
				Pair<Edge, Integer> bestForwardPositionForStartingEdge = this.getStartingEdgeBestForwardPosition(hostSwitch.getFirst(), node);
				Pair<Edge, Integer> bestForwardPositionForLandingEdge = this.getLandingEdgeBestForwardPosition(hostSwitch.getSecond(), node);
				
				Pair<Edge, Edge> updatedHostSwitch = this.updateSwitch(bestForwardPositionForStartingEdge, bestForwardPositionForLandingEdge);
				
				if (!(hostSwitch.equals(updatedHostSwitch))) {
					this.parasiteMapping.updateHostSwitchEvent(node, new MappingPoint(updatedHostSwitch));
				}
				
				this.bestForwardPosition.put(node, Math.min(bestForwardPositionForStartingEdge.getSecond(), bestForwardPositionForLandingEdge.getSecond()));
			}
			else if (this.parasiteMapping.getMappedPoint(node).mappingPointIsNode()){
				Node hostTreeNode = this.parasiteMapping.getMappedPoint(node).getMappedPointAsNode();
				this.bestForwardPosition.put(node, this.history.getHostTree().getNodeWithPrefix(hostTreeNode.getNodeName()).distanceToRoot());
			}
			else {
				Node parasiteNode = this.history.getParasiteTree().getNodeWithPrefix(node.getNodeName());
				if (null == this.bestForwardPosition.get(parasiteNode.getLeftChild()) || null == this.bestForwardPosition.get(parasiteNode.getRightChild())) {
					this.bestForwardPosition.put(node, 0);
				}
				else {
					this.bestForwardPosition.put(node, Math.min(this.bestForwardPosition.get(parasiteNode.getLeftChild()), this.bestForwardPosition.get(parasiteNode.getRightChild())));
				}
			}
		}
		return this.parasiteMapping;
	}
	
	protected Pair<Edge, Edge> updateSwitch(Pair<Edge, Integer> bestForwardPositionForStartingEdge, Pair<Edge, Integer> bestForwardPositionForLandingEdge) {
		
		int mappingPoint = Math.min(bestForwardPositionForStartingEdge.getSecond(), bestForwardPositionForLandingEdge.getSecond());
		
		Edge startingEdge = bestForwardPositionForStartingEdge.getFirst();
		Edge landingEdge = bestForwardPositionForLandingEdge.getFirst();
		
		while (mappingPoint < startingEdge.getParent().distanceToRoot()) {
			startingEdge = startingEdge.getParent().getParentEdge();
		}
		while (mappingPoint < landingEdge.getParent().distanceToRoot()) {
			landingEdge = landingEdge.getParent().getParentEdge();
		}

		return new Pair<Edge, Edge> (startingEdge, landingEdge);
	}
	
	protected Pair<Node, Child> getChildOfSwitchSite(Edge edge, Node mappedNode) {
		Node node = this.history.getParasiteTree().getNodeWithPrefix(mappedNode.getNodeName());

		MappingPoint leftChildMappingPoint = this.parasiteMapping.getMappedPoint(node.getLeftChild());
		MappingPoint rightChildMappingPoint = this.parasiteMapping.getMappedPoint(node.getRightChild());		
		
		Node hostTreeNode1 = this.history.getHostTree().getNodeWithPrefix(leftChildMappingPoint.getMappedPointAsNode().getNodeName());
		Node hostTreeNode2 = this.history.getHostTree().getNodeWithPrefix(rightChildMappingPoint.getMappedPointAsNode().getNodeName());
		Node hostTreeNode3 = this.history.getHostTree().getNodeWithPrefix(edge.getChild().getNodeName());
		
		if (null == this.history.getHostTree().distanceBetweenNodes(hostTreeNode3, hostTreeNode1)) {
			return new Pair<Node, Child> (hostTreeNode2, Child.Right);
		}
		else {
			return new Pair<Node, Child> (hostTreeNode1, Child.Left);
		}
	}
	
	private Pair<Edge, Integer> getLandingEdgeBestForwardPosition(Edge landingEdge, Node mappedNode) {
		Node node = this.history.getParasiteTree().getNodeWithPrefix(mappedNode.getNodeName());
		Node mappedChildDistance = getChildOfSwitchSite(landingEdge, mappedNode).getFirst();
		Integer mappedChildPoint = getChildOfSwitchSite(landingEdge, mappedNode).getSecond().equals(Child.Left) ? this.bestForwardPosition.get(node.getLeftChild()) : this.bestForwardPosition.get(node.getRightChild());
		return new Pair<Edge, Integer> (getChildOfSwitchSite(landingEdge, mappedNode).getFirst().getParentEdge(), Math.min(mappedChildDistance.distanceToRoot(), mappedChildPoint));
	}
	
	private Pair<Edge, Integer> getStartingEdgeBestForwardPosition(Edge startingEdge, Node mappedNode) {
		Node node = this.history.getParasiteTree().getNodeWithPrefix(mappedNode.getNodeName());
		Node mappedChildDistance = getChildOfSwitchSite(startingEdge, mappedNode).getFirst();
		Integer mappedChildPoint = getChildOfSwitchSite(startingEdge, mappedNode).getSecond().equals(Child.Left) ? this.bestForwardPosition.get(node.getLeftChild()) : this.bestForwardPosition.get(node.getRightChild());
		return new Pair<Edge, Integer> (getChildOfSwitchSite(startingEdge, mappedNode).getFirst().getParentEdge(), Math.min(mappedChildDistance.distanceToRoot(), mappedChildPoint));
	}
}
