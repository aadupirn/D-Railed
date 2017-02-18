package MBO.java;


import MBO.java.Train;

import java.util.ArrayList;
// import TrainModel.Train;

public class TrainInfo {
    ArrayList<Train> trains;

    public TrainInfo() {
        trains = new ArrayList<Train>();
    }

    public TrainInfo(Train t) {
        trains = new ArrayList<Train>();
        trains.add(t);
    }

    public void addTrain(Train t) {
        // Add train to trains
    }

    public Train getTrain(int id) {
        // Return train with matching ID
        return null;
    }

    public void deleteTrain(int id) {
        Train temp = getTrain(id);
        trains.remove(temp);
    }

    public int getTrainCount() {
        return trains.size();
    }

    public boolean trainExists(int id) {
        // Check if train id is in trains
        for(Train t : trains) {
            if(t.getId() == id)
                return true;
        }
        return false;
    }


}