package association;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import tree.Node;
import tree.Tree;

/**
 * The CophylogeneticAssociations encapsulate the set of Parasites which have
 * colonized a set of related Hosts. 
 * 
 * Within the calculations considered herein all Parasites must be associated with a Host
 * 
 * @author Ben
 *
 */
public class CophylogeneticAssociations implements Associations  {

	protected Set<Association> associations;
	protected Map<Node, CophylogeneticAssociations> hostNodeAssociations;
	protected Map<Node, CophylogeneticAssociations> parasiteNodeAssociations;
	
	public CophylogeneticAssociations() {
		this.associations = new TreeSet<Association>();
		this.hostNodeAssociations = new HashMap<Node, CophylogeneticAssociations>();
		this.parasiteNodeAssociations = new HashMap<Node, CophylogeneticAssociations>();
	}
	
	public CophylogeneticAssociations(Collection<Association> associations) {
		this();
		for (Association association : associations) {
			this.add(association);
		}
	}

	public CophylogeneticAssociations(Associations associations) {
		this();
		for (Association association : associations) {
			this.add(association);
		}
	}

	/**
	 * Add an association to the associations list
	 * 
	 * @param association the association to add to the list of associations
	 */
	public void add(Association association) {
		if (null != association) {
			this.associations.add(association);	
			{
				Node key = association.getFirstNode();
				if (this.hostNodeAssociations.containsKey(key)) {
					CophylogeneticAssociations associations = this.hostNodeAssociations.get(key);
					associations.addToTrackingSet(association);
					this.hostNodeAssociations.put(key, associations);
				}
				else {
					CophylogeneticAssociations associations = new CophylogeneticAssociations();
					associations.addToTrackingSet(association);	
					this.hostNodeAssociations.put(key, associations);				
				}
			}
			{
				Node key = association.getSecondNode();
				if (this.parasiteNodeAssociations.containsKey(key)) {
					CophylogeneticAssociations associations = this.parasiteNodeAssociations.get(key);
					associations.addToTrackingSet(association);
					this.parasiteNodeAssociations.put(key, associations);
				}
				else {
					CophylogeneticAssociations associations = new CophylogeneticAssociations();
					associations.addToTrackingSet(association);	
					this.parasiteNodeAssociations.put(key, associations);				
				}
			}
		}		
	}

	/**
	 * The association to delete for the associations list
	 * 
	 * @param association the association to delete from the list of associations
	 */
	public void delete(Association association) {
		if (null != association) {
			this.associations.remove(association);	
			{
				Node key = association.getFirstNode();
				if (this.hostNodeAssociations.containsKey(key)) {
					CophylogeneticAssociations associations = this.hostNodeAssociations.get(key);
					associations.removeFromTrackingSet(association);
					this.hostNodeAssociations.put(key, associations);
				}
				else {
					CophylogeneticAssociations associations = new CophylogeneticAssociations();
					associations.removeFromTrackingSet(association);	
					this.hostNodeAssociations.put(key, associations);				
				}
			}	
			{
				Node key = association.getSecondNode();
				if (this.parasiteNodeAssociations.containsKey(key)) {
					CophylogeneticAssociations associations = this.parasiteNodeAssociations.get(key);
					associations.removeFromTrackingSet(association);
					this.parasiteNodeAssociations.put(key, associations);
				}
				else {
					CophylogeneticAssociations associations = new CophylogeneticAssociations();
					associations.removeFromTrackingSet(association);	
					this.parasiteNodeAssociations.put(key, associations);				
				}
			}				
		}
	}

	/**
	 * Creates a copy of the associations list
	 * 
	 * @return a new copy of the associations in memory independent of this associations
	 */
	public Associations createCopy(Tree newHostTree, Tree newParasiteTree) {
		Associations clone = new CophylogeneticAssociations();
		
		for (Association association : this.associations) {
			Node hostNode = newHostTree.getNodeWithPrefix(association.getFirstNode().getNodeName());
			Node parasiteNode = newParasiteTree.getNodeWithPrefix(association.getSecondNode().getNodeName());
			clone.add(new CophylogeneticAssociation(hostNode, parasiteNode));
		}
		
		return clone;
	}

	/**
	 * The number of elements in the associations list
	 * 
	 * @return the number of elements in the associations list
	 */	
	public int size() {
		return this.associations.size();
	}

	/**
	 * Checks if a certain association exists in the list of associations 
	 * 
	 * @param association the association to check if it exists in the association list
	 * @return true if the association does exist otherwise false
	 */
	public boolean contains(Association association) {
		return this.associations.contains(association);
	}	
	
	/**
	 * Generates a list of associations for a specific host node
	 * 
	 * @param hostNode the parasite node in question
	 * @return the associations for a the particular host node
	 */
	public Associations getAssociationsForHostNode(Node hostNode) {
		return null != this.hostNodeAssociations.get(hostNode) ? this.hostNodeAssociations.get(hostNode) : new CophylogeneticAssociations();
	}
	
	/**
	 * Generates a list of associations for a specific parasite node
	 * 
	 * @param parasiteNode the parasite node in question
	 * @return the associations for a the particular parasite node
	 */
	public Associations getAssociationsForParasiteNode(Node parasiteNode) {
		return null != this.parasiteNodeAssociations.get(parasiteNode) ? this.parasiteNodeAssociations.get(parasiteNode) : new CophylogeneticAssociations();
	}
	
	/**
	 * Compares if the Associations are equals to another Object
	 * 
	 * @param compareTo object to compare against for equality
	 * @return true if the associations are equal to the comparison object
	 */	
	@Override
	public boolean equals(Object compareTo) {
		if (null != compareTo && compareTo instanceof CophylogeneticAssociations) {
			CophylogeneticAssociations comparisonAssociations = (CophylogeneticAssociations) compareTo;
			return (compareTreeMap(this.associations, comparisonAssociations.associations));
		}
		return false;
	}

	/**
	 * Helper method for the equality of the Assocaitions class.
	 * Method assumes that the order of the sets is maintained by a tree set
	 * 
	 * @param associations1 first associations set to compare
	 * @param associations2 second associations set to compare
	 * @return
	 */
	private boolean compareTreeMap(Set<Association> associations1,
			Set<Association> associations2) {
		
		Iterator<Association> iterator1 = associations1.iterator();
		Iterator<Association> iterator2 = associations2.iterator();
			
		while (iterator1.hasNext() && iterator2.hasNext()) {
			
			Association assocation1 = iterator1.next();
			Association assocation2 = iterator2.next();
			
			if (!(assocation1.equals(assocation2))) {
				return false;
			}
		}
		
		return (!iterator1.hasNext() && !iterator2.hasNext());
		
	}

	/**
	 * Allows access to an iterator to the associations list
	 * 
	 * @return iterator to the associations list
	 */
	@Override
	public Iterator<Association> iterator() {
		return associations.iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Association association : this) {
			builder.append(association.getFirstNode());
			builder.append(":");
			builder.append(association.getSecondNode());
			builder.append("\n");
		}
		return builder.toString();
	}	
	
	private void addToTrackingSet(Association association) {
		this.associations.add(association);	
	}
	
	private void removeFromTrackingSet(Association association) {
		this.associations.remove(association);	
	}
}
