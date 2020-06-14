package reconstruction.solver;

import reconstruction.solver.tc.ReconstructionGenerator;
import reconstruction.solver.rp.RightPush;
import reconstruction.solver.tc.TreeCollapseEventCounter;
import tanglegram.CoevolutionaryHistory;

public class TreeCollapse extends CophylogenyMapFinder {
	
	public TreeCollapse(CoevolutionaryHistory history) {
		super(history);
	}
	
	protected void generateParasiteMapping() {
		ReconstructionGenerator reconstructionGenerator 
				= new ReconstructionGenerator(super.history);
		RightPush rightPush = new RightPush(super.history, reconstructionGenerator.generatePartialReconstruction());
		super.parasiteMapping = rightPush.getLossMinimisedParasiteMapping();
	}
	
	protected void setEvents() {
		TreeCollapseEventCounter eventCounter = new TreeCollapseEventCounter(super.history, super.parasiteMapping);
		super.events = eventCounter.getEventCounts();
		super.hostMapping = eventCounter.getHostMapping();
	}	
}
