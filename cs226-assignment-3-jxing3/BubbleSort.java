/**

   A basic BubbleSort: Sorts by bubbling largest element to rear with
   every pass.

    @param <T> element type
*/


public final class BubbleSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

    /**
        Sort an array.

        @param array array to sort
    */
    public void sort(Array<T> array) {
        T temp;
        boolean swapped = false;
        for (int end = array.length() - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
        }
    }

/**
        Name of algorithm.

        @return name of algorithm
    */

    public String name() {
        return "Bubble Sort";
    }

}
