package association;

import java.util.Iterator;

import tree.Node;
import tree.Tree;

public interface Associations  extends Iterable<Association>{

	/**
	 * Add an association to the associations list
	 * 
	 * @param association the association to add to the list of associations
	 */
	public void add(Association association);
	
	/**
	 * The association to delete for the associations list
	 * 
	 * @param association the association to delete from the list of associations
	 */
	public void delete(Association association); 
	
	/**
	 * Creates a copy of the associations list
	 * 
	 * @param newHostTree the newly cloned host tree
	 * @param newParasiteTree the newly cloned parasite tree
	 * @return a new copy of the associations in memory independent of this associations
	 */
	public Associations createCopy(Tree newHostTree, Tree newParasiteTree);
	
	/**
	 * The number of elements in the associations list
	 * 
	 * @return the number of elements in the associations list
	 */
	public int size();
	
	/**
	 * Checks if a certain association exists in the list of associations 
	 * 
	 * @param association the association to check if it exists in the association list
	 * @return true if the association does exist otherwise false
	 */
	public boolean contains(Association association);
	
	/**
	 * Generates a list of associations for a specific host node
	 * 
	 * @param hostNode the parasite node in question
	 * @return the associations for a the particular host node
	 */
	public Associations getAssociationsForHostNode(Node hostNode);
	
	/**
	 * Generates a list of associations for a specific parasite node
	 * 
	 * @param parasiteNode the parasite node in question
	 * @return the associations for a the particular parasite node
	 */
	public Associations getAssociationsForParasiteNode(Node parasiteNode);
	
	/**
	 * Compares if the Associations are equals to another Object
	 * 
	 * @param compareTo object to compare against for equality
	 * @return true if the associations are equal to the comparison object
	 */
	public boolean equals(Object compareTo);

	/**
	 * Allows access to an iterator to the associations list
	 * 
	 * @return iterator to the associations list
	 */	
	public Iterator<Association> iterator();
	
}
