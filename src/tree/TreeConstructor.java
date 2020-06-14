package tree;

public class TreeConstructor {

	/**
	 * Factory method for a Tree object
	 * 
	 * @param rootNode the name of the root node for the tree
	 * @param prefix the prefix of the tree (host, parasite, etc)
	 * @return a new Tree object
	 */
	public static Tree createNewTree(String rootNode, String prefix) {
		return new RootedBifurcatingTree(rootNode, prefix);	
	}
	
}
