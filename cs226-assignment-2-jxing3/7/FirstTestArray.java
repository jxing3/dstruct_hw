import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;

import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestArray {

/*
    @Test
    public void lengthOfNewArrayIsFine() throws LengthException {
        Array<String> a = new SimpleArray<String>(4, "cs226");
        assertEquals(4, a.length());
    }
*/

    @DataPoints
    public static Array<String>[] data() throws LengthException {
        return new Array[] {
            new ListArray<String>(4, "cs226"),
            new SimpleArray<String>(4, "cs226"),
        };
    }

    @Theory
    public void lengthOfNewArrayIsFine(Array<String> a) {
        assertEquals(4, a.length());
    }

    @Theory
    public void contentOfNewArrayIsFine(Array<String> a) throws IndexException {
        for (int i = 0; i < a.length(); i++) {
            assertEquals("cs226", a.get(i));
        }
    }

    @Theory
    public void setDoesNotChangeLength(Array<String> a) throws IndexException {
        for (int i = 0; i < a.length(); i++) {
            a.set(i, "Peter");
            assertEquals(4, a.length());
            assertEquals("Peter", a.get(i));
        }
    }


/*
    @Test(expected=LengthException.class)
    public void zeroArrayLengthBoom() throws LengthException {
        Array<String> b = new SimpleArray<String>(0, "Peter"); 
    }

    @Test(expected=LengthException.class)
    public void negativeArrayLengthBoom() throws LengthException {
        Array<String> b = new SimpleArray<String>(-4, "Peter"); 
    }
*/
}
