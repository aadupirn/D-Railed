package MBO.java;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by joero on 2/16/2017.
 */
public class InfoRow {
    private SimpleStringProperty trainId, safeSpeed, speed, variance, authority, block, gps;
    public InfoRow (String id, String safeSpeed, String speed, String variance, String authority, String block, String gps) {
        this.trainId = new SimpleStringProperty(id);
        this.safeSpeed = new SimpleStringProperty(safeSpeed);
        this.speed = new SimpleStringProperty(speed);
        this.variance = new SimpleStringProperty(variance);
        this.authority = new SimpleStringProperty(authority);
        this.block = new SimpleStringProperty(block);
        this.gps = new SimpleStringProperty(gps);
    }

    public String getTrainId() {
        return trainId.get();
    }

    public void setTrainId(String trainId) {
        this.trainId.set(trainId);
    }

    public String getSafeSpeed() {
        return safeSpeed.get();
    }

    public void setSafeSpeed(String safeSpeed) {
        this.safeSpeed.set(safeSpeed);
    }

    public String getSpeed() {
        return speed.get();
    }

    public void setSpeed(String speed) {
        this.speed.set(speed);
    }

    public String getVariance() {
        return variance.get();
    }

    public void setVariance(String variance) { this.variance.set(variance); }

    public String getAuthority() { return authority.get(); }

    public void setAuthority(String authority) {
        this.authority.set(authority);
    }

    public String getBlock() {
        return block.get();
    }

    public void setBlock(String block) {
        this.block.set(block);
    }

    public String getGPS() {
        return gps.get();
    }

    public void setGPS(String gps) {
        this.gps.set(gps);
    }
}