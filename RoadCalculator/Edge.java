/**
 * <h1>Edge</h1>
 * The Edge class includes the information of edge.
 *
 * <p>
 * <b>Note:</b> Edge class includes the information of nodeA, nodeB and cost.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-30
 */
public class Edge {
    private Node A;
    private Node B;
    private int cost;
    public Edge(Node a,Node b,int c){
        A = a;
        B = b;
        cost = c;
    }
    /**
     * This method is a getter which get the information of Node A.
     *
     * @return Node This returns Node A.
     */
    public Node getA() {
        return A;
    }
    /**
     * This method is a setter which sets a Node to Node A.
     *
     */
    public void setA(Node a) {
        A = a;
    }
    /**
     * This method is a getter which get the information of Node B.
     *
     * @return Node This returns Node B.
     */
    public Node getB() {
        return B;
    }
    /**
     * This method is a setter which sets a Node to Node B.
     *
     */
    public void setB(Node b) {
        B = b;
    }
    /**
     * This method is a getter which get the cost of edge.
     *
     * @return int This returns the cost of edge.
     */
    public int getCost() {
        return cost;
    }
    /**
     * This method is a setter which sets the cost of edge.
     *
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
    /**
     * This method is used to get a String that can be used to print the information of edge.
     *
     * @return String This returns a String that includes the information of A, B and cost.
     */
    public String toString(){
        return A.toString()+" to "+B.toString()+" "+cost;
    }
    /**
     * This method is used to compare another edge.
     *
     * @return int This returns -1 if the current edge’s cost is less than otherEdge’s cost,
     * 0 if equal, and 1 if greater than.
     */
    public int compareTo(Object otherEdge){
        if(cost < ((Edge)otherEdge).cost) return -1;
        else if(cost == ((Edge)otherEdge).cost) return 0;
        else return 1;
    }
}
