import java.util.Scanner;

/**
    Filter unique integers.
*/
public final class Unique {
    private static Set<Integer> data;
    private static final double NANOS = 1e9;

    private Unique() { /* hide constructor */ }

    /**
        Main method.
        @param args command line arguments
    */
    public static void main(String[] args) {
        data = new OrderedArraySet<Integer>();
        Scanner scanner = new Scanner(System.in);

        long before = System.nanoTime();

        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            data.insert(i);
        }

        for (Integer i: data) {
            System.out.println(i);
        }

        long duration = System.nanoTime() - before;
        System.err.println("Took " + duration / NANOS + " seconds");
    }
}
