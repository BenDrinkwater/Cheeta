package io.nexus.jane;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.nexus.NexusFormatException;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import util.ComparablePair;

public class JaneNexusFileReaderTest {

	@Test
	public void ParseTest1() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex");
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			assertEquals("((3,4),(5,(7,(9,(11,(13,14))))))", fileReader.getHostString());
			assertEquals("((18,19),((22,(24,25)),(26,((32,(34,35)),(30,31)))))", fileReader.getParasiteString());
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}	
	
	@Test
	public void ParseTest2() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase2.nex");
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			assertEquals("((3,4),(5,(7,(9,(11,(13,14))))))", fileReader.getHostString());
			assertEquals("((18,19),((22,(24,25)),(26,((32,(34,35)),(30,31)))))", fileReader.getParasiteString());
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}	
	
	@Test
	public void ParseTest3() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex");
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			assertEquals("31 : 14,30 : 13,35 : 13,34 : 11,32 : 9,26 : 7,25 : 5,24 : 4,19 : 4,22 : 3,18 : 3", fileReader.associationString);
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}	
	
	@Test
	public void ParseTest4() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase2.nex");
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			assertEquals("31 : 14,30 : 13,35 : 13,34 : 11,32 : 9,26 : 7,25 : 5,24 : 4,19 : 4,22 : 3,18 : 3", fileReader.associationString);
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}		
	
	@Test
	public void ParseTest5() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex");
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			List<ComparablePair<String, String>> expected = new LinkedList<ComparablePair<String, String>>();
			expected.add(new ComparablePair<String, String>("31", "14"));	
			expected.add(new ComparablePair<String, String>("30", "13"));	
			expected.add(new ComparablePair<String, String>("35", "13"));
			expected.add(new ComparablePair<String, String>("34", "11"));	
			expected.add(new ComparablePair<String, String>("32", "9"));	
			expected.add(new ComparablePair<String, String>("26", "7"));
			expected.add(new ComparablePair<String, String>("25", "5"));	
			expected.add(new ComparablePair<String, String>("24", "4"));	
			expected.add(new ComparablePair<String, String>("19", "4"));
			expected.add(new ComparablePair<String, String>("22", "3"));	
			expected.add(new ComparablePair<String, String>("18", "3"));
			assertEquals(expected.toString(), fileReader.getAssociationStrings().toString());
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}	
	
	@Test
	public void ParseTest6() {
		File file = new File("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase2.nex");
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			List<ComparablePair<String, String>> expected = new LinkedList<ComparablePair<String, String>>();
			expected.add(new ComparablePair<String, String>("31", "14"));	
			expected.add(new ComparablePair<String, String>("30", "13"));	
			expected.add(new ComparablePair<String, String>("35", "13"));
			expected.add(new ComparablePair<String, String>("34", "11"));	
			expected.add(new ComparablePair<String, String>("32", "9"));	
			expected.add(new ComparablePair<String, String>("26", "7"));
			expected.add(new ComparablePair<String, String>("25", "5"));	
			expected.add(new ComparablePair<String, String>("24", "4"));	
			expected.add(new ComparablePair<String, String>("19", "4"));
			expected.add(new ComparablePair<String, String>("22", "3"));	
			expected.add(new ComparablePair<String, String>("18", "3"));
			assertEquals(expected.toString(), fileReader.getAssociationStrings().toString());
		} 
		catch (NexusFormatException e) {
			fail("Unexpected Exception Thrown");
		}
	}		
	
	@Test
	public void ParseTest7() {
		File file = null;
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			fail("Unexpected Exception Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("FileReader not initialised correctly", e.getMessage());
		}
	}	
	
	@Test
	public void ParseTest8() {
		File file = new File("FileIsNotHere");
		JaneNexusFileReader fileReader = new JaneNexusFileReader(file);
		try {
			fileReader.parse();
			fail("Unexpected Exception Thrown");
		} 
		catch (NexusFormatException e) {
			assertEquals("FileReader not initialised correctly", e.getMessage());
		}
	}		
}
