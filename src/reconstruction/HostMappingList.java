package reconstruction;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tree.Node;

public class HostMappingList implements Iterable<Node> {

	protected Map<Node, Integer> lossEvents;
	
	public HostMappingList() {
		this.lossEvents = new LinkedHashMap<Node, Integer>();
	}
	
	private HostMappingList(Map<Node, Integer> lossEvents) {
		this.lossEvents = new LinkedHashMap<Node, Integer>(lossEvents);
	}
	
	public HostMappingList(HostMappingList list) {
		this(list.lossEvents);
	}
	
	public boolean add(Node hostNode) {
		if (this.lossEvents.containsKey(hostNode)) {
			this.lossEvents.put(hostNode, this.lossEvents.get(hostNode) + 1);
		}
		else {
			this.lossEvents.put(hostNode, 1);
		}
		return true;
	}
	
	public boolean remove(Node hostNode) {
		if (this.lossEvents.containsKey(hostNode)) {
			this.lossEvents.put(hostNode, this.lossEvents.get(hostNode) - 1);
		}
		return true;
	}
	
	public boolean add(List<Node> hostNodes) {
		for (Node hostNode : hostNodes) {
			this.add(hostNode);
		}
		return true;
	}
	
	public boolean add(HostMappingList widespreadLossRecords) {
		for (Node hostNode : widespreadLossRecords) {
			int numbderOfLossEventsAtNode = widespreadLossRecords.numberOfLossEventsAtNode(hostNode);
			for (int i = 0; i < numbderOfLossEventsAtNode; ++i) {
				this.add(hostNode);
			}
		}
		return true;
	}
	
	public Iterator<Node> iterator() {
		return this.lossEvents.keySet().iterator();
	}
	
	public int numberOfLossEvents() {
		int numberOfLossEvents = 0;
		for (Integer lossEvents : this.lossEvents.values()) {
			numberOfLossEvents += lossEvents;
		}
		return numberOfLossEvents;
	}
	
	public Integer numberOfLossEventsAtNode(Node hostNode) {
		if (!this.lossEvents.containsKey(hostNode)) {
			return 0;
		}
		return this.lossEvents.get(hostNode);
	}
	
	public List<Node> lossSites() {
		List<Node> nodes = new LinkedList<Node>();
		for (Node node : this.lossEvents.keySet()) {
			for (int i = 0; i < (this.lossEvents.containsKey(node) ? this.lossEvents.get(node) : 0); ++i) {
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	public HostMappingList getClonedHostMappingList() {
		return new HostMappingList(this.lossEvents);
	}
	
	public String toString() {
		if (this.lossEvents.size() > 0) {
			StringBuilder stringBuilder = new StringBuilder();
			for (Map.Entry<Node, Integer> mapEntry : this.lossEvents.entrySet()) {
				stringBuilder.append(mapEntry.getKey().toString() + "=>" + mapEntry.getValue() + "\n");
			}
			return stringBuilder.toString();
		}
		else {
			return "No Loss Events in this Reconstruction";
		}
	}	
}
