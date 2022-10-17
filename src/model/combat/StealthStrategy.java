package model.combat;

import model.adventures.Adventurer;
import model.creatures.Creature;
import util.Utilities;

// Strategy for combat.
public class StealthStrategy implements ICombatStrategy {

	@Override
	public boolean fight(Adventurer ad, Creature c, int extra) {
	
		if (Utilities.tossCoin()) {
			
			int currentRolls = Utilities.diceRolls() + extra;
			int creatureRolls = Utilities.diceRolls();
			
			if (currentRolls > creatureRolls) {
				c.doDamage();
				return true;
			}
			if (Utilities.tossCoin()) {
				ad.doDamage();
				ad.damage++;
			}
			return false;
			
		}
		return false;
		
	}

}
