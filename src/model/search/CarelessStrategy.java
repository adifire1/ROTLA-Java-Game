package model.search;

import model.adventures.Adventurer;
import util.Utilities;

// Strategy for search.
public class CarelessStrategy implements ISearchStrategy {

	@Override
	public void findTreasure(Adventurer ad) {
		
		if (Utilities.diceRolls() > 7) {
			ad.extractTreasure();
		}
	
	}

}
