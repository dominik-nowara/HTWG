package aufgabe2;

public class Node {
    private Node next;
    private Node prev; // previous
    private Word word;
    public Node(Word word, Node next, Node prev){
        this.word = word;
        this.next = next;
        this.prev = prev;
    }

    public Word getWord() {
        return this.word;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNew(Node next, Node prev) {
        this.next = next;
        this.prev = prev;
    }
}
