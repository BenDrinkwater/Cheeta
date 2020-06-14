package io.nexus;

import io.configuration.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tanglegram.CoevolutionaryHistory;

public abstract class NexusFileWriter {

	protected File file;
	protected String outputString;
	protected CoevolutionaryHistory history;
	protected Configuration configuration;
	
	public NexusFileWriter(CoevolutionaryHistory history, File file, Configuration configuration) {
		this.history = history;
		this.file = file;
		this.configuration = configuration;
	}
	
	protected abstract boolean generateOutputString();
	
	public boolean write() {
		this.generateOutputString();
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(file));
	        out.write(this.outputString);
	        out.close();
	    } 
		catch (IOException e) {
			return false;
		}
		catch (NullPointerException e) {
			return false;
	    }
		return true;
	}
}
