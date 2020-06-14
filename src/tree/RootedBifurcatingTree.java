package tree;

import io.nexus.NexusStringFormat;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import util.Pair;

public class RootedBifurcatingTree implements Tree {

		// TODO check for unused methods (183 missed instructions for unit tests)
		protected Map<String, Node> treeNodes;
		protected Map<Node, Integer> nodeTimings;
		protected String rootNodeName;
		protected String prefix;
		protected Node rootNode;
		
		protected boolean cached;
		protected Map<Node, Map<Node, Integer>> cachedDistanceBetweenNodes;
		
		protected Integer[][] cachedDistanceBetweenNodesArray;
		
		protected Map<Node, Map<Node, Node>> cachedFirstCommonAncestor;	
		
		protected Node[][] cachedFirstCommonAncestorArray;
		
		protected Map<Edge, Edge[]> edgeReference; 
		
		protected Map<Node, Pair<Integer, Integer>> nodeRanges;
		protected Map<Integer, Map<Integer, Node>> lowestCommonAncestorRef;
		
		int lastMod = 0;
		
		
		protected RootedBifurcatingTree() {
			this.rootNode = null;
			this.treeNodes = new LinkedHashMap<String, Node>();
			this.cached = false;
			this.cachedDistanceBetweenNodes = null;
			this.cachedFirstCommonAncestor = null;
			this.cachedDistanceBetweenNodesArray = null;
			this.cachedFirstCommonAncestorArray = null;
			this.edgeReference = null;
		}
		
		protected RootedBifurcatingTree(String rootNodeName, String prefix) {
			this();
			this.rootNodeName = rootNodeName;
			this.prefix = prefix;
			String nodeName = prefix + "_" + rootNodeName;
			this.treeNodes.put(nodeName, new RootedBifurcatingNode(nodeName));
		}

		@Override
		public Node getRootNode() {
			if (null == rootNode) {
				this.rootNode = this.getNode(this.rootNodeName);
			}
			return this.rootNode;
		}

		@Override
		public Set<Node> getLeafNodes() {
			Set<Node> leafNodes = new LinkedHashSet<Node>();
			for(Node node : this.treeNodes.values()) {
				if (node.isLeafNode()) {
					leafNodes.add(node);
				}
			}
			return leafNodes;
		}

		@Override
		public List<Node> getLeafNodesList() {
			List<Node> leafNodes = new LinkedList<Node>();
			for(Node node : this.treeNodes.values()) {
				if (node.isLeafNode()) {
					leafNodes.add(node);
				}
			}
			return leafNodes;
		}
		
		@Override
		public Set<Node> getLeafNodes(Node node) {
			Set<Node> leafNodes = new LinkedHashSet<Node>();
			return this.getLeafNodes(node, leafNodes);
		}
		
		private Set<Node> getLeafNodes(Node node, Set<Node> leafNodes) {
			if (node.isLeafNode()) {
				leafNodes.add(node);
				return leafNodes;
			}
			this.getLeafNodes(node.getLeftChild(), leafNodes);
			this.getLeafNodes(node.getRightChild(), leafNodes);
			return leafNodes;
		}

		@Override
		public List<Node> getLeafNodesList(Node node) {
			List<Node> leafNodes = new LinkedList<Node>();
			return this.getLeafNodesList(node, leafNodes);
		}
		
		private List<Node> getLeafNodesList(Node node, List<Node> leafNodes) {
			if (node.isLeafNode()) {
				leafNodes.add(node);
				return leafNodes;
			}
			this.getLeafNodesList(node.getLeftChild(), leafNodes);
			this.getLeafNodesList(node.getRightChild(), leafNodes);
			return leafNodes;
		}
		
		@Override
		public Set<Node> getAllNodes() {
			return new LinkedHashSet<Node>(this.treeNodes.values());
		}
		
		@Override
		public List<Node> getAllNodesAsList() {
			return new LinkedList<Node>(this.treeNodes.values());
		}
		
		public Set<Node> getAllNodes(Node node) {
			Set<Node> nodes = new LinkedHashSet<Node>();
			return this.getAllNodes(node, nodes);		
		}
		
