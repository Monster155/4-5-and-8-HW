import java.util.*;

/**
 * Set with navigation forward and back
 *
 * @author Damir Davletshin
 * @version 1.3
 */

public class MyNavigableSet<T extends Comparable> extends AbstractSet<T> implements NavigableSet<T> {

    /**
     * head - first element, back - last element
     */
    private Node head, back;

    /**
     * Iterator over the elements in this set, in ascending order
     */
    private Iterator<T> iterator = new Iterator<T>() {
        private Node likeCursor = head;

        @Override
        public boolean hasNext() {
            if (likeCursor.next.value.equals(null))
                return false;
            return true;
        }

        @Override
        public T next() {
            likeCursor = likeCursor.next;
            return likeCursor.value;
        }
    };

    /**
     * Descending Iterator over the elements in this set, in descending order
     */
    private Iterator<T> descendingIterator = new Iterator<T>() {
        private Node likeBackCursor = back;

        @Override
        public boolean hasNext() {
            if (likeBackCursor.previous.value.equals(null))
                return false;
            return true;
        }

        @Override
        public T next() {
            likeBackCursor = likeBackCursor.previous;
            return likeBackCursor.value;
        }
    };


    /**
     * Construct empty Navigable Set
     */
    public MyNavigableSet() {
        super();
        head = new Node(null, null, 0);
        back = head;
    }

