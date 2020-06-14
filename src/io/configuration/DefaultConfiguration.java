package io.configuration;

public class DefaultConfiguration implements Configuration {

	protected CostScheme costScheme;
	
	public DefaultConfiguration() {
		this.costScheme = new CostScheme();
	}

	@Override
	public CostScheme getCostScheme() {
		return this.costScheme;
	}	
}
