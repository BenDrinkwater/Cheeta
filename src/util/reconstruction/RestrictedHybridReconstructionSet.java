package util.reconstruction;

import java.util.LinkedList;
import java.util.List;

import reconstruction.LooseParasiteMapping;

public class RestrictedHybridReconstructionSet extends RestrictedGreedyReconstructionSet {

	private double percentageOfGreedy;
	
	public RestrictedHybridReconstructionSet(Size restrictedSize, int size) {
		super(restrictedSize, size);
		this.percentageOfGreedy = 0.5;
	}
	
	public RestrictedHybridReconstructionSet(Size restrictedSize, int size, double percentage) {
		super(restrictedSize, size);
		this.percentageOfGreedy = percentage;
	}
	
	public RestrictedReconstructionSet boundSize() {
		if (super.size() <= this.maxReconstructionSet) {
			return this;
		}
		
		List<LooseParasiteMapping> subset = pickNGreedy(new LinkedList<LooseParasiteMapping>(super.container.values()), (int) (this.maxReconstructionSet * this.percentageOfGreedy));
		subset.addAll(pickNRandom(new LinkedList<LooseParasiteMapping>(super.container.values()), (int) (this.maxReconstructionSet * (1 - this.percentageOfGreedy))));
		
		super.clear();
		super.addAll(subset);
		return this;
	}
}
