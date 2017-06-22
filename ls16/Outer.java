public class Outer <T>{

    //just like any old class, but visible only INSIDE Outer
    //has it's own <T>
    private static class Nested<T> {
    }

    //aware of Outer, gets <T> from Outer.
    private class Inner {
        T t;
        Inner(T t) {
            this.t = t;
            System.out.println(t);
            System.out.println(this);
            System.out.println(Outer.this);
        }
    }

    Outer(T t) {
       Inner i = new Inner(t);
       System.out.println(this);

    }

    public static void main(String args[]) {
        Nested<String> n = new Nested<String>();
        //Inner i = new Inner();
        Outer<Integer> o = new Outer<Integer>(10);
    }
 }
