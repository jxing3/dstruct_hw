/* Jesse Xing
   jxing3@jhu.edu
*/

import static org.junit.Assert.*;
 
import org.junit.Test;
 
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import java.util.Iterator;
 
@RunWith(Theories.class)
public class TestTree {
 
    private interface Fixture {
        Tree<String> init();
    }

 
    @DataPoint
    public static final Fixture nodeList = new Fixture() {
        public Tree<String> init() {
            return new TreeImplementation<>();
        }
    };
 
    @Theory @Test(expected=InvalidPositionException.class)
    public void nullPositionisInvalid(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = null;
        boolean y = t.valid(p);
        assertFalse(y);
        t.removeAt(p); 
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void removedRootPositionisInvalid(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        String s = t.removeAt(p);
        assertEquals("Peter", s);
        boolean y = t.valid(p);
        assertFalse(y);
        t.removeAt(p);      
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void removedchildPositionisInvalid2(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.removeAt(p1);      
    }

    @Theory @Test(expected=RemovalException.class)
    public void removeNodewithChildrenisInvalid(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p);
    }

    @Theory
    public void removeNodewithoutChildrenisvalid(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter1");
        String s = t.removeAt(p1);
        String s1 = t.removeAt(p);
        assertEquals("Peter", s1);
        assertEquals("Peter1", s);
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void hasParentThrowsInvalidPositionException(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.hasParent(p1);      
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void hasChildrenThrowsInvalidPositionException(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.hasChildren(p1);      
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void isRootThrowsInvalidPositionException(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.isRoot(p1);      
    }


    @Theory @Test(expected=InvalidPositionException.class)
    public void insertChildThrowsInvalidPosition(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.insertChild(p1, "hi");      
    }


    @Theory @Test(expected=InvalidPositionException.class)
    public void removeAtThrowsInvalidPositionException(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.removeAt(p1);      
    }

    @Theory @Test(expected=EmptyTreeException.class)
    public void rootThrowsEmptyTreeExcep(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.root();
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void parentThrowsInvalidPositionException(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.parent(p1);      
    }
    @Theory @Test(expected=InvalidPositionException.class)
    public void parentThrowsInvalidPositionifRoot(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        t.parent(p);      
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void childrenThrowsInvalidPosition(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        String s = t.removeAt(p1);
        assertEquals("Peter", s);
        t.children(p1);      
    }

    @Theory @Test(expected=InvalidPositionException.class)
    public void childrenThrowsInvalidPositionifLeaf(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertChild(p, "Peter");
        t.children(p1);      
    }

    @Theory @Test(expected=InsertionException.class)
    public void InsertRootThrowsInsertionException(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Peter");
        Position<String> p1 = t.insertRoot("boom");
    }

    @Theory
    public void newTreeIsEmpty(Fixture fix) {
        Tree<String> t = fix.init();
        assertTrue(t.empty());
        assertEquals(0, t.size());
    }
    @Theory
    public void insertedTreeIsNotEmpty(Fixture f) {
        Tree<String> t = f.init();
        Position<String> x = t.insertRoot("Paul");
        boolean a = t.empty();
        assertFalse(a);
        assertEquals(1, t.size());
        assertEquals("Paul", x.get());
    }

   @Theory
    public void sizeincrementsanddecrements(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Paul");
        assertEquals(1, t.size());
        Position<String> p1 = t.insertChild(p, "Peter");
        assertEquals(2, t.size());
        String s = t.removeAt(p1);
        assertEquals(1, t.size());  
    }

    @Theory
    public void validisgood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Paul");
        assertTrue(t.valid(p));
        Position<String> p1 = t.insertChild(p, "Peter");
        assertTrue(t.valid(p1));
        String s = t.removeAt(p1);
        assertFalse(t.valid(p1));  
    }

    @Theory
    public void hasParentisgood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Paul");
        assertFalse(t.hasParent(p));
        Position<String> p1 = t.insertChild(p, "Peter");
        assertTrue(t.hasParent(p1)); 
    }

    @Theory
    public void hasChildrenisgood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Paul");
        assertFalse(t.hasChildren(p));
        Position<String> p1 = t.insertChild(p, "Peter");
        assertTrue(t.hasChildren(p));
        assertFalse(t.hasChildren(p1)); 
    }

    @Theory
    public void isRootisgood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Paul");
        assertTrue(t.isRoot(p));
        Position<String> p1 = t.insertChild(p, "Peter");
        assertFalse(t.isRoot(p1));
    }


    @Theory
    public void insertRootChildWorks(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Paul");
        assertTrue(t.isRoot(p));
        Position<String> p1 = t.insertChild(p, "Peter");
        assertFalse(t.isRoot(p1));
    }

    @Theory
    public void rootisgood(Fixture f) {
        Tree<String> t = f.init();
        t.insertRoot("Paul");
        Position<String> p = t.root();
        Position<String> p1 = t.insertChild(p, "Peter");
        assertTrue(t.isRoot(p));
    }

    @Theory
    public void parentisgood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("Paul");
        Position<String> p1 = t.insertChild(p, "Peter");
        Position<String> p2 = t.parent(p1);
        assertEquals(p, p2);
    }

    @Theory
    public void childrenisgood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("1");
        Position<String> p1 = t.insertChild(p, "2");
        Position<String> p2 = t.insertChild(p, "3");
        Position<String> p3 = t.insertChild(p2, "4");

        Iterable<Position<String>> iter = t.children(p);
        String s = "";

        for (Position<String> e: iter) {
            s+= e.get();
        }
        assertEquals("23",s);
    }

