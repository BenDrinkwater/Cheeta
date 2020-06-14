package reconstruction.solver.tc;

import util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import association.Association;
import association.Associations;
import tanglegram.CoevolutionaryHistory;
import tree.Edge;
import tree.LeafNodePair;
import tree.Node;

public class TreeShapeDetector extends TreeCollapseUtil {

	private CoevolutionaryHistory history;
	private List<Object> shapeSpecificFeautres;
	
	public enum TreeShape {
		ParasiteCircle,
		HostSwitchAndStrongLink,
		StrongLink,
		DuplicationSwitch,
		SwitchPathWithLoss,
		DoubleSwitchPathWithCommonLandingSite,
		DoubleSwitchPath,
		SwitchPath,
		LossEvent
	}
	
	private TreeShapeDetector() {
		super();
		this.shapeSpecificFeautres = new ArrayList<Object>();
	}
	
	public TreeShapeDetector(CoevolutionaryHistory history) {
		this();
		this.history = history;
	}
	
	public TreeShape treeShapeType(LeafNodePair leafNodePair) {
		if (this.hasParasiteCircle(leafNodePair)) {
			return TreeShape.ParasiteCircle;
		}
		else if (this.hasSwitchPathWithLoss(leafNodePair)) {
			return TreeShape.SwitchPath;
		}	
		else if (this.isIncompleteNodePair(leafNodePair)) {
			return TreeShape.LossEvent;
		}
		else if (this.hasHostSwitchAndStrongLink(leafNodePair)) {
			return TreeShape.HostSwitchAndStrongLink;
		}
		else if (this.hasStrongLink(leafNodePair)) {
			return TreeShape.StrongLink;
		}
		else if (this.hasDoubleSwitchToLoss(leafNodePair)) {
			return TreeShape.SwitchPath;
		}
		else if (this.hasDoubleSwitchFromCommonStartingPoint(leafNodePair)) {
			return TreeShape.DoubleSwitchPathWithCommonLandingSite;
		}
		else if (this.hasDuplicationSwitch(leafNodePair)) {
			return TreeShape.DuplicationSwitch;
		}
		else if (this.hasDoubleSwitchPath(leafNodePair)) {
			return TreeShape.DoubleSwitchPath;
		}
		else if (this.hasSwitchPath(leafNodePair)) {
			return TreeShape.SwitchPath;
		}
		else {
			return TreeShape.LossEvent;
		}
	}

	protected boolean hasParasiteCircle(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		return this.hasParasiteCircle(leafNodePair.getFirst()) || this.hasParasiteCircle(leafNodePair.getSecond());
	}		
	
	protected boolean hasParasiteCircle(Node startAndFinishNode) {		
		for (Association association : this.history.getAssociations().getAssociationsForHostNode(startAndFinishNode)) {
			Node node = super.alternateNodeInNodePair(association.getSecondNode());
			
			Associations reverseAssociations 
					= this.history.getAssociations().getAssociationsForParasiteNode(node);
			
			for (Association reverseAssociation : reverseAssociations) {
				if (startAndFinishNode.equals(reverseAssociation.getFirstNode())) {
					
					// adding a parasite node the node pair to collapse
					this.shapeSpecificFeautres.add(reverseAssociation.getSecondNode());
					// edge duplication occurs on
					this.shapeSpecificFeautres.add(startAndFinishNode.getParentEdge());
					// the parasite node that is mapped as a duplication
					this.shapeSpecificFeautres.add(reverseAssociation.getSecondNode().getParent());
					return true;
				}
			}
		}
		
		return false;
	}
	
	protected boolean isIncompleteNodePair(LeafNodePair leafNodePair) {
		Associations associationsForFirstNode 
				= this.history.getAssociations().getAssociationsForHostNode(leafNodePair.getFirst());
		Associations associationsForSecondNode 
				= this.history.getAssociations().getAssociationsForHostNode(leafNodePair.getSecond());		
		return 0 == associationsForFirstNode.size() || 0 == associationsForSecondNode.size();
	}	
	
