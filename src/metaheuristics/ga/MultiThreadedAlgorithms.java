package metaheuristics.ga;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import tree.Tree;
import util.SolutionSet;

public abstract class MultiThreadedAlgorithms implements GeneticAlgoritmSolver {

	private final CoevolutionaryHistory initialHistory;
	private final int populationSize;
	protected final int[] costScheme;
	private SolutionSet<CophylogenyReconstruction> solutions;
	private ExecutorService pool;
	
	public MultiThreadedAlgorithms(final CoevolutionaryHistory initialHistory, 
			final int populationSize, final int[] costScheme) {
		this.initialHistory = initialHistory;
		this.populationSize = populationSize;
		this.costScheme = costScheme;
		this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	
	protected abstract SolutionSet<CophylogenyReconstruction> initializeSolutionSet();
	
	protected abstract CophylogenyReconstruction solveInstance(CoevolutionaryHistory history);
	
	protected static SingleThreadedGenerticAlgorithmSolver getSingleThreadedSolver(
			final CoevolutionaryHistory initialHistory, final int populationSize, final int[] costScheme) {
		return null;
	}
	
	public SolutionSet<CophylogenyReconstruction> initialize() {
		this.solutions = this.initializeSolutionSet();
		
		ArrayList<CoevolutionaryHistory> populations = this.generateInitalPopulations();
		
		for (CoevolutionaryHistory population : populations) {
			CophylogenyReconstruction reconstruction = this.solveInstance(population);
			this.solutions.add(reconstruction);
		}		
		return this.solutions;
	}
	
	private ArrayList<CoevolutionaryHistory> generateInitalPopulations() {
		ArrayList<CoevolutionaryHistory> population = new ArrayList<CoevolutionaryHistory>();
		
		while(population.size() < this.populationSize) {
			Tree hostTree = this.initialHistory.getHostTree().cloneTree();
			hostTree.setRandomUniqueNodeTimings();	
			CoevolutionaryHistory history = new CoevolutionaryHistory(hostTree, this.initialHistory.getParasiteTree().cloneTree(), this.initialHistory.getAssociations());
			population.add(history);
		}		
		
		return population;
	}
	
	public SolutionSet<CophylogenyReconstruction> solve(int numberOfIterations) {
		return this.solve(this.solutions, numberOfIterations);
	}
	
	protected SolutionSet<CophylogenyReconstruction> solve(SolutionSet<CophylogenyReconstruction> solutions, int numberOfIterations) {
		ArrayList<CoevolutionaryHistory> populations = this.generateInitalPopulations();
		for (int i = 0; i < numberOfIterations / Runtime.getRuntime().availableProcessors(); ++i) {
			ArrayList<SolvingThread> threads = new ArrayList<SolvingThread>();
			for (int j = 0; j < Runtime.getRuntime().availableProcessors(); ++j) {
				threads.add(new SolvingThread(this.initialHistory, this.populationSize, this.costScheme, this.solutions.cloneSolutionSet(), populations));
			}
			for (SolvingThread thread : threads) {
				this.pool.execute(thread);
			}
			this.pool.shutdown();
			try {
				if (this.pool.awaitTermination(1, TimeUnit.HOURS)) {
					for (SolvingThread thread : threads) {
						this.solutions.add(thread.getResults());
					}
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		} 
		return solutions;		
	}	
	
	public class SolvingThread implements Runnable {
		
		private final CoevolutionaryHistory initialHistory;
		private final int populationSize;
		protected final int[] costScheme;
		private SolutionSet<CophylogenyReconstruction> solutions;
		private final ArrayList<CoevolutionaryHistory> population;
		
		public SolvingThread(final CoevolutionaryHistory initialHistory, 
				final int populationSize, final int[] costScheme, 
				SolutionSet<CophylogenyReconstruction> solutions,
				final ArrayList<CoevolutionaryHistory> population) {
			this.initialHistory = initialHistory;
			this.populationSize = populationSize;
			this.costScheme = costScheme;
			this.solutions = solutions;
			this.population = population;
		}

		@Override
		public void run() {
			// move to this late so that the inherited version of MultiThreadedAlgorithms can override this
			//SingleThreadedGenerticAlgorithmSolver solver = MultiThreadedAlgorithms.getSingleThreadedSolver(this.initialHistory, this.populationSize, this.costScheme)
			SingleThreadedGenerticAlgorithmSolver solver = new SingleThreadedSolver(this.initialHistory, this.populationSize);
			solver.initialize();
			this.solutions = solver.solve(this.solutions, 1, this.population);
		}
		
		public SolutionSet<CophylogenyReconstruction> getResults() {
			return this.solutions;
		}
		
	}
	
}
