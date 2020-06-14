package metaheuristics.ga;

import reconstruction.CophylogenyReconstruction;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.nm.NodeMappingEventCounter;
import reconstruction.solver.nm.RestrictedReconstructionGenerator;
import reconstruction.solver.nm.RestrictedReconstructionGenerator.RestrictionMode;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;
import util.reconstruction.RestrictedReconstructionSet;

public class SamplingNodeMappingGASolver extends NodeMappingGASolver {

	protected RestrictedReconstructionSet.Size restrictedSize;
	protected RestrictionMode restrictionMode;
	
	public SamplingNodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, 
			boolean multiThreaded, RestrictedReconstructionSet.Size restrictedSize) {
		super(history, numberOfPopulations, multiThreaded);
		this.restrictedSize = restrictedSize;
		this.restrictionMode = RestrictionMode.Random;
	}

	public SamplingNodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, int[] costScheme, 
			boolean multiThreaded, RestrictedReconstructionSet.Size restrictedSize) {
		super(history, numberOfPopulations, costScheme, multiThreaded);
		this.restrictedSize = restrictedSize;
		this.restrictionMode = RestrictionMode.Random;
	}
	
	public SamplingNodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, 
			boolean multiThreaded, RestrictedReconstructionSet.Size restrictedSize, RestrictionMode restrictionMode) {
		super(history, numberOfPopulations, multiThreaded);
		this.restrictedSize = restrictedSize;
		this.restrictionMode = restrictionMode;
	}

	public SamplingNodeMappingGASolver(CoevolutionaryHistory history, int numberOfPopulations, int[] costScheme, 
			boolean multiThreaded, RestrictedReconstructionSet.Size restrictedSize, RestrictionMode restrictionMode) {
		super(history, numberOfPopulations, costScheme, multiThreaded);
		this.restrictedSize = restrictedSize;
		this.restrictionMode = restrictionMode;
	}
	
	@Override
	protected CophylogenyReconstruction solveInstance(final CoevolutionaryHistory history) {
		RestrictedReconstructionGenerator reconstructionGenerator = new RestrictedReconstructionGenerator(history, super.costScheme, this.restrictionMode, this.restrictedSize);
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
		return new SamplingNodeMappingSolverThread(history, populationSize, costScheme, solutions, this.restrictedSize, this.restrictionMode);
	}
	
	private class SamplingNodeMappingSolverThread extends SolverThread {

		private RestrictedReconstructionSet.Size restrictedSize;
		private RestrictionMode restrictionMode;
		
		public SamplingNodeMappingSolverThread(CoevolutionaryHistory initialHistory, 
				int populationSize, int[] costScheme, SolutionSet<CophylogenyReconstruction> solutions, 
				RestrictedReconstructionSet.Size restrictedSize, RestrictionMode restrictionMode) {
			super(initialHistory, populationSize, costScheme, solutions);
			this.restrictedSize = restrictedSize;
			this.restrictionMode = restrictionMode;
		}

		@Override
		protected Solver getSolver() {
			return new SamplingNodeMappingGASolver(super.initialHistory, super.populationSize, super.costScheme, false, this.restrictedSize, this.restrictionMode);		
		}		
	}
	
}
