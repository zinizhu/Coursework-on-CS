import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    */
    static Palindrome palindrome = new Palindrome();


    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("toohottohoot"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("Asa"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("121"));
        assertFalse(palindrome.isPalindrome("s12"));
        assertTrue(palindrome.isPalindrome("$1ff1$"));
    }

    @Test
    public void testIsPalindromeMore() {
        assertTrue(palindrome.isPalindrome("flake", new OffByOne()));
        assertFalse(palindrome.isPalindrome("aaaa", new OffByOne()));
        assertFalse(palindrome.isPalindrome("Asb", new OffByOne()));
        assertTrue(palindrome.isPalindrome("bceda", new OffByOne()));
        assertTrue(palindrome.isPalindrome("", new OffByOne()));
        assertFalse(palindrome.isPalindrome(null, new OffByOne()));
        assertTrue(palindrome.isPalindrome("122", new OffByOne()));
        assertFalse(palindrome.isPalindrome("s12", new OffByOne()));
    }
}
