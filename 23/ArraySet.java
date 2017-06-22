import java.util.Iterator;

public class ArraySet<T> implements Set<T> {
    private class Iter implements Iterator<T> {
        private int current;
        public Iter() {
            this.current = 0;
        }

        public T next() {
            T t = ArraySet.this.rep[this.current];
            this.current += 1;
            return t;
        }
        public boolean hasNext() {
            return this.current < ArraySet.this.used;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private T[] rep;
    private int used;

    public ArraySet() {
        this.rep = (T[]) new Object[1];
        this.used = 0;
    }

    private int find(T t) {
        for (int i = 0; i < used; i++) {
            if (this.rep[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public boolean has(T t) {
        return this.find(t) != -1;
    }

    private void grow() {
        T[] big = (T[]) new Object[2*this.rep.length];
        for (int i = 0; i < this.used; i++) {
            big[i] = this.rep[i];
        }
        this.rep = big;
    }

    public void insert(T t) {
        int x = this.find(t);
        if (x == -1) {
            if (this.used == this.rep.length) {
                this.grow();
            }
            this.rep[this.used] = t;
            this.used += 1;
        }
    }

    public void remove(T t) {
        int x = this.find(t);
        if (x > -1) {
            this.rep[x] = this.rep[this.used-1];
            this.used -= 1;
        }
    }

    public Iterator<T> iterator() {
        return new Iter();
    }
}
