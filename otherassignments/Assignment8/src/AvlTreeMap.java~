import java.util.Iterator;
import java.util.ArrayList;

/** AVL Tree Map Implementation.
    @param <K> first type
    @param <V> second type
*/
public class AvlTreeMap<K extends Comparable<? super K>, V>
    implements OrderedMap<K, V> {

    private class Node {
        Node left, right;
        K key;
        V value;
        int height;

        Node(K k, V v) {
            this.left = null;
            this.right = null;
            this.key = k;
            this.value = v;
        }
    }

    private Node root, parent;

    @Override
    public Iterator<K> iterator() {
        ArrayList<K> keys = new ArrayList<K>();
        this.iterator(this.root, keys);
        return keys.iterator();
    }

    private void iterator(Node n, ArrayList<K> keys) {
        if (n == null) {
            return;
        }
        this.iterator(n.left, keys);
        keys.add(n.key);
        this.iterator(n.right, keys);
    }

    @Override
    public void insert(K k, V v) throws IllegalArgumentException {
        this.root = this.insert(this.root, k, v);
    }

    private Node checkBalance(K k, Node n) {
        if (n == null) {
            return this.root;
        }
        // Left Rotations
        if (this.height(n.left) - this.height(n.right) == 2) {
            if (k.compareTo(n.left.key) < 0) {
                n = this.rotateLeft(n);
            } else {
                n = this.doubleLeft(n);
            }
        }
        // Right Rotations
        if (this.height(n.right) - this.height(n.left) == 2) {
            if (k.compareTo(n.right.key) > 0) {
                n = this.rotateRight(n);
            } else {
                n = this.doubleRight(n);
            }
        }
        return n;
    }

    private Node insert(Node n, K k, V v) {
        if (n == null) {
            return new Node(k, v);
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.insert(n.left, k, v);
            // check balance & deal with case 1,2
            n = this.checkBalance(k, n);
        } else if (cmp > 0) {
            n.right = this.insert(n.right, k, v);
            // check balance & deal with case 3, 4
            n = this.checkBalance(k, n);
        } else {
            throw new IllegalArgumentException("duplicate key: " + k);
        }

        // Math.max returns greatest value between 2 parameters
        // so get max, then add 1...that's parents "height"
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        return n;
    }

    private Node rotateLeft(Node n) {
        Node n2 = n.left;
        if (n2 == null) {
            return n;
        }
        n.left = n2.right;
        n2.right = n;
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        n2.height = Math.max(height(n2.left), n.height) + 1;
        return n2;
    }

    private Node rotateRight(Node n) {
        Node n2 = n.right;
        if (n2 == null) {
            return n;
        }
        n.right = n2.left;
        n2.left = n;
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        n2.height = Math.max(height(n2.right), n.height) + 1;
        return n2;
    }

    private Node doubleLeft(Node n) {
        n.left = this.rotateRight(n.left);
        return this.rotateLeft(n);
    }

    private Node doubleRight(Node n) {
        n.right = this.rotateLeft(n.right);
        return this.rotateRight(n);
    }

    private int height(Node n) {
        if (n == null) {
            return -1;
        }
        return n.height;
    }

    private Node findMax(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    private Node removeMax(Node n) {
        if (n.right == null) {
            return n.left;
        }
        n.right = this.removeMax(n.right);
        return n;
    }

    private Node getParent(Node n, K k, Node a) {
        Node toReturn = a;
        if (k.compareTo(n.key) < 0) {
            toReturn = n;
            return this.getParent(n.left, k, toReturn);
        } else if (k.compareTo(n.key) > 0) {
            toReturn = n;
            return this.getParent(n.right, k, toReturn);
        } else {
            return toReturn;
        }
    }

    private Node remove(Node n) {
        if (n.left == null) { //has right child
            return n.right;
        }
        if (n.right == null) { //has left child
            return n.left;
        }

        Node max = this.findMax(n.left);
        n.left = this.removeMax(n.left);
        this.checkBalance(n.key, n);
        n.key = max.key;
        n.value = max.value;

        return n;
    }

    private Node remove(Node n, K k) {
        if (n == null) {
            throw new IllegalArgumentException("Cannot have null key: " + k);
        }

        this.parent = this.getParent(this.root, k, this.root);
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.remove(n.left, k);
        } else if (cmp > 0) {
            n.right = this.remove(n.right, k);
        } else {
            n = this.remove(n);
        }
        this.parent.height = Math.max(this.height(this.parent.left),
            this.height(this.parent.right)) + 1;
        return n;
    }

    @Override
    public V remove(K k) throws IllegalArgumentException {
        V value = this.get(k);
        this.root = this.remove(this.root, k);
        this.root = this.orderLevelTraversal(this.root, this.parent.key);
        return value;
    }

    @Override
    public void put(K k, V v) throws IllegalArgumentException {
        Node n = this.find(this.root, k);
        n.value = v;
    }

    @Override
    public V get(K k) throws IllegalArgumentException {
        Node n = this.find(this.root, k);
        return n.value;
    }

    private Node find(Node n, K k) {
        if (n == null) {
            return null;
        }

        if (k.compareTo(n.key) < 0) {
            return this.find(n.left, k);
        } else if (k.compareTo(n.key) > 0) {
            return this.find(n.right, k);
        } else {
            return n;
        }

    }

    @Override
    public boolean has(K k) {
        if (this.find(this.root, k) == null) {
            return false;
        }
        return true;
    }

    private Node orderLevelTraversal(Node r, K k) {
        if (r == null) {
            return r;
        }
        if (r.height == 0) { // no children
            r = this.checkBalance(k, r);
        }
        if (r.height > 0) { // has at least 1 child
            r.left = this.orderLevelTraversal(r.left, k);
            r = this.checkBalance(k, r);
            r.right = this.orderLevelTraversal(r.right, k);
            r = this.checkBalance(k, r);
        }
        return r;
    }

}
