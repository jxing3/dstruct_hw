/**
    Generic list.

    A general list interface for unbounded lists.

    Positions can be invalid for several reasons: They could
    be null, they could come from a different data structure,
    or they could come from a different, unrelated list.

    @param <T> Type of each element in list.
*/
public interface List<T> {
    /**
        Number of elements in list.
        @return Number of elements.
    */
    int length();
    /**
        List empty?
        @return True if empty, false otherwise.
    */
    boolean empty();

    /**
        Insert at front of list.

        @param t Element to insert.
        @return Position created to hold element.
    */
    Position<T> insertFront(T t);
    /**
        Insert at back of list.

        @param t Element to insert.
        @return Position created to hold element.
    */
    Position<T> insertBack(T t);
    /**
        Insert before a position.

        @param p Position to insert before.
        @param t Element to insert.
        @return Position created to hold element.
        @throws InvalidPositionException If p is invalid for this list.
    */
    Position<T> insertBefore(Position<T> p, T t)
        throws InvalidPositionException;
    /**
        Insert after a position.

        @param p Position to insert after.
        @param t Element to insert.
        @return Position created to hold element.
        @throws InvalidPositionException If p is invalid for this list.
    */
    Position<T> insertAfter(Position<T> p, T t)
        throws InvalidPositionException;

    /**
        Remove a position.

        @param p Position to remove.
        @return Element from destroyed position.
        @throws InvalidPositionException If p is invalid for this list.
    */
    T removeAt(Position<T> p) throws InvalidPositionException;
    /**
        Remove from front of list.

        @return Element from destroyed position.
        @throws EmptyListException If list is empty.
    */
    T removeFront() throws EmptyListException;
    /**
        Remove from back of list.

        @return Element from destroyed position.
        @throws EmptyListException If list is empty.
    */
    T removeBack() throws EmptyListException;

    /**
        First position.

        @return First position.
        @throws EmptyListException If list is empty.
    */
    Position<T> front() throws EmptyListException;
    /**
        Last position.

        @return Last position.
        @throws EmptyListException If list is empty.
    */
    Position<T> back() throws EmptyListException;
    /**
        Is this the first position?

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid.
        @return True if p is the first position, false otherwise.
    */
    boolean first(Position<T> p) throws InvalidPositionException;
    /**
        Is this the last position?

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid.
        @return True if p is the last position, false otherwise.
    */
    boolean last(Position<T> p) throws InvalidPositionException;
    /**
        Next position.

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid or the last position.
        @return Position after p.
    */
    Position<T> next(Position<T> p) throws InvalidPositionException;
    /**
        Previous position.

        @param p Position to examine.
        @throws InvalidPositionException If p is invalid or the first position.
        @return Position before p.
    */
    Position<T> previous(Position<T> p) throws InvalidPositionException;

    /**
        Create a forward iterator.
        @return Iterator at first list element.
    */
    Iterator<T> forward();
    /**
        Create a backward iterator.
        @return Iterator at last list element.
    */
    Iterator<T> backward();
}
