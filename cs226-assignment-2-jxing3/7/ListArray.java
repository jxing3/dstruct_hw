public class ListArray<T> implements Array<T> {
    private static class Node<T> { // nested class
        Node<T> next;
        T data;
    }

    private Node<T> rep;
    private int length;

    private void prepend(Node<T> n) {
        n.next = this.rep;
        this.rep = n;
    }

    public ListArray(int length, T t) throws LengthException{
        if (length <= 0) {
            throw new LengthException();
        }
        this.length = length;

        for (int i = 0; i < length; i++) {
            Node<T> n = new Node<T>();
            n.data = t;
            this.prepend(n);
        }
    }

    private Node<T> find(int index) throws IndexException {
        if (index < 0 || index >= this.length) {
            throw new IndexException();
        }

        Node<T> n = rep;
        while (index > 0) {
            n = n.next;
            index -= 1;
        }
        return n;
    }

    public void set(int index, T t) throws IndexException {
        this.find(index).data = t;
    }

    public T get(int index) throws IndexException {
        return this.find(index).data;
    }

    public int length() {
        return this.length;
    }
}
