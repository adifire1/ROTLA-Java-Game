package model.combat;

import model.adventures.Adventurer;
import model.creatures.Creature;
import util.Utilities;

// Strategy for combat.
public class ExpertStrategy implements ICombatStrategy {

	@Override
	public boolean fight(Adventurer ad, Creature c, int extra) {
	
		return Utilities.fight(ad, c, extra + 2, 0);
	
	}

}
