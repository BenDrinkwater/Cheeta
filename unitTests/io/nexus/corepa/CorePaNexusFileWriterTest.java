package io.nexus.corepa;

import static org.junit.Assert.assertEquals;
import io.nexus.FileWriterTestCommon;
import io.nexus.NexusFileWriter;

import java.io.File;

import org.junit.Test;

public class CorePaNexusFileWriterTest extends FileWriterTestCommon {
	
	public CorePaNexusFileWriterTest() {
		super("CorePaExample.nex", "JaneExample.nex");
	}
	
	@Test
	public void TaxaStringTest() {
		CorePaNexusFileWriter nexusFileWriter = new CorePaNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals("BEGIN TAXA;\n\tDIMENSIONS NTAX = 6;\n\tTAXLABELS\n\t\tLeaf1\n\t\tLeaf2\n\t\tLeaf3\n\t\tLeaf4\n\t\tLeaf5\n\t\tLeaf6\n\t\t;\nENDBLOCK;\n\n", nexusFileWriter.getTaxaString());
	}
	
	@Test
	public void NodeMappingTest1() {
		CorePaNexusFileWriter nexusFileWriter = new CorePaNexusFileWriter(super.largeHistory, new File(super.janeFileName));
		assertEquals(71, nexusFileWriter.getMapping(super.largeHistory.getHostTree()).size());
		assertEquals(99, nexusFileWriter.getMapping(super.largeHistory.getParasiteTree()).size());
	}
	
	@Test
	public void NodeMappingTest2() {
		CorePaNexusFileWriter nexusFileWriter = new CorePaNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals(5, nexusFileWriter.getMapping(super.smallHistory.getHostTree()).size());
		assertEquals(5, nexusFileWriter.getMapping(super.smallHistory.getParasiteTree()).size());
	}
	
	@Test
	public void TreeStringTest() {
		CorePaNexusFileWriter nexusFileWriter = new CorePaNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals("BEGIN TREES;\n\tTRANSLATE\n\t\tH0\tInternalNode_00002,\n\t\tH1\tInternalNode_00001,\n\t\tH2\tLeaf1,\n\t\tH3\tLeaf2,\n\t\tH4\tLeaf3,\n\t\tP0\tInternalNode_00002,\n\t\tP1\tInternalNode_00001,\n\t\tP2\tLeaf4,\n\t\tP3\tLeaf5,\n\t\tP4\tLeaf6\n\t\t;\n\tTREE HOST = ((H2,H3)H1,H4)H0;\n\tTREE PARASITE = ((P2,P3)P1,P4)P0;\nENDBLOCK;\n\n", nexusFileWriter.getTreesString());		
	}
	
	@Test
	public void PhiStringTest() {
		CorePaNexusFileWriter nexusFileWriter = new CorePaNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals("BEGIN COPHYLOGENY;\n[RANKS represents the ranks of the nodes in the tree]\n[Syntax is: nodename timezone_from timezone_to]\n\tRANK\n\t\t;\n\n[PHI represents the associations from the parasite leaf nodes to the host leaf nodes]\n[Syntax is: parasite_leaf_name host_leaf_name]\n\tPHI\n\t\tLeaf6\tLeaf1,\n\t\tLeaf5\tLeaf2,\n\t\tLeaf4\tLeaf3\n;\n[COSTS represents the cost table for the operations COSPECIATION, DUPLICATION, EXTINCTION, SORTING and HOSTSWITCH]\n[Syntax is: operation cost]\n\tCOSTS\n\t\tCOSPECIATION\t0,\n\t\tDUPLICATION\t1,\n\t\tSORTING\t1,\n\t\tHOSTSWITCH\t2\n\t\t;\nENDBLOCK;\n\n", nexusFileWriter.getCophylogenyString());	
	}
	
	@Test
	public void WriteTest1() {
		CorePaNexusFileWriter nexusFileWriter = new CorePaNexusFileWriter(super.smallHistory, new File(super.corePaFileName));
		assertEquals(true, nexusFileWriter.write());
		String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "corepa" + File.separator + "expectedWriteFiles" + File.separator + "SmallNexusFile.nex");
		String actual = this.getStringAsWrittenToFile(this.corePaFileName);
		assertEquals(expected, actual);		
	}
	
	@Test
	public void WriteTest2() {
		NexusFileWriter nexusFileWriter = new CorePaNexusFileWriter(super.largeHistory, new File(super.janeFileName));
		assertEquals(true, nexusFileWriter.write());
		String expected = this.getStringAsWrittenToFile("unitTestFiles" + File.separator + "io" + File.separator + "nexus" + File.separator + "corepa" + File.separator + "expectedWriteFiles" + File.separator + "LargeNexusFile.nex");
		String actual = this.getStringAsWrittenToFile(this.janeFileName);
		assertEquals(expected, actual);		
	}	
}
