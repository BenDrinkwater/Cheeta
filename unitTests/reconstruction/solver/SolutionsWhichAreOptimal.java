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

public class SolutionsWhichAreOptimal {
	
	private CoevolutionaryHistory actinobacillusActinomycetemcomitansE;
	private final String actinobacillusActinomycetemcomitansEFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ActinobacillusActinomycetemcomitans2003e.nex";
	
	private CoevolutionaryHistory alphaHerpesVirus;
	private final String alphaHerpesVirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "AlphaHerpesvirusVCV2005.nex";

	private CoevolutionaryHistory anolisPlasmodiumAzurophilumRed;
	private final String anolisPlasmodiumAzurophilumRedFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "AnolisPlasmodiumAzurophilum(Red)2003.nex";
	
	private CoevolutionaryHistory anolisPlasmodiumAzurophilumWhite;
	private final String anolisPlasmodiumAzurophilumWhiteFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "AnolisPlasmodiumAzurophilum(White)2003.nex";
	
	private CoevolutionaryHistory arenavirus;
	private final String arenavirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Arenavirus2003.nex";		
	
	private CoevolutionaryHistory arenavirusOldWorld;
	private final String arenavirusOldWorldFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ArenavirusOldWorld2011.nex";		
	
	private CoevolutionaryHistory arvicolinaeClade;
	private final String arvicolinaeCladeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ArvicolinaeClade2010.nex";		
	
	private CoevolutionaryHistory aslv;
	private final String aslvFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ASLV2003.nex";			
	
	private CoevolutionaryHistory atadenovirus;
	private final String atadenovirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Atadenovirus2005.nex";	
	
	private CoevolutionaryHistory avianMLV;
	private final String avianMLVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "AvianMLV2003.nex";
	
	private CoevolutionaryHistory aVRTE1;
	private final String aVRTE1FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "AVRTE12009.nex";	
		
	private CoevolutionaryHistory batsGammaViruses;
	private final String batsGammaVirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BatsGammaretroviruses2012.nex";			
		
	private CoevolutionaryHistory beesNosema;
	private final String beesNosemaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BeesNosema2009.nex";	
		
	private CoevolutionaryHistory bemisiaTabaciEndosymbiontsA;
	private final String bemisiaTabaciEndosymbiontsAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BemisiaTabaciEndosymbionts2013a.nex";	
	
	private CoevolutionaryHistory bemisiaTabaciEndosymbiontsB;
	private final String bemisiaTabaciEndosymbiontsBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BemisiaTabaciEndosymbionts2013b.nex";	
	
	private CoevolutionaryHistory bemisiaTabaciEndosymbiontsC;
	private final String bemisiaTabaciEndosymbiontsCFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BemisiaTabaciEndosymbionts2013c.nex";	
	
	private CoevolutionaryHistory bemisiaTabaciEndosymbiontsD;
	private final String bemisiaTabaciEndosymbiontsDFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BemisiaTabaciEndosymbionts2013d.nex";	
	
	private CoevolutionaryHistory bemisiaTabaciEndosymbiontsE;
	private final String bemisiaTabaciEndosymbiontsEFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BemisiaTabaciEndosymbionts2013e.nex";	
	
	private CoevolutionaryHistory betaHerpesVirus;
	private final String betaHerpesVirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BetaHerpesvirusCMV2005.nex";
	
	private CoevolutionaryHistory birdsLiceA;
	private final String birdsLiceAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsBodyLice2003.nex";			
	
	private CoevolutionaryHistory birdsLiceC;
	private final String birdsLiceCFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsLouse2003b.nex";

	private CoevolutionaryHistory birdsLiceE;
	private final String birdsLiceEFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsLice2004a.nex";	
	
	private CoevolutionaryHistory birdsLiceH;
	private final String birdsLiceHFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "BirdsLice2004d.nex";	
		
	private CoevolutionaryHistory caliciviruses;
	private final String calicivirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Caliciviruses2006.nex";	
	
	private CoevolutionaryHistory circovirus;
	private final String circovirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Circovirus2010.nex";	
		
	private CoevolutionaryHistory compositeClade;
	private final String compositeCladeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CompositeClade2010.nex";	
		
	private CoevolutionaryHistory coronavirusesA;
	private final String coronavirusesAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CoronavirusesA2007.nex";	
	
	private CoevolutionaryHistory coronavirusesB;
	private final String coronavirusesBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CoronavirusesB2007.nex";	
	
	private CoevolutionaryHistory coronavirusesC;
	private final String coronavirusesCFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CoronavirusesC2007.nex";	
		
	private CoevolutionaryHistory crinoidMyzostomidA;
	private final String crinoidMyzostomidAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "CrinoidMyzostomid2009a.nex";	
	
	private CoevolutionaryHistory estrildaeViduaEstrildaCoccopygia;
	private final String estrildaeViduaEstrildaCoccopygiaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeViduaEstrildaCoccopygia2004.nex";	
	
	private CoevolutionaryHistory estrildaeViduaLagonosticta;
	private final String estrildaeViduaLagonostictaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeViduaLagonosticta2004.nex";	
		
	private CoevolutionaryHistory estrildaeViduaLagonostictaAllAssociations;
	private final String estrildaeViduaLagonostictaAllAssociationsFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeViduaLagonostictaAllAssociations2004.nex";	
		
	private CoevolutionaryHistory estrildaeViduaPytilia;
	private final String estrildaeViduaPytiliaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeViduaPytilia2004.nex";			
	
	private CoevolutionaryHistory estrildaeViduaPytiliaAllAssociations;
	private final String estrildaeViduaPytiliaAllAssociationsFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "EstrildaeViduaPytiliaAllAssociations2004.nex";	
		
	private CoevolutionaryHistory figWaspA;
	private final String figWaspAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004a.nex";

	private CoevolutionaryHistory figWaspB;
	private final String figWaspBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004b.nex";			
	
	private CoevolutionaryHistory figWaspC;
	private final String figWaspCFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004c.nex";			
	
	private CoevolutionaryHistory figWaspD;
	private final String figwaspDFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004d.nex";	
	
	private CoevolutionaryHistory figWaspF;
	private final String figWaspFFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2012.nex";
	
	private CoevolutionaryHistory flamingoLice;
	private final String flamingoLiceFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "FlamingoLice2006.nex";
	
	private CoevolutionaryHistory frogsPolystomes;
	private final String frogsPolystomesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "FrogsPolystomes2011.nex";
		
	private CoevolutionaryHistory gammaHerpesvirusLCV;
	private final String gammaHerpesvirusLCVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "GammaHerpesvirusLCV2005.nex";	
	
	private CoevolutionaryHistory gammaHerpesVirusRV;
	private final String gammaHerpesvirusRVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "GammaHerpesvirusRV2005.nex";	
	
	private CoevolutionaryHistory hantavirus2003;
	private final String hantavirus2003FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantavirus2003.nex";	
		
	private CoevolutionaryHistory hantivirus2009a;
	private final String hantivirus2009aFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantavirus2009a.nex";	

	private CoevolutionaryHistory hantivirus2009b;
	private final String hantivirus2009bFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantavirus2009b.nex";	
	
	private CoevolutionaryHistory hantavirus2012;
	private final String hantavirus2012FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantavirus2012.nex";
	
	private CoevolutionaryHistory hantavirusesArvicolinae;
	private final String hantavirusesArvicolinaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusesArvicolinae2013.nex";		
	
	private CoevolutionaryHistory hantavirusesNeotominae;
	private final String hantavirusesNeotominaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusesNeotominae2013.nex";		
	
	private CoevolutionaryHistory hantavirusesSigmodontinae;
	private final String hantavirusesSigmodontinaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusesSigmodontinae2013.nex";		
		
	private CoevolutionaryHistory hantivirusLaa;
	private final String hantivirusLaaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusLaa2011a.nex";	

	private CoevolutionaryHistory hantivirusMaa;
	private final String hantivirusMaaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusMaa2011a.nex";	
	
	private CoevolutionaryHistory hantivirusSaa;
	private final String hantivirusSaaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusSaa2011a.nex";	
	
	private CoevolutionaryHistory hantavirusesLaaB;
	private final String hantavirusesLaaBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusLaa2011b.nex";		
	
	private CoevolutionaryHistory hantavirusesMaaB;
	private final String hantavirusesMaaBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusMaa2011b.nex";		
	
	private CoevolutionaryHistory hantavirusesSaaB;
	private final String hantavirusesSaaBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "HantavirusSaa2011b.nex";		
		
	private CoevolutionaryHistory lyssavirus;
	private final String lyssavirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Lyssavirus2003.nex";	
	
	private CoevolutionaryHistory mamalsMLV2001;
	private final String mamalsMLV2001FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MamalsMLV2001.nex";	

	private CoevolutionaryHistory MamalsMLVFurtherReduced2001;
	private final String MamalsMLVFurtherReduced2001FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MamalsMLVFurtherReduced2001.nex";	
	
	private CoevolutionaryHistory mammalMLVFurtherReduced;
	private final String mammalMLVFurtherReducedFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MamalsMLVFurtherReduced2003.nex";	
	
	private CoevolutionaryHistory mammalsMLVReduced;
	private final String mammalsMLVReducedFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MamalsMLVReduced2003.nex";	
		
	private CoevolutionaryHistory millipeadsMites6Example;
	private final String millipeadsMites6ExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MillipedesMitesTM6_16S2010.nex";			
	
	private CoevolutionaryHistory millipeadsMites7AExample;
	private final String millipeadsMites7AExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MillipedesMitesTM7A_16S2010.nex";
	
	private CoevolutionaryHistory millipeadsMites7BExample;
	private final String millipeadsMites7BExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MillipedesMitesTM7B_16S2010.nex";
	
	private CoevolutionaryHistory millipeadsMites7CExample;
	private final String millipeadsMites7CExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MillipedesMitesTM7C_16S2010.nex";
	
