public interface Queue<T> {
    void enqueue(T t);
    void dequeue() throws EmptyQueueException;
    T front() throws EmptyQueueException;
    boolean empty();
}
