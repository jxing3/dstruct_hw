import java.util.ArrayList;
import java.util.Iterator;

/** Ordered Array Set Class.
    @param <T> type
*/
public class OrderedArraySet<T extends Comparable<T>> implements OrderedSet<T> {
    private final int initialSize = 1;
    private Comparable<T>[] data;
    private int used;

    /** Constructor.
    */
    public OrderedArraySet() {
        this.data = (T[]) new Comparable[this.initialSize];
        this.used = 0;
    }

    private int binarySearch(int start, int end, T value) {
        int mid = (end + start) / 2;
        if (end <= start) {
            return mid;
        }

        if (this.data[mid].compareTo(value) == 0) { // if found
            return mid;
        } else if (this.data[mid].compareTo(value) > 0) { // search left
            return this.binarySearch(start, mid - 1, value);
        } else if (this.data[mid].compareTo(value) <= 0) { // search right
            return this.binarySearch(mid + 1, end, value);
        }

        return (this.used);
    }

    // find position in data that t *should* be in
    private int find(T t) {
        return this.binarySearch(0, this.used - 1, t);
    }

    private boolean found(int i, T t) {
        return ((0 <= i && i < this.used)
                && (this.data[i].compareTo(t) == 0));
    }

    private void resize() {
        Comparable<T>[] temp = (T[]) new Comparable[this.data.length * 2];
        for (int i = 0; i < this.data.length; i++) {
            temp[i] = this.data[i];
        }
        this.data = temp;
    }

    @Override
    public void insert(T t) {
        if (t == null) {
            throw new IllegalArgumentException("null value not allowed");
        }
        if (this.used == this.data.length) {
            this.resize();
        }

        int index = this.find(t);
        boolean changed;
        if (!this.found(index, t)) {
            if (this.data[index] == null) {
                this.data[index] = t;
            } else {
                if (t.compareTo((T) this.data[index]) > 0) {
                    index++;
                }
                for (int i = this.used; i > index; i--) {
                    this.data[i] = this.data[i - 1];
                }
                this.data[index] = t;
            }
            this.used++;
        }
    }

    @Override
    public void remove(T t) {
        if (t == null) {
            throw new IllegalArgumentException("null value not allowed");
        }

        int index = this.find(t);
        if (this.found(index, t)) {
            this.data[index] = null;
            for (int i = index; i < this.used - 1; i++) {
                Comparable<T> temp;
                temp = this.data[i];
                this.data[i] = this.data[i + 1];
                this.data[i + 1] = temp;
            }
            this.used--;
        }
    }

    @Override
    public boolean has(T t) {
        if (t == null) {
            throw new IllegalArgumentException("null value not allowed");
        }
        int index = this.find(t);
        return this.found(index, t);
    }

    @Override
    public Iterator<T> iterator() {
        ArrayList<T> temp = new ArrayList<T>();
        for (int i = 0; i < this.used; i++) {
            temp.add((T) this.data[i]);
        }
        return temp.iterator();
    }

}
