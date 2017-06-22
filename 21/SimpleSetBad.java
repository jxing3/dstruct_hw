import java.util.ArrayList;

public class SimpleSet<T> implements Set<T> {
    private ArrayList<T> rep;

    public SimpleSet() { // "O(1)"
        this.rep = new ArrayList<T>();
    }
    public boolean has(T t) { // O(n) 
        return this.rep.contains(t); // uses equals, compares values not addresses
    }
    public void insert(T t) { // O(1) amortized
        this.rep.add(t);
    }
    public void remove(T t) { // O(n^2)
        while (this.rep.contains(t)) {
            this.rep.remove(t);
        }
    }

}
