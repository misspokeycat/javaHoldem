//Sample class used for testing CalculateValue
import java.util.Arrays;

public class calcTest {
public static void main (String[] args){
	Card[] sampleTable = new Card[7];
	sampleTable[0] = new Card("10", "♦");
	sampleTable[1] = new Card("J", "♦");
	sampleTable[2] = new Card("Q", "♦");
	sampleTable[3] = new Card("3", "♣");
	sampleTable[4] = new Card("K", "♦");
	sampleTable[5] = new Card("A", "♦");
	sampleTable[6] = new Card("9", "♦");
	Arrays.sort(sampleTable);
	CalculateValue.Calculate(sampleTable);
}
}
