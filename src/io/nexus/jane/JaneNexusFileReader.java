package io.nexus.jane;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import util.ComparablePair;

import io.nexus.NexusFileReaderCommon;
import io.nexus.NexusFormatException;

public class JaneNexusFileReader extends NexusFileReaderCommon {
	
	private enum LineToProcess {
		HostTree,
		ParasiteTree,
		Associations,
		Unknown
	}	
	
	protected String associationString;
	
	public JaneNexusFileReader(File file) {
		super(file);
		this.associationString = "";
	}

	@Override
	public boolean parse() throws NexusFormatException {
		List<String> lines = new LinkedList<String>();	
		if (null == this.fileReader) {
			throw new NexusFormatException("FileReader not initialised correctly");
		}
		while(super.fileReader.hasNext()) {
			String line = super.fileReader.nextLine().toLowerCase();
			if (line.trim().length() > 0) {
				if (line.contains(",") && !line.contains("tree")) {
					String[] linesOfAssociations = line.split(",");
					if (linesOfAssociations.length > 2) {
						for (String associationPair : linesOfAssociations) {
							lines.add(associationPair + ",");
						}
						continue;
					}
				}
				lines.add(line);
			}
		}
		this.parseLines(lines);
		return null != super.hostString && 
				null != super.parasiteString &&
				null != super.associationStrings &&
				0 != this.associationStrings.size();
	}
	
	private void parseLines(List<String> lines) throws NexusFormatException {
		if (null == lines) {
			throw new NexusFormatException("List containing file lines is null");
		}		
		
		LineToProcess lineToProcess = LineToProcess.Unknown;
		
		for (String line : lines) {
			
			if (null == line) {
				continue;
			}
			
			line = line.toLowerCase().trim();
			
			if (line.equals("end;") || line.equals("endblock;")) {
				lineToProcess = LineToProcess.Unknown;
				continue;
			}
			
			if (LineToProcess.HostTree == lineToProcess) {
				super.hostString = line.substring(line.indexOf('=') + 1, line.length() - 1);
				lineToProcess = LineToProcess.Unknown;
				continue;
			}
			else if (LineToProcess.ParasiteTree == lineToProcess) { 
				super.parasiteString = line.substring(line.indexOf('=') + 1, line.length() - 1);
				lineToProcess = LineToProcess.Unknown;
				continue;
			}
			else if (LineToProcess.Associations == lineToProcess) { 
				if (!(line.equals("range") || line.equals(";"))) {
					this.associationString = this.associationString + line;
				}
				continue;
			}
			
			if (line.equals("begin host;")) {
				lineToProcess = LineToProcess.HostTree;
			}
			else if (line.equals("begin parasite;")) {
				lineToProcess = LineToProcess.ParasiteTree;
			}
			else if (line.equals("begin distribution;")) {
				lineToProcess = LineToProcess.Associations;
			}
		}
		this.processAssociations();
	}

	protected void processAssociations() throws NexusFormatException {
		if (this.associationString.length() > 1) {
			if (this.associationString.contains(";")) {
				this.associationString = this.associationString.substring(0, this.associationString.lastIndexOf(";"));
			}
			super.associationStrings = new LinkedList<ComparablePair<String, String>>();
			String[] associationPairs = this.associationString.split(",");
			for (String pairs : associationPairs) {
				String[] tuple = pairs.split(":");
				if (tuple.length < 2) {
					throw new NexusFormatException("Malformed Association String");
				}
				ComparablePair<String, String> associationPair = new ComparablePair<String, String>(tuple[0].trim(), tuple[1].trim());
				super.associationStrings.add(associationPair);
				
			}
		}
	}
}
