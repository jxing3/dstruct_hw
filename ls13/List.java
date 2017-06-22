public interface List<T> {

    boolean empty();
    Position<T> insertFront(T t);
    Position<T> insertBack(T t);
    Position<T> insertBefore(Position<T> p, T t) throws InvalidPositionExceptio$
    Position<T> insertAfter(Position<T> p, T t) throws InvalidPositionException;


    void removeFront() throws EmptyListException;
    void removeBack() throws EmptyListException;
    void remove(Position<T> p) throws InvalidPositionException;

    Position<T> front() throws EmptyListException;
    Position<T> back() throws EmptyListException;

    //if there's no neightbor, neturn null???
    Position<T> next(Position<T> p) throws InvalidPositionException;
    position<T> previous(Position<T> p) throws InvalidPositionException;

}
