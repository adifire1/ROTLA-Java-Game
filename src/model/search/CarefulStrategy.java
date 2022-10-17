package model.search;

import model.adventures.Adventurer;
import util.Utilities;

// Strategy for combat.
public class CarefulStrategy implements ISearchStrategy {

	@Override
	public void findTreasure(Adventurer ad) {
	
		if (Utilities.diceRolls() > 4) {
			ad.extractTreasure();
		}
		
	}

}
