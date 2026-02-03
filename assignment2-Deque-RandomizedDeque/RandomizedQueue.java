import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * RandomizedQueue stores items in an ARRAY.
 *
 * Unlike a normal queue, dequeue() removes a RANDOM item.
 *
 * We use a resizing array:
 *   - grow when full
 *   - shrink when 1/4 full
 *
 * This gives amortized O(1) time per operation.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items; // array to store items
    private int size;     // number of items

    // construct empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
        // O(1)
    }

    public boolean isEmpty() {
        return size == 0;
        // O(1)
    }

    public int size() {
        return size;
        // O(1)
    }

    /**
     * Add item to queue.
     *
     * If array is full, resize (O(n)), but this happens rarely.
     * Average cost per enqueue = O(1) amortized.
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null");

        if (size == items.length)
            resize(2 * items.length);

        items[size++] = item;
    }

    /**
     * Remove and return a RANDOM item.
     *
     * Steps:
     * 1. Pick random index r
     * 2. Save items[r]
     * 3. Move last item into r
     * 4. Set last slot to null
     *
     * All O(1).
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        int r = StdRandom.uniform(size);
        Item item = items[r];

        items[r] = items[size - 1];
        items[size - 1] = null;
        size--;

        if (size > 0 && size == items.length / 4)
            resize(items.length / 2);

        return item;
        // O(1) amortized
    }

    /**
     * Return random item without removing.
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        int r = StdRandom.uniform(size);
        return items[r];
        // O(1)
    }

    /**
     * Resize array to new capacity.
     * This takes O(n) time, but happens rarely.
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
        // O(n)
    }

    /**
     * Iterator returns items in RANDOM ORDER.
     *
     * We:
     *   - copy indices into an array
     *   - shuffle them
     *
     * Construction = O(n)
     * Each next() = O(1)
     */
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int[] order;
        private int current;

        public RandomIterator() {
            order = new int[size];
            for (int i = 0; i < size; i++)
                order[i] = i;

            StdRandom.shuffle(order);
            current = 0;
            // O(n)
        }

        public boolean hasNext() {
            return current < order.length;
            // O(1)
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return items[order[current++]];
            // O(1)
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(10);
        rq.enqueue(20);
        rq.enqueue(30);

        for (int x : rq)
            System.out.println(x);

        System.out.println("Dequeued: " + rq.dequeue());
    }
}
