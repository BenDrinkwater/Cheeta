package metaheuristics.ga;

import java.util.Map;

import tree.Node;

public class InvalidNodeTimingsException extends RuntimeException {

	private static final long serialVersionUID = -7442817240640866533L;
	private Map<Node, Integer> timings;
	
	public InvalidNodeTimingsException(String message, Map<Node, Integer> timings) {
		super(message);
		this.timings = timings;
	}
	
	public Map<Node, Integer> getInValidTimings() {
		return this.timings;
	}
}
