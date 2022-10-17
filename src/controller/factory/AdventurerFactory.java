package controller.factory;

import model.adventures.Adventurer;
import model.adventures.Brawler;
import model.adventures.Runner;
import model.adventures.Sneaker;
import model.adventures.Thief;

// To create objects using factory model
public class AdventurerFactory {

	// Creature Factory Methods..
	public static Adventurer createThief() {
		
		return new Thief();
		
	}
	
	public static Adventurer createBrawler() {
		
		return new Brawler();
		
	}
	
	public static Adventurer createSneaker() {
		
		return new Sneaker();
		
	}
	
	public static Adventurer createRunner() {
		
		return new Runner();
		
	}
	
	public static Adventurer createAdventurer(int option) {
		
		switch (option) {
		case 1:
			return createBrawler();
		case 2:
			return createRunner();
		case 3:
			return createSneaker();
		case 4:
			return createThief();
		}
		return null;
		
	}
	
}
