package util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import reconstruction.LooseParasiteMapping;
import tree.Edge;

public class EdgeQueryQueue {

	private Set<EdgeQuery>	queue;
	
	public EdgeQueryQueue() {
		this.queue = new TreeSet<EdgeQuery>();
	}
	
	public void add(LooseParasiteMapping mapping) {
		if (null == mapping) {
			return;
		}
		Edge key = mapping.getCurrentMappingPoint().getMappedPointAsNode().getParentEdge();
		this.queue.add(new EdgeQuery(key, mapping));
	}
	
	public LooseParasiteMapping getFront() {
		if (0 != this.queue.size()) {
			EdgeQuery entry = this.queue.iterator().next();
			this.queue.remove(entry);
			return entry.getValue();
		}
		return null;
	}
	
	public boolean empty() {
		return 0 == this.queue.size();
	}
	
	public String toString() {
		Iterator<EdgeQuery> iterator = this.queue.iterator();
		StringBuilder sb = new StringBuilder();
		
		while(iterator.hasNext()) {
			EdgeQuery query = iterator.next();
			sb.append(query.getKey());
			sb.append(" Mapped Vaule: ");
			sb.append(query.getValue().getCost());
			sb.append(", ");
		}
		
		return sb.toString();
	}
	
	class EdgeQuery implements Comparable<EdgeQuery>, Map.Entry<Edge, LooseParasiteMapping> {
		
		private Edge key;
		private LooseParasiteMapping value;
		
		public EdgeQuery(Edge edge, LooseParasiteMapping mapping) {
			this.key = edge;
			this.value = mapping;
		}

		@Override
		public int compareTo(EdgeQuery alternateEdge) {
			return this.value.getCost() - alternateEdge.value.getCost();
		}

		@Override
		public Edge getKey() {
			return this.key;
		}

		@Override
		public LooseParasiteMapping getValue() {
			return this.value;
		}

		@Override
		public LooseParasiteMapping setValue(LooseParasiteMapping value) {
			return this.value = value;
		}
		
	}
	
}
