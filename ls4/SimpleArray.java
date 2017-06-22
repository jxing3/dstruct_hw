public class SimpleArray <T> implements Array<T>{
  //would like new, but it's Java so constructor so sadface

  private T rep[];

  public SimpleArray(int length, T t) throws LengthException{

    if (length<=0){
      throw new LengthException();
   }
    this.rep = (T[]) new Object[length]; //Java sadface
    for (int i =0;i<this.rep.length;i++){
       this.rep[i] =t;
    }

  }

  public void set (int index, T t) throws IndexException{
    this.rep[index] = t;

    if (index <0||index >=this.rep.length){
      throw new IndexException();
    }
  }

  public T get(int index) throws IndexException{
   if (index <0||index >=this.rep.length){
      throw new IndexException();
    }

    return this.rep[index];
  }

  public int length(){
    return this.rep.length;
  }

  public static void main(String[] args) {
    Array<String> a = new SimpleArray<String> (4, "cs226");
    assert a.length() ==4;
    for (int i =0; i< a.length(); i++){

      assert a.get(i).equals("cs226");
    }

    for (int i=0; i<a.length();i++){
      a.set(i, "Peter");
      assert a.length() ==4;
      assert a.get(i).equals("Peter");
    }

    a.set(2, "Paul");
    assert a.get(2).equals("Paul");
    assert a.get(0).equals("Peter");
    assert a.get(1).equals("Peter");
    assert a.get(3).equals("Peter");

    try{
        Array< String> b = new SimpleArray<String>(0,"Peter");
        assert false;
   } catch (LengthException e){

     } catch (Exception e){

       assert false;
     }

   }
  }

}
