package aufgabe2;

import java.io.Console;

public class LinkedListFrequencyTable extends AbstractFrequencyTable {
    private Node begin;
    private Node end;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        begin = null;
        end = null;
        size = 0;
    }

    @Override
    public void add(String w, int f) {
        if (this.isEmpty()) {
            begin = new Node(new Word(w, f), null, null);
            end = begin;
            size++;
            return;
        }

        Node foundNode = getNode(w);

        //Node doesn't exist yet
        if (foundNode == null && f == 1) {
            addLast(new Word(w, f));
            return;
        }
        else if (foundNode == null) {
            Node next = nextSmaller(f);

            if (next == null) {
                addLast(new Word(w, f));
                return;
            }

            size++;

            if (next.getPrev() == null) {
                Node newNode = new Node(new Word(w, f), begin, null);
                begin.setPrev(newNode);
                begin = newNode;

                return;
            }

            Node newNode = new Node(new Word(w, f), next, next.getPrev());

            next.getPrev().setNext(newNode);
            next.setPrev(newNode);
            return;
        }

        foundNode.getWord().addFrequency(f);

        if (foundNode == begin)
            return;

        if (foundNode.getPrev() != null && foundNode.getNext() != null) {
            foundNode.getPrev().setNext(foundNode.getNext());
            foundNode.getNext().setPrev(foundNode.getPrev());
        }
        else if (foundNode.getPrev() != null) {
            foundNode.getPrev().setNext(null);
            end = foundNode.getPrev();
        }

        Node next = nextSmaller(foundNode.getWord().getFrequency());

        if (next == null) {
            return;
        }


        foundNode.setNew(null, null);

        if (next.getPrev() == null) {
            foundNode.setNew(begin, null);
            begin.setPrev(foundNode);
            begin = foundNode;
            return;
        }

        foundNode.setNew(next, next.getPrev());
        next.getPrev().setNext(foundNode);
        next.setPrev(foundNode);
    }

    @Override
    public Word get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node currentNode = begin;
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
        Node newNode = new Node(word, null, end);
        end.setNext(newNode);
        end = newNode;
        size++;
    }

    private Node getNode(String w) {
        Node currentNode = begin;

        for (int i = 0; i < size; i++) {
            if (w.equals(currentNode.getWord().getWord())) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
        }

        return null;
    }

    private Node nextSmaller(int f) {
        Node currentNode = begin;

        for (int i = 0; i < size; i++) {
            if (currentNode.getWord().getFrequency() < f) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
        }

        return null;
    }
}
