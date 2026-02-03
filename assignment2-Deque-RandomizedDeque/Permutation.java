import edu.princeton.cs.algs4.StdIn;

/**
 * Reads strings from standard input and prints k of them at random.
 *
 * This uses RandomizedQueue to guarantee:
 *   - uniform randomness
 *   - no duplicates
 *   - O(n) total running time
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        // Read all input strings
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        // Print exactly k random items
        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }
    }
}
