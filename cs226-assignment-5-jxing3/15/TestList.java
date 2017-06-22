import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestList {

    private interface Fixture {
        List<String> init();
    }

    @DataPoint
    public static final Fixture nodeList = new Fixture() {
        public List<String> init() {
            return new NodeList<>();
        }
    };

    @Theory
    public void newListIsEmpty(Fixture fix) {
        List<String> list = fix.init();
        assertTrue(list.empty());
        assertEquals(0, list.length());
    }

    @Theory
    public void newListHasEmptyIterator(Fixture fix) {
        List<String> list = fix.init();
        Iterator<String> iter = list.forward();
        int size = 0;
        while (iter.valid()) {
            size += 1;
            iter.next();
        }
        assertEquals(0, size);
    }

}
