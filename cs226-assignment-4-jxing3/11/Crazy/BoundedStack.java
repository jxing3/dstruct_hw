public interface BoundedStack<T> extends Stack<T> {
    void push(T t) throws FullStackException;
    boolean full();
}
