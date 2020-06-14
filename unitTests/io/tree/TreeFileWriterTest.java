package io.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tanglegram.CoevolutionaryHistory;
import tree.Node;
import tree.RootedBifurcatingTree;

public class TreeFileWriterTest {

	private CoevolutionaryHistory testCase1;
	private final String testCase1FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	
		
	//TODO
	
	@Before
	public void loadPocketGopherLouseExample() {
		try {
			NexusFile file = new JaneNexusFile(this.testCase1FileName);
			this.testCase1 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();	
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.testCase1.getHostTree().getNode("InternalNode_00002"), 6);			
			this.testCase1.getHostTree().setUniqueNodeTimings(nodes);	
			((RootedBifurcatingTree) this.testCase1.getHostTree()).cacheValues();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void assignReferenceList() {
		TreeFileWriter writer = new TreeFileWriter(this.testCase1, new File("TreeTest.tree"));
		writer.assignReferenceList();
		assertNotNull(writer.referenceList);
		assertEquals(36, writer.referenceList.size());
	}
	
	@Test
	public void getHostTreeString() {
		TreeFileWriter writer = new TreeFileWriter(this.testCase1, new File("TreeTest.tree"));
		writer.assignReferenceList();
	}
	
	@Test
	public void outputString() {
		TreeFileWriter writer = new TreeFileWriter(this.testCase1, new File("TreeTest.tree"));
		writer.generateOutputString();
	}	
}
