import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {
    /**
     * @Test public void testStudentArrayDequeAndArrayDequeSolution() {
     * StudentArrayDeque<Integer> sad = new StudentArrayDeque();
     * ArrayDequeSolution<Integer> ads = new ArrayDequeSolution();
     * boolean condition = true;
     * <p>
     * while (condition) {
     * double randomNumber = StdRandom.uniform();
     * <p>
     * if (randomNumber < 0.25) {
     * int addNum = StdRandom.uniform(20);
     * sad.addFirst(addNum);
     * ads.addFirst(addNum);
     * System.out.println("addFirst(" + addNum + ")");
     * } else if (randomNumber < 0.5) {
     * int addNum = StdRandom.uniform(20);
     * sad.addLast(addNum);
     * ads.addLast(addNum);
     * System.out.println("addLast(" + addNum + ")");
     * } else if (randomNumber < 0.75) {
     * if (!sad.isEmpty() && !ads.isEmpty()) {
     * Integer sAD = sad.removeFirst();
     * Integer aDS = ads.removeFirst();
     * if (sAD != aDS) {
     * condition = false;
     * System.out.println("removeFirst()");
     * }
     * }
     * } else {
     * if (!sad.isEmpty() && !ads.isEmpty()) {
     * Integer sAD = sad.removeLast();
     * Integer aDS = ads.removeLast();
     * if (sAD != aDS) {
     * condition = false;
     * System.out.println("removeLast()");
     * }
     * }
     * }
     * }
     * }
     */
    @Test
    public void testSandADeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution();
        boolean condition = true;

        while (condition) {
            double randomNumber = StdRandom.uniform();

            if (randomNumber < 0.25) {
                int addNum = StdRandom.uniform(20);
                sad.addFirst(addNum);
                ads.addFirst(addNum);
                System.out.println("addFirst(" + addNum + ")");
            } else if (randomNumber < 0.5) {
                int addNum = StdRandom.uniform(20);
                sad.addLast(addNum);
                ads.addLast(addNum);
                System.out.println("addLast(" + addNum + ")");
            } else if (randomNumber < 0.75) {
                if (!sad.isEmpty() && !ads.isEmpty()) {
                    Integer sAD = sad.removeFirst();
                    Integer aDS = ads.removeFirst();
                    System.out.println("removeFirst()");
                    assertEquals("removeFirst()", sAD, aDS);
                }
            } else {
                if (!sad.isEmpty() && !ads.isEmpty()) {
                    Integer sAD = sad.removeLast();
                    Integer aDS = ads.removeLast();
                    System.out.println("removeLast()");
                    assertEquals("removeLast()", sAD, aDS);
                }
            }
        }
    }
}

