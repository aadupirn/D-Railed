package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by joero on 4/4/2017.
 */
public class MBO {
    private boolean active;
    private boolean murphy;
    private String redLine = "RED";
    private String greenLine = "GREEN";

    private ObservableList<TrainInfo> redTrainInfos = FXCollections.observableArrayList();
    private ObservableList<TrainInfo> greenTrainInfos = FXCollections.observableArrayList();

    public MBO(){
        active = false;
        murphy = false;
    }

    private boolean trainExists(int id, String line){
        if(line.toUpperCase().equals(redLine)) {
            for (TrainInfo tf : redTrainInfos)
                if (tf.getId() == id)
                    return true;
        } else if (line.toUpperCase().equals(greenLine)) {
            for (TrainInfo tf : greenTrainInfos)
                if (tf.getId() == id)
                    return true;
        }

        return false;
    }

    private TrainInfo findTrain(int id, String line) {
        if(line.toUpperCase().equals(redLine)) {
            for (TrainInfo tf : redTrainInfos)
                if (tf.getId() == id)
                    return tf;
        } else if(line.toUpperCase().equals(greenLine)) {
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
        if(trainExists(id, line)) {
            TrainInfo tf = findTrain(id, line);

            tf.setSpeed(speed);
            tf.setStoppingDistance();
            tf.setAuthorithy(authority);
            tf.setLocation(location, active);
        } else if(line.toUpperCase().equals(redLine))
            redTrainInfos.add(new TrainInfo(id, line, speed, safeSpeed, location, authority, variance));
        else if(line.toUpperCase().equals(greenLine))
            greenTrainInfos.add(new TrainInfo(id, line, speed, safeSpeed, location, authority, variance));
    }

    private TrainInfo getTrain(int id, String line) {
        if(!trainExists(id, line)) addTrain(id, line);
        return findTrain(id, line);
    }

     public double calculateBreakingDistance(int id, String line) {


         return 0;
     }

     private int getMeters(String location) {
        String[] data = location.split(":");
        return Integer.parseInt(data[2]);
     }

    // (Get/Set)-ers
    public void setLocation(int id, String line, String location){
        getTrain(id, line).setLocation(location, active);
    }

    public void setSpeed(int id, String line, double speed){
        getTrain(id, line).setSpeed(speed);
    }


    public void setSafeSpeed(int id, String line, double safeSpeed){
        getTrain(id, line).setStoppingDistance();
    }

    public void setAuthority(int id, String line, int authority){
        getTrain(id, line).setAuthorithy(authority);
    }

    public int getAuthority(int id, String line){
        return getTrain(id, line).getAuthority();
    }


    public double getSafeSpeed(int id, String line){
        return getTrain(id, line).getStoppingDistance();
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