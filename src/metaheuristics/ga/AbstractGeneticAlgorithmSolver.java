package metaheuristics.ga;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import tree.Tree;
import util.SolutionSet;

public abstract class AbstractGeneticAlgorithmSolver implements GeneticAlgoritmSolver {

	protected final CoevolutionaryHistory initialHistory;
	protected final int populationSize;
	protected final int[] costScheme;
	protected final boolean multiThreadingEnabled;
	protected SolutionSet<CophylogenyReconstruction> solutions;
	private ExecutorService pool;
	
	public AbstractGeneticAlgorithmSolver(final CoevolutionaryHistory initialHistory, 
			final int populationSize, final int[] costScheme, final boolean multiThreadingEnabled) {
		this.initialHistory = initialHistory;
		this.populationSize = populationSize;
		this.costScheme = costScheme;
		this.multiThreadingEnabled = multiThreadingEnabled;
		this.solutions = null;
		this.pool = null;
	}
	
	protected abstract SolutionSet<CophylogenyReconstruction> initializeSolutionSet();
	
	protected abstract CophylogenyReconstruction solveInstance(CoevolutionaryHistory history);
	
	protected abstract SolverThread getSolverThread(CoevolutionaryHistory history, int populationSize, int[] costScheme, SolutionSet<CophylogenyReconstruction> solutions);
	
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
		ArrayList<CoevolutionaryHistory> populations = this.generateInitalPopulations();
		return this.solve(this.solutions, numberOfIterations, populations);
	}
	
	protected SolutionSet<CophylogenyReconstruction> solve(SolutionSet<CophylogenyReconstruction> solutions, int numberOfIterations, ArrayList<CoevolutionaryHistory> populations) {
		if (this.multiThreadingEnabled) {
			return solve(solutions, numberOfIterations);
		}
		else {
			for (int i = 0; i < numberOfIterations; ++i) {
				populations = this.generateNextPopulationSet(solutions);
				for (CoevolutionaryHistory population : populations) {
					if (null == population) {
						continue;
					}
					CophylogenyReconstruction reconstruction = this.solveInstance(population.cloneHistory());
					if (TreeTimingCrosser.validNodeTiming(reconstruction.getHostTree().getNodeTimings())) {
						solutions.add(reconstruction);	
					}
				}
		} 
		}
		return solutions;		
	}
	
	protected SolutionSet<CophylogenyReconstruction> solve(SolutionSet<CophylogenyReconstruction> solutions, int numberOfIterations) {
		for (int i = 0; i < Math.max(1, Math.ceil(numberOfIterations / Runtime.getRuntime().availableProcessors())); ++i) {
			this.pool = Executors.newFixedThreadPool(Math.min(8, Runtime.getRuntime().availableProcessors()));
			ArrayList<SolverThread> threads = new ArrayList<SolverThread>();
			for (int j = 0; j < Math.min(8, Runtime.getRuntime().availableProcessors()); ++j) {
				threads.add(this.getSolverThread(this.initialHistory.cloneHistory(), this.populationSize, this.costScheme, solutions.cloneSolutionSet()));
			}
			for (SolverThread thread : threads) {
				this.pool.execute(thread);
			}
			this.pool.shutdown();
			try {
				if (this.pool.awaitTermination(1, TimeUnit.HOURS)) {
					for (SolverThread thread : threads) {
						solutions.add(thread.getResults());
					}
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		return solutions;		
	}		
	
	protected ArrayList<CoevolutionaryHistory> generateNextPopulationSet(SolutionSet<CophylogenyReconstruction> previousSolution) {
		CophylogenyReconstruction first = null;
		CophylogenyReconstruction second = null; 
		if (null == previousSolution) {
			throw new SolutionSetException("Null SolutionSet");
		}
		else if (0 == previousSolution.size()) {
			throw new SolutionSetException("Previous Solution is empty");
		}
		else if (1 == previousSolution.size()) {
			first = previousSolution.getFirstElement();
			second = previousSolution.getFirstElement();				
		}
		else {
			first = previousSolution.getFirstElement();
			second = previousSolution.getSecondElement();				
		}
		
		Tree x = first.getHostTree().cloneTree();
		Tree y = second.getHostTree().cloneTree();	
		
		TreeTimingCrosser evolver = new TreeTimingCrosser(x, y);
		Tree nextHostTree = evolver.getCrossedTree();
		
		PopulationEvolver populationEvolver = new PopulationEvolver(nextHostTree, this.initialHistory.getParasiteTree(), this.initialHistory.getAssociations(), this.populationSize);
		
		ArrayList<CoevolutionaryHistory> nextPopulation = populationEvolver.getNextHistories();
		while(nextPopulation.size() < this.populationSize) {
			nextPopulation.add(new CoevolutionaryHistory(x, this.initialHistory.getParasiteTree(), this.initialHistory.getAssociations()));
			nextPopulation.add(new CoevolutionaryHistory(y, this.initialHistory.getParasiteTree(), this.initialHistory.getAssociations()));
		}
		
		return nextPopulation;
	}		
}
