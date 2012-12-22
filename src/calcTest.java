import java.util.Arrays;

public class calcTest {
public static void main (String[] args){
	Card[] sampleTable = new Card[7];
	sampleTable[0] = new Card("2", "♦");
	sampleTable[1] = new Card("2", "♥");
	sampleTable[2] = new Card("K", "♦");
	sampleTable[3] = new Card("3", "♦");
	sampleTable[4] = new Card("4", "♠");
	sampleTable[5] = new Card("3", "♦");
	sampleTable[6] = new Card("6", "♦");
	Arrays.sort(sampleTable);
	CalculateValue.Calculate(sampleTable);
}
}
