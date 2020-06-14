package metaheuristics.ga;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import tree.Node;
import tree.Tree;

public class TreeTimingCrosser {

	private final Tree xTree;
	private final Tree yTree;
	private final Node crossPoint;
	
	public TreeTimingCrosser(final Tree xTree, final Tree yTree) {
		this.xTree = xTree;
		this.yTree = yTree;
		this.crossPoint = this.selectRandomInternalNode();
	}
	
	public Tree getCrossedTree() {
		return this.getCrossedTree(this.crossPoint);
	}
	
	public Tree getCrossedTree(Node crossPoint) {
		Tree toReturn = this.xTree.cloneTree();	
		
		final List<Node> listMinusAncestors = this.xTree.listMinusAncestors(crossPoint);
		final List<Node> listOfAncestors = this.yTree.listOfAncestors(crossPoint);
		
		Map<Integer, Node> nodeTimingsX = this.getNodeTimings(this.xTree.getNodeTimings(), listMinusAncestors);
		Map<Integer, Node> nodeTimingsY = this.getNodeTimings(this.yTree.getNodeTimings(), listOfAncestors);
		
		Map<Node, Integer> timings = this.greedyAllocation(nodeTimingsX, nodeTimingsY, toReturn);	
			
		if (!validNodeTiming(timings)) {
			throw new InvalidNodeTimingsException("Invalid Node Timings to produced by Cross", timings);
		}
		
		toReturn.setUniqueNodeTimings(timings);
		return toReturn;		
	}
	
	protected Map<Node, Integer> greedyAllocation(final Map<Integer, Node> nodeTimingsX, final Map<Integer, Node> nodeTimingsY, final Tree tree) {
		Map<Node, Integer> nodeTimings = new LinkedHashMap<Node, Integer>();
		
		int counter = 0;
		
		while (0 != nodeTimingsX.size() && 0 != nodeTimingsY.size()) {
			Map.Entry<Integer, Node> x = nodeTimingsX.entrySet().iterator().next();
			Map.Entry<Integer, Node> y = nodeTimingsY.entrySet().iterator().next();
			
			if (x.getKey() == y.getKey()) {
				nodeTimings.put(tree.getNodeWithPrefix(x.getValue().getNodeName()), counter);
				nodeTimingsX.remove(x.getKey());			
			}
			else if (x.getKey() < y.getKey()) {
				nodeTimings.put(tree.getNodeWithPrefix(x.getValue().getNodeName()), counter);
				nodeTimingsX.remove(x.getKey());
			}
			else {
				// TODO this appears to be wrong
				nodeTimings.put(tree.getNodeWithPrefix(x.getValue().getNodeName()), counter);
				nodeTimingsX.remove(x.getKey());
			}
			counter++;
		}
		
		for (Map.Entry<Integer, Node> entry : nodeTimingsX.entrySet()) {
			nodeTimings.put(tree.getNodeWithPrefix(entry.getValue().getNodeName()), counter++);
		}		
		
		for (Map.Entry<Integer, Node> entry : nodeTimingsY.entrySet()) {
			nodeTimings.put(tree.getNodeWithPrefix(entry.getValue().getNodeName()), counter++);
		}
		
		return (nodeTimings);
	}
	
	protected static synchronized Map<Node, Integer> scaleTimings(final Tree tree, final Map<Node, Integer> timings) {
		Map<Node, Integer> toReturnTimings = new LinkedHashMap<Node, Integer>();
		Map<Integer, Node> reverseTimings = new HashMap<Integer, Node>();
		HashSet<Node> allocatedNodes = new HashSet<Node>();
		for (Map.Entry<Node, Integer> entry : timings.entrySet()) {
			reverseTimings.put(entry.getValue(), entry.getKey());
		}
		allocatedNodes.add(tree.getRootNode());
		toReturnTimings.put(tree.getRootNode(), 0);
		int counter = 1;
		for (Map.Entry<Integer, Node> entry : reverseTimings.entrySet()) {
			if (entry.getValue().isRootNode()) {
				continue;
			}
			if (allocatedNodes.contains(entry.getValue().getParent())) {
				allocatedNodes.add(entry.getValue());
				toReturnTimings.put(entry.getValue(), counter++);
			}
		}
		while (toReturnTimings.size() != timings.size()) {
			for (Map.Entry<Integer, Node> entry : reverseTimings.entrySet()) {
				if (entry.getValue().isRootNode() || allocatedNodes.contains(entry.getValue())) {
					continue;
				}
				if (allocatedNodes.contains(entry.getValue().getParent())) {
					allocatedNodes.add(entry.getValue());
					toReturnTimings.put(entry.getValue(), counter++);
				}
			}
		}
		
		return toReturnTimings;
	}
	
	public static boolean validNodeTiming(final Map<Node, Integer> timings) {
		HashSet<String> visited = new HashSet<String>();
		Map<Integer, Node> invertedTimings = new HashMap<Integer, Node>();
		for (Map.Entry<Node, Integer> entry : timings.entrySet()) {
			invertedTimings.put(entry.getValue(), entry.getKey());
		}
		for (int i = 0; i < timings.size(); ++i) {
			Node node = invertedTimings.get(i);
			if (node.isRootNode()) {
				visited.add(node.toString());
			}
			else {
				if (visited.contains(node.getParent().toString())) {
					visited.add(node.toString());
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	protected Map<Integer, Node> getNodeTimings(final Map<Node, Integer> nodeTimingsForTree, final List<Node> nodes) {
		Map<Integer, Node> nodeTimings = new TreeMap<Integer, Node>();
		
		for (Map.Entry<Node, Integer> entry : nodeTimingsForTree.entrySet()) {
			if (nodes.contains(entry.getKey())) {
				nodeTimings.put(entry.getValue(), entry.getKey());
			}
		}
		
		return nodeTimings;
	}	
	
	protected Node selectRandomInternalNode() {
		Random random = new Random();
		int nodeSelector = random.nextInt(this.xTree.numberOfInteranlNodes());
		int counter = 0;
		
		Node toReturn = null;
		
		Set<Node> nodes = this.xTree.getAllNodes();
		
		for (Node node : nodes) {
			if (!node.isLeafNode()) {
				if (counter++ == nodeSelector) {
					toReturn = node;
					break;
				}
			}
		}
		
		return toReturn;
	}
}
