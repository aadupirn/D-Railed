package MBO.java;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by joero on 4/17/2017.
 */
public class RedTrainSchedule {
    private final SimpleIntegerProperty id;

    // Stations
    private final SimpleStringProperty herron;
    private final SimpleStringProperty swissville;
    private final SimpleStringProperty pennStation;
    private final SimpleStringProperty steelPlaza;
    private final SimpleStringProperty firstAve;
    private final SimpleStringProperty southHills;
    private final SimpleStringProperty shadyside;

    private ArrayList<Station> displaySchedule;
    private LinkedList<Station> stationsSchedule;
    private long delay = 0;
    private long[] stationDelays = {138, 90, 108, 126, 126, 102, 138, 222};
    private int trackTime = 1050;

    public RedTrainSchedule(int id, LocalTime start) {
        displaySchedule = new ArrayList<>();

        long sum = 0;
        for(int i = 0; i < stationDelays.length ; i++){
            sum += stationDelays[i];
            displaySchedule.add(new Station(i + 1, start.plusSeconds(sum), LocalTime.of(start.getHour(), start.getMinute() + 1, start.getSecond()).plusSeconds(sum)));
        }

        this.id = new SimpleIntegerProperty(id);
        this.herron = new SimpleStringProperty(displaySchedule.get(0).toString());
        this.swissville = new SimpleStringProperty(displaySchedule.get(1).toString());
        this.pennStation = new SimpleStringProperty(displaySchedule.get(2).toString());
        this.steelPlaza = new SimpleStringProperty(displaySchedule.get(3).toString());
        this.firstAve = new SimpleStringProperty(displaySchedule.get(4).toString());
        this.southHills = new SimpleStringProperty(displaySchedule.get(5).toString());
        this.shadyside = new SimpleStringProperty(displaySchedule.get(6).toString());
    }

    public String getDeparture(int station) {
        return displaySchedule.get(station - 1).departure.toString();
    }

    public int getId(){ return id.get(); }
    public void setId(int id){ this.id.set(id); }

    // Needed to update UI
    public SimpleIntegerProperty idProperty() { return id; }
    public SimpleStringProperty herronProperty() { return herron; }
    public SimpleStringProperty swissvilleProperty() { return swissville; }
    public SimpleStringProperty pennStationProperty() { return pennStation; }
    public SimpleStringProperty steelPlazaProperty() { return steelPlaza; }
    public SimpleStringProperty firstAveProperty() { return firstAve; }
    public SimpleStringProperty southHillsProperty() { return southHills; }
    public SimpleStringProperty shadysideProperty() { return shadyside; }
}