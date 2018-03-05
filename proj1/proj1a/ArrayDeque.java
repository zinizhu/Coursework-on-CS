public class ArrayDeque<T> {


    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int newCapacity;
    private int firstIndex;
    private int lastIndex;

    /**
     * Create an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        newCapacity = 8;
    }

    /**
     * Resizes the underlying array to the target capacity.
     * If the usage ratio is lower than 25%, delete the excessive boxes
     * so that the requirement is satisfied.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int count = 0;
        while (count < size) {
            int seq = (firstIndex + count) % (items.length);
            a[count] = items[seq];
            count = count + 1;
        }
        items = a;
        newCapacity = capacity;
        nextFirst = items.length - 1;
        nextLast = size;
        firstIndex = 0;
        lastIndex = size - 1;
    }

    /**
     * Check if the memory is efficiently used.
     * If not, delete the excessive boxes.
     */
    private void checkCapacity() {
        if (4 * size <= newCapacity && newCapacity > 8) {
            resize(newCapacity / 2);
        }
    }

    /**
     * Adds an item of type Item to the front of the deque.
     * If the deque is empty, put the first element at position 0.
     * If the current position is at the first of the deque, put the new one
     * at the end of the deque.
     * If the deque is full, create a new box and put it in.
     */
    public void addFirst(T x) {
        //if the list is full, add new boxes;
        if (size == items.length) {
            resize(size * 2);
        }

        // if there is nothing in the box, put the item in position 0.
        if (size == 0) {
            nextFirst = 0;
            nextLast = 1;
            lastIndex = 0;
        }

        firstIndex = nextFirst;
        items[nextFirst] = x;

        nextFirst = (nextFirst + items.length - 1) % (items.length);

        size = size + 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * If the deque is empty, put the first element at position 0.
     * If the current position is at the end of the deque, put the new one
     * at the first of the deque.
     * If the deque is full, create a new box and put it in.
     */
    public void addLast(T x) {
        // If the box is full, add extra boxes.
        if (size == items.length) {
            resize(size * 2);
        }
        // If the box is empty, put the item in position 0;
        if (size == 0) {
            nextLast = 0;
            nextFirst = items.length - 1;
            firstIndex = 0;
        }

        lastIndex = nextLast;
        items[nextLast] = x;
        nextLast = (nextLast + 1) % (items.length);
        size = size + 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int i = 0;
        int index = (firstIndex + i) % (items.length);
        while (i < size) {
            System.out.print(" " + items[index]);
            i = i + 1;
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

        T x = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        firstIndex = (firstIndex + 1) % (items.length);
        size = size - 1;
        checkCapacity();
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

        T x = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        lastIndex = (lastIndex - 1 + items.length) % (items.length);
        size = size - 1;
        checkCapacity();
        return x;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int realIndex = (firstIndex + index) % (items.length);
        return items[realIndex];
    }

}
