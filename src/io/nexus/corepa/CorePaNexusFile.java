package io.nexus.corepa;

import io.configuration.Configuration;
import io.nexus.NexusFileCommon;
import io.nexus.NexusFileHandlingException;
import io.nexus.NexusFileReader;
import io.nexus.NexusFileWriter;

import java.io.File;

import tanglegram.CoevolutionaryHistory;

public class CorePaNexusFile extends NexusFileCommon {

	public CorePaNexusFile(File file) throws NexusFileHandlingException {
		super(file);
	}
	
	public CorePaNexusFile(String fileName) throws NexusFileHandlingException {
		super(fileName);
	}

	@Override
	public NexusFileReader getNexusFileReader() {
		return new CorePaNexusFileReader(super.file);
	}

	@Override
	public NexusFileWriter getNexusFileWriter(CoevolutionaryHistory history, Configuration configuration) {
		return new CorePaNexusFileWriter(history, super.file, configuration);
	}
}
