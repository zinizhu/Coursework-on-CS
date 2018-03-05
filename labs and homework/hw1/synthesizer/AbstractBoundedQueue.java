package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }

    public boolean isEmpty() {
        if (fillCount == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (fillCount == capacity) {
            return true;
        }
        return false;
    }

    @Override
    public abstract T peek();

    @Override
    public abstract T dequeue();

    @Override
    public abstract void enqueue(T x);

    @Override
    public abstract T get(int index);

}
