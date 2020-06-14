package tree;

public class FloatingEdge extends TreeEdge {

	protected FloatingEdge(Node rootNode) {
		this(new VirtualNode("VirtualNode"), rootNode, -1);
	}
	
	private FloatingEdge(Node first, Node second, int distance) {
		super(first, second, distance);
		super.edgeType = EdgeType.FloatingEdge;
	}
	
	@Override
	public boolean equals(Object object) {
		if (null == object) {
			return false;
		}
		return this.toString().equals(object.toString());
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}	
}
