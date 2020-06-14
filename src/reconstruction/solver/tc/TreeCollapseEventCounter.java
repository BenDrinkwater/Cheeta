package reconstruction.solver.tc;

import java.util.LinkedHashMap;
import java.util.Map;

import reconstruction.Events;
import reconstruction.HostMappingList;
import reconstruction.ParasiteMappingList;
import tanglegram.CoevolutionaryHistory;

public class TreeCollapseEventCounter {

	protected CoevolutionaryHistory history;
	protected Map<String, Integer> events;
	protected LossCounter lossCounter;
	protected ParasiteMappingList parasiteMapping;
	
	private TreeCollapseEventCounter() {
		this.lossCounter = null;
	}
	
	public TreeCollapseEventCounter(CoevolutionaryHistory history, ParasiteMappingList parasiteMapping) {	
		this();
		this.history = history;
		this.parasiteMapping = parasiteMapping;
		this.events = new LinkedHashMap<String, Integer>(this.parasiteMapping.getEvents());
	}
	
	public Events getEventCounts() {
		this.lossCounter = new LossCounter(this.history, this.parasiteMapping);
		this.events.put("Loss", this.lossCounter.countNumberOfLossEvents());
		return new Events(this.events, new int[] {0, 1, 1, 2});
	}

	public HostMappingList getHostMapping() {
		this.getEventCounts();
		return this.lossCounter.getHostMapping();
	}
}
