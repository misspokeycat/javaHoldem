public class Deck {
	private Card[] cardsAvail = new Card[52];
	private final String[] CARDS = new String[] { "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "J", "Q", "K", "A" };
	private final String[] SUITS = new String[] { "♠", "♣", "♥", "♦" };
	private int deckPos = 0;

	public Deck() {
		int i = 0;
		for (String card : CARDS) {
			for (String suit : SUITS) {
				cardsAvail[i] = new Card(card, suit);
				i++;
			}
		}
	}
	public void Reset(){
		int i = 0;
		for (String card : CARDS) {
			for (String suit : SUITS) {
				cardsAvail[i] = new Card(card, suit);
				i++;
			}
		}
		deckPos = 0;
	}
	
	public void shuffle() {
		Card[] shuffled = new Card[52];
			shuffled[0] = cardsAvail[0];
			for (int i = 1; i<52; i++){
				int z = (int)(Math.random()*i);
				shuffled[i] = shuffled[z];
				shuffled[z] = cardsAvail[i];
			}
		cardsAvail = shuffled;
	}

	public Card deal() {
		Card toDeal = cardsAvail[deckPos];
		deckPos++;
		return toDeal;
	}
}
