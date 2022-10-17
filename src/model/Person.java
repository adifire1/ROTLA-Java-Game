package model;

// Each person in the project.
public abstract class Person {

	// Attributes..
	public String name;
	protected int health;
	protected Room currentRoom;
	
	// constants.
	public static final int HEALTH = 3;

	// Constructor..
	public Person(String name, int healthCapacity) {
		
		this.name = name;
		this.health = healthCapacity;
		this.currentRoom = null;
		
	}
	
	// abstract.
	public abstract void move();
	
	// Check if person is dead.
	public boolean isDead() {
		
		return health <= 0;
		
	}
	
	public int getHealth() {
		
		return health;
		
	}

	public Room getCurrentRoom() {
		
		return currentRoom;
		
	}

	public void setCurrentRoom(Room currentRoom) {
		
		this.currentRoom = currentRoom;
		
	}
	
	public void increaseHealth() {
		
		health++;
		
	}
	
	public void doDamage() {
		
		health--;
		
	}

	public void doDamage(int healthSize) {
		
		health -= healthSize;
		
	}
	
	// abstracts
	public abstract String getType();
	
}
