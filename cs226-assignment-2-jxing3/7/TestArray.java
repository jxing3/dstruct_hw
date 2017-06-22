import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;

import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestArray {

    private interface Fixture<T> {
        Array<T> init(int length, T t) throws LengthException;
    }

    // Warning: anonymous classes in use, read up on those!

    @DataPoint
    public static final Fixture<String> simpleArray = new Fixture<String>() {
        public Array<String> init(int length, String t) throws LengthException {
            return new SimpleArray<String>(length, t);
        }
    };
    @DataPoint
    public static final Fixture<String> listArray = new Fixture<String>() {
        public Array<String> init(int length, String t) throws LengthException {
            return new ListArray<String>(length, t);
        }
    };

    @Theory
    public void lengthOfNewArrayIsFine(Fixture<String> f) throws LengthException{
        Array<String> a = f.init(4, "cs226");
        assertEquals(4, a.length());
    }

    @Theory
    public void contentOfNewArrayIsFine(Fixture<String> f) throws LengthException, IndexException {
        Array<String> a = f.init(4, "cs226");
        for (int i = 0; i < a.length(); i++) {
            assertEquals("cs226", a.get(i));
        }
    }

    @Theory
    public void setDoesNotChangeLength(Fixture<String> f) throws LengthException, IndexException {
        Array<String> a = f.init(4, "cs226");
        for (int i = 0; i < a.length(); i++) {
            a.set(i, "Peter");
            assertEquals(4, a.length());
            assertEquals("Peter", a.get(i));
        }
    }

    @Theory @Test(expected=LengthException.class)
    public void zeroArrayLengthBoom(Fixture<String> f) throws LengthException {
	Array<String> a = f.init(0, "cs226");
    }

    @Theory @Test(expected=LengthException.class)
    public void negativeArrayLengthBoom(Fixture<String> f) throws LengthException {
	Array<String> a = f.init(-4, "cs226");
    }
}
