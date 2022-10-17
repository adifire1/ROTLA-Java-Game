package model.adventures;

import model.Logger;
import model.combat.ExpertStrategy;
import model.creatures.Creature;
import model.search.CarelessStrategy;

// Brawler adventurer.
public class Brawler extends Adventurer {

	// Attributes..
	public Brawler() {
		
		super("Brawler", 12, new ExpertStrategy(), new CarelessStrategy());
		
	}

	@Override
	public void findTreasure() {
		
		searchStrategy.findTreasure(this);
		
	}

	@Override
	public void fightCreature(Creature creature) {
		
		if (this.combatStrategy.fight(this, creature, 2 + this.processTreasure())) {

			Logger.log(this.getType() + " won the fight!");
			System.out.println(this.getType() + " won the fight against '"+creature.getClass().getSimpleName()+"'");
			
		} else {
			Logger.log(this.getType() + " lost the fight!");
			System.out.println(this.getType() + " lost the fight against '"+creature.getClass().getSimpleName()+"'");	
		}
		
	}

	@Override
	public String getType() {
		
		return "Brawler";
	
	}
	
	@Override
	public String toString() {
		
		return "B";
		
	}

}
