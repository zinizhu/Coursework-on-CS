public class OffByN implements CharacterComparator {
    private int distance;

    public OffByN(int n) {
        distance = n;
    }

    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == distance) {
            return true;
        }
        return false;
    }
}
