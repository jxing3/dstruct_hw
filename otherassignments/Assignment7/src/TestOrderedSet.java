import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

public class TestOrderedSet {

    private OrderedSet<Integer> list;

    @Before
    public void setupList() {
        list = new OrderedArraySet<Integer>();
    }

    @Test
    public void insertWorks() {
        list.insert(1);
        list.insert(2);
        list.insert(1);
        list.insert(2);
        list.insert(-10);
        assertTrue(list.has(-10));
        assertTrue(list.has(1));
        assertTrue(list.has(2));
    }

    @Test
    public void iteratorWorks() {
        list.insert(1);
        list.insert(2);
        list.insert(1);
        list.insert(2);
        list.insert(-10);
        Iterator<Integer> i = list.iterator();
        int count = 0;
        while (i.hasNext()) {
            count++;
            i.next();
        }
        assertEquals(3, count);
    }

    @Test
    public void removeWorks() {
        list.insert(1);
        list.insert(2);
        list.insert(1);
        list.insert(2);
        list.insert(-10);
        list.insert(-1);
        Iterator<Integer> i = list.iterator();
        int count = 0;
        while (i.hasNext()) {
            count++;
            i.next();
        }
        assertEquals(4, count);

        list.remove(1);
        count = 0;
        Iterator<Integer> p = list.iterator();
        while (p.hasNext()) {
            count++;
            p.next();
        }
        assertEquals(3, count);
    }

    
}









