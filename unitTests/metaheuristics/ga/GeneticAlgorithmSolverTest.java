package metaheuristics.ga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFormatException;
import io.nexus.jane.JaneNexusFile;
import io.tree.TreeFile;
import io.tree.TreeFileParsingException;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;

public class GeneticAlgorithmSolverTest {

	private CoevolutionaryHistory ficusWasp;
	private final String ficusWaspFileName 
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Ficus2004a.nex";	

	private CoevolutionaryHistory figWaspSmall;
	private final String figWaspSmallFileName
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "testTreeHistories" + File.separator + "FicusWaspSmall.tree";	

	
	@Before
	public void LoadFicusWaspExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.ficusWaspFileName);	
			this.ficusWasp = file.read();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}			
	}
	
	@Before
	public void LoadSmallFigWaspExample() {
		try { 
			TreeFile file = new TreeFile(this.figWaspSmallFileName);		
			this.figWaspSmall = file.read();
		}
		catch (TreeFileParsingException e) {
			fail("Tree Format Exception");
		}
	}	
	
	@Test
	public void TreeCollapseGASolveTest1() {
		GeneticAlgoritmSolver solver = new TreeCollapseGASolver(this.ficusWasp, 30, false);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutions = solver.solve(200);
		assertNotNull(solutions);
		
		assertEquals((Integer) 8, solutions.getFirstElement().getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, solutions.getFirstElement().getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, solutions.getFirstElement().getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, solutions.getFirstElement().getEvents().getNumberOfLossEvents());		
	}
	
	@Test
	public void TreeCollapseGASolveTest2() {
		GeneticAlgoritmSolver solver = new TreeCollapseGASolver(this.ficusWasp, 100, false);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutions = solver.solve(100);
		assertNotNull(solutions);
		
		assertEquals(6, solutions.getFirstElement().getCost());	
	}
	
	@Test
	public void MultiThreadedTreeCollapseGASolveTest() {
		GeneticAlgoritmSolver solver = new TreeCollapseGASolver(this.ficusWasp, 30, true);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutions = solver.solve(200);
		assertNotNull(solutions);
		
		assertEquals((Integer) 8, solutions.getFirstElement().getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, solutions.getFirstElement().getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, solutions.getFirstElement().getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, solutions.getFirstElement().getEvents().getNumberOfLossEvents());		
	}	
	
	@Test
	public void MultiThreadedIncrementalTreeCollapseGASolveTest() {	
		GeneticAlgoritmSolver solver = new TreeCollapseGASolver(this.ficusWasp, 30, true);
		SolutionSet<CophylogenyReconstruction> solutions = solver.initialize();
		for (int i = 0; i < 200; ++i) {
			solutions.add(solver.solve(1));
		}
		assertNotNull(solutions);
		
		assertEquals((Integer) 8, solutions.getFirstElement().getEvents().getNumberOfCodivergenceEvents());
		assertEquals((Integer) 0, solutions.getFirstElement().getEvents().getNumberOfDuplicationEvents());
		assertEquals((Integer) 2, solutions.getFirstElement().getEvents().getNumberOfHostSwitchEvents());
		assertEquals((Integer) 2, solutions.getFirstElement().getEvents().getNumberOfLossEvents());				
	}
}
