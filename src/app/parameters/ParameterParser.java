package app.parameters;

import java.util.ArrayList;
import java.util.List;

public class ParameterParser {

	private final String[] Arguments;
	private SupportedOptions SupportedOptions;
	private List<Parameter> Parameters;
	
	public ParameterParser(String[] args) {
		this.Arguments = args;
		SupportedOptions = new SupportedOptions();
	}
	
	public List<Parameter> GetParameters() {
		this.Parameters = LoadParameters();
		return this.Parameters;
	}
	
	public String LoadErrorMessage() {
		return this.SupportedOptions.ErrorMessage();
	}
	
	public SupportedOptions getSupportedOptions() {
		return this.SupportedOptions;
	}
	
	private List<Parameter> LoadParameters() {
		List<Parameter> parameters = new ArrayList<Parameter>();
		for (int i = 0; i < Arguments.length -1; ++i) {
			SupportedOption option = SupportedOptions.getSupportedOption(Arguments[i]);
			if (option == null) {
				continue;
			}
			parameters.add(new Parameter(option, Arguments[i+1]));
			++i;
		}
		return parameters;
	}	
	
	public boolean IsValidParameterSet() {
		boolean isValid = true;
		for (Parameter parameter : this.Parameters) {
			isValid = isValid && parameter.valueMatchesPermittedDataType();
		}
		isValid = isValid && nubmberOfRequiredOptions() == this.SupportedOptions.numberOfRequiredOptions();
		return isValid;
	}
	
	private int nubmberOfRequiredOptions() {
		int count = 0;
		for (Parameter parameter : this.Parameters) {
			if (!parameter.getAssociatedOption().IsOptional()) {
				count++;
			}
		}
		return count;
	}
}
