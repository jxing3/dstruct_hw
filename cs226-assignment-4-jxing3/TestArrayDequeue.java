/* Jesse Xing
   jxing3@jhu.edu
*/


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class TestArrayDequeue {


    @Test
    public void newDequeueisEmpty() {
        ArrayDequeue<String> d = new ArrayDequeue<String>();
        assertTrue(d.empty());
        assertEquals(0, d.length());
    }

    @Test (expected=EmptyQueueException.class)
    public void frontOnEmptyQueueBoom() {
        ArrayDequeue<String> d = new ArrayDequeue<String>();
        String t = d.front();
    }

    @Test (expected=EmptyQueueException.class)
    public void backOnEmptyQueueBoom() {
        ArrayDequeue<String> d = new ArrayDequeue<String>();
        String t = d.back();
    }

    @Test (expected=EmptyQueueException.class)
    public void removeBackOnEmptyQueueBoom() {
        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.removeBack();
    }

    @Test (expected=EmptyQueueException.class)
    public void removeFrontOnEmptyQueueBoom() {
        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.removeFront();
    }

    @Test
    public void insertFrontWorks() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertFront("242");
        d.insertFront("535");

        String s = d.toString();
        String t = "[535, 242]";

    }

    @Test
    public void insertBackWorks() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertFront("242");
        d.insertFront("535");

        String s = d.toString();
        String t = "[242, 535]";

    }

    @Test
    public void removeFrontWorks() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertFront("242");
        d.insertFront("535");
        d.removeFront();

        String s = d.toString();
        String t = "[242]";

    }
   

    @Test
    public void removeBackWorks() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertFront("242");
        d.insertFront("535");
        d.removeBack();

        String s = d.toString();
        String t = "[535]";

    }

    @Test
    public void backWorks() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertFront("242");
        d.insertFront("535");
        
        String t = d.back();
        assertEquals("242", t);
        

    }

    @Test
    public void frontWorks() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertBack("242");
        d.insertBack("535");
        
        String t = d.front();
        assertEquals("242", t);
        

    }

    @Test
    public void lengthAndEmptyAreCorrect() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertFront("242");
        d.insertFront("535");
        d.removeFront();
        
        assertFalse(d.empty());
        assertEquals(1, d.length());

    }
   
    @Test
    public void growAndInsertsWork() {

        ArrayDequeue<String> d = new ArrayDequeue<String>();
        d.insertFront("1");
        d.insertBack("2");
        d.insertFront("3");
        d.insertFront("4");
        d.insertBack("5");
        d.insertFront("6");
        d.insertBack("7");
        d.insertFront("8");
        
        String s = d.front();
        String t = d.back();

        assertEquals(8, d.length());
        assertEquals("8", s);
        assertEquals("7", t);
        assertEquals("[8, 6, 4, 3, 1, 2, 5, 7]", d.toString());
        

    }

    @Test
    public void manyOperationsWork(){

        Dequeue<String> d = new ArrayDequeue <String>();

        d.insertBack("1");
        d.insertFront("2");
        d.insertBack("3");
       
        d.insertBack("4");
        d.insertFront("5");
        d.insertBack("6");
        d.insertBack("7");

        d.insertBack("8");

        d.insertFront("9");

        d.insertFront("10");

        d.insertBack("11");

        d.insertBack("12");

        String s = d.back();
        String t = d.front();

        d.removeBack();

        d.removeFront();
        
        assertEquals("12", s);

        assertEquals("10", t);
        assertEquals(10, d.length());

        assertEquals("[9, 5, 2, 1, 3, 4, 6, 7, 8, 11]", d.toString());

    }

    @Test
    public void extremeCasesWork(){

        Dequeue<String> e = new ArrayDequeue <String>();

        assertEquals("[]", e.toString());

        Dequeue<String> f = new ArrayDequeue <String>();

        f.insertBack("3233");

        assertEquals("[3233]", f.toString());

        Dequeue<String> g = new ArrayDequeue <String>();

        g.insertFront("3233");

        assertEquals("[3233]", g.toString());
    }

   
}
