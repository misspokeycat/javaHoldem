import java.util.Arrays;

public class CalculateValue {
	public static int Calculate(Card[] sortedTable) {
		int highestCardInHand;
		Boolean pair = false, twoPair = false, threeOfAKind = false, straight = false, flush = false, fullHouse = false, straightFlush = false, fourOfAKind = false, royal = false;

		// Checks for pairs
		// PairID is used to check for duplicates.
		int PairID = 0;
		// PairCount used to check for 2 pair.
		int pairCount = 0;
		
		for (int x = 0; x < 7; x++) {
			int sameCards = 1;
			int y=1;
			while (!(x+y==7) && sortedTable[x].getValueInt() == sortedTable[x + y]
					.getValueInt()) {
				sameCards++;
				y++;
			}
		
			switch (sameCards) {
			// High card
			case 1:
				if (!(pair || threeOfAKind || fourOfAKind)
						&& !(PairID == sortedTable[x].getValueInt())) {
					PairID = sortedTable[x].getValueInt();					
				}
				break;
			// Pair
			case 2:
				if (!(threeOfAKind || fourOfAKind)
						&& !(PairID == sortedTable[x].getValueInt())) {
					PairID = sortedTable[x].getValueInt();
				pairCount++;
				pair = true;
				}
				break;
			// 3 of a kind
			case 3:
				if (!fourOfAKind && !(PairID == sortedTable[x].getValueInt())) {
					PairID = sortedTable[x].getValueInt();
				}
				threeOfAKind = true;
				break;
			case 4:
				// 4 of a kind
				PairID = sortedTable[x].getValueInt();
				fourOfAKind = true;
				break;
			}
		}
		//NOTE: The value of twoPair will be false if there is a full house, because of the logic used for checking for multiple pairs.
		//However, for this it is fine, because if a player has a full house, it is greater then a two pair, so the value is not used, anyway.
		//Two pair
		if (pairCount>1){
			twoPair = true;
			highestCardInHand = PairID;
		}
		//Full House
		if (threeOfAKind && pair) {
			fullHouse = true;
			
		}
		// Checks for straights
		for (int x = 0; x < 7; x++) {
			int straightCounter = 1;
			int y=1;
			while (!(x+y==7) && sortedTable[x].getValueInt() + y == sortedTable[x + y]
					.getValueInt()) {
				straightCounter++;
				y++;
			}
			if (straightCounter == 5) {
				straight = true;
				highestCardInHand = sortedTable[x + 4].getValueInt();
				//TODO: Straight Flushes & Royal Flushes
			}
		}

		// Sorts hand by suit
		Card.compareBySuit(true);
		Arrays.sort(sortedTable);
		Card.compareBySuit(false);
		//Checks for flushes
		for (int x = 0; x < 7; x++) {
			int flushCounter = 1;
			int y = 1;
			while (!(x+y==7) && sortedTable[x].getSuitID() == sortedTable[x + y].getSuitID()) {
				flushCounter++;
				y++;
			}
			if (flushCounter >= 5) {
				flush = true;
				highestCardInHand = sortedTable[x + 4].getValueInt();
			}
		}
		//TODO:Return a meaningful number
		return 1;
	}
}