package tree;

import io.nexus.NexusStringFormat;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Tree {

	/**
	 * Get root node
	 * 
	 * @return trees root node
	 */
	public Node getRootNode();
	
	/**
	 * Gets a set of leaf nodes for the tree
	 * 
	 * @return a set of leaf nodes for the tree
	 */
	public Set<Node> getLeafNodes();
	
	/**
	 * Gets a list of leaf nodes for the tree
	 * 
	 * @return a list of leaf nodes for the tree
	 */	
	public List<Node> getLeafNodesList();

	/**
	 * Gets a set of leaf nodes for the subtree rooted at node
	 * 
	 * @param node root of the subtree
	 * @return a set of leaf nodes for the tree
	 */
	public Set<Node> getLeafNodes(Node node);
	
	/**
	 * Gets a list of leaf nodes for the subtree rooted at node
	 * 
	 * @param node root of the subtree
	 * @return a list of leaf nodes for the tree
	 */
	public List<Node> getLeafNodesList(Node node);
	
	/**
	 * Get a set of all the nodes in the tree
	 * 
	 * @return a set of all the nodes in the tree
	 */
	public Set<Node> getAllNodes();
	
	/**
	 * Get a list of all the nodes in the tree
	 * 
	 * @return a list of all the nodes in the tree
	 */
	public List<Node> getAllNodesAsList();
	
	/**
	 * Get a set of all the nodes for the subtree rooted at node
	 * 
	 * @param node root of the subtree
	 * @return a set of all the nodes in the tree
	 */
	public Set<Node> getAllNodes(Node node);
	
	/**
	 * Get a list of all the nodes for the subtree rooted at node
	 * 
	 * @param node root of the subtree
	 * @return a list of all the nodes in the tree
	 */
	public List<Node> getAllNodesAsList(Node node);
	
	/**
	 * Get a list of all the nodes as a post order traversal 
	 * 
	 * @return list of all the nodes as a post order traversal 
	 */
	public List<Node> getNodesAsPostOrder();
	
	/**
	 * Get node from node names reference
	 * 
	 * @param name of the node to return
	 * @return node of name to return
	 */
	public Node getNode(final String name);
	
	/**
	 * Get node from node names including prefix
	 * 
	 * @param name of the node to return
	 * @return node of name to return
	 */	
	public Node getNodeWithPrefix(final String nameWithPrefix);
	
	/**
	 * Get node name from node
	 * 
	 * @param node to find name of
	 * @return name
	 */
	public String getNodeName(Node node);
	
	/**
	 * Get node timings for tree
	 * 
	 * @return node timings for tree as map
	 */
	public Map<Node, Integer> getNodeTimings();
	
	/**
	 * Recover the height of the node based on the fixed node timing
	 * 
	 * @param node the query node
	 * @return the height of the query node
	 */
	public Integer getNodeHeight(Node node);	
	
	/**
	 * Get number of nodes in the tree
	 * 
	 * @return the number number of nodes in the tree
	 */
	public int size();
	
	/**
	 * Get number of leaf nodes in the tree
	 * 
	 * @return the number of leaf nodes in the tree
	 */
	public int numberOfLeafNodes();
	
	/**
	 * Get number of internal nodes in the tree
	 * 
	 * @return the number of leaf nodes in the tree
	 */
	public int numberOfInteranlNodes();
	
	/**
	 * Find the distance between two nodes
	 * 
	 * @param ancestorNode the node which is a ancestor of the second node 
	 * @param descendantNode the descendant node when the ancestor node is somewhere along the path towards the root
	 * @return the number of nodes between the two nodes or null if they are not on the same path
	 */
	public Integer distanceBetweenNodes(Node ancestorNode, Node descendantNode);
	
	/**
	 * Find the distance between two nodes
	 * 
	 * @param ancestorNode the node which is a ancestor of the second node 
	 * @param descendantNode the descendant node when the ancestor node is somewhere along the path towards the root
	 * @return the nodes between the two nodes or null if they are not on the same path
	 */	
	public List<Node> nodesBetweenNodes(Node ancestorNode, Node descendantNode);
	
	/**
	 * Adds children to node parent
	 * 
	 * @param parent the node to add children to
	 * @param leftChild node name distance
	 * @param rightChild node name distance
	 * 
	 * @return true if added successfully false otherwise
	 */
	public boolean addChildren(final String parent, final String leftChild, final String rightChild);
	
	/**
	 * Adds children to node parent
	 * 
	 * @param parent the node to add children to
	 * @param leftChild node name distance
	 * @param leftDistance distance to left node
	 * @param rightChild node name distance
	 * @param rightDistance distance to right node
	 * 
	 * @return true if added successfully false otherwise
	 */	
	public boolean addChildren(final String parent, final String leftChild, int leftDistance, final String rightChild, int rightDistance);
	
	/**
	 * Adds children to the node parent
	 * 
	 * @param parent the node to add children to
	 * @param leftSubTreeRootNode sub tree for the left branch
	 * @param rightSubTreeRootNode the name of the new right child
	 */
	public boolean addChildren(final String parent, Tree leftSubTreeRootNode, Tree rightSubTreeRootNode);	

	/**
	 * Delete leaf node pair from tree
	 * 
	 * @param pair of leaf nodes to remove from the tree
	 * @return true if nodes are removed else false
	 */
	public boolean deleteLeafNodePair(LeafNodePair pair);
	
	/**
	 * Delete leaf node pair from tree
	 * 
	 * @param leftNode left node from a leaf node pair
	 * @param rightNode right node from a leaf node pair
	 * @return true if the nodes are removed else false
	 */
	public boolean deleteLeafNodePair(Node leftNode, Node rightNode);
	
	/**
	 * Returns the list of ancestors for a node in the tree
	 * 
	 * @param node the query node
	 * @return a list of nodes containing the list of ancestors for a node in the tree
	 */
	public List<Node> listOfAncestors(Node node);
	
	/**
	 * Returns the complement of a list of ancestors for a node in the tree
	 * 
	 * @param node the query node
	 * @return a list of nodes containing the complement of a list of ancestors for a node in the tree
	 */
	public List<Node> listMinusAncestors(Node node);
	
	/**
	 * Uncovers the lowest common ancestor for a pair of nodes in the tree
	 * 
	 * @param first the first query node
	 * @param second the second query node
	 * @return the lowest common ancestor for a pair of nodes
	 */
	public Node getLowestCommonAncestor(Node first, Node second);
	
	/**
	 * Clone current tree
	 * 
	 * @return instance of the tree to clone 
	 */
	public Tree cloneTree();
	
	/**
	 * Clone current tree
	 * 
	 * @param newPrefix new prefix for the copied tree
	 * @return instance of the tree to clone 
	 */	
	public Tree cloneTree(final String newPrefix);
	
	/**
	 * Sets a unique node timing for the tree
	 * 
	 * @param timings unique node timings to set for current tree
	 */
	public void setUniqueNodeTimings(final Map<Node, Integer> timings);
	
	/**
	 * Create unique random timings
	 */
	public void setRandomUniqueNodeTimings();
	
	public void cacheValues();
	
	/**
	 * Equality method for the tree object
	 * 
	 * @param compareTo tree to compare this tree against
	 * @return true if equal otherwise false
	 */
	public boolean equals(Object compareTo);
	
	public String getNexusString(NexusStringFormat format);
}
