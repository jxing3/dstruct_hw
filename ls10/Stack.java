public interface Stack<T>{

    void push(T t);

    void pop() throws EmptyStackException;

    T top() throws EmptyStackException;

    boolean empty();

}
