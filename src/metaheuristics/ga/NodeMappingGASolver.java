package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.nm.NodeMappingEventCounter;
import reconstruction.solver.nm.ReconstructionGenerator;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;
import util.SolverSet;

public class NodeMappingGASolver extends AbstractGeneticAlgorithmSolver implements GeneticAlgoritmSolver {
	
	public NodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, boolean multiThreaded) {
		super(history, numberOfPopulations, new int[] {0, 1, 1, 2}, multiThreaded);
	}
	
	public NodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, int[] costScheme, boolean multiThreaded) {
		super(history, numberOfPopulations, costScheme, multiThreaded);
	}

	@Override
	protected SolutionSet<CophylogenyReconstruction> initializeSolutionSet() {
		SolutionSet<CophylogenyReconstruction> reconstruction = new SolverSet<CophylogenyReconstruction>();
		return reconstruction;
	}

	@Override
	protected CophylogenyReconstruction solveInstance(final CoevolutionaryHistory history) {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGenerator(history, super.costScheme);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		
		NodeMappingEventCounter eventCounter = new NodeMappingEventCounter(history, list, super.costScheme);
		eventCounter.getEventCounts();
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(history.getHostTree(), eventCounter.getHostMapping(), list, eventCounter.getEventCounts());
		return reconstruction;
	}

	@Override
	protected SolverThread getSolverThread(CoevolutionaryHistory history,
			int populationSize, int[] costScheme,
			SolutionSet<CophylogenyReconstruction> solutions) {
		return new NodeMappingSolverThread(history, populationSize, costScheme, solutions);
	}
	
	private class NodeMappingSolverThread extends SolverThread {

		public NodeMappingSolverThread(CoevolutionaryHistory initialHistory,
				int populationSize, int[] costScheme,
				SolutionSet<CophylogenyReconstruction> solutions) {
			super(initialHistory, populationSize, costScheme, solutions);
		}

		@Override
		protected GeneticAlgoritmSolver getSolver() {
			return new NodeMappingGASolver(super.initialHistory, super.populationSize, super.costScheme, false);		
		}		
	}
}
