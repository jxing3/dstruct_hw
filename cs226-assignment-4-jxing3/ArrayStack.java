/**
    Unbounded generic stack created from an array representation.
    @param <T> element type
*/

public class ArrayStack<T> implements Stack<T> {
    private T[] rep;
    private int used;

/**
    Initializes an array to represent the stack.

*/
    public ArrayStack() {
        this.rep = (T[]) new Object[1];
    }

    private void grow() {
        T[] bigger = (T[]) new Object[this.rep.length * 2];
        for (int i = 0; i < this.rep.length; i++) {
            bigger[i] = this.rep[i];
        }
        this.rep = bigger;
    }

/**
   Pushes t into top of Stack.
   @param t the value to push
*/
    public void push(T t) {
        if (this.used == this.rep.length) {
            this.grow();
        }
        this.rep[this.used] = t;
        this.used += 1;
    }
/**
   Pops element out of top of Stack (gets rid of it).
   @throws EmptyStackException if stack is empty
*/

    public void pop() throws EmptyStackException {
        if (this.empty()) {
            throw new EmptyStackException();
        }
        this.used -= 1;
    }

/**
   Returns value at top of stack.
   @throws EmptyStackException if stack is empty
   @return the value at the top of the stack
*/

    public T top() throws EmptyStackException {
        if (this.empty()) {
            throw new EmptyStackException();
        }
        return this.rep[this.used - 1];
    }

/**
   Returns whether or not the stack is empty.
   @return true if the stack is empty, false otherwise
*/

    public boolean empty() {
        return this.used == 0;
    }

/**
   Returns a string printing the stack from top to bottom
   in the format [top, ...,bottom].
   @return the string described above
*/

    public String toString() {
        String s = "[";

        for (int i = this.used - 1; i > 0; i--) {
            s += this.rep[i] + " ";
        }

        if (!this.empty()) {
            s += this.rep[0] + "]";
        }

        return s;

    }
}
