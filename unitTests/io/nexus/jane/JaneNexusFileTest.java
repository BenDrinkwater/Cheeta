package io.nexus.jane;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import io.configuration.DefaultConfiguration;
import io.nexus.FileWriterTestCommon;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.NexusParsingException;

public class JaneNexusFileTest extends FileWriterTestCommon {

	private final String invalidNexusFile1 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "InvalidAssociations.nex";
	private final String invalidNexusFile2 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MalformedAssociations.nex";
	private final String invalidNexusFile3 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MalformedHostTree.nex";
	private final String invalidNexusFile4 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MalformedParasiteTree.nex";
	private final String invalidNexusFile5 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MissingHostTree.nex";
	private final String invalidNexusFile6 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MissingParasiteAssociation.nex";
	private final String invalidNexusFile7 = "unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MissingParasiteTree.nex";
	
	public JaneNexusFileTest() {
		super("CorePaExample.nex", "JaneExample.nex");
	}

	@Test
	public void ParseInvalidNexusFileTest1() {
		try {
			NexusFile nexusFile = new JaneNexusFile(this.invalidNexusFile1);
			nexusFile.read();
			fail("Nexus Format Exception Should be Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("Cannot locate leaf node 3 in parasite tree", e.getMessage());
		} 
		catch (NexusFileHandlingException e) {
			fail("Nexus Format Exception Should be Thrown");
		}
	}
	
	@Test
	public void ParseInvalidNexusFileTest2() {
		try {
			NexusFile nexusFile = new JaneNexusFile(this.invalidNexusFile2);
			nexusFile.read();
			fail("Nexus Format Exception Should be Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("Malformed Association String", e.getMessage());
		} 
		catch (NexusFileHandlingException e) {
			fail("Nexus Format Exception Should be Thrown");
		}	
	}
	
	@Test
	public void ParseInvalidNexusFileTest3() {
		try {
			NexusFile nexusFile = new JaneNexusFile(this.invalidNexusFile3);
			nexusFile.read();
			fail("Nexus Format Exception Should be Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("Braces mismatch in tree ((3,4),5,6", e.getMessage());
		} 
		catch (NexusFileHandlingException e) {
			fail("Nexus Format Exception Should be Thrown");
		}
	}
	
	@Test
	public void ParseInvalidNexusFileTest4() {
		try {
			NexusFile nexusFile = new JaneNexusFile(this.invalidNexusFile4);
			nexusFile.read();
			fail("Nexus Format Exception Should be Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("Comma mismatch in tree ((10:11):(12:13))", e.getMessage());
		} 
		catch (NexusFileHandlingException e) {
			fail("Nexus Format Exception Should be Thrown");
		}
	}
	
	@Test
	public void ParseInvalidNexusFileTest5() {
		try {
			NexusFile nexusFile = new JaneNexusFile(this.invalidNexusFile5);
			nexusFile.read();
			fail("Nexus Format Exception Should be Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("Tree is missing from nexus file", e.getMessage());
		} 
		catch (NexusFileHandlingException e) {
			fail("Nexus Format Exception Should be Thrown");
		}
	}
	
	@Test
	public void ParseInvalidNexusFileTest6() {
		try {
			NexusFile nexusFile = new JaneNexusFile(this.invalidNexusFile6);
			nexusFile.read();
			fail("Nexus Format Exception Should be Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("Unable to read associations from Nexus File", e.getMessage());
		} 
		catch (NexusFileHandlingException e) {
			fail("Nexus Format Exception Should be Thrown");
		}
	}
	
	@Test
	public void ParseInvalidNexusFileTest7() {
		try {
			NexusFile nexusFile = new JaneNexusFile(this.invalidNexusFile7);
			nexusFile.read();
			fail("Nexus Format Exception Should be Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("Tree is missing from nexus file", e.getMessage());
		} 
		catch (NexusFileHandlingException e) {
			fail("Nexus Format Exception Should be Thrown");
		}
	}
	
	@Test
	public void WriteTest1() {
		try {
			NexusFile nexusFile = new JaneNexusFile(super.corePaFileName);
			nexusFile.write(this.smallHistory);
			String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "jane" + File.separator + "expectedWriteFiles" + File.separator + "SmallNexusFile.nex");
			String actual = this.getStringAsWrittenToFile(this.corePaFileName);
			assertEquals(expected, actual);
		}
		catch(NexusParsingException e) {
			fail("Unexpected exception has been thrown");
		}
	}
	
	@Test
	public void WriteTest2() {
		try {
			NexusFile nexusFile = new JaneNexusFile(super.janeFileName);
			nexusFile.write(this.largeHistory, new DefaultConfiguration());
			String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "jane" + File.separator + "expectedWriteFiles" + File.separator + "LargeNexusFile.nex");
			String actual = this.getStringAsWrittenToFile(super.janeFileName);
			assertEquals(expected, actual);
		}
		catch(NexusParsingException e) {
			fail("Unexpected exception has been thrown");
		}
	}		
}
