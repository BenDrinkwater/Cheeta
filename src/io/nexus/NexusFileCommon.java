package io.nexus;

import io.AssociationBuilder;
import io.CophylogenyFileParsingException;
import io.configuration.Configuration;
import io.configuration.DefaultConfiguration;

import java.io.File;

import association.Associations;

import tanglegram.CoevolutionaryHistory;
import tree.Tree;

public abstract class NexusFileCommon implements NexusFile {

	protected File file;
	
	public NexusFileCommon(String fileName) throws NexusFileHandlingException {
		if (null != fileName) {
			this.file = new File(fileName);
		}
		else {
			throw new NexusFileHandlingException("File Name is null");
		}
	}
	
	public NexusFileCommon(File file) throws NexusFileHandlingException {
		if (null != file) {
			this.file = file;
		}
		else {
			throw new NexusFileHandlingException("File is null");	
		}
	}	
	
	@Override
	public CoevolutionaryHistory read() throws NexusFormatException, NexusFileHandlingException {
		NexusFileReader fileReader = this.getNexusFileReader();
		fileReader.parse();
		
		Tree hostTree = new NexusTreeBuilder(new NexusString(fileReader.getHostString())).createTree("host");
		Tree parasiteTree = new NexusTreeBuilder(new NexusString(fileReader.getParasiteString())).createTree("parasite");
		Associations associations = null;
		try {
			associations = new AssociationBuilder(fileReader.getAssociationStrings()).populateAssociations(hostTree, parasiteTree);
		}
		catch(CophylogenyFileParsingException e) {
			throw new NexusFormatException(e.getMessage());
		}
		
		return new CoevolutionaryHistory(hostTree, parasiteTree, associations);
	}
	
	public boolean write(CoevolutionaryHistory history) {
		NexusFileWriter writer = this.getNexusFileWriter(history, new DefaultConfiguration());
		return writer.write();
	}
	
	public boolean write(CoevolutionaryHistory history, Configuration configuration) {
		NexusFileWriter writer = this.getNexusFileWriter(history, configuration);
		return writer.write();
	}
	
	public abstract NexusFileReader getNexusFileReader();
	
	public abstract NexusFileWriter getNexusFileWriter(CoevolutionaryHistory history, Configuration configuration);
}
