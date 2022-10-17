package model.adventures;

import model.Logger;
import model.combat.StealthStrategy;
import model.creatures.Creature;
import model.search.QuickStrategy;
import util.Utilities;

// Sneaker adventurer.
public class Sneaker extends Adventurer {

	// Attributes..
	public Sneaker() {
		
		super("Sneaker", 8, new StealthStrategy(), new QuickStrategy());
		
	}

	@Override
	public void findTreasure() {
		
		if (Utilities.tossCoin()) {
			searchStrategy.findTreasure(this);
		}
		
	}

	@Override
	public void fightCreature(Creature creature) {
		
		if (this.combatStrategy.fight(this, creature, this.processTreasure())) {

			Logger.log(this.getType() + " won the fight!");
			System.out.println(this.getType() + " won the fight against '"+creature.getClass().getSimpleName()+"'");
			
		} else {
			Logger.log(this.getType() + " lost the fight!");
			System.out.println(this.getType() + " lost the fight against '"+creature.getClass().getSimpleName()+"'");	
		}
		
	}

	@Override
	public String getType() {
		
		return "Sneaker";
	
	}
	
	@Override
	public String toString() {
		
		return "S";
		
	}

}
