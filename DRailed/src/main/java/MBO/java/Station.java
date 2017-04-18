package MBO.java;

import java.time.LocalTime;

/**
 * Created by joero on 4/17/2017.
 */
public class Station {
    public int id;
    public LocalTime arrival;
    public LocalTime departure;

    public Station(int id, LocalTime arrival, LocalTime departure){
        this.id = id;
        this.arrival = arrival;
        this.departure = departure;
    }

    public void updateTimes(long delay){
        arrival.plusSeconds(delay);
        departure.plusSeconds(delay);
    }

    public String toString(){
        return arrival.toString() + " | " + departure.toString();
    }
}
