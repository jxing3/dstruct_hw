import java.util.ArrayList;
import java.util.Iterator;

public class ArraySet<T> implements Set<T> {
    private T[] rep;
    int count;

    private class Iter implements Iterator<T> {
        private int current;

        public Iter() {
            current = 0;
        }
        public boolean hasNext() {
            return current < count;
        }

        public T next() {
            return rep[current++];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private void grow(){
        T[] temp = (T[]) new Object[rep.length*2];
        for (int c=0; c <= count; c++){
            temp[c] = rep[c];
        }
            rep= temp;
    }

    public ArraySet() {
        rep = (T[]) new Object[10];
    }
    public boolean has(T t) {
        for(int i = 0; i<count; i++) {
           if(rep[i] == t) {
               return true;
           }
        }
        return false;
    }
    public void insert(T t) {
       if(count == this.rep.length-1) {
          grow();
       }
       rep[count] = t;
       count++;

    }
    public void remove(T t) {
        for(int i = 0; i<count; i++) {
            if(rep[i] == t) {
                rep[i] = rep[count-1];
                count--;
            }
        }

    }

    public Iterator<T> iterator() {
        return new Iter();
    }
}
