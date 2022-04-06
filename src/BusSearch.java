import java.io.File;
import java.io.FileNotFoundException;
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
    String meaningfulString = "";

    public String makeMeaningful(String line) {
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
            System.out.println(current);
            meaningfulString = meaningfulString.concat(current);
        }
        //Return meaningful String with keyword at end.
        return meaningfulString;


    }

    //stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station
    TST stopsTST = new TST<String>();

    public void fileToTST(String fileName) {
        String curLine;
        if (fileName != null) {
            try {
                File f = new File(fileName);
                Scanner scan = new Scanner(f);
                //Start adding names from second lane in file as first line contains headings.
                int i = 1;
                while (scan.hasNextLine()) {
                    //Get line of file.
                    curLine = scan.nextLine();
                    String[] myStrings = curLine.split(",");
                    //Get the name of the stop
                    String stopName = myStrings[2];
                    //Create TST and add stop names to it.
                    stopsTST.put(stopName, i);
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


    public TST getStopsTST()
    {
        return stopsTST;
    }
}
