/* Jesse Xing
   jxing3@jhu.edu
*/


import java.util.Scanner;

/**
   Program will take act as an RPM calculator.
   Entering integers pushes them on the stack.
   Entering *, /, +, - performs operations on top two integers
   in stack (if they are there).
   Entering . pops and prints top element of stack.
   Entering ? prints the whole stack top to bottom.
   Entering ! quits program.

*/

public final class Calc {


    private static Stack<Integer> s = new ArrayStack<Integer>();

    private Calc() {}

    //Processes operations +, *, /, -

    private static void parseOps(String in) {

        int i = 0;
        int j = 0;

        try {
            i = s.top();
            s.pop();
        } catch (EmptyStackException e) {
            System.err.println("? Stack has no operands left");
            return;
        }

        try {
            j = s.top();
            s.pop();
        } catch (EmptyStackException e) {
            s.push(i);
            System.err.println("? Stack only has one operand left");
            return;
        }

        int k = 0;

        if (in.equals("+")) {
            k = i + j;
        } else if (in.equals("-")) {
            k = j - i;
        } else if (in.equals("*")) {
            k = j * i;
        } else if (in.equals("/")) {
            if (i != 0) {
                k = j / i;
            } else {
                System.err.println("? Thou shalt not divide by zero");
                return;
            }
        }

        s.push(k);

    }

    //Processes operations !, ?, .

    private static boolean otherOps(String in) {

        if (in.equals("!")) {

            return true;

        } else if (in.equals("?")) {

            System.out.println(s.toString());

            return true;

        } else if (in.equals(".")) {

            if (!s.empty()) {
                System.out.println(s.top());
                s.pop();
            } else {
                System.err.println("? Stack is empty");
            }
            return true;
        }

        return false;

    }

    /** Main program takes input from user and parses it to perform
    operations outlined in class javadocs.
    @param args Command line arguments
    */

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        String input;

        do {
            System.out.print(">");

            input = kb.next();

            if (!otherOps(input)) {
                if (input.equals("+") || input.equals("-") || input.equals("*")
                    || input.equals("/")) {
                    parseOps(input);
                } else {
                    try {
                        int i = Integer.parseInt(input);
                        s.push(i);
                    } catch (NumberFormatException e) {
                        System.err.println("? The following input: '" + input
                                           + "' is invalid");
                    }
                }
            }
        }
        while (!(input.equals("!")));

        if (!s.empty()) {

            System.out.println(s.toString());

        }

    }

}