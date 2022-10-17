package model.search;

import model.adventures.Adventurer;
import util.Utilities;

// Strategy for search.
public class QuickStrategy implements ISearchStrategy {

	@Override
	public void findTreasure(Adventurer ad) {
		
		if (Utilities.diceRolls() > 6) {
			ad.extractTreasure();
		}
	
	}

}
