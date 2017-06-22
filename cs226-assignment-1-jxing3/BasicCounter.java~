/* Jesse Xing
   jxing3@jhu.edu
*/
//see assignment page: Assignment 1 Prob 3 for details

public class BasicCounter implements ResetableCounter{

  private int value;

  public BasicCounter(){

    value = 0;
  }
  public int value(){
    return this.value;
  }

  public void up(){
    value++;
  }

  public void down(){
    value--;

  }

  public void reset(){
    value = 0;

  }


  public static void main(String[] args){

    ResetableCounter c= new BasicCounter();
    assert c.value()== 0;
    c.up();
    assert c.value()== 1;
    c.up();
    assert c.value()==2;
    c.down();
    assert c.value()==1;
    c.down();
    assert c.value()==0;
    c.down();
    assert c.value()==-1;
    c.reset();
    assert c.value()==0;

  }

}
