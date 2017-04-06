package MBO.java;

import sun.rmi.runtime.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by joero on 2/6/2017.
 */
public class TrainSchedule {
    private ArrayList<LocalDateTime> stationSchedule;
    private long[] stationDelays = {138, 138, 144, 162, 156, 114, 120, 120, 132, 150, 132, 264, 132, 138, 144, 126, 120, 120};

    public TrainSchedule(LocalDateTime time) {
        stationSchedule = new ArrayList<LocalDateTime>(18);
        stationSchedule.add(0, time);
        for(int i = 1; i < 18; i++)
            stationSchedule.add(i, stationSchedule.get(i-1).plusSeconds(stationDelays[(i + 7)%17]));

        System.out.print(stationSchedule.toString());
    }
}
