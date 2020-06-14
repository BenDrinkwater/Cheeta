package io.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Map;

import org.junit.Test;

public class TreeFileReaderTest {

	private final String testFile1 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "validTestFiles" + File.separator + "WidespreadMultiParasiteTest.tree";
	private final String expectedHostString = " 1	 2	 3\n 2	 4	 5\n 3	 8	 9\n 4	 null\n 5	 6	 7\n 6	 null\n 7	 null\n 8	 10	 11\n 9	 14	 15\n 10	 null\n 11	 12	 13\n 12	 null\n 13	 null\n 14	 16	 17\n 15	 26	 27\n 16	 18	 19\n 17	 24	 25\n 18	 20	 21\n 19	 22	 23\n 20	 null\n 21	 null\n 22	 null\n 23	 null\n 24	 null\n 25	 null\n 26	 28	 29\n 27	 36	 37\n 28	 null\n 29	 30	 31\n 30	 null\n 31	 32	 33\n 32	 null\n 33	 34	 35\n 34	 null\n 35	 null\n 36	 null\n 37	 38	 39\n 38	 null\n 39	 null\n";
	private final String expectedParaisteString = " 40	 41	 42\n 41	 43	 44\n 42	 null\n 43	 45	 46\n 44	 47	 48\n 45	 null\n 46	 null\n 47	 51	 52\n 48	 49	 50\n 49	 null\n 50	 null\n 51	 53	 54\n 52	 55	 56\n 53	 null\n 54	 null\n 55	 57	 58\n 56	 59	 60\n 57	 null\n 58	 null\n 59	 null\n 60	 61	 62\n 61	 63	 64\n 62	 67	 68\n 63	 65	 66\n 64	 null\n 65	 null\n 66	 null\n 67	 null\n 68	 null\n";
	
	@Test
	public void CreateTreeFileReaderTest() {
		File file = new File(testFile1);
		TreeFileReader fileReader = new TreeFileReader(file);
		try {
			fileReader.parse();
			assertNotNull(fileReader.getHostString());
			assertNotNull(fileReader.getParasiteString());
			assertNotNull(fileReader.getAssociationStrings());
		}
		catch (TreeFileParsingException e) {
			
		}
	}
	
	@Test
	public void ValidateHostTreeTest() {
		File file = new File(testFile1);
		TreeFileReader fileReader = new TreeFileReader(file);
		try {
			fileReader.parse();
			assertEquals(this.expectedHostString, fileReader.getHostString());
		}
		catch (TreeFileParsingException e) {
			fail("Exception Thrown");			
		}
	}		
	
	@Test
	public void ValidateParasiteTreeTest() {
		File file = new File(testFile1);
		TreeFileReader fileReader = new TreeFileReader(file);
		try {
			fileReader.parse();
			assertEquals(this.expectedParaisteString, fileReader.getParasiteString());
		}
		catch (TreeFileParsingException e) {
			fail("Exception Thrown");			
		}
	}	
	
	@Test
	public void FileDoesNotExistTest() {
		File file = new File("No File with this name around here");
		TreeFileReader fileReader = new TreeFileReader(file);
		try {
			fileReader.parse();
		}
		catch (TreeFileParsingException e) {
			assertEquals("File Not Found", e.getMessage());			
		}
	}	
	
	@Test
	public void FileIsNullTest() {
		File file = null;
		TreeFileReader fileReader = new TreeFileReader(file);
		try {
			fileReader.parse();
		}
		catch (TreeFileParsingException e) {
			assertEquals("File is null", e.getMessage());			
		}
	}
	
	@Test
	public void HostMapValidationTest() {
		File file = new File(testFile1);
		TreeFileReader fileReader = new TreeFileReader(file);
		try {
			fileReader.parse();
			Map<String, String> hostNames = fileReader.getHostNames();
			assertNotNull(hostNames);
			assertEquals(39, hostNames.size());
		}
		catch (TreeFileParsingException e) {
			fail("Exception Thrown");			
		}
	}
	
	@Test
	public void ParasiteMapValidationTest() {
		File file = new File(testFile1);
		TreeFileReader fileReader = new TreeFileReader(file);
		try {
			fileReader.parse();
			Map<String, String> parasiteNames = fileReader.getParasiteNames();
			assertNotNull(parasiteNames);
			assertEquals(29, parasiteNames.size());
		}
		catch (TreeFileParsingException e) {
			fail("Exception Thrown");			
		}
	}
}
