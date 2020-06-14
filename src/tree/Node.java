package tree;

import java.util.List;
import java.util.Set;

public interface Node extends Comparable<Node>{

	/**
	 * Get the name of this node
	 * 
	 * @return name of this node
	 */
	public String getNodeName();
	
	/**
	 * Checks if the node is a leaf node
	 * 
	 * @return true if a leaf node otherwise false
	 */
	public boolean isLeafNode();
	
	/**
	 * Check if the node is a virtual node
	 * 
	 * @return true if a virtual node
	 */
	public boolean isVirtualNode();
	
	/** 
	 * Check if the node is a root node
	 * 
	 * @return true if a root ndoe otherwise false
	 */
	public boolean isRootNode();
	
	/**
	 * Creates the children of this node 
	 * 
	 * @param leftNodeName left child of the node
	 * @param distanceFromLeftNode distance from this node to the left node
	 * @param rightNodeName right child of the node
	 * @param distanceFromRightNode distance from this node to the right node
	 */
	public boolean setChildren(String leftNodeName, int distanceFromLeftNode, String rightNodeName, int distanceFromRightNode);

	/**
	 * Adds two existing children as the left and right child of this node
	 * 
	 * @param leftChild node to add as left child of this node
	 * @param rightChild node to add as right child of this node
	 */
	public boolean addExistingNodesAsChildren(Node leftChild, Node rightChild);	
	
	/**
	 * Get parent node of current node
	 * 
	 * @return parent of current node
	 */
	public Node getParent();
	
	/**
	 * Get the parent edge for the current node
	 * 
	 * @return the parent edge for the current node
	 */
	public Edge getParentEdge();
	
	/**
	 * Get left node of current node
	 * 
	 * @return left node of current node
	 */
	public Node getLeftChild();
	
	/**
	 * Get right node of current node
	 * 
	 * @return right node of current node
	 */
	public Node getRightChild();	
	
	/**
	 * Get distance to root node
	 * 
	 * @return distance to root node
	 */
	public int distanceToRoot();
	
	public void calibrateDistanceToRoot();
	
	/**
	 * Set height in the tree
	 * 
	 * @param height new height of the node in the tree
	 */
	public void setHeight(int height);
	
	/**
	 * Retrieve the height of a node in a tree
	 * 
	 * @return the height of a node
	 */
	public int getHeight();
	
	/**
	 * Get the number of children that are internal nodes
	 * 
	 * @return the number of children that are internal nodes
	 */
	public int numberOfChildrenThatAreInternalNodes();
	
	/**
	 * Set the number of children that are internal nodes
	 */
	public void setTheNumberOfChildrenThatAreInternalNodes();
	
	/**
	 * Returns a set of edges connected to the node
	 * 
	 * @return a set of edges connected to the node
	 */
	public Set<Edge> getEdgeSet();
	
	/**
	 * Returns a list of edges connected to the node
	 * 
	 * @return a list of edges connected to the node
	 */
	public List<Edge> getEdgeList();
	
	/**
	 * Add new edge to the node. Used for rewiring when deleting a node. 
	 * 
	 * @param edge new edge to add to the node
	 * @return true if the edge was added false if it was not
	 */
	public boolean addEdge(Edge edge);	
	
	/**
	 * Remove edge connecting to node
	 * 
	 * @param node potential connected node from which to remove from node
	 * @return true if edge is removed else false;
	 */
	public boolean removeEdge(Node node);
	
	/**
	 * Equality method for the node class. Compares the name, parents name,
	 * left and right child and the distance from parent. 
	 * 
	 * @param compareTo object to compare node against
	 * @return true if they are equal otherwise false
	 */
	@Override
	public boolean equals(Object compareTo);

	/**
	 * CompareTo method for the Node class to maintain order of a list of Nodes
	 * 
	 * @param compareTo node to compare to
	 * @return negative indicates less than, 0 means equals and grater than 1 means greater than
	 */
	public int compareTo(Node compareTo);		

	/**
	 * Nodes require a hash code to allow use in hash sets and maps
	 * 
	 * @return hash code for the node
	 */
	@Override
	public int hashCode();		
	
	/**
	 * To string method for the Node Class used for printing to Nexus Files
	 * 
	 * @return a string representation of the node
	 */
	@Override
	public String toString();
	
	//TODO implement with more clear details after testing
	
	public int getLeafLabel();
	
}
