package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import controller.GameEngine;
import model.adventures.Adventurer;
import model.creatures.Creature;

// Representing each room.
public class Room {

	// Attributes..
	private int level;
	private int x;
	private int y;
	private List<Person> persons; 
	private Map<Direction, Room> rooms;
	private Queue<Treasure> treasures;
	private GameEngine engine;
	
	// Constructor..
	public Room(int level, int x, int y, GameEngine engine) {
		
		this.level = level;
		this.x = x;
		this.y = y;
		persons = new ArrayList<>();
		rooms = new HashMap<>();
		treasures = new LinkedList<>();
		this.engine = engine;
		
	}
	
	public Room getRandomRoom() {
		
		return engine.getRandomRoom();
		
	}
	
	// Adding room.
	public void addRoom(Direction direction, Room room) {
		
		rooms.put(direction, room);
		
	}
	
	public void addTreasure(Treasure treasure) {
		
		this.treasures.add(treasure);
		
	}
	
	public Treasure fetchTreasure() {
		
		if (treasures.isEmpty()) {
			return null;
		}
		return treasures.peek();
		
	}
	
	public Treasure removeTreasure() {
		
		if (treasures.isEmpty()) {
			return null;
		}
		return treasures.poll();
		
	}
	
	// Adding the person.
	public void addPerson(Person person) {
		
		if (person.isDead()) {
			return;
		}
		persons.add(person);
		
	}
	
	// Removing
	public void removePerson(Person person) {
		
		persons.remove(person);
		
	}
	
	public boolean isCenterRoom() {
		
		return x == 1 && y == 1;
		
	}
	
	// Get persons.
	public List<Person> getPersons() {
		
		return new ArrayList<>(persons);
		
	}
	
	public List<Direction> getDirections() {
		
		return new ArrayList<>(rooms.keySet());
		
	}
	
	public Room getRoom(Direction direction) {
		
		return rooms.get(direction);
		
	}
	
	public Collection<Room> getAdjacentRooms() {
		
		return rooms.values();
		
	}
	
	public boolean hasAdventurer() {
		
		for (Person each: persons) {
			if (each instanceof Adventurer) {
				return true;
			}
		}
		return false;
		
	}
	
	public List<Creature> getCreatures() {
		
		List<Creature> creatures = new ArrayList<>();
		for (Person each: persons) {
			if (each instanceof Creature) {
				creatures.add((Creature) each);
			}
		}
		return creatures;
		
	}
	
	@Override
	public String toString() {
		
		return level + "-" + x + "-" + y;
		
	}
	
}
