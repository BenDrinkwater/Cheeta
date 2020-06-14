package io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tanglegram.CoevolutionaryHistory;
import tree.Tree;
import util.ComparablePair;

public class AssociationBuilderExceptionCasesTest {
	
	private CoevolutionaryHistory gopherLice;
	private Tree hostTree;
	private Tree parasiteTree;
	private final String gopherLiceFileName 
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	

	@Before
	public void LoadGopherLiceExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.gopherLiceFileName);		
			this.gopherLice = file.read();
			this.hostTree = this.gopherLice.getHostTree();
			this.parasiteTree = this.gopherLice.getParasiteTree();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Test
	public void ExceptionCaseTest1() {
		List<ComparablePair<String, String>> list = new LinkedList<ComparablePair<String, String>>();
		list.add(new ComparablePair<String, String>("Non existant host tree", "talpoides"));
		list.add(new ComparablePair<String, String>("thomomyus", "talpoides"));
		assertEquals(2, list.size());
		AssociationBuilder builder = new AssociationBuilder(list);
		assertEquals(2, builder.associationStrings.size());
		try {
			builder.populateAssociations(this.hostTree, this.parasiteTree);
			fail("Exception should have been thrown");
		} catch (CophylogenyFileParsingException e) {
			assertEquals("Cannot locate leaf node Non existant host tree in parasite tree", e.getMessage());
		}
	}
	
	@Test
	public void ExceptionCaseTest2() {
		List<ComparablePair<String, String>> list = new LinkedList<ComparablePair<String, String>>();
		list.add(new ComparablePair<String, String>("TEMP", "wardi"));
		AssociationBuilder builder = new AssociationBuilder(list);
		try {
			builder.populateAssociations(this.hostTree, this.parasiteTree);
			fail("Exception should have been thrown");
		} catch (CophylogenyFileParsingException e) {
			assertEquals("Cannot locate leaf node TEMP in parasite tree", e.getMessage());
		}		
	}
	
	@Test
	public void ExceptionCaseTest3() {
		List<ComparablePair<String, String>> list = new LinkedList<ComparablePair<String, String>>();
		list.add(new ComparablePair<String, String>("wardi", null));
		list.add(new ComparablePair<String, String>("minor", null));
		list.add(new ComparablePair<String, String>("thomomyus", null));
		AssociationBuilder builder = new AssociationBuilder(list);
		try {
			builder.populateAssociations(this.hostTree, this.parasiteTree);
			fail("Exception should have been thrown");
		} catch (CophylogenyFileParsingException e) {
			assertEquals("Malformed Set Of Assoications", e.getMessage());
		}		
	}	
	
	@Test
	public void ExceptionCaseTest4() {
		List<ComparablePair<String, String>> list = null;
		AssociationBuilder builder = new AssociationBuilder(list);
		try {
			builder.populateAssociations(this.hostTree, this.parasiteTree);
			fail("Exception should have been thrown");
		} catch (CophylogenyFileParsingException e) {
			assertEquals("Unable to read associations from Nexus File", e.getMessage());
		}		
	}
}
