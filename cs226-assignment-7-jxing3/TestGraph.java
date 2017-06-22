/* Jesse Xing
   jxing3@jhu.edu
*/

import static org.junit.Assert.*;
 
import org.junit.Test;
 
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import java.util.Iterator;
 
@RunWith(Theories.class)
public class TestGraph {
 
    private interface Fixture {
        Graph<String, String> init();
    }

 
    @DataPoint
    public static final Fixture nodeList = new Fixture() {
        public Graph<String, String> init() {
            return new SparseGraph<>();
        }
    };
 


    @Theory @Test(expected=IllegalArgumentException.class)
    public void removedVertexisInvalidthrowsException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        String s = g.remove(v);
        assertEquals("Peter", s);
        g.remove(v);      
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void anotherGraphVertexisInvalid(Fixture f) {
        Graph<String, String> g = f.init();
        Graph<String, String> g1 = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g1.insert("Peter");
        String s = g.remove(v1);
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void edgeInsertThrowsExceptionInvalidVertex(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        String s = g.remove(v);
        g.insert(v,v1,"peter");
        
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void edgeInsertThrowsExceptionInvalidVertex1(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        String s = g.remove(v);
        g.insert(v1,v,"peter");
        
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void edgeInsertThrowsExceptionSelfLoop(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        g.insert(v,v,"peter");
        
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void edgeInsertThrowsExceptionDuplicate(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        g.insert(v,v1,"peter");
        g.insert(v1,v,"peter");
        g.insert(v,v1,"hi");
        
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void edgeInsertThrowsExceptionDuplicate2(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        g.insert(v,v1,"peter");
        g.insert(v1,v,"peter");
        g.insert(v1,v,"hi");
        
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void removeThrowsExceptionIncidentEdges(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        g.insert(v,v1,"peter");
        g.remove(v1);
        
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void removeThrowsExceptionIncidentEdges1(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        g.insert(v,v1,"peter");
        g.remove(v);
        
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void removeExceptionInvalidEdges(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        g.remove(e);
        g.remove(e);
        
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void outgoingthrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        g.remove(v);
        g.outgoing(v); 
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void incomingthrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        g.remove(v);
        g.incoming(v); 
    }

    @Theory @Test(expected=IllegalArgumentException.class)
    public void fromthrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        g.remove(e);
        g.from(e); 
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void tothrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        g.remove(e);
        g.to(e); 
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void labeledgethrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        g.remove(e);
        g.label(e, "hi"); 
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void labelvertexthrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        g.remove(v);
        g.label(v, "hi"); 
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void retvertlabelthrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        g.remove(v);
        g.label(v); 
    }
    @Theory @Test(expected=IllegalArgumentException.class)
    public void retedgelabelthrowsInvalidException(Fixture f) {
        Graph<String, String> g = f.init();
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        g.remove(e);
        g.label(e); 
    }
    @Theory
    public void verticesIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        int count = 0;
        for(Vertex<String> v2: g.vertices()) {
            assertEquals("Peter",v2.get());
            count++;
        }
        assertEquals(2, count);
    }

    @Theory
    public void removeverticesIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        String x = g.remove(v1);
        int count = 0;
        for(Vertex<String> v2: g.vertices()) {
            assertEquals("Peter",v2.get());
            count++;
        }
        assertEquals(1, count);
        assertEquals(x, "Peter");
    }
    @Theory
    public void edgesIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v1,v2,"peter");
        int count = 0;
        for(Edge<String> e2: g.edges()) {
            assertEquals("peter",e2.get());
            count++;
        }
        assertEquals(2, count);
    }
    @Theory
    public void remomveedgesIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v1,v2,"peter");
        int count = 0;
        String x = g.remove(e1);
        for(Edge<String> e2: g.edges()) {
            assertEquals("peter",e2.get());
            count++;
        }
        assertEquals(1, count);
        assertEquals("peter", x);
    }
    @Theory
    public void outgoingIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v,v2,"peter");
        int count = 0;
        for(Edge<String> e2: g.outgoing(v)) {
            assertEquals("peter",e2.get());
            count++;
        }
        assertEquals(2, count);
    }
    @Theory
    public void removeoutgoingIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v,v2,"peter");
        int count = 0;
        g.remove(e);
        for(Edge<String> e2: g.outgoing(v)) {
            assertEquals("peter",e2.get());
            count++;
        }
        assertEquals(1, count);
    }
    @Theory
    public void incomingIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        int count = 0;
        for(Edge<String> e2: g.incoming(v1)) {
            assertEquals("peter",e2.get());
            count++;
        }
        assertEquals(2, count);
    }
    @Theory
    public void removedincomingIterableIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        int count = 0;
        g.remove(e);
        for(Edge<String> e2: g.incoming(v1)) {
            assertEquals("peter",e2.get());
            count++;
        }
        assertEquals(1, count);
    }
    @Theory
    public void vertexToIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        assertEquals(v1,g.to(e));
        assertEquals(v1,g.to(e1));
    }

    @Theory
    public void vertexFromIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        assertEquals(v,g.from(e));
        assertEquals(v2,g.from(e1));
    }
    @Theory
    public void labelVertexIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        g.label(v, "hi");
        g.label(v1, "hi");
        g.label(v2, "hi");
        assertEquals("hi",g.label(v));
        assertEquals("hi",g.label(v1));
        assertEquals("hi",g.label(v2));
    }

    @Theory
    public void labelEdgeIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        g.label(e, "hi");
        g.label(e1, "hi");
        assertEquals("hi",g.label(e));
        assertEquals("hi",g.label(e1));
    }
    @Theory
    public void clearlabelIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        g.label(v, "hi");
        g.label(v1, "hi");
        g.label(v2, "hi");
        g.label(e, "hi");
        g.label(e1, "hi");
        g.clearLabels();
        assertEquals(null,g.label(v));
        assertEquals(null,g.label(v1));
        assertEquals(null,g.label(v2));
        assertEquals(null,g.label(e));
        assertEquals(null,g.label(e1));
    }
    @Theory
    public void toStringNothingisGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        String s = "digraph {\n}";
        assertEquals(s, g.toString());
    }

    @Theory
    public void toStringofOneVertexisGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        Vertex<String> v = g.insert("A");
        String s = "digraph {\n  \"A\";\n}";
        assertEquals(s, g.toString());
    }
    @Theory
    public void toStringofManyisGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        Vertex<String> v = g.insert("A");
        Vertex<String> v1 = g.insert("B");
        Edge<String> e = g.insert(v, v1, "X");
        String s = "digraph {\n  \"A\";\n";
        s += "  \"B\";\n";
        s += "  \"A\" -> \"B\" [label=\"X\"];\n";
        s += "}";
        assertEquals(s, g.toString());
    }

    @Theory
    public void toStringofManyEdgesisGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        Vertex<String> v = g.insert("A");
        Vertex<String> v1 = g.insert("B");
        Edge<String> e = g.insert(v, v1, "X");
        Edge<String> e1 = g.insert(v1, v, "Y");
        String s = "digraph {\n  \"A\";\n";
        s += "  \"B\";\n";
        s += "  \"A\" -> \"B\" [label=\"X\"];\n";
        s += "  \"B\" -> \"A\" [label=\"Y\"];\n";
        s += "}";
        assertEquals(s, g.toString());
    }
    @Theory
    public void getPutIsGood(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        String s = v.get();
        v.put("hi");
        String s1 = v.get();
        assertEquals("Peter", s);
        assertEquals("hi", s1);
    }
    @Theory
    public void getPutIsGood1(Fixture fix) {
        Graph<String, String> g = fix.init();
        
        Vertex<String> v = g.insert("Peter");
        Vertex<String> v1 = g.insert("Peter");
        Vertex<String> v2 = g.insert("Peter");
        Edge<String> e= g.insert(v,v1,"peter");
        Edge<String> e1= g.insert(v2,v1,"peter");
        String s = e.get();
        e.put("hi");
        String s1 = e.get();
        assertEquals("peter", s);
        assertEquals("hi", s1);
    }
}
