package reconstruction;

import java.util.LinkedHashMap;
import java.util.Map;

public class Events {
	
	protected Map<String, Integer> events;
	protected int[] costScheme; 
	
	protected Events() {
		this.events = new LinkedHashMap<String, Integer>();
		this.costScheme = null;
	}
	
	public Events(int[] costScheme) {
		this();
		this.costScheme = costScheme;
	}
	
	public Events(Map<String, Integer> events, int[] costScheme) {
		if (null != events) {
			this.events = new LinkedHashMap<String, Integer> (events);
			this.costScheme = new int[costScheme.length];
			for (int i = 0; i < this.costScheme.length; ++i) {
				this.costScheme[i] = costScheme[i];
			}
		}
	}
	
	public Integer getNumberOfCodivergenceEvents() {
		return (null != this.events && this.events.containsKey("Codivergence")) ? this.events.get("Codivergence") : 0;
	}
	
	public Integer getNumberOfDuplicationEvents() {
		return (null != this.events && this.events.containsKey("Duplication")) ? this.events.get("Duplication") : 0;
	}
	
	public Integer getNumberOfHostSwitchEvents() {
		return (null != this.events && this.events.containsKey("Host Switch")) ? this.events.get("Host Switch") : 0;
	}
	
	public Integer getNumberOfLossEvents() {
		return (null != this.events && this.events.containsKey("Loss")) ? this.events.get("Loss") : 0;
	}
	
	public void addCodivergenceEvent() {
		if (null != this.events && this.events.containsKey("Codivergence")) {
			this.events.put("Codivergence", this.events.get("Codivergence") + 1);
		}
		else if (null != this.events) {
			this.events.put("Codivergence", 1);
		}
	}
	
	public void addDuplicationEvent() {
		if (null != this.events && this.events.containsKey("Duplication")) {
			this.events.put("Duplication", this.events.get("Duplication") + 1);
		}
		else if (null != this.events) {
			this.events.put("Duplication", 1);
		}
	}
	
	public void addHostSwitchEvent() {
		if (null != this.events && this.events.containsKey("Host Switch")) {
			this.events.put("Host Switch", this.events.get("Host Switch") + 1);
		}
		else if (null != this.events) {
			this.events.put("Host Switch", 1);
		}
	}
	
	public void addLossEvent() {
		if (null != this.events && this.events.containsKey("Loss")) {
			this.events.put("Loss", this.events.get("Loss") + 1);
		}
		else if (null != this.events) {
			this.events.put("Loss", 1);
		}
	}
	
	public void removeLossEvent() {
		if (null != this.events && this.events.containsKey("Loss")) {
			this.events.put("Loss", this.events.get("Loss") -1);
		}
		else {
			this.events.put("Loss", -1);
		}
	}
	
	public void addEvents(Events copy) {
		if (null != copy) {
			for (Map.Entry<String, Integer> event : copy.events.entrySet()) {
				if (this.events.containsKey(event.getKey())) {
					this.events.put(event.getKey(), event.getValue() + this.events.get(event.getKey()));
				}
				else {
					this.events.put(event.getKey(), event.getValue());
				}
			}
		}
	}
	
	public int getCost() {
		return this.costScheme[0] * this.getNumberOfCodivergenceEvents() + 
				this.costScheme[1] * this.getNumberOfDuplicationEvents() + 
				this.costScheme[2] * this.getNumberOfLossEvents() + 
				this.costScheme[3] * this.getNumberOfHostSwitchEvents();					
	}
	
	public int compare(Events compareTo) {
		if (null != compareTo) {
			
			int thisCost = this.getCost();
			int compareToCost = compareTo.getCost();
			
			return compareToCost - thisCost;
		}
		return 1;
	}
	
	public Events getClonedEvent() {
		return new Events(this.events, this.costScheme);
	}
	
	public String toString() {
		return this.events.toString() + " " + this.getCost();
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
}
