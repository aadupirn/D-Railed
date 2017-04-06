package MBO.java;

import javafx.beans.property.SimpleStringProperty;
import sun.rmi.runtime.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by joero on 2/6/2017.
 */
public class TrainSchedule {
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

    public TrainSchedule(LocalDateTime time) {
        stationSchedule = new ArrayList<LocalDateTime>(Collections.nCopies(18, null));
        stationSchedule.set(7, time);

        for(int i = 0; i < 18; i++)
            stationSchedule.set((i + 8) % 18, stationSchedule.get((i + 7) % 18).plusSeconds(stationDelays[(i + 8) % 18]));

        System.out.print(stationSchedule.toString());
        prepUIProperties();
    }

    private void prepUIProperties() {
        station2 = new SimpleStringProperty(stationSchedule.get(0).getHour() + ":" + stationSchedule.get(0).getMinute() + ":" + stationSchedule.get(0).getSecond());
        station9 = new SimpleStringProperty(stationSchedule.get(1).getHour() + ":" + stationSchedule.get(1).getMinute() + ":" + stationSchedule.get(1).getSecond());
        station16 = new SimpleStringProperty(stationSchedule.get(2).getHour() + ":" + stationSchedule.get(2).getMinute() + ":" + stationSchedule.get(2).getSecond());
        station22 = new SimpleStringProperty(stationSchedule.get(3).getHour() + ":" + stationSchedule.get(3).getMinute() + ":" + stationSchedule.get(3).getSecond());
        station31 = new SimpleStringProperty(stationSchedule.get(4).getHour() + ":" + stationSchedule.get(4).getMinute() + ":" + stationSchedule.get(4).getSecond());
        station39 = new SimpleStringProperty(stationSchedule.get(5).getHour() + ":" + stationSchedule.get(5).getMinute() + ":" + stationSchedule.get(5).getSecond());
        station48 = new SimpleStringProperty(stationSchedule.get(6).getHour() + ":" + stationSchedule.get(6).getMinute() + ":" + stationSchedule.get(6).getSecond());
        station57 = new SimpleStringProperty(stationSchedule.get(7).getHour() + ":" + stationSchedule.get(7).getMinute() + ":" + stationSchedule.get(7).getSecond());
        station65 = new SimpleStringProperty(stationSchedule.get(8).getHour() + ":" + stationSchedule.get(8).getMinute() + ":" + stationSchedule.get(8).getSecond());
        station73 = new SimpleStringProperty(stationSchedule.get(9).getHour() + ":" + stationSchedule.get(9).getMinute() + ":" + stationSchedule.get(9).getSecond());
        station77 = new SimpleStringProperty(stationSchedule.get(10).getHour() + ":" + stationSchedule.get(10).getMinute() + ":" + stationSchedule.get(10).getSecond());
        station88 = new SimpleStringProperty(stationSchedule.get(11).getHour() + ":" + stationSchedule.get(11).getMinute() + ":" + stationSchedule.get(11).getSecond());
        station96 = new SimpleStringProperty(stationSchedule.get(12).getHour() + ":" + stationSchedule.get(12).getMinute() + ":" + stationSchedule.get(12).getSecond());
        station105 = new SimpleStringProperty(stationSchedule.get(13).getHour() + ":" + stationSchedule.get(13).getMinute() + ":" + stationSchedule.get(13).getSecond());
        station114 = new SimpleStringProperty(stationSchedule.get(14).getHour() + ":" + stationSchedule.get(14).getMinute() + ":" + stationSchedule.get(14).getSecond());
        station123 = new SimpleStringProperty(stationSchedule.get(15).getHour() + ":" + stationSchedule.get(15).getMinute() + ":" + stationSchedule.get(15).getSecond());
        station132 = new SimpleStringProperty(stationSchedule.get(16).getHour() + ":" + stationSchedule.get(16).getMinute() + ":" + stationSchedule.get(16).getSecond());
        station141 = new SimpleStringProperty(stationSchedule.get(17).getHour() + ":" + stationSchedule.get(17).getMinute() + ":" + stationSchedule.get(17).getSecond());
    }

    // NEEDED FOR UI UPDATES
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
