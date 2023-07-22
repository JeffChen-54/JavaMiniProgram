import java.util.*;
/**
 * <h1>Node</h1>
 * The Node class includes the information of nodes.
 *
 * <p>
 * <b>Note:</b> Node class includes the information of name, edges, visited and path.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-30
 */
public class Node {
    private String name;
    private HashSet<Edge> edges = new HashSet<>();
    private boolean visited = false;
    private LinkedList<String> path = new LinkedList<>();
    private int distance;
    public Node(String name){
        this.name = name;
    }
    /**
     * This method is a getter which get the name.
     *
     * @return Node This returns name.
     */
    public String getName() {
        return name;
    }
    /**
     * This method is a setter which sets name.
     *
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This method is a getter which get the edges.
     *
     * @return HashSet<Edge> This returns the hashset of edges.
     */
    public HashSet<Edge> getEdges() {
        return edges;
    }
    /**
     * This method is a setter which sets a HashSet of edges.
     *
     */
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }
    /**
     * This method is a getter which get the distance.
     *
     * @return int This returns the value of distance.
     */
    public int getDistance() {
        return distance;
    }
    /**
     * This method is a setter which sets distance.
     *
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    /**
     * This method is a getter which get the LinkedList of path.
     *
     * @return LinkedList<String> This returns the LinkedList of path.
     */
    public LinkedList<String> getPath() {
        return path;
    }
    /**
     * This method is a setter which sets the LinkedList of path.
     *
     */
    public void setPath(LinkedList<String> path) {
        this.path = path;
    }
    /**
     * This method is a getter which get the information whether the node visited or not.
     *
     * @return boolean This returns true or false indicates if the node is visited.
     */
    public boolean getVisited(){
        return visited;
    }
    /**
     * This method is a setter which sets if the node is visited.
     *
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
