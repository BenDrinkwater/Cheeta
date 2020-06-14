package metaheuristics.ga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;

public class NodeMappingTest {

	private CoevolutionaryHistory smallTestCase;
	private final String smallTestCaseFileName 
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PocketGopherChewingLice2003A.nex";
	
	@Before
	public void LoadSmallTestCaseExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.smallTestCaseFileName);		
			this.smallTestCase = file.read();
			this.smallTestCase.getHostTree().setRandomUniqueNodeTimings();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Test
	public void SmallTestCaseNewMethod() {
		GeneticAlgoritmSolver solver = new ImprovedNodeMappingGASolver(this.smallTestCase, 50, false);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutionSet = solver.solve(50);
		assertEquals(true, 8 <= solutionSet.getFirstElement().getEvents().getCost());
		assertEquals(true, 8 >= solutionSet.getFirstElement().getEvents().getCost());
	}
	
	@Test
	public void SmallTestCaseOldMethod() {
		GeneticAlgoritmSolver solver = new NodeMappingGASolver(this.smallTestCase, 50, false);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutionSet = solver.solve(50);
		assertEquals(true, 8 <= solutionSet.getFirstElement().getEvents().getCost());
		assertEquals(true, 8 >= solutionSet.getFirstElement().getEvents().getCost());	
	}
}
