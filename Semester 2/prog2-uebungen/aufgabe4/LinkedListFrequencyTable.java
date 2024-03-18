package aufgabe4;

import java.util.Iterator;

public class LinkedListFrequencyTable<T> extends AbstractFrequencyTable<T> {
    private Node<Element<T>> begin;
    private Node<Element<T>> end;
    private int size = 0;

    public LinkedListFrequencyTable() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        begin = new Node<Element<T>>(new Element<T>(null, Integer.MAX_VALUE), null, null);
        end = new Node<Element<T>>(new Element<T>(null, -1), null, begin);
        begin.setNext(end);
        size = 0;
    }

    @Override
    public void add(T w, int f) {
        Node<Element<T>> foundNode = getNode(w);

        //Node doesn't exist yet
        if (foundNode == null) {
            size++;

            if (f == 1) {
                addLast(new Element<T>(w, f));
                return;
            }

            Node<Element<T>> next = nextSmaller(f);

            if (next == end) {
                addLast(new Element<T>(w, f));
                return;
            }

            Node<Element<T>> newNode = new Node<Element<T>>(new Element<T>(w, f), next, next.getPrev());
            next.getPrev().setNext(newNode);
            next.setPrev(newNode);
            return;
        }

        foundNode.getElement().addFrequency(f);

        if (foundNode == begin.getNext())
            return;

        Node<Element<T>> next = nextSmaller(foundNode.getElement().getFrequency());

        foundNode.getNext().setPrev(foundNode.getPrev());
        foundNode.getPrev().setNext(foundNode.getNext());

        foundNode.setNew(next, next.getPrev());
        next.getPrev().setNext(foundNode);
        next.setPrev(foundNode);
    }

    @Override
    public Element<T> get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<Element<T>> currentNode = begin.getNext();
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getElement();
    }

    @Override
    public int get(T w) {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        Node<Element<T>> getNode = getNode(w);
        if (getNode != null)
            return getNode.getElement().getFrequency();

        return 0;
    }

    private void addLast(Element element) {
        Node<Element<T>> newNode = new Node(element, end, end.getPrev());
        end.getPrev().setNext(newNode);
        end.setPrev(newNode);
    }

    private Node<Element<T>> getNode(T w) {
        Node<Element<T>> currentNode = begin.getNext();

        for (int i = 0; i < size; i++) {
            if (w.equals(currentNode.getElement().getData())) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
        }

        return null;
    }

    private Node<Element<T>> nextSmaller(int f) {
        Node<Element<T>> currentNode = begin.getNext();

        for (int i = 0; i < size; i++) {
            if (currentNode.getElement().getFrequency() < f) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
        }

        return null;
    }

    @Override
    public Iterator<Element<T>> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Element<T>> {
        Node<Element<T>> currentElement  = begin.getNext();

        @Override
        public boolean hasNext() {
            return currentElement.getNext() != null;
        }

        @Override
        public Element<T> next() {
            Element<T> nextElement = currentElement.getElement();
            currentElement = currentElement.getNext();
            return nextElement;
        }
    }
}
