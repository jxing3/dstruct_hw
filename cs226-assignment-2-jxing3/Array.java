/**Defines an array interface*/
public interface Array<T> {
    // would like new, but it's Java so constructor so sadface

/**Sets the value in the array at a particular index to t
@param index the index in the array to be changed
@param t the value that particular index in the array should be changed to
*/
    void set(int index, T t) throws IndexException;

/**Gets and returns the value at a particular index from the array
@param index the index or position in the array we want to get the value from
@return the value at the particular index in the array
*/

    T get(int index) throws IndexException;

/**Returns the length of the array
@return the length of the array
*/
    int length();
}
