/**
    StatableleArray<T> extends SimpleArray.
    Adds get, set, and length count operation to simplearray.

    @param <T> element type
*/

public class StatableArray<T> extends SimpleArray<T> implements Statable {

    private int getcount;
    private int setcount;
    private int lengthcount;

 /**
        Create a new StatableArray<T> instance.

        @param length number of slots
        @param element initial value for all slots
        @throws InvalidLengthException if length &le; 0.
    */

    public StatableArray(int length, T element) throws InvalidLengthException {

        super(length, element);

    }

/**
        Return element stored at index.

        @param index index between 0 and length-1 where to get from
        @return the element at the given index
        @throws InvalidIndexException if index is outside array bounds
    */

    public T get(int index) throws InvalidIndexException {

        this.getcount++;
        return super.get(index);

    }

/**
        Store new element at index.

        @param index index between 0 and length-1 where to put the element
        @param element element to put at the given index
        @throws InvalidIndexException if index is outside array bounds
    */

    public void set(int index, T element) throws InvalidIndexException {

        this.setcount++;
        super.set(index, element);

    }

  /**
        How many slots in this array?

        @return the length of the array, always greater than 0
    */

    public int length() {

        this.lengthcount++;
        return super.length();

    }

/**
        Return statistics about array object.

        @return String representing statistics about the array object.
    */

    public String getStatistics() {
        String s = "StatableArray Statistics\n";
        s += "========================\n";
        int total = this.getcount + this.setcount + this.lengthcount;
        s += "Total: " + total + "\n";
        s += "get(): " + this.getcount + "\n";
        s += "set(): " + this.setcount + "\n";
        s += "length(): " + this.lengthcount;

        return s;
    }

/**
        Reset statistics object has accumulated.
    */

    public void resetStatistics() {

        this.getcount = 0;
        this.setcount = 0;
        this.lengthcount = 0;

    }


}
