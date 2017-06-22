import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestArray {

    private interface Fixture<T> {
        Array<T> init(int length, T t) throws LengthException;
    }


    @DataPoint
    public static final Fixture<String> simpleArray = new Fixture<String>() {
        public Array<String> init(int length, String t) throws LengthException {
            return new SimpleArray<String>(length, t);
        }
    };
    @DataPoint
    public static final Fixture<String> sparseArray = new Fixture<String>() {
        public Array<String> init(int length, String t) throws LengthException {
            return new SparseArray<String>(length, t);
        }
    };

    @Theory
    public void lengthOfNewArrayIsFine(Fixture<String> f) throws LengthException{
        Array<String> a = f.init(4, "cs226");
        assertEquals(4, a.length());
    }

    @Theory
    public void contentOfNewArrayIsFine(Fixture<String> f) throws LengthException, IndexException {
        Array<String> a = f.init(4, "cs226");
        for (int i = 0; i < a.length(); i++) {
            assertEquals("cs226", a.get(i));
        }
    }

    @Theory
    public void setDoesNotChangeLength(Fixture<String> f) throws LengthException, IndexException {
        Array<String> a = f.init(4, "cs226");
        for (int i = 0; i < a.length(); i++) {
            a.set(i, "Peter");
            assertEquals(4, a.length());
            assertEquals("Peter", a.get(i));
        }
    }

    @Theory
    public void setOneDoesNotChangeOthers(Fixture<String> f) throws LengthException, IndexException{
         Array<String> a = f.init(4, "Peter");
         a.set(2, "Paul");

        assertEquals("Paul", a.get(2));
        assertEquals("Peter", a.get(0));
        assertEquals("Peter", a.get(1));
        assertEquals("Peter", a.get(3));

    }

    @Theory
    public void ManySetOperationsDoesNotChangeOthers(Fixture<String> f) throws LengthException, IndexException{
         Array<String> a = f.init(10, "Peter");
         a.set(2, "Paul");
         a.set(4, "Paul");
         a.set(6, "Paul");
         a.set(1, "Paul");
        assertEquals("Paul", a.get(2));
        assertEquals("Paul", a.get(4));
        assertEquals("Paul", a.get(6));
        assertEquals("Paul", a.get(1));
        assertEquals("Peter", a.get(0));
        assertEquals("Peter", a.get(3));
        assertEquals("Peter", a.get(5));
        assertEquals("Peter", a.get(7));
        assertEquals("Peter", a.get(8));
        assertEquals("Peter", a.get(9));

    }

    @Theory @Test(expected=LengthException.class)
    public void zeroArrayLengthBoom(Fixture<String> f) throws LengthException {
	Array<String> a = f.init(0, "cs226");
        
    }
    @Theory @Test(expected=LengthException.class)
    public void negativeArrayLengthBoom(Fixture<String> f) throws LengthException {
	Array<String> a = f.init(-4, "cs226");
    }
  
    @Theory @Test(expected=IndexException.class)
    public void TooLargeIndexBoomSet(Fixture<String> f) throws LengthException, IndexException {
	Array<String> a = f.init(4, "cs226");
        a.set(4, "boom");
    }

    @Theory @Test(expected=IndexException.class)
    public void TooLargeIndexBoomGet(Fixture<String> f) throws LengthException, IndexException {
	Array<String> a = f.init(4, "cs226");
        a.get(4);
    }
    @Theory @Test(expected=IndexException.class)
    public void NegativeIndexBoomSet(Fixture<String> f) throws LengthException, IndexException {
	Array<String> a = f.init(4, "cs226");
        a.set(-1, "boom");
    }

    @Theory @Test(expected=IndexException.class)
    public void NegativeIndexBoomGet(Fixture<String> f) throws LengthException, IndexException {
	Array<String> a = f.init(4, "cs226");
        a.get(-1);
    }

   
}
