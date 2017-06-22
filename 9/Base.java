public class Base { // implict "extends Object"
    // giving a constructor here "hides" the default constructor
    public Base(int x) {
        System.out.println("Base(int)!");
    }
    public void bla() {
        System.out.println("Base.bla()!");
    }
}