		public Set<Node> getAllNodes(Node node, Set<Node> nodes) {
			nodes.add(node);
			if (node.isLeafNode()) {
				return nodes;
			}
			this.getAllNodes(node.getLeftChild(), nodes);
			this.getAllNodes(node.getRightChild(), nodes);	
			return nodes;	
		}
		
		public List<Node> getAllNodesAsList(Node node) {
			List<Node> nodes = new LinkedList<Node>();
			return this.getAllNodesAsList(node, nodes);		
		}
		
		public List<Node> getAllNodesAsList(Node node, List<Node> nodes) {
			nodes.add(node);
			if (node.isLeafNode()) {
				return nodes;
			}
			this.getAllNodesAsList(node.getLeftChild(), nodes);
			this.getAllNodesAsList(node.getRightChild(), nodes);	
			return nodes;				
		}
		

		@Override
		public List<Node> getNodesAsPostOrder() {
			List<Node> nodes = new LinkedList<Node>();
			return getNodesAsPostOrder(null == this.rootNode ? this.getRootNode() : this.rootNode, nodes);
		}
		
		private List<Node> getNodesAsPostOrder(Node node, List<Node> nodes) {
			if (node.isLeafNode()) {
				nodes.add(node);
			}
			else {
				Node left = node.getLeftChild();
				Node right = node.getRightChild();
				getNodesAsPostOrder(left, nodes);
				getNodesAsPostOrder(right, nodes);
				nodes.add(node);
			}
			return nodes;
		}		

		@Override
		public Node getNode(String name) {
			return this.treeNodes.get(this.prefix + "_" + name);
		}

		@Override
		public Node getNodeWithPrefix(String nameWithPrefix) {
			return this.treeNodes.get(nameWithPrefix);
		}
		
		@Override
		public String getNodeName(Node node) {
			return node.getNodeName().substring(this.prefix.length() + 1);
		}

		@Override
		public Map<Node, Integer> getNodeTimings() {
			return this.nodeTimings;
		}

		@Override
		public Integer getNodeHeight(Node node) {
			if (null == node) {
				return -1;
			}
			if (node.isLeafNode()) {
				return ((this.size() - 1) / 2);
			}
			else {
				return this.getNodeTimings().get(node);
			}
		}
		
		@Override
		public int size() {
			return this.treeNodes.size();
		}

		@Override
		public int numberOfLeafNodes() {
			return (this.treeNodes.size() / 2) + 1;
		}

		@Override
		public int numberOfInteranlNodes() {
			return this.size() - this.numberOfLeafNodes();
		}
		
		public void setTreeCache() {
			for (Node node : this.getAllNodes()) {
				node.calibrateDistanceToRoot();
			}
		}
		
		public Integer distanceBetweenNodes(Node ancestorNode, Node descendantNode) {
			if (this.cached) {
				int leftIndex = ancestorNode.isLeafNode() ? ancestorNode.getLeafLabel() : ancestorNode.getHeight();
				int rightIndex = descendantNode.isLeafNode() ? descendantNode.getLeafLabel() : descendantNode.getHeight();
				
				return this.cachedDistanceBetweenNodesArray[leftIndex][rightIndex];
				
				//return this.cachedDistanceBetweenNodes.get(ancestorNode).get(descendantNode);
			}
			if (null == ancestorNode || null == descendantNode) {
				return null;
			}
			if (ancestorNode.equals(descendantNode)) {
				return 0;
			}
			else if (descendantNode.equals(this.getRootNode())) {
				return null;
			}
			return this.distanceBetweenNodes(ancestorNode, descendantNode.getParent(), 0);
		}
		
		private Integer distanceBetweenNodes(Node finshingPoint, Node currentPoint, Integer numberOfVisitedNodes) {
			if (currentPoint.equals(finshingPoint)) {
				return numberOfVisitedNodes;
			}
			else if (currentPoint.equals(this.getRootNode())) {
				return null;
			}
			else {
				return this.distanceBetweenNodes(finshingPoint, currentPoint.getParent(), numberOfVisitedNodes + 1);
			}
		}
		
