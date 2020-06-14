package tree;

public class RootedBifurcatingNode extends TreeNode {

	public RootedBifurcatingNode(String name) {
		super(name);
		super.connectingEdges.add(new FloatingEdge(this));		
	}

	@Override
	public boolean setChildren(String leftNodeName, int distanceFromLeftNode,
			String rightNodeName, int distanceFromRightNode) {
		Node leftNode = new RootedBifurcatingNode(leftNodeName);
		Node rightNode = new RootedBifurcatingNode(rightNodeName);
		boolean newNodesAddedSuccessfully = super.addEdge(new InternalTreeEdge(this, leftNode, distanceFromLeftNode)) && super.addEdge(new InternalTreeEdge(this, rightNode, distanceFromRightNode));
		if (newNodesAddedSuccessfully) {
			leftNode.getEdgeList().set(0, super.connectingEdges.get(1));
			rightNode.getEdgeList().set(0, super.connectingEdges.get(2));
		}
		return newNodesAddedSuccessfully;
	}
	
	@Override
	public boolean addExistingNodesAsChildren(Node leftChild, Node rightChild) {
		boolean newNodesAddedSuccessfully = super.addEdge(new InternalTreeEdge(this, leftChild)) && super.addEdge(new InternalTreeEdge(this, rightChild));
		if (newNodesAddedSuccessfully) {
			leftChild.getEdgeList().set(0, super.connectingEdges.get(1));
			rightChild.getEdgeList().set(0, super.connectingEdges.get(2));		
		}
		return newNodesAddedSuccessfully;
	}
}
