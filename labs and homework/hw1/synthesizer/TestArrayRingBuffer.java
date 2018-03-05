package synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        int p = arb.peek();
        int expectA = 1;
        Assert.assertEquals(expectA, p);
        int d = arb.dequeue();
        int expectB = 1;
        Assert.assertEquals(expectB, d);
        int c = arb.fillCount();
        int expectC = 2;
        Assert.assertEquals(expectC, c);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
