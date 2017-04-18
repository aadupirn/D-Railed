package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Timer;
=======
>>>>>>> master

/**
 * Created by joero on 4/4/2017.
 */
public class Scheduler {
<<<<<<< HEAD
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
=======
    private ObservableList<TrainSchedule> trainSchedules = FXCollections.observableArrayList();
    private ObservableList<WorkerSchedule> workSchedules = FXCollections.observableArrayList();


    public Scheduler(int trainCount) {
        trainSchedules.add(new TrainSchedule(1, LocalDateTime.now()));
        workSchedules.add(new WorkerSchedule(1, LocalDateTime.now()));
    }

    public TrainSchedule getSchedule() {
        return trainSchedules.get(0);
    }

    public String getDeparture(int trainId, int stationId) {
        for(TrainSchedule t : trainSchedules)
            if(t.idProperty().equals(Integer.toString(trainId)))
                return t.getDeparture(stationId);

        return null;
    }


    public ObservableList<TrainSchedule> getTrainRows() { return trainSchedules; }
>>>>>>> master
    public ObservableList<WorkerSchedule> getWorkerRows() { return workSchedules; }
}
