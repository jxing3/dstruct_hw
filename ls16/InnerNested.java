public class Innernested {
    private static class Nested {
    }

    //aware of InnerNested
    private class Inner{

    }

    public static void main(String args[]) {
        Nested n = new Nested();

    }
 }
