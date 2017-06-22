
import java.util.ArrayList;
import java.util.Iterator;

public class OpenAddressHashTable<K,V> implements Map<K,V> {

    private static class Entry<K,V> {
        K key;
        V value;
        boolean del;
        Entry(K k, V v) {
            this.key = k;
            this.value = v;
            del = false;

        }

        public int hashCode() {
             return this.key.hashCode();
        }

    }

    private class HashTableIterator {

        int pos;
        int max;

        HashTableIterator(int m) {
            pos = 0;
            max = m;

        }

        private boolean hasNext() {
            while(pos < max) {
                if (OpenAddressHashTable.this.data.get(pos) == null ||OpenAddressHashTable.this.data.get(pos).del == true) {
                    pos ++;
                } else {
                    return true;
                }
            }

            return false;
            
        }

        private Entry<K,V> next() {
            Entry<K,V> e = null;
            if(this.hasNext()) {
                e = OpenAddressHashTable.this.data.get(pos);
                pos++;
            }
            return e;
        }


    }

    private class HashTableKIterator implements Iterator<K> {

        HashTableIterator iter = new HashTableIterator(OpenAddressHashTable.this.size);
        Entry<K,V> e;

        public boolean hasNext() {
            return iter.hasNext();
        }

        public K next() {
            e = iter.next();
            if(e!= null) {
                return e.key;
            }
            return null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private ArrayList<Entry<K,V>> data;
    private int size;
    private int used;

    public OpenAddressHashTable() {

        this.data = new ArrayList<>();
        size = 8;
        used = 0;
        for(int i = 0; i < this.size; i++) {
            this.data.add(i, null);
        }


    }

    private int hash(Object o) {
        return Math.abs(o.hashCode()) % this.size;
    }

    private void grow() {

        ArrayList<Entry<K,V>> newdata = new ArrayList<>();

        int prevsize = this.size;
        this.size = 2*this.size;
        for(int i = 0; i < this.size; i++) {
            newdata.add(i, null);
        }

        HashTableIterator iter = new HashTableIterator(prevsize);

        while(iter.hasNext()) {
            Entry<K,V> e = iter.next();

            insertHelper(e, newdata);
        }

        this.data = newdata;


    }


    private Entry<K,V> find(K k) {

        int slot = this.hash(k);
        while (this.data.get(slot)!= null) {
            if(!(this.data.get(slot).del) && this.data.get(slot).key.equals(k)){
                return this.data.get(slot);
            }
            slot = (slot + 1) % this.size;

        }

        return null;
    }

    private Entry<K,V> findForSure(K k) {

        Entry<K,V> e = this.find(k);
        if (e == null) {
            throw new IllegalArgumentException();
        }
        return e;

    }

    private void insertHelper(Entry<K,V> e, ArrayList<Entry<K,V>> rep) {

        int slot = this.hash(e.key);
        while (rep.get(slot)!= null && !(rep.get(slot).del)) {

            slot = (slot + 1) % this.size;

        }
        rep.set(slot, e);
    }

  /**
        Insert a new key/value pair.

        @param k The key.
        @param v The value to be associated with k.
        @throws IllegalArgumentException If k==null or
            if a mapping for k already exists.
    */
    public void insert(K k, V v) throws IllegalArgumentException {
        if (((double) used)/this.size > .5) {
            this.grow();
        }

        if (k == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (this.has(k)) {
            throw new IllegalArgumentException("Key exists");
        }
        this.insertHelper(new Entry<K,V>(k, v), this.data);
        used ++;

    }

    /**
        Remove an existing key/value pair.

        @param k The key.
        @throws IllegalArgumentException If k==null or
            if no mapping exists for k.
    */
    public void remove(K k) throws IllegalArgumentException {

         Entry<K,V> e = this.findForSure(k);

         e.del = true;

         used--;


    }

    /**
        Update the value associated with a key.

        @param k The key.
        @param v The value to be associated with k.
        @throws IllegalArgumentException If k==null or
            if no mapping exists for k.
    */
    public void put(K k, V v) throws IllegalArgumentException {

        Entry<K,V> e = this.findForSure(k);
        e.value = v;

    }

    /**
        Get the value associated with a key.

        @param k The key.
        @return The value associated with k.
        @throws IllegalArgumentException If k==null or
            if no mapping exists for k.
    */
    public V get(K k) throws IllegalArgumentException {

        Entry<K,V> e = this.findForSure(k);
        return e.value;

    }

    /**
        Check existence of a key.

        @param k The key.
        @return True if a mapping for k exists, false otherwise.
    */
    public boolean has(K k) {

       return this.find(k) != null;
        

    }

    /**
        Number of mappings.

        @return Number of key/value pairs in the map.
    */
    public int size() {
        return used;
    }


    public Iterator<K> iterator() {
        return new HashTableKIterator();
    }




}
