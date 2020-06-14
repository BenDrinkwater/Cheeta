package reconstruction;

import tree.Tree;
import util.SolutionComparable;

public class CophylogenyReconstruction implements SolutionComparable {

	protected Tree hostTree;
	protected HostMappingList hostMapping;
	protected ParasiteMappingList parasiteMapping;
	protected Events events;
	protected int[] costScheme;
	
	public CophylogenyReconstruction(Tree hostTree, HostMappingList hostMapping, ParasiteMappingList parasiteMapping, Events events) {
		this.hostTree= hostTree;
		this.hostMapping = hostMapping;
		this.parasiteMapping = parasiteMapping;
		this.events = events;
		this.costScheme = this.events.costScheme;
	}	
	
	public Tree getHostTree() {
		return this.hostTree;
	}
	
	public HostMappingList getHostMapping() {
		return this.hostMapping;
	}	
	
	public ParasiteMappingList getParasiteMapping() {
		return this.parasiteMapping;
	}
	
	public Events getEvents() {
		return this.events;
	}
	
	public String toString() {
		return this.events.toString();
	}

	@Override
	public int hashCode() {
		return this.events.hashCode();
	}
	
	@Override
	public int getCost() {
		return this.events.getCost();
	}

	@Override
	public int getNumberOfCodivergenceEvents() {
		return this.events.getNumberOfCodivergenceEvents();
	}
	
	@Override
	public int compareTo(SolutionComparable o) {
		return 1;
	}

	@Override
	public int compareTo(CophylogenyReconstruction reconstruction) {
		return 1;
	}

	@Override
	public SolutionComparable getClonedSolution() {
		CophylogenyReconstruction clone = new CophylogenyReconstruction(this.hostTree.cloneTree(), 
				null == this.hostMapping ? null : this.hostMapping.getClonedHostMappingList(), 
				this.parasiteMapping.getClonedParasiteMappingList(), 
				this.events.getClonedEvent());
		return clone;
	}
}
