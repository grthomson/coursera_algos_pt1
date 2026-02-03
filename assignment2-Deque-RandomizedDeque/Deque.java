import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Deque (double-ended queue) implemented using a DOUBLY LINKED LIST.
 *
 * A linked list is a chain of nodes.
 * Each node stores:
 *   - an item
 *   - a reference to the next node
 *   - a reference to the previous node
 *
 * This allows us to:
 *   add/remove from BOTH ends in O(1) worst-case time.
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * Node = one element in the linked list.
     */
    private class Node {
        Item item;   // the data stored
        Node next;   // link to next node
        Node prev;   // link to previous node
    }

    private Node first; // front of deque
    private Node last;  // back of deque
    private int size;   // number of elements

    // Construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
        // O(1)
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return size == 0;
        // O(1)
    }

    // Number of items in the deque
    public int size() {
        return size;
        // O(1)
    }

    /**
     * Add an item to the FRONT of the deque.
     *
     * We create a new node and link it before the old first node.
     * No traversal is needed → O(1) time.
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null");

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.prev = null;

        if (isEmpty()) {
            last = newNode; // if empty, first and last are same
        } else {
            first.prev = newNode;
        }

        first = newNode;
        size++;
        // O(1)
    }

    /**
     * Add an item to the BACK of the deque.
     *
     * Again, no traversal → just adjust pointers → O(1).
     */
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null");

        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = last;
        newNode.next = null;

        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }

        last = newNode;
        size++;
        // O(1)
    }

    /**
     * Remove and return the item from the FRONT.
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty");

        Item item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }

        return item;
        // O(1)
    }

    /**
     * Remove and return the item from the BACK.
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty");

        Item item = last.item;
        last = last.prev;
        size--;

        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }

        return item;
        // O(1)
    }

    /**
     * Iterator lets us loop over items from front to back.
     * Each next() call moves one step forward → O(1) per operation.
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
            // O(1)
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
            // O(1)
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Unit testing
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addLast(2);
        d.addLast(3);

        for (int x : d) {
            System.out.println(x);
        }

        System.out.println("Removed: " + d.removeFirst());
        System.out.println("Removed: " + d.removeLast());
    }
}
