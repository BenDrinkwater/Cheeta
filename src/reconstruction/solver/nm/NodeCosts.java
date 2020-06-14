package reconstruction.solver.nm;

import reconstruction.LooseParasiteMapping;
import tree.Node;
import util.Pair;

public class NodeCosts {

	protected Pair<Integer, Pair<Node, LooseParasiteMapping>> nodeCost;
	private Node node;
	
	public NodeCosts(Pair<Integer, Pair<Node, LooseParasiteMapping>> nodeCost, Node node) {
		this.nodeCost = nodeCost;
		this.node = node;
	}
	
	public Integer getCost() {
		return this.nodeCost.getFirst();
	}
	
	public Node getNode() {
		return this.nodeCost.getSecond().getFirst();
	}
	
	public LooseParasiteMapping getMapping() {
		return this.nodeCost.getSecond().getSecond();
	}	
	
	public Pair<Integer, Pair<Node, LooseParasiteMapping>> getLevelCosts() {
		return this.nodeCost;
	}	
	
	public String toString() {
		return this.nodeCost.getFirst() + " " + this.nodeCost.getSecond().getFirst() + " " + this.nodeCost.getSecond().getSecond();
	}
	
	public Node getMappedLocation() {
		return this.node;
	}
}
