/**Creates SparseArray objects. Sparse arrays save memory in situations when most of the positions
in the array object have values that aren't changed from the default value set when the array was created. Points of memory storage are only initialized when a value at a position has been changed
through the set operation.
*/

public class SparseArray<T> implements Array<T> {
    private static class Node<T> { // nested class
        Node<T> next;
        T data;
        int pos;
    }

    private Node<T> rep;
    private int length;
    private T defval;

/**Initialize a SimpleArray object
   @param length the length of the array
   @param t the default value that all elements in the array are initialized to
*/
    public SparseArray(int length, T t) throws LengthException{
        if (length <= 0) {
            throw new LengthException();
        }
        this.length = length;

        defval = t;
    }

    private Node<T> find(int index) throws IndexException {
        if (index < 0 || index >= this.length) {
            throw new IndexException();
        }

        Node<T> n = rep;

        while (n!=null) {
            if(n.pos==index){
               return n;
            }
            n=n.next;
        }
        return null;
    }
  
    private void prepend(Node<T> n) {
        n.next = this.rep;
        this.rep = n;
    }


    public void set(int index, T t) throws IndexException {
        if (this.find(index)==null){
            Node<T> n1= new Node<T>();
            n1.pos = index;
            prepend(n1);
        }
        this.find(index).data = t;
    }

    public T get(int index) throws IndexException {
        if(this.find(index)==null){
           return defval;
        }
        else{

          return this.find(index).data;
        }
    }

    public int length() {
        return this.length;
    }

}