    /**
     * Construct Navigable Set by array
     */
    public MyNavigableSet(T[] array) {
        super();
        Arrays.sort(array);
        head = new Node(array[0], null, 1);
        Node node = head;
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i - 1]) == 0)
                continue;
            node.next = new Node(array[i], node, i + 1);
            node = node.next;
        }
        back = node;
    }


    /**
     * Add new value to Navigable Set
     *
     * @param t the value to match
     * @return true - value added, false - adding failed
     */
    @Override
    public boolean add(T t) {
        Node node = head;
        while (!node.equals(null)) {
            if (node.value.compareTo(t) == 0) {
                return false;
            } else if (node.value.compareTo(t) > 0) {
                break;
            }
            node = node.next;
        }
        back.next = new Node(t, back, back.number + 1);
        return true;
    }

    /**
     * Returns the greatest element in this set strictly less than the given element, or null if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than e, or null if there is no such element
     */
    @Override
    public T lower(T t) {
        Node node = head;
        while (true) {
            if (node.next.value.equals(null))
                return null;
            if (node.next.value.compareTo(t) >= 0)
                return node.value;
            node = node.next;
        }
    }

    /**
     * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than or equal to e, or null if there is no such element
     */
    @Override
    public T floor(T t) {
        Node node = head;
        while (true) {
            if (node.next.value.equals(null))
                return null;
            if (node.next.value.compareTo(t) > 0)
                return node.value;
            node = node.next;
        }
    }

    /**
     * Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than or equal to e, or null if there is no such element
     */
    @Override
    public T ceiling(T t) {
        Node node = back;
        while (true) {
            if (node.previous.value.equals(null))
                return null;
            if (node.previous.value.compareTo(t) <= 0)
                return node.previous.value;
            node = node.previous;
        }
    }

    /**
     * Returns the least element in this set strictly greater than the given element, or null if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than e, or null if there is no such element
     */
    @Override
    public T higher(T t) {
        Node node = back;
        while (true) {
            if (node.previous.value.equals(null))
                return null;
            if (node.previous.value.compareTo(t) < 0)
                return node.value;
            node = node.next;
        }
    }

    /**
     * Retrieves and removes the first (lowest) element, or returns null if this set is empty.
     *
     * @return the first element, or null if this set is empty
     */
    @Override
    public T pollFirst() {
        return head.next.value;
    }

    /**
     * Retrieves and removes the last (highest) element, or returns null if this set is empty.
     *
     * @return the last element, or null if this set is empty
     */
    @Override
    public T pollLast() {
        Node node = head;
        while (true) {
            if (node.next.equals(null))
                return node.value;
            node = node.next;
        }
    }

    /**
     * Returns an iterator over the elements in this set, in ascending order.
     *
     * @return an iterator over the elements in this set, in ascending order
     */
    @Override
    public Iterator<T> iterator() {
        return iterator;
    }

    /**
     * Returns a reverse order view of the elements contained in this set.
     *
     * @return a reverse order view of this set
     */
    @Override
    public NavigableSet<T> descendingSet() {
        T[] array = (T[]) new Object[back.number];
        Node node = head;
        for (int i = 0; i < array.length; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return new MyNavigableSet<T>(array);
    }

    /**
     * Returns an iterator over the elements in this set, in descending order
     *
     * @return an iterator over the elements in this set, in descending order
     */
    @Override
    public Iterator<T> descendingIterator() {
        return descendingIterator;
    }

    /**
     * Returns a view of the portion of this set whose elements range from fromElement to toElement
     *
     * @param fromElement   low endpoint of the returned set
     * @param fromInclusive true if the low endpoint is to be included in the returned view
     * @param toElement     high endpoint of the returned set
     * @param toInclusive   true if the high endpoint is to be included in the returned view
     * @return a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive
     */
    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        Node node = head;
        while (true) {
            if (!node.equals(null)) return null;

            if (fromInclusive) {
                if (node.value.compareTo(fromElement) >= 0) {
                    break;
                }
            } else if (node.value.compareTo(fromElement) > 0) {
                break;
            }
            node = node.next;
        }
        MyNavigableSet<T> navigableSet = new MyNavigableSet<T>();
        if (toInclusive) {
            while (node.value.compareTo(fromElement) <= 0) {
                if (!node.equals(null)) return null;
                navigableSet.add(node.value);
                node = node.next;
            }
        } else {
            while (node.value.compareTo(fromElement) < 0) {
                if (!node.equals(null)) return null;
                navigableSet.add(node.value);
                node = node.next;
            }
        }
        return navigableSet;
    }

    /**
     * Returns a view of the portion of this set whose elements are less than (or equal to, if inclusive is true) toElement
     *
     * @param toElement high endpoint of the returned set
     * @param inclusive true if the high endpoint is to be included in the returned view
     * @return a view of the portion of this set whose elements are less than (or equal to, if inclusive is true) toElement
     */
    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        return subSet(null, true, toElement, inclusive);
    }

    /**
     * Returns a view of the portion of this set whose elements are greater than (or equal to, if inclusive is true) fromElement
     *
     * @param fromElement low endpoint of the returned set
     * @param inclusive   true if the low endpoint is to be included in the returned view
     * @return a view of the portion of this set whose elements are greater than or equal to fromElement
     */
    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        return subSet(fromElement, inclusive, null, true);
    }

    /**
     * Returns a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive
     *
     * @param fromElement low endpoint (inclusive) of the returned set
     * @param toElement   high endpoint (exclusive) of the returned set
     * @return a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive
     */
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        subSet(toElement, false, toElement, false);
        return null;
    }

    /**
     * Returns a view of the portion of this set whose elements are strictly less than toElement
     *
     * @param toElement high endpoint (exclusive) of the returned set
     * @return a view of the portion of this set whose elements are strictly less than toElement
     */
    @Override
    public SortedSet<T> headSet(T toElement) {
        return headSet(toElement, false);
    }

    /**
     * Returns a view of the portion of this set whose elements are greater than or equal to fromElement
     *
     * @param fromElement low endpoint (inclusive) of the returned set
     * @return a view of the portion of this set whose elements are greater than or equal to fromElement
     */
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return tailSet(fromElement, false);
    }

    /**
     * Returns the comparator used to order the elements in this set
     *
     * @return the comparator used to order the elements in this set
     */
    @Override
    public Comparator<? super T> comparator() {
        return (Comparator<T>) Comparator.naturalOrder();
    }

    /**
     * Returns the first (lowest) element currently in this set
     *
     * @return the first (lowest) element currently in this set
     */
    @Override
    public T first() {
        return head.value;
    }

    /**
     * Returns the last (highest) element currently in this set
     *
     * @return the last (highest) element currently in this set
     */
    @Override
    public T last() {
        return back.value;
    }

    /**
     * Returns the number of elements in this set
     *
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return back.number;
    }

    /**
     * Element of Set
     */
    private class Node {
        private T value;
        private int number;
        private Node next, previous;

        public Node(T value, Node previous, int number) {
            this.value = value;
            this.previous = previous;
            this.number = number;
        }
    }

}
