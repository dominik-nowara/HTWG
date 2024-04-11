import java.util.Iterator;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    private Entry<K, V>[] dic;
    private int size;
    private static final int DEF_CAPACITY = 32;

    public SortedArrayDictionary() {
        this.dic = new Entry[DEF_CAPACITY];
        this.size = 0;
    }

    private int searchKey(K key) {
        for (int i = 0; i < size; i++) {
            if (dic[i].getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size)
            return;
        Entry[] old = dic;
        dic = new Entry[newCapacity];
        System.arraycopy(old, 0, dic, 0, size);
    }

    @Override
    public V insert(K key, V value) {
        int i = searchKey(key);

        if (i >= 0) {
            V old = dic[i].getValue();
            dic[i].setValue(value);
            return old;
        }

        if (dic.length == size) {
            ensureCapacity(2 * size);
        }

        int j = size - 1;
        while (j >= 0 && key.compareTo(dic[j].getKey()) < 0) {
            dic[j + 1] = dic[j];
            j--;
        }

        dic[j + 1] = new Entry<K, V>(key, value);
        size++;
        return null;
    }

    @Override
    public V search(K key) {
        int i = searchKey(key);

        if (i >= 0)
            return dic[i].getValue();
        else
            return null;
    }

    @Override
    public V remove(K key) {
        int i = searchKey(key);
        if (i == -1)
            return null;

        V r = dic[i].getValue();
        for (int j = i; j < size-1; j++)
            dic[j] = dic[j + 1];
        dic[--size] = null;
        return r;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Entry next() {
                Entry<K, V> returnEntry = dic[index++];
                return returnEntry;
            }
        };
    }
}