package io.nexus.corepa;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import util.ComparablePair;

import io.nexus.NexusFileReaderCommon;
import io.nexus.NexusFormatException;

public class CorePaNexusFileReader extends NexusFileReaderCommon {

	protected List<String> taxa;
	protected Map<String, String> treeTranslation;
	
	protected enum TranslationMode {
		Searching,
		Taxa,
		Trees,
		Cophylogeny,
		Phi
	}
	
	protected enum TreeTranslationMode {
		Searching,
		NodeReference,
		HostTree,
		ParasiteTree
	}
	
	public CorePaNexusFileReader(File file) {
		super(file);
	}

	@Override
	public boolean parse() throws NexusFormatException {
		
		TranslationMode mode = TranslationMode.Searching;
		
		List<String> taxaStrings = new LinkedList<String>();
		List<String> treeStrings = new LinkedList<String>();
		List<String> phiStrings = new LinkedList<String>();
		
		while(this.fileReader.hasNext()) {
			String line = fileReader.nextLine();
			if ("BEGIN TAXA;".equals(line)) {
				mode = TranslationMode.Taxa;
				continue;
			}
			else if ("BEGIN TREES;".equals(line)) {
				mode = TranslationMode.Trees;
				continue;
			}
			else if ("BEGIN COPHYLOGENY;".equals(line)) {
				mode = TranslationMode.Cophylogeny;
				continue;
			}
			else if (TranslationMode.Cophylogeny == mode && line.trim().equals("PHI")) {
				mode = TranslationMode.Phi;
				continue;
			}
			else if ("ENDBLOCK;".equals(line)) {
				mode = TranslationMode.Searching;
				continue;
			}
			else if (TranslationMode.Phi == mode && "\t\t;".equals(line)) {
				mode = TranslationMode.Searching;
				continue;
			}			
			switch (mode) {
				case Taxa : taxaStrings.add(line.trim());
					break;
				case Trees : treeStrings.add(line.trim());
					break;
				case Phi : phiStrings.add(line.trim());
					break;					
			}
		}
		
		this.processTaxaString(taxaStrings);
		this.processTreeStrings(treeStrings);
		this.processPhiStrings(phiStrings);
		
		return true;
	}

	private void processTaxaString(List<String> taxaStrings) {
		this.taxa = new LinkedList<String>();
		for (String str : taxaStrings) {
			if (str.contains("DIMENSIONS NTAX") || str.contains("TAXLABELS") || str.equals(";")) {
				continue;
			}
			this.taxa.add(str);
		}
	}
	
	private void processTreeStrings(List<String> treeStrings) {
		this.treeTranslation = new LinkedHashMap<String, String>();
		
		TreeTranslationMode treeTranslationMode = TreeTranslationMode.Searching;
		
		for (String str : treeStrings) {
			if ("TRANSLATE".equals(str)) {
				treeTranslationMode = TreeTranslationMode.NodeReference;
				continue;
			}
			if (";".equals(str)) {
				treeTranslationMode = TreeTranslationMode.Searching;
				continue;
			}
			if (str.contains("TREE") && TreeTranslationMode.Searching == treeTranslationMode) {
				treeTranslationMode = TreeTranslationMode.HostTree;
			}
			
			switch(treeTranslationMode) {
				case NodeReference : this.treeTranslation.put(this.getPair(str).getFirst(), this.getPair(str).getSecond());
					break;
				case HostTree : super.hostString = this.cleanCorePaTreeString(this.trimTreeLine(str)).trim();
					treeTranslationMode = TreeTranslationMode.ParasiteTree;
					break;
				case ParasiteTree : super.parasiteString = this.cleanCorePaTreeString(this.trimTreeLine(str)).trim();		
			}
		}
		this.updateTrees();
	}		
	
	private void processPhiStrings(List<String> phiStrings) {
		super.associationStrings = new LinkedList<ComparablePair<String, String>>();
		for (String str : phiStrings) {
			ComparablePair<String, String> associationPair = this.getPair(str.replaceAll(",", "").trim());
			if (null != associationPair) {
				super.associationStrings.add(associationPair);
			}
		}
	}
	
	protected ComparablePair<String, String> getPair(String line) {
		line = line.replaceAll(",", "");
		String[] segements = line.split("\t");
		if (segements.length < 2) {
			segements = line.split(" ");
		}
		if (segements.length == 2) {
			return new ComparablePair<String, String>(segements[0].trim(), segements[1].trim());
		}
		return null;
	}
	
	protected String trimTreeLine(String line) {
		int index = line.indexOf("=") + 1;
		return line.substring(index);		
	}

	protected String cleanCorePaTreeString(String corePaString) {
		StringBuilder cleanString = new StringBuilder();
		boolean closingBracketFound = false;
		for (int i = 0; i < corePaString.length(); ++i) {
			if (',' == corePaString.charAt(i)) {
				closingBracketFound = false;
			}
			if (!closingBracketFound || ')' == corePaString.charAt(i)) {
				cleanString.append(corePaString.charAt(i));
			}
			if (')' == corePaString.charAt(i)) {
				closingBracketFound = true;
			}
		}
		return cleanString.toString();
	}
	
	protected void updateTrees() {
		for (Map.Entry<String, String> entry : this.treeTranslation.entrySet()) {
			this.hostString = this.hostString.replaceAll(entry.getKey(), entry.getValue());
			this.parasiteString = this.parasiteString.replaceAll(entry.getKey(), entry.getValue());
		}
	}
}
