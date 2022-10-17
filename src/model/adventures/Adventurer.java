package model.adventures;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Command;
import model.Direction;
import model.Logger;
import model.Person;
import model.Room;
import model.Treasure;
import model.celebrations.DanceCelebration;
import model.celebrations.ICelebration;
import model.celebrations.JumpCelebration;
import model.celebrations.ShoutCelebration;
import model.celebrations.SpinCelebration;
import model.combat.ICombatStrategy;
import model.creatures.Creature;
import model.search.ISearchStrategy;
import util.Utilities;

// Adventurer..
public abstract class Adventurer extends Person implements Command {

	// Attributes..
	public int damage;
	protected int treasure;
	protected Set<Treasure> treasures;
	protected ICombatStrategy combatStrategy;
	protected ISearchStrategy searchStrategy;
	protected Set<Treasure> found;

	// Constructor..
	public Adventurer(String name, int healthCapacity, ICombatStrategy c, ISearchStrategy s) {

		super(name, healthCapacity);
		this.combatStrategy = c;
		this.searchStrategy = s;
		this.damage = 0;
		this.treasure = 0;
		found = new HashSet<>();
		treasures = new HashSet<>();

	}

	public String getDetail() {

		return name + " - " + treasure + " Treasure(s) - " + damage + " Damage";

	}
	
	public String getTreasureString() {
		
		String data = "";
		for (Treasure t: treasures) {
			String value = t.toString().toLowerCase();
			value = Character.toUpperCase(value.charAt(0)) + 
					value.substring(1);
			data += value + ", ";
		}
		return data.isEmpty() ? "" : data.substring(0, data.length() - 2);
		
	}
	
	public String getTreasureFoundString() {
		
		String data = "";
		for (Treasure t: found) {
			String value = t.toString().toLowerCase();
			value = Character.toUpperCase(value.charAt(0)) + 
					value.substring(1);
			data += value + ", ";
		}
		return data.isEmpty() ? "" : data.substring(0, data.length() - 2);
		
	}
	
	public void addTreasure(Treasure treasure) {
		
		found.add(treasure);
		Logger.log(this.getType() + " found the treasure '"+treasure+"'");
		switch (treasure) {
		case TRAP:
			System.out.println("Adventurer '"+name+"' got trapped!");
			doDamage();
			break;
		case PORTAL:
			Room newRoom = currentRoom.getRandomRoom();
			currentRoom.removePerson(this);
			newRoom.addPerson(this);
			this.setCurrentRoom(newRoom);
			System.out.println("Adventurer teleported to another room!");
			break;
		default:
			treasures.add(treasure);
		}
		
	}
	
	public int processTreasure() {
		
		int extraRolls = 0;
		if (treasures.contains(Treasure.ARMOR)) {
			extraRolls++;
			treasures.remove(Treasure.ARMOR);
		//	System.out.println("Adventurer '"+name+"' used the Armor in combat.");
		}
		if (treasures.contains(Treasure.GEM)) {
			extraRolls--;
			treasures.remove(Treasure.GEM);
		//	System.out.println("Adventurer '"+name+"' used the Gem in combat.");
		}
		if (treasures.contains(Treasure.POTION)) {
			extraRolls++;
			super.increaseHealth();
			treasures.remove(Treasure.POTION);
		//	System.out.println("Adventurer '"+name+"' used the Potion in combat.");
		}
		if (treasures.contains(Treasure.SWORD)) {
			extraRolls++;
		//	System.out.println("Adventurer '"+name+"' used the Sword in combat.");
		}
		return extraRolls;
		
	}
	
	public void extractTreasure() {

		Treasure t = currentRoom.fetchTreasure();
		if (t == null) {
			System.out.println("No any treasure found!");
		} else {
			System.out.println("Found treasure: " + t);
			if (!treasures.contains(t)) {
				addTreasure(t);
				System.out.println("\tTreasure Added to Bag");
			} else {
				System.out.println("\tAlready added");
			}
		}
		
	}
	
	public void removeTreasure(Treasure t) {
		
		Logger.log(this.getType() + " use the treasure in fight '"+treasure+"'");
		treasures.remove(t);
		
	}

	@Override
	public void move() {

		List<Direction> directions = currentRoom.getDirections();
		moveCommand(directions.get(Utilities.getRandom(directions.size())));
		
	}
	
	// Fighting the creature..
	public void fight() {
		
		for (Person each: currentRoom.getPersons()) {
			if (each instanceof Creature) {
				Creature creature = (Creature) each;
				fightCreature(creature);
				if (creature.isDead()) {
					currentRoom.removePerson(creature);
				}
				if (this.isDead()) {
					currentRoom.removePerson(this);
					break;
				}
			}
		}
		
	}
	
	public String doCelebration() {
		
		String celebrate = "";
		for (int i = 0; i < Utilities.getRandom(3); i++) {
			ICelebration cel = null;
			switch (Utilities.getRandom(4)) {
			case 0:
				cel = new DanceCelebration();
				break;
			case 1:
				cel = new JumpCelebration();
				break;
			case 2:
				cel = new ShoutCelebration();
				break;
			default:
				cel = new SpinCelebration();
				break;
			}
			celebrate += cel.celebrate();
		}
		Logger.log(this.getType() + " celebrates the win: "+celebrate);
		return celebrate;
		
	}
	
	public int getTreasure() {
		
		return treasure;
		
	}
	
	public Set<Treasure> getFound() {
		return found;
	}
	
	public List<Direction> getDirections() {
		
		return this.currentRoom.getDirections();
		
	}

	// To fight.
	public abstract void fightCreature(Creature creature);
	public abstract void findTreasure();

	@Override
	public void fightCommand() {
		this.fight();
	}

	@Override
	public void moveCommand(Direction direction) {
		
		Room newRoom = currentRoom.getRoom(direction);
		currentRoom.removePerson(this);
		newRoom.addPerson(this);
		Logger.log(this.getType() + " moved to room '"+newRoom+"'");
		this.setCurrentRoom(newRoom);
		
	}

	@Override
	public String celebrateCommand() {
		return this.doCelebration();
	}

	@Override
	public void searchCommand() {
		this.findTreasure();
	}

}
