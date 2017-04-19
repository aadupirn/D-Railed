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
    private ObservableList<WorkerSchedule> workSchedules = FXCollections.observableArrayList();
    private Timer clock;

    public Scheduler(Timer clock) {
        this.clock = clock;
    }

    public String getDeparture(String line, int trainId, int stationId) {
        if(line.equals(redLine)) {
            for (RedTrainSchedule r : redTrainSchedules)
                if (r.idProperty().equals(Integer.toString(trainId)))
                    return r.getDeparture(stationId);
        }

        return null;
    }

    public void generateSchedule(String line, int thruput, LocalTime start, LocalTime end) {
        if(line.equals(redLine))
            buildRedSchedule(thruput, start, end);
        else if(line.equals(greenLine))
            buildGreenSchedule(thruput, start, end);

    }

    public ObservableList<GreenTrainSchedule> getGreenTrainRows() { return greenTrainSchedules; }
    public ObservableList<RedTrainSchedule> getRedTrainRows() { return redTrainSchedules; }
    public ObservableList<WorkerSchedule> getWorkerRows() { return workSchedules; }

    private void buildRedSchedule(int thruput, LocalTime start, LocalTime end) {
        for (int i = 0; i < (Math.ceil(end.getHour() - start.getHour()) * thruput); i++) {
            redTrainSchedules.add(new RedTrainSchedule(i + 1, start));
        }
    }

    private void buildGreenSchedule(int thurput, LocalTime start, LocalTime end) {
        LocalTime temp = LocalTime.of(start.getHour(), start.getMinute(), start.getSecond());

        for (int i = 0; i < (Math.ceil(end.getHour() / start.getHour())); i++) {
            greenTrainSchedules.add(new GreenTrainSchedule(i + 1, start));
        }
    }

}