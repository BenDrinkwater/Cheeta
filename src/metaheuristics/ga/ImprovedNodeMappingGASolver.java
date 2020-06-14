package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.nm.ImprovedReconstructionGenerator;
import reconstruction.solver.nm.NodeMappingEventCounter;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;
import util.SolverSet;

public class ImprovedNodeMappingGASolver extends AbstractGeneticAlgorithmSolver implements GeneticAlgoritmSolver {

	public ImprovedNodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, boolean multiThreaded) {
		super(history, numberOfPopulations, new int[] {0, 1, 1, 2}, multiThreaded);
	}
	
	public ImprovedNodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, int[] costScheme, boolean multiThreaded) {
		super(history, numberOfPopulations, costScheme, multiThreaded);
	}

	@Override
	protected SolutionSet<CophylogenyReconstruction> initializeSolutionSet() {
		SolutionSet<CophylogenyReconstruction> reconstruction = new SolverSet<CophylogenyReconstruction>();
		return reconstruction;
	}

	@Override
	protected CophylogenyReconstruction solveInstance(final CoevolutionaryHistory history) {
		ImprovedReconstructionGenerator reconstructionGenerator = new ImprovedReconstructionGenerator(history, super.costScheme);
		ParasiteMappingList list = reconstructionGenerator.getParasiteMappingList();
		
		NodeMappingEventCounter eventCounter = new NodeMappingEventCounter(history, list);
		eventCounter.getEventCounts();
		
		CophylogenyReconstruction reconstruction = new CophylogenyReconstruction(history.getHostTree(), eventCounter.getHostMapping(), list, eventCounter.getEventCounts());
		return reconstruction;
	}

	@Override
	protected SolverThread getSolverThread(CoevolutionaryHistory history,
			int populationSize, int[] costScheme,
			SolutionSet<CophylogenyReconstruction> solutions) {
		return new ImprovedNodeMappingSolverThread(history, populationSize, costScheme, solutions);
	}
	
	private class ImprovedNodeMappingSolverThread extends SolverThread {

		public ImprovedNodeMappingSolverThread(CoevolutionaryHistory initialHistory,
				int populationSize, int[] costScheme,
				SolutionSet<CophylogenyReconstruction> solutions) {
			super(initialHistory, populationSize, costScheme, solutions);
		}

		@Override
		protected GeneticAlgoritmSolver getSolver() {
			return new ImprovedNodeMappingGASolver(super.initialHistory, super.populationSize, super.costScheme, false);		
		}		
	}
	
}
