import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayTest {

  @Test
  public void lengthOfNewArrayIsFine() throws LengthException{
    Array <String> a = new SimpleArray<String>(4,"cs226");
    assertEquals(4, a.length());

  }

  @Test
  public void contentOfNewArrayIsFine() throws LengthException, IndexException{
     Array <String> a = new SimpleArray<String>(4,"cs226");

     for (int i = 0; i < a.length(); i++) {
            assertEquals("cs226",a.get(i));
        }
  }

  @Test
  public void setDoesNotChangeLength() throws LengthException, IndexException{
        Array <String> a = new SimpleArray<String>(4,"cs226");

        for (int i = 0; i < a.length(); i++) {
            a.set(i, "Peter");
            assertEquals(4,a.length());
            assertEquals("Peter", a.get(i));
        }
   }
  @Test (expected =LengthException.class)

  public void zeroArrayLengthBoom() throws LengthException{
    Array<String> b = new SimpleArray<String>(0, "Peter");
  }

  @Test (expected =LengthException.class)

  public void negativeArrayLengthBoom() throws LengthException{
    Array<String> b = new SimpleArray<String>(-4, "Peter");
  }


}
