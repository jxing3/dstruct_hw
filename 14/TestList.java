import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;

import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestList {

    private interface Fixture<T> {
        List<T> init();
    }

    @DataPoint
    public static final Fixture<String> nodeList = new Fixture<String>() {
        public List<String> init() {
            return new NodeList<String>();
        }
    };

    @Theory
    public void newListIsEmpty(Fixture<String> f) {
        List<String> s = f.init();
        assertTrue(s.empty());
    }

    @Theory
    public void insertFrontIsFirst(Fixture<String> f) {
        List<String> s = f.init();
        s.insertFront("Paul");
        Position<String> p = s.insertFront("Peter");
        Position<String> q = s.front();
        assertEquals(p, q);
        assertEquals("Peter", s.get(q));
    }

    @Theory
    @Test(expected=InvalidPositionException.class)
    public void nullPositionGoBoom(Fixture<String> f) {
        List<String> s = f.init();
        s.get(null);
    }




}