	protected boolean hasHostSwitchAndStrongLink(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		return this.hasHostSwitchAndStrongLinkEvent(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.hasHostSwitchAndStrongLinkEvent(leafNodePair.getSecond(), leafNodePair.getFirst());			
	}
	
	protected boolean hasHostSwitchAndStrongLinkEvent(Node start, Node finish) {
		if (this.hasStrongLink(start, finish)) {
			Associations associations 
				= this.history.getAssociations().getAssociationsForHostNode(start);

			Set<Node> blackListedLeafNodes = new HashSet<Node>();
			
			for (Association association : associations) {
				blackListedLeafNodes.add(association.getSecondNode());
			}
			
			for (Association association : associations) {
				Node node = super.alternateNodeForInternalNodePair(association.getSecondNode());
				
				if (node.equals(((Node) this.shapeSpecificFeautres.get(0)).getParent())) {
					Node collapsePoint = (Node) this.shapeSpecificFeautres.get(0);
					this.shapeSpecificFeautres = new ArrayList<Object>();
					
					if (blackListedLeafNodes.contains(collapsePoint)) {
						collapsePoint = new TreeCollapseUtil().alternateNodeForInternalNodePair(collapsePoint);
					}
					
					this.shapeSpecificFeautres.add(collapsePoint);
					this.shapeSpecificFeautres.add(node);
					this.shapeSpecificFeautres.add(new Pair<Edge,Edge> (finish.getParentEdge(), start.getParentEdge()));
					
					return true;
				}
			}		
		}
		return false;
	}

	protected boolean hasStrongLink(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		return this.hasStrongLink(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.hasStrongLink(leafNodePair.getSecond(), leafNodePair.getFirst());
	}	
	
	protected boolean hasStrongLink(Node start, Node finish) {	
		Associations associations 
				= this.history.getAssociations().getAssociationsForHostNode(start);
		
		boolean hasStrongLink = false;
		
		for (Association association : associations) {
			Node node = super.alternateNodeInNodePair(association.getSecondNode());
			
			Associations reverseAssociations 
					= this.history.getAssociations().getAssociationsForParasiteNode(node);
			
			for (Association reverseAssociation : reverseAssociations) {
				if (finish.equals(reverseAssociation.getFirstNode())) {
					hasStrongLink = true;
					// adding a parasite node the node pair to collapse
					this.shapeSpecificFeautres.add(reverseAssociation.getSecondNode());
					// adding parent node where the codivergence takes place
					this.shapeSpecificFeautres.add(start.getParent());
					// adding the parasite node that is to be mapped
					this.shapeSpecificFeautres.add(reverseAssociation.getSecondNode().getParent());
				}
			}
		}
		
		return hasStrongLink;
	}
	
	protected boolean hasSwitchPathWithLoss(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		boolean response = (this.isIncompleteNodePair(leafNodePair) && (this.hasSwitchPathWithLoss(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.hasSwitchPathWithLoss(leafNodePair.getSecond(), leafNodePair.getFirst())));
		return response;
	}
	
	protected boolean hasDoubleSwitchToLoss(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		return this.hasDoubleSwitchToLoss(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.hasDoubleSwitchToLoss(leafNodePair.getSecond(), leafNodePair.getFirst());
	}
	
	protected boolean hasDoubleSwitchFromCommonStartingPoint(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		if (this.multiSwitchPoint(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.multiSwitchPoint(leafNodePair.getSecond(), leafNodePair.getFirst())) {
			return false;
		}
		return this.hasDoubleSwitchFromCommonStartingPoint(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.hasDoubleSwitchFromCommonStartingPoint(leafNodePair.getSecond(), leafNodePair.getFirst());
	}

	@SuppressWarnings("unchecked")
	protected boolean hasDuplicationSwitch(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		if (this.hasSwitchPath(leafNodePair)) {
			Node startingPoint1 = ((Pair<Edge, Edge>) this.shapeSpecificFeautres.get(2)).getSecond().getNodePair().getSecond();
			Node startingPoint2 = ((Pair<Edge, Edge>) this.shapeSpecificFeautres.get(2)).getFirst().getNodePair().getSecond();
			return hasDuplicationSwitch(startingPoint1, startingPoint2,  (Node) this.shapeSpecificFeautres.get(0));
		}
		return false;
	}
	
	private boolean hasDuplicationSwitch(Node startingPoint1, Node startingPoint2, Node parasiteNodeStartingPoint) {
		if (null != parasiteNodeStartingPoint.getParent() && null != parasiteNodeStartingPoint.getParent().getParent()) {
			Node validationNode = parasiteNodeStartingPoint.getParent().getParent();
			Associations associations = this.history.getAssociations().getAssociationsForHostNode(startingPoint1);
			for (Association association : associations) {
				if (association.getSecondNode().getParent().equals(validationNode)) {
					this.shapeSpecificFeautres = new ArrayList<Object>();
					this.shapeSpecificFeautres.add(new TreeCollapseUtil().alternateNodeInNodePair(parasiteNodeStartingPoint));
					this.shapeSpecificFeautres.add(parasiteNodeStartingPoint.getParent());
					this.shapeSpecificFeautres.add(new Pair<Edge,Edge> (startingPoint1.getParentEdge(), startingPoint2.getParentEdge()));
					return true;
				}
			}
		}
		return false;
	}
	
	protected boolean hasSwitchPath(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		return this.hasSwitchPath(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.hasSwitchPath(leafNodePair.getSecond(), leafNodePair.getFirst());
	}
	
	protected boolean hasSwitchPath(Node startingPoint, Node invalidEndPoint) {
		Associations associations = this.history.getAssociations().getAssociationsForHostNode(startingPoint);
		
		for (Association association : associations) {
			Node node = super.alternateNodeInNodePair(association.getSecondNode());
			Associations reverseAssociations 
					= this.history.getAssociations().getAssociationsForParasiteNode(node);
			
			for (Association reverseAssociation : reverseAssociations) {
				if (!(invalidEndPoint.equals(reverseAssociation.getFirstNode()) 
						|| startingPoint.equals(reverseAssociation.getFirstNode()))) {
					
					// adding a parasite node the node pair to collapse
					this.shapeSpecificFeautres.add(association.getSecondNode());
					// the node that is mapped
					this.shapeSpecificFeautres.add(reverseAssociation.getSecondNode().getParent());
					// the host switch that is mapped to
					this.shapeSpecificFeautres.add(new Pair<Edge, Edge> (startingPoint.getParentEdge(), reverseAssociation.getFirstNode().getParentEdge()));
					return true;
				}
			}		
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private boolean hasSwitchPathWithLoss(Node first, Node second) {
		if (this.hasSwitchPath(first, second)) {
			Pair<Edge, Edge> hostSwitch = (Pair<Edge,Edge>) this.shapeSpecificFeautres.get(2);
			Node collapsePoint = new TreeCollapseUtil().alternateNodeInNodePair((Node) this.shapeSpecificFeautres.get(0));
			Node mappedNode = (Node) this.shapeSpecificFeautres.get(1);
			
			Node node1 = hostSwitch.getSecond().getParent();
			Node node2 = hostSwitch.getFirst().getParent();
			
			if (null != this.history.getHostTree().distanceBetweenNodes(node1, node2) && this.history.getHostTree().distanceBetweenNodes(node1, node2) == 1) {
				this.shapeSpecificFeautres = new ArrayList<Object>();
				this.shapeSpecificFeautres.add(collapsePoint);
				this.shapeSpecificFeautres.add(mappedNode);
				this.shapeSpecificFeautres.add(new Pair<Edge, Edge>(hostSwitch.getSecond(), hostSwitch.getFirst()));
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private boolean hasDoubleSwitchToLoss(Node first, Node second) {
		
		// TODO clean this method up
		
		if (this.hasSwitchPath(first, second) && this.hasSwitchPath(second, first)) {
			Pair<Edge, Edge> firstSwitch = (Pair<Edge, Edge>) this.shapeSpecificFeautres.get(2);
			Pair<Edge, Edge> secondSwitch = (Pair<Edge, Edge>) this.shapeSpecificFeautres.get(5);
			// test first switch for possibility of double switch
			if ((null != this.history.getHostTree().distanceBetweenNodes(firstSwitch.getSecond().getParent(), first)) && 
					(1 == this.history.getHostTree().distanceBetweenNodes(firstSwitch.getSecond().getParent(), first))) {
				Node collapsePoint = new TreeCollapseUtil().alternateNodeInNodePair((Node) this.shapeSpecificFeautres.get(3));
				Node mappedPoint = (Node) this.shapeSpecificFeautres.get(4);
				Pair<Edge, Edge> newSwitch = new Pair<Edge, Edge>(secondSwitch.getSecond(), secondSwitch.getFirst());
				this.shapeSpecificFeautres = new ArrayList<Object>();
				this.shapeSpecificFeautres.add(collapsePoint);
				this.shapeSpecificFeautres.add(mappedPoint);
				this.shapeSpecificFeautres.add(newSwitch);
				return true;
			}
			else if ((null != this.history.getHostTree().distanceBetweenNodes(secondSwitch.getSecond().getParent(), second)) && 
					(1 == this.history.getHostTree().distanceBetweenNodes(secondSwitch.getSecond().getParent(), second))) {
				Node collapsePoint = new TreeCollapseUtil().alternateNodeInNodePair((Node) this.shapeSpecificFeautres.get(0));
				Node mappedPoint = (Node) this.shapeSpecificFeautres.get(1);
				Pair<Edge, Edge> newSwitch = new Pair<Edge, Edge>(firstSwitch.getSecond(), firstSwitch.getFirst());
				this.shapeSpecificFeautres = new ArrayList<Object>();
				this.shapeSpecificFeautres.add(collapsePoint);
				this.shapeSpecificFeautres.add(mappedPoint);
				this.shapeSpecificFeautres.add(newSwitch);
				return true;
			}
		}
		return false;
	}
	
	private boolean multiSwitchPoint(Node first, Node second) {
		Associations associations = this.history.getAssociations().getAssociationsForHostNode(first);
		
		for (Association association : associations) {
			Node node = super.alternateNodeForInternalNodePair(association.getSecondNode().getParent());
			if (null == node) {
				return false;
			}
			return node.isLeafNode();	
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private boolean hasDoubleSwitchFromCommonStartingPoint(Node first, Node second) {
/*		boolean multiSwitchExists = this.multiSwitchPoint(first, second) || this.multiSwitchPoint(second, first);
		if (multiSwitchExists) {
			return false;
		}*/
		if (this.hasSwitchPath(first, second) && this.hasSwitchPath(second, first)) {
			Pair<Edge, Edge> firstSwitch = (Pair<Edge, Edge>) this.shapeSpecificFeautres.get(2);
			Pair<Edge, Edge> secondSwitch = (Pair<Edge, Edge>) this.shapeSpecificFeautres.get(5);
			
			if ((null != this.history.getHostTree().distanceBetweenNodes(firstSwitch.getSecond().getParent(), secondSwitch.getSecond().getParent())) && 
					(0 == this.history.getHostTree().distanceBetweenNodes(firstSwitch.getSecond().getParent(), secondSwitch.getSecond().getParent())) ||
					(null != this.history.getHostTree().distanceBetweenNodes(secondSwitch.getSecond().getParent(), firstSwitch.getSecond().getParent())) && 
					(0 == this.history.getHostTree().distanceBetweenNodes(secondSwitch.getSecond().getParent(), firstSwitch.getSecond().getParent()))) {
				Node collapsePoint1 = new TreeCollapseUtil().alternateNodeInNodePair((Node) this.shapeSpecificFeautres.get(3));
				Node mappedPoint1 = (Node) this.shapeSpecificFeautres.get(4);
				Pair<Edge, Edge> newSwitch1 = new Pair<Edge, Edge>(secondSwitch.getSecond(), secondSwitch.getFirst());
				
				Node collapsePoint2 = new TreeCollapseUtil().alternateNodeInNodePair((Node) this.shapeSpecificFeautres.get(0));
				Node mappedPoint2 = (Node) this.shapeSpecificFeautres.get(1);
				Pair<Edge, Edge> newSwitch2 = new Pair<Edge, Edge>(firstSwitch.getSecond(), firstSwitch.getFirst());
				
				this.shapeSpecificFeautres = new ArrayList<Object>();
				this.shapeSpecificFeautres.add(collapsePoint1);
				this.shapeSpecificFeautres.add(mappedPoint1);
				this.shapeSpecificFeautres.add(newSwitch1);
				this.shapeSpecificFeautres.add(collapsePoint2);
				this.shapeSpecificFeautres.add(mappedPoint2);
				this.shapeSpecificFeautres.add(newSwitch2);				
				return true;
			}			
		}
		return false;
	}
	
	protected boolean hasDoubleSwitchPath(LeafNodePair leafNodePair) {
		this.shapeSpecificFeautres = new ArrayList<Object>();
		return this.hasDoubleSwitchPath(leafNodePair.getFirst(), leafNodePair.getSecond()) || this.hasDoubleSwitchPath(leafNodePair.getSecond(), leafNodePair.getFirst());		
	}
	
	protected boolean hasDoubleSwitchPath(Node startingPoint, Node invalidEndPoint) {
		Associations associations = this.history.getAssociations().getAssociationsForHostNode(startingPoint);
		
		for (Association association : associations) {
			Node node = super.alternateNodeForInternalNodePair(association.getSecondNode());
			
			Node leafChild = node.getLeftChild();
			Node rightChild = node.getRightChild();
			
			// adding the two collapse points
			this.shapeSpecificFeautres.add(leafChild);
			this.shapeSpecificFeautres.add(node);
			// adding the two mapping points
			this.shapeSpecificFeautres.add(node.getParent());
			this.shapeSpecificFeautres.add(node);			
			
			// adding three edges (one here and the other in is valid Child for Double Switch method)
			this.shapeSpecificFeautres.add(startingPoint.getParentEdge());
			
			if (this.isValidChildForDoubleSwitch(leafChild, startingPoint, invalidEndPoint) && this.isValidChildForDoubleSwitch(rightChild, startingPoint, invalidEndPoint)) {
				this.shapeSpecificFeautres.add(association.getSecondNode());
				return true;
			}
			else {
				this.shapeSpecificFeautres = new ArrayList<Object>();
			}	
		}
		return false;
	}
	
	protected boolean isValidChildForDoubleSwitch(Node child, Node startingPoint, Node invalidEndPoint) {
		Associations reverseAssociations = this.history.getAssociations().getAssociationsForParasiteNode(child);
		
		for (Association reverseAssociation : reverseAssociations) {
			if (!(invalidEndPoint.equals(reverseAssociation.getFirstNode()) 
					|| startingPoint.equals(reverseAssociation.getFirstNode()))) {		
				this.shapeSpecificFeautres.add(reverseAssociation.getFirstNode().getParentEdge());
				return true;
			}
		}
		return false;
	}
	
	public List<Object> getShapeSpecificFeatures() {		
		return this.shapeSpecificFeautres;
	}		
}
