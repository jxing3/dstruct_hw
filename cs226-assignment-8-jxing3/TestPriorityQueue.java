/* Jesse Xing
   jxing3@jhu.edu
   Stefan Reichenstein - sreiche2@jhu.edu
*/

import static org.junit.Assert.*;
 
import org.junit.Test;
 
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import java.util.Comparator;
 
@RunWith(Theories.class)
public class TestPriorityQueue {
 
    private interface Fixture {
        PriorityQueue<Integer> init();
    }

 
    @DataPoint
    public static final Fixture binaryHeapQueue = new Fixture() {
        public PriorityQueue<Integer> init() {
            return new BinaryHeapPriorityQueue<>();
        }

    };

    @DataPoint
    public static final Fixture binaryHeapQueue2 = new Fixture() {
        class TestComparator<T extends Comparable<? super T>> implements Comparator<T>{
    	
            public int compare(T o1, T o2) {
                 return o1.compareTo(o2);
            }
    	
        }
        
        public PriorityQueue<Integer> init() {
            Comparator<Integer> c = new TestComparator<Integer>();
            return new BinaryHeapPriorityQueue<>(c);
        }

    };
 

    @Theory @Test(expected=EmptyQueueException.class)
    public void removeThrowsEmptyQueueExcept(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        p.remove();
    }

    @Theory @Test(expected=EmptyQueueException.class)
    public void topThrowsEmptyQueueExcept(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        int i = p.top();
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void insertThrowsIllegalArgOnNull(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        p.insert(null);
    }

    @Theory
    public void newQueueIsEmpty(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        assertTrue(p.empty());
    }


    @Theory
    public void onInsertIsNotEmpty(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        p.insert(1);
        assertFalse(p.empty());
    }

    @Theory
    public void onRemoveIsEmpty(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        p.insert(1);
        p.remove();
        assertTrue(p.empty());
    }

    @Theory
    public void correctOrderingOdd(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        p.insert(1);
        p.insert(3);
        p.insert(2);
        String s="";
        while(!p.empty()) {
            s+= p.top();
            p.remove();
        }
        assertEquals("321", s);
    }

    @Theory
    public void correctOrderingEven(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        p.insert(1);
        p.insert(3);
        p.insert(2);
        p.insert(4);
        String s="";
        while(!p.empty()) {
            s+= p.top();
            p.remove();
        }
        assertEquals("4321", s);
    }

    @Theory
    public void manyInsertionsCorrectEven(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        for(int i = 0; i < 100; i++) {
            p.insert(i);
        }
        String t = "";
        for(int i = 99; i>=0; i--) {
            t += i;
        }
        String s="";
        while(!p.empty()) {
            s+= p.top();
            p.remove();
        }
        assertEquals(t, s);
    }

    @Theory
    public void manyInsertionsCorrectOdd(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        for(int i = 0; i < 99; i++) {
            p.insert(i);
        }
        String t = "";
        for(int i = 98; i>=0; i--) {
            t += i;
        }
        String s="";
        while(!p.empty()) {
            s+= p.top();
            p.remove();
        }
        assertEquals(t, s);
    }

    @Theory
    public void manyOrderedInsertionsCorrectEven(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        for(int i = 99; i >= 0; i--) {
            p.insert(i);
        }
        String t = "";
        for(int i = 99; i>=0; i--) {
            t += i;
        }
        String s="";
        while(!p.empty()) {
            s+= p.top();
            p.remove();
        }
        assertEquals(t, s);
    }

    @Theory
    public void manyOrderedInsertionsCorrectOdd(Fixture f) {
        PriorityQueue<Integer> p = f.init();
        for(int i = 98; i >= 0; i--) {
            p.insert(i);
        }
        String t = "";
        for(int i = 98; i>=0; i--) {
            t += i;
        }
        String s="";
        while(!p.empty()) {
            s+= p.top();
            p.remove();
        }
        assertEquals(t, s);
    }


}
