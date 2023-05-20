package Code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VertexTest {

    @Test
    @DisplayName("Testing finding the minimum connection from vertex")
    void testNextMinimum() {

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");

        // Edges
        Edge ab = new Edge(31);
        Edge ac = new Edge(43);
        Edge ad = new Edge(12);
        Edge ae = new Edge(3);

        a.addEdge(b, ab);
        a.addEdge(c, ac);
        a.addEdge(d, ad);
        a.addEdge(e, ae);


        Pair<Vertex, Edge> min = a.nextMinimum();

        int expected = 3;
        int actual = min.getValue().getWeight();

        Assertions.assertEquals(expected, actual);


    }

    @Test
    @DisplayName("Testing adding edge to a vertex")
    void testAddEdge() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");

        Edge ab = new Edge(12);
        Edge ab_long = new Edge(31);

        // Add edge will create a one directional link between vertices
        a.addEdge(b, ab);

        // this will get the edges connected from a to b
        Edge edge_ab = a.getEdges().get(b);
        Edge edge_ba = b.getEdges().get(a);

        Assertions.assertEquals(ab, edge_ba);
        Assertions.assertEquals(ab, edge_ab);

        a.addEdge(b, ab_long); // this will not be added because it is not the shortest edge

        Edge edge_ab_afterLong = a.getEdges().get(b);
        Edge edge_ba_afterLong = b.getEdges().get(a);

        Assertions.assertEquals(ab, edge_ab_afterLong);
        Assertions.assertEquals(ab, edge_ba_afterLong);

    }
}