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
import java.io.IOException;
import java.util.Scanner;

public class busDijskra {

    private int V;
    private int E;
    private EdgeWeightedDigraph G;
    public busDijskra()
    {
        try {
            String[] values;
            String curLine;
            Scanner sc = new Scanner(System.in);

            V = 8757; // Number of lines in stops.txt
            int tEdges = 5_083; //Number of lines in transfers.txt
            int stEdges = 0; //Can't read number of edges in stop_times.txt.
            while ()


        }catch (IOException e)
        {
            this.G = null;
        }


    }


}
