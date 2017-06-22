import java.util.Random;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Stefan Reichenstein - sreiche2@jhu.edu
 * Jesse Xing - jxing3@jhu.edu
 * Implementation of a Treap Map.
 * min treap so lower values have higher priorities (closer to root).
 * Ordered maps from comparable keys to arbitrary values.
 *
 * Maps are also known as "dictionaries" or "associative
 * arrays" in other contexts.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class TreapMap<K extends Comparable<? super K>, V>
    implements OrderedMap<K, V> {

    /**
     * Inner Node Class.
     */
    private class Node {
        Node left, right;
        K key;
        V value;
        Integer priority;

        /**
         * Inner Node Class Constructor.
         * @param k is the key
         * @param v is the value
         */
        public Node(K k, V v, int p) {
            this.priority = p;
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
        }

        /**
         * toString.
         * @return String is the string of the key
         */
        public String toString() {
            return ("Node<key: " + this.key
                + "; value: " + this.value
                + ">");
        }
    }

    private Node root;
    private int size;
    private Random random;
    private StringBuilder stringBuilder;

    /**
     * Default Constructor.
     */
    public TreapMap() {
        this.random = new Random(System.currentTimeMillis());
        this.random.nextInt(); //first get was generating negative numers
        this.root = null;
        this.size = 0;
    }

    /**
     * iterator.
     * from BinarySearchTreeMap.
     * @return Iterator<K> is the iterator of keys
     */
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<K>();
        this.iteratorHelper(this.root, keys);
        return keys.iterator();
    }

    /**
     * from BinarySearchTreeMap.
     * recursive.
     */
    private void iteratorHelper(Node n, List<K> keys) {
        if (n == null) { return; }
        this.iteratorHelper(n.left, keys);
        keys.add(n.key);
        this.iteratorHelper(n.right, keys);
    }

    /**
     * From provided code (BinarySearchTree).
     * uses iterative method.
     * THROWS IF KEY IS NULL
     */
    private Node find(K k) {
        if (k == null) {
            throw new IllegalArgumentException("cannot handle null key");
        }
        Node n = this.root;
        while (n != null) {
            int cmp = k.compareTo(n.key);
            if (cmp < 0) {
                n = n.left;
            } else if (cmp > 0) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    /**
     * From provided code (BinarySearchTree).
     * THROWS IF NODE IS NULL (NOT FOUND).
     */
    private Node getNode(K k) {
        Node n = this.find(k);
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }
        return n;
    }

    /**
     * Insert a new key/value pair.
     *
     * @param k The key.
     * @param v The value to be associated with k.
     * @throws IllegalArgumentException If k==null or
     *     if a mapping for k already exists.
     */
    public void insert(K k, V v) throws IllegalArgumentException {
        if (k == null) { //this throws for null k
            throw new IllegalArgumentException("error: null key");
        }

        //this throws if k exists
        this.root = this.insert(this.root, k, v, this.random.nextInt());
        this.root = this.checkPriority(this.root);
        ++this.size;
    }

    private Node insert(Node n, K k, V v, int p) {
        if (n == null) {
            return new Node(k, v, p);
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.checkPriority(this.insert(n.left, k, v, p));
        } else if (cmp > 0) {
            n.right = this.checkPriority(this.insert(n.right, k, v, p));
        } else {
            //thrown if k exists
            throw new IllegalArgumentException("duplicate key " + k);
        }

        return n;
    }

    private Node checkPriority(Node n) {
        if (n == null) {
            return n;
        }

        //left child is right rot
        if ((n.left != null) && (n.left.priority <= n.priority)) {
            n = this.rotateRight(n);
        }
        //right child is left rot
        if ((n.right != null) && (n.right.priority <= n.priority)) {
            n = this.rotateLeft(n);
        }

        return n;
    }

    private Node rotateLeft(Node n) {
        Node hold = n.right;
        if (hold == null) {
            return n;
        }

        n.right = hold.left;
        hold.left = n;
        return hold;
    }

    private Node rotateRight(Node n) {
        Node hold = n.left;
        if (hold == null) {
            return n;
        }

        n.left = hold.right;
        hold.right = n;
        return hold;
    }

    /**
     * Remove an existing key/value pair.
     *
     * @param k The key.
     * @throws IllegalArgumentException If k==null or
     *      if no mapping exists for k.
     */
    public void remove(K k) throws IllegalArgumentException {
        if (k == null) { //throws if k is null, or if node not found
            throw new IllegalArgumentException(k
                + " does not exist or associated node is null");
        }
        this.root = this.remove(this.root, k);
        --this.size;
    }

    private Node remove(Node n, K k) {
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.remove(n.left, k);
        } else if (cmp > 0) {
            n.right = this.remove(n.right, k);
        } else {
            if (n.left == null && n.right == null) {
                return null;
            } else if (n.left == null) {
                n = this.rotateLeft(n);
            } else if (n.right == null) {
                n = this.rotateRight(n);
            } else if (n.right.priority < n.left.priority) {
                n = this.rotateLeft(n);
            } else {
                n = this.rotateRight(n);
            }

            n = this.remove(n, k);
        }

        return n;
    }

    /**
     * Update the value associated with a key.
     *
     * @param k The key.
     * @param v The value to be associated with k.
     * @throws IllegalArgumentException If k==null or
     *     if no mapping exists for k.
     */
    public void put(K k, V v) throws IllegalArgumentException {
        /*#
         * was throwing checkstyle errors unless this.getNode(k)
         * was put in parentheses (as is)
         */
        (this.getNode(k)).value = v;
    }

    /**
     * Get the value associated with a key.
     *
     * @param k The key.
     * @return The value associated with k.
     * @throws IllegalArgumentException If k==null or
     *     if no mapping exists for k.
     */
    public V get(K k) throws IllegalArgumentException {
        return this.getNode(k).value;
    }

    /**
     * Check existence of a key.
     *
     * @param k The key.
     * @return True if a mapping for k exists, false otherwise.
     */
    public boolean has(K k) {
        return this.find(k) != null;
    }

    /**
     * Number of mappings.
     *
     * @return Number of key/value pairs in the map.
     */
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        this.setupStringBuilder();
        this.stringBuilder.append("{");

        this.toStringHelper(this.root, this.stringBuilder);

        int length = this.stringBuilder.length();
        if (length > 1) {
            // If anything was appended at all, get rid of
            // the last ", " the toStringHelper put in.
            this.stringBuilder.setLength(length - 2);
        }
        this.stringBuilder.append("}");

        return this.stringBuilder.toString();
    }

    // Recursively append string representations of keys and
    // values from subtree rooted at given node.
    private void toStringHelper(Node n, StringBuilder s) {
        if (n == null) { return; }
        this.toStringHelper(n.left, s);
        s.append(n.key);
        s.append(": ");
        s.append(n.value);
        s.append(", ");
        this.toStringHelper(n.right, s);
    }

    // If we don't have a StringBuilder yet, make one;
    // otherwise just reset it back to a clean slate.
    private void setupStringBuilder() {
        if (this.stringBuilder == null) {
            this.stringBuilder = new StringBuilder();
        } else {
            this.stringBuilder.setLength(0);
        }
    }
}
