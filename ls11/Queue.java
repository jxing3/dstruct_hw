public interface Queue<T> {

    boolean empty();
    void enqueue(T t);
    void dequeue() throws EmptyQueueException;
    T front() throws EmptyQueueException;
}
