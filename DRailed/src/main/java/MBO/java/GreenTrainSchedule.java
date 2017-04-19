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
    private final SimpleIntegerProperty id;

    // Stations
    private final SimpleStringProperty glenburyA;
    private final SimpleStringProperty dormontA;
    private final SimpleStringProperty mtLebanon;
    private final SimpleStringProperty poplar;
    private final SimpleStringProperty castleShannon;
    private final SimpleStringProperty dormontB;
    private final SimpleStringProperty glenburyB;
    private final SimpleStringProperty overbrookA;
    private final SimpleStringProperty inglewoodA;
    private final SimpleStringProperty centralA;
    private final SimpleStringProperty pioneer;
    private final SimpleStringProperty edgebrook;
    private final SimpleStringProperty whited;
    private final SimpleStringProperty university;
    private final SimpleStringProperty southBank;
    private final SimpleStringProperty centralB;
    private final SimpleStringProperty inglewoodB;
    private final SimpleStringProperty overbrookB;

    private ArrayList<Station> displaySchedule;
    private LinkedList<Station> stationsSchedule;
    private long delay = 0;
    private long[] stationDelays = {132, 150, 132, 264, 132, 138, 142, 126, 120, 120, 138, 138, 142, 162, 156, 114, 120, 120};
    private int trackTime = 2546;

    public GreenTrainSchedule(int id, LocalTime start) {
        displaySchedule = new ArrayList<>();

        long sum = 0;
        for(int i = 0; i < stationDelays.length ; i++) {
            sum += stationDelays[i];
            displaySchedule.add(new Station(i + 1, start.plusSeconds(sum), LocalTime.of(start.getHour(), start.getMinute() + 1, start.getSecond()).plusSeconds(sum)));
        }

        this.id = new SimpleIntegerProperty(id);
        this.glenburyA = new SimpleStringProperty(displaySchedule.get(0).toString());
        this.dormontA = new SimpleStringProperty(displaySchedule.get(1).toString());
        this.mtLebanon = new SimpleStringProperty(displaySchedule.get(2).toString());
        this.poplar = new SimpleStringProperty(displaySchedule.get(3).toString());
        this.castleShannon = new SimpleStringProperty(displaySchedule.get(4).toString());
        this.dormontB = new SimpleStringProperty(displaySchedule.get(5).toString());
        this.glenburyB = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.overbrookA = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.inglewoodA = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.centralA = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.pioneer = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.edgebrook = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.whited = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.university = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.southBank = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.centralB = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.inglewoodB = new SimpleStringProperty(displaySchedule.get(6).toString());
        this.overbrookB = new SimpleStringProperty(displaySchedule.get(6).toString());
    }

    public String getDeparture(int station) {
        return displaySchedule.get(station - 1).departure.toString();
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