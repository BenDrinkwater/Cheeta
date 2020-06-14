package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.nm.ReconstructionGenerator;
import reconstruction.solver.tc.TreeCollapseEventCounter;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;
import util.SolverSet;

public class SingleThreadedSolver extends SingleThreadedGenerticAlgorithmSolver {

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
