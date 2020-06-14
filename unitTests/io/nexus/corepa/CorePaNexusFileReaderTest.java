package io.nexus.corepa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import io.nexus.NexusFormatException;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import util.ComparablePair;

public class CorePaNexusFileReaderTest {

	@Test
	public void CleanCorePaTreeStringTest1() {
		CorePaNexusFileReader fileReader = new CorePaNexusFileReader(null);
		String treeString = fileReader.cleanCorePaTreeString("((H2,H3)H1,H4)H0;");
		assertEquals("((H2,H3),H4)", treeString);
	}
	
	@Test
	public void CleanCorePaTreeStringTest2() {
		CorePaNexusFileReader fileReader = new CorePaNexusFileReader(null);
		String treeString = fileReader.cleanCorePaTreeString("(((H4,H5)H3,(((H9,(H11,(H13,H14)H12)H10)H8,H15)H7,H16)H6)H2,H17)H1,H18)H0;");
		assertEquals("(((H4,H5),(((H9,(H11,(H13,H14))),H15),H16)),H17),H18)", treeString);
	}
	
	@Test
	public void ParseTest1() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "corepa" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex");
		CorePaNexusFileReader fileReader = new CorePaNexusFileReader(file);
		try {
			fileReader.parse();
			assertEquals(6, fileReader.taxa.size());
			List<String> expected = Arrays.asList(new String[]{"Leaf1", "Leaf2", "Leaf3", "Leaf4", "Leaf5", "Leaf6"}); 
			assertEquals(expected, fileReader.taxa);
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}
	
	@Test
	public void ParseTest2() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "corepa" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex");
		CorePaNexusFileReader fileReader = new CorePaNexusFileReader(file);
		try {
			fileReader.parse();
			assertEquals(10, fileReader.treeTranslation.size());
			assertEquals("HOST_ROOT", fileReader.treeTranslation.get("H0"));
			assertEquals("((Leaf1,Leaf2),Leaf3)", fileReader.getHostString());
			assertEquals("((Leaf4,Leaf5),Leaf6)", fileReader.getParasiteString());
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}	
	
	@Test
	public void ParseTest3() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "corepa" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex");
		CorePaNexusFileReader fileReader = new CorePaNexusFileReader(file);
		try {
			fileReader.parse();
			List<ComparablePair<String, String>> expected = new LinkedList<ComparablePair<String, String>>();
			expected.add(new ComparablePair<String, String>("Leaf4", "Leaf3"));	
			expected.add(new ComparablePair<String, String>("Leaf5", "Leaf2"));	
			expected.add(new ComparablePair<String, String>("Leaf6", "Leaf1"));
			assertEquals(expected.toString(), fileReader.getAssociationStrings().toString());
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}		
}
