/* Jesse Xing
   jxing3@jhu.edu
*/
//see assignment page: Assignment 1 Prob 3 for details

public class FlexibleCounter implements ResetableCounter{

  private int value;
  private int increment;
  private int startvalue;

  public FlexibleCounter(int val, int inc){
    this.startvalue=val;
    this.value = this.startvalue;
    this.increment = inc;

  }
  public int value(){
    return this.value;
  }

  public void up(){
    this.value+=increment;
  }

  public void down(){
    this.value-=increment;

  }

  public void reset(){
    value = this.startvalue;

  }

  public static void main(String[] args){
    ResetableCounter c= new FlexibleCounter(2, 6);

    assert c.value()== 2;
    c.up();
    assert c.value()== 8;
    c.up();
    assert c.value()== 14;
    c.reset();
    assert c.value()== 2;
    c.down();
    assert c.value()== -4;
    c.down();
    assert c.value()== -10;
    c.down();
    assert c.value()==-16;
    c.reset();
    assert c.value()==2;
 
    ResetableCounter d= new FlexibleCounter(4, -3);

    assert d.value()== 4;
    d.up();
    assert d.value()== 1;
    d.up();
    assert d.value()== -2;
    d.reset();
    assert d.value()== 4;
    d.down();
    assert d.value()== 7;
    d.down();
    assert d.value()== 10;
    d.down();
    assert d.value()== 13;
    d.reset();
    assert d.value()== 4;

    
    ResetableCounter e= new FlexibleCounter(-2, 0);

    assert e.value()== -2;
    e.up();
    assert e.value()== -2;
    e.up();
    assert e.value()== -2;
    e.reset();
    assert e.value()== -2;
    e.down();
    assert e.value()== -2;
    e.down();
    assert e.value()== -2;
    e.down();
    assert e.value()== -2;
    e.reset();
    assert e.value()== -2;



  }

}
