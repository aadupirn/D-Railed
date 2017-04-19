package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Timer;

/**
 * Created by joero on 4/4/2017.
 */
public class Scheduler {
    private final String redLine = "RED";
    private final String greenLine = "GREEN";
    private ObservableList<GreenTrainSchedule> greenTrainSchedules = FXCollections.observableArrayList();
    private ObservableList<RedTrainSchedule> redTrainSchedules = FXCollections.observableArrayList();

    public void generateSchedule(String line, int thruput, LocalTime start, LocalTime end) {
        System.out.println("DEV: Generating schedule for " + line + " line.");
        if(line.toUpperCase().equals(redLine))
            buildRedSchedule(thruput, start, end);
        else if(line.toUpperCase().equals(greenLine))
            buildGreenSchedule(thruput, start, end);

    }

    public ObservableList<GreenTrainSchedule> getGreenTrainRows() { return greenTrainSchedules; }
    public ObservableList<RedTrainSchedule> getRedTrainRows() { return redTrainSchedules; }

    private void buildRedSchedule(int thruput, LocalTime start, LocalTime end) {
        redTrainSchedules.clear();
        for (int i = 0; i < (Math.ceil(end.getHour() - start.getHour()) * thruput); i++)
            redTrainSchedules.add(new RedTrainSchedule(i % thruput + 1, start.plusSeconds((long) (i / (double)thruput * 60 * 60))));
    }

    private void buildGreenSchedule(int thruput, LocalTime start, LocalTime end) {
        greenTrainSchedules.clear();
        for (int i = 0; i < (Math.ceil((double) (end.getHour() - start.getHour()))) * thruput; i++)
            greenTrainSchedules.add(new GreenTrainSchedule(i % thruput + 1, start.plusSeconds((long) (i / (double)thruput * 60 * 60))));
    }

}