package app;

import java.util.List;

import app.algorithm.ExecutionEngine;
import app.parameters.Parameter;
import app.parameters.ParameterParser;

public class Chetta {

	public static void main(String[] args) {
		ParameterParser parameterParser = new ParameterParser(args);
		ExecutionEngine executionEngine = null;
		try {
			List<Parameter> parameters = parameterParser.GetParameters();
			executionEngine = new ExecutionEngine(parameters, parameterParser.getSupportedOptions());
		}
		catch(Exception e) {
			System.out.println(parameterParser.LoadErrorMessage());
			return;
		}
		System.out.println(executionEngine.run());
	}
	
}
