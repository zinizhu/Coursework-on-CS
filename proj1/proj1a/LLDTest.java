public class LLDTest {

    public static int test() {
        LinkedListDeque<Integer> actual = new LinkedListDeque<>();
        actual.addFirst(1);
        actual.addLast(2);
        actual.addLast(3);
        actual.addLast(4);
        actual.addLast(5);
        actual.addLast(6);
        int m = actual.getRecursive(3);
        int n = actual.getRecursive(1);
        return n;
    }

    public static void main(String[] args) {
        test();

    }
}
