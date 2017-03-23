package com.valtech.training.monopoly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.valtech.training.monopoly.spaces.*;
import com.valtech.training.monpoly.exceptions.ImBrokeException;

public class Game {
	public List<Player> listOfPlayers;
	public List<Player> tempPlayerList;
	public Board b;
	public String[] arrayOfTokens;

	public int nrOfPlayers;
	public int nrOfTurns;
	private int playerNumber;

	private boolean stillPlaying;
	private boolean playerRemoved;

	public Game() {
		initialize();
		play();
	}

	public void initialize() {
		b = new Board();
		listOfPlayers = new ArrayList<Player>();
		arrayOfTokens = new String[] { "Horse", "Car", "Shoe", "Hat", "Ship", "Dog", "Thimble", "Iron", };
		stillPlaying = true;

		getInput("Players", 2);
		getInput("turns", 20);
		populatePlayerList();

	}

	private void getInput(String input, int limit) {

		Scanner reader = new Scanner(System.in);
		switch (input.toLowerCase()) {

		case "players":
			while (true) {
				// Reading from System.in

				System.out.println("Enter the number of players: ");
				nrOfPlayers = reader.nextInt();

				if (nrOfPlayers < 2) {
					System.out.println("Minimum number of players is " + limit + " ! ");
				} else if (nrOfPlayers > 8) {

					System.out.println("Maximum number of players is 8 ! ");
				} else if (nrOfPlayers >= 2) {
					break;
				}
			}
			break;

		case "turns":
			while (true) {
				System.out.println("Enter the number of Turns: ");
				nrOfTurns = reader.nextInt();

				if (nrOfTurns >= 20) {
					break;
				} else {
					System.out.println("Minimum number of turns is 20 ! ");
				}
			}
			break;
		}
		// reader.close();

	}

	public void play() {
		System.out.print("Start of the game of Monopoly !");
		System.out.println();

		Cup d1 = new Cup();

		for (int i = 1; i <= nrOfTurns; i++) {
			if (stillPlaying) {

//				for (Iterator<Player> iterator = listOfPlayers.iterator(); iterator.hasNext();) {
//					Player player = iterator.next();
//					player.takeTurn(d1);
//					validatePlayerAndSpace(player);
//					while (player.isHasAnotherTurn() &&stillPlaying) {
//						player.takeTurn(d1);
//						validatePlayerAndSpace(player);
//					}
//				}
				if(tempPlayerList!=null){
					listOfPlayers = new ArrayList<>(tempPlayerList);
				}
				for (int j = 0; j < listOfPlayers.size(); j++) {
					Player p = listOfPlayers.get(j);
					p.takeTurn(d1);
					validatePlayerAndSpace(p);
					while (p.isHasAnotherTurn() && stillPlaying) {
						p.takeTurn(d1);
						validatePlayerAndSpace(p);
					}
					// validatePlayerAndSpace(p);
				}

				System.out.println("======================================");
			} else if (!stillPlaying && tempPlayerList.size() == 1) {
				System.out.println("***WINNER*** " + tempPlayerList.get(0).getToken());
				break;
			}
		}
	}

	public void populatePlayerList() {

		for (int i = 1; i <= nrOfPlayers; i++) {
			listOfPlayers.add(new Player(arrayOfTokens[i - 1]));
		}
		playerNumber = listOfPlayers.size();
	}

	private void validatePlayerAndSpace(Player p) {
		validatePlayerAndSpaceSpecial(p);
		validatePlayerAndSpaceProperty(p);
		validatePlayerBalance(p);
		System.out.println();
	}

	private void validatePlayerAndSpaceSpecial(Player p) {

		int playerPosition = p.position;
		int playerBalance = p.getBalance();

		// SpaceGo logic
		if (playerPosition >= b.listOfSpaces.size()) {
			p.position = playerPosition % 40;
			if (p.position == b.getSpacePosition(0)) {
				p.setBalance(playerBalance + 400);
				System.out.println(p.getToken() + " lands on " + b.listOfSpaces.get(p.position).getName()
						+ " and receives 400");
			} else {
				p.setBalance(playerBalance + 200);
				System.out.println(p.getToken() + " passed SpaceGo and receives 200");
			}
		}

		// SpaceIncomeTax logic
		if (playerPosition == b.getSpacePosition(4)) {
			int tax = ((int) (playerBalance * 0.1) < 200) ? 200 : (int) (playerBalance * 0.1);

			p.setBalance(playerBalance - tax);
			System.out.println(p.getToken() + " lands on " + b.listOfSpaces.get(p.position).getName() + " and pays "
					+ tax);
		}

		// SpaceLuxuryTax logic
		else if (playerPosition == b.getSpacePosition(38)) {
			p.setBalance(playerBalance - 75);
			System.out.println(p.getToken() + " lands on " + b.listOfSpaces.get(p.position).getName() + " and pays 75");
		} else {
			System.out.println(p.getToken() + " lands on " + b.listOfSpaces.get(p.position).getName());
		}

	}

	private void validatePlayerAndSpaceProperty(Player player) {

		if (b.listOfSpaces.get(player.position) instanceof SpaceProperty) {
			SpaceProperty space = (SpaceProperty) b.listOfSpaces.get(player.position);

			if (space.getOwner() == null) {
				if (player.getBalance() >= space.getPrice()) {
					space.setOwner(player);
					player.setBalance(player.getBalance() - space.getPrice());

					if (space.getGroup() == 2) {
						player.ownedUtilities.add(space);
					} else if (space.getGroup() == 3) {
						player.ownedRailroads.add(space);
					}
					System.out.println(player.getToken() + " buys " + space.getName() + " for " + space.getPrice());
				} else {
					System.out.println(player.getToken() + " do not have enough balance to purchase!!!!");
				}
			} else if (space.getOwner() != null) {
				if (!space.getOwner().getToken().equals(player.getToken())) {
					switch (space.getGroup()) {

					case 1:
						player.setBalance(player.getBalance() - space.getRent());
						System.out.println(player.getToken() + " pays " + space.getOwner().getToken() + " a rent of "
								+ space.getRent());
						break;
					case 2:
					case 3:
						int rent = space.calculateRent(space.getOwner());
						player.setBalance(player.getBalance() - rent);
						System.out.println(player.getToken() + " pays " + space.getOwner().getToken() + " a rent of "
								+ rent + " for " + space.getName());
						break;
					}
				}
			}
			System.out.println(player.getToken() + " has a balance of " + player.getBalance());
		}
	}

	private void validatePlayerBalance(Player p) {

		try {
			if (p.getBalance() < 0) {
				System.out.println(p.getToken() + " is broke! ");
				p.setHasAnotherTurn(false);
				removePlayer(p);
				playerRemoved = true;
				throw new ImBrokeException();
			}

		} catch (ImBrokeException e) {
			e.printStackTrace(System.out);// using the same print out channel

			System.out.println(p.getToken() + " ----LOST----");
			if (tempPlayerList.size() == 1) {
				stillPlaying = false;
			}
		}
	}

	private void removePlayer(Player p) {
		for (Space sp : b.listOfSpaces) {
			if (sp instanceof SpaceProperty) {
				SpaceProperty temp = (SpaceProperty) sp;
				if (temp.getOwner() != null && temp.getOwner().equals(p)) {
					temp.setOwner(null);
				}
			}
		}
		tempPlayerList = new ArrayList<>(listOfPlayers);
		tempPlayerList.remove(p);

	}

	public static void main(String[] args) {
		Game g = new Game();
	}
}