	private CoevolutionaryHistory millipeadsMites7DExample;
	private final String millipeadsMites7DExampleFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MillipedesMitesTM7D_16S2010.nex";	
	
	private CoevolutionaryHistory mimeticHeliconiusButterflies;
	private final String mimeticHeliconiusButterfliesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MimeticHeliconiusButterflies2012.nex";
	
	private CoevolutionaryHistory murinaeClade;
	private final String murinaeCladeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MurinaeClade2010.nex";	
	
	private CoevolutionaryHistory myrmarachneMimic;
	private final String myrmarachneMimicFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "MyrmarachneMimic2006.nex";		
	
	private CoevolutionaryHistory narnavirdae;
	private final String narnavirdaeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Narnavirdae2011.nex";
		
	private CoevolutionaryHistory nematodesBacteria;
	private final String nematodesBacteriaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "NematodesBacteria2011.nex";		
	
	private CoevolutionaryHistory papillomavirus;
	private final String papillomavirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Papillomavirus2005.nex";				
	
	private CoevolutionaryHistory pocketGopherLouseA;
	private final String pocketGopherLouseFileNameA = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";	
	
	private CoevolutionaryHistory pocketGopherLouseB;
	private final String pocketGopherLouseFileNameB = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003B.nex";	
	
	private CoevolutionaryHistory pocketGopherLouseC;
	private final String pocketGopherLouseFileNameC = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003C.nex";	
	
	private CoevolutionaryHistory pocketGopherLouseD;
	private final String pocketGopherLouseFileNameD = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003D.nex";
	
	private CoevolutionaryHistory pocketGopherLouseE;
	private final String pocketGopherLouseFileNameE = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003E.nex";		
	
	private CoevolutionaryHistory pocketGopherLouseLikelihood;
	private final String pocketGopherLouseFileNameLikelihood = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLiceLikelihood2003.nex";
	
	private CoevolutionaryHistory pocketGopherLouseParafitReducedSet;
	private final String pocketGopherLouseFileNameParafitReducedSet = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLiceReduced2002.nex";	
		
	private CoevolutionaryHistory primateLentiVirus;
	private final String primateLentiVirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimateLentivirus2001.nex";	
	
	private CoevolutionaryHistory primatesMalaria;
	private final String primatesMalariaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimateMalaria2005.nex";			

	private CoevolutionaryHistory primatesOxyuroids;
	private final String primatesOxyuroidsFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimatesOxyuroids2003.nex";	
		
	private CoevolutionaryHistory primatePinworms;
	private final String primatePinwormsFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimatePinworms1998.nex";		
	
	private CoevolutionaryHistory primatesPinwormsCatarhini;
	private final String primatesPinwormsCatarhiniFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimatePinworms1999Catarhini.nex";	
		
	private CoevolutionaryHistory primatesPinwormsPlatyrrhini;
	private final String primatesPinwormsPlatyrrhiniFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimatePinworms1999Platyrrhini.nex";			
	
	private CoevolutionaryHistory primatesPinwormsStrepsirhini;
	private final String primatesPinwormsStrepsirhiniFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimatePinworms1999Strepsirhini.nex";			
	
	private CoevolutionaryHistory primatesPinwormsLarge;
	private final String primatesPinwormsLargeFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimatePinworms1999.nex";
	
	private CoevolutionaryHistory pytiliaVidua;
	private final String pytiliaViduaFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PytiliaVidua1994.nex";		
	
	private CoevolutionaryHistory reptilesMLV;
	private final String reptilesMLVFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ReptilesMLV2003.nex";			
	
	private CoevolutionaryHistory retroelementsA;
	private final String retroelementsAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Retroelements1999a.nex";	

	private CoevolutionaryHistory retroelementsB;
	private final String retroelementsBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Retroelements1999b.nex";		
	
	private CoevolutionaryHistory retroelementsD;
	private final String retroelementsDFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Retroelements1999d.nex";	
	
	private CoevolutionaryHistory retroelementsF;
	private final String retroelementsFFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Retroelements1999f.nex";
	
	private CoevolutionaryHistory rodentLouse;
	private final String rodentLouseFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "RodentLouse2008.nex";	
		
	private CoevolutionaryHistory rodentPinworms2;
	private final String rodentPinworms2FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "RodentPinwormsHostTree22003.nex";	
	
	private CoevolutionaryHistory rodentPinworms3;
	private final String rodentPinworms3FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "RodentPinwormsHostTree32003.nex";	
	
	private CoevolutionaryHistory rodentPinWorms2Expanded;
	private final String rodentPinWormsExpanded2FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "RodentPinwormsHostTree2Expanded2003.nex";				
	
	private CoevolutionaryHistory rugosusWaterhousei;
	private final String rugosusWaterhouseiFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "RugosusWaterhousei2007.nex";				
		
	private CoevolutionaryHistory seabirdLouseA;
	private final String seabirdLouseAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Seabird2001a.nex";				
		
	private CoevolutionaryHistory seabirdLouseB;
	private final String seabirdLouseBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Seabird2001b.nex";		
	
	private CoevolutionaryHistory largeSeabirdLouseA;
	private final String largeSeabirdLouseAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "SeabirdLouse1999a.nex";	
	
	private CoevolutionaryHistory largeSeabirdLouseB;
	private final String largeSeabirdLouseBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "SeabirdLouse2004.nex";	
	
	private CoevolutionaryHistory sigmodontinaeClade;
	private final String sigmodontinaeCladeFileName	= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "SigmodontinaeClade2010.nex";	
	
	private CoevolutionaryHistory spumaVirus;
	private final String spumavirusFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Spumavirus2003.nex";	
	
	private CoevolutionaryHistory tephritinaeSymbioticBacteriaA;
	private final String tephritinaeSymbioticBacteriaAFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "TephritinaeSymbioticBacteria2010a.nex";
	
	private CoevolutionaryHistory tephritinaeSymbioticBacteriaB;
	private final String tephritinaeSymbioticBacteriaBFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "TephritinaeSymbioticBacteria2010b.nex";
		
	private CoevolutionaryHistory termiteProtist;
	private final String termiteProtistFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "TermiteProtist2007.nex";		
		
	private CoevolutionaryHistory toucansChewingLice;
	private final String toucansChewingLiceFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "ToucansChewingLice2004.nex";
		
	private CoevolutionaryHistory tribevKemerovoViruses;
	private final String tribevKemerovoVirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "TribevKemerovoViruses2011.nex";	
		
	private CoevolutionaryHistory uP1aUPIII;
	private final String uP1aUPIIIFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "UP1aUPIII2006.nex";	
	
	private CoevolutionaryHistory uP1bUPII;
	private final String uP1bUPIIFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "UP1bUPII2006.nex";	
	
	private CoevolutionaryHistory uP1bUPIII;
	private final String uP1bUPIII2006FileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "UP1bUPIII2006.nex";	

