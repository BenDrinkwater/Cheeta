package util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import reconstruction.LooseParasiteMapping;
import tree.Node;
import tree.Tree;

public class PartialMultiMap implements MultiMap<Node, LooseParasiteMapping>{

	private SetWrapper array[];
	private int numberOfItems = 0;
	
	public PartialMultiMap(Tree parasiteTree) {
		this.array = new SetWrapper[parasiteTree.size()+1];
		this.numberOfItems = 0;
	}
	
	public boolean add(Node key, LooseParasiteMapping value) {
		int index = key.isLeafNode() ? key.getLeafLabel() : key.getHeight();		
		this.numberOfItems++;
		if (null == this.array[index]) {
			this.array[index] = new SetWrapper();
			this.array[index].value.add(value);		
			return true;
		}
		else {
			this.array[index].value.add(value);	
			return true;
		}
	}
	
	public void clear() {
		this.array = new SetWrapper[this.array.length];
		this.numberOfItems = 0;
	}

	@Override
	public Set<LooseParasiteMapping> get(Object arg0) {
		Node key = (Node) arg0;
		int index = key.isLeafNode() ? key.getLeafLabel() : key.getHeight();		
		return null == this.array[index] ? null : this.array[index].value;
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<Node, Set<LooseParasiteMapping>>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return 0 == this.size();
	}

	@Override
	public Set<Node> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<LooseParasiteMapping> put(Node arg0, Set<LooseParasiteMapping> arg1) {
		for (LooseParasiteMapping mapping : arg1) {
			this.add(arg0, mapping);
		}
		return this.get(arg0);
	}

	@Override
	public void putAll(Map<? extends Node, ? extends Set<LooseParasiteMapping>> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<LooseParasiteMapping> remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return this.numberOfItems;
	}

	@Override
	public Collection<Set<LooseParasiteMapping>> values() {
		// TODO Auto-generated method stub
		return null;
	}

	private class SetWrapper {
		Set<LooseParasiteMapping> value;
		
		public SetWrapper() {
			this.value = new HashSet<LooseParasiteMapping>();
		}
	}
}
