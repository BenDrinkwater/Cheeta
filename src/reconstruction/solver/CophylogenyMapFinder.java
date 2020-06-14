package reconstruction.solver;

import reconstruction.CophylogenyReconstruction;
import reconstruction.Events;
import reconstruction.HostMappingList;
import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;

public abstract class CophylogenyMapFinder {

	protected CoevolutionaryHistory history;
	protected ParasiteMappingList parasiteMapping;
	protected HostMappingList hostMapping;
	protected Events events;	
	
	public CophylogenyMapFinder(CoevolutionaryHistory history) {
		this.history = history;
		//this.history.getHostTree().setTreeCache();
		//this.history.getParasiteTree().setTreeCache();
	}
	
	public CophylogenyReconstruction getCophylogenyMappingReconstruction() {
		this.generateParasiteMapping();
		this.setEvents();
		return new CophylogenyReconstruction(this.history.getHostTree(), this.hostMapping, this.parasiteMapping, this.events);				
	}
	
	protected abstract void generateParasiteMapping();
	
	protected abstract void setEvents();
	
}
