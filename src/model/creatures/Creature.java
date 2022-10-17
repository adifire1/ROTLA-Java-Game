package model.creatures;

import model.Person;

// Representing the creature.
public abstract class Creature extends Person {

	// Constructor..
	public Creature(String name) {
		super(name, Person.HEALTH);
	}

}
