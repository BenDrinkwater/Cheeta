package io.tree;

import tanglegram.CoevolutionaryHistory;

public class TreeFileLoader {

	private String fileName;
	private TreeFile file;	
	
	public TreeFileLoader(String fileName) {
		this.fileName = fileName;
	}
	
	public CoevolutionaryHistory loadHistory() throws TreeFileParsingException {
		this.file = new TreeFile(this.fileName);
		return (null != this.file) ? this.file.read() : null;
	}
	
}
