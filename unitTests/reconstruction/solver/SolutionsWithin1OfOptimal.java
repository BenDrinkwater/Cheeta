package reconstruction.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import tree.Node;

public class SolutionsWithin1OfOptimal {

	private CoevolutionaryHistory birdsLiceD;
	private final String birdsLiceDFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsWingLice2003.nex";			
		
	private CoevolutionaryHistory birdsLiceG;
	private final String birdsLiceGFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsLice2004c.nex";	
	
	private CoevolutionaryHistory chrysoviridae;
	private final String chrysoviridaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Chrysoviridae2011.nex";	
	
	private CoevolutionaryHistory crinoidMyzostomidB;
	private final String crinoidMyzostomidBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CrinoidMyzostomid2009b.nex";	
		
	private CoevolutionaryHistory estrildaeViduaAllAssociations;
	private final String estrildaeViduaAllAssociationsFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeViduaAllAssociations2004.nex";		
	
	private CoevolutionaryHistory hantavirus1999;
	private final String hantavirus1999FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantavirus1999.nex";	
	
	private CoevolutionaryHistory hantavirusesMurinae;
	private final String hantavirusesMurinaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusesMurinae2013.nex";		
	
	private CoevolutionaryHistory hantavirusesRodentia;
	private final String hantavirusesRodentiaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusesRodentia2013.nex";		
		
	private CoevolutionaryHistory mammalsMLV;
	private final String mamalsMLVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MamalsMLV2003.nex";
	
	private CoevolutionaryHistory pelicanLice;
	private final String pelicanLiceFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PelicanLice2006.nex";	
		
	private CoevolutionaryHistory pocketGophers;
	private final String pocketGophersFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2002.nex";	
	
	private CoevolutionaryHistory polyomavirus;
	private final String polyomavirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Polyomavirus2006.nex";
		
	private CoevolutionaryHistory retroelementsC;
	private final String retroelementsCFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Retroelements1999c.nex";	
	
	private CoevolutionaryHistory retroelementsE;
	private final String retroelementsEFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Retroelements1999e.nex";	

	private CoevolutionaryHistory rodentPinworms1;
	private final String rodentPinworms1FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "RodentPinwormsHostTree12003.nex";	
	
	private CoevolutionaryHistory rodentPintWorms3Expanded;
	private final String rodentPintWormsExpanded3FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "RodentPinwormsHostTree3Expanded2003.nex";

	private CoevolutionaryHistory uP1aUPII;
	private final String uP1aUPIIFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "UP1aUPII2006.nex";	
	
