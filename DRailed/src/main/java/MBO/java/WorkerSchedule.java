package MBO.java;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by joero on 2/6/2017.
 */
public class WorkerSchedule {
    private ArrayList<LocalDateTime> stationSchedule;

    private SimpleIntegerProperty id;
    private SimpleStringProperty schedule;

    public WorkerSchedule(int id, LocalDateTime time){
        this.id  = new SimpleIntegerProperty(id);
        stationSchedule = new ArrayList<>();
        stationSchedule.add(time);
        stationSchedule.add(time.plusHours(4));
        stationSchedule.add(time.plusMinutes(270));
        stationSchedule.add(time.plusMinutes(750));
        prepUIProperties();
    }

    private void prepUIProperties() {
        schedule = new SimpleStringProperty(stationSchedule.get(0).format(DateTimeFormatter.ISO_LOCAL_TIME) + " - " + stationSchedule.get(1).format(DateTimeFormatter.ISO_LOCAL_TIME) + " |BREAK| " + stationSchedule.get(2).format(DateTimeFormatter.ISO_LOCAL_TIME) + " - " + stationSchedule.get(3).format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    public int getId(){ return id.get(); }

    public void setId(int id){ this.id.set(id); }

    public String getSchedule(){
        return schedule.get();
    }

    public void setLocation(String schedule){ this.schedule.set(schedule); }

    // NEEDED FOR AUTO-UPDATING OF UI
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    public SimpleStringProperty scheduleProperty() {
        return schedule;
    }
}