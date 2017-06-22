import java.util.ArrayList;
import java.util.Comparator;

/** Priority Queue using Binary Heap implementation.
    @param <T> type of input
*/
public class BinaryHeapPriorityQueue<T extends Comparable<T>>
    implements PriorityQueue<T> {

    private ArrayList<Comparable> data;
    private Comparator c;

    /** Constructor.
    */
    public BinaryHeapPriorityQueue() {
        this.data = new ArrayList<Comparable>();
        this.data.add(null); // to make sure we start at 1
    }

    /** Constructor.
        @param c Comparator, decides how to sort
    */
    public BinaryHeapPriorityQueue(Comparator c) {
        this.data = new ArrayList<Comparable>();
        this.c = c;
        this.data.add(null); // to make sure we start at 1
    }

    private int getParent(int index) {
        if (index % 2 == 0) {
            return (index / 2);
        } else {
            return ((index - 1) / 2);
        }
    }
    
    private int compareItems(T o1, T o2){
    	if (this.c == null) {
    		return ((Comparable<T>) o1).compareTo((T) o2);
    	} else {
    		return c.compare(o1, o2);
    	}
    }
    
    @Override
    public void insert(T t) {
    	this.data.add(t);
    	int index = this.data.size() - 1;
        
    	int parentIndex = this.getParent(index);
        while (parentIndex > 0 && this.compareItems((T) this.data.get(parentIndex), t) < 0) {
        	T temp = (T) this.data.get(parentIndex);
        	this.data.set(parentIndex, t);
        	this.data.set(index, temp);
        	index = parentIndex;
        	parentIndex = this.getParent(index);
        }
    }

    private int getMaxChild(int index) {
    	int left = 2 * index;
    	int right = 2 * index + 1;
    	
        if (left >= this.data.size()) {
    		return -1;
    	}
    	if (right >= this.data.size()) {
    		return left;
    	}
    	if (this.compareItems( (T) this.data.get(left), (T) this.data.get(right)) < 0) {
    		return right;
    	} else {
    		return left;
    	}
    }
    
    @Override
    public void remove() throws EmptyQueueException {
        if (this.empty()) {
            throw new EmptyQueueException();
        }
        int lastIndex = this.data.size() - 1;
        int index = 1;
        this.data.set(index, this.data.get(lastIndex));
        this.data.remove(lastIndex);
        
        int maxChild = this.getMaxChild(index);
        while (maxChild > 0 && this.compareItems((T) this.data.get(index), (T) this.data.get(maxChild)) < 0) {
        	T temp = (T) this.data.get(maxChild);
        	this.data.set(maxChild, this.data.get(index));
        	this.data.set(index, temp);
        	index = maxChild;
        	maxChild = this.getMaxChild(index);
        }
        
    }

    @Override
    public T top() throws EmptyQueueException {
        if (this.empty()) {
            throw new EmptyQueueException();
        }
        return (T) this.data.get(1);
    }

    @Override
    public boolean empty() {
        return this.data.size() == 1;
    }

}
