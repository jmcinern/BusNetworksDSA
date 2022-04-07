import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BusSearch {

    /*
    2. Searching for a bus stop by full name or by the first few characters in the name, using a ternary search tree (TST),
    returning the full stop information for each stop matching the search criteria (which can be zero, one or more stops)
    In order for this to provide meaningful search functionality please move keywords flagstop, wb, nb, sb, eb from start
    of the names to the end of the names of the stops when reading the file into a TST (eg “WB HASTINGS ST FS HOLDOM AVE”
     becomes “HASTINGS ST FS HOLDOM AVE WB”)
     */

    /*
    provide meaningful search functionality, move keywords flagstop, wb, nb, sb, eb from start
    of the names to the end of the names of the stops when reading the file into a TST (eg “WB HASTINGS ST FS HOLDOM AVE”
     becomes “HASTINGS ST FS HOLDOM AVE WB”)
     */


    public String makeMeaningful(String line) {
        String meaningfulString = "";
        String[] myStrings = line.split("\\s+");
        //Get keyword
        String keyWord = myStrings[0];
        //Add key word to end with space
        line = line.concat(" ");
        line = line.concat(keyWord);
        //Create new string array with key word at start and end.
        String[] myStrings2 = line.split("\\s+");

        //Add all strings except the keyword that is still at the start.
        for (int i = 1; i < myStrings2.length; i++) {
            String current = myStrings2[i];
            meaningfulString = meaningfulString.concat(current);
        }
        //Return meaningful String with keyword at end.
        return meaningfulString;
    }

    //stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station
    TST stopsTST = new TST<String>();
    HashMap stopsMap = new HashMap();
    public void fileToTST(String fileName) {
        String curLine;
        if (fileName != null) {
            try {
                File f = new File(fileName);
                Scanner scan = new Scanner(f);

                int i = 0;
                while (scan.hasNextLine()) {
                    //Get line of file.
                    curLine = scan.nextLine();

                    if(i!=0) {
                        Stop curStop = new Stop(curLine);   //Create stop object from row in stops.txt

                        String[] myStrings = curLine.split(",");
                        //Get the name of the stop
                        String stopName = myStrings[2];
                        stopName = makeMeaningful(stopName);
                        //Store stop names with their ID as the key.
                        this.stopsTST.put(stopName, curStop.getID() );
                    }
                        i++;
                }
                scan.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                return;
            }
        } else {
            return;
        }

    }

    public String getStopInfo(String stopName)
    {
        String stopInfo = "";

        return stopInfo;
    }


    public TST getStopsTST()
    {
        return this.stopsTST;
    }
}
