public interface Array<T>{
  //would like new, but it's Java so constructor so sadface
  void set(int i, T t) throws IndexException;
  T get(int index) throws IndexException;
  int length();

}
