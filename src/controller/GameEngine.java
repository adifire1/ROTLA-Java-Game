package controller;

import java.util.ArrayList;
import java.util.List;

import controller.factory.CreatureFactory;
import model.Command;
import model.Direction;
import model.Room;
import model.Treasure;
import model.adventures.Adventurer;
import model.creatures.Blinker;
import model.creatures.Creature;
import model.creatures.Orbiter;
import model.creatures.Seeker;
import util.Utilities;

// Game engine..
public class GameEngine implements Command {

	// Attributes..
	private List<Room> rooms;
	private Adventurer adventurer;
	private List<Creature> creatures;
	private Room start;
	private int turn;
	private boolean moved;
	private boolean fought;
	
	// Constructor..
	public GameEngine() {
		
		rooms = new ArrayList<>();
		adventurer = null;
		creatures = new ArrayList<>();
		turn = 1;
		fought = false;
		moved = false;
		
	}
	
	public Room getRandomRoom() {
		
		return rooms.get(Utilities.getRandom(rooms.size()));
		
	}
	
	// Initialize everything..
	public void init() {
		
		// rooms..
		start = new Room(0, 1, 1, this);
		Room[][] level1 = getRooms(1);
		Room[][] level2 = getRooms(2);
		Room[][] level3 = getRooms(3);
		Room[][] level4 = getRooms(4);
		
		start.addRoom(Direction.DOWN, level1[1][1]);
		
		level1[1][1].addRoom(Direction.UP, start);
		level1[1][1].addRoom(Direction.DOWN, level2[1][1]);
		
		level2[1][1].addRoom(Direction.UP, level1[1][1]);
		level2[1][1].addRoom(Direction.DOWN, level3[1][1]);
		
		level3[1][1].addRoom(Direction.UP, level2[1][1]);
		level3[1][1].addRoom(Direction.DOWN, level4[1][1]);
		
		level4[1][1].addRoom(Direction.UP, level3[1][1]);
		
		addRooms(level1);
		addRooms(level2);
		addRooms(level3);
		addRooms(level4);
		
		for (Treasure each: Treasure.values()) {
			for(int i = 0; i < 4; i++) {
				rooms.get(Utilities.getRandom(rooms.size())).addTreasure(each);
			}
		}
		
		// adding orbiters..
		for (int i = 0; i < 4; i++) {
			
			Room room;
			while (true) {
				room = rooms.get(Utilities.getRandom(rooms.size()));
				if (!room.isCenterRoom()) {
					break;
				}
			}
			putInRoom(CreatureFactory.createOrbiter(), room);
			putInRoom(CreatureFactory.createBlinker(), null);
			putInRoom(CreatureFactory.createSeeker(), null);
			
		}
		
		rooms.add(start);
		
	}
	
	public void setAdventurer(Adventurer selected) {
		
		this.adventurer = selected;
		start.addPerson(adventurer);
		adventurer.setCurrentRoom(start);
		
	}
	
	// Print the board on screen.
	public void printBoard() {

		System.out.println("\nRotLA Turn " + turn + ":");

		System.out.printf("%-15s %-10s %-10s %-10s %s%n",
				"Adventurer", "Room", "Damage", "Health", "Treasures Found");
		System.out.printf("%-15s %-10s %-10s %-10s %s%n",
				adventurer.getType(), adventurer.getCurrentRoom().toString(),
				String.valueOf(adventurer.damage), String.valueOf(adventurer.getHealth()), adventurer.getTreasureFoundString());
		System.out.println();
		
		System.out.println("Total Active Creatures: " + this.getTotalCreatures());
		System.out.printf("%-15s %s%n", "Creatures", "Room");
		for (Creature each: creatures) {
			System.out.printf("%-15s %s%n", each.getType(), each.getCurrentRoom().toString());
		}
		System.out.println();
		
	}

	public void printGameBoard() {

		int o = 0, s = 0, b = 0, count = 1;
		System.out.println("RotLA Turn " + turn + ":");
		System.out.println(rooms.get(rooms.size() - 1));
		for (int i = 0; i < rooms.size() - 1; i++) {

			Room room = rooms.get(i);
			System.out.printf("%-20s", room);
			if (count % 3 == 0) {
				System.out.println();
			}
			for (Creature each: room.getCreatures()) {
				if (each instanceof Orbiter) {
					o++;
				} else if (each instanceof Blinker) {
					b++;
				} else if (each instanceof Seeker) {
					s++;
				}
			}
			count++;

		}
		System.out.println();

		System.out.println();
		System.out.println("Orbiters - " + o + " Remaining");
		System.out.println("Seekers - " + s + " Remaining");
		System.out.println("Blinkers - " + b + " Remaining");
		System.out.println();

	}
	
