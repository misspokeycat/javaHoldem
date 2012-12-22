import java.util.Arrays;
import java.util.Scanner;

public class holdem {
	static int turnNumber = 0;
	static Deck Deck = new Deck();
	static Card[] table = new Card[5];
	static int lastBet = 0;
	static int pot = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many players?");
		int numberOfPlayers = scan.nextInt();
		Player[] players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.println("Enter player " + (i + 1) + "'s name.");
			players[i] = new Player(i, scan.next(), 10000);
		}
		scan.nextLine();
		boolean quit = false;
		while (!quit) {
			while (turnNumber < 5) {
				lastBet = 0;
				switch (turnNumber) {
				case 0:
					// Reset the deck, shuffle the deck, and deal 2 cards to
					// each player
					Deck.Reset();
					Deck.shuffle();
					for (Player player : players) {
						player.giveCards(Deck.deal(), Deck.deal());
					}
					break; // No cards are dealt on turn 1.
				case 1:
					table[0] = Deck.deal();
					table[1] = Deck.deal();
					table[2] = Deck.deal();
					break; // Flop - 3 cards dealt
				case 2:
					table[3] = Deck.deal();
					break;
				case 3:
					table[4] = Deck.deal();
					break;
				case 4:
					// Win checks
					int[] handValues = new int[numberOfPlayers];
					int handToBeat;
					for (Player player : players) {
						int highestCardInHand;
						Boolean pair = false, twoPair = false, threeOfAKind = false, straight= false, flush = false, straightFlush = false, fourOfAKind= false, royal = false;
						Card[] sortedTable = new Card[7];
						// Concatenates the player's hand and the table.
						System.arraycopy(player.getHand(), 0, sortedTable, 0, 2);
						System.arraycopy(table, 0, sortedTable, 2, 5);
						Arrays.sort(sortedTable);
						//TODO: Abstraction into CalulateValue
						// Checks for a straight
						for (int x = 0; x < 7; x++) {
							int straightCounter = 0;
							while (sortedTable[x].getValueInt() + 1 == sortedTable[x + 1]
									.getValueInt()) {
								straightCounter++;
							}
							if (straightCounter >= 5) {
								straight = true;
								highestCardInHand = sortedTable[x + 4]
										.getValueInt();
							}
						}
						// TODO: sorting by suit
						for (int x = 0; x < 7; x++) {
							int flushCounter = 0;
							while (sortedTable[x].getSuitID() == sortedTable[x + 1]
									.getSuitID()) {
								flushCounter++;
							}
							if (flushCounter >= 5) {
								flush = true;
								highestCardInHand = sortedTable[x + 4]
										.getValueInt();
							}
							//Checks for pairs
							//TODO: Find some way to check for a full house
							int PairID;
							for (int x1 = 0; x1 < 7; x1++) {
								int sameCards = 1;
								while (sortedTable[x1].getValueInt() == sortedTable[x1 + 1]
										.getValueInt()) {
									sameCards++;
								}
								switch (sameCards) {
								case 1:
									if (!pair||!threeOfAKind||!fourOfAKind){
										PairID = x1;
									}
									break;
								case 2:
									if (!threeOfAKind||!fourOfAKind){
										PairID = x1;
									}
									pair = true;
									break;
								case 3:
										if (!fourOfAKind){
											PairID = x1;
										}
									threeOfAKind = true;
									break;
								case 4:
									PairID = x1;
									fourOfAKind = true;
									break;
								}
							}
							//Checks for 
						}
						break;
					}
				}
					String callOrCheck;

					for (Player player : players) {
						if (!player.hasFolded()) {
							System.out.println("It's " + player.getPlayerName()
									+ "'s turn.");
							System.out.println("Table: " + table[0] + " "
									+ table[1] + " " + table[2] + " "
									+ table[3] + " " + table[4]
									+ "\tCurrent pot: " + pot);
							System.out.println("Your hand: "
									+ player.getHand()[0] + " "
									+ player.getHand()[1] + "\tYour cash: "
									+ player.getCurrentCash());
							Boolean isCheckAvailable = false; // A player can
																// only
																// check
																// when no
																// bets have
																// been
																// made.
							if (lastBet == 0) {
								isCheckAvailable = true;
							}
							if (isCheckAvailable) {
								callOrCheck = "Check";
							} else {
								callOrCheck = "Call";
							}
							if (lastBet > player.getCurrentCash()) {
								System.out.println("All in or fold?");
								String userMove = scan.nextLine().toLowerCase();
								if (userMove == "all in") {
									pot += player.getCurrentCash();
									player.Bet(player.getCurrentCash());
								} else if (userMove == "fold") {
									player.Fold();
								} else {
									System.out.println("Invalid entry.");
								}
							}
							System.out.println("Raise, " + callOrCheck
									+ ", All In, or Fold?");
							String userMove = scan.nextLine().toLowerCase();
							if (userMove.equals("call")) {
								pot += lastBet;
								player.Bet(lastBet);
							} else if (userMove.equals("check")) {

							} else if (userMove.equals("raise")) {
								System.out
										.println("Enter the amount that you want to raise.");
								String bet = scan.next();

								int betAmount = Integer.parseInt(bet);
								if (player.Bet(betAmount)) {
									pot += betAmount;
									lastBet = betAmount;
								} else {
									System.out.println(betAmount);
									System.out.println("Invalid bet!");
								}

							} else if (userMove.equals("fold")) {
								player.Fold();
							} else if (userMove.equals("all in")) {
								pot += player.getCurrentCash();
								player.Bet(player.getCurrentCash());
							} else {
								System.out.println("Invalid entry.");
							}
						}
					}
					turnNumber++;
				
			}
		}
	}
}
/*
 * This code is obsolete.
 */
// public static String[][] deal(int numberOfCards) {
//
// String[][] hand = new String[numberOfCards][2];
// final String[] CARDS = { "2", "3", "4", "5", "6", "7", "8", "9", "10",
// "J", "Q", "K", "A" };
// final String[] SUITS = { "♠", "♣", "♥", "♦" };
// for (int i = 0; i < numberOfCards; i++) {
// hand[i][0] = CARDS[(int) (Math.random() * 13)];
// hand[i][1] = SUITS[(int) (Math.random() * 4)];
// }
// return hand;
// }

// public static void bet(int betAmount)
// {
// pot = pot + betAmount;
// playerCash = playerCash - betAmount;
// }
// }
