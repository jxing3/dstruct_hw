public class Unique {
    // array of unique numbers
    private static int[] data;
    // how many slots in data are used?
    private static int used;

    // position of given value in data[], -1 if not found
    private static int find(int value) {
        for (int i = 0; i < used; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // insert value into data[] if not already present
    private static void insert(int value) {
        int position = find(value);
        if (position < 0) {
            data[used] = value;
            used += 1;
        }
    }

    public static void main(String[] args) {
        // worst case: args.length distinct numbers
        data = new int[args.length];
        // process args and insert unique numbers into data[]
        for (String arg: args) {
            try {
                int i = Integer.parseInt(arg);
                insert(i);
            }
            catch (NumberFormatException e) {
                 System.out.printf("Ignored non-integer argument %s\n", arg);
            }
        }
        // output unique numbers in array order
        for (int i = 0; i < used; i++) {
            System.out.println(data[i]);
        }
    }
}
