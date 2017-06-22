/* Jesse Xing
   jxing3@jhu.edu
*/

import java.util.ArrayList;
import java.util.Iterator;
/**
    Implementation of a general n-ary position-based tree.
    This implementation uses nodes with Arraylist to hold children

    @param <T> Type of elements stored in tree.
*/
public class TreeImplementation<T> implements Tree<T> {

    private static class Node<T> implements Position<T> {
        T data;
        Node<T> parent;
        ArrayList<Node<T>> children;
        Tree<T> color;

        Node(T t, Tree<T> c) {
            this.children = new ArrayList<Node<T>>();
            this.data = t;
            this.color = c;
        }

        public T get() {
            return this.data;
        }
        public void put(T t) {
            this.data = t;
        }
    }


    private static class SubOp1<T> extends Operation<T> {

        ArrayList<Position<T>> iter = new ArrayList<Position<T>>();

        public void pre(Position<T> p) {

            this.iter.add(p);

        }

    }

    private static class SubOp2<T> extends Operation<T> {

        String s = "[";

        public void pre(Position<T> p) {
            this.s += p.get() + ", ";

        }

    }

    private static class SubOp3<T> extends Operation<T> {

        ArrayList<T> iter = new ArrayList<T>();

        public void pre(Position<T> p) {
            this.iter.add(p.get());

        }

    }

    private Node<T> root;
    private int size;

    /**
       Constructor for Treeimplementation. Sets root to null
       size to zero.
    */
    public TreeImplementation() {

        this.root = null;
        this.size = 0;

    }

    private void recurse(Node<T> n, Operation<T> o) {

        if (n == null) {
            return;
        }
        o.pre(n);
        int num = n.children.size();

        if (num == 0) {
            o.in(n);

        } else if (num == 1) {
            this.recurse(n.children.get(0), o);
            o.in(n);
        } else {
            for (int i = 0; i < num - 1; i++) {
                this.recurse(n.children.get(i), o);
                o.in(n);
            }
            this.recurse(n.children.get(num - 1), o);
        }

        o.post(n);

    }


    /**
        No elements?
        @return True if tree is empty, false otherwise.
    */
    public boolean empty() {

        return this.size == 0;
    }

    /**
        How many elements?
        @return Number of elements in tree.
    */
    public int size() {

        return this.size;

    }

    /**
        Is the given position valid for this tree?
        @param p Position to check.
        @return True if p is valid, false otherwise.
    */
    public boolean valid(Position<T> p) {
        Node<T> n;
        if (p == null || !(p instanceof Node<?>)) {
            return false;
        }
        n = (Node<T>) p;
        if (n.color != this) {
            return false;
        }
        return true;

    }

    /**
        Does given position have a parent?
        @param p Position to check.
        @return True if p has parent, false otherwise.
        @throws InvalidPositionException If p not valid.
    */
    public boolean hasParent(Position<T> p) throws InvalidPositionException {

        if (!this.valid(p)) {
            throw new InvalidPositionException();
        }
        Node<T> n = (Node<T>) p;
        return n.parent != null;

    }

    /**
        Does given position have children?
        @param p Position to check.
        @return True if p has children, false otherwise.
        @throws InvalidPositionException If p not valid.
    */
    public boolean hasChildren(Position<T> p) throws InvalidPositionException {

        if (!this.valid(p)) {
            throw new InvalidPositionException();
        }
        Node<T> n = (Node<T>) p;
        return !n.children.isEmpty();

    }

    /**
        Is given position the root of the tree?
        @param p Position to check.
        @return True if p is root of tree, false otherwise.
        @throws InvalidPositionException If p not valid.
    */
    public boolean isRoot(Position<T> p) throws InvalidPositionException {

        if (!this.valid(p)) {
            throw new InvalidPositionException();
        }
        Node<T> n = (Node<T>) p;
        return n == this.root;

    }

