package app.algorithm;

import java.util.List;

import app.parameters.Parameter;
import app.parameters.SupportedOption;
import app.parameters.SupportedOptions;
import io.FileLoader;
import metaheuristics.ga.NodeMappingGASolver;
import metaheuristics.ga.SamplingNodeMappingGASolver;
import metaheuristics.ga.Solver;
import metaheuristics.ga.TreeCollapseGASolver;
import reconstruction.solver.nm.RestrictedReconstructionGenerator;
import reconstruction.solver.nm.RestrictedReconstructionGenerator.RestrictionMode;
import tanglegram.CoevolutionaryHistory;
import util.reconstruction.RestrictedReconstructionSet;

public class ExecutionParameters {

	private final List<Parameter> parameters;
	private final SupportedOptions supportedOptions;
	
	private int[] costScheme;
	
	private String algorithm; 
	
	private FileLoader fileLoader;
	
	int[] metaHeuristicSettings;
	
	boolean multiThreaded;
	
	RestrictedReconstructionSet.Size rascalSampling;
	
	RestrictionMode rascalStrategy;
	
	public ExecutionParameters(List<Parameter> parameters, SupportedOptions supportedOptions) {
		this.parameters = parameters;
		this.supportedOptions = supportedOptions;
	}
	
	public Solver getSolver() {
		SetDefaultIfTheyExist();
		SetValues();
		fileLoader.loadFile();
		CoevolutionaryHistory history = fileLoader.getHistory();
		
		if (algorithm.equalsIgnoreCase("TreeCollapse")) {
			return new TreeCollapseGASolver(history, metaHeuristicSettings[0], costScheme, multiThreaded);
		}
		else if (algorithm.equalsIgnoreCase("Rascal")) {
 			return new SamplingNodeMappingGASolver(history, metaHeuristicSettings[0], costScheme, multiThreaded, rascalSampling, rascalStrategy);
		}
		else if (algorithm.equalsIgnoreCase("INM")) {			
			return new NodeMappingGASolver(history, metaHeuristicSettings[0], costScheme, multiThreaded);	
		}
		
		return null;
	}
	
	public int numberOfExecutions() {
		return metaHeuristicSettings[1];
	}
	
	public void SetDefaultIfTheyExist() {
		for (SupportedOption option : this.supportedOptions) {
			String defaultValue = option.getDefaultValue();
			if (option.getKey().equalsIgnoreCase("CostScheme")) {
				costScheme = defaultValue != null ? ParseIntegerArray(defaultValue) : null;
			}			
			else if (option.getKey().equalsIgnoreCase("Algorithm")) {
				algorithm = defaultValue != null ? defaultValue : null;
			}			
			else if (option.getKey().equalsIgnoreCase("File")) {
				fileLoader = defaultValue != null ? new FileLoader(defaultValue) : null;
			}			
			else if (option.getKey().equalsIgnoreCase("MetaHeuristic")) {
				// don't need this check right now as Genetic Algorithms is currently only supported model
			}			
			else if (option.getKey().equalsIgnoreCase("MetaHeuristicSettings")) {
				metaHeuristicSettings = ParseIntegerArray(defaultValue);
			}			
			else if (option.getKey().equalsIgnoreCase("RaSCAlSampling")) {
				rascalSampling = RestrictedReconstructionSet.ParseRestructionSize(defaultValue);
			}			
			else if (option.getKey().equalsIgnoreCase("RaSCAlStrategy")) {
				rascalStrategy = RestrictedReconstructionGenerator.ParseRestructionMode(defaultValue);
			}			
			else if (option.getKey().equalsIgnoreCase("MultiThreaded")) {
				multiThreaded = Boolean.getBoolean(defaultValue);
			}
		}
	}
	
	public void SetValues() {
		for (Parameter parameter : this.parameters) {
			if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("CostScheme")) {
				this.costScheme = ParseIntegerArray(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("Algorithm")) {
				this.algorithm = parameter.getValue();
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("File")) {
				this.fileLoader = new FileLoader(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("MetaHeuristic")) {
				// don't need this check right now as Genetic Algorithms is currently only supported model
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("MetaHeuristicSettings")) {
				this.metaHeuristicSettings = ParseIntegerArray(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("RaSCAlSampling")) {
				this.rascalSampling = RestrictedReconstructionSet.ParseRestructionSize(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("RaSCAlStrategy")) {
				this.rascalStrategy = RestrictedReconstructionGenerator.ParseRestructionMode(parameter.getValue());
			}			
			else if (parameter.getAssociatedOption().getKey().equalsIgnoreCase("MultiThreaded")) {
				this.multiThreaded = Boolean.getBoolean(parameter.getValue());
			}
		}
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
