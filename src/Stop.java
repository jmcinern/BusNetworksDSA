public class Stop {

    int stop_id = 0;
    String info = ""; //Other information about stop.

    //Costructor
    public Stop(String line)
    {
        String[] myStrings = line.split(",");
        this.stop_id = Integer.parseInt(myStrings[0]);
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

    public int getID()
    {
        return this.stop_id;
    }

    public String getInfo()
    {
        return this.info;
    }


}
