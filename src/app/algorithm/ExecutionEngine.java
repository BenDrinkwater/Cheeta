package app.algorithm;

import java.util.List;

import app.parameters.Parameter;
import io.FileLoader;
import metaheuristics.ga.NodeMappingGASolver;
import metaheuristics.ga.SamplingNodeMappingGASolver;
import metaheuristics.ga.Solver;
import metaheuristics.ga.TreeCollapseGASolver;
import reconstruction.CophylogenyReconstruction;
import reconstruction.solver.nm.RestrictedReconstructionGenerator;
import reconstruction.solver.nm.RestrictedReconstructionGenerator.RestrictionMode;
import tanglegram.CoevolutionaryHistory;
import util.SolutionSet;
import util.reconstruction.RestrictedReconstructionSet;
import util.reconstruction.RestrictedReconstructionSet.Size;

public class ExecutionEngine {
	
	private final List<Parameter> parameters;
	private Solver solver;
	private int[] metaHeuristicSettings;
		
	public ExecutionEngine(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public CophylogenyReconstruction run() {
		ProcessParameters();
		this.solver.initialize();
		SolutionSet<CophylogenyReconstruction> solutions = this.solver.solve(metaHeuristicSettings[1]);
		return solutions.getFirstElement();
	}
	
	protected void ProcessParameters() {
		// TODO implement defaults
		int[] costScheme = null;
		String algorithm = null;
		FileLoader fileLoader = null;
		int[] metaHeuristicSettings = null;
		boolean multiThreaded = true;
		RestrictedReconstructionSet.Size rascalSampling = Size.Log;
		RestrictionMode rascalStrategy = RestrictionMode.Random;
		for (Parameter parameter : this.parameters) {
			if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("CostScheme")) {
				costScheme = ParseIntegerArray(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("Algorithm")) {
				algorithm = parameter.getValue();
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("File")) {
				fileLoader = new FileLoader(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("MetaHeuristic")) {
				// don't need this check right now as Genetic Algorithms is currently only supported model
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("MetaHeuristicSettings")) {
				metaHeuristicSettings = ParseIntegerArray(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("RaSCAlSampling")) {
				rascalSampling = RestrictedReconstructionSet.ParseRestructionSize(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("RaSCAlStrategy")) {
				rascalStrategy = RestrictedReconstructionGenerator.ParseRestructionMode(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("MultiThreaded")) {
				multiThreaded = Boolean.getBoolean(parameter.getValue());
			}
		}
		
		// TODO fix defaults to avoid needing this
		if (algorithm == null) {
			return;
		}
		
		if (fileLoader == null) {
			return;
		}
		
		// TODO move this into a separate section
		
		CoevolutionaryHistory history = fileLoader.getHistory();
		
		this.solver = new TreeCollapseGASolver(history, metaHeuristicSettings[0], costScheme, multiThreaded);
	
		this.solver = new NodeMappingGASolver(history, metaHeuristicSettings[0], costScheme, multiThreaded);
		
		this.solver = new SamplingNodeMappingGASolver(history, metaHeuristicSettings[0], costScheme, multiThreaded, rascalSampling, rascalStrategy);		
	}
	
	public static int[] ParseIntegerArray(String array) {
		array = (String) array.subSequence(2, array.length() - 1);
		String[] elements = array.split(",");
		int[] toReturn = new int[elements.length];
		for (int i = 0; i < elements.length; ++i) {
			toReturn[i] = Integer.parseInt(elements[i]);
		}
		return toReturn;
	}	
}
