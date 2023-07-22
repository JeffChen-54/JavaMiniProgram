import big.data.DataSource;

import java.rmi.NoSuchObjectException;
import java.util.*;
/**
 * <h1>RoadCalculator</h1>
 * The RoadCalculator class implements an application that
 * builds a graph of Nodes and Edges, and allows users to find
 * the shortest path between their two corresponding nodes.
 * <p>
 * <b>Note:</b> RoadCalculator can also build the Minimum Spanning Tree.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-11-30
 */
public class RoadCalculator {
    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst;
    private static int[][]g;
    private static String[] node;
    public static void main(String[] args) {
        System.out.println("Please enter graph URL:");
        Scanner sc = new Scanner(System.in);
        String location = sc.nextLine();
        graph = buildGraph(location);
        mst = buildMST(graph);
        while (true){
            try {
            System.out.println("\nEnter a starting point for shortest path or Q to quit:");
            String source = sc.nextLine();
            if(source.charAt(0)=='q'||source.charAt(0)=='Q'){break;}
            System.out.println("Enter a destination:");
            String destination = sc.nextLine();
            Djikstra(graph,source,destination);}
            catch (Exception e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        System.out.println("Goodbye; PSA, there's a cop on the right in 3 miles!");
    }
    /**
     * This method is used to build a graph of Nodes and Edges by calling the BigData class.
     *
     * @return HashMap<String, Node> This returns a buildGraph that can be used in main method.
     */
    public static HashMap<String, Node> buildGraph(String location){
        System.out.println("\nLoading Map...");
        HashMap<String,Node> cities = new HashMap<String,Node>();
        DataSource ds = DataSource.connectXML(location+"");
        ds.load();
        String cityNamesStr=ds.fetchString("cities");
        String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).
                replace("\"","").split(",");
        System.out.println("\nCities:\n");
        for (int i=0;i<cityNames.length;i++){
            System.out.println(cityNames[i]);
            cities.put(cityNames[i],new Node(cityNames[i]));
        }
        String roadNamesStr=ds.fetchString("roads");
        String[] roadNames=roadNamesStr.substring(2,roadNamesStr.length()-2).split("\",\"");
        System.out.println("\nRoads:\n");
        for (int i=0;i<roadNames.length;i++){
            String cityA = roadNames[i].substring(0,roadNames[i].indexOf(","));
            String rest = roadNames[i].substring(roadNames[i].indexOf(",")+1);
            String cityB = rest.substring(0,rest.indexOf(","));
            String distance = rest.substring(rest.indexOf(",")+1);
            Edge newEdge = new Edge(cities.get(cityA),cities.get(cityB),Integer.parseInt(distance));
            cities.get(cityA).getEdges().add(newEdge);
            cities.get(cityB).getEdges().add(new Edge(cities.get(cityB),cities.get(cityA),Integer.parseInt(distance)));
            System.out.println(cityA +" to "+cityB+" "+distance);
        }

        return cities;
    }
    /**
     * This method is used to build the Minimum Spanning Tree.
     *
     * @return LinkedList<Edge> This returns a LinkedList of edges of MST.
     */
    public static LinkedList<Edge> buildMST(HashMap<String, Node> graph){
        int num = graph.size()+1;
        g = new int[num-1][num-1];
        node = new String[num-1];
        for (int i=0;i<num-1;i++){
            node[i] = graph.keySet().toArray()[i].toString();
        }
        for (int i=0;i<num-1;i++){
            String city = graph.keySet().toArray()[i].toString();
            for(Edge edge:graph.get(city).getEdges()){
                for (int j=0;j<num-1;j++){
                    if(node[j].equalsIgnoreCase(edge.getB().getName())){
                        g[i][j] = edge.getCost();
                    }
                }
            }
        }
        int[] dis = new int[num-1];
        Boolean[] vis = new Boolean[num-1];
        int[] parent = new int[num-1];
        for(int i=0;i<num-1;i++){
            dis[i] = Integer.MAX_VALUE;
            vis[i] = false;
        }
        dis[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < num-1 ; count++) {
            int u = minDistance(dis, vis);
            vis[u] = true;
            for (int v = 0; v < num-1; v++){
                if (!vis[v] && g[u][v] != 0 && g[u][v] < dis[v]){
                    parent[v] = u;
                    dis[v] = g[u][v];
                }
            }
        }
        LinkedList<Edge> edges = new LinkedList<>();
        System.out.println("\nMinimum Spanning Tree:\n");
        for (int i = 1; i < num-1; i++){
            System.out.println(node[parent[i]] + " to " + node[i] + " " + g[i][parent[i]]);
            edges.add(new Edge(graph.get(node[parent[i]]),graph.get(node[i]),g[i][parent[i]]));
        }

        return edges;

    }
    /**
     * This method is used to find the index of node that has the minimum distance.
     *
     * @return int This returns the index of node.
     */
    public static int minDistance(int dis[],Boolean vis[]){
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < dis.length; v++)
            if (!vis[v] && dis[v] <= min) {
                min = dis[v];
                min_index = v;
            }
        return min_index;
    }
    /**
     * This method is used to find the minimum distance between two nodes.
     *
     * @return int This returns the minimum distance between two nodes.
     */
    public static int Djikstra(HashMap<String,Node> graph, String source, String dest){
        int[] dis = new int[graph.size()];
        Boolean[] vis = new Boolean[graph.size()];
        int[] pre = new int[graph.size()];
        int src = 0;
        int des = 0;
        boolean b1 = false, b2 = false;
        for (int i=0;i<graph.size();i++){
            if (node[i].equalsIgnoreCase(source)){
                src = i;
                b1 = true;
            }
            if (node[i].equalsIgnoreCase(dest)){
                des = i;
                b2 = true;
            }
        }
        if(!b1||!b2){
            System.out.println("Given source and destination are not connected.");
            return -999;
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (int i=0;i<graph.size();i++){
            dis[i] = Integer.MAX_VALUE;
            vis[i] = false;
            pre[i] = -1;
        }
        dis[src]=0;
        for (int count = 0; count<graph.size();count++){
            int u = minDistance(dis,vis);
            vis[u] = true;
            for (int v = 0; v<graph.size();v++){
                if (!vis[v] && g[u][v] != 0 && dis[u] != Integer.MAX_VALUE && dis[u] + g[u][v] < dis[v]) {
                    dis[v] = dis[u] + g[u][v];
                    pre[v] = u;
                }
            }
        }
        int tem = des;
        path.add(tem);
        while (pre[tem] != -1){
            path.add(pre[tem]);
            tem = pre[tem];
        }
        System.out.println("Distance: "+dis[des]);
        System.out.println("Path:");
        for (int i = path.size()-1; i >0; i--){
            System.out.print(node[path.get(i)]+", ");
        }
        System.out.println("");
        return dis[des];
    }
}