		public List<Node> nodesBetweenNodes(Node ancestorNode, Node descendantNode) {
			if (null == ancestorNode || null == descendantNode) {
				return null;
			}
			if (ancestorNode.equals(descendantNode)) {
				return new LinkedList<Node>();
			}
			else if (descendantNode.equals(this.getRootNode())) {
				return null;
			}
			List<Node> nodes = this.nodesBetweenNodes(ancestorNode, descendantNode.getParent(), new LinkedList<Node>());
			if (null != nodes) {
				Collections.reverse(nodes);
			}
			return nodes;
		}
		
		private List<Node> nodesBetweenNodes(Node finshingPoint, Node currentPoint, List<Node> visitedNodes) {
			if (currentPoint.equals(finshingPoint)) {
				return visitedNodes;
			}
			else if (currentPoint.equals(this.getRootNode())) {
				return null;
			}
			else {
				visitedNodes.add(currentPoint);
				return this.nodesBetweenNodes(finshingPoint, currentPoint.getParent(), visitedNodes);
			}
		}	

		@Override
		public boolean addChildren(String parent, String leftChild,
				String rightChild) {
			return this.addChildren(parent, leftChild, 1, rightChild, 1);
		}

		@Override
		public boolean addChildren(String parent, String leftChild,
				int leftDistance, String rightChild, int rightDistance) {
			String leftChildName = this.prefix + "_" + leftChild;
			String rightChildName = this.prefix + "_" + rightChild;
			
			boolean childrenAdded =  this.getNode(parent).setChildren(leftChildName, leftDistance, rightChildName, rightDistance);
			if (childrenAdded) {
				this.treeNodes.put(leftChildName, this.getNode(parent).getLeftChild());
				this.treeNodes.put(rightChildName, this.getNode(parent).getRightChild());
			}
			//this.setTreeCache();
			return childrenAdded;
		}

		@Override
		public boolean addChildren(String parent, Tree leftSubTreeRootNode,
				Tree rightSubTreeRootNode) {
			boolean childrenAdded =  this.getNode(parent).addExistingNodesAsChildren(leftSubTreeRootNode.getRootNode(), rightSubTreeRootNode.getRootNode());
			if (childrenAdded) {
				this.treeNodes.putAll(((RootedBifurcatingTree) leftSubTreeRootNode).treeNodes);
				this.treeNodes.putAll(((RootedBifurcatingTree) rightSubTreeRootNode).treeNodes);
			}
			//this.setTreeCache();
			return childrenAdded;		
		}

		@Override
		public boolean deleteLeafNodePair(LeafNodePair pair) {
			if (null != pair && pair.isValidLeafNodePair()) {
				this.treeNodes.remove(pair.getFirst().getNodeName());
				this.treeNodes.remove(pair.getSecond().getNodeName());
				return pair.getParent().removeEdge(pair.getFirst()) && pair.getParent().removeEdge(pair.getSecond());
			}
			return false;
		}
		
		@Override
		public boolean deleteLeafNodePair(Node leftNode, Node rightNode) {
			return this.deleteLeafNodePair(new LeafNodePair(leftNode, rightNode));
		}
		
		@Override
		public List<Node> listMinusAncestors(final Node node) {
			List<Node> subset = this.listOfAncestors(node);
			List<Node> nodes = this.getAllNodesAsList();
			List<Node> leafNodes = this.getLeafNodesList();
			nodes.removeAll(subset);
			nodes.removeAll(leafNodes);
			return nodes;
		}
		
		@Override
		public List<Node> listOfAncestors(final Node node) {
			List<Node> nodes = new LinkedList<Node>();			
			this.listOfAncestors(nodes, node);
			return nodes;
		}
		
		public void listOfAncestors(List<Node> nodes, Node node) {	
			if (null == node || null == node.getNodeName()) {
				return;
			}
			Node key = this.getNodeWithPrefix(node.getNodeName());
			if (!node.isLeafNode()) {
				nodes.add(key);
				listOfAncestors(nodes, node.getLeftChild());
				listOfAncestors(nodes, node.getRightChild());
			}
		}
		
