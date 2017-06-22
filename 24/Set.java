public interface Set<T> extends Iterable<T> {
    boolean has(T t);
    void insert(T t);
    void remove(T t);
}
