public class ArrayTest {

    public static void testAddFirst() {
        ArrayDeque<Integer> actual = new ArrayDeque<>();
        actual.addFirst(5);
        actual.addFirst(4);
        actual.addFirst(3);
        actual.addFirst(2);
        actual.addLast(5);
        actual.addLast(6);
        actual.addLast(8);
        actual.addLast(9);
        actual.addFirst(0);
        actual.addFirst(3);
        actual.removeFirst();
        actual.removeFirst();
        actual.removeFirst();
        actual.removeLast();
        actual.removeLast();
        actual.removeLast();
        actual.removeFirst();
        actual.removeFirst();

    }


    public static void main(String[] args) {
        testAddFirst();
    }
}
