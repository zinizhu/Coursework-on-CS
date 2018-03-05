public class OffByOne implements CharacterComparator {
    /**
     * Return true for letters that are different by exactly one letter.
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == 1) {
            return true;
        }
        return false;
    }
}
