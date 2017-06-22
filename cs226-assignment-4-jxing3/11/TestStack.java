import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;

import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestStack {

    private interface Fixture<T> {
        Stack<T> init();
    }

    // Warning: anonymous classes in use, read up on those!

    @DataPoint
    public static final Fixture<String> arrayStack = new Fixture<String>() {
        public Stack<String> init() {
            return new ArrayStack<String>();
        }
    };

    @Theory
    public void newStackIsEmpty(Fixture<String> f) {
        Stack<String> s = f.init();
        assertTrue(s.empty());
    }

    @Theory
    public void pushedStackIsNotEmpty(Fixture<String> f) {
        Stack<String> s = f.init();
        s.push("Peter");
        assertFalse(s.empty());
    }

    @Theory
    public void pushedThingIsTop(Fixture<String> f) {
        Stack<String> s = f.init();
        s.push("Peter");
        assertEquals("Peter", s.top());
    }

    @Theory
    public void pushAFewWorksFineish(Fixture<String> f) {
        Stack<String> s = f.init();
        s.push("Peter");
        assertEquals("Peter", s.top());
        s.push("Paul");
        assertEquals("Paul", s.top());
    }
}
