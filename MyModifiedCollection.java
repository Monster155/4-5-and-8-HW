import java.util.Collection;
import java.util.Iterator;

public class MyModifiedCollection<T> implements Iterable<T> {

    private T array[];
    private int size;
    private Iterator iterator = new Iterator<T>() {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            if (cursor >= size())
                return false;
            return true;
        }

        @Override
        public T next() {
            cursor++;
            return array[cursor - 1];
        }
    };

    public MyModifiedCollection() {
        array = (T[]) new Object[0];
        size = 0;
    }

    public MyModifiedCollection(Collection<? extends T> collection) {
        array = (T[]) new Object[collection.size()];
        int i = 0;
        for (T t : collection) {
            array[i] = t;
            i++;
        }
        size = collection.size();
    }

    public void add(T value) {
        if (size >= array.length) {
            T[] arr = (T[]) new Object[(int) (size * 1.5)];
            for (int i = 0; i < size; i++) {
                arr[i] = array[i];
            }
            array = arr;
        }
        array[size] = value;
        size++;
    }

    public void remove(T value) {
        boolean check = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                check = true;
            }
            if (check) {
                array[i - 1] = array[i];
            }
        }
        size--;
    }

    public int size() {
        return array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }

    public boolean equals(MyModifiedCollection obj) {
        MyModifiedCollection mmc = obj;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < obj.size; j++) {
                if (!array[i].equals(obj.array[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
