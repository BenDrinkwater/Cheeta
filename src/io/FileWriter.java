package io;

import java.io.File;

import tanglegram.CoevolutionaryHistory;
import io.nexus.NexusFile;
import io.nexus.NexusFileHandlingException;
import io.nexus.corepa.CorePaNexusFile;
import io.nexus.jane.JaneNexusFile;
import io.tree.TreeFileWriter;

public class FileWriter {

	public enum FileType {
		Jane,
		CorePa,
		Tree,
		Undefined
	}

	private String fileName;
	private FileType fileType;

	public FileWriter(String fileName, FileWriter.FileType fileType) {
		this.fileName = fileName;
		this.fileType = fileType;
	}
	
	public boolean save(CoevolutionaryHistory history) {
		if (FileType.Tree.equals(this.fileType)) {
			// TODO come back and clean this interface up I think
			TreeFileWriter writer = new TreeFileWriter(history, new File(this.fileName));
			writer.write();
		}
		try {
			NexusFile file = this.getFileType();
			if (null == file) {
				return false;
			}
			return file.write(history);
		} 
		catch (NexusFileHandlingException e) {
			return false;
		}
		
	}

	private NexusFile getFileType() throws NexusFileHandlingException {
		switch(this.fileType) {
			case Jane: return new JaneNexusFile(this.fileName);
			case CorePa: return new CorePaNexusFile(this.fileName);
			default: return null;
		}
	}
}