		public Node getLowestCommonAncestor(Node first, Node second) {
			if (this.cached) {
				int leftIndex = first.isLeafNode() ? first.getLeafLabel() : first.getHeight();
				int rightIndex = second.isLeafNode() ? second.getLeafLabel() : second.getHeight();
				
				return this.cachedFirstCommonAncestorArray[leftIndex][rightIndex];
			}
			if (first.isRootNode()) {
				return first;
			}
			else if (second.isRootNode()) {
				return second;
			}
			else if (first.equals(second)) {
				return first;
			}
			Set<Node> nodesInPath = new LinkedHashSet<Node>();
			Node current = first;
			nodesInPath.add(current);
			while(!current.isRootNode()) {
				current = current.getParent();
				nodesInPath.add(current);
			}
			current = second;
			while(!current.isRootNode()) {	
				if (nodesInPath.contains(current)) {
					return current;
				}
				current = current.getParent();					
			}
			if (nodesInPath.contains(current)) {
				return current;
			}
			return null;
		}
		
		@Override
		public Tree cloneTree() {
			return this.cloneTree(this.prefix);
		}

		@Override
		public Tree cloneTree(final String newPrefix) {
			RootedBifurcatingTree copy = (RootedBifurcatingTree) this.cloneTree(new RootedBifurcatingTree(this.rootNodeName, newPrefix), this.getRootNode());
			if (null != this.nodeTimings) {
				copy.setUniqueNodeTimings(this.nodeTimings);
				copy.cached = cached;
				copy.cachedDistanceBetweenNodesArray = cachedDistanceBetweenNodesArray;
				copy.cachedFirstCommonAncestorArray = cachedFirstCommonAncestorArray;
				copy.cachedDistanceBetweenNodes = cachedDistanceBetweenNodes;		
				copy.cachedFirstCommonAncestor = cachedFirstCommonAncestor;			
				copy.edgeReference = edgeReference; 				
				copy.nodeRanges = nodeRanges;
				copy.lowestCommonAncestorRef = lowestCommonAncestorRef;
			}
			return copy;
		}
		
		private Tree cloneTree(Tree tree, Node currentNode) {
			if (!currentNode.isLeafNode()) {
				tree.addChildren(this.getNodeName(currentNode), 
						this.getNodeName(currentNode.getLeftChild()),
						currentNode.getLeftChild().getParentEdge().getEdgeDistance(),
						this.getNodeName(currentNode.getRightChild()),
						currentNode.getRightChild().getParentEdge().getEdgeDistance());
				this.cloneTree(tree, currentNode.getLeftChild());
				this.cloneTree(tree, currentNode.getRightChild());
			}
			return tree;
		}
		
		@Override
		public void setUniqueNodeTimings(final Map<Node, Integer> timings) {
			Node rootNode = this.getNode(this.rootNodeName);
			this.nodeTimings = this.copyTimings(timings);
			this.setUniqueNodeTimings(this.nodeTimings, rootNode);
			for (Map.Entry<Node, Integer> entry : this.nodeTimings.entrySet()) {
				entry.getKey().setHeight(entry.getValue());
			}
			int i = 0;
			for (Node leaf : this.getLeafNodes()) {
				leaf.setHeight(((this.size() - 1) / 2));
				((TreeNode) leaf).setLeafLabel(++i);
			}
		}
		
		private Map<Node, Integer> copyTimings(final Map<Node, Integer> timings) { 
			Map<Node, Integer> toReturn = new HashMap<Node, Integer>();		
			for (Map.Entry<Node, Integer> entry : timings.entrySet()) {
				Node key = this.getNodeWithPrefix(entry.getKey().getNodeName());
				toReturn.put(key, entry.getValue());
			}
			return toReturn;
		}
		
		private void setUniqueNodeTimings(final Map<Node, Integer> timings, Node parent) {
			if (parent.isLeafNode()) {
				int k = parent.getParentEdge().getParent().distanceToRoot();
				int l = this.numberOfInteranlNodes();
				parent.getParentEdge().setEdgeDistance(l - k);
				parent.calibrateDistanceToRoot();
				return;
			}
			else {	
				int i = timings.get(parent);
				int j = parent.getParentEdge().getParent().distanceToRoot();
				int distanceEdge = i - j;
				parent.getParentEdge().setEdgeDistance(distanceEdge);
				parent.calibrateDistanceToRoot();
				this.setUniqueNodeTimings(timings, parent.getLeftChild());
				this.setUniqueNodeTimings(timings, parent.getRightChild());
			}
		}
		
