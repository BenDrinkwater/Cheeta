package io.nexus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import util.ComparablePair;

public abstract class NexusFileReaderCommon implements NexusFileReader {

	protected Scanner fileReader;
	
	protected String hostString;
	protected String parasiteString;
	protected List<ComparablePair<String, String>> associationStrings;

	public NexusFileReaderCommon(File file) {
		try {
			this.fileReader = new Scanner(file);
		}
		catch (FileNotFoundException e) {
			this.fileReader = null;
		}
		catch (NullPointerException e) {
			this.fileReader = null;
		}
	}
	
	@Override
	public String getHostString() {
		return this.hostString;
	}

	@Override
	public String getParasiteString() {
		return this.parasiteString;
	}

	@Override
	public List<ComparablePair<String, String>> getAssociationStrings() {
		return this.associationStrings;
	}
}
