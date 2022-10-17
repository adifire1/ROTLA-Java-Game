package controller.factory;

import model.creatures.Blinker;
import model.creatures.Creature;
import model.creatures.Orbiter;
import model.creatures.Seeker;

// To create objects using the factory model
public class CreatureFactory {

	// Creature Factory Methods..
	public static Creature createBlinker() {
		
		return new Blinker();
		
	}
	
	public static Creature createOrbiter() {
		
		return new Orbiter();
		
	}
	
	public static Creature createSeeker() {
		
		return new Seeker();
		
	}
	
}
