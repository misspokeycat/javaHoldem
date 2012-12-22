
public class Player {
	private int playerID;
	private String playerName;
	private int currentCash;
	private Boolean hasPlayerFolded = false;
	private Card[] hand = new Card[2];
	public Player ( int ID, String name, int startingCash){
		playerID = ID;
		playerName = name;
		currentCash = startingCash;
	}
	public Card[] getHand() {
		return hand;
	}
	public void setHand(Card[] hand) {
		this.hand = hand;
	}
	public int getPlayerID() {
		return playerID;
	}
	public String getPlayerName() {
		return playerName;
	}
	public int getCurrentCash() {
		return currentCash;
	}
	public void Fold()
	{
		hasPlayerFolded = true;
	}
	public Boolean hasFolded(){
		return hasPlayerFolded;
	}
	//Bet returns true if the bet succeded, or false if the player does not have enough
	//money to place their bet.
	public Boolean Bet(int amountToBet){
		Boolean a = true;
		if (amountToBet>currentCash){
			a = false;
		}
		else
		{
			currentCash -= amountToBet;
		}
		return a;
	}
	public void giveCards(Card card1, Card card2){
		hand[0] = card1;
		hand[1] = card2;
	}	
}
