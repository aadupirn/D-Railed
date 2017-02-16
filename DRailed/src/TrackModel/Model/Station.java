package TrackModel.Model;

import java.util.Random;

/**
 * Created by adzun_000 on 1/20/2017.
 */
public class Station {

    private String stationName;
    private int occupancy;
    private int departing;
    private Heater heater;
    private String fromSection;

    /***
     * Create a station with a randomly generated occupancy
     *
     * @param stationName - name of the station
     */
    public Station(String stationName, String from){
        this.stationName = stationName;
        this.occupancy = generateOccupancy();
        this.departing = generateDeparting();
        this.fromSection = from;
        this.heater = new Heater(stationName);
    }

    /***
     * Create a station with a custom occupancy
     *
     * @param stationName - name of the station
     * @param occupancy - occupancy to set
     */
    public Station(String stationName, int occupancy, String from){
        this.stationName = stationName;
        this.occupancy = occupancy;
        this.fromSection = from;
    }

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

    /***
     * @return the occupancy of a station
     */
    public Integer getOccupancy() {
        return occupancy;
    }

    /***
     * Manually set an occupancy for a station
     *
     * @param occupancy - occupancy to set
     */
    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
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
}
