package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class TreeNode implements Node {
	
	protected List<Edge> connectingEdges;
	protected String name;
	private int numberOfInternalNodesThatAreChildren;
	private int distanceToRoot;
	protected int height;
	protected int leafLabel;
	
	private TreeNode() {
		this.connectingEdges = new ArrayList<Edge>();
		this.height = 0;
		this.leafLabel = -1;
	}
	
	public TreeNode(String name) {
		this();
		this.name = name;
	}

	@Override
	public String getNodeName() {
		return this.name;
	}

	@Override
	public boolean isLeafNode() {
		return (this.connectingEdges.size() != 3);
	}

	@Override
	public boolean isRootNode() {
		return (this.connectingEdges.get(0).isFloatingEdge());
	}

	@Override
	public boolean isVirtualNode() {
		return false;
	}
	
	@Override
	public abstract boolean setChildren(String leftNodeName, int distanceFromLeftNode,
			String rightNodeName, int distanceFromRightNode);

	@Override
	public abstract boolean addExistingNodesAsChildren(Node leftChild, Node rightChild);
	
	@Override
	public Node getParent() {
		return this.connectingEdges.size() > 0 ? this.connectingEdges.get(0).getParent() : null;
	}
	
	@Override
	public Edge getParentEdge() {
		if (null == this.connectingEdges || this.connectingEdges.size() == 0) {
			return null;
		}
		return this.connectingEdges.get(0);
	}
	
	@Override
	public Node getLeftChild() {
		if (!(this.isLeafNode())) {
			return this.connectingEdges.get(1).getChild();
		}
		return null;
	}

	@Override
	public Node getRightChild() {
		if (!(this.isLeafNode())) {
			return this.connectingEdges.get(2).getChild();
		}
		return null;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getLeafLabel() {
		return this.leafLabel;
	}
	
	public void setLeafLabel(int leafLabel) {
		this.leafLabel = this.height + leafLabel;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public int distanceToRoot() {
		return this.distanceToRoot;
	}
	
	@Override
	public void calibrateDistanceToRoot() {
		Node node = this;
		int distance = 0;
		while(!node.isRootNode()) {
			distance += node.getParentEdge().getEdgeDistance();
			node = node.getParent();
		}
		this.distanceToRoot = distance;
	}	

	@Override
	public int numberOfChildrenThatAreInternalNodes() {
		return this.numberOfInternalNodesThatAreChildren;
	}
	
	@Override
	public void setTheNumberOfChildrenThatAreInternalNodes() {
		if (this.isLeafNode()) {
			this.numberOfInternalNodesThatAreChildren = 0;
			return;
		}
		else {
			this.getLeftChild().setTheNumberOfChildrenThatAreInternalNodes();
			this.getRightChild().setTheNumberOfChildrenThatAreInternalNodes();
			
			if (this.getLeftChild().isLeafNode() && this.getRightChild().isLeafNode()) {
				this.numberOfInternalNodesThatAreChildren = 0;
			}
			else if (this.getLeftChild().isLeafNode()) {
				this.numberOfInternalNodesThatAreChildren = this.getRightChild().numberOfChildrenThatAreInternalNodes() + 1;
			}
			else if (this.getRightChild().isLeafNode()) {
				this.numberOfInternalNodesThatAreChildren = this.getLeftChild().numberOfChildrenThatAreInternalNodes() + 1;
			}
			else {
				this.numberOfInternalNodesThatAreChildren = this.getLeftChild().numberOfChildrenThatAreInternalNodes() + this.getRightChild().numberOfChildrenThatAreInternalNodes() + 2;
			}
		}
	}

	@Override
	public Set<Edge> getEdgeSet() {
		Set<Edge> edges = new TreeSet<Edge>();
		edges.addAll(this.connectingEdges);
		return edges;
	}

	@Override
	public List<Edge> getEdgeList() {
		return this.connectingEdges;
	}

	@Override
	public boolean addEdge(Edge edge) {
		return this.connectingEdges.add(edge);
	}
	
	@Override	
	public boolean removeEdge(Node node) {
		Edge toRemove = null;
		for (Edge edge : this.getEdgeSet()) {
			if (edge.getAlternateNode(this).equals(node)) {
				toRemove = edge;
			}
		}
		return this.connectingEdges.remove(toRemove);
	}

	@Override
	public boolean equals(Object compareTo) {
		if (null != compareTo) {
			return this.toString().equals(compareTo.toString());
		}
		return false;
	}
	
	@Override
	public int compareTo(Node compareTo) {
		return this.name.compareTo(compareTo.getNodeName());
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
