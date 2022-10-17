package model.adventures;

import model.Logger;
import model.combat.UntrainedStrategy;
import model.creatures.Creature;
import model.search.QuickStrategy;

// Runner adventurer.
public class Runner extends Adventurer {

	// Attributes..
	public Runner() {
		
		super("Runner", 10, new UntrainedStrategy(), new QuickStrategy());
		
	}

	@Override
	public void findTreasure() {
		
		for (int i = 0; i < 2; i++) {
			searchStrategy.findTreasure(this);
		}
		
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
		
		return "Runner";
	
	}
	
	@Override
	public void move() {
		
		super.move();
		super.move();
		
	}
	
	@Override
	public String toString() {
		
		return "T";
		
	}

}
