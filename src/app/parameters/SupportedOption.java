package app.parameters;

public class SupportedOption {

	private String Key;
	
	private SupportedDataType SupportedDataType;
	
	private boolean IsOptional;
	
	private String DefaultValue;
	
	public SupportedOption (String key, SupportedDataType supportedDataType, boolean isOptional) {
		this.Key = key;
		this.SupportedDataType = supportedDataType;
		this.IsOptional = isOptional;
	}	
	
	public SupportedOption (String key, SupportedDataType supportedDataType, boolean isOptional, String defaultValue) {
		this (key, supportedDataType, isOptional);
		this.DefaultValue = defaultValue;
	}

	public String getKey() {
		return Key;
	}
	
	public SupportedDataType getSupportedDataType() {
		return SupportedDataType;
	}
	
	public boolean IsOptional() {
		return this.IsOptional;
	}
	
	@Override
	public int hashCode() {
		return this.getKey().hashCode();
	}
}
