package io.nexus;


import java.util.List;

import util.ComparablePair;

public interface NexusFileReader {

	public boolean parse() throws NexusFormatException;
	
	public String getHostString();
	
	public String getParasiteString();
	
	public List<ComparablePair<String, String>> getAssociationStrings();
	
}
