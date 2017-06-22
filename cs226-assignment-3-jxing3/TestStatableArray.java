import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestStatableArray {


    @Test
    public void lengthOfNewArrayIsFine() throws InvalidLengthException{
        StatableArray<String> a = new StatableArray<String>(4, "cs226");
        assertEquals(4, a.length());
    }

    @Test
    public void contentOfNewArrayIsFine() throws InvalidLengthException, InvalidIndexException {
        StatableArray<String> a = new StatableArray<String>(4, "cs226");
        for (int i = 0; i < a.length(); i++) {
            assertEquals("cs226", a.get(i));
        }
    }

    @Test
    public void setDoesNotChangeLength() throws InvalidLengthException, InvalidIndexException {
        StatableArray<String> a = new StatableArray<String>(4, "cs226");
        for (int i = 0; i < a.length(); i++) {
            a.set(i, "Peter");
            assertEquals(4, a.length());
            assertEquals("Peter", a.get(i));
        }
    }

    @Test
    public void setOneDoesNotChangeOthers() throws InvalidLengthException, InvalidIndexException{
         StatableArray<String> a = new StatableArray<String>(4, "Peter");
         a.set(2, "Paul");

        assertEquals("Paul", a.get(2));
        assertEquals("Peter", a.get(0));
        assertEquals("Peter", a.get(1));
        assertEquals("Peter", a.get(3));

    }

    @Test
    public void ManySetOperationsDoesNotChangeOthers() throws InvalidLengthException, InvalidIndexException{
        StatableArray<String> a = new StatableArray<String>(10, "Peter");
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

    @Test(expected=InvalidLengthException.class)
    public void zeroArrayLengthBoom() throws InvalidLengthException {
	StatableArray<String> a = new StatableArray<String>(0, "Peter");
        
    }
    @Test(expected=InvalidLengthException.class)
    public void negativeArrayLengthBoom() throws InvalidLengthException {
        StatableArray<String> a = new StatableArray<String>(-4, "cs226");
    }
  
    @Test(expected=InvalidIndexException.class)
    public void TooLargeIndexBoomSet() throws InvalidLengthException, InvalidIndexException {
        StatableArray<String> a = new StatableArray<String>(4, "cs226");
        a.set(4, "boom");
    }

    @Test(expected=InvalidIndexException.class)
    public void TooLargeIndexBoomGet() throws InvalidLengthException, InvalidIndexException {
        StatableArray<String> a = new StatableArray<String>(4, "cs226");
        a.get(4);
    }
    @Test(expected=InvalidIndexException.class)
    public void NegativeIndexBoomSet() throws InvalidLengthException, InvalidIndexException {
        StatableArray<String> a = new StatableArray<String>(4, "cs226");
        a.set(-1, "boom");
    }

    @Test(expected=InvalidIndexException.class)
    public void NegativeIndexBoomGet() throws InvalidLengthException, InvalidIndexException {
        StatableArray<String> a = new StatableArray<String>(4, "cs226");
        a.get(-1);
    }

    @Test
    public void newStatisticsisZero(){
        StatableArray<String> a = new StatableArray<String>(12, "cs226");

      String s= "StatableArray Statistics\n";
      s+= "========================\n";
      s+= "Total: 0\n";
      s+= "get(): 0\n";
      s+= "set(): 0\n";
      s+= "length(): 0";

      assertEquals(s, a.getStatistics());



    }

    @Test
    public void getLengthandTotalIncrementCorrectly(){
        StatableArray<String> a = new StatableArray<String>(12, "cs226");
        for (int i = 0; i < a.length(); i++) {
            assertEquals("cs226", a.get(i));
        }

      String s= "StatableArray Statistics\n";
      s+= "========================\n";
      s+= "Total: 25\n";
      s+= "get(): 12\n";
      s+= "set(): 0\n";
      s+= "length(): 13";

      assertEquals(s, a.getStatistics());



    }

    @Test
    public void getSetandTotalIncrementCorrectly(){
        
        StatableArray<String> a = new StatableArray<String>(10, "Peter");
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

      String s= "StatableArray Statistics\n";
      s+= "========================\n";
      s+= "Total: 14\n";
      s+= "get(): 10\n";
      s+= "set(): 4\n";
      s+= "length(): 0";

      assertEquals(s, a.getStatistics());



    }

    @Test
    public void lengthTotalIncrementCorrectly(){
        
        StatableArray<String> a = new StatableArray<String>(10, "Peter");
        
        for(int i=0; i<10;i++){
          a.length();
        }

      String s= "StatableArray Statistics\n";
      s+= "========================\n";
      s+= "Total: 10\n";
      s+= "get(): 0\n";
      s+= "set(): 0\n";
      s+= "length(): 10";

      assertEquals(s, a.getStatistics());



    }


    @Test
    public void statisticsResetCorrectly(){
        
        StatableArray<String> a = new StatableArray<String>(10, "Peter");
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
        a.length();
        a.length();

      a.resetStatistics(); 

      String s= "StatableArray Statistics\n";
      s+= "========================\n";
      s+= "Total: 0\n";
      s+= "get(): 0\n";
      s+= "set(): 0\n";
      s+= "length(): 0";

      assertEquals(s, a.getStatistics());


   }
}
