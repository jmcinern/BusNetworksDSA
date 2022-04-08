public class StopTime {

    public String info = "";
    public int stop_time_ID;
    public StopTime(String line) {

        String[] myStrings = line.split(",");
        this.stop_time_ID = Integer.parseInt(myStrings[0]);
        for (int i = 1; i < myStrings.length; i++)
        {
            String curInfo = myStrings[i];
            if(!curInfo.isEmpty()) {
                //Seperate each type of info by comma.
                this.info = this.info.concat(curInfo);
                if (i<myStrings.length-1) {
                    this.info = this.info.concat(",");
                }
            }
        }


    }

    public  int getStop_time_ID()
    {
        return this.stop_time_ID;
    }
    public String getStop_time_info()
    {
        return this.info;
    }

}
