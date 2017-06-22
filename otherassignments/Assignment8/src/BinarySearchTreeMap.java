import java.util.Iterator;
import java.util.ArrayList;

/**
    Binary Search Tree implementation of OrderedMap<K,V>.

    These trees are not balanced, so worst-case all operations
    are O(n).

    @param <K> Type for keys.
    @param <V> Type for values.
*/
public class BinarySearchTreeMap<K extends Comparable<? super K>, V>
    implements OrderedMap<K, V> {

    private class Node {
        Node left, right;
        K key;
        V value;

        Node(K k, V v) {
            this.left = null;
            this.right = null;
            this.key = k;
            this.value = v;
        }
    }

    private Node root;
    private StringBuilder stringBuilder;

    @Override
    public boolean has(K k) {
        return this.find(k) != null;
    }

    // Iterative find() just for variety. Originally this
    // was the recursive find() from lecture.
    private Node find(K k) {
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

    @Override
    public void put(K k, V v) {
        Node n = this.findForSure(k);
        n.value = v;
    }

    @Override
    public V get(K k) {
        Node n = this.findForSure(k);
        return n.value;
    }

    private Node findForSure(K k) {
        Node n = this.find(k);
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }
        return n;
    }

    @Override
    public void insert(K k, V v) {
        this.root = this.insert(this.root, k, v);
    }

    private Node insert(Node n, K k, V v) {
        if (n == null) {
            return new Node(k, v);
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.insert(n.left, k, v);
        } else if (cmp > 0) {
            n.right = this.insert(n.right, k, v);
        } else {
            throw new IllegalArgumentException("duplicate key " + k);
        }

        return n;
    }

    @Override
    public V remove(K k) {
        V value = this.get(k);
        this.root = this.remove(this.root, k);
        return value;
    }

    // Remove node with key k from subtree rooted at n; return
    // new subtree without said node.
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
            n = this.remove(n);
        }

        return n;
    }

    // Remove node n, return remaining tree.
    private Node remove(Node n) {
        // 0 and 1 child
        if (n.left == null) {
            return n.right;
        }
        if (n.right == null) {
            return n.left;
        }

        // 2 children
        Node max = this.max(n.left);
        n.left = this.removeMax(n.left);
        n.key = max.key;
        n.value = max.value;
        return n;
    }

    // Return node holding maximum key from given
    // subtree. Iterative version. We need this in
    // addition to removeMax() because that method
    // cannot return both the modified tree and the
    // key/value we need to write into the node we
    // originally wanted to delete.
    private Node max(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    // Remove node holding maximum key from given
    // subtree, returning the remaining tree.
    private Node removeMax(Node n) {
        if (n.right == null) {
            return n.left;
        }
        n.right = this.removeMax(n.right);
        return n;
    }

    @Override
    public Iterator<K> iterator() {
        ArrayList<K> keys = new ArrayList<K>();
        this.iteratorHelper(this.root, keys);
        return keys.iterator();
    }

    private void iteratorHelper(Node n, ArrayList<K> keys) {
        if (n == null) { return; }
        this.iteratorHelper(n.left, keys);
        keys.add(n.key);
        this.iteratorHelper(n.right, keys);
    }

    @Override
    public String toString() {
        if (this.stringBuilder == null) {
            this.stringBuilder = new StringBuilder();
        } else {
            this.stringBuilder.setLength(0);
        }

        this.stringBuilder.append("{");
        this.toStringHelper(this.root, this.stringBuilder);
        int length = this.stringBuilder.length();
        if (length > 1) {
            this.stringBuilder.setLength(length - 2);
        }
        this.stringBuilder.append("}");

        return this.stringBuilder.toString();
    }

    private void toStringHelper(Node n, StringBuilder s) {
        if (n == null) { return; }
        this.toStringHelper(n.left, s);
        s.append(n.key);
        s.append(": ");
        s.append(n.value);
        s.append(", ");
        this.toStringHelper(n.right, s);
    }
}
