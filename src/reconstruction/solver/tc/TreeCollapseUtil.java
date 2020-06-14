package reconstruction.solver.tc;

import tree.Node;

public class TreeCollapseUtil {

	protected TreeCollapseUtil() {
		
	}
	
	protected Node alternateNodeInNodePair(Node node) {
		if (null  != node && node.isLeafNode()) {
			Node parentOfNode = node.getParent();
			
			if (null != parentOfNode) {
				Node alteranteNode = null;
				
				if (node.equals(parentOfNode.getLeftChild())) {
					alteranteNode = parentOfNode.getRightChild();
				}
				else if (node.equals(parentOfNode.getRightChild())) {
					alteranteNode = parentOfNode.getLeftChild();
				}
				if (null != alteranteNode && alteranteNode.isLeafNode()) {
					return alteranteNode;
				}
			}
		}
		return null;
	}	
	
	protected Node alternateNodeForInternalNodePair(Node node) {
		if (null  != node) {
			Node parentOfNode = node.getParent();
			
			if (null != parentOfNode) {
				Node alteranteNode = null;
				
				if (node.equals(parentOfNode.getLeftChild())) {
					alteranteNode = parentOfNode.getRightChild();
				}
				else if (node.equals(parentOfNode.getRightChild())) {
					alteranteNode = parentOfNode.getLeftChild();
				}
				if (null != alteranteNode) {
					return alteranteNode;
				}
			}
		}
		return null;
	}		
	
}
