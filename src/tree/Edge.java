package tree;

import util.Pair;
import tree.Node;

public interface Edge extends Comparable<Edge> {
	
	/**
	 * Return the parent (leading) node of the edge
	 * 
	 * @return first node added to the edge
	 */
	public Node getParent();
	
	/**
	 * Return the child (leading) node of the edge
	 * 
	 * @return second node added to the edge
	 */
	public Node getChild();
	
	/**
	 * Return the alternate of the edge
	 * 
	 * @param node one of the nodes from the edge 
	 * @return alternate node of th edge
	 */
	public Node getAlternateNode(Node node);

	/**
	 * Gets the distance of the edge	
	 * @return gets the distance of an edge
	 */
	public int getEdgeDistance();
	
	
	/**
	 * Sets the distance of the edge
	 * @param distance new distance of the edge
	 */
	public void setEdgeDistance(int distance);
	
	/**
	 * Generates the pair of connected nodes for an edge
	 * 
	 * @return a pair class instance containing the two nodes sharing this edge
	 */
	public Pair<Node, Node> getNodePair();	

	/**
	 * Is edge an internal edge
	 * 
	 * @return true if an internal edge false otherwise
	 */
	public boolean isInternalEdge();
	
	/**
	 * Is edge an virtual edge
	 * 
	 * @return true if a floating edge false otherwise
	 */
	public boolean isFloatingEdge();
	
	/**
	 * Equality method for the node class. Compares the name, parents name,
	 * left and right child and the distance from parent. 
	 * 
	 * @param compareTo object to compare node against
	 * @return true if they are equal otherwise false
	 */
	public boolean equals(Object compareTo);

	/**
	 * CompareTo method for the Edge class to maintain order of a list of Nodes
	 * 
	 * @param compareTo node to compare to
	 * @return negative indicates less than, 0 means equals and grater than 1 means greater than
	 */
	public int compareTo(Edge compareTo);	
	
	/**
	 * Generate a hash code of the edge
	 * 
	 * @return hash code for the edge
	 */
	public int hashCode();
	
	/**
	 * Generates a string representation for the edge connecting two nodes in graph
	 * 
	 * @return a string representation for the edge connecting two nodes in graph
	 */
	public String toString();	
	
}
