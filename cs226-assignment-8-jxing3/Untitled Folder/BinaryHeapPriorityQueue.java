import java.util.Comparator;

public class BinaryHeapPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueue<T> {


    private T[] rep;
    private int used;
    private Comparator<T> c;

    private int getParent(int index) {
        if (index % 2 == 0) {
            return (index / 2);
        } else {
            return ((index - 1) / 2);
        }
    }
    
    private int comp(T o1, T o2){
    	if (this.c == null) {
    		return o1.compareTo(o2);
    	} else {
    		return c.compare(o1, o2);
    	}
    }

    private int largerChild(int index) {
    	int left = 2 * index;
    	int right = 2 * index + 1;

        if (left>used-1) {
            return -1;
        }
        else if (right>used-1) {
            return left;
        }
    	else if (this.comp(rep[left], rep[right]) > 0) {
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
        T[] big = (T[]) new Comparable<?>[2*this.rep.length];
        for (int i = 0; i < this.used; i++) {
            big[i] = this.rep[i];
        }
        this.rep = big;
    }

    public BinaryHeapPriorityQueue() {
        this.rep = (T[]) new Comparable<?>[10];
        used = 1;
    }
    public BinaryHeapPriorityQueue(Comparator<T> c) {
        this.rep = (T[]) new Comparable<?>[10];
        this.c = c;
        used = 1;
    }

    /**
        Insert a value. Duplicate values <b>do</b> end up in the
        queue, so inserting X three times means it has to
        be removed three times before it's gone again.

        @param t Value to insert.
    */

    public void insert(T t) {
        this.ensureNotNull(t);

        if(used == this.rep.length) {
            this.grow();
        }

    	this.rep[used] = t;
        
        int child= used;

    	int parent = this.getParent(used);

        while (parent > 0 && this.comp(t, this.rep[parent]) > 0) {
        	T temp = this.rep[parent];
        	this.rep[parent] = t;
        	this.rep[child] = temp;
        	child = parent;
        	parent = this.getParent(child);
        }

        used++;

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
        int lastIndex = used-1;
        int parent = 1;
        this.rep[parent] = this.rep[lastIndex];
        used--;
        int child = this.largerChild(parent);

        while (child!=-1 && this.comp(rep[child], rep[parent]) > 0) {
        	T temp = rep[child];
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

        return used == 1;

    }
}
