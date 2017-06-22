import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Stuff {

    public int find(int a[], int k) {
        for (int i = 0; // 1 assignment
             i < a.length; // n+1 comparisons
             i++) { // n assignments
            if (a[i] == k) { // n comparisons
                return i;
            }
        }
        return -1;
    }

    @Test
    public void evenArray() {
        int a[] = {10, 20, 30, 40};
        assertEquals(-1, find(a, 31));
        assertEquals(-1, find(a, 1));
        assertEquals(-1, find(a, 41));
        assertEquals(0, find(a, 10));
        assertEquals(1, find(a, 20));
        assertEquals(3, find(a, 40));
    }

    // try even/odd/empty/singleton array (rules of thumb)
}
