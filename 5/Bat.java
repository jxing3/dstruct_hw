import java.util.Scanner;

public class Bat {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        while (s.hasNextInt()) {
            int i = s.nextInt();
            System.out.println("!" + i);
        }
    }

}
