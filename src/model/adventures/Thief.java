package model.adventures;

import model.Logger;
import model.combat.TrainedStrategy;
import model.creatures.Creature;
import model.search.CarefulStrategy;

// Thief adventurer.
public class Thief extends Adventurer {

	// Attributes..
	public Thief() {
		
		super("Thief", 10, new TrainedStrategy(), new CarefulStrategy());
		
	}

	@Override
	public void findTreasure() {
		
		searchStrategy.findTreasure(this);
		
	}

	@Override
	public void fightCreature(Creature creature) {
		
		if (this.combatStrategy.fight(this, creature, 1 + this.processTreasure())) {
			
			Logger.log(this.getType() + " won the fight!");
			System.out.println(this.getType() + " won the fight against '"+creature.getClass().getSimpleName()+"'");
			
		} else {
			Logger.log(this.getType() + " lost the fight!");
			System.out.println(this.getType() + " lost the fight against '"+creature.getClass().getSimpleName()+"'");	
		}
		
	}

	@Override
	public String getType() {
		
		return "Thief";
	
	}
	
	@Override
	public String toString() {
		
		return "T";
		
	}

}