		@Override
		public void setRandomUniqueNodeTimings() {
			this.setUniqueNodeTimings(this.getRandomNodeTimingFromNodeOrdering());
		}
		
		protected Map<Node, Integer> getRandomNodeTimingFromNodeOrdering() {
			Map<Node, Integer> map = new LinkedHashMap<Node, Integer>();
			
			List<Node> appropriateNodeOrdering = this.getRandomNodeOrdering();
			int counter = 0;
			for (Node node : appropriateNodeOrdering) {
				map.put(node, counter++);
			}
			
			return map;
		}
		
		protected List<Node> getRandomNodeOrdering() {
			Node root = this.getNode(this.rootNodeName);
			List<Node> list = new LinkedList<Node>();
			this.getRandomListNodeOrdering(list, root);
			return list;
		}
		
		protected void getRandomListNodeOrdering(List<Node> nodeOrdering, Node parent) {
			if (parent.isLeafNode()) {
				return;
			}
			else {
				nodeOrdering.add(parent);
				Node left = parent.getLeftChild();
				Node right = parent.getRightChild();
				if (new Random().nextBoolean()) {
					this.getRandomListNodeOrdering(nodeOrdering, left);
					this.getRandomListNodeOrdering(nodeOrdering, right);				
				}
				else {
					this.getRandomListNodeOrdering(nodeOrdering, right);
					this.getRandomListNodeOrdering(nodeOrdering, left);	
				}
			}
		}
		
		public Map<Edge, Edge[]> getEdgeReference() {
			return this.edgeReference;
		}
		
		public void cacheValues() {
			this.cachedDistanceBetweenNodesArray = new Integer[2*this.numberOfLeafNodes()][2*this.numberOfLeafNodes()];
			this.cachedDistanceBetweenNodes = new HashMap<Node, Map<Node, Integer>>();
			this.cachedFirstCommonAncestor = new HashMap<Node, Map<Node, Node>>();
			
			this.cachedFirstCommonAncestorArray = new Node[2*this.numberOfLeafNodes()][2*this.numberOfLeafNodes()];
			
			cachePairs();
			
			for (Node left : this.treeNodes.values()) {
				for (Node right : this.treeNodes.values()) {
					Integer distance = this.distanceBetweenNodes(left, right);
					
					int leftIndex = left.isLeafNode() ? left.getLeafLabel() : left.getHeight();
					int rightIndex = right.isLeafNode() ? right.getLeafLabel() : right.getHeight();
					
					if (null != distance) {
						
						this.cachedDistanceBetweenNodesArray[leftIndex][rightIndex] = distance;
					}
					
					Node lca = this.getLowestCommonAncestor(left, right);
					
					this.cachedFirstCommonAncestorArray[leftIndex][rightIndex] = lca;
				}
			}
			
			for (Node left : this.treeNodes.values()) {
				for (Node right : this.treeNodes.values()) {
					Map<Node, Integer> distanceMap = this.cachedDistanceBetweenNodes.get(left);
					if (null == distanceMap) {
						distanceMap = new HashMap<Node, Integer>();
					}
					distanceMap.put(right, this.distanceBetweenNodes(left, right));
					this.cachedDistanceBetweenNodes.put(left, distanceMap);
					
					Map<Node, Node> parentMap = this.cachedFirstCommonAncestor.get(left);
					if (null == parentMap) {
						parentMap = new HashMap<Node, Node>();
					}
					parentMap.put(right, this.getLowestCommonAncestor(left, right));
					this.cachedFirstCommonAncestor.put(left, parentMap);
				}
			}
			
			this.edgeReference = new HashMap<Edge, Edge[]>();
			
			for (Node node : this.treeNodes.values()) {
				Edge edge = node.getParentEdge();
				if (edge.isInternalEdge()) {
					Edge[] reference = new Edge[this.treeNodes.size()];
					Edge current = edge;
					while (!current.isFloatingEdge()) {
						int top = edge.getParent().getHeight();
						int bottom = edge.getChild().getHeight();
						for (int i = top + 1; i <= bottom; ++i) {
							reference[edge.getChild().getHeight()] = current;
						}
						current = current.getParent().getParentEdge();
					}
					this.edgeReference.put(edge, reference);
				}
			}
			
			this.cached = true;
		}
		
