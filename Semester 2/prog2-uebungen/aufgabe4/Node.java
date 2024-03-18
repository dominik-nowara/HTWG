package aufgabe4;

public class Node<T> {
    private Node<T> next;
    private Node<T> prev; // previous
    private T element;
    public Node(T element, Node<T> next, Node<T> prev){
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    public T getElement() {
        return this.element;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public Node<T> getPrev() {
        return this.prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNew(Node<T> next, Node<T> prev) {
        this.next = next;
        this.prev = prev;
    }
}
