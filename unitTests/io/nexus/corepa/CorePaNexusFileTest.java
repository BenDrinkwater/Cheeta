package io.nexus.corepa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import io.configuration.DefaultConfiguration;
import io.nexus.FileWriterTestCommon;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusParsingException;

import java.io.File;

import org.junit.Test;

public class CorePaNexusFileTest extends FileWriterTestCommon {
	
	public CorePaNexusFileTest() {
		super("CorePaExample.nex", "JaneExample.nex");
	}

	@Test
	public void CreateNexusFile() {
		try {
			NexusFile file 
				= new CorePaNexusFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "corepa" + File.separator + "validTestFiles" + File.separator + "TestCase1.nex");
			file.read();
		} 
		catch (NexusParsingException e) {
			fail("Unexpected Exception has occured");
		}
	}
	
	@Test
	public void ValidateNexusFileRead1() {	
		try {
			NexusFile file 
				= new CorePaNexusFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "corepa" + File.separator + "validTestFiles" + File.separator + "TestCase1.nex");
			assertEquals(3, file.read().getHostTree().numberOfLeafNodes());
			assertEquals(3, file.read().getParasiteTree().numberOfLeafNodes());
			assertEquals(3, file.read().getAssociations().size());
			assertEquals(2, file.read().getHostTree().numberOfInteranlNodes());
			assertEquals(2, file.read().getParasiteTree().numberOfInteranlNodes());			
		} 
		catch (NexusParsingException e) {
			fail("Unexpected Exception has occured");
		}		
	}
	
	@Test
	public void ValidateNexusFileRead2() {	
		try {
			NexusFile file 
				= new CorePaNexusFile(new File("unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "corepa" + File.separator + "validTestFiles" + File.separator + "TestCase1.nex"));
			assertEquals(3, file.read().getHostTree().numberOfLeafNodes());
			assertEquals(3, file.read().getParasiteTree().numberOfLeafNodes());
			assertEquals(3, file.read().getAssociations().size());
			assertEquals(2, file.read().getHostTree().numberOfInteranlNodes());
			assertEquals(2, file.read().getParasiteTree().numberOfInteranlNodes());			
		} 
		catch (NexusParsingException e) {
			fail("Unexpected Exception has occured");
		}		
	}
	
	@Test
	public void WriteTest1() {
		try {
			NexusFile nexusFile = new CorePaNexusFile(super.corePaFileName);
			nexusFile.write(super.smallHistory);
			String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "corepa" + File.separator + "expectedWriteFiles" + File.separator + "SmallNexusFile.nex");
			String actual = this.getStringAsWrittenToFile(this.corePaFileName);
			assertEquals(expected, actual);		
		}
		catch (NexusFileHandlingException e) {
			fail("Unexpected Exception has occured");
		}
	}
	
	@Test
	public void WriteTest2() {
		try {
			NexusFile nexusFile = new CorePaNexusFile(super.corePaFileName);
			nexusFile.write(super.smallHistory, new DefaultConfiguration());
			String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "corepa" + File.separator + "expectedWriteFiles" + File.separator + "SmallNexusFile.nex");
			String actual = this.getStringAsWrittenToFile(this.corePaFileName);
			assertEquals(expected, actual);		
		}
		catch (NexusFileHandlingException e) {
			fail("Unexpected Exception has occured");
		}			
	}	
}