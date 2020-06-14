package app.parameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SupportedOptions implements Iterable<SupportedOption> {

	private List<SupportedOption> SupportedOptions;
	
	public SupportedOptions() {
		SupportedOptions = new ArrayList<SupportedOption>();
		SupportedOptions.add(new SupportedOption("Algorithm", SupportedDataType.String, false, "[INM, RaSCAl, TreeCollapse]", "Select either Improved Node Mapping (INM), RaSCAl or TreeCollapse as the tree reconcilation algorihtm."));
		SupportedOptions.add(new SupportedOption("CostScheme", SupportedDataType.Array, false, "@{0,1,1,2}", "This array represents the Costs for a Codivergence, Duplication, Loss and Host Switch Event."));
		SupportedOptions.add(new SupportedOption("File", SupportedDataType.String, false, "PocketGopherChewingLice2002.nex", "The Path to the file to Process. Currently Chetta supports .tree and .nex (both Jane and Core-PA formats)."));
		SupportedOptions.add(new SupportedOption("MetaHeuristic", SupportedDataType.String, true, "[GA]", "Currently Chetta only supports the Genetic Algorihtm MetaHeuristic.", "GA"));
		SupportedOptions.add(new SupportedOption("MetaHeuristicSettings", SupportedDataType.Array, true, "@{100,100}", "Population and Iteration Size for Genetic Algorithm Traversal", "@{100,100}"));
		SupportedOptions.add(new SupportedOption("RaSCAlSampling", SupportedDataType.String, true, "[Constant4, Log, SquareRoot]", "Sampling Size for the RaSCAl Algorithm. Default is Log.", "Log"));
		SupportedOptions.add(new SupportedOption("RaSCAlStrategy", SupportedDataType.String, true, "[Hybird, Greedy, Random]", "Sampling Strategy for the RaSCAl Algorithm. Default is Random.", "Random"));
		SupportedOptions.add(new SupportedOption("MultiThreaded", SupportedDataType.String, true, "[true, false]", "Execute MetaHeuristic accross multiple threads. Default is True.", "true"));
	}
	
	public String ErrorMessage() {
		StringBuilder errorBuilder = new StringBuilder();
		
		errorBuilder.append("Cheeta Supports the following Command Line Arguments\n");
		errorBuilder.append("\n");
		
		for (SupportedOption option : this.SupportedOptions) {
			String status = option.IsOptional() ? "[Optional]" : "[Required]";
			errorBuilder.append(status);
			errorBuilder.append(" --");
			errorBuilder.append(option.getKey());
			errorBuilder.append(" ");
			errorBuilder.append(option.getExample());
			errorBuilder.append(", ");
			errorBuilder.append(option.getHelpMessage());
			errorBuilder.append("\n");
		}

		errorBuilder.append("\n");
		errorBuilder.append("e.g. Chetta.jar --Algorithm INM --CostScheme @{0,1,1,2} --File PocketGopherChewingLice2002.nex --MetaHeuristicSettings @{100,100}");	
		errorBuilder.append("\n");
		
		return errorBuilder.toString();
	}
	
	public SupportedOption getSupportedOption(String key) {
		if (key.length() < 2) {
			return null;
		}
		for (SupportedOption option : this.SupportedOptions) {
			if (option.getKey().equalsIgnoreCase(key.substring(2))) {
				return option;
			}
		}
		return null;
	}
	
	public int numberOfRequiredOptions() {
		int count = 0;
		for (SupportedOption option : this.SupportedOptions) {
			if (!option.IsOptional()) {
				count++;
			}
		}
		return count;
	}

	@Override
	public Iterator<SupportedOption> iterator() {
		return SupportedOptions.iterator();
	}
	
}
