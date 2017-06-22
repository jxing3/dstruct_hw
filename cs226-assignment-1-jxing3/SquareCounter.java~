/* Jesse Xing
   jxing3@jhu.edu
*/
//see assignment page: Assignment 1 Prob 3 for details

public class SquareCounter implements ResetableCounter{

  private int value;

  public SquareCounter(){

    value=2;

  }

  public int value(){
    return this.value;
  }

  public void up(){
    value= (int) Math.ceil(Math.pow(value, 2));
  }

  public void down(){
    value= (int) Math.ceil(Math.sqrt(value));

  }

  public void reset(){
    value = 2;

  }

  public static void main(String[] args){

    ResetableCounter c= new SquareCounter();
    assert c.value()== 2;
    c.up();
    assert c.value()== 4;
    c.up();
    assert c.value()==16;
    c.down();
    assert c.value()==4;
    c.down();
    assert c.value()==2;
    c.down();
    assert c.value()==2;
    c.up();
    c.up();
    c.reset();
    assert c.value()==2;


  }


}
