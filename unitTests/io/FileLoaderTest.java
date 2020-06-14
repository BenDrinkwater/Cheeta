package io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import tanglegram.CoevolutionaryHistory;

public class FileLoaderTest {

	private final String corePaFile = "unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "corepa" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex";
	private final String janeFile = "unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex";
	private final String treeFile = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "validTestFiles" + File.separator + "WidespreadMultiParasiteTest.tree";
	
	private final String invalidFile1 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "InvalidAssociations.nex";
	private final String invalidFile2 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile1.tree";
	private final String invalidFile3 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MissingHostTree.nex";
	
	@Test
	public void loadCorePaNexusFile() {
		FileLoader fileLoader = new FileLoader(this.corePaFile);
		assertEquals(true, fileLoader.loadFile());
		CoevolutionaryHistory history = fileLoader.getHistory();
		assertNotNull(history);
	}
	
	@Test
	public void loadJaneNexusFile() {
		FileLoader fileLoader = new FileLoader(this.janeFile);
		assertEquals(true, fileLoader.loadFile());
		CoevolutionaryHistory history = fileLoader.getHistory();
		assertNotNull(history);
	}
	
	@Test
	public void loadTreeFile() {
		FileLoader fileLoader = new FileLoader(this.treeFile);
		assertEquals(true, fileLoader.loadFile());
		CoevolutionaryHistory history = fileLoader.getHistory();
		assertNotNull(history);
	}
	
	@Test
	public void validateExceptionHandling1() {
		FileLoader fileLoader = new FileLoader(null);
		assertEquals(false, fileLoader.loadFile());		
	}	
	
	@Test
	public void validateExceptionHandling2() {
		FileLoader fileLoader = new FileLoader(this.invalidFile1);
		assertEquals(false, fileLoader.loadFile());		
	}	
	
	@Test
	public void validateExceptionHandling3() {
		FileLoader fileLoader = new FileLoader(this.invalidFile2);
		assertEquals(false, fileLoader.loadFile());		
	}	
	
	@Test
	public void validateExceptionHandling4() {
		FileLoader fileLoader = new FileLoader(this.invalidFile3);
		assertEquals(false, fileLoader.loadFile());		
	}	
}
