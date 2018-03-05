public class LinkedListDeque<T> {
    /**
     * Initialization of parameters.
     */
    private IntNode sentinel;
    private int size;


    /**
     * Create a sentinel whose previous and next are both null.
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null, null, null);
    }

    /**
     * Add x to the front of the list.
     */
    public void addFirst(T x) {
        sentinel.next = new IntNode(x, sentinel, sentinel.next);
        if (size == 0) {
            sentinel.previous = sentinel.next;
        } else {
            sentinel.next.next.previous = sentinel.next;
        }
        size += 1;
    }

    /**
     * Add x to the last of the list.
     */
    public void addLast(T x) {
        if (size == 0) {
            sentinel.previous = new IntNode(x, sentinel, null);
            sentinel.next = sentinel.previous;
        } else {
            sentinel.previous.next = new IntNode(x, sentinel.previous, null);
            sentinel.previous = sentinel.previous.next;
        }
        size = size + 1;
    }

    /**
     * Return true if Deque is empty, otherwise false.
     */
    public boolean isEmpty() {
        if (sentinel.next != null) {
            return false;
        }
        return true;
    }

    /**
     * Return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the item in deque from the first to last, seperated
     * by a space.
     */
    public void printDeque() {
        IntNode m = sentinel;
        while (m.next != null) {
            m = m.next;
            System.out.print(" ");
            System.out.print(m.item);
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = sentinel.next.item;
        if (size == 1) {
            sentinel.previous = null;
            sentinel.next = null;
        } else {
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
        }
        size = size - 1;
        return x;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = sentinel.previous.item;
        if (size == 1) {
            sentinel.next = null;
            sentinel.previous = null;
        } else {
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = null;
        }
        size = size - 1;
        return x;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (size < index + 1) {
            return null;
        }
        IntNode m = sentinel;
        int i = 0;
        while (i <= index) {
            m = m.next;
            i += 1;
        }
        return m.item;
    }

    /**
     * same as get, but use getRecursive.
     */
    public T getRecursive(int index) {
        if (size < index + 1 && index < 0) {
            return null;
        }

        IntNode p = sentinel.next;
        return recursive(p, index);
    }

    private T recursive(IntNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        p = p.next;
        return recursive(p, index - 1);
    }

    /**
     * Nest IntNode class.
     */
    private class IntNode {
        private T item;
        private IntNode next;
        private IntNode previous;

        private IntNode(T i, IntNode p, IntNode n) {
            item = i;
            next = n;
            previous = p;
        }
    }

}
