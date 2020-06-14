package io.nexus;

import io.nexus.corepa.CorePaNexusFile;
import io.nexus.jane.JaneNexusFile;

public class NexusFileIdentifier {

	private String fileName;
	
	public NexusFileIdentifier(String fileName) {
		this.fileName = fileName;
	}
	
	public NexusFile loadNexusFile() {
		NexusFile file;	
		try {
			file = new JaneNexusFile(this.fileName);
			file.read();
			return file;
		} 
		catch (NexusParsingException outerE) {
			try {
				file = new CorePaNexusFile(this.fileName);
				file.read();
				return file;
			} 
			catch (NexusParsingException innerE) {
				return null;
			}
		}

	}
}
