import java.util.Iterator;

public class OrderedArraySet<T extends Comparable<? super T>> implements OrderedSet<T> {
    private class Iter implements Iterator<T> {
        private int current;
        public Iter() {
            this.current = 0;
        }

        public T next() {
            T t = OrderedArraySet.this.rep[this.current];
            this.current += 1;
            return t;
        }
        public boolean hasNext() {
            return this.current < OrderedArraySet.this.used;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private T[] rep;
    private int used;

    public OrderedArraySet() {
        this.rep = (T[]) new Comparable<?>[1];
        this.used = 0;
    }

    private int find(T t) {
        for (int i = 0; i < used; i++) {
            if (this.rep[i].compareTo(t) >= 0) { // rep[i] >= t
                return i;
            }
        }
        return this.used;
    }

    private boolean found(int i, T t) {
        return 0 <= i && i < used && this.rep[i].equals(t);
    }

    public boolean has(T t) {
        int i = this.find(t);
        return this.found(i, t);
    }

    private void grow() {
        T[] big = (T[]) new Comparable<?>[2*this.rep.length];
        for (int i = 0; i < this.used; i++) {
            big[i] = this.rep[i];
        }
        this.rep = big;
    }

    public void insert(T t) {
        int x = this.find(t);
        if (this.found(x, t)) {
            return;
        }
        if (this.used == this.rep.length) {
            this.grow();
        }
        for (int i = this.used; i > x;  i--) {
            this.rep[i] = this.rep[i-1];
        }
        this.rep[x] = t;
        this.used += 1;
    }

    public void remove(T t) {
        int x = this.find(t);
        if (!this.found(x, t)) {
            return;
        }
        for (int i = x; i < this.used-1; i++) {
            this.rep[i] = this.rep[i+1];
        }
        this.used -= 1;
    }

    public Iterator<T> iterator() {
        return new Iter();
    }
}
