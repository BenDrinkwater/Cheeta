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
import reconstruction.ParasiteMappingList;
import reconstruction.solver.nm.ReconstructionGenerator;
import reconstruction.solver.tc.TreeCollapseEventCounter;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;
import util.SolverSet;

public class SingleThreadedTest {

	private CoevolutionaryHistory partitiviridae;
	private final String partitiviridaeFileName 
		= "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Partitiviridae2011.nex";
	
	@Before
	public void loadPartitiviridaeExample() {
		try { 
			NexusFile file = new JaneNexusFile(this.partitiviridaeFileName);		
			this.partitiviridae = file.read();
			this.partitiviridae.getHostTree().setRandomUniqueNodeTimings();
		}
		catch (NexusFormatException e) {
			fail("Nexus Format Exception");
		}
		catch (NexusFileHandlingException e) {
			fail("Nexus File Handling Exception");
		}	
	}
	
	@Test
	public void MultiThreadedSolverWithPartitiviridaeTest() {
		Solver solver = new MultiThreadedSolver(this.partitiviridae, 10);
		solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutionSet = solver.solve(10);
		assertEquals(true, 71 <= solutionSet.getFirstElement().getEvents().getCost());
		assertEquals(true, 85 >= solutionSet.getFirstElement().getEvents().getCost());
	}	
	
	
	class SingleThreadedSolver extends SingleThreadedGenerticAlgorithmSolver {

		public SingleThreadedSolver(CoevolutionaryHistory initialHistory, int populationSize) {
			super(initialHistory, populationSize, new int[] {0, 1, 1, 2});
		}
		
		@Override
		protected SolutionSet<CophylogenyReconstruction> initializeSolutionSet() {
			SolutionSet<CophylogenyReconstruction> reconstruction = new SolverSet<CophylogenyReconstruction>();
			return reconstruction;
		}

		@Override
		protected CophylogenyReconstruction solveInstance(CoevolutionaryHistory history) {
			ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(history, new int[] {0, 1, 1, 2});
			
			ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
			
			TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(history, list);
			eventCounter.getEventCounts();
			
			CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(history.getHostTree(), eventCounter.getHostMapping(), list, eventCounter.getEventCounts());
			return reconstruction;
		}		
	}
	
	class MultiThreadedSolver extends MultiThreadedAlgorithms {

		public MultiThreadedSolver(CoevolutionaryHistory initialHistory, int populationSize) {
			super(initialHistory, populationSize, new int[] {0, 1, 1, 2});
		}
		
		@Override
		protected SolutionSet<CophylogenyReconstruction> initializeSolutionSet() {
			SolutionSet<CophylogenyReconstruction> reconstruction = new SolverSet<CophylogenyReconstruction>();
			return reconstruction;
		}

		@Override
		protected CophylogenyReconstruction solveInstance(CoevolutionaryHistory history) {
			ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(history, new int[] {0, 1, 1, 2});
			
			ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
			
			TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(history, list);
			eventCounter.getEventCounts();
			
			CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(history.getHostTree(), eventCounter.getHostMapping(), list, eventCounter.getEventCounts());
			return reconstruction;
		}		
	}	
}
