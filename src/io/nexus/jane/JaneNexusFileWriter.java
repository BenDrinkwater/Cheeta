package io.nexus.jane;

import java.io.File;

import association.Association;

import tanglegram.CoevolutionaryHistory;

import io.configuration.Configuration;
import io.configuration.DefaultConfiguration;
import io.nexus.NexusFileWriter;
import io.nexus.NexusStringFormat;

public class JaneNexusFileWriter extends NexusFileWriter {

	protected JaneNexusFileWriter(CoevolutionaryHistory history, File file) {
		super(history, file, new DefaultConfiguration());
	}

	
	public JaneNexusFileWriter(CoevolutionaryHistory history, File file, Configuration configuration) {
		super(history, file, configuration);
	}
	
	@Override
	public boolean generateOutputString() {
		super.outputString = "#nexus\n" + this.getHostTreeString() +  this.getParasiteTreeString() + this.getAssociationsString();
		return true;
	}

	protected String getHostTreeString() {
		StringBuilder builder = new StringBuilder();
		builder.append("begin host;");
		builder.append("\n");
		builder.append("tree host =");
		builder.append(this.history.getHostTree().getNexusString(NexusStringFormat.Jane));
		builder.append(";");
		builder.append("\n");	
		builder.append("endblock;");
		builder.append("\n");	
		return builder.toString();
	}

	protected String getParasiteTreeString() {
		StringBuilder builder = new StringBuilder();
		builder.append("begin parasite;");
		builder.append("\n");
		builder.append("tree parasite =");
		builder.append(this.history.getParasiteTree().getNexusString(NexusStringFormat.Jane));
		builder.append(";");
		builder.append("\n");	
		builder.append("endblock;");
		builder.append("\n");	
		return builder.toString();
	}
	
	protected String getAssociationsString() {
		StringBuilder builder = new StringBuilder();
		builder.append("begin distribution;");
		builder.append("\n");
		builder.append("Range");
		builder.append("\n");
		for (Association association : this.history.getAssociations()) {
			builder.append(this.history.getParasiteTree().getNodeName(association.getSecondNode()) + " : " + this.history.getHostTree().getNodeName(association.getFirstNode()));
			builder.append(", ");
		}
		builder.delete(builder.length()-2, builder.length());
		builder.append(";");
		builder.append("\n");	
		builder.append("endblock;");	
		return builder.toString();
	}
}
