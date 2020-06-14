package reconstruction.solver.nm;

import reconstruction.LooseParasiteMapping;
import tree.Node;
import util.Pair;

public class LevelCosts {

	Pair<Integer, Pair<Node, LooseParasiteMapping>> levelCost;
	
	public LevelCosts(Pair<Integer, Pair<Node, LooseParasiteMapping>> levelCost) {
		this.levelCost = levelCost;
	}
	
	public Integer getCost() {
		return this.levelCost.getFirst();
	}
	
	public Node getNode() {
		return this.levelCost.getSecond().getFirst();
	}
	
	public LooseParasiteMapping getMapping() {
		return this.levelCost.getSecond().getSecond();
	}	
	
	public Pair<Integer, Pair<Node, LooseParasiteMapping>> getLevelCosts() {
		return this.levelCost;
	}	
	
	public String toString() {
		return this.levelCost.getFirst() + " " + this.levelCost.getSecond().getFirst() + " " + this.levelCost.getSecond().getSecond();
	}
}
