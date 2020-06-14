package io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.FileWriter.FileType;
import io.nexus.NexusFile;
import io.nexus.NexusParsingException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tanglegram.CoevolutionaryHistory;

public class FileWriterTest {

	private CoevolutionaryHistory history;
	private final String janeFileName = "JaneWriterExampleTest.nex";
	private final String fileName = "CorePaWriterExampleTest.nex";
	
	@Before
	public void CleanupBefore() {
		cleanUp(this.janeFileName);
		cleanUp(this.fileName);
	}	
	
	@After
	public void CleanupAfter() {
		cleanUp(this.janeFileName);
		cleanUp(this.fileName);
	}
	
	private void cleanUp(String fileName) {
		File file = new File(fileName);
		
		if (file.exists()) {
			file.delete();
		}		
	}
	
	@Before
	public void CreateNexusFile() {
		try {
			NexusFile file 
				= new JaneNexusFile("unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "JC2006.nex");
			this.history = file.read();
		} 
		catch (NexusParsingException e) {
			fail("Unexpected Exception has occured");
		}
	}	
	
	@Test
	public void WriteCorePaNexusFile() {
		FileWriter fileWriter = new FileWriter(this.fileName, FileType.CorePa);
		fileWriter.save(this.history);
		String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "corepa" + File.separator + "expectedWriteFiles" + File.separator + "JC2006_CorePa.nex");
		String actual = this.getStringAsWrittenToFile(this.fileName);
		assertEquals(expected, actual);			
	}
	
	@Test
	public void WriteJaneNexusFile() {
		FileWriter fileWriter = new FileWriter(this.janeFileName, FileType.Jane);
		fileWriter.save(this.history);
		String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "jane" + File.separator + "expectedWriteFiles" + File.separator + "JC2006_Jane.nex");
		String actual = this.getStringAsWrittenToFile(this.janeFileName);
		assertEquals(expected, actual);			
	}
	
	@Test
	public void WriteUndefinedFile() {
		FileWriter fileWriter = new FileWriter(this.janeFileName, FileType.Undefined);
		assertEquals(false, fileWriter.save(this.history));
	}	
	
	@Test
	public void WriteNonExistentFileTest() {
		FileWriter fileWriter = new FileWriter(null, FileType.CorePa);
		assertEquals(false, fileWriter.save(this.history));
	}
	
	protected String getStringAsWrittenToFile(String fileName) {
		File file = new File(fileName);
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fr = new FileReader(file);
			Scanner scanner = new Scanner(fr);
			while ( scanner.hasNextLine() ){
				sb.append(scanner.nextLine() + "\r\n");		
			}
			fr.close();
		} catch (FileNotFoundException e) {
			fail("File Not Found Excpetion should not occur during this test");
			e.printStackTrace();
		} catch (IOException e) {
			fail("IOException Excpetion should not occur during this test");
			e.printStackTrace();
		}
		return sb.toString();
	}	
}
