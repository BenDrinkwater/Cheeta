package tree;

import util.Pair;

public abstract class TreeEdge implements Edge {

	protected enum EdgeType {
		InternalEdge,
		FloatingEdge,
		UnknownEdgeType
	}	
	
	protected Pair<Node, Node> nodes;
	protected int distance;
	protected EdgeType edgeType;
	protected String edgeString;
	
	public TreeEdge(Node first, Node second) {
		this.nodes = new Pair<Node, Node>(first, second);
		this.distance = 0;
		this.edgeType = EdgeType.UnknownEdgeType;
		this.edgeString = "(" + this.getParent().toString() 
		+ "," + this.getChild().toString() + ")";
	}
	
	public TreeEdge(Node first, Node second, int distance) {
		this(first, second);
		this.distance = distance;
	}
	
	@Override
	public Node getParent() {
		return this.nodes.getFirst();
	}

	@Override
	public Node getChild() {
		return this.nodes.getSecond();
	}

	@Override
	public Node getAlternateNode(Node node) {
		if (this.nodes.getFirst().equals(node)) {
			return this.nodes.getSecond();
		}
		else if (this.nodes.getSecond().equals(node)) {
			return this.nodes.getFirst();
		}
		else {
			return null;
		}
	}

	@Override
	public int getEdgeDistance() {
		return this.distance;
	}

	@Override
	public void setEdgeDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public Pair<Node, Node> getNodePair() {
		return this.nodes;
	}
	
	@Override
	public boolean isInternalEdge() {
		return EdgeType.InternalEdge == this.edgeType;
	}
	
	@Override
	public boolean isFloatingEdge() {
		return EdgeType.FloatingEdge == this.edgeType;
	}

	@Override
	public boolean equals(Object object) {
		return this.toString().equals(object.toString());
	}
	
	@Override
	public int compareTo(Edge compareTo) {
		if (null != compareTo) {
			return this.toString().compareTo(compareTo.toString());
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return this.edgeString;
	}
}
