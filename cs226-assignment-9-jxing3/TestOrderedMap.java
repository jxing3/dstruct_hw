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
public class TestMap {

    private interface Fixture {
        OrderedMap<Integer, String> init();
    }

    @DataPoint
    public static final Fixture orderedMapList = new Fixture() {
            public OrderedMap<Integer, String> init() {
                return new TreapMap<Integer, String>();
            }
        };

    @DataPoint
    public static final Fixture orderedMapList = new Fixture() {
            public OrderedMap<Integer, String> init() {
                return new AvlTreeMap<Integer, String>();
            }
        };


    //gets with empty fail (no mappings)
    @Theory @Test(expected=IllegalArgumentException.class)
    public void getWithEmptyFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.get(0);
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void putWithEmptyFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.put(0, "0");
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void removeWithEmptyFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.remove(0);
    }
    //end get iwth empty

    //null keys fail
    @Theory @Test(expected=IllegalArgumentException.class)
    public void getWithNullFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.get(null);
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void putWithNullFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.put(null, "0");
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void removeWithNullFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.remove(null);
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void insertWithNullFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.insert(null, "0");
    }
    //end null keys fail

    @Theory @Test(expected=IllegalArgumentException.class)
    public void insertDupFails(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.insert(0, "0");
        map.insert(0, "1");
    }

    //start will z's tests. modify!!!!
    @Theory
    public void emptyMapIsEmpty(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        assertFalse(map.has(9));
    }

    @Theory
    public void insertWorks(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.insert(9, "a");
        assertTrue(map.has(9));
    }

    @Theory
    public void putGetWorks(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(4, "c");
        map.insert(3, "d");
        map.insert(5, "e");
        map.insert(0, "f");
        assertTrue(map.has(5));
        assertFalse(map.has(6));
        assertEquals("f", map.get(0));
        map.put(0, "changed");
        assertEquals("changed", map.get(0));
    }

    @Theory
    public void iteratorWorks(Fixture f) {
        OrderedMap<Integer, String> list = f.init();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(4, "c");
        map.insert(3, "d");
        map.insert(5, "e");
        map.insert(0, "f");
        assertTrue(map.has(5));
        assertFalse(map.has(6));
        assertEquals("f", map.get(0));
        list.put(0, "changed");
        assertEquals("changed", map.get(0));

        Iterator<Integer> i = map.iterator();
        int count =  0;
        while (i.hasNext()) {
            count++;
            i.next();
        }
        assertEquals(6, count);
    }   

    @Theory
    public void removeWorks(Fixture f) {
        OrderedMap<Integer, String> map = f.init();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(4, "c");
        map.insert(3, "d");
        map.insert(5, "e");
        map.insert(0, "f");
        assertTrue(map.has(3));
        map.remove(3);
        assertFalse(map.has(3));
    }   

}
