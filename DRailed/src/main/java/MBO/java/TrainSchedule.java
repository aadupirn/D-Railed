package MBO.java;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by joero on 2/6/2017.
 */
public class TrainSchedule {
    private SimpleIntegerProperty id;
    private SimpleStringProperty station2;
    private SimpleStringProperty station9;
    private SimpleStringProperty station16;
    private SimpleStringProperty station22;
    private SimpleStringProperty station31;
    private SimpleStringProperty station39;
    private SimpleStringProperty station48;
    private SimpleStringProperty station57;
    private SimpleStringProperty station65;
    private SimpleStringProperty station73;
    private SimpleStringProperty station77;
    private SimpleStringProperty station88;
    private SimpleStringProperty station96;
    private SimpleStringProperty station105;
    private SimpleStringProperty station114;
    private SimpleStringProperty station123;
    private SimpleStringProperty station132;
    private SimpleStringProperty station141;

    private ArrayList<LocalDateTime> stationSchedule;

    private long[] stationDelays = {138, 138, 144, 162, 156, 114, 120, 120, 132, 150, 132, 264, 132, 138, 144, 126, 120, 120};

    public TrainSchedule(int id, LocalDateTime time) {
        stationSchedule = new ArrayList<LocalDateTime>(Collections.nCopies(18, null));
        stationSchedule.set(7, time);

        for(int i = 0; i < 18; i++)
            stationSchedule.set((i + 8) % 18, stationSchedule.get((i + 7) % 18).plusSeconds(stationDelays[(i + 8) % 18]));

        this.id = new SimpleIntegerProperty(id);
        prepUIProperties();
    }

    private void prepUIProperties() {
        station2 = new SimpleStringProperty(stationSchedule.get(0).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station9 = new SimpleStringProperty(stationSchedule.get(1).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station16 = new SimpleStringProperty(stationSchedule.get(2).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station22 = new SimpleStringProperty(stationSchedule.get(3).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station31 = new SimpleStringProperty(stationSchedule.get(4).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station39 = new SimpleStringProperty(stationSchedule.get(5).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station48 = new SimpleStringProperty(stationSchedule.get(6).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station57 = new SimpleStringProperty(stationSchedule.get(7).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station65 = new SimpleStringProperty(stationSchedule.get(8).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station73 = new SimpleStringProperty(stationSchedule.get(9).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station77 = new SimpleStringProperty(stationSchedule.get(10).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station88 = new SimpleStringProperty(stationSchedule.get(11).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station96 = new SimpleStringProperty(stationSchedule.get(12).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station105 = new SimpleStringProperty(stationSchedule.get(13).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station114 = new SimpleStringProperty(stationSchedule.get(14).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station123 = new SimpleStringProperty(stationSchedule.get(15).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station132 = new SimpleStringProperty(stationSchedule.get(16).format(DateTimeFormatter.ISO_LOCAL_TIME));
        station141 = new SimpleStringProperty(stationSchedule.get(17).format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    public String getDeparture(int id) {
        LocalDateTime now = LocalDateTime.now();
        if(now.compareTo(stationSchedule.get(id - 1).minusMinutes(1)) == 1)
            updateSchedule(id, now);

        return stationSchedule.get(id - 1).format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    private void updateSchedule(int id, LocalDateTime arrival) {
        stationSchedule.set((id - 1) % 18, arrival.plusMinutes(1));
        for(int i = 0; i < 18; i++) {
            stationSchedule.set((i + id - 1) % 18, stationSchedule.get((i + id - 2) % 18).plusSeconds(stationDelays[(i + id - 1) % 18]));
        }
    }

    // NEEDED FOR UI UPDATES
    public SimpleIntegerProperty idProperty() { return id; }
    public SimpleStringProperty station2Property() { return station2; }
    public SimpleStringProperty station9Property() { return station9; }
    public SimpleStringProperty station16Property() { return station16; }
    public SimpleStringProperty station22Property() { return station22; }
    public SimpleStringProperty station31Property() { return station31; }
    public SimpleStringProperty station39Property() { return station39; }
    public SimpleStringProperty station48Property() { return station48; }
    public SimpleStringProperty station57Property() { return station57; }
    public SimpleStringProperty station65Property() { return station65; }
    public SimpleStringProperty station73Property() { return station73; }
    public SimpleStringProperty station77Property() { return station77; }
    public SimpleStringProperty station88Property() { return station88; }
    public SimpleStringProperty station96Property() { return station96; }
    public SimpleStringProperty station105Property() { return station105; }
    public SimpleStringProperty station114Property() { return station114; }
    public SimpleStringProperty station123Property() { return station123; }
    public SimpleStringProperty station132Property() { return station132; }
    public SimpleStringProperty station141Property() { return station141; }
}
