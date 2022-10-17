package model.creatures;

import java.util.List;

import model.Direction;
import model.Logger;
import model.Room;
import util.Utilities;

// Orbiter creature.
public class Orbiter extends Creature {

	// Constructor..
	public Orbiter() {
		
		super("Orbiter");
		
	}

	@Override
	public void move() {
		
		if (!currentRoom.hasAdventurer()) {
			List<Direction> directions = currentRoom.getDirections();
			Room room;
			while (true) {
				
				room = currentRoom.getRoom(directions.get(Utilities.getRandom(directions.size())));
				if (!room.isCenterRoom()) {
					break;
				}
				
			}
			currentRoom.removePerson(this);
			room.addPerson(this);
			Logger.log(this.getType() + " move to the room '"+room+"'");
			setCurrentRoom(room);
		}
		
	}

	@Override
	public String getType() {
		
		return "Orbiter";
	
	}
	
	@Override
	public String toString() {
		
		return "O";
		
	}

}
