package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by joero on 4/4/2017.
 */
public class MBO {
    private boolean active;
    private boolean murphy;
    private ObservableList<TrainInfo> trainInfos = FXCollections.observableArrayList();


    public MBO(int trainCount){
        for(int i = 0; i < trainCount; i++)
            trainInfos.add(i, new TrainInfo(i + 1, 0, 0, null, 0, 0));

        active = false;
        murphy = false;
    }

    private boolean trainExists(int id){
        if(trainInfos.get(id - 1) == null) return false;
        return true;
    }

    public void setLocation(int id, String location){
        trainInfos.get(id - 1).setLocation(location);
    }

    public void setSpeed(int id, double speed){ trainInfos.get(id - 1).setSpeed(speed); }

    public void setSafeSpeed(int id, double safeSpeed){
        trainInfos.get(id - 1).setSafeSpeed(safeSpeed);
    }

    public void setAuthority(int id, int authority){
        trainInfos.get(id - 1).setAuthorithy(authority);
    }

    public void setVariance(int id, double variance) { trainInfos.get(id - 1).setVariance(variance); }

    public int getAuthority(int id){
        return 0;
    }


    public double getSafeSpeed(int id){
        return 0;
    }

    public boolean isMBOActive(){
        return false;
    }

    public void toggleMBO(){ active = !active; }

    public void toggleMurphy(){ murphy = !murphy; }

    private void addTrain(int id){
        trainInfos.add(id - 1, new TrainInfo(id, 0, 0, null, 0, 0));
    }

    public ObservableList<TrainInfo> getRows() { return trainInfos; };
}
