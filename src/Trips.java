import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
3. Searching for all trips with a given arrival time, returning full details of all trips matching the criteria
(zero, one or more), sorted by trip id
Arrival time should be provided by the user as hh:mm:ss. When reading in stop_times.txt file you will need to remove
all invalid times, e.g., there are times in the file that start at 27/28 hours, so are clearly invalid. Maximum time allowed is 23:59:59.
 */
public class Trips {
    HashMap ID_time = new HashMap(); //Store times and stop IDs
    HashMap ID_info = new HashMap(); //Store info and stop ID

    public void runSearch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What time would you like to arrive at? (hh:mm:ss)");
        String timeIn = sc.next();
        Trips ts = new Trips();
        ts.fileToHash("stop_times.txt");
        HashMap ID_time_run = ts.getID_time();
        HashMap ID_info_run = ts.getID_info();
        //Return stop IDs that match time in ascending order of ID



    }

    public void fileToHash(String fileName) {
        String curLine;
        if (fileName != null) {
            try {
                File f = new File(fileName);
                Scanner scan = new Scanner(f);

                int i = 0;
                while (scan.hasNextLine()) {
                    //Get line of file.
                    curLine = scan.nextLine();

                    if (i != 0) {
                        StopTime curStop = new StopTime(curLine);   //Create stop object from row in stops.txt
                        String[] myStrings = curLine.split(",");
                        //Get the name of the stop
                        String arrivalTime = myStrings[1];
                        //Check if time is valid.
                        if(isValidTime(arrivalTime))
                        {
                            ID_time.put(curStop.getStop_time_ID(), arrivalTime);
                            ID_info.put(curStop.getStop_time_ID(), curStop.getStop_time_info());
                        }
                    }
                    i++;
                }
                scan.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                return;
            }
        }
    }
    public HashMap getID_time()
    {
        return  this.ID_time;
    }
    public HashMap getID_info()
    {
        return this.ID_info;
    }
    public boolean isValidTime(String time)
    {
        time = time.replaceAll(" ", "");//remove whitespace.
        boolean isValid = false;
        String[] timeArr = time.split(":");
        if( Integer.parseInt(timeArr[0]) < 24 && Integer.parseInt(timeArr[1]) < 60 && Integer.parseInt(timeArr[2]) < 60 )
        {
            isValid = true;
        }
        return isValid;
    }
}
