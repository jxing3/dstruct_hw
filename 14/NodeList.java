public class NodeList<T> implements List<T> {
    private static class Node<T> implements Position<T> {
        Node<T> next;
        Node<T> prev;
        T data;

        Node(T t) {
            this.data = t;
        }
    }

    private Node<T> first;
    private Node<T> last;

    public boolean empty() {
        return this.first == null;
    }

    public Position<T> insertFront(T t) {
        Node<T> n = new Node<T>(t);
        n.next = this.first;
        if (this.first != null) {
            this.first.prev = n;
        }
        if (this.last == null) {
            this.last = n;
        }
        this.first = n;
        return n;
    }

    public Position<T> insertBack(T t) {
        return null;
    }
    public Position<T> insertBefore(Position<T> pos, T t) throws InvalidPositionException {
        Node<T> q = this.convert(pos);
        Node<T> p = q.prev;
        Node<T> n = new Node <T>(t);

        n.next = q;
        n.prev = p;

        q.prev = n;

        if (p!=null) {

            p.next = n;

        } else {
            this.first = n;
        }

    }
    public Position<T> insertAfter(Position<T> p, T t) throws InvalidPositionException {
        return null;
    }

    public void removeFront() throws EmptyListException {
    }
    public void removeBack() throws EmptyListException {
    }
    public void remove(Position<T> p) throws InvalidPositionException {
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

    public Position<T> next(Position<T> p) throws InvalidPositionException {
        return this.convert(p).next;
    }
    public Position<T> previous(Position<T> p) throws InvalidPositionException {
        return this.convert(p).prev;
    }

    private Node<T> convert(Position<T> p) {
        if (p == null || !(p instanceof Node<?>)) {
            throw new InvalidPositionException();
        }
        return (Node<T>) p;
    }

    public T get(Position<T> p) throws InvalidPositionException {
        Node<T> n = this.convert(p);
        return n.data;
    }
    public void put(Position<T> p, T t) throws InvalidPositionException {
        Node<T> n = this.convert(p);
        n.data = t;
    }
}
