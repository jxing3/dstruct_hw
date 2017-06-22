public class NodeList<T> implements List<T> {
    private static class Node<T> implements Position<T> {
        Node<T> next;
        Node<T> prev;
        T data;
        List<T> color;

        Node(T t, List<T> color) {
            this.data = t;
            this.color = color;
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
        private boolean forward;

        public Iter(Node<T> c, boolean f) {
            this.current = c;
            this.forward = f;
        }
        public T get() {
            return this.current.data;
        }
        public boolean valid() {
            return this.current != null;
        }
        public void next() {
            this.current = this.forward ? this.current.next : this.current.prev;
        }
    }

    private Node<T> first;
    private Node<T> last;
    int length;

    public boolean empty() {
        return this.first == null;
    }
    public int length() {
        return this.length;
    }

    public Position<T> insertFront(T t) {
        Node<T> n = new Node<T>(t, this);
        n.next = this.first;
        if (this.first != null) {
            this.first.prev = n;
        }
        if (this.last == null) {
            this.last = n;
        }
        this.first = n;
        this.length += 1;
        return n;
    }

    public Position<T> insertBack(T t) {
        return null;
    }
    public Position<T> insertBefore(Position<T> pos, T t) throws InvalidPositionException {
        Node<T> q = this.convert(pos);
        Node<T> p = q.prev;
        Node<T> n = new Node<T>(t, this);

        n.next = q;
        n.prev = p;

        q.prev = n;
        if (p != null) {
            p.next = n;
        } else {
            this.first = n;
        }

        this.length += 1;
        return n;
    }
    public Position<T> insertAfter(Position<T> p, T t) throws InvalidPositionException {
        return null;
    }

    public T removeFront() throws EmptyListException {
        return null;
    }
    public T removeBack() throws EmptyListException {
        return null;
    }
    public T removeAt(Position<T> p) throws InvalidPositionException {
        return null;
    }

    public Position<T> front() throws EmptyListException {
        if (this.empty()) {
            throw new EmptyListException();
        }
        return this.first;
    }
    public Position<T> back() throws EmptyListException {
        if (this.empty()) {
            throw new EmptyListException();
        }
        return this.last;
    }

    public boolean first(Position<T> p) throws InvalidPositionException {
        return this.first == p;
    }
    public boolean last(Position<T> p) throws InvalidPositionException {
        return this.last == p;
    }

    public Position<T> next(Position<T> p) throws InvalidPositionException {
        return this.convert(p).next;
    }
    public Position<T> previous(Position<T> p) throws InvalidPositionException {
        return this.convert(p).prev;
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

    public T get(Position<T> p) throws InvalidPositionException {
        Node<T> n = this.convert(p);
        return n.data;
    }
    public void put(Position<T> p, T t) throws InvalidPositionException {
        Node<T> n = this.convert(p);
        n.data = t;
    }


    public Iterator<T> forward() {
        return new Iter<T>(this.first, true);
    }
    public Iterator<T> backward() {
        return new Iter<T>(this.last, false);
    }

}
