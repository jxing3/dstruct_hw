import java.util.ArrayList;
import java.util.Scanner;

/**
    Filter unique integers.
*/
public final class Unique {
    private static PriorityQueue<Integer> data;
    private static final double NANOS = 1e9;

    private Unique() { }
    
    /**
        Main method.
        @param args command line arguments
    */
    public static void main(String[] args) {
        data = new BinaryHeapPriorityQueue<Integer>();
        Scanner scanner = new Scanner(System.in);

        long before = System.nanoTime();

        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            data.insert(i);
        }

        while (!data.empty()) {
            int lastSeen = data.top();
            data.remove();
            while (!data.empty() && data.top() == lastSeen) {
            	data.remove();
            	if (!data.empty()) {
            	    lastSeen = data.top();
            	}
            }
            System.out.println(lastSeen);
        }
        
        long duration = System.nanoTime() - before;
        System.err.println(duration / NANOS);
    }
}
