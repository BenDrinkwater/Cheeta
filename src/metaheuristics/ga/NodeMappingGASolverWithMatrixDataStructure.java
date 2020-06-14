package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.nm.NodeMappingEventCounter;
import reconstruction.solver.nm.ReconstructionGenerator;
import reconstruction.solver.nm.ReconstructionGeneratorWithMatrix;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;

public class NodeMappingGASolverWithMatrixDataStructure extends NodeMappingGASolver {

	public NodeMappingGASolverWithMatrixDataStructure(CoevolutionaryHistory history, int numberOfPopulations, boolean multiThreaded) {
		super(history, numberOfPopulations, multiThreaded);
	}
	
	public NodeMappingGASolverWithMatrixDataStructure(CoevolutionaryHistory history, int numberOfPopulations, int[] costScheme, boolean multiThreaded) {
		super(history, numberOfPopulations, costScheme, multiThreaded);
	}
	
	@Override
	protected CophylogenyReconstruction solveInstance(final CoevolutionaryHistory history) {
		ReconstructionGenerator reconstructionGenerator = new ReconstructionGeneratorWithMatrix(history, super.costScheme);
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
			return new NodeMappingGASolverWithMatrixDataStructure(super.initialHistory, super.populationSize, super.costScheme, false);		
		}		
	}	
}
