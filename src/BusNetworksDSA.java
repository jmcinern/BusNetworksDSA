import java.sql.SQLOutput;
import java.util.Scanner;

public class BusNetworksDSA {
    //Used as Interface.
    public static void main(String[] args) {
        //Get the route the user would like to take.
        int depart = 0;
        int arrive = 0;
        int count = 1;
        String route = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome the Vancouver Bus Network, if at any time you would like to stop the program" +
                "please enter 'quit'");
        System.out.print("Depart:");

        boolean quit = false;
        while (!quit) {

            if (scan.hasNext("quit")) {
                quit = true;
                scan.next();

            } else if (scan.hasNextInt() && count %2 != 0) {
                depart = scan.nextInt();
                count++;

            } else if (scan.hasNextInt() && count % 2 == 0) {
                arrive = scan.nextInt();
                System.out.print("Arrive: ");
                count++;
                route = SP(depart,arrive);
                System.out.println(route);

            }else {
                System.out.println("Error - Enter a valid stop ID: ");
                scan.next();
            }

        }
    }

    /*1. Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops en route as
well as the associated “cost”. Stops are listed in stops.txt and connections (edges) between them come from
stop_times.txt and transfers.txt files. All lines in transfers.txt are edges (directed), while in stop_times.txt
an edge should be added only between 2 consecutive stops with the same trip_id. eg first 3 entries in stop_times.txt are
9017927, 5:25:00, 5:25:00,646,1,,0,0, 9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300 9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780
This should add a directed edge from 646 to 378, and a directed edge from 378 to 379 (as they’re on the same trip id 9017927).
Cost associated with edges should be as follows: 1 if it comes from stop_times.txt, 2 if it comes from transfers.txt with
transfer type 0 (which is immediate transfer possible), and for transfer type 2 the cost is the minimum transfer time divided by 100.
 */
    //Finding shortest paths between 2 bus stops (as input by the user)
    public static String SP(int depart, int arrive)
    {
        String route = depart+" -> "+arrive;
        return route;
    }
}
