package Code;

public class Edge {
    private final int weight;
    private boolean isIncluded; // this is to set whether this edge is taken to the connection
    private boolean isTraverses; // this is to check if iterator gone through this Edge when traversing

    /**
     * Use to represent Edge between two edges
     * @param weight: weight of the edge
     */
    public Edge(int weight) {
        this.weight = weight;
        this.isIncluded = false;
        this.isTraverses = false;
    }

    // Getters and Setters ----------------------------------
    public int getWeight() {
        return weight;
    }

    public boolean isIncluded() {
        return isIncluded;
    }

    public void setIncluded(boolean included) {
        isIncluded = included;
    }

    public boolean isTraverses(){
        return isTraverses;
    }

    public void setTraverses(boolean value){
        this.isTraverses = value;
    }
}
