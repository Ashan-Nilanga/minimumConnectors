package Code;

import java.util.*;
import java.util.Map.Entry;


public class Vertex {

    private final String name;
    private final Map<Vertex, Edge> edges;
    private boolean isVisited;

    /**
     * Use to represent Code.Vertex of a graph
     * @param name: Name of the vertex
     */
    public Vertex(String name) {
        this.name = name;
        this.isVisited = false; // this is to store whether the connection has been established
        this.edges = new HashMap<>();
    }

    /**
     * This will iterate through all the edges and find the minimum edge and next vertex connected to the Code.Vertex
     * @return: next Code.Vertex to connect and edge weight needed to connect
     */
    public Pair<Vertex, Edge> nextMinimum(){

        // At the beginning we take the max weight can occur as a value in an Edge
        Edge nextMinimum = new Edge(Integer.MAX_VALUE);
        Vertex nextVertex = this; // Initial value of the next vertex

        // Iterate through all the edges connected to this Code.Vertex
        for (Entry<Vertex, Edge> pair : this.edges.entrySet()) {

            // Check whether vertex is already visited
            if (!pair.getKey().isVisited) {

                // check whether this edge is included (since each two vertexes are bi directionally connected)
                if (!pair.getValue().isIncluded()) {

                    // This is to check whether this edge is the minimum weight edge
                    if (pair.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = pair.getValue(); // New min Edge
                        nextVertex = pair.getKey(); // next vertex to be connected
                    }
                }
            }
        }

        return new Pair<>(nextVertex, nextMinimum);

    }

    /**
     * This will create a bidirectional edge between this vertex and given vertex
     * @param vertex: vertex to create link with
     * @param edge: edge to create the link
     */
    public void addEdge(Vertex vertex, Edge edge){

        // This will check whether there is an already added link between vertexes
        // if there is, the minimal weighted link will be added
        if (this.edges.containsKey(vertex)){
            if (edge.getWeight() < this.edges.get(vertex).getWeight()){
                this.edges.replace(vertex, edge);
            }
        } else {
            this.edges.put(vertex, edge);
            // this will create the link from the other direction as well
            vertex.addEdge(this, edge);
        }



    }


    /**
     * This is used to retrieve connections for the MST
     * @return: list containing connection pairs
     */
    public List<Pair<String, Integer>> getConnections(){

        List<Pair<String, Integer>> connections = new ArrayList<>();

        if(this.isVisited){

            for (Entry<Vertex, Edge> link : edges.entrySet()) {
                // Check whether this link has been chosen
                if (link.getValue().isIncluded()) {
                    // check whether this link is already traversed through
                    if (!link.getValue().isTraverses()) {
                        int edgeWeight = link.getValue().getWeight();
                        String vertex_connection = this.getName() + link.getKey().getName();

                        connections.add(new Pair<>(vertex_connection, edgeWeight));

                        link.getValue().setTraverses(true);
                    }

                }


            }
        }

        return  connections;
    }

    public void resetTraverses(){
        for(Entry<Vertex, Edge> link : edges.entrySet()){
            link.getValue().setTraverses(false);
        }
    }

    // Getters and Setters -----------------------------------------------------

    public String getName() {
        return name;
    }


    public Map<Vertex, Edge> getEdges() {
        return edges;
    }


    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}
