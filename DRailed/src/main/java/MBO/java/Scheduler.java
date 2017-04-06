package MBO.java;

import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by joero on 4/4/2017.
 */
public class Scheduler {
    private ArrayList<TrainSchedule> trainSchedules;


    public Scheduler(int trainCount) {
        trainSchedules = new ArrayList<>(trainCount);
        trainSchedules.add(new TrainSchedule(LocalDateTime.now()));
    }

    public void geTrackData() {

    }
}
