package app.parameters;

public class SupportedOption {

	private String key;
	
	private SupportedDataType supportedDataType;
	
	private boolean isOptional;
	
	private String defaultValue;
	
	private String examples;
	
	private String helpMessage;
	
	public SupportedOption (String key, SupportedDataType supportedDataType, boolean isOptional, String examples, String helpMessage) {
		this.key = key;
		this.supportedDataType = supportedDataType;
		this.isOptional = isOptional;
		this.examples = examples;
		this.helpMessage = helpMessage;
		this.defaultValue = null;
	}
	
	public SupportedOption (String key, SupportedDataType supportedDataType, boolean isOptional, String examples, String helpMessage, String defaultValue) {
		this (key, supportedDataType, isOptional, examples, helpMessage);
		this.defaultValue = defaultValue;
	}

	public String getKey() {
		return key;
	}
	
	public SupportedDataType getSupportedDataType() {
		return supportedDataType;
	}
	
	public boolean IsOptional() {
		return this.isOptional;
	}
	
	public String getExample() {
		return this.examples;
	}
	
	public String getHelpMessage() {
		return this.helpMessage;
	}
	
	
	public String getDefaultValue() {
		return this.defaultValue;
	}
	
	@Override
	public int hashCode() {
		return this.getKey().hashCode();
	}
}
