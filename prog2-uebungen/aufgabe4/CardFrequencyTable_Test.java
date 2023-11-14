package aufgabe4;

import java.util.Random;

/**
 *
 * @author oliverbittel
 * @since 31.07.2023
 */
public class CardFrequencyTable_Test {

	public static void main(String[] args) {

		FrequencyTable<Card> cardTab1 = new ArrayFrequencyTable<>();
		FrequencyTable<Card> cardTab2 = new ArrayFrequencyTable<>();
		FrequencyTable<RedCard> redCardTab = new ArrayFrequencyTable<>();
		FrequencyTable<BlackCard> blackCardTab = new ArrayFrequencyTable<>();

		// Beachte:
		// Evtl. wird bei Ihnen die rote Karte "sieben Herz" anders definiert.
		redCardTab.add(new RedCard(Card.Suit.HERZ, Card.Rank.SIEBEN));
		System.out.println(redCardTab.get(new RedCard(Card.Suit.HERZ, Card.Rank.SIEBEN))); // sollte die Häufigkeit 1 ergeben
		
		Random rand = new Random();
		for (int i = 0; i < 400; i++) {
			if (rand.nextInt() % 2 == 0) {
				RedCard c = new RedCard();
				cardTab1.add(c);
				redCardTab.add(c);
			} else {
				BlackCard c = new BlackCard();
				cardTab1.add(c);
				blackCardTab.add(c);
			}
		}


		System.out.println(cardTab1);
		System.out.println(blackCardTab);
		System.out.println(redCardTab);
		System.out.println();
		
		//redCardTab.addAll(cardTab1); // nicht OK
		cardTab1.addAll(redCardTab);
		
		redCardTab.addAll(redCardTab); // Aufruf funktioniert nur, wenn add strukturerhaltend implemntiert ist. Daher problematisch.
		System.out.println(redCardTab);
		
		cardTab1.collectNMostFrequent(5,cardTab2);
		System.out.println(cardTab2);
		//cardTab1.collectNMostFrequent(10, redCardTab); // nicht OK
		redCardTab.collectNMostFrequent(5, cardTab2);
		System.out.println(cardTab2);
	}
}
