import java.util.Collection;
import java.util.Iterator;

public class MyCollection<T> implements Iterable<T> {

    private T array[];
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

    public MyCollection() {
        array = (T[]) new Object[0];
    }

    public MyCollection(Collection<? extends T> collection) {
        array = (T[]) new Object[collection.size()];
        int i = 0;
        for (T t : collection) {
            array[i] = t;
            i++;
        }
    }

    public int size() {
        return array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }

    public boolean equals(MyCollection obj) {
        MyCollection mmc = obj;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < obj.array.length; j++) {
                if (!array[i].equals(obj.array[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
