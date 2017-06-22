import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

@RunWith(Theories.class)
public class TestOrderedMap {

    private interface Fixture {
        OrderedMap<Integer, String> init();	
    }

    @DataPoint
    public static final Fixture orderedMap = new Fixture() {
            public OrderedMap<Integer, String> init() {
                return new BinarySearchTreeMap<Integer, String>();
            }
        };
    @DataPoint
    public static final Fixture orderedMap1 = new Fixture() {
            public OrderedMap<Integer, String> init() {
                return new AVLTreeMap<Integer, String>();
            }
        };
    @DataPoint
    public static final Fixture orderedMap2 = new Fixture() {
            public OrderedMap<Integer, String> init() {
                return new TreapMap<Integer, String>();
            }
        };

    //gets with empty (non-existant key) fail
    @Theory @Test(expected=IllegalArgumentException.class)
    public void getWithEmptyFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.get(0);
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void putWithEmptyFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.put(0, "0");
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void removeWithEmptyFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.remove(0);
    }
    //end get iwth empty

    //null keys fail
    @Theory @Test(expected=IllegalArgumentException.class)
    public void getWithNullFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.get(null);
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void putWithNullFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.put(null, "0");
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void removeWithNullFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.remove(null);
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void insertWithNullFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.insert(null, "0");
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void hasWithNullFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.has(null);
    }
    //end null keys fail

    @Theory @Test(expected=IllegalArgumentException.class)
    public void insertDupFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.insert(0, "0");
        list.insert(0, "1");
    }

    @Theory
    public void emptyMapIsEmpty(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        assertEquals(list.has(0), false);
    }

    @Theory
    public void insertOneWorks(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.insert(0, "0");
        assertTrue(list.has(0));

        assertEquals(list.get(0), "0");
    }

    @Theory
    public void insertLotsWorks(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        for (int i = 99; i >= 0; --i) {
            list.insert(i, "" + i);
        }

        for (int i = 0; i < 100; ++i) {
            assertEquals(list.get(i), "" + i);
        }
    }

    @Theory
    public void putAndGetWorks(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        list.insert(0, "0");

        assertEquals("0", list.get(0));
        list.put(0, "asdf");
        assertEquals("asdf", list.get(0));
    }

    @Theory
    public void iteratorAND_ORDERWorks(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        for (int i = 99; i >= 0; --i) {
            list.insert(i, "" + i);
        }

        Iterator<Integer> iter = list.iterator();

        for (int i = 0; (i < 100) && (iter.hasNext()); ++i) {
            assertTrue(i == iter.next());
        }
    }   

    // test remove
    @Theory
    public void removeWorks(Fixture f) {
        OrderedMap<Integer, String> list = f.init();

        for (int i = 99; i >= 0; --i) {
            list.insert(i, "" + i);
        }

        list.remove(50);
        list.remove(2);

        Iterator<Integer> iter = list.iterator();

        for (int i = 0; (i < 100) && (iter.hasNext()); ++i) {
            if ((i == 50) || (i== 2)) {
                i = i;
            } else {
                assertTrue(i == iter.next());
            }
        }
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void getRemovedFails(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        for (int i = 9; i >= 0; --i) {
            list.insert(i, "" + i);
        }

        list.remove(0);
        list.remove(0);
    }

    @Theory
    public void addRemovedOK(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        for (int i = 9; i >= 0; --i) {
            list.insert(i, "" + i);
        }

        list.remove(0);
        list.insert(0, "NEW");
        assertEquals(list.get(0), "NEW");
        list.remove(0);
    }

    //end remove test
}
