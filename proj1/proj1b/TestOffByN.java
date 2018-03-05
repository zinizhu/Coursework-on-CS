import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByFive = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(offByFive.equalChars('a', 'f'));
        assertFalse(offByFive.equalChars('a', 'z'));
        assertTrue(offByFive.equalChars('f', 'a'));
        assertFalse(offByFive.equalChars('a', 'F'));
        assertFalse(offByFive.equalChars('a', 'a'));
    }
}
