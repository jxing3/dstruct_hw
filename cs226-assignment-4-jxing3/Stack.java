/**
    Unbounded generic stack.
    @param <T> element type
*/

public interface Stack<T> {

/**
   Pushes t into top of Stack.
   @param t the value to push
*/
    void push(T t);

/**
   Pops element out of top of Stack.
   @throws EmptyStackException if stack is empty
*/
    void pop() throws EmptyStackException;
/**
   Returns value at top of stack.
   @throws EmptyStackException if stack is empty
   @return the value at the top of the stack
*/
    T top() throws EmptyStackException;
/**
   Returns if the stack is empty or not.
   @return true if the stack is empty, false otherwise
*/
    boolean empty();
}