	@Before
	public void LoadActinobacillusActinomycetemcomitansEExample() {
		try {
			NexusFile file = new JaneNexusFile(this.actinobacillusActinomycetemcomitansEFileName);
			this.actinobacillusActinomycetemcomitansE = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00012"), 3);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00011"), 5);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00006"), 6);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00009"), 7);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00018"), 9);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00016"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00015"), 11);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00014"), 13);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00002"), 14);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00004"), 15);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00010"), 16);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00001"), 17);
			nodes.put(this.actinobacillusActinomycetemcomitansE.getHostTree().getNode("InternalNode_00003"), 18);
			this.actinobacillusActinomycetemcomitansE.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadAlphaHerpesVirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.alphaHerpesVirusFileName);
			this.alphaHerpesVirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00013"), 0);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00009"), 1);			
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00012"), 2);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00011"), 3);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00010"), 4);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00006"), 5);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00008"), 6);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00007"), 7); 
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00004"), 9);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00002"), 10);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00001"), 11);
			nodes.put(this.alphaHerpesVirus.getHostTree().getNode("InternalNode_00003"), 12);
			this.alphaHerpesVirus.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadAnolisPlasmodiumAzurophilumRedExample() {
		try {
			NexusFile file = new JaneNexusFile(this.anolisPlasmodiumAzurophilumRedFileName);
			this.anolisPlasmodiumAzurophilumRed = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00006"), 5);
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.anolisPlasmodiumAzurophilumRed.getHostTree().getNode("InternalNode_00003"), 7);
			this.anolisPlasmodiumAzurophilumRed.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadAnolisPlasmodiumAzurophilumWhiteExample() {
		try {
			NexusFile file = new JaneNexusFile(this.anolisPlasmodiumAzurophilumWhiteFileName);
			this.anolisPlasmodiumAzurophilumWhite = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.anolisPlasmodiumAzurophilumWhite.getHostTree().getNode("InternalNode_00006"), 0); 
			nodes.put(this.anolisPlasmodiumAzurophilumWhite.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.anolisPlasmodiumAzurophilumWhite.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.anolisPlasmodiumAzurophilumWhite.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.anolisPlasmodiumAzurophilumWhite.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.anolisPlasmodiumAzurophilumWhite.getHostTree().getNode("InternalNode_00002"), 5);
			this.anolisPlasmodiumAzurophilumWhite.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadArenaVirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.arenavirusFileName);
			this.arenavirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00007"), 8);
			nodes.put(this.arenavirus.getHostTree().getNode("InternalNode_00001"), 9);
			this.arenavirus.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadArenaVirusOldWorldExample() {
		try {
			NexusFile file = new JaneNexusFile(this.arenavirusOldWorldFileName);
			this.arenavirusOldWorld = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.arenavirusOldWorld.getHostTree().getNode("InternalNode_00001"), 7);
			this.arenavirusOldWorld.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadArvicolinaeCladeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.arvicolinaeCladeFileName);
			this.arvicolinaeClade = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.arvicolinaeClade.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.arvicolinaeClade.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.arvicolinaeClade.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.arvicolinaeClade.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.arvicolinaeClade.getHostTree().getNode("InternalNode_00002"), 4);
			this.arvicolinaeClade.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadASLVExample() {
		try {
			NexusFile file = new JaneNexusFile(this.aslvFileName);
			this.aslv = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00012"), 0);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00011"), 1);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00009"), 7);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00001"), 9);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00003"), 10);
			nodes.put(this.aslv.getHostTree().getNode("InternalNode_00007"), 11);
			this.aslv.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadAtadenovirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.atadenovirusFileName);
			this.atadenovirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.atadenovirus.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.atadenovirus.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.atadenovirus.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.atadenovirus.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.atadenovirus.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.atadenovirus.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.atadenovirus.getHostTree().getNode("InternalNode_00001"), 6);
			this.atadenovirus.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadAvianMLVExample() {
		try {
			NexusFile file = new JaneNexusFile(this.avianMLVFileName);
			this.avianMLV = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00013"), 0);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00012"), 2);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00009"), 3);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00011"), 4);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00001"), 9);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00006"), 11);
			nodes.put(this.avianMLV.getHostTree().getNode("InternalNode_00005"), 12);
			this.avianMLV.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadAVRTE1Example() {
		try {
			NexusFile file = new JaneNexusFile(this.aVRTE1FileName);
			this.aVRTE1 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00028"), 0);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00027"), 1);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00025"), 2);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00019"), 3);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00013"), 6);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00005"), 7);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00012"), 8);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00018"), 9);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00001"), 10);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00017"), 11);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00024"), 12);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00023"), 13);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00011"), 14);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00022"), 15);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00020"), 16);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00008"), 17);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00021"), 18);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00006"), 19);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00016"), 20);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00003"), 21);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00010"), 22);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00002"), 23);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00009"), 24);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00015"), 25);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00007"), 26);
			nodes.put(this.aVRTE1.getHostTree().getNode("InternalNode_00026"), 27);
			this.aVRTE1.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadBatsGammaVirusesExample() {
		try {
			NexusFile file = new JaneNexusFile(this.batsGammaVirusesFileName);
			this.batsGammaViruses = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.batsGammaViruses.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.batsGammaViruses.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.batsGammaViruses.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.batsGammaViruses.getHostTree().getNode("InternalNode_00003"), 3);			
			nodes.put(this.batsGammaViruses.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.batsGammaViruses.getHostTree().getNode("InternalNode_00001"), 5);
			this.batsGammaViruses.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadBeesNosemaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.beesNosemaFileName);
			this.beesNosema = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.beesNosema.getHostTree().getNode("InternalNode_00003"), 0);
			nodes.put(this.beesNosema.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.beesNosema.getHostTree().getNode("InternalNode_00001"), 2);
			this.beesNosema.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadBemisiaTabaciEndosymbiontsAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.bemisiaTabaciEndosymbiontsAFileName);
			this.bemisiaTabaciEndosymbiontsA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00014"), 1);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00011"), 5);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00010"), 6);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00012"), 7);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00004"), 8);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00009"), 9);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00002"), 10);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00007"), 11);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00001"), 13);
			nodes.put(this.bemisiaTabaciEndosymbiontsA.getHostTree().getNode("InternalNode_00003"), 14);
			this.bemisiaTabaciEndosymbiontsA.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadBemisiaTabaciEndosymbiontsBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.bemisiaTabaciEndosymbiontsBFileName);
			this.bemisiaTabaciEndosymbiontsB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00009"), 5);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00008"), 7);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00007"), 8);
			nodes.put(this.bemisiaTabaciEndosymbiontsB.getHostTree().getNode("InternalNode_00006"), 9);
			this.bemisiaTabaciEndosymbiontsB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadBemisiaTabaciEndosymbiontsCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.bemisiaTabaciEndosymbiontsCFileName);
			this.bemisiaTabaciEndosymbiontsC = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00010"), 6);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00013"), 7);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00011"), 9);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00002"), 10);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00008"), 11);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00007"), 12);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00012"), 13);
			nodes.put(this.bemisiaTabaciEndosymbiontsC.getHostTree().getNode("InternalNode_00001"), 14);
			this.bemisiaTabaciEndosymbiontsC.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadBemisiaTabaciEndosymbiontsDExample() {
		try {
			NexusFile file = new JaneNexusFile(this.bemisiaTabaciEndosymbiontsDFileName);
			this.bemisiaTabaciEndosymbiontsD = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00005"), 6);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.bemisiaTabaciEndosymbiontsD.getHostTree().getNode("InternalNode_00004"), 8);
			this.bemisiaTabaciEndosymbiontsD.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadBemisiaTabaciEndosymbiontsEExample() {
		try {
			NexusFile file = new JaneNexusFile(this.bemisiaTabaciEndosymbiontsEFileName);
			this.bemisiaTabaciEndosymbiontsE = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.bemisiaTabaciEndosymbiontsE.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.bemisiaTabaciEndosymbiontsE.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.bemisiaTabaciEndosymbiontsE.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.bemisiaTabaciEndosymbiontsE.getHostTree().getNode("InternalNode_00001"), 3);
			this.bemisiaTabaciEndosymbiontsE.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadBetaHerpesVirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.betaHerpesVirusFileName);
			this.betaHerpesVirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00009"), 1);			
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00003"), 7); 
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.betaHerpesVirus.getHostTree().getNode("InternalNode_00001"), 9);
			this.betaHerpesVirus.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadBirdsLiceAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceAFileName);
			this.birdsLiceA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00018"), 1);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00017"), 2);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00016"), 3);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00009"), 4);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00013"), 5);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00012"), 8);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00004"), 9);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00003"), 10);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00011"), 11);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00015"), 12);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00001"), 13);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00014"), 14);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00005"), 15);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00006"), 16);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00010"), 17);
			nodes.put(this.birdsLiceA.getHostTree().getNode("InternalNode_00008"), 18);
			this.birdsLiceA.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadBirdLiceCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceCFileName);
			this.birdsLiceC = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00024"), 0);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00019"), 1);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00018"), 2);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00023"), 3);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00016"), 4);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00022"), 5);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00015"), 6);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00013"), 8);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00012"), 9);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00010"), 10);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00001"), 11);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00009"), 12);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00011"), 13);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00017"), 14);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00008"), 15);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00021"), 16);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00020"), 17);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00007"), 18);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00005"), 19);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00006"), 20);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00004"), 21);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00003"), 22);
			nodes.put(this.birdsLiceC.getHostTree().getNode("InternalNode_00002"), 23);
			this.birdsLiceC.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadBirdLiceEExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceEFileName);
			this.birdsLiceE = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00009"), 3);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00008"), 5);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00006"), 7);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00007"), 8);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00001"), 9);
			nodes.put(this.birdsLiceE.getHostTree().getNode("InternalNode_00004"), 10);
			this.birdsLiceE.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadBirdLiceHExample() {
		try {
			NexusFile file = new JaneNexusFile(this.birdsLiceHFileName);
			this.birdsLiceH = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.birdsLiceH.getHostTree().getNode("InternalNode_00002"), 7);
			this.birdsLiceH.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}

	@Before
	public void LoadCalicivirusesExample() {
		try {
			NexusFile file = new JaneNexusFile(this.calicivirusesFileName);
			this.caliciviruses = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.caliciviruses.getHostTree().getNode("InternalNode_00003"), 5);
			this.caliciviruses.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadCircoVirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.circovirusFileName);
			this.circovirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00006"), 7);
			nodes.put(this.circovirus.getHostTree().getNode("InternalNode_00005"), 8);
			this.circovirus.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}
	}
	
	@Before
	public void LoadCompositeCladeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.compositeCladeFileName);
			
			this.compositeClade = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.compositeClade.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.compositeClade.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.compositeClade.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.compositeClade.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.compositeClade.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.compositeClade.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.compositeClade.getHostTree().getNode("InternalNode_00001"), 6);
			this.compositeClade.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadCoronavirusesAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.coronavirusesAFileName);
			
			this.coronavirusesA = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00012"), 0);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00011"), 1);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00009"), 6);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00007"), 9);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00006"), 10);
			nodes.put(this.coronavirusesA.getHostTree().getNode("InternalNode_00001"), 11);
			this.coronavirusesA.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadCoronavirusesBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.coronavirusesBFileName);
			
			this.coronavirusesB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.coronavirusesB.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.coronavirusesB.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.coronavirusesB.getHostTree().getNode("InternalNode_00001"), 2);
			nodes.put(this.coronavirusesB.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.coronavirusesB.getHostTree().getNode("InternalNode_00003"), 4);
			this.coronavirusesB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadCoronavirusesCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.coronavirusesCFileName);
			
			this.coronavirusesC = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.coronavirusesC.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.coronavirusesC.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.coronavirusesC.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.coronavirusesC.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.coronavirusesC.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.coronavirusesC.getHostTree().getNode("InternalNode_00003"), 5);
			this.coronavirusesC.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadCrinoidMyzostomidAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.crinoidMyzostomidAFileName);
			this.crinoidMyzostomidA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00011"), 3);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00010"), 9);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00001"), 10);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00007"), 11);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00009"), 12);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00008"), 13);
			nodes.put(this.crinoidMyzostomidA.getHostTree().getNode("InternalNode_00013"), 14);
			this.crinoidMyzostomidA.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadEstrildaeViduaEstrildaCoccopygiaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.estrildaeViduaEstrildaCoccopygiaFileName);
			this.estrildaeViduaEstrildaCoccopygia = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.estrildaeViduaEstrildaCoccopygia.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.estrildaeViduaEstrildaCoccopygia.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.estrildaeViduaEstrildaCoccopygia.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.estrildaeViduaEstrildaCoccopygia.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.estrildaeViduaEstrildaCoccopygia.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.estrildaeViduaEstrildaCoccopygia.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.estrildaeViduaEstrildaCoccopygia.getHostTree().getNode("InternalNode_00002"), 6);
			this.estrildaeViduaEstrildaCoccopygia.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadEstrildaeViduaLagonostictaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.estrildaeViduaLagonostictaFileName);
			this.estrildaeViduaLagonosticta = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00014"), 0);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00013"), 1);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00012"), 2);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00011"), 5);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00006"), 7);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00010"), 9);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00001"), 10);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00005"), 11);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00004"), 12);
			nodes.put(this.estrildaeViduaLagonosticta.getHostTree().getNode("InternalNode_00003"), 13);
			this.estrildaeViduaLagonosticta.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadEstrildaeViduaLagonostictaAllAssociationsExample() {
		try {
			NexusFile file = new JaneNexusFile(this.estrildaeViduaLagonostictaAllAssociationsFileName);
			this.estrildaeViduaLagonostictaAllAssociations = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00014"), 0);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00013"), 1);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00012"), 3);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00008"), 4);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00011"), 5);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00010"), 6);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00007"), 7);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00006"), 8);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00005"), 9);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00009"), 10);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00004"), 11);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00003"), 12);
			nodes.put(this.estrildaeViduaLagonostictaAllAssociations.getHostTree().getNode("InternalNode_00001"), 13);
			this.estrildaeViduaLagonostictaAllAssociations.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadEstrildaeViduaPytiliaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.estrildaeViduaPytiliaFileName);
			this.estrildaeViduaPytilia = file.read();		
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.estrildaeViduaPytilia.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.estrildaeViduaPytilia.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.estrildaeViduaPytilia.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.estrildaeViduaPytilia.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.estrildaeViduaPytilia.getHostTree().getNode("InternalNode_00002"), 4);
			this.estrildaeViduaPytilia.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadEstrildaeViduaPytiliaAllAssociationsExample() {
		try {
			NexusFile file = new JaneNexusFile(this.estrildaeViduaPytiliaAllAssociationsFileName);
			this.estrildaeViduaPytiliaAllAssociations = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.estrildaeViduaPytiliaAllAssociations.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.estrildaeViduaPytiliaAllAssociations.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.estrildaeViduaPytiliaAllAssociations.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.estrildaeViduaPytiliaAllAssociations.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.estrildaeViduaPytiliaAllAssociations.getHostTree().getNode("InternalNode_00002"), 4);
			this.estrildaeViduaPytiliaAllAssociations.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadFigWaspAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.figWaspAFileName);
			this.figWaspA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00009"), 1);			
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00003"), 7); 
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.figWaspA.getHostTree().getNode("InternalNode_00001"), 9);
			this.figWaspA.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadFigWaspBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.figWaspBFileName);
			this.figWaspB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00009"), 6);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00006"), 8);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00008"), 9);
			nodes.put(this.figWaspB.getHostTree().getNode("InternalNode_00005"), 10);
			this.figWaspB.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadFigWaspCExample() {
		try {
			NexusFile file = new JaneNexusFile(this.figWaspCFileName);
			this.figWaspC = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00014"), 3);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00009"), 6);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00011"), 8);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00013"), 9);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00004"), 10);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00002"), 11);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00001"), 13);
			nodes.put(this.figWaspC.getHostTree().getNode("InternalNode_00007"), 14);
			this.figWaspC.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadFigWaspDExample() {
		try {
			NexusFile file = new JaneNexusFile(this.figwaspDFileName);
			this.figWaspD = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00010"), 5);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00009"), 6);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00004"), 9);
			nodes.put(this.figWaspD.getHostTree().getNode("InternalNode_00003"), 10);
			this.figWaspD.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadFigWaspFExample() {
		try {
			NexusFile file = new JaneNexusFile(this.figWaspFFileName);
			this.figWaspF = file.read();
						
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.figWaspF.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.figWaspF.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.figWaspF.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.figWaspF.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.figWaspF.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.figWaspF.getHostTree().getNode("InternalNode_00001"), 5);
			this.figWaspF.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadFlamingoLiceExample() {
		try {
			NexusFile file = new JaneNexusFile(this.flamingoLiceFileName);
			this.flamingoLice = file.read();
						
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00008"), 4);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.flamingoLice.getHostTree().getNode("InternalNode_00002"), 8);
			this.flamingoLice.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadFrogsPolystomesExample() {
		try {
			NexusFile file = new JaneNexusFile(this.frogsPolystomesFileName);
			this.frogsPolystomes = file.read();
						
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00016"), 0);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00015"), 3);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00012"), 5);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00011"), 6);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00008"), 8);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00014"), 9);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00002"), 10);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00013"), 11);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00001"), 12);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00006"), 13);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00010"), 14);
			nodes.put(this.frogsPolystomes.getHostTree().getNode("InternalNode_00009"), 15);
			this.frogsPolystomes.getHostTree().setUniqueNodeTimings(nodes);

		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadGammaHerpesvirusExample() {	
		try {
			NexusFile file = new JaneNexusFile(this.gammaHerpesvirusLCVFileName);
			this.gammaHerpesvirusLCV = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00017"), 0);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00016"), 1);			
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00011"), 2);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00015"), 3);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00013"), 5);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00012"), 6); 
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00010"), 7);			
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00008"), 9);			
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00005"), 10);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00007"), 11);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00006"), 12);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00003"), 13);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00004"), 14);
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00002"), 15); 
			nodes.put(this.gammaHerpesvirusLCV.getHostTree().getNode("InternalNode_00001"), 16);
			this.gammaHerpesvirusLCV.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadGammaHerpesVirus() {
		try {
			NexusFile file = new JaneNexusFile(this.gammaHerpesvirusRVFileName);
			this.gammaHerpesVirusRV = file.read();				
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00008"), 4);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.gammaHerpesVirusRV.getHostTree().getNode("InternalNode_00001"), 8);
			this.gammaHerpesVirusRV.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadHantavirus2003Example() {	
		try {
			NexusFile file = new JaneNexusFile(this.hantavirus2003FileName);
			this.hantavirus2003 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00013"), 0);
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00006"), 1); 
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00012"), 2); 
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00010"), 3);			
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00009"), 4);
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00008"), 5);			
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00011"), 7);
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00004"), 9);
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00003"), 10);
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00002"), 11); 
			nodes.put(this.hantavirus2003.getHostTree().getNode("InternalNode_00001"), 12);
			this.hantavirus2003.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadHantivirus2009aExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantivirus2009aFileName);
			
			this.hantivirus2009a = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00018"), 0);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00009"), 2);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00016"), 4);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00003"), 8);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00008"), 9);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00012"), 10);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00013"), 11);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00007"), 12);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00015"), 13);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00002"), 14);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00006"), 15);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00011"), 16);
			nodes.put(this.hantivirus2009a.getHostTree().getNode("InternalNode_00010"), 17);
			this.hantivirus2009a.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	

	@Before
	public void LoadHantivirus2009bExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantivirus2009bFileName);
			
			this.hantivirus2009b = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00018"), 0);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00009"), 2);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00016"), 5);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00015"), 6);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00014"), 8);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00012"), 9);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00011"), 10);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00003"), 11);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00007"), 13);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00010"), 14);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00006"), 15);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00002"), 16);
			nodes.put(this.hantivirus2009b.getHostTree().getNode("InternalNode_00013"), 17);
			this.hantivirus2009b.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
		
	@Before
	public void LoadHantavirus2012Example() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirus2012FileName);
			this.hantavirus2012 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00018"), 0);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00017"), 1);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00016"), 2);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00015"), 5);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00013"), 7);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00001"), 8);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00002"), 9);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00012"), 10);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00011"), 11);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00010"), 13);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00009"), 14);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00007"), 15);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00006"), 16);
			nodes.put(this.hantavirus2012.getHostTree().getNode("InternalNode_00014"), 17);
			this.hantavirus2012.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadHantavirusesArvicolinaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesArvicolinaeFileName);
			
			this.hantavirusesArvicolinae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00010"), 2);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00009"), 6);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00001"), 8);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00008"), 9);
			nodes.put(this.hantavirusesArvicolinae.getHostTree().getNode("InternalNode_00007"), 10);
			this.hantavirusesArvicolinae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadHantavirusesNeotominaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesNeotominaeFileName);
			
			this.hantavirusesNeotominae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesNeotominae.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.hantavirusesNeotominae.getHostTree().getNode("InternalNode_00002"), 1);
			nodes.put(this.hantavirusesNeotominae.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.hantavirusesNeotominae.getHostTree().getNode("InternalNode_00001"), 3);
			this.hantavirusesNeotominae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadHantavirusesSigmodontinaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesSigmodontinaeFileName);
			
			this.hantavirusesSigmodontinae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.hantavirusesSigmodontinae.getHostTree().getNode("InternalNode_00003"), 7);
			this.hantavirusesSigmodontinae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadHantavirusLaaExample() {	
		try {
			NexusFile file = new JaneNexusFile(this.hantivirusLaaFileName);
			this.hantivirusLaa = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00020"), 0);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00019"), 1);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00018"), 2);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00017"), 3);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00016"), 4);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00008"), 5);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00015"), 6);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00007"), 7);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00013"), 8);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00014"), 9);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00006"), 10);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00009"), 11);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00004"), 13);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00003"), 14);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00012"), 15);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00002"), 16);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00010"), 17);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00011"), 18);
			nodes.put(this.hantivirusLaa.getHostTree().getNode("InternalNode_00001"), 19);
			this.hantivirusLaa.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadHantavirusMaaExample() {	
		try {
			NexusFile file = new JaneNexusFile(this.hantivirusMaaFileName);
			this.hantivirusMaa = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00020"), 0);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00019"), 2);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00018"), 3);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00015"), 5);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00017"), 6);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00013"), 7);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00014"), 9);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00007"), 10);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00003"), 11);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00002"), 12);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00012"), 13);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00016"), 14);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00001"), 15);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00011"), 16);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00006"), 17);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00010"), 18);
			nodes.put(this.hantivirusMaa.getHostTree().getNode("InternalNode_00005"), 19);
			this.hantivirusMaa.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadHantavirusSaaExample() {	
		try {
			NexusFile file = new JaneNexusFile(this.hantivirusSaaFileName);
			this.hantivirusSaa = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00020"), 0);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00019"), 1);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00018"), 2);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00017"), 3);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00016"), 4);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00008"), 5);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00015"), 7);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00013"), 8);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00012"), 9);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00014"), 10);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00009"), 11);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00007"), 12);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00006"), 13);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00003"), 14);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00002"), 15);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00001"), 16);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00011"), 17);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00010"), 18);
			nodes.put(this.hantivirusSaa.getHostTree().getNode("InternalNode_00005"), 19);
			this.hantivirusSaa.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadHantavirusesLaaBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesLaaBFileName);
			
			this.hantavirusesLaaB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00026"), 0);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00025"), 1);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00016"), 2);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00024"), 4);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00015"), 5);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00003"), 8);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00010"), 9);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00009"), 10);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00008"), 11);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00023"), 12);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00019"), 13);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00007"), 14);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00006"), 15);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00002"), 16);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00013"), 17);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00001"), 18);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00012"), 19);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00017"), 20);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00018"), 21);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00022"), 22);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00011"), 23);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00021"), 24);
			nodes.put(this.hantavirusesLaaB.getHostTree().getNode("InternalNode_00020"), 25);
			this.hantavirusesLaaB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		

	@Before
	public void LoadHantavirusesMaaBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesMaaBFileName);
			
			this.hantavirusesMaaB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00026"), 0);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00025"), 1);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00024"), 2);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00023"), 3);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00019"), 4);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00016"), 5);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00015"), 6);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00014"), 7);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00010"), 8);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00009"), 9);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00008"), 10);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00002"), 11);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00001"), 12);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00005"), 13);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00004"), 14);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00003"), 15);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00007"), 16);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00006"), 17);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00022"), 18);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00021"), 19);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00013"), 20);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00017"), 21);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00018"), 22);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00011"), 23);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00020"), 24);
			nodes.put(this.hantavirusesMaaB.getHostTree().getNode("InternalNode_00012"), 25);
			this.hantavirusesMaaB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadHantavirusesSaaBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.hantavirusesSaaBFileName);
			
			this.hantavirusesSaaB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00026"), 0);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00025"), 1);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00016"), 2);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00024"), 3);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00015"), 4);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00014"), 5);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00010"), 6);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00005"), 7);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00004"), 8);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00003"), 9);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00023"), 10);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00019"), 11);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00009"), 12);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00008"), 13);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00007"), 14);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00002"), 15);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00001"), 16);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00006"), 17);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00013"), 18);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00011"), 19);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00012"), 20);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00017"), 21);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00018"), 22);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00022"), 23);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00021"), 24);
			nodes.put(this.hantavirusesSaaB.getHostTree().getNode("InternalNode_00020"), 25);
			this.hantavirusesSaaB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
		
	@Before
	public void LoadLyssavirusVirus() {
		try {
			NexusFile file = new JaneNexusFile(this.lyssavirusFileName);
			this.lyssavirus = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00007"), 7);
			nodes.put(this.lyssavirus.getHostTree().getNode("InternalNode_00006"), 8);
			this.lyssavirus.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadMamalsMLV2001Example() {
		try {
			NexusFile file = new JaneNexusFile(this.mamalsMLV2001FileName);
			
			this.mamalsMLV2001 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00014"), 0);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00013"), 1);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00012"), 2);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00011"), 3);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00010"), 6);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00009"), 8);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00003"), 9);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00008"), 10);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00007"), 11);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00006"), 13);
			nodes.put(this.mamalsMLV2001.getHostTree().getNode("InternalNode_00002"), 14);
			this.mamalsMLV2001.getHostTree().setUniqueNodeTimings(nodes);

		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMamalsMLV2001FurtherReducedExample() {
		try {
			NexusFile file = new JaneNexusFile(this.MamalsMLVFurtherReduced2001FileName);
			
			this.MamalsMLVFurtherReduced2001 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.MamalsMLVFurtherReduced2001.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.MamalsMLVFurtherReduced2001.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.MamalsMLVFurtherReduced2001.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.MamalsMLVFurtherReduced2001.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.MamalsMLVFurtherReduced2001.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.MamalsMLVFurtherReduced2001.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.MamalsMLVFurtherReduced2001.getHostTree().getNode("InternalNode_00001"), 6);
			this.MamalsMLVFurtherReduced2001.getHostTree().setUniqueNodeTimings(nodes);
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
			NexusFile file = new JaneNexusFile(this.mammalMLVFurtherReducedFileName);
			this.mammalMLVFurtherReduced = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.mammalMLVFurtherReduced.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.mammalMLVFurtherReduced.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.mammalMLVFurtherReduced.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.mammalMLVFurtherReduced.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.mammalMLVFurtherReduced.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.mammalMLVFurtherReduced.getHostTree().getNode("InternalNode_00001"), 5);
			this.mammalMLVFurtherReduced.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMammalMLVReducedExample() {
		try {
			NexusFile file = new JaneNexusFile(this.mammalsMLVReducedFileName);
			this.mammalsMLVReduced = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00013"), 0);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00011"), 2);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00010"), 3);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00009"), 4);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00008"), 7);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00007"), 9);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00006"), 10);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00004"), 11);
			nodes.put(this.mammalsMLVReduced.getHostTree().getNode("InternalNode_00005"), 12);
			this.mammalsMLVReduced.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadMillipedesMitesTM6Example() {
		try {
			NexusFile file = new JaneNexusFile(this.millipeadsMites6ExampleFileName);
			this.millipeadsMites6Example = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.millipeadsMites6Example.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.millipeadsMites6Example.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.millipeadsMites6Example.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.millipeadsMites6Example.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.millipeadsMites6Example.getHostTree().getNode("InternalNode_00001"), 4);
			this.millipeadsMites6Example.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMillipedesMitesTM7AExample() {
		try {
			NexusFile file = new JaneNexusFile(this.millipeadsMites7AExampleFileName);
			this.millipeadsMites7AExample = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.millipeadsMites7AExample.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.millipeadsMites7AExample.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.millipeadsMites7AExample.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.millipeadsMites7AExample.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.millipeadsMites7AExample.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.millipeadsMites7AExample.getHostTree().getNode("InternalNode_00005"), 5);
			this.millipeadsMites7AExample.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMillipedesMitesTM7BExample() {
		try {
			NexusFile file = new JaneNexusFile(this.millipeadsMites7BExampleFileName);
			this.millipeadsMites7BExample = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.millipeadsMites7BExample.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.millipeadsMites7BExample.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.millipeadsMites7BExample.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.millipeadsMites7BExample.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.millipeadsMites7BExample.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.millipeadsMites7BExample.getHostTree().getNode("InternalNode_00005"), 5);
			this.millipeadsMites7BExample.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMillipedesMitesTM7CExample() {
		try {
			NexusFile file = new JaneNexusFile(this.millipeadsMites7CExampleFileName);
			this.millipeadsMites7CExample = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.millipeadsMites7CExample.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.millipeadsMites7CExample.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.millipeadsMites7CExample.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.millipeadsMites7CExample.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.millipeadsMites7CExample.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.millipeadsMites7CExample.getHostTree().getNode("InternalNode_00001"), 5);
			this.millipeadsMites7CExample.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMillipedesMitesTM7DExample() {
		try {
			NexusFile file = new JaneNexusFile(this.millipeadsMites7DExampleFileName);
			this.millipeadsMites7DExample = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.millipeadsMites7DExample.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.millipeadsMites7DExample.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.millipeadsMites7DExample.getHostTree().getNode("InternalNode_00005"), 2);
			nodes.put(this.millipeadsMites7DExample.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.millipeadsMites7DExample.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.millipeadsMites7DExample.getHostTree().getNode("InternalNode_00001"), 5);
			this.millipeadsMites7DExample.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadMimeticVirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.mimeticHeliconiusButterfliesFileName);
			this.mimeticHeliconiusButterflies = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00009"), 2);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00006"), 5);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00005"), 6);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00003"), 8);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00002"), 9);
			nodes.put(this.mimeticHeliconiusButterflies.getHostTree().getNode("InternalNode_00001"), 10);
			this.mimeticHeliconiusButterflies.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadMurinaeCladeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.murinaeCladeFileName);
			
			this.murinaeClade = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.murinaeClade.getHostTree().getNode("InternalNode_00003"), 0);
			nodes.put(this.murinaeClade.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.murinaeClade.getHostTree().getNode("InternalNode_00002"), 2);
			this.murinaeClade.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadMyrmarachneMimicExample() {
		try {
			NexusFile file = new JaneNexusFile(this.myrmarachneMimicFileName);
			
			this.myrmarachneMimic = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.myrmarachneMimic.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.myrmarachneMimic.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.myrmarachneMimic.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.myrmarachneMimic.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.myrmarachneMimic.getHostTree().getNode("InternalNode_00002"), 4);
			this.myrmarachneMimic.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}			
	
	@Before
	public void NarnavirdaeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.narnavirdaeFileName);
			this.narnavirdae = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.narnavirdae.getHostTree().getNode("InternalNode_00001"), 4);
			this.narnavirdae.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadNematodesBacteriaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.nematodesBacteriaFileName);
			
			this.nematodesBacteria = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00009"), 2);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00006"), 6);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00003"), 9);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00002"), 10);
			nodes.put(this.nematodesBacteria.getHostTree().getNode("InternalNode_00001"), 11);
			this.nematodesBacteria.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPapillomavirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.papillomavirusFileName);
			this.papillomavirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00014"), 4);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00013"), 5);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00010"), 6);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00012"), 7);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00004"), 8);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00009"), 9);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00001"), 10);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00002"), 11);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00011"), 13);
			nodes.put(this.papillomavirus.getHostTree().getNode("InternalNode_00003"), 14);
			this.papillomavirus.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPocketGopherLouseExampleA() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGopherLouseFileNameA);
			this.pocketGopherLouseA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();	
			nodes.put(this.pocketGopherLouseA.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.pocketGopherLouseA.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.pocketGopherLouseA.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.pocketGopherLouseA.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.pocketGopherLouseA.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.pocketGopherLouseA.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.pocketGopherLouseA.getHostTree().getNode("InternalNode_00002"), 6);			
			this.pocketGopherLouseA.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPocketGopherLouseExampleB() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGopherLouseFileNameB);
			this.pocketGopherLouseB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pocketGopherLouseB.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.pocketGopherLouseB.getHostTree().getNode("InternalNode_00001"), 1);
			nodes.put(this.pocketGopherLouseB.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.pocketGopherLouseB.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.pocketGopherLouseB.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.pocketGopherLouseB.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.pocketGopherLouseB.getHostTree().getNode("InternalNode_00002"), 6);
			this.pocketGopherLouseB.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPocketGopherLouseExampleC() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGopherLouseFileNameC);
			this.pocketGopherLouseC = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.pocketGopherLouseC.getHostTree().getNode("InternalNode_00003"), 8);
			this.pocketGopherLouseC.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPocketGopherLouseExampleD() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGopherLouseFileNameD);
			this.pocketGopherLouseD = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00011"), 2);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00018"), 3);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00017"), 4);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00014"), 6);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00004"), 8);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00010"), 9);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00013"), 10);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00001"), 11);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00009"), 12);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00008"), 13);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00007"), 14);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00006"), 15);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00016"), 16);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00002"), 17);
			nodes.put(this.pocketGopherLouseD.getHostTree().getNode("InternalNode_00015"), 18);
			this.pocketGopherLouseD.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPocketGopherLouseExampleE() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGopherLouseFileNameE);
			this.pocketGopherLouseE = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pocketGopherLouseE.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.pocketGopherLouseE.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.pocketGopherLouseE.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.pocketGopherLouseE.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.pocketGopherLouseE.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.pocketGopherLouseE.getHostTree().getNode("InternalNode_00001"), 5);
			nodes.put(this.pocketGopherLouseE.getHostTree().getNode("InternalNode_00005"), 6);
			this.pocketGopherLouseE.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPocketGophersLikelihoodExample() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGopherLouseFileNameLikelihood);
			this.pocketGopherLouseLikelihood = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00012"), 0);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00011"), 1);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00010"), 3);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00009"), 9);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00001"), 10);
			nodes.put(this.pocketGopherLouseLikelihood.getHostTree().getNode("InternalNode_00008"), 11);
			this.pocketGopherLouseLikelihood.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPocketGopherLouseExampleParifitReducedSet() {
		try {
			NexusFile file = new JaneNexusFile(this.pocketGopherLouseFileNameParafitReducedSet);
			this.pocketGopherLouseParafitReducedSet = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00006"), 3);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.pocketGopherLouseParafitReducedSet.getHostTree().getNode("InternalNode_00001"), 8);
			this.pocketGopherLouseParafitReducedSet.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadPrimateLentiVirusExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primateLentiVirusFileName);
			this.primateLentiVirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00010"), 1);			
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00009"), 2);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00006"), 7);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00004"), 9);
			nodes.put(this.primateLentiVirus.getHostTree().getNode("InternalNode_00001"), 10);
			this.primateLentiVirus.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	
	@Before
	public void LoadPrimateMalariaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primatesMalariaFileName);
			this.primatesMalaria = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.primatesMalaria.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.primatesMalaria.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.primatesMalaria.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.primatesMalaria.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.primatesMalaria.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.primatesMalaria.getHostTree().getNode("InternalNode_00003"), 5);
			this.primatesMalaria.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPrimatesOxyuroidsExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primatesOxyuroidsFileName);
			
			this.primatesOxyuroids = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00033"), 0);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00032"), 1);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00031"), 2);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00029"), 3);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00027"), 4);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00028"), 5);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00030"), 6);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00026"), 7);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00025"), 8);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00021"), 9);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00020"), 10);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00024"), 11);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00023"), 12);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00022"), 13);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00019"), 14);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00018"), 15);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00017"), 16);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00016"), 17);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00015"), 18);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00014"), 19);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00008"), 20);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00007"), 21);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00013"), 22);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00009"), 23);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00012"), 24);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00011"), 25);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00010"), 26);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00006"), 27);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00005"), 28);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00004"), 29);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00003"), 30);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00002"), 31);
			nodes.put(this.primatesOxyuroids.getHostTree().getNode("InternalNode_00001"), 32);
			this.primatesOxyuroids.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPrimatePinwormsExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primatePinwormsFileName);
			this.primatePinworms = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.primatePinworms.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.primatePinworms.getHostTree().getNode("InternalNode_00003"), 1);			
			nodes.put(this.primatePinworms.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.primatePinworms.getHostTree().getNode("InternalNode_00001"), 3);
			this.primatePinworms.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPrimatePinwormsCatarhiniExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primatesPinwormsCatarhiniFileName);
			this.primatesPinwormsCatarhini = file.read();		
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00012"), 0);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00011"), 1);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00010"), 8);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00006"), 9);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00001"), 10);
			nodes.put(this.primatesPinwormsCatarhini.getHostTree().getNode("InternalNode_00009"), 11);
			this.primatesPinwormsCatarhini.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPrimatePinwormsPlatyrrhiniExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primatesPinwormsPlatyrrhiniFileName);
			this.primatesPinwormsPlatyrrhini = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();				
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00014"), 0);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00008"), 1);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00007"), 2);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00006"), 3);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00005"), 4);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00004"), 5);				
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00003"), 6);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00002"), 7);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00001"), 8);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00013"), 9);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00012"), 10);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00011"), 11);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00010"), 12);			
			nodes.put(this.primatesPinwormsPlatyrrhini.getHostTree().getNode("InternalNode_00009"), 13);
			this.primatesPinwormsPlatyrrhini.getHostTree().setUniqueNodeTimings(nodes);				
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadPrimatePinwormsStrepsirhiniExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primatesPinwormsStrepsirhiniFileName);
			this.primatesPinwormsStrepsirhini = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();	
			nodes.put(this.primatesPinwormsStrepsirhini.getHostTree().getNode("InternalNode_00007"), 0);	
			nodes.put(this.primatesPinwormsStrepsirhini.getHostTree().getNode("InternalNode_00006"), 1);	
			nodes.put(this.primatesPinwormsStrepsirhini.getHostTree().getNode("InternalNode_00005"), 2);	
			nodes.put(this.primatesPinwormsStrepsirhini.getHostTree().getNode("InternalNode_00004"), 3);	
			nodes.put(this.primatesPinwormsStrepsirhini.getHostTree().getNode("InternalNode_00001"), 4);	
			nodes.put(this.primatesPinwormsStrepsirhini.getHostTree().getNode("InternalNode_00003"), 5);	
			nodes.put(this.primatesPinwormsStrepsirhini.getHostTree().getNode("InternalNode_00002"), 6);	
			this.primatesPinwormsStrepsirhini.getHostTree().setUniqueNodeTimings(nodes);
			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadPrimatePinwormsLargeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.primatesPinwormsLargeFileName);
			this.primatesPinwormsLarge = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00035"), 0);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00027"), 1);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00014"), 2);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00034"), 3);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00026"), 4);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00033"), 5);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00022"), 6);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00008"), 7);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00007"), 8);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00021"), 9);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00013"), 10);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00032"), 11);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00005"), 12);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00019"), 13);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00012"), 14);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00031"), 15);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00011"), 16);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00016"), 17);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00025"), 18);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00024"), 19);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00003"), 20);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00020"), 21);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00018"), 22);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00010"), 23);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00017"), 24);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00015"), 25);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00028"), 26);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00030"), 27);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00004"), 28);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00006"), 29);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00002"), 30);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00023"), 31);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00001"), 32);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00029"), 33);
			nodes.put(this.primatesPinwormsLarge.getHostTree().getNode("InternalNode_00009"), 34);
			this.primatesPinwormsLarge.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadPytiliaViduaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.pytiliaViduaFileName);
			this.pytiliaVidua = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.pytiliaVidua.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.pytiliaVidua.getHostTree().getNode("InternalNode_00005"), 1);
			nodes.put(this.pytiliaVidua.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.pytiliaVidua.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.pytiliaVidua.getHostTree().getNode("InternalNode_00001"), 4);
			nodes.put(this.pytiliaVidua.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.pytiliaVidua.getHostTree().getNode("InternalNode_00006"), 6);
			this.pytiliaVidua.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadReptilesMLVExample() {
		try {
			NexusFile file = new JaneNexusFile(this.reptilesMLVFileName);
			this.reptilesMLV = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00002"), 3);
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00003"), 6);
			nodes.put(this.reptilesMLV.getHostTree().getNode("InternalNode_00001"), 7);
			this.reptilesMLV.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Before
	public void LoadRetroelementsAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.retroelementsAFileName);
			
			this.retroelementsA = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.retroelementsA.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.retroelementsA.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.retroelementsA.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.retroelementsA.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.retroelementsA.getHostTree().getNode("InternalNode_00003"), 4);
			this.retroelementsA.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadRetroelementsBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.retroelementsBFileName);
			
			this.retroelementsB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.retroelementsB.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.retroelementsB.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.retroelementsB.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.retroelementsB.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.retroelementsB.getHostTree().getNode("InternalNode_00003"), 4);
			this.retroelementsB.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadRetroelementsDExample() {
		try {
			NexusFile file = new JaneNexusFile(this.retroelementsDFileName);
			
			this.retroelementsD = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.retroelementsD.getHostTree().getNode("InternalNode_00007"), 0);
			nodes.put(this.retroelementsD.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.retroelementsD.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.retroelementsD.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.retroelementsD.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.retroelementsD.getHostTree().getNode("InternalNode_00004"), 5);
			nodes.put(this.retroelementsD.getHostTree().getNode("InternalNode_00003"), 6);
			this.retroelementsD.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadRetroelementsFExample() {
		try {
			NexusFile file = new JaneNexusFile(this.retroelementsFFileName);
			
			this.retroelementsF = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.retroelementsF.getHostTree().getNode("InternalNode_00005"), 0);
			nodes.put(this.retroelementsF.getHostTree().getNode("InternalNode_00004"), 1);
			nodes.put(this.retroelementsF.getHostTree().getNode("InternalNode_00003"), 2);
			nodes.put(this.retroelementsF.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.retroelementsF.getHostTree().getNode("InternalNode_00002"), 4);
			this.retroelementsF.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadRodentLouseExample() {
		try {
			NexusFile file = new JaneNexusFile(this.rodentLouseFileName);
			this.rodentLouse = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00019"), 0);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00003"), 1);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00001"), 3);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00017"), 4);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00008"), 5);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00015"), 6);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00018"), 7);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00002"), 8);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00005"), 9);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00014"), 10);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00011"), 11);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00004"), 12);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00013"), 13);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00009"), 14);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00016"), 15);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00012"), 16);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00010"), 17);
			nodes.put(this.rodentLouse.getHostTree().getNode("InternalNode_00006"), 18);
			this.rodentLouse.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadRodentPinworms2Example() {
		try {
			NexusFile file = new JaneNexusFile(this.rodentPinworms2FileName);
			this.rodentPinworms2 = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.rodentPinworms2.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.rodentPinworms2.getHostTree().getNode("InternalNode_00005"), 1);				
			nodes.put(this.rodentPinworms2.getHostTree().getNode("InternalNode_00001"), 2);
			nodes.put(this.rodentPinworms2.getHostTree().getNode("InternalNode_00004"), 3);			
			nodes.put(this.rodentPinworms2.getHostTree().getNode("InternalNode_00003"), 4);
			nodes.put(this.rodentPinworms2.getHostTree().getNode("InternalNode_00002"), 5);
			//this.rodentPinworms2.getHostTree().setUniqueNodeTimings(nodes);					
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadRodentPinworms3Example() {
		try {
			NexusFile file = new JaneNexusFile(this.rodentPinworms3FileName);
			this.rodentPinworms3 = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.rodentPinworms3.getHostTree().getNode("InternalNode_00006"), 0);
			nodes.put(this.rodentPinworms3.getHostTree().getNode("InternalNode_00005"), 1);				
			nodes.put(this.rodentPinworms3.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.rodentPinworms3.getHostTree().getNode("InternalNode_00003"), 3);			
			nodes.put(this.rodentPinworms3.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.rodentPinworms3.getHostTree().getNode("InternalNode_00001"), 5);
			this.rodentPinworms3.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadRodentPinwormsExpanded2Example() {
		try {
			NexusFile file = new JaneNexusFile(this.rodentPinWormsExpanded2FileName);
			this.rodentPinWorms2Expanded = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00009"), 0);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00001"), 2);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00002"), 5);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00005"), 6);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.rodentPinWorms2Expanded.getHostTree().getNode("InternalNode_00003"), 8);
			this.rodentPinWorms2Expanded.getHostTree().setUniqueNodeTimings(nodes);			
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadRugosusWaterhouseiExample() {
		try {
			NexusFile file = new JaneNexusFile(this.rugosusWaterhouseiFileName);
			this.rugosusWaterhousei = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00011"), 0);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00010"), 1);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00001"), 2);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00007"), 4);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00006"), 8);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00002"), 9);
			nodes.put(this.rugosusWaterhousei.getHostTree().getNode("InternalNode_00009"), 10);
			this.rugosusWaterhousei.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}		
	
	@Before
	public void LoadSeabirdLouseAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.seabirdLouseAFileName);
			this.seabirdLouseA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.seabirdLouseA.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.seabirdLouseA.getHostTree().getNode("InternalNode_00003"), 1);			
			nodes.put(this.seabirdLouseA.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.seabirdLouseA.getHostTree().getNode("InternalNode_00001"), 3);
			this.seabirdLouseA.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadSeabirdLouseBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.seabirdLouseBFileName);
			this.seabirdLouseB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00004"), 0);
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00003"), 1);			
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00002"), 2);
			nodes.put(this.seabirdLouseB.getHostTree().getNode("InternalNode_00001"), 3);
			this.seabirdLouseB.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadSeabirdLouseLargeAExample() {
		try {
			NexusFile file = new JaneNexusFile(this.largeSeabirdLouseAFileName);
			this.largeSeabirdLouseA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00006"), 7);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00005"), 8);
			nodes.put(this.largeSeabirdLouseA.getHostTree().getNode("InternalNode_00004"), 9);
			this.largeSeabirdLouseA.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadSeabirdLouseLargeBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.largeSeabirdLouseBFileName);
			this.largeSeabirdLouseB = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00005"), 4);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00007"), 5);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00003"), 7);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00001"), 8);
			nodes.put(this.largeSeabirdLouseB.getHostTree().getNode("InternalNode_00002"), 9);
			this.largeSeabirdLouseB.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadSigmodontinaeCladeExample() {
		try {
			NexusFile file = new JaneNexusFile(this.sigmodontinaeCladeFileName);
			
			this.sigmodontinaeClade = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00012"), 1);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00011"), 2);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00007"), 3);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00006"), 4);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00010"), 5);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00004"), 6);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00005"), 7);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00014"), 8);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00003"), 9);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00002"), 10);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00013"), 11);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00001"), 12);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00009"), 13);
			nodes.put(this.sigmodontinaeClade.getHostTree().getNode("InternalNode_00008"), 14);
			this.sigmodontinaeClade.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadSpumaviursExample() {
		try {
			NexusFile file = new JaneNexusFile(this.spumavirusFileName);
			this.spumaVirus = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00009"), 0);			
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00008"), 1);
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00007"), 2);			
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00003"), 3);
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00006"), 4);			
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00004"), 6);			
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00002"), 7);
			nodes.put(this.spumaVirus.getHostTree().getNode("InternalNode_00001"), 8);
			this.spumaVirus.getHostTree().setUniqueNodeTimings(nodes);		
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadTephritinaeSymbioticBacteriaExample() {
		try {
			NexusFile file = new JaneNexusFile(this.tephritinaeSymbioticBacteriaAFileName);
			this.tephritinaeSymbioticBacteriaA = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00037"), 0);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00036"), 1);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00034"), 2);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00032"), 3);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00024"), 4);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00019"), 5);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00013"), 6);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00018"), 7);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00017"), 8);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00033"), 9);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00012"), 10);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00011"), 11);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00023"), 12);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00022"), 13);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00010"), 14);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00009"), 15);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00031"), 16);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00008"), 17);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00025"), 18);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00030"), 19);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00005"), 20);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00007"), 21);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00029"), 22);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00004"), 23);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00016"), 24);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00028"), 25);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00003"), 26);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00006"), 27);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00014"), 28);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00015"), 29);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00026"), 30);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00027"), 31);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00002"), 32);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00020"), 33);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00021"), 34);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00035"), 35);
			nodes.put(this.tephritinaeSymbioticBacteriaA.getHostTree().getNode("InternalNode_00001"), 36);
			this.tephritinaeSymbioticBacteriaA.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}	
	
	@Before
	public void LoadTephritinaeSymbioticBacteriaBExample() {
		try {
			NexusFile file = new JaneNexusFile(this.tephritinaeSymbioticBacteriaBFileName);
			this.tephritinaeSymbioticBacteriaB = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00016"), 0);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00015"), 1);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00013"), 2);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00012"), 3);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00008"), 4);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00011"), 6);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00004"), 7);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00010"), 8);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00007"), 9);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00009"), 10);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00014"), 11);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00003"), 12);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00001"), 13);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00002"), 14);
			nodes.put(this.tephritinaeSymbioticBacteriaB.getHostTree().getNode("InternalNode_00006"), 15);
			this.tephritinaeSymbioticBacteriaB.getHostTree().setUniqueNodeTimings(nodes);

		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadTermiteProtistExample() {
		try {
			NexusFile file = new JaneNexusFile(this.termiteProtistFileName);
			
			this.termiteProtist = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00015"), 0);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00007"), 1);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00006"), 2);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00014"), 8);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00013"), 9);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00012"), 10);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00009"), 11);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00008"), 12);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00011"), 13);
			nodes.put(this.termiteProtist.getHostTree().getNode("InternalNode_00010"), 14);
			this.termiteProtist.getHostTree().setUniqueNodeTimings(nodes);

		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadToucansChewingLiceExample() {
		try {
			NexusFile file = new JaneNexusFile(this.toucansChewingLiceFileName);
			this.toucansChewingLice = file.read();	
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00008"), 2);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00007"), 7);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00001"), 8);
			nodes.put(this.toucansChewingLice.getHostTree().getNode("InternalNode_00006"), 9);
			this.toucansChewingLice.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadTribevKemerovoVirusesExample() {
		try {
			NexusFile file = new JaneNexusFile(this.tribevKemerovoVirusesFileName);
			this.tribevKemerovoViruses = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00010"), 0);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00009"), 1);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00004"), 2);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00008"), 3);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00005"), 5);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00007"), 6);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00001"), 7);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00003"), 8);
			nodes.put(this.tribevKemerovoViruses.getHostTree().getNode("InternalNode_00006"), 9);
			this.tribevKemerovoViruses.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadUP1aUPIIIExample() {
		try {
			NexusFile file = new JaneNexusFile(this.uP1aUPIIIFileName);
			this.uP1aUPIII = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00004"), 3);
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00002"), 4);
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00001"), 6);
			nodes.put(this.uP1aUPIII.getHostTree().getNode("InternalNode_00005"), 7);
			this.uP1aUPIII.getHostTree().setUniqueNodeTimings(nodes);	
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}	
	
	@Before
	public void LoadUP1bUPIIExample() {
		try {
			NexusFile file = new JaneNexusFile(this.uP1bUPIIFileName);
			this.uP1bUPII = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.uP1bUPII.getHostTree().getNode("InternalNode_00001"), 7);
			this.uP1bUPII.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadUP1bUPIIIExample() {
		try {
			NexusFile file = new JaneNexusFile(this.uP1bUPIII2006FileName);
			this.uP1bUPIII = file.read();
			
			Map<Node, Integer> nodes = new LinkedHashMap<Node, Integer>();
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00008"), 0);
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00006"), 1);
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00007"), 2);
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00005"), 3);
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00004"), 4);
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00003"), 5);
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00002"), 6);
			nodes.put(this.uP1bUPIII.getHostTree().getNode("InternalNode_00001"), 7);
			this.uP1bUPIII.getHostTree().setUniqueNodeTimings(nodes);
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
		
	@Test
	public void ActinobacillusActinomycetemcomitansETest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.actinobacillusActinomycetemcomitansE);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void AlphaHerpesTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.alphaHerpesVirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void AnolisPlasmodiumAzurophilumRedTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.anolisPlasmodiumAzurophilumRed);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	
	@Test
	public void AnolisPlasmodiumAzurophilumWhiteTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.anolisPlasmodiumAzurophilumWhite);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	
	@Test
	public void ArenavirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.arenavirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
			
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void ArenavirusOldWorldTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.arenavirusOldWorld);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void ArvicolinaeCladeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.arvicolinaeClade);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
		
	@Test
	public void ASLVTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.aslv);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void AtadenovirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.atadenovirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void AvianMLVTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.avianMLV);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void AVRTE1Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.aVRTE1);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal under Jane cost scheme
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 17, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void BatsGammaVirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.batsGammaViruses);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void BeesNosemaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.beesNosema);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void BemisiaTabaciEndosymbiontsATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.bemisiaTabaciEndosymbiontsA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void BemisiaTabaciEndosymbiontsBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.bemisiaTabaciEndosymbiontsB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void BemisiaTabaciEndosymbiontsCTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.bemisiaTabaciEndosymbiontsC);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void BemisiaTabaciEndosymbiontsDTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.bemisiaTabaciEndosymbiontsD);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void BemisiaTabaciEndosymbiontsETest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.bemisiaTabaciEndosymbiontsE);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void BetaHerpesVirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.betaHerpesVirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void BirdLiceATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void BirdsLiceCTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceC);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void BirdsLiceETest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceE);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void BirdsLiceHTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.birdsLiceH);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}

	@Test
	public void CalicivirusesTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.caliciviruses);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();		
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void CircovirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.circovirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void CompositeCladeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.compositeClade);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void CoronavirusesATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.coronavirusesA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void CoronavirusesBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.coronavirusesB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void CoronavirusesCTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.coronavirusesC);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void CrinoidMyzostomidATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.crinoidMyzostomidA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
		
	@Test
	public void EstrildaeViduaEstrildaCoccopygiaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeViduaEstrildaCoccopygia);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal under the Jane cost scheme
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void EstrildaeViduaLagonostictaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeViduaLagonosticta);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void EstrildaeViduaLagonostictaAllAssociationsTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeViduaLagonostictaAllAssociations);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void EstrildaeViduaPytiliaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeViduaPytilia);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void EstrildaeViduaPytiliaAllAssociationsTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.estrildaeViduaPytiliaAllAssociations);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void FigWaspATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.figWaspA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();		
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void FigWaspTestB() {
		TreeCollapse treeCollapse = new TreeCollapse(this.figWaspB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void FigWaspTestC() {
		TreeCollapse treeCollapse = new TreeCollapse(this.figWaspC);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void FigWaspTestD() {
		TreeCollapse treeCollapse = new TreeCollapse(this.figWaspD);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void FigWaspTestF() {
		TreeCollapse treeCollapse = new TreeCollapse(this.figWaspF);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void FlamingoLiceTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.flamingoLice);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void FrogsPolystomesTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.frogsPolystomes);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void GammaHerpesvirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.gammaHerpesvirusLCV);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// Note this solution is not the best solution always. If Codivergence cost is set to 1 then this is one less then the best solution
		assertEquals((Integer) 14, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void GammaHerpesVirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.gammaHerpesVirusRV);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void Hantavirus2003Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirus2003);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void Hantivirus2009aTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantivirus2009a);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void Hantivirus2009bTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantivirus2009b);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void Hantavirus2012Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirus2012);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal under Jane
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void HantavirusesArvicolinaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesArvicolinae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void HantavirusesNeotominaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesNeotominae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();	
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void HantavirusesSigmodontinaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesSigmodontinae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void HantavirusLaaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantivirusLaa);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void HantavirusMaaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantivirusMaa);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void HantavirusesLaaBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesLaaB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void HantavirusesMaaBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesMaaB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void HantavirusesSaaBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantavirusesSaaB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void HantavirusSaaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.hantivirusSaa);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void LyssavirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.lyssavirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// Note this solution is not the best solution always. Only when codivergence is set to 1 is this optimal
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void MammalMLV2001Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.mamalsMLV2001);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void MammalMLV2001FurtherReduced2001Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.MamalsMLVFurtherReduced2001);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void MammalMLVFurtherReducedTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.mammalMLVFurtherReduced);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void MammalsMLVReducedTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.mammalsMLVReduced);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void MimeticHeliconiusButterfliesTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.mimeticHeliconiusButterflies);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void MillipeadsMitesATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.millipeadsMites6Example);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void MillipeadsMitesBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.millipeadsMites7AExample);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void MillipeadsMitesCTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.millipeadsMites7BExample);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void MillipeadsMitesDTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.millipeadsMites7CExample);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void MillipeadsMitesETest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.millipeadsMites7DExample);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();

		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void MurinaeCladeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.murinaeClade);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void MyrmarachneMimicTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.myrmarachneMimic);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void NarnavirdaeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.narnavirdae);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void NematodesBacteriaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.nematodesBacteria);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void PapillomavirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.papillomavirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());	
	}
	
	@Test
	public void PocketGopherLouseTestA() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGopherLouseA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void PocketGopherLouseTestB() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGopherLouseB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void PocketGopherLouseTestC() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGopherLouseC);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void PocketGopherLouseTestD() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGopherLouseD);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void PocketGopherLouseTestE() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGopherLouseE);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void PocketGophersLouseLikelihoodTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGopherLouseLikelihood);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}
			
	@Test
	public void PocketGopherLouseTestParafitReducedSet() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pocketGopherLouseParafitReducedSet);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void PrimateLentiVirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primateLentiVirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void PrimatesMalariaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primatesMalaria);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void PrimatesOxyuroidsTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primatesOxyuroids);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 23, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void PrimatePinwormsTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primatePinworms);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void PrimatesPinwormsCatarhiniTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primatesPinwormsCatarhini);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void PrimatePinwormsPlatyrrhiniTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primatesPinwormsPlatyrrhini);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());				
	}	
	
	@Test
	public void PrimatePinwormsStrepsirhini() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primatesPinwormsStrepsirhini);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());		
	}		
	
	@Test
	public void PrimatesPinwormsLargeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.primatesPinwormsLarge);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 24, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 12, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void PytiliaViduaTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.pytiliaVidua);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void ReptilesMLVTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.reptilesMLV);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void RetroelementsATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.retroelementsA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());	
	}	
	
	@Test
	public void RetroelementsBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.retroelementsB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());	
	}
	
	@Test
	public void RetroelementsDTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.retroelementsD);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());	
	}
	
	@Test
	public void RetroelementsFTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.retroelementsF);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfLossEvents());	
	}		
	
	@Test
	public void RodentLouseTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.rodentLouse);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 10, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void RodentPinworms2Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.rodentPinworms2);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void RodentPinworms3Test() {
		TreeCollapse treeCollapse = new TreeCollapse(this.rodentPinworms3);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void RodentPinWorms2ExpandedTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.rodentPinWorms2Expanded);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void RugosusWaterhouseiTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.rugosusWaterhousei);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void SeabirdLouseATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.seabirdLouseA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void SeabirdLouseBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.seabirdLouseB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void SeabirdLouseLargeATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.largeSeabirdLouseA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void SeabirdLouseLargeBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.largeSeabirdLouseB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 4, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void SigmodontinaeCladeTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.sigmodontinaeClade);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();	
		
		// optimal under Jane 1 away under jungles
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 11, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void SpumaVirusTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.spumaVirus);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void TephritinaeSymbioticBacteriaATest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.tephritinaeSymbioticBacteriaA);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal under Jane
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}		
	
	@Test
	public void TephritinaeSymbioticBacteriaBTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.tephritinaeSymbioticBacteriaB);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		// optimal under Jane, 1 away under Jungles
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 9, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 1, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void TermiteProtistTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.termiteProtist);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 13, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void ToucansChewingLiceTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.toucansChewingLice);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 7, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void TribevKemerovoVirusesTest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.tribevKemerovoViruses);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 8, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfLossEvents());
	}
	
	@Test
	public void UP1aUPIIITest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.uP1aUPIII);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void UP1bUPIITest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.uP1bUPII);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();	
		
		assertEquals((Integer) 5, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 3, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
	
	@Test
	public void UP1bUPIIITest() {
		TreeCollapse treeCollapse = new TreeCollapse(this.uP1bUPIII);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();
		
		assertEquals((Integer) 6, reconstruction.getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, reconstruction.getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, reconstruction.getEvents().getNumberOfLossEvents());
	}	
}
