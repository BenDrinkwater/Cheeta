package tree;

public class InternalTreeEdge extends TreeEdge {

	public InternalTreeEdge(Node first, Node second) {
		this(first, second, 1);
	}
	
	public InternalTreeEdge(Node first, Node second, int distance) {
		super(first, second, distance);
		super.edgeType = EdgeType.InternalEdge;		
	}	
	
	@Override
	public boolean equals(Object object) {
		if (null == object) {
			return false;
		}
		return super.equals(object);
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
