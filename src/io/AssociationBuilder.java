package io;

import java.util.List;

import tree.Node;
import tree.Tree;
import util.ComparablePair;

import association.Associations;
import association.CophylogeneticAssociation;
import association.CophylogeneticAssociations;

public class AssociationBuilder {

	protected List<ComparablePair<String, String>> associationStrings;
	private CophylogeneticAssociations associations;
	
	protected AssociationBuilder() {
		this.associations = new CophylogeneticAssociations();		
	}
	
	public AssociationBuilder(List<ComparablePair<String, String>> list) {
		this();
		this.associationStrings = list;
	}
	
	public Associations populateAssociations(Tree hostTree, Tree parasiteTree) throws CophylogenyFileParsingException {
		if (null == this.associationStrings) {
			throw new CophylogenyFileParsingException("Unable to read associations from Nexus File");
		}
		for (ComparablePair<String, String> pair : this.associationStrings) {
			
			if (null == hostTree) {
				throw new CophylogenyFileParsingException("Missing host Tree");
			}
			
			if (null == pair.getFirst() || null == pair.getSecond()) {
				throw new CophylogenyFileParsingException("Malformed Set Of Assoications");
			}
			
			Node hostNode = hostTree.getNode(pair.getSecond());
			
			if (null == hostNode) {
				createMultiHostAssociations(hostTree, parasiteTree, pair);
				continue;
			}
			
			if (null == parasiteTree) {
				throw new CophylogenyFileParsingException("Missing parasite Tree");
			}
			
			Node parasiteNode = parasiteTree.getNode(pair.getFirst());
			
			if (null == parasiteNode) {
				throw new CophylogenyFileParsingException("Cannot locate leaf node " + pair.getFirst() + " in parasite tree");
			}	
			
			this.associations.add(new CophylogeneticAssociation(hostNode, parasiteNode));
		}
		return associations;		
	}
	
	protected void createMultiHostAssociations(Tree hostTree, Tree parasiteTree, ComparablePair<String, String> pair) throws CophylogenyFileParsingException {
		String[] tuple = pair.getSecond().split(" ");
		for (String host : tuple) {
			Node hostNode = hostTree.getNode(host);
			
			if (null == parasiteTree) {
				throw new CophylogenyFileParsingException("Missing parasite Tree");
			}
			
			Node parasiteNode = parasiteTree.getNode(pair.getFirst());
			
			if (null == parasiteNode) {
				throw new CophylogenyFileParsingException("Cannot locate leaf node " + pair.getFirst() + " in parasite tree");
			}	
			
			this.associations.add(new CophylogeneticAssociation(hostNode, parasiteNode));			
		}
	}
}
