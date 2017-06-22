public interface Graph<V,E> {

    Position<V> insertVertex(V v);
    Position<E> insertEdge(Position<V> from, Position<V> to, E e) throws InvalidPositionException, InsertionException;

    removeVertex throws InvalidPositionException, RemovalException;
    E removeEdge(Position<E> p) throws InvalidPositionException;

    Iterable<Position<V>> vertices();
    Iterable<Position<E>> edges();
}
