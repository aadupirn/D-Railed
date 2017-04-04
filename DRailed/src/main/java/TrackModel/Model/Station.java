package TrackModel.Model;

import java.util.Random;

/**
 * Created by adzun_000 on 1/20/2017.
 */
public class Station {

    private int stationNumber;
    private String stationName;
    private int departing;
    private Heater heater;
    private String trainTimes;
    private Beacon beacon;
    private String leftSection;
    private String rightSection;

    /***
     * Create a station with a randomly generated occupancy
     *
     * @param stationName - name of the station
     */
    public Station(String stationName, String from){
        this.stationName = stationName;
        this.departing = generateDeparting();
        this.leftSection = from;
        this.rightSection = null;
        this.heater = new Heater(stationName);
    }

    /***
     * Create a station with a custom occupancy
     *
     * @param stationName - name of the station
     * @param departing - departing to set to set
     */
    public Station(String stationName, int departing, String from){
        this.stationName = stationName;
        this.departing = departing;
        this.leftSection = from;
        this.rightSection = null;
    }

    public int getStationNumber(){ return stationNumber; }

    public void setStationNumber(int stationNumber){ this.stationNumber = stationNumber; }

    /***
     * @return the name of the station
     */
    public String getStationName() {
        return stationName;
    }

    /***
     * Manually set the station name associated with this station
     *
     * @param stationName - the name to set
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getDeparting() {
        return departing;
    }

    public void setDeparting(int departing) {
        this.departing = departing;
    }

    public int depart(){
        int depart = this.departing;
        this.departing = generateDeparting();
        return depart;
    }

    public void addSectionConnection(String section){
        if(leftSection == null){
            leftSection = section;
        }else if(rightSection == null){
            rightSection = section;
        }
    }

    /***
     * Randomly generates an occupancy for a station
     */
    public int generateOccupancy() {
        return new Random().nextInt(1000);
    }

    public int generateDeparting() {
        return new Random().nextInt(222 - 74) + 74;
    }

    public Heater getHeater() {
        return heater;
    }

    public void setHeater(Heater heater) {
        this.heater = heater;
    }

    public void setTrainTimes(String time){
        this.trainTimes = time;
    }

    public String getTrainTimes(){
        return trainTimes;
    }
}
