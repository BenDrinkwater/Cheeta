package io.nexus;

import tanglegram.CoevolutionaryHistory;

public class NexusFileLoader {

	private String fileName;
	private NexusFile file;
	
	public NexusFileLoader(String fileName) {
		this.fileName = fileName; 
	}
	
	public CoevolutionaryHistory loadHistory() {
		this.file = new NexusFileIdentifier(this.fileName).loadNexusFile();
		if (null == this.file) {
			return null;
		}
		try {
			return this.file.read();
		}
		catch (NexusParsingException e){
			return null;
		}
	}
}
