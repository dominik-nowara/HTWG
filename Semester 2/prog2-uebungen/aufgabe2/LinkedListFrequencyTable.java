package aufgabe2;

import java.io.Console;

public class LinkedListFrequencyTable extends AbstractFrequencyTable {
    private Node begin;
    private Node end;
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
        begin = new Node(new Word("|", Integer.MAX_VALUE), null, null);
        end = new Node(new Word("|", -1), null, begin);
        begin.setNext(end);
        size = 0;
    }

    @Override
    public void add(String w, int f) {
        Node foundNode = getNode(w);

        //Node doesn't exist yet
        if (foundNode == null) {
            size++;

            if (f == 1) {
                addLast(new Word(w, f));
                return;
            }

            Node next = nextSmaller(f);

            if (next == end) {
                addLast(new Word(w, f));
                return;
            }

            Node newNode = new Node(new Word(w, f), next, next.getPrev());
            next.getPrev().setNext(newNode);
            next.setPrev(newNode);
            return;
        }

        foundNode.getWord().addFrequency(f);

        if (foundNode == begin.getNext())
            return;

        Node next = nextSmaller(foundNode.getWord().getFrequency());

        foundNode.getNext().setPrev(foundNode.getPrev());
        foundNode.getPrev().setNext(foundNode.getNext());

        foundNode.setNew(next, next.getPrev());
        next.getPrev().setNext(foundNode);
        next.setPrev(foundNode);
    }

    @Override
    public Word get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node currentNode = begin.getNext();
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getWord();
    }

    @Override
    public int get(String w) {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        Node getNode = getNode(w);
        if (getNode != null)
            return getNode.getWord().getFrequency();

        return 0;
    }

    private void addLast(Word word) {
        Node newNode = new Node(word, end, end.getPrev());
        end.getPrev().setNext(newNode);
        end.setPrev(newNode);
    }

    private Node getNode(String w) {
        Node currentNode = begin.getNext();

        for (int i = 0; i < size; i++) {
            if (w.equals(currentNode.getWord().getWord())) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
        }

        return null;
    }

    private Node nextSmaller(int f) {
        Node currentNode = begin.getNext();

        for (int i = 0; i < size; i++) {
            if (currentNode.getWord().getFrequency() < f) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
        }

        return null;
    }
}
