import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestSet {

    private interface Fixture {
        Set<Integer> init();
    }

    @DataPoint
    public static final Fixture simpleSet = new Fixture() {
        public Set<Integer> init() {
            return new SimpleSet<>();
        }
    };

    @DataPoint
    public static final Fixture arraySet = new Fixture() {
        public Set<Integer> init() {
            return new ArraySet<>();
        }
    };

    @Theory
    public void emptySetHasNothing(Fixture fix) {
        Set<Integer> set = fix.init();
        for (int i = 0; i < 247928; i++) {
            assertFalse(set.has(i));
        }
    }

    @Theory
    public void insertActuallyInserts(Fixture fix) {
        Set<Integer> set = fix.init();
        set.insert(10);
        assertTrue(set.has(10));
    }

    @Theory
    public void removeActuallyRemoves(Fixture fix) {
        Set<Integer> set = fix.init();
        set.insert(10);
        set.insert(20);
        assertTrue(set.has(20));
        set.remove(20);
        assertFalse(set.has(20));
        assertTrue(set.has(10));
    }
    @Theory
    public void newSetEmptyIterator(Fixture fix) {
        Set<Integer> set = fix.init();
        int count=0;
        for (int i :set) {
          count++;
        }
        assertEquals(count, 0);
    }

}
