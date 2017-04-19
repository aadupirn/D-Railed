package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by joero on 4/4/2017.
 */
public class MBO {
    private boolean active;
    private boolean murphy;
    private String redLine = "Red";
    private String greenLine = "Green";
    private ObservableList<TrainInfo> redTrainInfos = FXCollections.observableArrayList();
    private ObservableList<TrainInfo> greenTrainInfos = FXCollections.observableArrayList();


    public MBO(int trainCount){
        active = false;
        murphy = false;
    }

    private boolean trainExists(int id, String line){
        if(line.equals(redLine)) {
            for (TrainInfo tf : redTrainInfos)
                if (tf.getId() == id) return true;
        } else if (line.equals(greenLine)) {
            for (TrainInfo tf : greenTrainInfos)
                if (tf.getId() == id) return true;
        }

        return false;
    }

    private TrainInfo findTrain(int id, String line) {
        if(line.equals(redLine)) {
            for (TrainInfo tf : redTrainInfos)
                if (tf.getId() == id)
                    return tf;
        } else if(line.equals(greenLine)) {
            for (TrainInfo tf : greenTrainInfos)
                if (tf.getId() == id)
                    return tf;
        }

        return null;
    }

    private void addTrain(int id, String line) {
        this.addTrain(id, line, 0, 0, 0, 0, "...");
    }

    public void addTrain(int id, String line, double speed, double safeSpeed, double variance, int authority, String location) {
        if(line.equals(redLine))
            redTrainInfos.add(new TrainInfo(id, line, speed, safeSpeed, location, authority, variance));
        else if(line.equals(greenLine))
            greenTrainInfos.add(new TrainInfo(id, line, speed, safeSpeed, location, authority, variance));
    }

    public void setLocation(int id, String line, String location){
        if(!trainExists(id, line)) addTrain(id, line);
        findTrain(id, line).setLocation(location);
    }

    public void setSpeed(int id, String line, double speed){
        if(!trainExists(id, line)) addTrain(id, line);
        findTrain(id, line).setSpeed(speed);
    }


    public void setSafeSpeed(int id, String line, double safeSpeed){
        if(!trainExists(id, line)) addTrain(id, line);
        findTrain(id, line).setSafeSpeed(safeSpeed);
    }

    public void setAuthority(int id, String line, int authority){
        if(!trainExists(id, line)) addTrain(id, line);
        findTrain(id, line).setAuthorithy(authority);
    }

    public void setVariance(int id, String line, double variance) {
        if(!trainExists(id, line)) addTrain(id, line);
        findTrain(id, line).setVariance(variance);
    }

    public int getAuthority(int id, String line){
        if(!trainExists(id, line)) addTrain(id, line);
        return findTrain(id, line).getAuthority();
    }


    public double getSafeSpeed(int id, String line){
        if(!trainExists(id, line)) addTrain(id, line);
        return findTrain(id, line).getSafeSpeed();
    }

    public boolean isMBOActive(){
        return active;
    }

    public void activateMBO(){ active = true; }
    public void deactivateMBO(){ active = false; }

    public void toggleMurphy(){ murphy = !murphy; }

    public ObservableList<TrainInfo> getRedRows() { return redTrainInfos; };
    public ObservableList<TrainInfo> getGreenRows() { return greenTrainInfos; };
}
