import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

public class TestPriorityQueue {

    private PriorityQueue<Integer> list;
    private PriorityQueue<Integer> listC;
    Comparator c;

    @Before
    public void setupQueue() {
        c = new TestComparator();
        list = new BinaryHeapPriorityQueue<Integer>();
        listC = new BinaryHeapPriorityQueue<Integer>(c);
    }

    @Test
    public void emptyWorks() {
        assertTrue(list.empty());
    }

    @Test
    public void insertWorks() {
        assertTrue(list.empty());
        list.insert(1);
        list.insert(2);
        list.insert(1);
        list.insert(2);
        list.insert(-10);
        assertFalse(list.empty());

        assertTrue(listC.empty());
        listC.insert(1);
        listC.insert(-10);
        listC.insert(100);
        listC.insert(5);
        assertFalse(listC.empty());
    }

    @Test
    public void removeWorks() {
        list.insert(1);
        list.insert(2);
        list.insert(1);
        list.insert(2);
        list.insert(-10);
        list.insert(-1);
        list.remove();
        list.remove();
        list.remove();

        listC.insert(1);
        listC.insert(2);
        listC.insert(3);
        listC.remove();
        listC.remove();
    }

    @Test
    public void topWorks() {
        list.insert(1);
        list.insert(2);
        list.insert(1);
        list.insert(2);
        list.insert(-10);
        list.insert(-1);
        assertEquals(2, list.top().intValue());
        list.remove();
        assertEquals(2, list.top().intValue());
        list.remove();
        assertEquals(1, list.top().intValue());
        list.remove();
        assertEquals(1, list.top().intValue());

        listC.insert(1);
        listC.insert(2);
        listC.insert(3);
        listC.insert(4);
        listC.insert(-1);
        listC.insert(-10);
        assertEquals(-10, listC.top().intValue());
        listC.remove();
        assertEquals(-1, listC.top().intValue());
    }
    
}

