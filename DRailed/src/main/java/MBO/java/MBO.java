package MBO.java;

import java.util.ArrayList;

/**
 * Created by joero on 4/4/2017.
 */
public class MBO {
    private boolean active;
    private ArrayList<TrainInfo> trainInfos;

    public MBO(int trainCount){
        trainInfos = new ArrayList<TrainInfo>(trainCount);
        active = false;
    }

    private boolean trainExists(int id){
        if(trainInfos.get(id - 1) == null) return false;
        return true;
    }

    public void setLocation(int id, String gps){
        if(!trainExists(id)) addTrain(id);
    }

    public int getAuthority(int id){
        return 0;
    }

    public void setSpeed(int id, double speed){

    }

    public double getSafeSpeed(int id){
        return 0;
    }

    public boolean isMBOActive(){
        return false;
    }

    public void toggleMBO(){

    }

    private void addTrain(int id){
        trainInfos.add(id - 1, new TrainInfo(id, 0, 0, null, 0, 0));
    }
}
