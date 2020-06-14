package reconstruction;

import util.Pair;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import tree.Edge;
import tree.Node;

public class ParasiteMappingList implements Iterable<Node> {

	protected Map<Node, MappingPoint> parasiteMapping;
	protected Map<String, Integer> events;
	
	public ParasiteMappingList() {
		this.parasiteMapping = new LinkedHashMap<Node, MappingPoint>();
		this.events = new LinkedHashMap<String, Integer>();
		this.events.put("Codivergence", 0);
		this.events.put("Duplication", 0);
		this.events.put("Host Switch", 0);
	}
	
	private ParasiteMappingList(Map<Node, MappingPoint> parasiteMapping, Map<String, Integer> events) {
		this.parasiteMapping = new LinkedHashMap<Node, MappingPoint> (parasiteMapping);
		this.events = new LinkedHashMap<String, Integer>(events);
	}

	public void add(Node parasiteNode, MappingPoint mappingPoint) {
		if (null != mappingPoint && !(this.parasiteMapping.containsKey(parasiteNode))) {
			mappingPoint = this.duplicationCorrection(mappingPoint);
			switch(mappingPoint.getMappedTo()) {
				case Node: this.events.put("Codivergence", this.events.get("Codivergence") + 1);
					break;
				case Edge: this.events.put("Duplication", this.events.get("Duplication") + 1);
					break;
				case HostSwitchPair: this.events.put("Host Switch", this.events.get("Host Switch") + 1);
					break;
			}
			this.parasiteMapping.put(parasiteNode, mappingPoint);
		}
	}
	
	@SuppressWarnings("unchecked")
	private MappingPoint duplicationCorrection(MappingPoint mappingPoint) {
		if (mappingPoint.mappingPointIsHostSwitchPair()) {
			Pair<Edge,Edge> hostSwitch = (Pair<Edge, Edge>) mappingPoint.getMappedPoint();
			if (hostSwitch.getFirst().equals(hostSwitch.getSecond())) {
				return new MappingPoint(hostSwitch.getFirst());
			}
		}
		return mappingPoint;
	}
	
	public void addLeafNode(Node parasiteNode, MappingPoint mappingPoint) {
		this.parasiteMapping.put(parasiteNode, mappingPoint);
	}
	
	public Node hostNodeMappedTo(Node parasiteNode) {
		return this.parasiteMapping.get(parasiteNode).getMappedPointAsNode();
	}
	
	public MappingPoint getMappedPoint(Node parasiteNode) {
		return this.parasiteMapping.get(parasiteNode);
	}
	
	public boolean isParasiteMappedToHostNode(Node parasiteNode) {
		return MappingPoint.MappedPoint.Node == this.parasiteMapping.get(parasiteNode).getMappedTo();
	}
	
	public boolean isParasiteMappedToHostEdge(Node parasiteNode) {
		return MappingPoint.MappedPoint.Edge == this.parasiteMapping.get(parasiteNode).getMappedTo();
	}	
	
	public boolean isParasiteMappedToHostEdgeAndHostSwitchEvent(Node parasiteNode) {
		return MappingPoint.MappedPoint.HostSwitchPair == this.parasiteMapping.get(parasiteNode).getMappedTo();
	}		
	
	public void updateHostSwitchEvent(Node parasiteNode, MappingPoint mappingPoint) {
		if (this.parasiteMapping.containsKey(parasiteNode)) {
			this.parasiteMapping.put(parasiteNode, mappingPoint);
		}
	}
	
	public void updateLandingEdgeForHostSwitchEvent(Node parasiteNode, Edge newLandingEdge) {
		MappingPoint mappingPoint = this.getMappedPoint(parasiteNode);
		@SuppressWarnings("unchecked")
		Pair<Edge, Edge> hostSwitchMapping = (Pair<Edge, Edge>) mappingPoint.getMappedPoint();
		this.parasiteMapping.put(parasiteNode, new MappingPoint(new Pair<Edge, Edge> (hostSwitchMapping.getFirst(), newLandingEdge)));
	}
	
	@Override
	public Iterator<Node> iterator() {
		return this.parasiteMapping.keySet().iterator();
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<Node, MappingPoint> mapEntry : this.parasiteMapping.entrySet()) {
			stringBuilder.append(mapEntry.getKey().toString() + "=>" + mapEntry.getValue() + "\n");
		}
		return stringBuilder.toString();
	}
	
	public String toStringNoNewLines() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<Node, MappingPoint> mapEntry : this.parasiteMapping.entrySet()) {
			stringBuilder.append(mapEntry.getKey().toString() + "=>" + mapEntry.getValue());
		}
		return stringBuilder.toString();
	}
	
	public Map<String, Integer> getEvents() {
		return this.events;
	}
	
	public int size() {
		return this.parasiteMapping.size();
	}
	
	public ParasiteMappingList getClonedParasiteMappingList() {
		return new ParasiteMappingList(this.parasiteMapping, this.events);
	}
}
