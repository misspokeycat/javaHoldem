public class Card implements Comparable {
	private String value;
	private String suit;
	private static boolean compareBySuit = false;

	public Card(String Value, String Suit) {
		value = Value;
		suit = Suit;
	}

	// If true, compareTo will sort by suit.
	public static void compareBySuit(boolean x) {
		if (x) {
			compareBySuit = true;
		}
	}

	public int compareTo(Object obj) {
		int ret = 0;
		Card tmp = (Card) obj;
		if (compareBySuit == false) {
			if (this.getValueInt() < tmp.getValueInt()) {
				ret = -1;
			} else if (this.getValueInt() > tmp.getValueInt()) {
				ret = 1;
			} else if (this.getValueInt() == tmp.getValueInt()) {
				if (this.getValueInt() < tmp.getValueInt()) {
					ret = -1;
				} else if (this.getValueInt() > tmp.getValueInt()) {
					ret = 1;
				}
			}
		} 
		else {
			if (this.getSuitID() < tmp.getSuitID()) {
				ret = -1;
			} else if (this.getSuitID() > tmp.getSuitID()) {
				ret = 1;
			} else if (this.getSuitID() == tmp.getSuitID()) {
				if (this.getSuitID() < tmp.getSuitID()) {
					ret = -1;
				} else if (this.getSuitID() > tmp.getSuitID()) {
					ret = 1;
				}
			}
		}
		return ret;
	}

	public int getValueInt() {
		int worth = 0;
		if (value.equals("2") || value.equals("3") || value.equals("4")
				|| value.equals("5") || value.equals("6") || value.equals("7")
				|| value.equals("8") || value.equals("9") || value.equals("10")) {
			worth = Integer.parseInt(value);
		} else if (value.equals("J")) {
			worth = 11;
		} else if (value.equals("Q")) {
			worth = 12;
		} else if (value.equals("K")) {
			worth = 13;
		} else if (value.equals("A")) {
			worth = 14;
		}
		return worth;
	}

	public String getSuit() {
		return suit;
	}

	public String getValue() {
		return value;
	}

	public int getSuitID() {
		int ret = 0;
		if (this.suit == "♠")
			ret = 1;
		else if (this.suit == "♣")
			ret = 2;
		else if (this.suit == "♥")
			ret = 3;
		else if (this.suit == "♦")
			ret = 4;
		return ret;
	}

	public String toString() {
		return (value + suit);
	}
}
