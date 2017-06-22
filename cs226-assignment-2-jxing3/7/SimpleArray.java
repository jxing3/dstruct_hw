public class SimpleArray<T> implements Array<T> {
    private T rep[]; // representation of array idea

    public SimpleArray(int length, T t) throws LengthException{
        if (length <= 0) {
            throw new LengthException();
        }

        this.rep = (T[]) new Object[length]; // Java sadface
        for (int i = 0; i < this.rep.length; i++) {
            this.rep[i] = t;
        }
    }

    public void set(int index, T t) throws IndexException {
        if (index < 0 || index >= this.rep.length) {
            throw new IndexException();
        }

        this.rep[index] = t;
    }

    public T get(int index) throws IndexException {
        if (index < 0 || index >= this.rep.length) {
            throw new IndexException();
        }

        return this.rep[index];
    }

    public int length() {
        return this.rep.length;
    }

}
