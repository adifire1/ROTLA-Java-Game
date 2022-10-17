package model.creatures;

import java.util.List;

import model.Direction;
import model.Logger;
import model.Room;
import util.Utilities;

// Blinker creature.
public class Blinker extends Creature {

	// Constructor..
	public Blinker() {
		
		super("Blinker");
		
	}

	@Override
	public void move() {
		
		List<Direction> directions = currentRoom.getDirections();
		Room newRoom = currentRoom.getRoom(directions.get(Utilities.getRandom(directions.size())));
		currentRoom.removePerson(this);
		newRoom.addPerson(this);
		Logger.log(this.getType() + " move to the room '"+newRoom+"'");
		this.setCurrentRoom(newRoom);
		
	}

	@Override
	public String getType() {
		
		return "Blinker";
	
	}
	
	@Override
	public String toString() {
		
		return "B";
		
	}

}
