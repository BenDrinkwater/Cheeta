package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import reconstruction.solver.CophylogenyMapFinder;
import reconstruction.solver.TreeCollapse;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;
import util.SolverSet;

public class TreeCollapseGASolver extends AbstractGeneticAlgorithmSolver implements Solver {

	public TreeCollapseGASolver(CoevolutionaryHistory history, int numberOfPopulations, boolean multiThreaded) {
		super(history, numberOfPopulations, new int[] {0, 1, 1, 2}, multiThreaded);
	}
	
	public TreeCollapseGASolver(CoevolutionaryHistory history, int numberOfPopulations, int[] costScheme, boolean multiThreaded) {
		super(history, numberOfPopulations, costScheme, multiThreaded);
	}

	@Override
	protected SolutionSet<CophylogenyReconstruction> initializeSolutionSet() {
		SolutionSet<CophylogenyReconstruction> reconstruction = new SolverSet<CophylogenyReconstruction>();
		return reconstruction;
	}
	
	protected CophylogenyReconstruction solveInstance(CoevolutionaryHistory history) {
		CophylogenyMapFinder treeCollapse = new TreeCollapse(history);
		CophylogenyReconstruction reconstruction = treeCollapse.getCophylogenyMappingReconstruction();		
  		return reconstruction;
	}

	@Override
	protected SolverThread getSolverThread(CoevolutionaryHistory history,
			int populationSize, int[] costScheme,
			SolutionSet<CophylogenyReconstruction> solutions) {
		return new TreeCollapseSolverThread(history, populationSize, costScheme, solutions);
	}
	
	private class TreeCollapseSolverThread extends SolverThread {

		public TreeCollapseSolverThread(CoevolutionaryHistory initialHistory,
				int populationSize, int[] costScheme,
				SolutionSet<CophylogenyReconstruction> solutions) {
			super(initialHistory, populationSize, costScheme, solutions);
		}

		@Override
		protected Solver getSolver() {
			return new TreeCollapseGASolver(super.initialHistory, super.populationSize, false);		
		}		
	}	
}
