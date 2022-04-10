/*
    1. Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops en route as
    well as the associated “cost”.

    Stops are listed in stops.txt and connections (edges) between them come from stop_times.txt and transfers.txt files. All lines in transfers.txt are edges (directed), while in stop_times.txt an edge should be added only between 2 consecutive stops with the same trip_id.
    eg first 3 entries in stop_times.txt are
    9017927, 5:25:00, 5:25:00,646,1,,0,0, 9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300 9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780
    This should add a directed edge from 646 to 378, and a directed edge from 378 to 379 (as they’re on the same trip id 9017927).
    Cost associated with edges should be as follows: 1 if it comes from stop_times.txt, 2 if it comes from transfers.txt with transfer
    type 0 (which is immediate)
 */
//Create an edge weighted digraph with all routes.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BusDijskra {

    private int V;
    private int E;
    private EdgeWeightedDigraph G;
    public BusDijskra()
    {
        int V = 8757; //Number of stop from stops.txt.
        int E = 8757;
        this.G = new EdgeWeightedDigraph(V, E);
        stopTimesToEdges();
        transfersToEdges();

    }
    //
    public void stopTimesToEdges()
    {
        String fname = "stop_times.txt";
        String curLine;
        String nextLine;
        String[] values;
        String[] valuesNext;
        File f = new File(fname);
        try {
            Scanner sc = new Scanner(f);
            int count = 0; //Ignore first line.
            while (sc.hasNextLine()) {
                // Read directed edge from file
                String header = sc.nextLine();
                if(count >-1) { //Useful for testing.
                    curLine = sc.nextLine();
                    if(sc.hasNext()) {
                        nextLine = sc.nextLine();
                    }
                    else
                    {
                        nextLine = null;
                    }

                    if (nextLine != null) {
                        values = curLine.trim().split(",");
                        valuesNext = nextLine.trim().split(",");
                        int curStopTrip = Integer.parseInt(values[0]);
                        int nextStopTrip = Integer.parseInt(valuesNext[0]);
                        int curSeq = Integer.parseInt(values[4]);
                        int nextSeq = Integer.parseInt(valuesNext[4]);
                        int curStopID = Integer.parseInt(values[3]);
                        int nextStopID = Integer.parseInt(valuesNext[3]);
                        //Need stop IDs to create directed edge.
                        int cost = 1; //Per spec.


                        if (nextSeq == curSeq + 1 && curStopTrip == nextStopTrip) {
                            // Add directed edge to graph if stops are one after the other and on same bus route.
                            List<DirectedEdge> list = G.adj.getOrDefault(curStopID, new ArrayList<>()); // List
                            list.add(new DirectedEdge(curStopID, nextStopID, cost));
                            G.adj.put(curStopID, list);
                        }
                    }
                }
                count++;
                }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File error");
            e.printStackTrace();
        }

    }

    public void transfersToEdges()
    {
        String fname = "transfers.txt";
        String curLine;
        String[] values;
        File f = new File(fname);
        try {
            Scanner sc = new Scanner(f);
            int count=0;
            while (sc.hasNextLine()) {
                // Read directed edge from file
                String header = sc.nextLine();
                curLine = sc.nextLine();
                values = curLine.trim().split(",");
                if(count>-1) {
                    int fromStop = Integer.parseInt(values[0]);
                    int toStop = Integer.parseInt(values[1]);
                    int transferType = Integer.parseInt(values[2]);

                    int cost = 0;
                    if (transferType == 0) {
                        cost = 2;
                    }
                    if (transferType == 2) {
                        int minTransferTime = Integer.parseInt(values[3]);
                        cost = minTransferTime / 100;
                    }

                    // Add directed edge to graph.
                    List<DirectedEdge> list = G.adj.getOrDefault(fromStop, new ArrayList<>()); // List
                    list.add(new DirectedEdge(fromStop, toStop, cost));
                    G.adj.put(fromStop, list);

                }
                count++;
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File error");
            e.printStackTrace();
        }
    }

    //Dijskra algorithm to find shortest path of source vertex to all other vertices.
    public double[] DijkstraSP(int src)
    {

        boolean[] marked = new boolean[G.V];
        double[] distTo = new double[G.V];
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(vertex -> distTo[vertex]));
        Map<Integer, List<DirectedEdge>> adj = G.adj;

        for (int v = 0; v < distTo.length; v++)
        {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[src] = 0.0;
        queue.add(src);
        while (!queue.isEmpty())
        {
            int curV = queue.poll();
            marked[curV] = true;
            for (DirectedEdge adjacent : adj.getOrDefault(curV, new ArrayList<>()))
            {
                int adjNode = adjacent.w;
                //If not already visited
                if (!marked[adjNode])
                {
                    double newDist = distTo[curV] + adjacent.weight;
                    if (newDist < distTo[adjNode])
                    {
                        System.out.println("Distance is updated");
                        distTo[adjNode] = newDist;
                        queue.remove(adjNode);
                        queue.add(adjNode);
                    }
                }
            }
        }
        return distTo;
    }

    public int runSearch()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter stop ID of departure stop: ");
        int fromID = sc.nextInt();
        System.out.println("Enter stop ID of destination stop: ");
        int toID = sc.nextInt();
        if (this.G == null)
        {
            System.out.println("Null graph error");
            return -1;
        }
        //Create hashmap of graph index and stop number.
        BusDijskra bd = new BusDijskra();
        int sp = 0;
        double minDistance = 0.0;
        for (int i = 0; i < G.V; i++)
        {
            double[] dist = bd.DijkstraSP(i); //Returns an array of distances from source vertex to all other vertices.
            //Find the shortest distance to destination.
            for (int j = 0; j < G.V; j++)
            {
                double curDistance = dist[j];
                if (curDistance == Double.POSITIVE_INFINITY)
                {
                    System.out.println("Max value reached");
                    return -1;

                }
            }
        }
        sp = (int) minDistance;
        System.out.println("Shortest path: "+sp);
        return sp;
    }
}
