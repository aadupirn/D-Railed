package MBO.java;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by joero on 2/6/2017.
 */

/**
 * Created by joero on 4/17/2017.
 */
public class GreenTrainSchedule {
    private SimpleIntegerProperty id;

    // Stations
    private SimpleStringProperty glenburyA;
    private SimpleStringProperty dormontA;
    private SimpleStringProperty mtLebanon;
    private SimpleStringProperty poplar;
    private SimpleStringProperty castleShannon;
    private SimpleStringProperty dormontB;
    private SimpleStringProperty glenburyB;
    private SimpleStringProperty overbrookA;
    private SimpleStringProperty inglewoodA;
    private SimpleStringProperty centralA;
    private SimpleStringProperty pioneer;
    private SimpleStringProperty edgebrook;
    private SimpleStringProperty whited;
    private SimpleStringProperty university;
    private SimpleStringProperty southBank;
    private SimpleStringProperty centralB;
    private SimpleStringProperty inglewoodB;
    private SimpleStringProperty overbrookB;

    private ArrayList<Station> displaySchedule;
    private LinkedList<Station> stationsSchedule;
    private long delay = 0;
    private long[] stationDelays = {132, 150, 132, 264, 132, 138, 142, 126, 120, 120, 138, 138, 142, 162, 156, 114, 120, 120};
    private int trackTime = 2546;

    private void prepUIProperties() {
        glenburyA = new SimpleStringProperty(displaySchedule.get(0).toString());
        dormontA = new SimpleStringProperty(displaySchedule.get(1).toString());
        mtLebanon = new SimpleStringProperty(displaySchedule.get(2).toString());
        poplar = new SimpleStringProperty(displaySchedule.get(3).toString());
        castleShannon = new SimpleStringProperty(displaySchedule.get(4).toString());
        dormontB = new SimpleStringProperty(displaySchedule.get(5).toString());
        glenburyB = new SimpleStringProperty(displaySchedule.get(6).toString());
        overbrookA = new SimpleStringProperty(displaySchedule.get(6).toString());
        inglewoodA = new SimpleStringProperty(displaySchedule.get(6).toString());
        centralA = new SimpleStringProperty(displaySchedule.get(6).toString());
        pioneer = new SimpleStringProperty(displaySchedule.get(6).toString());
        edgebrook = new SimpleStringProperty(displaySchedule.get(6).toString());
        whited = new SimpleStringProperty(displaySchedule.get(6).toString());
        university = new SimpleStringProperty(displaySchedule.get(6).toString());
        southBank = new SimpleStringProperty(displaySchedule.get(6).toString());
        centralB = new SimpleStringProperty(displaySchedule.get(6).toString());
        inglewoodB = new SimpleStringProperty(displaySchedule.get(6).toString());
        overbrookB = new SimpleStringProperty(displaySchedule.get(6).toString());
    }

    private void updateUI() {
        for (Station s : displaySchedule)
            s.updateTimes(delay);

        glenburyA.set(displaySchedule.get(0).toString());
        dormontA.set(displaySchedule.get(1).toString());
        mtLebanon.set(displaySchedule.get(2).toString());
        poplar.set(displaySchedule.get(3).toString());
        castleShannon.set(displaySchedule.get(4).toString());
        dormontB.set(displaySchedule.get(5).toString());
        glenburyB.set(displaySchedule.get(6).toString());
        overbrookA.set(displaySchedule.get(7).toString());
        inglewoodA.set(displaySchedule.get(8).toString());
        centralA.set(displaySchedule.get(9).toString());
        pioneer.set(displaySchedule.get(10).toString());
        edgebrook.set(displaySchedule.get(11).toString());
        whited.set(displaySchedule.get(12).toString());
        university.set(displaySchedule.get(13).toString());
        southBank.set(displaySchedule.get(14).toString());
        centralB.set(displaySchedule.get(15).toString());
        inglewoodB.set(displaySchedule.get(16).toString());
        overbrookB.set(displaySchedule.get(17).toString());
    }

    public void updateSchedule() {
        for (Station s : stationsSchedule)
            s.updateTimes(delay);

        updateUI();
        delay = 0;
    }

    public String getDeparture(int station) {
        return displaySchedule.get(station - 1).departure.toString();
    }

    public GreenTrainSchedule(int id, LocalTime start) {
        stationsSchedule = new LinkedList<>();

        for(int i = 0; i < stationDelays.length ; i++)
            stationsSchedule.add(new Station(i + 1, start.plusSeconds(stationDelays[i]), LocalTime.of(start.getHour(), start.getMinute() + 1, start.getSecond())));

        displaySchedule = new ArrayList<>(7);
        updateUI();
    }

    // NEEDED FOR UI UPDATES
    public SimpleIntegerProperty idProperty() { return id; }
    public SimpleStringProperty glenburyAProperty() { return glenburyA; }
    public SimpleStringProperty dormontAProperty() { return dormontA; }
    public SimpleStringProperty mtLebanonProperty() { return mtLebanon; }
    public SimpleStringProperty poplarProperty() { return poplar; }
    public SimpleStringProperty castleShannonProperty() { return castleShannon; }
    public SimpleStringProperty dormontBProperty() { return dormontB; }
    public SimpleStringProperty glenburyBProperty() { return glenburyB; }
    public SimpleStringProperty overbrookAProperty() { return overbrookA; }
    public SimpleStringProperty inglewoodAProperty() { return inglewoodA; }
    public SimpleStringProperty centralAProperty() { return centralA; }
    public SimpleStringProperty pionerProperty() { return pioneer; }
    public SimpleStringProperty edgebrookProperty() { return edgebrook; }
    public SimpleStringProperty universityProperty() { return university; }
    public SimpleStringProperty whitedProperty() { return whited; }
    public SimpleStringProperty southBankProperty() { return southBank; }
    public SimpleStringProperty centralBProperty() { return centralB; }
    public SimpleStringProperty inglewoodBProperty() { return inglewoodB; }
    public SimpleStringProperty overbrookBProperty() { return overbrookB; }
}