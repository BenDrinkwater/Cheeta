package app.algorithm;

import java.util.List;

import app.parameters.Parameter;
import app.parameters.SupportedOptions;
import metaheuristics.ga.Solver;
import reconstruction.CophylogenyReconstruction;
import util.SolutionSet;

public class ExecutionEngine {
	
	private final ExecutionParameters executionParameters;
	private final Solver solver;
	
	public ExecutionEngine(List<Parameter> parameters, SupportedOptions supportedOptions) {
		this.executionParameters = new ExecutionParameters(parameters, supportedOptions);
		this.solver = this.executionParameters.getSolver();
	}
	
	public CophylogenyReconstruction run() {
		this.solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutions = this.solver.solve(this.executionParameters.numberOfExecutions());
		return solutions.getFirstElement();
	}
}