    /**
        Insert at the root of the tree.
        @param t Element to store.
        @return Position of new child.
        @throws InsertionException If root exists already.
    */
    public Position<T> insertRoot(T t) throws InsertionException {

        if (this.root != null) {
            throw new InsertionException();
        }

        this.root = new Node<T>(t, this);
        this.size += 1;
        return this.root;

    }

    /**
        Insert new child of given position.
        @param p Position to insert a child for.
        @param t Element to store.
        @return Position of new child.
        @throws InvalidPositionException If p not valid.
    */
    public Position<T> insertChild(Position<T> p, T t)
        throws InvalidPositionException {
        if (!this.valid(p)) {
            throw new InvalidPositionException();
        }
        Node<T> p1 = (Node<T>) p;
        Node<T> n = new Node<T>(t, this);
        n.parent = p1;
        p1.children.add(n);
        this.size += 1;
        return n;

    }

    /**
        Remove given position.
        @param p Position to remove.
        @return Element stored at p.
        @throws InvalidPositionException If p not valid.
        @throws RemovalException If node has children.
    */
    public T removeAt(Position<T> p)
        throws InvalidPositionException, RemovalException {

        if (!this.valid(p)) {
            throw new InvalidPositionException();
        }
        Node<T> n = (Node<T>) p;
        if (!(n.children.isEmpty())) {
            throw new RemovalException();
        }
        n.color = null;
        T t = n.data;
        n.data = null;
        Node<T> n1 = n.parent;
        n.parent = null;
        if (n1 != null) {
            n1.children.remove(n);
        }

        this.size--;

        return t;

    }

    /**
        Get the root position of the tree.
        @return Position of tree's root.
        @throws EmptyTreeException If tree was empty.
    */
    public Position<T> root() throws EmptyTreeException {

        if (this.root == null) {
            throw new EmptyTreeException();
        }

        return this.root;

    }

    /**
        Return the parent of the given position.
        @param p Position to return parent for.
        @return Position of p's parent.
        @throws InvalidPositionException If p not valid or the root.
    */
    public Position<T> parent(Position<T> p) throws InvalidPositionException {

        if (!this.valid(p)) {
            throw new InvalidPositionException();
        }

        Node<T> n = (Node<T>) p;
        if (n == this.root) {
            throw new InvalidPositionException();
        }
        return n.parent;

    }

    /**
        Return iterable collection of children of the given position.
        @param p Position to return children for.
        @return Iterable with all children.
        @throws InvalidPositionException If p not valid or a leaf.
    */
    public Iterable<Position<T>> children(Position<T> p)
        throws InvalidPositionException {
        if (!this.valid(p)) {
            throw new InvalidPositionException();
        }
        Node<T> n = (Node<T>) p;
        if (n.children.isEmpty()) {
            throw new InvalidPositionException();
        }
        ArrayList<Position<T>> iter = new ArrayList<Position<T>>();
        iter.addAll(n.children);
        return iter;

    }

    /**
        Return iterable collection of all positions in the tree.
        @return Iterable with all positions.
    */
    public Iterable<Position<T>> positions() {
        SubOp1<T> o = new SubOp1<T>();
        this.traverse(o);
        return o.iter;
    }

    /**
        Return iterator of all elements stored in the tree.
        @return Iterator of elements.
    */
    public Iterator<T> iterator() {
        SubOp3<T> o = new SubOp3<T>();
        this.traverse(o);
        return o.iter.listIterator();

    }

    /**
        Traverse the tree performing the given operations as required.

        Note that nothing is called for an empty tree; for a tree with
        several children, in() is called between *every* two children.

        @param o Operation object to run over the tree with.
    */
    public void traverse(Operation<T> o) {

        this.recurse(this.root, o);

    }
    /**
        Returns String that list the elements of the tree as they would
        be processed during a pre-order traversal.

        @return The string described above
    */

    public String toString() {

        SubOp2<T> o = new SubOp2<T>();
        this.traverse(o);
        String s1 = o.s;
        int indexOfLast = s1.lastIndexOf(",");
        if (indexOfLast > -1) {
            s1 = o.s.substring(0, indexOfLast);
        }
        s1 += "]";
        return s1;

    }



}
