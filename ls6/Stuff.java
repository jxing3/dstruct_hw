import org.junit.Test;
import org.junit.Assert.assertEquals;

public class Stuff {

  public int find(int a[], int k){
    for (int i=0; //1 a
       i <a.length; // n+1 c
        i++){ //n a
      if(a[i]==k){ //n c
        return i;
      }
    }

    return -1;
  }

//C(n) = 2n+1
//A(n) = n+1

  @test
  public void notFoundReturnsBad(){
    int a[]= {10, 20, 30, 40};
    assertEquals(-1, find(a,31));

    assertEquals(-1, find(a,31));
    assertEquals(-1, find(a,1));
    assertEquals(-1, find(a,41));

    assertEquals(-1, find(0,10));
    assertEquals(-1, find(1,20));
    assertEquals(-1, find(3,40));
  }

}

//try even/odd/empty/singleton array(rules of thumb)
