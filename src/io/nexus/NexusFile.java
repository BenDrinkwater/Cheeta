package io.nexus;

import io.configuration.Configuration;
import tanglegram.CoevolutionaryHistory;

public interface NexusFile {
	
	public CoevolutionaryHistory read() throws NexusFormatException, NexusFileHandlingException;
	
	public boolean write(CoevolutionaryHistory history);
	
	public boolean write(CoevolutionaryHistory history, Configuration configuration);
}