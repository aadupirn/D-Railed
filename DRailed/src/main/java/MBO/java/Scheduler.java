package MBO.java;

import ctc.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * Created by joero on 4/4/2017.
 */
public class Scheduler {
    private final String redLine = "RED";
    private final String greenLine = "GREEN";
    private HashMap<Integer, Integer> redBlockToId;
    private HashMap<Integer, Integer> greenBlockToId;
    private ObservableList<GreenTrainSchedule> greenTrainSchedules = FXCollections.observableArrayList();
    private ObservableList<RedTrainSchedule> redTrainSchedules = FXCollections.observableArrayList();
    private Timer clock;

    public Scheduler() {
//        this.clock = clock;
    }

    public void generateSchedule(String line, int thruput, LocalTime start, LocalTime end) {
        if(line.toUpperCase().equals(redLine))
            buildRedSchedule(thruput, start, end);
        else if(line.toUpperCase().equals(greenLine))
            buildGreenSchedule(thruput, start, end);

        loadHashMap();
    }

    public void updateSchedule(String line, int id, String location, LocalTime now) {
        if(line.toUpperCase().equals(redLine))
            for(RedTrainSchedule r : redTrainSchedules)
                if(r.getId() == id) return;
//                    r.updateSchedule(location, LocalTime.of(clock.getHour()));
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


    private void loadHashMap() {
        redBlockToId = new HashMap<>();
        greenBlockToId = new HashMap<>();
        Integer[] redBlockNums = {16, 21, 25, 35, 45, 48, 60};
        Integer[] greenBlockNums = {65, 73, 77, 88, 96, 105, 11, 123, 132, 141, 2, 9, 16, 22, 31, 39, 48, 57};
        for(int i = 0; i < redBlockNums.length ; i++)
            redBlockToId.put(redBlockNums[i], i);

        for(int i =0; i < greenBlockNums.length; i++)
            greenBlockToId.put(greenBlockNums[i], i);
    }

}