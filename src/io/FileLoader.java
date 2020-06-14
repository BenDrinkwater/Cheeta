package io;

import io.nexus.NexusFileLoader;
import io.tree.TreeFileLoader;
import io.tree.TreeFileParsingException;
import tanglegram.CoevolutionaryHistory;

public final class FileLoader {
	
	private CoevolutionaryHistory history;
	private String fileName;
	
	public FileLoader(String fileName) {
		this.fileName = fileName;
	}
	
	public boolean loadFile() {
		// allowing for extension possible tree format later
		if (".nex".equals(this.getExtension())) {
			NexusFileLoader nexusFileLoader = new NexusFileLoader(this.fileName);
			this.history = nexusFileLoader.loadHistory();
			return null != this.history;
		}
		else if (".tree".equals(this.getExtension())) {
			TreeFileLoader treeFileLoader = new TreeFileLoader(this.fileName);
			try {
				this.history = treeFileLoader.loadHistory();
				return true;
			} 
			catch (TreeFileParsingException e) {
				return false;
			}
		}
		return false;
	}
	
	protected String getExtension() {
		if (null == this.fileName) {
			return null;
		}
		int index = this.fileName.lastIndexOf(".");
		return this.fileName.substring(index);
	}
	
	public CoevolutionaryHistory getHistory() {
		return this.history;
	}
	
}
