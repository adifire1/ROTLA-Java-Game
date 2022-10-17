package util;

import java.util.Random;
import java.util.Scanner;

import model.adventures.Adventurer;
import model.creatures.Creature;

// Utilities..
public class Utilities {

	// Random..
	private static Random random = new Random();
	
	// get random to size.
	public static int getRandom(int size) {
		
		return random.nextInt(size);
		
	}
	
	public static int diceRolls() {
		
		return (1 + random.nextInt(6)) + 
				(1 + random.nextInt(6));
		
	}
	
	public static boolean tossCoin() {
		
		return random.nextBoolean();
		
	}
	
	public static boolean fight(Adventurer ad, Creature c, int extraAd, int extraC) {
		
		int currentRolls = Utilities.diceRolls() + extraAd;
		int creatureRolls = Utilities.diceRolls() + extraC;
		
		if (currentRolls > creatureRolls) {
			c.doDamage();
			return true;
		}
		ad.doDamage();
		ad.damage++;
		return false;
		
	}
	

	// take number..
	public static int getNumber(Scanner scan, int start, int end) {
		
		while (true) {
			try {
				int number = Integer.parseInt(scan.nextLine());
				if (number >= start && number <= end) {
					return number;
				} else {
					System.out.print("Invalid number, try again: ");
				}
			} catch (Exception e) {
				System.out.print("Invalid number, try again: ");
			}
		}
		
	}
	
}
