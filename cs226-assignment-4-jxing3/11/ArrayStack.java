public class ArrayStack<T> implements Stack<T> {
    private T[] rep;
    private int used;

    public ArrayStack() {
        this.rep = (T[]) new Object[1];
    }

    private void grow() {
        T[] bigger = (T[]) new Object[this.rep.length*2];
        for (int i = 0; i < this.rep.length; i++) {
            bigger[i] = this.rep[i];
        }
        this.rep = bigger;
    }

    public void push(T t) {
        if (this.used == this.rep.length) {
            this.grow();
        }
        this.rep[used] = t;
        this.used += 1;
    }

    public void pop() throws EmptyStackException {
        if (this.empty()) {
            throw new EmptyStackException();
        }
        this.used -= 1;
    }

    public T top() throws EmptyStackException {
        if (this.empty()) {
            throw new EmptyStackException();
        }
        return this.rep[this.used-1];
    }

    public boolean empty() {
        return this.used == 0;
    }
}
