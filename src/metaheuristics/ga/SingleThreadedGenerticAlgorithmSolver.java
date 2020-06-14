package metaheuristics.ga;

import java.util.ArrayList;

import reconstruction.CophylogenyReconstruction;
import tanglegram.CoevolutionaryHistory;
import tree.Tree;
import util.SolutionSet;

public abstract class SingleThreadedGenerticAlgorithmSolver implements Solver {

	private final CoevolutionaryHistory initialHistory;
	private final int populationSize;
	protected final int[] costScheme;
	private SolutionSet<CophylogenyReconstruction> solutions;
	
	public SingleThreadedGenerticAlgorithmSolver(final CoevolutionaryHistory initialHistory, 
			final int populationSize, final int[] costScheme) {
		this.initialHistory = initialHistory;
		this.populationSize = populationSize;
		this.costScheme = costScheme;
	}
	
	protected abstract SolutionSet<CophylogenyReconstruction> initializeSolutionSet();
	
	protected abstract CophylogenyReconstruction solveInstance(CoevolutionaryHistory history);
	
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
		return this.solve(solutions, numberOfIterations, populations);
	}
	
	protected SolutionSet<CophylogenyReconstruction> solve(SolutionSet<CophylogenyReconstruction> solutions, int numberOfIterations, ArrayList<CoevolutionaryHistory> populations) {
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