		private void cachePairs() {
			Integer count = 0;
			this.nodeRanges = new HashMap<Node, Pair<Integer, Integer>>();
			LinkedList<Node> queue = new LinkedList<Node>(this.getLeafNodes());
			while(!queue.isEmpty()) {
				Node frontOfQueue = queue.peekFirst();
				if (frontOfQueue.isLeafNode()) {
					this.nodeRanges.put(frontOfQueue, new Pair<Integer, Integer>(count,count));
					count++;
					queue.addLast(frontOfQueue.getParent());
				}
				else {
					if (null == this.nodeRanges.get(frontOfQueue.getLeftChild()) || null == this.nodeRanges.get(frontOfQueue.getRightChild())) {
						queue.addLast(frontOfQueue);
					}
					else {
						this.nodeRanges.put(frontOfQueue, new Pair<Integer, Integer>(this.nodeRanges.get(frontOfQueue.getLeftChild()).getFirst(), this.nodeRanges.get(frontOfQueue.getRightChild()).getSecond()));
						if (!frontOfQueue.isRootNode()) {
							queue.addLast(frontOfQueue.getParent());
						}
					}
				}
				queue.removeFirst();
			}
			this.lowestCommonAncestorRef = new HashMap<Integer, Map<Integer, Node>>();
			for (Map.Entry<Node, Pair<Integer, Integer>> entrySet : this.nodeRanges.entrySet()) {
				Map<Integer, Node> map = this.lowestCommonAncestorRef.get(entrySet.getValue().getFirst());
				if (null == map) {
					map = new HashMap<Integer, Node>();
				}
				map.put(entrySet.getValue().getSecond(), entrySet.getKey());
				this.lowestCommonAncestorRef.put(entrySet.getValue().getFirst(), map);
			}
		}
		
		@Override
		public boolean equals(Object compareTo) {
			if (null != compareTo && compareTo instanceof RootedBifurcatingTree) {
				RootedBifurcatingTree treeToCompareTo = (RootedBifurcatingTree) compareTo;
				return this.mapEquality(this.treeNodes, treeToCompareTo.treeNodes);
			}
			return false;
		}
		
		private boolean mapEquality(Map<String, Node> map1, Map<String, Node> map2) {
			if (map1.size() == map2.size()) {
				for (String key : map1.keySet()) {
					if (null != map2.get(key) && map1.get(key).equals(map2.get(key))){
						continue;
					}
					else { 
						return false;
					}
				}
				return true;
			}
			else {
				return false;
			}
		}
		
		public String getNexusString(NexusStringFormat format) {
			if (NexusStringFormat.CorePa == format){
				return this.getCorePaNexusString(this.getRootNode());
			}
			return this.getJaneNexusString(this.getRootNode());
		}
		
		private String getJaneNexusString(Node thisNode) {	
			if (null == ((RootedBifurcatingNode) thisNode).getLeftChild() && null == ((RootedBifurcatingNode) thisNode).getRightChild()) {
				return this.getNodeName(thisNode);
			}
			else {
				return "(" + this.getJaneNexusString(((RootedBifurcatingNode) thisNode).getLeftChild()) + "," + 
						this.getJaneNexusString(((RootedBifurcatingNode) thisNode).getRightChild()) + ")";
			}
		}	
		
		private String getCorePaNexusString(Node thisNode) {	
			if (null == ((RootedBifurcatingNode) thisNode).getLeftChild() && null == ((RootedBifurcatingNode) thisNode).getRightChild()) {
				return this.getNodeName(thisNode);
			}
			else {
				return "(" + this.getCorePaNexusString(((RootedBifurcatingNode) thisNode).getLeftChild()) + "," + 
						this.getCorePaNexusString(((RootedBifurcatingNode) thisNode).getRightChild()) + ")" + this.getNodeName(thisNode);
			}
		}
}
