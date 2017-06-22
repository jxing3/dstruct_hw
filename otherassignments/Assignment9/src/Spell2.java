import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Spell Checker.
*/
public final class Spell2 {

    private static HashSet<String> dm = new HashSet<String>();

    private Spell2() {/*silence checkstyle*/}

    /** Main method.
        @param args array of arguments passed into program
        @throws IOException if dictionary not included
    */
    public static void main(String[] args) throws IOException {
        BufferedReader inputStream;
        inputStream = new BufferedReader(new FileReader(args[0]));

        String data;
        while ((data = inputStream.readLine()) != null) {
            dm.add(data.trim());
        }
        inputStream.close();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] words = s.split("[\\s[^a-zA-Z]]+");
            for (String word: words) {
                if (!dm.contains(word.toLowerCase())
                    && !word.equals("")) {
                    System.out.println(word);
                }
            }
        }
        scanner.close();
    }
}
