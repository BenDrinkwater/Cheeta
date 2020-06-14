package util;

import reconstruction.CophylogenyReconstruction;

public interface SolutionComparable extends Comparable<SolutionComparable> {

	public int getCost();
	
	public int getNumberOfCodivergenceEvents();

	public int compareTo(CophylogenyReconstruction reconstruction);
	
	public SolutionComparable getClonedSolution();
}