	public int getTotalCreatures() {
		
		int count = 0;
		for (Creature each: creatures) {
			if (!each.isDead()) {
				count++;
			}
		}
		return count;
		
	}
	
	// Put in room.
	private void putInRoom(Creature person, Room room) {
		
		if (room == null) {
			room = rooms.get(Utilities.getRandom(rooms.size()));
		}
		person.setCurrentRoom(room);
		room.addPerson(person);
		creatures.add(person);
		
	}
	
	// Adding rooms.
	private void addRooms(Room[][] level) {
		
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level.length; j++) {
				rooms.add(level[i][j]);
			}
		}
		
	}
	
	// Generate rooms for each level.
	private Room[][] getRooms(int level) {
		
		Room[][] rooms = new Room[3][3];
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms.length; j++) {
				rooms[i][j] = new Room(level, i, j, this);
			}
		}
		rooms[0][0].addRoom(Direction.EAST, rooms[0][1]);
		rooms[0][0].addRoom(Direction.SOUTH, rooms[1][0]);
		
		rooms[0][1].addRoom(Direction.WEST, rooms[0][0]);
		rooms[0][1].addRoom(Direction.EAST, rooms[0][2]);
		rooms[0][1].addRoom(Direction.SOUTH, rooms[1][1]);
		
		rooms[0][2].addRoom(Direction.WEST, rooms[0][1]);
		rooms[0][2].addRoom(Direction.SOUTH, rooms[1][2]);
		
		rooms[1][0].addRoom(Direction.NORTH, rooms[0][0]);
		rooms[1][0].addRoom(Direction.EAST, rooms[1][1]);
		rooms[1][0].addRoom(Direction.SOUTH, rooms[2][0]);
		
		rooms[1][1].addRoom(Direction.NORTH, rooms[0][1]);
		rooms[1][1].addRoom(Direction.SOUTH, rooms[2][1]);
		rooms[1][1].addRoom(Direction.EAST, rooms[1][2]);
		rooms[1][1].addRoom(Direction.WEST, rooms[1][0]);

		rooms[1][2].addRoom(Direction.NORTH, rooms[0][2]);
		rooms[1][2].addRoom(Direction.WEST, rooms[1][1]);
		rooms[1][2].addRoom(Direction.SOUTH, rooms[2][2]);

		rooms[2][0].addRoom(Direction.EAST, rooms[2][1]);
		rooms[2][0].addRoom(Direction.NORTH, rooms[1][0]);
		
		rooms[2][1].addRoom(Direction.WEST, rooms[2][0]);
		rooms[2][1].addRoom(Direction.EAST, rooms[2][2]);
		rooms[2][1].addRoom(Direction.NORTH, rooms[1][1]);
		
		rooms[2][2].addRoom(Direction.WEST, rooms[2][1]);
		rooms[2][2].addRoom(Direction.NORTH, rooms[1][2]);
		
		return rooms;
		
	}

	// To check if game is over or not.
	public boolean gameOver() {
		
		if (adventurer.isDead()) {
			System.out.println("Adventurer is dead!");
			return true;
		}
		if (moved && adventurer.getCurrentRoom().toString().equals("0-1-1")) {
			
			boolean end = true;
			for (Creature each: creatures) {
				if (!each.isDead()) {
					end = false;
					break;
				}
			}
			if (end) {
				System.out.println("GAME END! ALL CREATURES DIED!");
				return true;
			} else {
				end = true;
				for (Treasure each: Treasure.values()) {
					if (!adventurer.getFound().contains(each)) {
						end = false;
						break;
					}
				}
				if (end) {
					System.out.println("GAME END! ALL TREASURES ARE FOUND!!");
					return true;
				}
			}
			
			System.out.println("You Failed and returned without winning");
			return true;
			
		}
		return false;
		
	}

	@Override
	public void fightCommand() {
		
		adventurer.fightCommand();
		fought = true;
		
	}

	@Override
	public void moveCommand(Direction direction) {
		
		if (moved && !fought) {
			System.out.println("You moved without fighting.");
			adventurer.doDamage(adventurer.getCurrentRoom().getCreatures().size());
		}
		adventurer.moveCommand(direction);
		moved = true;
		fought = false;
		for (Creature each: creatures) {
			if (!each.isDead()) {
				each.move();
			}
		}
		turn++;
		
	}

	@Override
	public String celebrateCommand() {
		
		return adventurer.celebrateCommand();
		
	}

	@Override
	public void searchCommand() {
		
		adventurer.searchCommand();
		
	}
	
	public List<Direction> getNextMoveDirections() {
		
		return adventurer.getDirections();
		
	}
	
}
