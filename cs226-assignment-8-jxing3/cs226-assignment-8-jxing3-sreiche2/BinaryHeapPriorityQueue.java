/*Jesse Xing - jxing3@jhu.edu
Stefan Reichenstein - sreiche2@jhu.edu
*/

import java.util.Comparator;

/**
    BinaryHeap implementation of PriorityQueue using array to mimic
    tree data structure.

    Class takes a comparator object to order elements if user does not
    want default order. Default order simply makes the priority element
    the "largest" element in the queue.

    @param <T> Type of element values.
*/

public class BinaryHeapPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueue<T> {


    private T[] rep;
    private int used;
    private Comparator<T> c;

    private static class DefComp<T extends Comparable<? super T>>
        implements Comparator<T> {

        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }

    }

    /**
       Default constructor for BinaryHeapPriorityQueue. Creates new array
       to store values in binary tree structure. Comparator C is set to the
       default comparator (largest element first) and used is set to 1.
    */

    public BinaryHeapPriorityQueue() {
        this.rep = (T[]) new Comparable<?>[2];
        this.c = new DefComp<T>();
        this.used = 1;
    }

    /**
       Overloaded constructor for BinaryHeapPriorityQueue. Creates new array
       to store values in binary tree structure. Comparator C is set to the
       comparator supplied by user to and used is set to 1.
       @param comp the comparator supplied by the user.
    */
    public BinaryHeapPriorityQueue(Comparator<T> comp) {
        this.rep = (T[]) new Comparable<?>[2];
        this.c = comp;
        this.used = 1;
    }

    private int getParent(int index) {
        if (index % 2 == 0) {
            return (index / 2);
        } else {
            return ((index - 1) / 2);
        }
    }

    private int largerChild(int index) {
        int left = 2 * index;
        int right = 2 * index + 1;

        if (left > this.used - 1) {
            return -1;
        } else if (right > this.used - 1) {
            return left;
        } else if (this.c.compare(this.rep[left], this.rep[right]) > 0) {
            return left;
        } else {
            return right;
        }
    }

    // refactoring helper
    private void ensureNotNull(T t) {
        if (t == null) {
            throw new IllegalArgumentException("null value not allowed");
        }
    }

    private void grow() {
        T[] big = (T[]) new Comparable<?>[2 * this.rep.length];
        for (int i = 0; i < this.used; i++) {
            big[i] = this.rep[i];
        }
        this.rep = big;
    }



    /**
        Insert a value. Duplicate values <b>do</b> end up in the
        queue, so inserting X three times means it has to
        be removed three times before it's gone again.

        @param t Value to insert.
    */

    public void insert(T t) {
        this.ensureNotNull(t);

        if (this.used == this.rep.length) {
            this.grow();
        }

        this.rep[this.used] = t;

        int child = this.used;

        int parent = this.getParent(this.used);

        while (parent > 0 && this.c.compare(t, this.rep[parent]) > 0) {
            T temp = this.rep[parent];
            this.rep[parent] = t;
            this.rep[child] = temp;
            child = parent;
            parent = this.getParent(child);
        }

        this.used++;

    }
    /**
        Remove top value. The top value is the "largest"
        value in the queue as determined by the comparator
        for the queue.

        @throws EmptyQueueException If queue is empty.
    */
    public void remove() throws EmptyQueueException {

        if (this.empty()) {
            throw new EmptyQueueException();
        }
        int lastIndex = this.used - 1;
        int parent = 1;
        this.rep[parent] = this.rep[lastIndex];
        this.used--;
        int child = this.largerChild(parent);

        while (child != -1
            && this.c.compare(this.rep[child], this.rep[parent]) > 0) {

            T temp = this.rep[child];
            this.rep[child] = this.rep[parent];
            this.rep[parent] = temp;
            parent = child;
            child = this.largerChild(parent);
        }

    }
    /**
        Return top value. The top value is the largest
        value in the queue as determined by the comparator
        for the queue.

        @return Top value in the queue.
        @throws EmptyQueueException If queue is empty.
    */
    public T top() throws EmptyQueueException {

        if (this.empty()) {
            throw new EmptyQueueException();
        }
        return this.rep[1];

    }
    /**
        No elements?

        @return True if queue is empty, false otherwise.
    */
    public boolean empty() {

        return this.used == 1;

    }
}
