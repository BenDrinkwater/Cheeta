package app.parameters;

public class Parameter {
	private SupportedOption AssociatedOption;
	
	private String Value;
	
	public Parameter(SupportedOption associatedOption, String value) {
		this.AssociatedOption = associatedOption;
		this.Value = value;
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
	
	private boolean validateArrayParameter() {
		if (this.Value.length() < 3) {
			return false;
		}
		if (!this.Value.subSequence(0, 2).equals("@{")) {
			return false;
		}
		if (this.Value.charAt(this.Value.length()-1) != '}') {
			return false;
		}
		String arrayAsString = this.Value.subSequence(0, this.Value.length()-1).toString();
		return arrayAsString.split(",").length > 0;
	}
}
