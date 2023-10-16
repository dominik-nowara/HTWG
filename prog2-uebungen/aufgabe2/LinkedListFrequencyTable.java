package aufgabe2;

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

        if (foundNode == null && f == 1) {
            Node newNode = new Node(new Word(w, f), null, end);
            end.setNext(newNode);
            end = newNode;
            size++;
            return;
        }
        else if (foundNode == null) {
            size++;
            foundNode = new Node(new Word(w, f), null, null);
        }
        else {
            foundNode.getWord().addFrequency(f);

            Node beforeFound = foundNode.getPrev();
            Node afterFround = foundNode.getNext();

            if (beforeFound != null && afterFround != null) {
                beforeFound.setNext(afterFround);
                afterFround.setPrev(beforeFound);
            }
            else if (beforeFound != null) {
                beforeFound.setNext(null);
                end = beforeFound;
            }
            else if (afterFround != null) {
                afterFround.setPrev(null);
                begin = afterFround;
            }

            foundNode.setNew(null, null);
        }

        Node next = nextSmaller(foundNode.getWord().getFrequency());

        if (next == null) {
            foundNode.setPrev(end);
            end.setNext(foundNode);
            end = foundNode;
            return;
        }

        if (next == begin)
            begin = foundNode;

        Node prev = next.getPrev();

        next.setPrev(foundNode);
        foundNode.setNext(next);

        if (prev != null) {
            foundNode.setPrev(prev);
            prev.setNext(foundNode);
        }
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

    private Node getNode(String w) {
        Node currentNode = begin;

        for (int i = 0; i < size; i++) {
            if (currentNode == null) return null;

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
            if (currentNode == null) return null;

            if (currentNode.getWord().getFrequency() < f) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
        }

        return null;
    }
}