    @Theory
    public void positionsisgood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("1");
        Position<String> p1 = t.insertChild(p, "2");
        Position<String> p2 = t.insertChild(p, "3");
        Position<String> p3 = t.insertChild(p, "4");
        Position<String> p4 = t.insertChild(p2, "5");

        Iterable<Position<String>> iter = t.positions();
        String s = "";

        for (Position<String> e: iter) {
            s+= e.get();
        }
        assertEquals("12354",s);
    }


    @Theory
    public void newTreeHasEmptyIterator(Fixture fix) {
        Tree<String> tree = fix.init();
        Iterator<String> iter = tree.iterator();
        int size = 0;
        while (iter.hasNext()) {
            size += 1;
            iter.next();
        }
        assertEquals(0, size);
    }

    @Theory
    public void iteratorIsGood(Fixture fix) {
        Tree<String> t = fix.init();
        
        Position<String> p = t.insertRoot("1");
        String u = "1";
        for(int i = 0; i < 20; i++) {
            t.insertChild(p,Integer.toString(i));
            u += Integer.toString(i);
        }
        Iterator<String> iter = t.iterator();
        String v = "";
        int size = 0;
        while (iter.hasNext()) {
            size += 1;
            v += iter.next();
        }
        assertEquals(21, size);
        assertEquals(u, v);
    }
    @Theory
    public void traversePreisGood(Fixture fix) {

        Tree<String> t = fix.init();
        class preOr<T> extends Operation<T> {

            String s = "";

            public void pre(Position<T> p) {
            s+= p.get();

            }

        }
        preOr<String> o = new preOr<String>();
        Position<String> p = t.insertRoot("1");
        String u = "1";
        for(int i = 0; i < 20; i++) {
            t.insertChild(p,Integer.toString(i));
            u += Integer.toString(i);
        }
        t.traverse(o);
        assertEquals(u, o.s);   
    }

    @Theory
    public void traverseInisGood(Fixture fix) {

        Tree<String> t = fix.init();
        class inOr<T> extends Operation<T> {

            String s = "";

            public void in(Position<T> p) {
            s+= p.get();

            }

        }
        inOr<String> o = new inOr<String>();
        Position<String> p = t.insertRoot("+");
        for(int i = 1; i < 6; i++) {
            t.insertChild(p,Integer.toString(i));
        }
        t.traverse(o);
        assertEquals("1+2+3+4+5", o.s);   
    }
    @Theory
    public void traverseInisGoodwithOneRoot(Fixture fix) {

        Tree<String> t = fix.init();
        class inOr<T> extends Operation<T> {

            String s = "";

            public void in(Position<T> p) {
            s+= p.get();

            }

        }
        inOr<String> o = new inOr<String>();
        Position<String> p = t.insertRoot("+");
        t.traverse(o);
        assertEquals("+", o.s);   
    }
    @Theory
    public void traverseInisGoodwithOneChild(Fixture fix) {

        Tree<String> t = fix.init();
        class inOr<T> extends Operation<T> {

            String s = "";

            public void in(Position<T> p) {
            s+= p.get();

            }

        }
        inOr<String> o = new inOr<String>();
        Position<String> p = t.insertRoot("+");
        t.insertChild(p,"1");
        t.traverse(o);
        assertEquals("1+", o.s);   
    }
    @Theory
    public void traversePostisGood(Fixture fix) {

        Tree<String> t = fix.init();
        class postOr<T> extends Operation<T> {

            String s = "";

            public void post(Position<T> p) {
            s+= p.get();

            }

        }
        postOr<String> o = new postOr<String>();
        Position<String> p = t.insertRoot("0");
        for(int i = 1; i < 6; i++) {
            t.insertChild(p,Integer.toString(i));
        }
        t.traverse(o);
        assertEquals("123450", o.s);   
    }



    @Theory
    public void toStringisGood(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("1");
        Position<String> p1 = t.insertChild(p, "2");
        Position<String> p2 = t.insertChild(p, "3");
        String s = t.toString();
        assertEquals("[1, 2, 3]", s);

    }

    @Theory
    public void toStringisGood1(Fixture f) {
        Tree<String> t = f.init();
        String s = t.toString();
        assertEquals("[]", s);

    }
    @Theory
    public void toStringisGood2(Fixture f) {
        Tree<String> t = f.init();
        Position<String> p = t.insertRoot("1");
        String s = t.toString();
        assertEquals("[1]", s);

    }

    @Theory
    public void getputWorks(Fixture f) {

        Tree<String> t = f.init();

        Position<String> x = t.insertRoot("Hi");
        Position<String> y = t.insertChild(x, "Bye");
        x.put("Peter");
        y.put("Griffin");

        assertEquals("Peter", x.get());
        assertEquals("Griffin", y.get());
      

    }

}
