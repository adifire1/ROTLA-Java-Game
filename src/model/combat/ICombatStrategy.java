package model.combat;

import model.adventures.Adventurer;
import model.creatures.Creature;

// combat strategy..
public interface ICombatStrategy {

	public abstract boolean fight(Adventurer ad, Creature c, int extra);
	
}
