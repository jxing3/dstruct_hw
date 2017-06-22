public class hi {

    private static class Node {
        Node left, right;
        String key;
        String value;
        int height;

        Node(String k, String v) {
            this.left = null;
            this.right = null;
            this.key = k;
            this.value = v;
        }
    }

    public static void main(String[] args) {

        Node n = new Node("hi","hi");
        Node n1 = n.left;

    }


}
