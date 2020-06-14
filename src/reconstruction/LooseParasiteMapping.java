package reconstruction;

import tree.Node;

public class LooseParasiteMapping implements Comparable<LooseParasiteMapping>{

	public enum Event {
		Codivergence,
		Duplication,
		HostSwitchDirection1,
		HostSwitchDirection2,
		FailureToDiverge,
		LeafNodeMapping
	}
	
	protected Event currentEvent;
	protected Event leftChildEvent;
	protected Event rightChildEvent;
	protected MappingPoint leftChildMappingPoint;
	protected MappingPoint rightChildMappingPoint;
	protected MappingPoint mappingPoint;
	protected String mappingPointAsNode;
	protected int costToThisPoint;
	protected int bestForwardPositions;
	
	protected Events events;
	
	protected LooseParasiteMapping() {
		this.costToThisPoint = 0;
		this.leftChildEvent = null;
		this.rightChildEvent = null;
		this.leftChildMappingPoint = null;
		this.rightChildMappingPoint = null;
	}
	
	public LooseParasiteMapping(MappingPoint mappingPoint) {
		this();
		this.mappingPoint = mappingPoint;
		this.mappingPointAsNode = this.mappingPoint.getMappedPointAsNode().toString();
		this.currentEvent = Event.LeafNodeMapping;
	}
	
	public LooseParasiteMapping(MappingPoint mappingPoint, Event event) {
		this(mappingPoint);
		this.currentEvent = event;
	}
	
	public LooseParasiteMapping(LooseParasiteMapping mapping, MappingPoint leaf, int bestForwardPositions) {
		this.currentEvent = mapping.currentEvent;
		this.leftChildEvent = mapping.leftChildEvent;
		this.rightChildEvent = mapping.rightChildEvent;
		this.leftChildMappingPoint = mapping.leftChildMappingPoint;
		this.rightChildMappingPoint = mapping.rightChildMappingPoint;
		this.mappingPoint = leaf;
		this.mappingPointAsNode = mapping.mappingPointAsNode;
		this.costToThisPoint = mapping.costToThisPoint;
		this.bestForwardPositions = bestForwardPositions;
	}
	
	public LooseParasiteMapping(MappingPoint mappingPoint, Event event, LooseParasiteMapping left, LooseParasiteMapping right) {
		this(mappingPoint, event);
		this.leftChildEvent = left.getCurrentEvent();
		this.rightChildEvent = right.getCurrentEvent();
		this.leftChildMappingPoint = left.getCurrentMappingPoint();
		this.rightChildMappingPoint = right.getCurrentMappingPoint();
		this.costToThisPoint = left.getCost() + right.getCost();
	}
	
	public LooseParasiteMapping(MappingPoint mappingPoint, Event event, MappingPoint left, MappingPoint right) {
		this(mappingPoint, event);
		this.leftChildMappingPoint = left;
		this.rightChildMappingPoint = right;
	}
	
	public LooseParasiteMapping(LooseParasiteMapping looseParasiteMapping) {
		this.currentEvent = looseParasiteMapping.currentEvent;
		this.leftChildEvent = looseParasiteMapping.leftChildEvent;
		this.rightChildEvent = looseParasiteMapping.rightChildEvent;
		this.leftChildMappingPoint = looseParasiteMapping.leftChildMappingPoint;
		this.rightChildMappingPoint = looseParasiteMapping.rightChildMappingPoint;
		this.mappingPoint = looseParasiteMapping.mappingPoint;
		this.mappingPointAsNode = looseParasiteMapping.mappingPointAsNode;
		this.costToThisPoint = looseParasiteMapping.costToThisPoint;
		this.bestForwardPositions = looseParasiteMapping.bestForwardPositions;
		this.events = looseParasiteMapping.events;
	}

	public MappingPoint getCurrentMappingPoint() {
		return this.mappingPoint;
	}
	
	public Event getCurrentEvent() {
		return this.currentEvent;
	}
	
	public int getBestForwardPosition() {
		return this.bestForwardPositions;
	}
	
	public Event getLeftChildsEvent() {
		return this.leftChildEvent;
	}
	
	public Event getRightChildsEvent() {
		return this.rightChildEvent;
	}
	
	public MappingPoint getLeftChildsMappingPoint() {
		return this.leftChildMappingPoint;
	}
	
	public MappingPoint getRightChildsMappingPoint() {
		return this.rightChildMappingPoint;
	}
	
	@Override
	public int hashCode() {
		return this.mappingPoint.getMappedPointAsNode().toString().hashCode();
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.mappingPointAsNode);
		builder.append(this.costToThisPoint);
		return builder.toString();
	}

	public void addCost(int cost) {
		this.costToThisPoint += cost;
	}
	
	public int getCost() {
		if (this.mappingPoint.isWideSpreadMappingPoint()) {
			// do something else which gets its complete cost
			return this.costToThisPoint;
		}
		else {
			return this.costToThisPoint;
		}
	}
	
	public void setBestForwardPosition(int bestForwardPositions) {
		this.bestForwardPositions = bestForwardPositions;
	}
	
	@Override
	public int compareTo(LooseParasiteMapping compareTo) {
		return this.getCurrentMappingPoint().getMappedPointAsNode().compareTo(compareTo.getCurrentMappingPoint().getMappedPointAsNode());
	}
	
	public void setLeftChild(MappingPoint point, Event event) {
		this.leftChildMappingPoint = point;
		this.leftChildEvent = event;
	}
	
	public void setRightChild(MappingPoint point, Event event) {
		this.rightChildMappingPoint = point;
		this.rightChildEvent = event;	
	}
	
	public Events getEvents() {
		return this.events;
	}
	
	public void setEvents(Events events) {
		this.events = events;
	}	
	
	public void addEvent(Event event) {
		switch (event) {
			case Codivergence  : this.events.addCodivergenceEvent();
			break;
			case Duplication  : this.events.addDuplicationEvent();
			break;
			case HostSwitchDirection1  : this.events.addHostSwitchEvent();
			break;
			case HostSwitchDirection2  : this.events.addHostSwitchEvent();
			break;
		}
	}
	
	public void addEvents(Events events) {
		this.events.addEvents(events);
	}
	
	public void addLossEvents(Node node) {
		this.events.addLossEvent();
	}
	
	public void removeLossEvents(Node node) {
		this.events.removeLossEvent();
	}
}
