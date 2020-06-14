package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import util.SolutionSet;

public interface Solver {

	/**
	 * Establishes the initial population and solves this. 
	 * @return the solutions from the initialization run
	 */
	public SolutionSet<CophylogenyReconstruction> initialize();
	
	/**
	 * Solves the Cophylogeny Reconstruction problem over a set number of iterations on the initial population
	 * 
	 * @param numberOfIterations the number of iterations to run on the problem set before returning a set of solutions
	 * @return a solution set of cophylogeny reconstructions. This is a set of the best solutions found during the genetic algorithms exploration
	 */
	public SolutionSet<CophylogenyReconstruction> solve(int numberOfIterations);
}
