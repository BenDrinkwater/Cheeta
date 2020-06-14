package io.nexus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import tanglegram.CoevolutionaryHistory;

public class NexusFileLoaderTest {

	@Test
	public void LoadJaneNexusFile() {
		NexusFileLoader nexusFileLoader = new NexusFileLoader("unitTestFiles" + File.separator + "io" +  File.separator + "nexus" +  File.separator + "jane" +  File.separator + "validTestFiles" +  File.separator + "TestCase1.nex");
		CoevolutionaryHistory history = nexusFileLoader.loadHistory();
		assertEquals(8, history.getHostTree().numberOfLeafNodes());
		assertEquals(11, history.getParasiteTree().numberOfLeafNodes());
	}
	
	@Test
	public void LoadCorePaNexusFile() {
		NexusFileLoader nexusFileLoader = new NexusFileLoader("unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "corepa" + File.separator + "validTestFiles" + File.separator + "TestCase1.nex");
		CoevolutionaryHistory history = nexusFileLoader.loadHistory();
		assertEquals(3, history.getHostTree().numberOfLeafNodes());
		assertEquals(3, history.getParasiteTree().numberOfLeafNodes());	
	}
	
	@Test
	public void LoadInvalidNexusFile() {
		NexusFileLoader nexusFileLoader = new NexusFileLoader("unitTestFiles" + File.separator + "io" + File.separator + "nexus"  + File.separator + "jane" + File.separator + "invalidTestFiles" + File.separator + "MissingHostTree.nex");
		CoevolutionaryHistory history = nexusFileLoader.loadHistory();
		assertNull(history);		
	}	
	
}
