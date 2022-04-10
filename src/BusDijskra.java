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
        this.G = new EdgeWeightedDigraph(V);
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
            while (sc.hasNextLine()) {
                // Read directed edge from file
                curLine = sc.nextLine();
                nextLine = sc.nextLine();
                values = curLine.trim().split(",");
                valuesNext = nextLine.trim().split(",");
                int curStopTrip= Integer.parseInt(values[0]);
                int nextStopTrip = Integer.parseInt(valuesNext[0]);
                int curSeq = Integer.parseInt(values[4]);
                int nextSeq = Integer.parseInt(values[4]);

                int cost = 1; //Per spec.
                if(nextSeq == curSeq+1 && curStopTrip == nextStopTrip) {
                    // Add directed edge to graph if stops are one after the other and on same bus route.
                    List<DirectedEdge> list = G.adj.getOrDefault(curStopTrip, new ArrayList<>()); // List
                    list.add(new DirectedEdge(curStopTrip, nextStopTrip, cost));
                    G.adj.put(curStopTrip, list);
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
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
            while (sc.hasNextLine()) {
                // Read directed edge from file
                curLine = sc.nextLine();
                values = curLine.trim().split(",");
                int fromStop = Integer.parseInt(values[0]);
                int toStop = Integer.parseInt(values[1]);
                int transferType = Integer.parseInt(values[2]);
                int minTransferTime = Integer.parseInt(values[3]);
                int cost = 0;
                if (transferType == 0)
                {
                    cost = 2;
                }
                if(transferType == 0)
                {
                    cost = minTransferTime / 100;
                }

                // Add directed edge to graph.
                List<DirectedEdge> list = G.adj.getOrDefault(fromStop, new ArrayList<>()); // List
                list.add(new DirectedEdge(fromStop, toStop, cost));
                G.adj.put(fromStop, list);
            }
            sc.close();

        } catch (FileNotFoundException e) {
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
                        distTo[adjNode] = newDist;
                        queue.remove(adjNode);
                        queue.add(adjNode);
                    }
                }
            }
        }
        return distTo;
    }




}
