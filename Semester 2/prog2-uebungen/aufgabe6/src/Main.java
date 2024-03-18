public class Main {
    public static void main(String[] args) {
        Tätigkeit tk1 = new ParalleleTätigkeit();
        tk1.add(new ElementareTätigkeit("Linke Seitenwand montieren", 5.0));
        tk1.add(new ElementareTätigkeit("Rechte Seitenwand montieren", 5.0));

        Tätigkeit tk2 = new ParalleleTätigkeit();
        tk2.add(new ElementareTätigkeit("Linke Türe montieren", 7.0));
        tk2.add(new ElementareTätigkeit("Rechte Türe mit Griff montieren", 9.0));

        Tätigkeit schrankMontage = new SerielleTätigkeit();
        schrankMontage.add(new ElementareTätigkeit("Füße an Boden montieren", 6.0));
        schrankMontage.add(tk1);
        schrankMontage.add(new ElementareTätigkeit("Decke montieren", 8.0));
        schrankMontage.add(tk2);
        System.out.println(schrankMontage.getTime() + " min"); // 28.0 min
        System.out.println(schrankMontage.getAmount()); // 6
        System.out.println(schrankMontage); // Test von toString
    }
}