package io.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import io.CophylogenyFileParsingException;

import java.io.File;

import org.junit.Test;

public class InvalidTreeFileTest {

	private final String testFile1 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile1.tree";
	private final String testFile2 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile2.tree";
	private final String testFile3 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile3.tree";
	private final String testFile4 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile4.tree";
	private final String testFile5 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile5.tree";
	private final String testFile6 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile6.tree";
	private final String testFile7 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "invalidTestFiles" + File.separator + "InvaidTreeFile7.tree";
	
	@Test
	public void InvalidTestFile1() {
		TreeFile file = new TreeFile(this.testFile1);
		assertNotNull(file);
		try {
			file.read();
			fail("Exception not thrown");
		} 
		catch (TreeFileParsingException e) {
			assertEquals("Missing host tree string", e.getMessage());
		}		
	}
	
	@Test
	public void InvalidTestFile2() {
		TreeFile file = new TreeFile(this.testFile2);
		assertNotNull(file);
		try {
			file.read();
			fail("Exception not thrown");
		} 
		catch (TreeFileParsingException e) {
			assertEquals("Missing parasite tree string", e.getMessage());
		}		
	}
	
	@Test
	public void InvalidTestFile3() {
		TreeFile file = new TreeFile(this.testFile3);
		assertNotNull(file);
		try {
			file.read();
			fail("Exception not thrown");
		} 
		catch (TreeFileParsingException e) {
			assertEquals("No associations in Tree File", e.getMessage());
		}		
	}
	
	@Test
	public void InvalidTestFile4() {
		TreeFile file = new TreeFile(this.testFile4);
		assertNotNull(file);
		try {
			file.read();
			fail("Exception not thrown");
		} 
		catch (CophylogenyFileParsingException e) {
			assertEquals("Missing host Tree", e.getMessage());
		}		
	}	
	
	@Test
	public void InvalidTestFile5() {
		TreeFile file = new TreeFile(this.testFile5);
		assertNotNull(file);
		try {
			file.read();
			fail("Exception not thrown");
		} 
		catch (CophylogenyFileParsingException e) {
			assertEquals("Missing parasite Tree", e.getMessage());
		}		
	}
	
	@Test
	public void InvalidTestFile6() {
		TreeFile file = new TreeFile(this.testFile6);
		assertNotNull(file);
		try {
			file.read();
			fail("Exception not thrown");
		} 
		catch (TreeFileParsingException e) {
			assertEquals("Parasite Tree not found in association string", e.getMessage());
		}		
	}
	
	@Test
	public void InvalidTestFile7() {
		TreeFile file = new TreeFile(this.testFile7);
		assertNotNull(file);
		try {
			file.read();
			fail("Exception not thrown");
		} 
		catch (TreeFileParsingException e) {
			assertEquals("Host Tree not found in association string", e.getMessage());
		}		
	}	
}
