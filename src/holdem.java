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
					int handToBeat = 0;
					int f = 0;
					for (Player player : players) {
						if (!player.hasFolded()) {
							Card[] sortedTable = new Card[7];
							// Concatenates the player's hand and the table.
							// TODO: Saving everybody's hand value for the
							// reveal, not calculating for folded hands
							System.arraycopy(player.getHand(), 0, sortedTable,
									0, 2);
							System.arraycopy(table, 0, sortedTable, 2, 5);
							Arrays.sort(sortedTable);
							handValues[f] = CalculateValue
									.Calculate(sortedTable);
							//TODO: Ties
							if (!(f == 0)
									&& (handValues[f] > handValues[handToBeat])) {
								handToBeat = f;
							}
						}
						f++;
					}
					// TODO:Some way of keeping track of who has the highest
					// score, winning rewards etc.
					//TODO: Some form of leaderboard -something like
					//1. Luke 2C 3C 4C 5C 6C 8D 5D
					//2. Bob 2C 2S 4C 4S etc.
					System.out.println(players[handToBeat].getPlayerName()+ "wins the hand!");
					players[handToBeat].giveMoney(pot);
					break;

				}
				String callOrCheck;

				for (Player player : players) {
					if (!player.hasFolded()) {
						System.out.println("It's " + player.getPlayerName()
								+ "'s turn.");
						System.out.println("Table: " + table[0] + " "
								+ table[1] + " " + table[2] + " " + table[3]
								+ " " + table[4] + "\tCurrent pot: " + pot);
						System.out.println("Your hand: " + player.getHand()[0]
								+ " " + player.getHand()[1] + "\tYour cash: "
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
