package aufgabe4;

public class RedCard extends Card {
    public RedCard() {
        super((random.nextInt(2) == 0) ? Suit.KARO : Suit.HERZ,
                Rank.values()[random.nextInt(8)]);
    }

    public RedCard(Suit suit, Rank rank) {
        super(suit, rank);
    }
}
