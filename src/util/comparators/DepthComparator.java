package util.comparators;

import java.util.Comparator;

import tree.Node;

public class DepthComparator implements Comparator<Node>{

	@Override
	public int compare(Node node1, Node node2) {
		return node2.distanceToRoot() - node1.distanceToRoot();
	}

}
