// O. Bittel
// 9.3.2018

package bibliothek;

public class Bibliothek {

	public static void main(String[] args) {
		Buch b1 = new Buch("Tod auf dem Nil");
		Buch b2 = new Buch("Alibi");
		Buch b3 = new Buch("Mord im Orientexpress");
		Person p1 = new Person("Peter");
		Person p2 = new Person("Maria");
		
		System.out.println(p1.leihtAus(b1));			// true
		System.out.println(b2.wirdAusgeliehen(p1));		// true
		
		p1.print();
		
		System.out.println(p2.leihtAus(b1));			// false
		System.out.println(p1.gibtZurueck(b1));			// true
		System.out.println(p2.leihtAus(b1));			// true
		System.out.println(b3.wirdAusgeliehen(p2));		// true
		
		p1.print();
		p2.print();
		b1.print();
		b2.print();
		b3.print();
		
		System.out.println(p1.gibtZurueck(b1));			// false
		System.out.println( b2.wirdZurueckGegeben());	// true
		System.out.println(p2.gibtZurueck(b1));			// true
		System.out.println(b3.wirdZurueckGegeben());	// true
		
		p1.print();
		p2.print();
		b1.print();
		b2.print();
		b3.print();
	}
	
}
