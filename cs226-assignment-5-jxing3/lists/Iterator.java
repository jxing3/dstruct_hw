/**
    Generic iterator.

    A general way to run over all the elements stored in
    a data structure.

    All data structures could use this interface because
    it only assumes that we have <b>some</b> type of data
    inside the data structure, and that's necessarily the
    case.

    We do not provide a put() operation here because some
    data structures will not support arbitrary updates; to
    remain general, we have to avoid modifying the values
    the data structure holds.

    Also note that iterators are not appropriate for some
    data structures. Stacks and queues, for example, are
    <b>not supposed</b> to allow access to all internal
    elements.

    <b>Note:</b> This is <b>not</b> the way iterators work
    in the Java class library! We'll use our own iterators
    for the list, but for later data structures we'll use
    the Iterable<T>/Iterator<T> stuff from Java (which will
    also allows us to use the for-each loop).

    @param <T> Type of each element in the data structure.
*/
public interface Iterator<T> {
    /**
         Is this iterator valid?

         @return True if valid, false otherwise.
    */
    boolean valid();
    /**
        Advance iterator to next element.

        @requires valid()
        @throws InvalidIteratorException If not valid.
    */
    void next() throws InvalidIteratorException;
    /**
        Return the iterator's current element.

        @requires valid()
        @throws InvalidIteratorException If not valid.
        @return Current element.
    */
    T get() throws InvalidIteratorException;
}
