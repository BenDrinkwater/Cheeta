package util.reconstruction;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import reconstruction.LooseParasiteMapping;
import tree.Tree;

public class ImprovedReconstructionSet implements Set<LooseParasiteMapping> {
	
	private LooseParasiteMapping[] looseParasiteMappings;
	private int size;
	private Tree tree;
	
	public ImprovedReconstructionSet(Tree tree) {
		this.tree = tree;
		this.looseParasiteMappings = new LooseParasiteMapping[tree.size()+1];
		this.size = 0;
	}	
	
	@Override
	public boolean add(LooseParasiteMapping looseParasiteMapping) {
		if (null == looseParasiteMapping) {
			return false;
		}
		
		int index = looseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().isLeafNode() 
				? looseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getLeafLabel() 
				: looseParasiteMapping.getCurrentMappingPoint().getMappedPointAsNode().getHeight();
		
		if (null == this.looseParasiteMappings[index]) {
			this.looseParasiteMappings[index] = looseParasiteMapping;
			this.size++;
			return true;
		}
		else {
			if (looseParasiteMapping.getCost() < this.looseParasiteMappings[index].getCost()) {
				this.looseParasiteMappings[index] = looseParasiteMapping;
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends LooseParasiteMapping> c) {
		boolean allAdded = true;
		if (null == c) {
			return false;
		}
		for (LooseParasiteMapping looseParasiteMapping : c) {
			if (!(this.add(looseParasiteMapping))) {
				allAdded = false;
			}
		}
		return allAdded;
	}
	@Override
	public void clear() {
		this.looseParasiteMappings = new LooseParasiteMapping[this.looseParasiteMappings.length];
		this.size = 0;
	}
	@Override
	public boolean contains(Object element) {
		LooseParasiteMapping mapping = (LooseParasiteMapping) element;
		int index = mapping.getCurrentMappingPoint().getMappedPointAsNode().isLeafNode() ? mapping.getCurrentMappingPoint().getMappedPointAsNode().getLeafLabel() :
			mapping.getCurrentMappingPoint().getMappedPointAsNode().getHeight();
		
		return null != this.looseParasiteMappings[index] && mapping.getCost() == this.looseParasiteMappings[index].getCost();
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!(this.contains(o))) {
				return false;
			}
		}		
		return true;
	}
	@Override
	public boolean isEmpty() {
		return 0 == this.size;
	}
	
	@Override
	public Iterator<LooseParasiteMapping> iterator() {
		return new ArrayIterator(this.looseParasiteMappings, this.size);
	}
	
	@Override
	public boolean remove(Object element) {
		LooseParasiteMapping mapping = (LooseParasiteMapping) element;
		int index = mapping.getCurrentMappingPoint().getMappedPointAsNode().isLeafNode() ? mapping.getCurrentMappingPoint().getMappedPointAsNode().getLeafLabel() :
			mapping.getCurrentMappingPoint().getMappedPointAsNode().getHeight();
		
		if (null != this.looseParasiteMappings[index] && mapping.getCost() == this.looseParasiteMappings[index].getCost()) {
			this.looseParasiteMappings[index] = null;
			--this.size;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		if (null == c) {
			return false;
		}
		int removalCounter = 0;
		for (Object looseParasiteMapping : c) {
			removalCounter += this.remove(looseParasiteMapping) ? 1 : 0;
		}
		return c.size() == removalCounter;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		ImprovedReconstructionSet set = new ImprovedReconstructionSet(tree);
		set.addAll((Collection<? extends LooseParasiteMapping>) c);
		this.looseParasiteMappings = set.looseParasiteMappings;
		this.size = set.size;
		return true;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public Object[] toArray() {
		return this.looseParasiteMappings;
	}
	
	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
	
	class ArrayIterator implements Iterator<LooseParasiteMapping> {
		private LooseParasiteMapping[] array;
		private int pos = 0;
		private int numberOfItems;
		private int numberOfItemsVisited;

		public ArrayIterator(LooseParasiteMapping[] array, int numberOfItems) {
			this.array = array;
			this.numberOfItems = numberOfItems;
		}

		public boolean hasNext() {
			return this.numberOfItemsVisited < this.numberOfItems;
		}

		public LooseParasiteMapping next() throws NoSuchElementException {
 			if (hasNext()) {
				for (int i = pos; i < this.array.length; ++i) {
					if (null != this.array[i]) {
						++this.numberOfItemsVisited;
						this.pos = i+1;
						return this.array[i];
					}
				}
			}
			throw new NoSuchElementException();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}	
}