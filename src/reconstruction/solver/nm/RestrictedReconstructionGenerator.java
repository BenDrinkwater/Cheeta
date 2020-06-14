package reconstruction.solver.nm;

import java.util.Set;

import reconstruction.LooseParasiteMapping;
import tanglegram.CoevolutionaryHistory;
import util.reconstruction.ReconstructionSet;
import util.reconstruction.RestrictedGreedyReconstructionSet;
import util.reconstruction.RestrictedHybridReconstructionSet;
import util.reconstruction.RestrictedReconstructionSet;

public class RestrictedReconstructionGenerator extends ReconstructionGenerator {

	public enum RestrictionMode {
		Greedy,
		Hybrid,
		Random
	}
	
	protected RestrictedReconstructionSet.Size restrictedSize;
	protected RestrictionMode restrictionMode;
	
	public RestrictedReconstructionGenerator(CoevolutionaryHistory history,	int[] costs, RestrictedReconstructionSet.Size restrictedSize) {
		super(history, costs);
		this.restrictedSize = restrictedSize;
		this.restrictionMode = RestrictionMode.Random;
	}
	
	public RestrictedReconstructionGenerator(CoevolutionaryHistory history,	int[] costs, RestrictionMode restrictionMode, RestrictedReconstructionSet.Size restrictedSize) {
		this(history, costs, restrictedSize);
		this.restrictionMode = restrictionMode;
	}

	protected ReconstructionSet getReconstructionSet() {
		switch(this.restrictionMode) {
			case Greedy : return new RestrictedGreedyReconstructionSet(this.restrictedSize, super.history.getHostTree().size());	
			case Hybrid : return new RestrictedHybridReconstructionSet(this.restrictedSize, super.history.getHostTree().size());	
			case Random : return new RestrictedReconstructionSet(this.restrictedSize, super.history.getHostTree().size());	
			default : return null;
		}		
	}
	
	protected Set<LooseParasiteMapping> returnReconstructionSet(Set<LooseParasiteMapping> currentSet) {
		return ((RestrictedReconstructionSet) currentSet).boundSize();
	}	
}