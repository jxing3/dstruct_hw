import java.util.ArrayList;
import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {
    private ArrayList<T> rep;

    public SimpleSet() { // "O(1)"
        this.rep = new ArrayList<T>();
    }
    public boolean has(T t) { // O(n)
        return this.rep.contains(t); // uses equals, compares values not addresses
    }
    public void insert(T t) { // O(n)
        if (!this.rep.contains(t)) {
            this.rep.add(t);
        }
    }
    public void remove(T t) { // O(n)
        this.rep.remove(t);
    }

    public Iterator<T> iterator() {
        return this.rep.iterator();
    }
}
