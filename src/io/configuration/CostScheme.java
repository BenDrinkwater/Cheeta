package io.configuration;

public class CostScheme {

	protected int codivergenceEventCost;
	protected int duplicationEventCost;
	protected int sortingEventCost;
	protected int switchEventCost;
	
	public CostScheme() {
		this.codivergenceEventCost = 0;
		this.duplicationEventCost = 1;
		this.sortingEventCost = 1;
		this.switchEventCost = 2;
	}
	
	public int getCostForCodivergence() {
		return this.codivergenceEventCost;
	}
	
	public int getCostForDuplication() {
		return this.duplicationEventCost;
	}
	
	public int getCostForSorting() {
		return this.sortingEventCost;
	}
	
	public int getCostForSwitch() {
		return this.switchEventCost;
	}	
}
