public class trygraph {


    public static void main(String[] args) {

        Graph<String, String> graph = new SparseGraph<String, String>();

        Vertex<String> v= graph.insert("a");
        Vertex<String> v1 = graph.insert("b");
        Vertex<String> v2 = graph.insert("c");
        Edge<String> e = graph.insert(v, v1, "X");
        Edge<String> e1 = graph.insert(v1, v2, "Y");

        String s = graph.toString();
        System.out.println(s);
    }


}
