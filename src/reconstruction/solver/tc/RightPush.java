package reconstruction.solver.tc;

import util.Pair;
import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.Node;

public class RightPush {

	protected ParasiteMappingList parasiteMapping;
	protected CoevolutionaryHistory history; 
	
	private RightPush() {
	}
	
	public RightPush(CoevolutionaryHistory history, ParasiteMappingList parasiteMapping) {
		this();
		this.history = history;
		this.parasiteMapping = parasiteMapping;
	}
	
	public void pushHostSwitchesToTheRight() {
		for (Node parasiteNode : this.parasiteMapping) {
			if (this.parasiteMapping.getMappedPoint(parasiteNode).mappingPointIsHostSwitchPair()) {
				@SuppressWarnings("unchecked")
				Edge startingEdge = ((Pair<Edge, Edge>) this.parasiteMapping.getMappedPoint(parasiteNode).getMappedPoint()).getFirst();
				@SuppressWarnings("unchecked")
				Edge landingEdge = ((Pair<Edge, Edge>) this.parasiteMapping.getMappedPoint(parasiteNode).getMappedPoint()).getSecond();;
				
				int node1DistanceToRoot = startingEdge.getChild().distanceToRoot();
				int node2DistanceToRoot = landingEdge.getChild().distanceToRoot();
				
				Node leftChildMappingPoint = this.parasiteMapping.getMappedPoint(this.history.getParasiteTree().getNodeWithPrefix(parasiteNode.getNodeName()).getLeftChild()).getMappedPointAsNode();
				Node rightChildMappingPoint = this.parasiteMapping.getMappedPoint(this.history.getParasiteTree().getNodeWithPrefix(parasiteNode.getNodeName()).getRightChild()).getMappedPointAsNode();
				
				Node switchedToChild = null != this.history.getHostTree().distanceBetweenNodes(landingEdge.getChild(), leftChildMappingPoint) ? leftChildMappingPoint : rightChildMappingPoint;
				
				if (!switchedToChild.equals(landingEdge.getChild())) {
					if (node1DistanceToRoot > node2DistanceToRoot && node1DistanceToRoot >= switchedToChild.distanceToRoot()) {
						this.parasiteMapping.updateLandingEdgeForHostSwitchEvent(parasiteNode, switchedToChild.getParentEdge());
					}
					else if (node1DistanceToRoot > node2DistanceToRoot) {
						Node childOfEdge = switchedToChild;
						while (node1DistanceToRoot < childOfEdge.distanceToRoot() && !landingEdge.getChild().equals(childOfEdge)) {
							childOfEdge = childOfEdge.getParent();
						}
						if (!landingEdge.getChild().equals(childOfEdge)) {
							this.parasiteMapping.updateLandingEdgeForHostSwitchEvent(parasiteNode, childOfEdge.getParentEdge());
						}
					}
				}
			}
		}
	}
	
	public ParasiteMappingList getNewParasiteMapping() {
		return this.parasiteMapping;
	}
	
}
