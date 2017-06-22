import java.util.Scanner;

/**
    Filter unique integers.
*/
public final class Unique {
    private static PriorityQueue<Integer> data;
    private static final double NANOS = 1e9;

    private Unique() { /* hide constructor */ }

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

        int last = 0;

        if (!data.empty()) {

            last = data.top();
            System.out.println(last);
            data.remove();

        }

        while (!data.empty()) {
            if (data.top() != last) {
                System.out.println(data.top());
            }
            last = data.top();
            data.remove();
        }

        long duration = System.nanoTime() - before;
        System.err.println(duration / NANOS);
    }
}
