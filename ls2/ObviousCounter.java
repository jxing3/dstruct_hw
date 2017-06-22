public class ObviousCounter implements Counter{
  private int value;

  public int value(){
    return this.value;
  }

  public void increment(){
    this.value++;
  }

  public static void main(String[] args){
    Counter c = new ObviousCounter();
    assert c.value()==0;
    c.increment();
    assert c.value()==1;
    c.increment();
    assert c.value()==2;
    c.increment();
    assert c.value()==3;
  }
}
