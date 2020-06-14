package io.nexus.jane;

import java.io.File;

import tanglegram.CoevolutionaryHistory;

import io.configuration.Configuration;
import io.nexus.NexusFileCommon;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFileReader;
import io.nexus.NexusFileWriter;

public class JaneNexusFile extends NexusFileCommon {
	
	public JaneNexusFile(File file) throws NexusFileHandlingException {
		super(file);
	}
	
	public JaneNexusFile(String fileName) throws NexusFileHandlingException {
		super(fileName);
	}

	@Override
	public NexusFileReader getNexusFileReader() {
		return new JaneNexusFileReader(super.file);
	}

	@Override
	public NexusFileWriter getNexusFileWriter(CoevolutionaryHistory history, Configuration configuration) {
		return new JaneNexusFileWriter(history, super.file, configuration);
	}

}
