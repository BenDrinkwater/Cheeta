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

public class TreeCollapseTest {

	private CoevolutionaryHistory primatesOxyuroids;
	private final String primatesOxyuroidsFileName = 
			"unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "PrimatesOxyuroids2003.nex";	
	
	@Before
	public void LoadPrimatesOxyuroidsExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.primatesOxyuroidsFileName);		
			this.primatesOxyuroids = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Test
	public void SingleThreadedSolveTest() {
		Solver solver = new TreeCollapseGASolver(this.primatesOxyuroids, 20, false);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutionSet = solver.solve(20);
		assertEquals(true, 34 <= solutionSet.getFirstElement().getEvents().getCost());
		assertEquals(true, 35 >= solutionSet.getFirstElement().getEvents().getCost());
	}
	
	@Test
	public void MultiThreadedSolveTest() {
		Solver solver = new TreeCollapseGASolver(this.primatesOxyuroids, 20, true);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutionSet = solver.solve(20);
		assertEquals(true, 34 <= solutionSet.getFirstElement().getEvents().getCost());
		assertEquals(true, 35 >= solutionSet.getFirstElement().getEvents().getCost());
	}	
}
