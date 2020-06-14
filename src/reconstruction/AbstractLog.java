package reconstruction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AbstractLog {

	protected String filename;
	protected File file;
	
	public AbstractLog(String filename) throws FileNotFoundException {
		this.filename = filename;
		this.file = new File(this.filename);
	}
	
	public List<String> readLog() {
		List<String> list = new LinkedList<String>();
		try {
			Scanner scanner = new Scanner(this.file);
			 
	        while (scanner.hasNextLine()) {
	        	list.add(scanner.nextLine());
	        }
	        scanner.close();
		}
		catch (FileNotFoundException e) {
		}
		return list;
	}
	
	protected String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);		
	}
	
	public void write(String output) {
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(this.file ,true));
	        out.write(output + "\n");
	        out.close();
	    } 
		catch (IOException e) {
			return;
		}		
	}
	
}
