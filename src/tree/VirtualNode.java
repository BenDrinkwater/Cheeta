package tree;

public class VirtualNode extends TreeNode {

	public VirtualNode(String name) {
		super(name);
		super.height = 0;
	}

	@Override
	public boolean isVirtualNode() {
		return true;
	}
	
	@Override
	public boolean setChildren(String leftNodeName, int distanceFromLeftNode,
			String rightNodeName, int distanceFromRightNode) {
		return false;
	}
	
	@Override
	public boolean addExistingNodesAsChildren(Node leftChild, Node rightChild) {
		return false;
	}

}
