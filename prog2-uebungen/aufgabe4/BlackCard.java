package aufgabe4;

public class BlackCard extends Card {
    public BlackCard() {
        super((random.nextInt(2) == 0) ? Suit.PIQUE : Suit.KREUZ,
                Rank.values()[random.nextInt(8)]);
    }
}
