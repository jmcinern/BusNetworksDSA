import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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

    public void runSearch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What time would you like to arrive at?: ");
        String timeIn = sc.next();
        //If time inputed is valid
        if (isValidTime(timeIn))
        {
            //Return all stops that arrive at this time.
            System.out.println("valid time: " + timeIn);
            String fname = "stop_times.txt";
            printStopsWithTime(fname, timeIn);
        }
        else {
            System.out.println("Invalid time");
        }
    }

    public boolean isValidTime(String time)
    {
        boolean isValid = false;
        try {
            LocalTime.parse(time);
            isValid = true;
        } catch (DateTimeParseException | NullPointerException e) {
            isValid = false;
        }
        return isValid;
    }

    public void printStopsWithTime(String fname, String timeIn)
    {
        String curLine;
        if (fname != null) {
            try {
                File f = new File(fname);
                Scanner scan = new Scanner(f);

                int i = 0;
                while (scan.hasNextLine()) {
                    //Get line of file.
                    curLine = scan.nextLine();
                    if(i!=0) {
                        StopTime curStop = new StopTime(curLine);   //Create stop object from row in stops.txt
                        String[] myStrings = curLine.split(",");
                        String arrivalTime = myStrings[1];

                        if (arrivalTime.equals(timeIn))
                        {
                            System.out.println("Stop ID: "+ curStop.stop_time_ID);
                            System.out.println("Stop info: "+ curStop.getStop_time_info());
                        }

                    }
                    i++;
                }
                scan.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");

            }
        } else {

        }
    }

}
