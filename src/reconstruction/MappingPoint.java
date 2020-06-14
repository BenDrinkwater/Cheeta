package reconstruction;

import util.Pair;
import tree.Edge;
import tree.Node;

public class MappingPoint {

	protected enum MappedPoint {
		Edge,
		Node,
		HostSwitchPair,
		Undefined
	}
	
	protected Edge hostTreeEdge;
	protected Node hostTreeNode;
	protected Pair<Edge, Edge> hostSwitchPair;
	protected MappedPoint mappedTo;
	protected boolean isWideSpreadMultiHostParasiteMappingPoint;
	protected String mappingPointString;
	
	protected MappingPoint() {
		this.mappedTo = MappedPoint.Undefined;
		this.isWideSpreadMultiHostParasiteMappingPoint = false;
		this.mappingPointString = null;
	}
	
	public MappingPoint(Edge hostTreeEdge) {
		this();
		this.hostTreeEdge = hostTreeEdge;
		this.mappedTo = MappedPoint.Edge;
	}
	
	public MappingPoint(Node hostTreeNode) {
		this();
		this.hostTreeNode = hostTreeNode;
		this.mappedTo = MappedPoint.Node;		
	}
	
	public MappingPoint(Pair<Edge, Edge> hostSwitchPair) {
		this();
		this.hostSwitchPair = hostSwitchPair;
		this.mappedTo = MappedPoint.HostSwitchPair;
	}

	public MappedPoint getMappedTo() {
		return this.mappedTo;
	}
	
	public Object getMappedPoint() {
		switch (this.mappedTo) {
			case Edge: return this.hostTreeEdge;
			case Node: return this.hostTreeNode;	
			case HostSwitchPair: return this.hostSwitchPair;
		}
		return null;
	}
	
	public boolean isWideSpreadMappingPoint() {
		return this.isWideSpreadMultiHostParasiteMappingPoint;
	}
	
	protected Pair<Edge, Edge> getEdgePair() {
		return this.hostSwitchPair;
	}
	
	public Node getMappedPointAsNode() {
		switch (this.mappedTo) {
			case Edge: return this.hostTreeEdge.getChild();
			case Node: return this.hostTreeNode;	
			case HostSwitchPair: return this.hostSwitchPair.getFirst().getChild();
		}
		return null;		
	}
	
	public Node getSecondHostNode() {
		if (this.mappingPointIsHostSwitchPair()) {
			return this.hostSwitchPair.getSecond().getChild();
		}
		return null;
	}
	
	public boolean mappingPointIsEdge() {
		return MappedPoint.Edge == this.mappedTo || 
				MappedPoint.HostSwitchPair == this.mappedTo;
	}
	
	public boolean mappingPointIsDuplication() {
		return MappedPoint.Edge == this.mappedTo && 
				MappedPoint.HostSwitchPair != this.mappedTo;
	}	
	
	public boolean mappingPointIsNode() {
		return MappedPoint.Node == this.mappedTo;
	}
	
	public boolean mappingPointIsHostSwitchPair() {
		return MappedPoint.HostSwitchPair == this.mappedTo;
	}
	
	public boolean equals(Object obj) {
		return null != obj && this.toString().equals(obj.toString());
	}
	
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	public String toString() {
		if (null == this.mappingPointString)
		{
			switch (this.mappedTo) {
				case Edge: 
					this.mappingPointString = this.hostTreeEdge.toString();
					break;
				case Node:  
					this.mappingPointString = this.hostTreeNode.toString();
					break;
				case HostSwitchPair:   
					this.mappingPointString = "(" + this.hostSwitchPair.getFirst().toString() + "," + this.hostSwitchPair.getSecond().toString() + ")";
					break;
				default:
					this.mappingPointString = "Unknown Mapping";
			}
			
		}
		return this.mappingPointString;
	}
}
