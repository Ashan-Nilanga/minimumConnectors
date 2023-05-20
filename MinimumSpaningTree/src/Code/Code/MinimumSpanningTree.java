package Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


public class MinimumSpanningTree {
    private final List<Vertex> graph;

    public MinimumSpanningTree(List<Vertex> graph) {
        this.graph = graph;
    }

    /**
     * This will update the graph such that it is a Minimum spanning tree
     * @param root: starting vertex to create MST
     */
    public void run(int root){
        if(this.graph.size() > 0){
            this.graph.get(root).setVisited(true); // set the first Code.Vertex as visited
        }
        while(!isConnected()){
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Vertex nextVertex = graph.get(0);

            // this is the loop to get the minimum connection from all the visited vertexes
            for(Vertex vertex: graph){
                if(vertex.isVisited()){

                    // getting the next minimum connection
                    Entry<Vertex, Edge> potentialMinPath = vertex.nextMinimum();

                    if(potentialMinPath.getValue().getWeight() < nextMinimum.getWeight()){
                        nextMinimum = potentialMinPath.getValue();
                        nextVertex = potentialMinPath.getKey();
                    }
                }
            }

            // Set the selected edge as included and vertex as visited
            nextMinimum.setIncluded(true);
            nextVertex.setVisited(true);

        }
    }

    /**
     * This will check whether all the Vertexes in the graph is connected
     * @return: boolean: true if Vertexes in the graph is connected(Visited)
     */
    private boolean isConnected(){
        for (Vertex vertex: this.graph){
            if(!vertex.isVisited()){
                return false;
            }
        }
        return true;
    }

    /**
     * used to get all the connectors to create the MST in the graph
     * @return: ArrayList containing edge and edge weights
     */
    private List<Pair<String, Integer>> getConnectorPair(){

        List<Pair<String, Integer>> returnList = new ArrayList<>();

        for (Vertex vertex : this.graph) {
            returnList.addAll(vertex.getConnections());
        }

        return returnList;

    }

    /**
     * Get edges in the MST
     * @return: ArrayList containing string representation of Edges
     */
    public List<String> getMinimumConnectors(){
        List<Pair<String, Integer>> connection_list = getConnectorPair();
        List<String> return_list = new ArrayList<>();

        for(Pair<String, Integer> pair: connection_list){
            return_list.add(pair.getKey());
        }

        // We need to reset all traversed for future traversing
        resetAllTraversed();
        return return_list;
    }

    /**
     * Get minimum distance of the MST
     * @return: Total Minimum distance of the MST
     */
    public int getMinimumDistance(){
        List<Pair<String, Integer>> connection_list = getConnectorPair();

        int sum = 0;

        for(Pair<String, Integer> pair: connection_list){
            sum += pair.getValue();
        }

        // we need to reset all traversed for future traversing
        resetAllTraversed();
        return sum;

    }

    public void resetAllTraversed(){
        for(Vertex vertex: graph){
            vertex.resetTraverses();
        }
    }



}
