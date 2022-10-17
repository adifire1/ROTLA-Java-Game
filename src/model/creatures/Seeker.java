package model.creatures;

import model.Logger;
import model.Room;

// Seeker creature.
public class Seeker extends Creature {

	// Constructor..
	public Seeker() {
		
		super("Seeker");
		
	}

	@Override
	public void move() {
		
		for (Room room: currentRoom.getAdjacentRooms()) {
			if (room.hasAdventurer()) {
				currentRoom.removePerson(this);
				room.addPerson(this);
				setCurrentRoom(room);
				Logger.log(this.getType() + " move to the room '"+room+"'");
				break;
			}
		}
		
	}

	@Override
	public String getType() {
		
		return "Seeker";
	
	}
	
	@Override
	public String toString() {
		
		return "S";
		
	}

}
