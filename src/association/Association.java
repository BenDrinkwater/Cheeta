package association;

import tree.Node;

public interface Association extends Comparable<Association> {
	
	/**
	 * Gets the first (host tree) node for the association
	 * 
	 * @return first node of the association
	 */
	public Node getFirstNode();
	
	/**
	 * Gets the second (parasite tree) node for the association
	 * 
	 * @return second node of the association
	 */	
	public Node getSecondNode();
	
	public void replaceSecondNode(Node node);	
	
	/**
	 * Equality method for the Association class. Compares each node. 
	 * 
	 * @param compareTo object to compare association against
	 * @return true if they are equal otherwise false
	 */
	@Override
	public boolean equals(Object compareTo);

	/**
	 * CompareTo method for the Association class to maintain order of a list of Association
	 * 
	 * @param compareTo association to compare to
	 * @return negative indicates less than, 0 means equals and grater than 1 means greater than
	 */
	public int compareTo(Association compareTo);	
	
	/**
	 * Override of the hash code to allow for even more elements to be included in a hash map or set
	 * 
	 * @return a hash function for the node
	 */
	@Override
	public int hashCode();
	
	/**
	 * To string method for the Association Class used for printing to Nexus Files
	 * 
	 * @return a string representation of the association between the first and second node
	 */
	public String toString();
	
}
