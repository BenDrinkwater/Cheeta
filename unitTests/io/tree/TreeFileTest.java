package io.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import association.Associations;

import tanglegram.CoevolutionaryHistory;

public class TreeFileTest {

	private final String testFile1 = "unitTestFiles" + File.separator + "io" + File.separator + "tree"  + File.separator + "validTestFiles" + File.separator + "WidespreadMultiParasiteTest.tree";
	
	@Test
	public void CreateTreeFileTest() {
		TreeFile file = new TreeFile(testFile1);
		assertNotNull(file);
		try {
			CoevolutionaryHistory history = file.read();
			assertNotNull(history);
		} 
		catch (TreeFileParsingException e) {
			fail("Exception Thrown");
		}
	}
	
	@Test
	public void ValidTreeConstructionTest() {
		TreeFile file = new TreeFile(testFile1);
		assertNotNull(file);
		try {
			CoevolutionaryHistory history = file.read();
			assertNotNull(history);
			
			assertNotNull(history.getHostTree());	
			assertEquals(20, history.getHostTree().numberOfLeafNodes());
			assertNotNull(history.getParasiteTree());
			assertEquals(15, history.getParasiteTree().numberOfLeafNodes());
		} 
		catch (TreeFileParsingException e) {
			fail("Exception Thrown");
		}
	}
	
	@Test
	public void ValidAssociationConstructionTest() {	
		TreeFile file = new TreeFile(testFile1);
		assertNotNull(file);
		try {
			CoevolutionaryHistory history = file.read();
			assertNotNull(history);
			
			assertNotNull(history.getAssociations());
			assertEquals(24, history.getAssociations().size());
			
			Associations associations = history.getAssociations();
			Associations nodeWithMostAssociations = associations.getAssociationsForParasiteNode(history.getParasiteTree().getNode("ParasiteLeafNode09"));
			assertEquals(4, nodeWithMostAssociations.size());
		} 
		catch (TreeFileParsingException e) {
			fail("Exception thrown " + e);
		}		
	}
}
