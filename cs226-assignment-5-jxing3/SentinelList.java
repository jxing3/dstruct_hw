/* Jesse Xing
   jxing3@jhu.edu
*/

/**
    Sentinel list. Uses sentinel nodes pointed to by front and back to make
    list easier to implement. Data structure consists of nodes with data
    fields next, prev, data, and color.

    Positions can be invalid for several reasons: They could
    be null, they could come from a different data structure,
    or they could come from a different, unrelated list.

    @param <T> Type of each element in list.
*/

public class SentinelList<T> implements List<T> {

    private static class Node<T> implements Position<T> {
        Node<T> next;
        Node<T> prev;
        T data;
        List<T> color;

        Node(T t, List<T> c) {
            this.data = t;
            this.color = c;
        }

        public T get() {
            return this.data;
        }
        public void put(T t) {
            this.data = t;
        }
    }


    private static class Iter<T> implements Iterator<T> {
        private Node<T> current;
        private Node<T> sentinel;
        private boolean forward;

        public Iter(Node<T> c, boolean f, Node<T> s) {
            this.current = c;
            this.forward = f;
            this.sentinel = s;
        }
        public T get() throws InvalidIteratorException {
            if (!(this.valid())) {
                throw new InvalidIteratorException();
            }
            return this.current.data;
        }
        public boolean valid() {
            return this.current != this.sentinel;
        }
        public void next() throws InvalidIteratorException {
            if (!(this.valid())) {
                throw new InvalidIteratorException();
            }
            this.current = this.forward ? this.current.next : this.current.prev;
        }
    }

    private Node<T> front;
    private Node<T> back;
    private int length;

    /**
       Constructor for SentinelList. Creates the two sentinel nodes and
       makes them point at each other.
    */

    public SentinelList() {

        this.front = new Node<T>(null, this);
        this.back = new Node<T>(null, this);
        this.front.prev = null;
        this.back.next = null;
        this.front.next = this.back;
        this.back.prev = this.front;

    }

   /*
    Puts new node into list with element of type T before
    or after defined node
   */

    private Node<T> put(Node<T> n, T t, boolean before) {
        Node<T> n1 = new Node<T>(t, this);
        Node<T> p;

        if (before) {
            p = n.prev;
            n1.prev = p;
            n1.next = n;
            n.prev = n1;
            p.next = n1;
        } else {
            p = n.next;
            n1.prev = n;
            n1.next = p;
            n.next = n1;
            p.prev = n1;
        }
        this.length++;
        return n1;
    }

   /*
      Removes node defined from list
   */
    private T remove(Node<T> n) {

        if (this.empty()) {
            throw new EmptyListException();
        }

        Node<T> n1 = n.prev;
        Node<T> n2 = n.next;
        n.color = null;
        n.prev = null;
        n.next = null;
        n1.next = n2;
        n2.prev = n1;
        this.length--;

        return n.data;

    }

    private Node<T> convert(Position<T> p) {
        Node<T> n;
        if (p == null || !(p instanceof Node<?>)) {
            throw new InvalidPositionException();
        }
        n = (Node<T>) p;
        if (n.color != this) {
            throw new InvalidPositionException();
        }
        return n;
    }

    /**
        Number of elements in list.
        @return Number of elements.
    */

    public int length() {
        return this.length;
    }

    /**
        List empty?
        @return True if empty, false otherwise.
    */
    public boolean empty() {
        return this.length == 0;
    }

    /**
        Insert at front of list.

        @param t Element to insert.
        @return Position created to hold element.
    */

    public Position<T> insertFront(T t) {

        return this.put(this.front, t, false);
    }

    /**
        Insert at back of list.

        @param t Element to insert.
        @return Position created to hold element.
    */

    public Position<T> insertBack(T t) {

        return this.put(this.back, t, true);
    }

    /**
        Insert before a position.

        @param p Position to insert before.
        @param t Element to insert.
        @return Position created to hold element.
        @throws InvalidPositionException If p is invalid for this list.
    */

    public Position<T> insertBefore(Position<T> p, T t) throws
    InvalidPositionException {
        Node<T> q = this.convert(p);
        return this.put(q, t, true);
    }

    /**
        Insert after a position.

        @param p Position to insert after.
        @param t Element to insert.
        @return Position created to hold element.
        @throws InvalidPositionException If p is invalid for this list.
    */

    public Position<T> insertAfter(Position<T> p, T t) throws
    InvalidPositionException {
        Node<T> q = this.convert(p);
        return this.put(q, t, false);
    }

    /**
        Remove from front of list.

        @return Element from destroyed position.
        @throws EmptyListException If list is empty.
    */
    public T removeFront() throws EmptyListException {
        return this.remove(this.front.next);
    }

   /**
        Remove from back of list.

        @return Element from destroyed position.
        @throws EmptyListException If list is empty.
    */
    public T removeBack() throws EmptyListException {
        return this.remove(this.back.prev);
    }
   /**
        Remove a position.

        @param p Position to remove.
        @return Element from destroyed position.
        @throws InvalidPositionException If p is invalid for this list.
    */
    public T removeAt(Position<T> p) throws InvalidPositionException {
        Node<T> q = this.convert(p);
        return this.remove(q);
    }


    /**
        First position.

        @return First position.
        @throws EmptyListException If list is empty.
    */

    public Position<T> front() throws EmptyListException {
        if (this.empty()) {
            throw new EmptyListException();
        }
        return this.front.next;
    }

    /**
        Last position.

        @return Last position.
        @throws EmptyListException If list is empty.
    */
    public Position<T> back() throws EmptyListException {
        if (this.empty()) {
            throw new EmptyListException();
        }
        return this.back.prev;
    }

    /**
        Is this the first position?

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid.
        @return True if p is the first position, false otherwise.
    */

    public boolean first(Position<T> p) throws InvalidPositionException {
        Node<T> q = this.convert(p);
        return this.front.next == q;
    }

    /**
        Is this the last position?

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid.
        @return True if p is the last position, false otherwise.
    */
    public boolean last(Position<T> p) throws InvalidPositionException {
        Node<T> q = this.convert(p);
        return this.back.prev == q;
    }

    /**
        Next position.

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid or the last position.
        @return Position after p.
    */

    public Position<T> next(Position<T> p) throws InvalidPositionException {
        Node<T> q = this.convert(p);
        if (q == this.back.prev) {
            throw new InvalidPositionException();
        }
        return q.next;
    }

    /**
        Previous position.

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid or the first position.
        @return Position before p.
    */

    public Position<T> previous(Position<T> p) throws InvalidPositionException {
        Node<T> q = this.convert(p);
        if (q == this.front.next) {
            throw new InvalidPositionException();
        }
        return q.prev;
    }

    /**
        Create a forward iterator.
        @return Iterator at first list element.
    */

    public Iterator<T> forward() {
        return new Iter<T>(this.front.next, true, this.back);
    }

    /**
        Create a backward iterator.
        @return Iterator at last list element.
    */

    public Iterator<T> backward() {
        return new Iter<T>(this.back.prev, false, this.front);
    }

    /**
        Returns string that prints from front of list to back
        in following format: [f, ...., b].
        @return the string as described above
    */

    public String toString() {

        String s = "[";
        Node<T> n = this.front.next;

        if (!this.empty()) {
            while (n != this.back.prev) {
                s += n.get() + ", ";
                n = n.next;
            }
            s += this.back.prev.get();
        }

        s += "]";

        return s;

    }

}
