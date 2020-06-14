package io.nexus.jane;

import static org.junit.Assert.assertEquals;
import io.nexus.FileWriterTestCommon;

import java.io.File;
import org.junit.Test;

public class JaneNexusFileWriterTest extends FileWriterTestCommon {
	
	public JaneNexusFileWriterTest() {
		super("CorePaExample.nex", "JaneExample.nex");
	}
	
	@Test
	public void HostTreeStringTest() {
		JaneNexusFileWriter nexusFileWriter = new JaneNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals("begin host;\ntree host =((Leaf1,Leaf2),Leaf3);\nendblock;\n", nexusFileWriter.getHostTreeString());
	}
	
	@Test
	public void ParasiteTreeStringTest() {
		JaneNexusFileWriter nexusFileWriter = new JaneNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals("begin parasite;\ntree parasite =((Leaf4,Leaf5),Leaf6);\nendblock;\n", nexusFileWriter.getParasiteTreeString());		
	}	
	
	@Test
	public void AssociationStringTest() {
		JaneNexusFileWriter nexusFileWriter = new JaneNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals("begin distribution;\nRange\nLeaf6 : Leaf1, Leaf5 : Leaf2, Leaf4 : Leaf3;\nendblock;", nexusFileWriter.getAssociationsString());				
	}
	
	@Test
	public void WriteTest1() {
		JaneNexusFileWriter nexusFileWriter = new JaneNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals(true, nexusFileWriter.write());
		String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "jane" + File.separator + "expectedWriteFiles" + File.separator + "SmallNexusFile.nex");
		String actual = this.getStringAsWrittenToFile(this.corePaFileName);
		assertEquals(expected, actual);
	}
	
	@Test
	public void WriteTest2() {
		JaneNexusFileWriter nexusFileWriter = new JaneNexusFileWriter(super.largeHistory, new File(super.janeFileName));
		assertEquals(true, nexusFileWriter.write());
		String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "jane" + File.separator + "expectedWriteFiles" + File.separator + "LargeNexusFile.nex");
		String actual = this.getStringAsWrittenToFile(super.janeFileName);
		assertEquals(expected, actual);
	}
}
