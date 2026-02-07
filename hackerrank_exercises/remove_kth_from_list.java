/*No top-level functions as in Python 

where previously we would have

def remove_kth_from_end(head, k):
    ...

we must define a class containing methods:

class Result {
    public static SinglyLinkedListNode removeKthNodeFromEnd(...) {
        ...
    }
}
*/

class Result {

    // Remove the k-th node from the end of a singly linked list (one pass)
    public static SinglyLinkedListNode removeKthNodeFromEnd(
            SinglyLinkedListNode head, int k) {

        if (head == null || k <= 0) return head;

        // Dummy node avoids special-case when removing the head
        SinglyLinkedListNode dummy = new SinglyLinkedListNode(0);
        dummy.next = head;

        SinglyLinkedListNode fast = dummy;
        SinglyLinkedListNode slow = dummy;

        // Move fast pointer k steps ahead
        for (int i = 0; i < k; i++) {
            if (fast.next == null) return head; // k > length
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the target node
        slow.next = slow.next.next;

        return dummy.next;
    }
}




class Result {

    /*
     * Remove the k-th node from the end of a singly linked list in ONE traversal.
     *
     * PARAMETERS
     * ----------
     * head : SinglyLinkedListNode
     *     The first node of the linked list.
     *
     * k : int
     *     The position from the end (1-based).
     *
     * RETURNS
     * -------
     * SinglyLinkedListNode
     *     The (possibly new) head of the list.
     *
     * IMPORTANT JAVA NOTES UP FRONT
     * ------------------------------
     * - This is a STATIC method: it belongs to the class Result, not an object.
     * - Java has no free functions (unlike Python); methods must be in classes.
     * - SinglyLinkedListNode is a reference type (like objects in Python).
     */
    public static SinglyLinkedListNode removeKthNodeFromEnd(
            SinglyLinkedListNode head, int k) {

        /*
         * EARLY EXIT (GUARD CLAUSE)
         *
         * If the list is empty, there's nothing to remove.
         * If k <= 0, the request is invalid.
         *
         * Java peculiarity:
         * - "null" is Java's equivalent of Python's None.
         * - int is a primitive and can never be null.
         */
        if (head == null || k <= 0) {
            return head;
        }

        /*
         * THE DUMMY NODE (THIS IS THE PART YOU ASKED ABOUT)
         * -------------------------------------------------
         * What is "dummy"?
         * - It is a *real node* that we create artificially.
         * - It is NOT part of the original list.
         * - It exists purely to make the algorithm simpler.
         * We create it with an arbitrary value (0 here).
         * Then we point it to the real head of the list.
         * Before:
         *     head -> A -> B -> C
         * After:
         *     dummy -> head -> A -> B -> C
         * Why do this?
         * - Removing the head node is normally a special case.
         * - With a dummy node, EVERY deletion looks the same.
         * Java peculiarity:
         * - Objects must be created explicitly with "new".
         */
        SinglyLinkedListNode dummy = new SinglyLinkedListNode(0);
        dummy.next = head;

        /*
         * TWO POINTERS (REFERENCES)
         * fast and slow are NOT new nodes.
         * They are REFERENCES pointing to existing nodes.
         * This is exactly like Python:
         *     fast = dummy
         *     slow = dummy
         * Both start at the dummy node.
         */
        SinglyLinkedListNode fast = dummy;
        SinglyLinkedListNode slow = dummy;

        /*
         * STEP 1: MOVE FAST k STEPS AHEAD
         * After this loop:
         * - fast is k nodes ahead of slow.
         * If we cannot move k steps, k is larger than the list length.
         * In that case, the problem statement says:
         *     "If k is invalid, return the original list."
         */
        for (int i = 0; i < k; i++) {

            /*
             * We check fast.next, NOT fast:
             * - fast starts at dummy
             * - fast.next is the first real node
             * If fast.next is null, we've hit the end too early.
             */
            if (fast.next == null) {
                return head;  // original head, unchanged
            }

            fast = fast.next;
        }

        /*
         * STEP 2: MOVE FAST AND SLOW TOGETHER
         * We stop when fast.next == null,
         * meaning fast is at the LAST node.
         * At this moment:
         * - slow is JUST BEFORE the node we want to remove.
         */
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        /*
         * STEP 3: REMOVE THE TARGET NODE
         *
         * slow.next is the node to delete.
         * We "skip" it by re-linking:
         *
         * Before:
         *     slow -> X -> Y
         *
         * After:
         *     slow -> Y
         *
         * Java peculiarity:
         * - No manual memory management.
         * - The removed node becomes unreachable and is garbage collected.
         */
        slow.next = slow.next.next;

        /*
         * STEP 4: RETURN THE NEW HEAD
         *
         * We return dummy.next, NOT head, because:
         * - The original head may have been removed.
         * - dummy.next always points to the correct head.
         *
         * The dummy node itself is discarded.
         */
        return dummy.next;
    }
}
