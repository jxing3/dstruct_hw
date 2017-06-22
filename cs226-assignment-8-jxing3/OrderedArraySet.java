/*
Stefan Reichenstein - sreiche2@jhu.edu
Jesse Xing - jxing3@jhu.edu
*/

import java.util.Iterator;

/**
 * Implementation of an ordered Array. Utilizes Binary Search.
 * @param <T> is the generic T that allows us to implement/use comperable
 * @author Stefan Reichenstein - sreiche2@jhu.edu
 */
public class OrderedArraySet<T extends Comparable<? super T>>
    implements OrderedSet<T> {
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

    /**
     * Default concstructor for an OrderedArraySet.
     */
    public OrderedArraySet() {
        this.rep = (T[]) new Comparable<?>[1];
        this.used = 0;
    }

    private int binSearch(int start, int end, T t) {
        int mid = (start + end) / 2;
        int comp;

        //if ending index <= starting index, return the value
        //of mid, where the next value should go.
        //the value of where the value SHOULD go if not found
        if (end <= start) { //does this need to be '<=' ? i dont think so...
            return mid;
        }

        comp = t.compareTo(this.rep[mid]);
        //if comp == then value is found
        //if comp > 0 then it is larger than the middle value
        //  so search in the lower half
        //if comp < 0 then it is less than the middle value
        //  so search in the upper half

        if (comp == 0) { //t is found, return position
            return mid;
        } else if (comp < 0) { //t is in the lower half (left)
            return this.binSearch(start, mid - 1, t);
        } else if (comp > 0) { //t is in the upper half (right)
            return this.binSearch(mid + 1, end, t);
        }

        //should be...?
        //FOR INVALID?
        //CHECK THIS
        return this.used;
    }

    private int find(T t) {
        return this.binSearch(0, this.used - 1, t);
    }

    private boolean found(int i, T t) {
        return 0 <= i && i < this.used && this.rep[i].equals(t);
    }

    private void grow() {
        T[] big = (T[]) new Comparable<?>[2 * this.rep.length];
        for (int i = 0; i < this.used; i++) {
            big[i] = this.rep[i];
        }
        this.rep = big;
    }

    /**
     * Insert a value. Set doesn't change if we insert
     * a value we already have.
     *
     * @param t Value to insert.
     */
    public void insert(T t) {
        int x = this.find(t);
        if (this.found(x, t)) {
            return;
        }
        if (this.used == this.rep.length) {
            this.grow();
        }

        /*#
         * this is neccessary to deal with the first value.
         * when there is only one element in the array,
         * my binary search method will return the current
         * middle position, and the middle of 0 and 0 is 0,
         * so it will insert the new value at position 0,
         * regardless of whether it is larger than the current element at 0
         */
        if ((this.used > 0) && (t.compareTo(this.rep[x]) > 0)) {
            ++x;
        }

        //moves evertyhing from the previous index to the next index
        //starting from the valid found one. Then inserts the new value
        //at the foudn index
        for (int i = this.used; i > x;  i--) {
            this.rep[i] = this.rep[i - 1];
        }
        this.rep[x] = t;
        this.used += 1;
    }

    /**
     * Remove a value. Set doesn't change if we remove
     * a value we don't have.
     *
     * @param t Value to remove.
     */
    public void remove(T t) {
        int x = this.find(t);
        if (!this.found(x, t)) {
            return;
        }
        //uses the valid found index and in the position (x)
        //overwrites (x+1)
        for (int i = x; i < this.used - 1; i++) {
            this.rep[i] = this.rep[i + 1];
        }
        this.used -= 1;
    }

    /**
     * Test membership of a value.
     *
     * @param t Value to test.
     * @return True if t is in the set, false otherwise.
     */
    public boolean has(T t) {
        int i = this.find(t);
        return this.found(i, t);
    }

    /**
     * ITERATOR!
     * @return Iterator<T> is an iterator of the data
     */
    public Iterator<T> iterator() {
        return new Iter();
    }
}
