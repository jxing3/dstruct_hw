/**
    Ordered maps from comparable keys to arbitrary values.

    Maps are also known as "dictionaries" or "associative
    arrays" in other contexts.

    @param <K> Type for keys.
    @param <V> Type for values.
*/
public interface OrderedMap<K extends Comparable<? super K>, V>
    extends Iterable<K> {
    /**
        Insert a new key/value pair.

        @param k The key.
        @param v The value to be associated with k.
        @throws IllegalArgumentException If a mapping for k already exists.
    */
    void insert(K k, V v) throws IllegalArgumentException;

    /**
        Remove an existing key/value pair.

        @param k The key.
        @return The value formerly associated with k.
        @throws IllegalArgumentException If no mapping exists for k.
    */
    V remove(K k) throws IllegalArgumentException;

    /**
        Update the value associated with a key.

        @param k The key.
        @param v The value to be associated with k.
        @throws IllegalArgumentException If no mapping exists for k.
    */
    void put(K k, V v) throws IllegalArgumentException;

    /**
        Get the value associated with a key.

        @param k The key.
        @return The value associated with k.
        @throws IllegalArgumentException If no mapping exists for k.
    */
    V get(K k) throws IllegalArgumentException;

    /**
        Check existence of a key.

        @param k The key.
        @return True if a mapping for k exists, false otherwise.
    */
    boolean has(K k);
}
