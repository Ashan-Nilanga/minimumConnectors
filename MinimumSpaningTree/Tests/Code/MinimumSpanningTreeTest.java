package Code;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class MinimumSpanningTreeTest {

    private static List<Vertex> graph;
    private static MinimumSpanningTree primAlgoSolver;

    @BeforeAll
    static void setUp(){
        graph = new ArrayList<>();

        // vertexes
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");

        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);

        Random rand = new Random();

        int[][] weights = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {13, 3, 0, 0, 0},
                {4, 22, 4, 0, 0},
                {5, 8, 2, 4, 0}
        };

        // creating the links between vertices
        for(int i = 0; i < graph.size(); i++){
            for (int j = 0; j < i; j++){
                graph.get(i).addEdge(graph.get(j), new Edge(weights[i][j]));
            }
        }

        primAlgoSolver = new MinimumSpanningTree(graph);
    }

    @BeforeEach
    void setUpEach() {
        primAlgoSolver.resetAllTraversed();
    }

    @Test
    @DisplayName("Testing creating a MST from vertices and edges")
    void testRun() {


        primAlgoSolver.run(2); // graph will be linked in MST starting from vertex-> B

        // This is the array to collect all the connections
        List<Pair<String, Integer>> connections = new ArrayList<>();



        for(Vertex vertex: graph){
            connections.addAll(vertex.getConnections());
        }


        ArrayList<String> actual_connections = new ArrayList<>();
        int actual_min_distance = 0;


        int i = 0;


        for(Pair<String, Integer> pair: connections){
            actual_connections.add(pair.getKey());
            actual_min_distance += pair.getValue();
            i++;
        }


        Assertions.assertEquals(10, actual_min_distance);

        ArrayList<String> expected_connections = new ArrayList<>(Arrays.asList("AB", "AD", "BC", "CE"));

        // Following code confirm that the actual and expected values are similar for connections
        for(String out_connection: actual_connections){
            Assertions.assertTrue(expected_connections.contains(out_connection));
        }

        Assertions.assertEquals(expected_connections.size(), actual_connections.size());



    }

    @Test
    void getMinimumConnectors() {

        ArrayList<String> outputConnections = (ArrayList<String>) primAlgoSolver.getMinimumConnectors();

        ArrayList<String> expected_connections = new ArrayList<>(Arrays.asList("AB", "AD", "BC", "CE"));

        // Following code confirm that the actual and expected values are similar for connections
        for(String out_connection: outputConnections){
            Assertions.assertTrue(expected_connections.contains(out_connection));
        }

        Assertions.assertEquals(expected_connections.size(), outputConnections.size());

    }

    @Test
    void getMinimumDistance() {

        int output = primAlgoSolver.getMinimumDistance();
        int expected = 10;

        Assertions.assertEquals(expected, output);
    }
}