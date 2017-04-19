package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.util.Timer;

/**
 * Created by joero on 4/4/2017.
 */
public class Scheduler {
    private final String redLine = "Red";
    private final String greenLine = "Green";
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

    public void generateSchedule(int thruput, Time start, Time end) {
        Time temp = (Time) start.clone();
        while(temp.compareTo(end) == -1){

        }
    }

    public ObservableList<GreenTrainSchedule> getGreenTrainRows() { return greenTrainSchedules; }
    public ObservableList<RedTrainSchedule> getRedTrainRows() { return redTrainSchedules; }
    public ObservableList<WorkerSchedule> getWorkerRows() { return workSchedules; }
}