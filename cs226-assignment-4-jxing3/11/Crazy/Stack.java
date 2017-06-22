public interface Stack<T> {
    void pop() throws EmptyStackException;
    T top() throws EmptyStackException;
    boolean empty();
}
