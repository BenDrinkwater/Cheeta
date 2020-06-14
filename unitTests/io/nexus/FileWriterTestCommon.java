package io.nexus;

import static org.junit.Assert.fail;

import io.nexus.corepa.CorePaNexusFile;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;

import tanglegram.CoevolutionaryHistory;

public class FileWriterTestCommon {

	protected CoevolutionaryHistory smallHistory;
	protected CoevolutionaryHistory largeHistory;
	protected final String corePaFileName;
	protected final String janeFileName;
	
	public FileWriterTestCommon(String corePaFileName, String janeFileName) {
		this.corePaFileName = corePaFileName;
		this.janeFileName = janeFileName;
	}
	
	@Before
	public void CleanupBefore() {
		cleanUp();	
	}	
	
	@After
	public void CleanupAfter() {
		cleanUp();	
	}
	
	private void cleanUp() {
		File file1 = new File(this.corePaFileName);
		
		if (file1.exists()) {
			file1.delete();
		}	
		
		File file2 = new File(this.janeFileName);
		
		if (file2.exists()) {
			file2.delete();
		}		
	}
	
	@Before
	public void CreateSmallNexusFile() {
		try {
			NexusFile file 
				= new CorePaNexusFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "corepa" + File.separator + "validTestFiles" + File.separator + "TestCase1.nex");
			this.smallHistory = file.read();
		} 
		catch (NexusParsingException e) {
			fail("Unexpected Exception has occured");
		}
	}
	
	@Before
	public void CreateLargeNexusFile() {
		try {
			NexusFile file 
				= new JaneNexusFile("unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Partitiviridae2011.nex");
			this.largeHistory = file.read();
		} 
		catch (NexusParsingException e) {
			fail("Unexpected Exception has occured");
		}
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
