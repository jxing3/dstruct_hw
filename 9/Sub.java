public class Sub extends Base {
    public Sub(int y) {
        super(y);
        System.out.println("Sub(int)!");
    }
    public void bla() {
        super.bla();
        System.out.println("Sub.bla()!");
    }

    public static void main(String args[]) {
        Base b = new Sub(10);
        b.bla();
    }
}
