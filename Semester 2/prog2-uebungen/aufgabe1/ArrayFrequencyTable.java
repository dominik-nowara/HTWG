package aufgabe1;

import aufgabe1.AbstractFrequencyTable;
import aufgabe1.Word;

import java.util.Arrays;

/**
 *
 * @author oliverbittel
 */
public class ArrayFrequencyTable extends AbstractFrequencyTable {

    private int size = 0;
    private Word fqTable[];
    private final int DEFAULT_SIZE = 100;

    public ArrayFrequencyTable() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public final void clear() {
        fqTable = new Word[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(String w, int f) {
        if (fqTable.length == size)
            fqTable = Arrays.copyOf(fqTable, 2 * size);

        int wordPosition = getPosition(w);

        if (f == 1 && wordPosition == -1) {
            fqTable[size] = new Word(w, f);
            size++;
            return;
        }
        else if (wordPosition == -1) {
            int position = getNewPosition(f);

            if (fqTable.length + 1 == size)
                fqTable = Arrays.copyOf(fqTable, 2 * size);

            for (int i = size; i > position; i--) {
                fqTable[i] = fqTable[i-1];
            }

            fqTable[position] = new Word(w, f);
            size++;
            return;
        }

        Word tempWord = fqTable[wordPosition];
        tempWord.addFrequency(f);
        int position = getNewPosition(f);

        for (int i = wordPosition; i > position; i--) {
            fqTable[i] = fqTable[i - 1];
        }

        fqTable[position] = tempWord;
    }

    @Override
    public Word get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        return fqTable[pos];
    }

    @Override
    public int get(String w) {
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getWord().equals(w))
                return fqTable[i].getFrequency();
        }

        return 0;
    }

    public int getPosition(String w) {
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getWord().equals(w))
                return i;
        }

        return -1;
    }

    private int getNewPosition(int f) {
        int position = size;

        for (int i = 0; i < size; i++) {
            if (fqTable[i].getFrequency() < f) {
                position = i;
                break;
            }
        }

        return position;
    }
}
