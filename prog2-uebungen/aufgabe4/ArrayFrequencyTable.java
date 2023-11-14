package aufgabe4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 *
 * @author oliverbittel
 */
public class ArrayFrequencyTable<T> extends AbstractFrequencyTable<T> {

    private int size = 0;
    private Element<T>[] fqTable;
    private final int DEFAULT_SIZE = 100;

    public ArrayFrequencyTable() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void clear() {
        fqTable = (Element<T>[]) new Element[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(T w, int f) {
        if (fqTable.length == size)
            fqTable = Arrays.copyOf(fqTable, 2 * size);

        int wordPosition = getPosition(w);

        if (f == 1 && wordPosition == -1) {
            fqTable[size] = new Element<T>(w, f);
            size++;
            return;
        }
        else if (wordPosition == -1) {
            int position = getNewPosition(f, Integer.MAX_VALUE);

            if (fqTable.length + 1 == size)
                fqTable = Arrays.copyOf(fqTable, 2 * size);

            for (int i = size; i > position; i--) {
                fqTable[i] = fqTable[i-1];
            }

            fqTable[position] = new Element<T>(w, f);
            size++;
            return;
        }

        Element<T> tempElement = fqTable[wordPosition];
        tempElement.addFrequency(f);
        int position = getNewPosition(tempElement.getFrequency(), wordPosition);

        for (int i = wordPosition; i > position; i--) {
            fqTable[i] = fqTable[i - 1];
        }

        fqTable[position] = tempElement;
    }

    @Override
    public Element<T> get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        return fqTable[pos];
    }

    @Override
    public int get(T w) {
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getData().equals(w))
                return fqTable[i].getFrequency();
        }

        return 0;
    }

    public int getPosition(T w) {
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getData().equals(w))
                return i;
        }

        return -1;
    }

    private int getNewPosition(int f, int currentPosition) {
        int position = size;

        for (int i = 0; i < size; i++) {
            if (fqTable[i].getFrequency() < f) {
                position = i;
                break;
            }
        }

        if (currentPosition <= position)
            return currentPosition;

        return position;
    }

    @Override
    public Iterator<Element<T>> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<Element<T>> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 <= size;
        }

        @Override
        public Element<T> next() {
            Element<T> nextElement = fqTable[currentIndex];
            currentIndex++;
            return nextElement;
        }
    }
}
