package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by joero on 4/4/2017.
 */
public class Scheduler {
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
    public ObservableList<WorkerSchedule> getWorkerRows() { return workSchedules; }
}
