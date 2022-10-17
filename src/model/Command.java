package model;

// Interface for command execution..
public interface Command {

	public abstract void fightCommand();
	public abstract void moveCommand(Direction direction);
	public abstract String celebrateCommand();
	public abstract void searchCommand();
	
}
