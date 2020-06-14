package io.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import util.ComparablePair;

public class TreeFileReader {

	private enum Parsing {
		HostTreeParsing,
		HostNameParsing,
		ParasiteTreeParsing,
		ParasiteNameParsing,
		AssociationParsing,
		NoParsing
	}
	
	private File file;
	private String hostString;
	private String parasiteString;
	private List<ComparablePair<String, String>> associations;
	
	private Map<String, String> hostNames;
	private Map<String, String> parasiteNames;
	
	public TreeFileReader(File file) {
		this.file = file;
	}

	public void parse() throws TreeFileParsingException {
		if (null == this.file) {
			throw new TreeFileParsingException("File is null");
		}
		try {
			Scanner scanner = new Scanner(this.file);
			Parsing parserState = Parsing.NoParsing;
			this.associations = new LinkedList<ComparablePair<String, String>>();
			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.equals("HOSTTREE")) {
					parserState = Parsing.HostTreeParsing;
					continue;
				}
				else if (line.equals("HOSTNAMES")) {
					this.hostString = builder.toString();
					parserState = Parsing.HostNameParsing;
					this.hostNames = new HashMap<String, String>();
					continue;
					
				}
				else if (line.equals("PARASITETREE")) {	
					builder = new StringBuilder();
					parserState = Parsing.ParasiteTreeParsing;
					continue;				
				}
				else if (line.equals("PARASITENAMES")) {
					this.parasiteString = builder.toString();				
					parserState = Parsing.ParasiteNameParsing;
					this.parasiteNames = new HashMap<String, String>();
					continue;							
				}				
				else if (line.equals("PHI")) {
					builder = new StringBuilder();					
					parserState = Parsing.AssociationParsing;
					continue;				
				}
				else if (line.equals("HOSTRANKS")) {				
					parserState = Parsing.NoParsing;
					continue;
				}
				
				if (Parsing.HostTreeParsing == parserState || Parsing.ParasiteTreeParsing == parserState) {
					if (line.length() > 1 && line.split("\t").length == 3) {
						String[] tuples = line.split("\t");
						builder.append(" ");
						builder.append(tuples[0]);
						builder.append("\t ");
						builder.append(tuples[1]);
						builder.append("\t ");
						builder.append(tuples[2]);
						builder.append("\n");		
					}
					else if (line.length() > 1 && line.split("\t").length == 2) {
						String[] tuples = line.split("\t");
						builder.append(" ");
						builder.append(tuples[0]);
						builder.append("\t ");
						builder.append(tuples[1]);
						builder.append("\n");					
					}
					else {
						continue;
					}
				}
				else if (Parsing.HostNameParsing == parserState || Parsing.ParasiteNameParsing == parserState) {
					if (line.length() >= 2) {
						String[] tuples = line.split("\t");
						if (tuples.length == 2) {
							if (Parsing.HostNameParsing == parserState) {
								this.hostNames.put(tuples[0], tuples[1]);
							}
							else if (Parsing.ParasiteNameParsing == parserState) {
								this.parasiteNames.put(tuples[0], tuples[1]);
							}
						}
					}
				}
				else if (Parsing.AssociationParsing == parserState) {
					if (line.length() >= 3) {
						String[] tuples = line.split("\t");
						if (tuples.length == 2) {
							if (null == this.hostNames) {
								throw new TreeFileParsingException("Missing host tree string");
							}
							else if (null == this.parasiteNames) {
								throw new TreeFileParsingException("Missing parasite tree string");								
							}
							else if (null == this.hostNames.get(tuples[0]))  {
								throw new TreeFileParsingException("Host Tree not found in association string");
							}							
							else if (null == this.parasiteNames.get(tuples[1]))  {
								throw new TreeFileParsingException("Parasite Tree not found in association string");
							}
							this.associations.add(new ComparablePair<String, String> (this.parasiteNames.get(tuples[1]), this.hostNames.get(tuples[0])));
						}
						else if (tuples.length > 2) {
							for (int i = 1; i < tuples.length; ++i) {
								this.associations.add(new ComparablePair<String, String> (this.parasiteNames.get(tuples[i]), this.hostNames.get(tuples[0])));
							}
						}
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			throw new TreeFileParsingException("File Not Found");
		}
		if (0 == this.associations.size()) {
			throw new TreeFileParsingException("No associations in Tree File");
		}
	}

	protected String getHostString() {
		return this.hostString;
	}
	
	public String getHostStringWithNames() {
		return this.withNames(this.hostString, this.getHostNames());
	}
	
	protected Map<String, String> getHostNames() {
		return this.hostNames;
	}

	protected String getParasiteString() {
		return this.parasiteString;
	}
	
	public String getParasiteStringWithNames() {
		return this.withNames(this.parasiteString, this.getParasiteNames());
	}
	
	protected Map<String, String> getParasiteNames() {
		return this.parasiteNames;
	}

	public List<ComparablePair<String, String>> getAssociationStrings() {
		return this.associations;
	}
	
	private String withNames(String str, Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			str = str.replaceAll(" " + entry.getKey() + "\t", entry.getValue() + "\t");
			str = str.replaceAll(" " + entry.getKey() + "\n", entry.getValue() + "\n");
		}
		return str;		
	}
}
