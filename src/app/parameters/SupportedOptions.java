package app.parameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SupportedOptions implements Iterable<SupportedOption> {

	private List<SupportedOption> SupportedOptions;
	
	public SupportedOptions() {
		SupportedOptions = new ArrayList<SupportedOption>();
		SupportedOptions.add(new SupportedOption("CostScheme", SupportedDataType.Array, false));
		SupportedOptions.add(new SupportedOption("Algorithm", SupportedDataType.String, false));
		SupportedOptions.add(new SupportedOption("File", SupportedDataType.String, false));
		SupportedOptions.add(new SupportedOption("MetaHeuristic", SupportedDataType.String, true, "GA"));
		SupportedOptions.add(new SupportedOption("MetaHeuristicSettings", SupportedDataType.Array, true, "${100,100}"));
		SupportedOptions.add(new SupportedOption("RaSCAlSampling", SupportedDataType.String, true));
		SupportedOptions.add(new SupportedOption("RaSCAlStrategy", SupportedDataType.String, true));
		SupportedOptions.add(new SupportedOption("MultiThreaded", SupportedDataType.String, true, "true"));
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
