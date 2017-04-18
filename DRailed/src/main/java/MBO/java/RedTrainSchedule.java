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
    private SimpleIntegerProperty id;

    // Stations
    private SimpleStringProperty herron;
    private SimpleStringProperty swissville;
    private SimpleStringProperty pennStation;
    private SimpleStringProperty steelPlaza;
    private SimpleStringProperty firstAve;
    private SimpleStringProperty southHills;
    private SimpleStringProperty shadyside;

    private ArrayList<Station> displaySchedule;
    private LinkedList<Station> stationsSchedule;
    private long delay = 0;
    private long[] stationDelays = {};

    private void prepUIProperties() {
        herron = new SimpleStringProperty(displaySchedule.get(0).toString());
        swissville = new SimpleStringProperty(displaySchedule.get(1).toString());
        pennStation = new SimpleStringProperty(displaySchedule.get(2).toString());
        steelPlaza = new SimpleStringProperty(displaySchedule.get(3).toString());
        firstAve = new SimpleStringProperty(displaySchedule.get(4).toString());
        southHills = new SimpleStringProperty(displaySchedule.get(5).toString());
        shadyside = new SimpleStringProperty(displaySchedule.get(6).toString());
    }

    private void updateUI() {
        for (Station s : displaySchedule)
            s.updateTimes(delay);

        herron.set(displaySchedule.get(0).toString());
        swissville.set(displaySchedule.get(1).toString());
        pennStation.set(displaySchedule.get(2).toString());
        steelPlaza.set(displaySchedule.get(3).toString());
        firstAve.set(displaySchedule.get(4).toString());
        southHills.set(displaySchedule.get(5).toString());
        shadyside.set(displaySchedule.get(6).toString());
    }

    public void updateSchedule() {
        for (Station s : stationsSchedule)
            s.updateTimes(delay);

        updateUI();
        delay = 0;
    }

    public RedTrainSchedule(int id, LocalTime start, LocalTime end) {
        int i = 0;
        stationsSchedule = new LinkedList<>();

        while (start.compareTo(end) == -1) {
            stationsSchedule.add(new Station(i + 1, start, LocalTime.of(start.getHour(), start.getMinute() + 1, start.getSecond())));
            start.plusSeconds(stationDelays[i]);
            i = (i + 1) % 7;
        }

        displaySchedule = new ArrayList<>(7);
        updateUI();
    }

    public String getDeparture(int station) {
        return displaySchedule.get(station - 1).departure.toString();
    }

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