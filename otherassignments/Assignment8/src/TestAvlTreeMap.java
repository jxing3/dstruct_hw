import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

public class TestAvlTreeMap {

    private OrderedMap<Integer, String> list;

    @Before
    public void setupAvlTreeMap() {
        list = new AvlTreeMap<Integer, String>();
    }

    @Test
    public void emptyMapIsEmpty() {
        assertFalse(list.has(9));
    }
   
    @Test
    public void insertWorks() {
        list.insert(9, "a");
        assertTrue(list.has(9));
    }

    @Test
    public void putGetWorks() {
        list.insert(1, "a");
        list.insert(2, "b");
        list.insert(4, "c");
        list.insert(3, "d");
        list.insert(5, "e");
        list.insert(0, "f");
        assertTrue(list.has(5));
        assertFalse(list.has(6));
        assertEquals("f", list.get(0));
        list.put(0, "changed");
        assertEquals("changed", list.get(0));
    }

    @Test
    public void iteratorWorks() {
        list.insert(1, "a");
        list.insert(2, "b");
        list.insert(4, "c");
        list.insert(3, "d");
        list.insert(5, "e");
        list.insert(0, "f");
        assertTrue(list.has(5));
        assertFalse(list.has(6));
        assertEquals("f", list.get(0));
        list.put(0, "changed");
        assertEquals("changed", list.get(0));

        Iterator<Integer> i = list.iterator();
        int count =  0;
        while (i.hasNext()) {
            count++;
            i.next();
        }
        assertEquals(6, count);
    }   

    @Test
    public void removeWorks() {
        list.insert(1, "a");
        list.insert(2, "b");
        list.insert(4, "c");
        list.insert(3, "d");
        list.insert(5, "e");
        list.insert(0, "f");
        assertTrue(list.has(3));
        list.remove(3);
        assertFalse(list.has(3));
    }   

}
