package app.parameters;

public class Parameter {
	private SupportedOption AssociatedOption;
	
	private String value;
	
	public Parameter(SupportedOption associatedOption, String value) {
		this.AssociatedOption = associatedOption;
		this.value = value;
	}
	
	public boolean valueMatchesPermittedDataType() {
		if(this.AssociatedOption.getSupportedDataType() == SupportedDataType.Array) {
			return validateArrayParameter();
		}
		return true;
	}
	
	public SupportedOption getAssociatedOption() {
		return this.AssociatedOption;
	}
	
	public String getValue() {
		return this.value;
	}
	
	private boolean validateArrayParameter() {
		if (this.value.length() < 3) {
			return false;
		}
		if (!this.value.subSequence(0, 2).equals("@{")) {
			return false;
		}
		if (this.value.charAt(this.value.length()-1) != '}') {
			return false;
		}
		String arrayAsString = this.value.subSequence(0, this.value.length() - 1).toString();
		return arrayAsString.split(",").length > 0;
	}
}
