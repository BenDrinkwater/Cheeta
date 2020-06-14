package reconstruction.solver.tc;

import reconstruction.HostMappingList;
import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.Tree;

public class LossCounter {

	protected CoevolutionaryHistory history;
	protected Tree hostTree;
	protected Tree parasiteTree;
	protected HostMappingList hostMapping;
	protected ParasiteMappingList parasiteMapping;

	private LossCounter () {
		this.hostMapping = new HostMappingList();
	}
	
	public LossCounter(CoevolutionaryHistory history, 
			ParasiteMappingList parasiteMapping) {
		this();
		this.history = history;
		this.hostTree = this.history.getHostTree();
		this.parasiteTree = this.history.getParasiteTree();
		this.parasiteMapping = parasiteMapping;
	}
	
	public int countNumberOfLossEvents() {
		this.numberOfHopsToNextNodes(this.history.getParasiteTree().getRootNode());
		return this.hostMapping.numberOfLossEvents();
	}
	
	protected void numberOfHopsToNextNodes (Node node) {
		Node leftChild = node.getLeftChild();
		Node rightChild = node.getRightChild();
		
		if (null == leftChild || null == rightChild) {
			return;
		}
		
		if (this.parasiteMapping.isParasiteMappedToHostNode(node)) {
			this.computeLossForParentNodeMappedToHostNode(node, leftChild, rightChild);
		}
		else if (this.parasiteMapping.isParasiteMappedToHostEdge(node)) {
			this.computeLossForParentNodeMappedToHostEdge(node, leftChild, rightChild);
		}
		else if (this.parasiteMapping.isParasiteMappedToHostEdgeAndHostSwitchEvent(node)) {
			this.computeLossForParentNodeMappedAsHostSwitch(node, leftChild, rightChild);
		}
		
		this.numberOfHopsToNextNodes(leftChild);
		this.numberOfHopsToNextNodes(rightChild);
	}
	
	protected void computeLossForParentNodeMappedToHostNode(Node parent, Node leftChild, Node rightChild) {
		Node hostNodeMappedToNode = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(parent).toString());
		
		Node hostNodeMappedToLeftChild = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(leftChild).toString());
		Node hostNodeMappedToRightChild = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(rightChild).toString());
		
		Integer numberOfNodesBetweenParentAndLeftChild = this.hostTree.distanceBetweenNodes(hostNodeMappedToNode, hostNodeMappedToLeftChild);
		Integer numberOfNodesBetweenParentAndRightChild = this.hostTree.distanceBetweenNodes(hostNodeMappedToNode, hostNodeMappedToRightChild);
			
		if (null != numberOfNodesBetweenParentAndLeftChild && numberOfNodesBetweenParentAndLeftChild > 0) {
			this.hostMapping.add(this.hostTree.nodesBetweenNodes(hostNodeMappedToNode, hostNodeMappedToLeftChild));
		}
		
		if (null != numberOfNodesBetweenParentAndRightChild && numberOfNodesBetweenParentAndRightChild > 0) {
			this.hostMapping.add(this.hostTree.nodesBetweenNodes(hostNodeMappedToNode, hostNodeMappedToRightChild));
		}		
	}

	protected void computeLossForParentNodeMappedToHostEdge(Node parent, Node leftChild, Node rightChild) {
		Node hostNodeMappedToNode = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(parent).toString());
		
		Node hostNodeMappedToLeftChild = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(leftChild).toString());
		Node hostNodeMappedToRightChild = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(rightChild).toString());
		
		Integer numberOfNodesBetweenParentAndLeftChild = this.hostTree.distanceBetweenNodes(hostNodeMappedToNode, hostNodeMappedToLeftChild);
		Integer numberOfNodesBetweenParentAndRightChild = this.hostTree.distanceBetweenNodes(hostNodeMappedToNode, hostNodeMappedToRightChild);
			
		if (null != numberOfNodesBetweenParentAndLeftChild && numberOfNodesBetweenParentAndLeftChild > 0) {
			this.hostMapping.add(this.hostTree.nodesBetweenNodes(hostNodeMappedToNode, hostNodeMappedToLeftChild));
		}
		
		if (null != numberOfNodesBetweenParentAndRightChild && numberOfNodesBetweenParentAndRightChild > 0) {
			this.hostMapping.add(this.hostTree.nodesBetweenNodes(hostNodeMappedToNode, hostNodeMappedToRightChild));
		}
		
		if (!hostNodeMappedToNode.equals(hostNodeMappedToLeftChild)) {
			this.hostMapping.add(hostNodeMappedToNode);
		}
		if (!hostNodeMappedToNode.equals(hostNodeMappedToRightChild)) {
			this.hostMapping.add(hostNodeMappedToNode);
		}	
	}
	
	protected void computeLossForParentNodeMappedAsHostSwitch(Node parent, Node leftChild, Node rightChild) {
		Node firstEdgeMappedHostNode = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(parent).toString());
		Node secondEdgeMappedHostNode = this.hostTree.getNodeWithPrefix(this.parasiteMapping.getMappedPoint(parent).getSecondHostNode().toString());
		
		Node hostNodeMappedToLeftChild = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(leftChild).toString());
		Node hostNodeMappedToRightChild = this.hostTree.getNodeWithPrefix(this.parasiteMapping.hostNodeMappedTo(rightChild).toString());
		
		this.processFirstHostSwitchPair(firstEdgeMappedHostNode, hostNodeMappedToLeftChild, hostNodeMappedToRightChild);	
		this.processFirstHostSwitchPair(secondEdgeMappedHostNode, hostNodeMappedToLeftChild, hostNodeMappedToRightChild);
	}
	
	protected void processFirstHostSwitchPair(Node parent, Node leftChild, Node rightChild) {
		
		// Fixes bug introduced by multi-associations. 
		// TODO investigate how to mitigate this without the if statement
		if (parent.isLeafNode()) {
			// check for cases and if not valid then throw exception
			return;
		}
		
		Integer numberOfNodesBetweenParentAndLeftChild = this.hostTree.distanceBetweenNodes(parent, leftChild);
		Integer numberOfNodesBetweenParentAndRightChild = this.hostTree.distanceBetweenNodes(parent, rightChild);
			
		if (null != numberOfNodesBetweenParentAndLeftChild && numberOfNodesBetweenParentAndLeftChild > 0) {
			this.hostMapping.add(this.hostTree.nodesBetweenNodes(parent, leftChild));
		}
		
		if (null != numberOfNodesBetweenParentAndRightChild && numberOfNodesBetweenParentAndRightChild > 0) {
			this.hostMapping.add(this.hostTree.nodesBetweenNodes(parent, rightChild));
		}
		
		if (null == numberOfNodesBetweenParentAndLeftChild && !parent.equals(rightChild)) {
			this.hostMapping.add(parent);
		}
		
		if (null == numberOfNodesBetweenParentAndRightChild && !parent.equals(leftChild)) {
			this.hostMapping.add(parent);
		}
	}
	
	public HostMappingList getHostMapping() {
		return this.hostMapping;
	}
}
