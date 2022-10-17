package model;

// To log the details..
public class Logger {

	// Attributes..
	private static Tracker tracker = Tracker.getInstance();
	
	public static void log(String log) {
		
		tracker.track(log);
		
	}
	
}
