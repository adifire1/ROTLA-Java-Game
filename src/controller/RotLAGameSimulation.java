package controller;

import java.util.List;
import java.util.Scanner;

import controller.factory.AdventurerFactory;
import model.Direction;
import model.adventures.Adventurer;
import util.Utilities;

// Simulation game!
public class RotLAGameSimulation {
	
	// Main method to run the program.
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		GameEngine engine = new GameEngine();
		engine.init();

		System.out.print("Welcome to RotLA Game Simulation\n\n"
				+ "1: Brawler\n"
				+ "2: Runner\n"
				+ "3: Sneaker\n"
				+ "4: Thief\n"
				+ "Select option: ");
		int selection = Utilities.getNumber(scan, 1, 4);
		Adventurer adventurer = AdventurerFactory.createAdventurer(selection);
		engine.setAdventurer(adventurer);
		System.out.println("Adventurer selected");
		
		int command = 0;
		engine.printBoard();
		while (!engine.gameOver()) {
			
			System.out.print("1: Fight\n"
					+ "2: Search\n"
					+ "3: Celebrate\n"
					+ "4: Move\n"
					+ "Select command: ");
			command = Utilities.getNumber(scan, 1, 4);
			switch (command) {
			case 1:
				engine.fightCommand();
				System.out.println("FIGHT - COMMAND EXECUTED!");
				engine.printBoard();
				engine.printGameBoard();
				break;
			case 2:
				engine.searchCommand();
				System.out.println("SEARCH - COMMAND EXECUTED!");
				engine.printBoard();
				engine.printGameBoard();
				break;
			case 3:
				System.out.println("Celebrations: " + engine.celebrateCommand());
				System.out.println("CELEBRATION - COMMAND EXECUTED!");
				engine.printBoard();
				engine.printGameBoard();
				break;
			case 4:
				List<Direction> directions = engine.getNextMoveDirections();
				for (int i = 0; i < directions.size(); i++) {
					System.out.println((i + 1) + ": " + directions.get(i));
				}
				System.out.print("Select direction: ");
				int direction = Utilities.getNumber(scan, 1, directions.size());
				engine.moveCommand(directions.get(direction - 1));
				System.out.println("MOVE - COMMAND EXECUTED!");
				engine.printBoard();
				engine.printGameBoard();
				break;
			}
			System.out.println();
			
		}
		
		scan.close();
		System.out.println("Game end successfully!");
		
	}

}