	@Before
	public void LoadBirdsLiceDExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceDFileName);
			this.birdsLiceD = file.read();
		
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00018"), 1);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00017"), 2);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00009"), 3);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00016"), 8);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00005"), 9);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00015"), 10);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00013"), 11);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00012"), 12);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00008"), 13);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00011"), 14);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00014"), 15);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00001"), 16);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00010"), 17);
			nodes.put(this.birdsLiceD.getHostTree().getNode("InternalNode_00006"), 18);
			this.birdsLiceD.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadBirdLiceFExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceGFileName);
			this.birdsLiceG = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00012"), 0);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00009"), 3);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00011"), 4);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00006"), 5);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00005"), 6);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00008"), 7);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00007"), 9);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00004"), 10);
			nodes.put(this.birdsLiceG.getHostTree().getNode("InternalNode_00001"), 11);
			this.birdsLiceG.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadChrysoviridaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.chrysoviridaeFileName);
			this.chrysoviridae = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.chrysoviridae.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.chrysoviridae.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.chrysoviridae.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.chrysoviridae.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.chrysoviridae.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.chrysoviridae.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.chrysoviridae.getHostTree().getNode("InternalNode_00002"), 6);
			this.chrysoviridae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadCrinoidMyzostomidBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.crinoidMyzostomidBFileName);
			this.crinoidMyzostomidB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00011"), 4);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00014"), 6);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00010"), 8);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00002"), 9);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00009"), 10);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00013"), 11);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00001"), 12);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00008"), 13);
			nodes.put(this.crinoidMyzostomidB.getHostTree().getNode("InternalNode_00007"), 14);
			this.crinoidMyzostomidB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadEstrildaeViduaAllAssociationsExample() {
		try {
			NexusFile file = new JaneNexusFile(this.estrildaeViduaAllAssociationsFileName);
			this.estrildaeViduaAllAssociations = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00032"), 0);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00031"), 1);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00023"), 2);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00022"), 3);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00021"), 4);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00015"), 5);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00014"), 6);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00030"), 7);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00010"), 8);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00020"), 9);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00029"), 10);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00009"), 11);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00028"), 12);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00002"), 13);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00013"), 14);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00027"), 15);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00019"), 16);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00008"), 17);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00011"), 18);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00001"), 19);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00012"), 20);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00004"), 21);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00026"), 22);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00024"), 23);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00016"), 24);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00018"), 25);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00005"), 26);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00007"), 27);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00017"), 28);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00006"), 29);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00003"), 30);
			nodes.put(this.estrildaeViduaAllAssociations.getHostTree().getNode("InternalNode_00025"), 31);
			this.estrildaeViduaAllAssociations.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void Loadhantavirus1999Example() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirus1999FileName);
			this.hantavirus1999 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00014"), 1);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00012"), 3);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00011"), 4);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00010"), 7);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00009"), 9);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00006"), 10);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00007"), 11);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00002"), 12);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00005"), 13);
			nodes.put(this.hantavirus1999.getHostTree().getNode("InternalNode_00004"), 14);
			this.hantavirus1999.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	
	@Before
	public void LoadHantavirusesMurinaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesMurinaeFileName);
			
			this.hantavirusesMurinae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00003"), 1);
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00005"), 6);
			nodes.put(this.hantavirusesMurinae.getHostTree().getNode("InternalNode_00004"), 7);
			this.hantavirusesMurinae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadHantavirusesRodentiaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesRodentiaFileName);
			
			this.hantavirusesRodentia = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00034"), 0);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00033"), 1);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00028"), 2);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00027"), 3);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00020"), 4);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00019"), 5);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00008"), 6);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00018"), 8);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00003"), 9);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00013"), 10);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00011"), 11);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00012"), 12);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00026"), 13);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00007"), 14);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00006"), 15);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00010"), 16);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00005"), 17);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00009"), 18);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00025"), 19);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00004"), 20);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00024"), 21);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00002"), 22);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00021"), 23);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00023"), 24);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00032"), 25);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00017"), 26);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00022"), 27);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00030"), 28);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00031"), 29);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00016"), 30);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00029"), 31);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00015"), 32);
			nodes.put(this.hantavirusesRodentia.getHostTree().getNode("InternalNode_00001"), 33);
			this.hantavirusesRodentia.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadMammalMLVExample() {
		try {
			NexusFile file = new JaneNexusFile(this.mamalsMLVFileName);
			this.mammalsMLV = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00016"), 0);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00015"), 1);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00014"), 2);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00013"), 3);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00012"), 4);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00011"), 8);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00002"), 9);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00008"), 10);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00007"), 11);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00010"), 12);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00009"), 13);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00006"), 14);
			nodes.put(this.mammalsMLV.getHostTree().getNode("InternalNode_00005"), 15);
			this.mammalsMLV.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPelicanLiceExample() {
		try {
			NexusFile file = new JaneNexusFile(this.pelicanLiceFileName);
			this.pelicanLice = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00017"), 0);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00015"), 1);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00016"), 2);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00014"), 3);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00013"), 4);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00012"), 5);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00011"), 6);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00010"), 7);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00008"), 9);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00006"), 11);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00004"), 13);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00003"), 14);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00002"), 15);
			nodes.put(this.pelicanLice.getHostTree().getNode("InternalNode_00001"), 16);
			this.pelicanLice.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPocketGophersExample() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGophersFileName);
			this.pocketGophers = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00014"), 0);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00013"), 1);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00012"), 2);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00009"), 3);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00008"), 4);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00006"), 6);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00011"), 7);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00007"), 8);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00005"), 9);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00004"), 10);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00010"), 11);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00003"), 12);
			nodes.put(this.pocketGophers.getHostTree().getNode("InternalNode_00002"), 13);
			this.pocketGophers.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPolyomavirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.polyomavirusFileName);
			this.polyomavirus = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.polyomavirus.getHostTree().getNode("InternalNode_00001"), 7);
			this.polyomavirus.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadRodentPinworms1Example() {
		try {
			NexusFile file = new JaneNexusFile(this.rodentPinworms1FileName);
			this.rodentPinworms1 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.rodentPinworms1.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.rodentPinworms1.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.rodentPinworms1.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.rodentPinworms1.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.rodentPinworms1.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.rodentPinworms1.getHostTree().getNode("InternalNode_00001"), 5);
			this.rodentPinworms1.getHostTree().setUniqueNodeTimings(nodes);				
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadRetroelementsCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.retroelementsCFileName);
			
			this.retroelementsC = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.retroelementsC.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.retroelementsC.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.retroelementsC.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.retroelementsC.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.retroelementsC.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.retroelementsC.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.retroelementsC.getHostTree().getNode("InternalNode_00004"), 6);
			this.retroelementsC.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadRetroelementsEExample() {
		try {
			NexusFile file = new JaneNexusFile(this.retroelementsEFileName);
			
			this.retroelementsE = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.retroelementsE.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.retroelementsE.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.retroelementsE.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.retroelementsE.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.retroelementsE.getHostTree().getNode("InternalNode_00002"), 4);
			this.retroelementsE.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadRodentPinwormsExpanded3Example() {
		try {
			NexusFile file = new JaneNexusFile(this.rodentPintWormsExpanded3FileName);
			this.rodentPintWorms3Expanded = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00012"), 0);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00011"), 1);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00006"), 7);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00001"), 8);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00009"), 9);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00004"), 10);
			nodes.put(this.rodentPintWorms3Expanded.getHostTree().getNode("InternalNode_00002"), 11);
			this.rodentPintWorms3Expanded.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	
	
	@Before
	public void LoadUP1aUPIIExample() {
		try {
			NexusFile file = new JaneNexusFile(this.uP1aUPIIFileName);
			this.uP1aUPII = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00005"), 6);
			nodes.put(this.uP1aUPII.getHostTree().getNode("InternalNode_00003"), 7);
			this.uP1aUPII.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Test
	public void BirdLiceDTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceD);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void BirdsLiceGTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceG);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void ChrysoviridaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.chrysoviridae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void CrinoidMyzostomidBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.crinoidMyzostomidB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void EstrildaeViduaAllAssociationsTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeViduaAllAssociations);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 13, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 20, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void Hantavirus1999Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirus1999);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void HantavirusesMurinaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesMurinae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void HantavirusesRodentiaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesRodentia);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 17, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 18, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void MammalsMLVTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.mammalsMLV);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 15, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void PelicanLiceTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pelicanLice);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void PocketGophersTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGophers);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void PolyomavirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.polyomavirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void RetroelementsCTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.retroelementsC);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());	
	}	
	
	@Test
	public void RetroelementsETest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.retroelementsE);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());	
	}		
	
	@Test
	public void RodentPinworms1Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.rodentPinworms1);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void RodentPintWorms3ExpandedTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.rodentPintWorms3Expanded);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// 1 away under Jane cost scheme 5 away under Jungles
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void UP1aUPIITest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.uP1aUPII);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
}
