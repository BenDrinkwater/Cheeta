package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;

public abstract class SolverThread implements Runnable {
	
	protected final CoevolutionaryHistory initialHistory;
	protected final int populationSize;
	protected final int[] costScheme;
	protected SolutionSet<CophylogenyReconstruction> solutions;
	
	public SolverThread(final CoevolutionaryHistory initialHistory, 
			final int populationSize, final int[] costScheme, 
			SolutionSet<CophylogenyReconstruction> solutions) {
		this.initialHistory = initialHistory;
		this.populationSize = populationSize;
		this.costScheme = costScheme;
		this.solutions = solutions;
	}

	@Override
	public void run() {
		AbstractGeneticAlgorithmSolver solver = (AbstractGeneticAlgorithmSolver) this.getSolver();
		solver.initialize();
		this.solutions = solver.solve(this.solutions, 1, null);
	}

	protected abstract GeneticAlgoritmSolver getSolver();

	public SolutionSet<CophylogenyReconstruction> getResults() {
		return this.solutions;
	}	
}