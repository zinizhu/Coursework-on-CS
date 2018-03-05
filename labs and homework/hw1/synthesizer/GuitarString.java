package synthesizer;

//Make sure this class is public
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i < capacity; i += 1) {
            buffer.enqueue(0.0);
        }
    }

    private boolean checkDiff(double r, BoundedQueue<Double> list) {
        for (int j = 0; j < list.capacity(); j += 1) {
            if (r == list.get(j)) {
                return false;
            }
        }
        return true;
    }

    private void refill(BoundedQueue<Double> list) {
        double r = Math.random() - 0.5;
        if (checkDiff(r, list)) {
            list.dequeue();
            list.enqueue(r);
        } else {
            refill(list);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int capacity = buffer.capacity();
        for (int i = 0; i < capacity; i += 1) {
            refill(buffer);
        }
    }

    public void tic() {
        double deleteNum = buffer.dequeue();
        double nextNum = buffer.peek();
        double addNum = (deleteNum + nextNum) * 0.996 / 2;
        buffer.enqueue(addNum);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
