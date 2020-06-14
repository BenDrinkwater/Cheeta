package reconstruction.solver.nm;

import java.util.LinkedHashMap;
import java.util.Map;

import reconstruction.Events;
import reconstruction.HostMappingList;
import reconstruction.ParasiteMappingList;
import reconstruction.solver.tc.LossCounter;
import tanglegram.CoevolutionaryHistory;

public class NodeMappingEventCounter {

	protected CoevolutionaryHistory history;
	protected Map<String, Integer> events;
	protected LossCounter lossCounter;
	protected ParasiteMappingList parasiteMapping;
	protected int[] costScheme;
	
	private NodeMappingEventCounter() {
		this.lossCounter = null;
	}
	
	public NodeMappingEventCounter(CoevolutionaryHistory history, ParasiteMappingList parasiteMapping) {
		this(history, parasiteMapping, new int[] {0, 1, 1, 2});
	}
	
	public NodeMappingEventCounter(CoevolutionaryHistory history, ParasiteMappingList parasiteMapping, int[] costScheme) {	
		this();
		this.history = history;
		this.parasiteMapping = parasiteMapping;
		this.events = new LinkedHashMap<String, Integer>(this.parasiteMapping.getEvents());
		this.costScheme = costScheme;
	}
	
	public Events getEventCounts() {
		this.lossCounter = new LossCounter(this.history, this.parasiteMapping);
		this.events.put("Loss", this.lossCounter.countNumberOfLossEvents());
		return new Events(this.events, this.costScheme);
	}

	public HostMappingList getHostMapping() {
		this.getEventCounts();
		return this.lossCounter.getHostMapping();
	}
}
