public class Palindrome {
    /**
     * Store the word in the Deque.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> actual = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            actual.addLast(c);
        }
        return actual;
    }

    /**
     * Return true if the given word is a palindrome, otherwise return false.
     * A palindrome is defined as a word that is same whether it is read
     * forwards or backwards.
     */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 1 || word.equals("")) {
            return true;
        }
        Deque<Character> origin = wordToDeque(word);
        return compare(origin);
    }

    private boolean compare(Deque<Character> word) {
        char first = word.removeFirst();
        char last = word.removeLast();
        if (first != last) {
            return false;
        }
        if (word.size() == 1 || word.size() == 0) {
            return true;
        } else {
            return compare(word);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() == 1 || word.equals("")) {
            return true;
        }
        Deque<Character> origin = wordToDeque(word);
        while (origin.size() >= 2) {
            char first = origin.removeFirst();
            char last = origin.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
