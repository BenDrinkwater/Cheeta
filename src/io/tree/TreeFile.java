package io.tree;

import io.AssociationBuilder;
import io.CophylogenyFileParsingException;

import java.io.File;

import association.Associations;

import tanglegram.CoevolutionaryHistory;
import tree.Tree;

public class TreeFile {

	protected File file;
	
	public TreeFile(String fileName) {
		this.file = new File(fileName);
	}

	public CoevolutionaryHistory read() throws TreeFileParsingException {
		TreeFileReader fileReader = new TreeFileReader(this.file);
		fileReader.parse();
		
		Tree hostTree = new TreeBuilder(fileReader.getHostStringWithNames()).createTree("host");
		Tree parasiteTree = new TreeBuilder(fileReader.getParasiteStringWithNames()).createTree("parasite");
		Associations associations = null;
		try {
			associations = new AssociationBuilder(fileReader.getAssociationStrings()).populateAssociations(hostTree, parasiteTree);
		}
		catch(CophylogenyFileParsingException e) {
			throw new TreeFileParsingException(e.getMessage());
		}
		
		return new CoevolutionaryHistory(hostTree, parasiteTree, associations);
	}

}
