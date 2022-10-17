package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

// To track the logging. 
public class Tracker {

	// Attributes..
	private static final String FILENAME = "logger.txt";
	private static Tracker tracker = null;
	
	// Singleton.
	private Tracker() {
		
	}
	
	// Instance of tracker.
	public static Tracker getInstance() {
		
		if (tracker == null) {
			tracker = new Tracker();
		}
		return tracker;
		
	}
	
	public void track(String line) {
		
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true));
			writer.println((new Date()) + " -> " + line);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
