package util.reconstruction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import reconstruction.LooseParasiteMapping;

public class RestrictedGreedyReconstructionSet extends RestrictedReconstructionSet {

	public RestrictedGreedyReconstructionSet(Size restrictedSize, int size) {
		super(restrictedSize, size);
	}

	public RestrictedReconstructionSet boundSize() {
		if (super.size() <= this.maxReconstructionSet) {
			return this;
		}
		List<LooseParasiteMapping> subset = pickNGreedy(new LinkedList<LooseParasiteMapping>(super.container.values()), this.maxReconstructionSet);
		super.clear();
		super.addAll(subset);
		return this;
	}
	
	protected static List<LooseParasiteMapping> pickNGreedy(List<LooseParasiteMapping> values, int n) {
	    List<LooseParasiteMapping> copy = new LinkedList<LooseParasiteMapping>(values);
	    Collections.sort(copy);
	    return copy.subList(0, n);
	}	
}
