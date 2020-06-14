package util.reconstruction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import reconstruction.LooseParasiteMapping;
import tree.Node;

public class SolutionMap {

	private Map<Node, Map<Node, LooseParasiteMapping>> solutionMap;
	
	public SolutionMap() {
		this.solutionMap = new HashMap<Node, Map<Node, LooseParasiteMapping>>();
	}
	
	public void add(Node parasiteNode, Node hostNode, LooseParasiteMapping mapping) {
		if (!this.solutionMap.containsKey(parasiteNode)) {
			Map<Node, LooseParasiteMapping> map = new HashMap<Node, LooseParasiteMapping>();
			this.solutionMap.put(parasiteNode, map);
		}
		this.addItem(parasiteNode, hostNode, mapping);
	}
	
	private void addItem(Node parasiteNode, Node hostNode, LooseParasiteMapping mapping) {
		LooseParasiteMapping alternate = this.solutionMap.get(parasiteNode).containsKey(hostNode) ? this.solutionMap.get(parasiteNode).get(hostNode) : null;
		if (null == alternate || alternate.getCost() > mapping.getCost()) {
			this.solutionMap.get(parasiteNode).put(hostNode, mapping);
		}
	}
	
	public Map<Node, LooseParasiteMapping> getParasiteNodeMappingLocations(Node parasiteNode) {
		return this.solutionMap.get(parasiteNode);
	}
	
	public List<LooseParasiteMapping> getParasiteNodeMappingLocationList(Node parasiteNode) {
		List<LooseParasiteMapping> mappingCites = new LinkedList<LooseParasiteMapping>();
		for (Map.Entry<Node, LooseParasiteMapping> entry : this.solutionMap.get(parasiteNode).entrySet()) {
			mappingCites.add(entry.getValue());
		}
		return mappingCites;
	}
}
