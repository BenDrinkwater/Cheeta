package util.reconstruction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import reconstruction.LooseParasiteMapping;

public class RestrictedReconstructionSet extends ReconstructionSet {

	public enum Size {
		Linear,
		DividedByLog,
		SquareRoot,
		Log,
		Constant4,
		Constant
	}
	
	protected int maxReconstructionSet;
	
	public RestrictedReconstructionSet(Size restrictedSize, int hostTreeSize)	{
		this.maxReconstructionSet = (int) getReconstructionSetSize(restrictedSize, hostTreeSize);
	}
	
	private static double getReconstructionSetSize(Size restrictedSize, int hostTreeSize) {
		switch(restrictedSize) {
			case Linear: return hostTreeSize;
			case DividedByLog: return Math.ceil(hostTreeSize / logBase2(Math.max(1, hostTreeSize)));
			case SquareRoot: return Math.ceil(Math.sqrt(hostTreeSize));
			case Log: return Math.ceil(logBase2(Math.max(1, hostTreeSize)));
			case Constant4: return 4;
			case Constant: return 1;
			default : return hostTreeSize;
		}
	}
	
	private static double logBase2(int value){
		return Math.log(value) / Math.log(2);
	}
	
	public RestrictedReconstructionSet boundSize() {
		if (super.size() <= this.maxReconstructionSet) {
			return this;
		}
		List<LooseParasiteMapping> subset = pickNRandom(new LinkedList<LooseParasiteMapping>(super.container.values()), this.maxReconstructionSet);
		super.clear();
		super.addAll(subset);
		return this;
	}
	
	protected static List<LooseParasiteMapping> pickNRandom(List<LooseParasiteMapping> values, int n) {
	    List<LooseParasiteMapping> copy = new LinkedList<LooseParasiteMapping>(values);
	    Random random = new Random(System.nanoTime());
	    Collections.shuffle(copy, random);
	    return copy.subList(0, n);
	}
}
