package proj1b;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);

    // Your tests go here.
    @Test
    public void testEqualChars() {
        offBy5.equalChars('a', 'f');  // true
        offBy5.equalChars('f', 'a');  // true
        offBy5.equalChars('f', 'h');  // false
    }
}
