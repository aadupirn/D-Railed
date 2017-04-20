package MBO.java;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DecimalFormat;

/**
 * Created by joero on 2/6/2017.
 */
public class TrainInfo {
    private final SimpleIntegerProperty id;
    private String line;
    private String block;
    private int distInBlk;
    private final SimpleDoubleProperty speed;
    private final SimpleDoubleProperty safeSpeed;
    private final SimpleStringProperty location;
    private final SimpleIntegerProperty authority;
    private final SimpleDoubleProperty variance;
    private double stoppingDistance;
    private int gps;
    private int len = 2546;

    public TrainInfo(int id, String line, double speed, double safeSpeed, String location, double authority, double variance){
        this.id  = new SimpleIntegerProperty(id);
        this.line = line;
        this.block = "init";
        this.speed = new SimpleDoubleProperty(speed);
        this.safeSpeed = new SimpleDoubleProperty(safeSpeed);
        this.location = new SimpleStringProperty(location);
        this.variance = new SimpleDoubleProperty(variance);
        this.stoppingDistance = 0;
        this.gps = 1;
        if(line.toUpperCase().equals("RED"))
            len = 1050;
        else
            len = 2546;

        this.authority = new SimpleIntegerProperty(len);

    }

    private String formatGPS(String location) {
        String data[] = location.split(":");
        if(!block.equals(data[1]))
            gps += distInBlk;

        block = data[1];
        distInBlk = (int)Double.parseDouble(data[2]);
        return Integer.toString(gps + distInBlk);
    }

    private double getMPH(double mps) {
        return (mps * 3.28084)/60;
    }

    public int getId(){ return id.get(); }

    public double getSpeed(){ return speed.get(); }

    public void setSpeed(double speed){ this.speed.set(getMPH(speed)); }

    public double getStoppingDistance(){
        return safeSpeed.doubleValue();
    }

    private boolean verifyDistance() {
        double test = 1.5 * Math.pow(speed.get(), 2) * .5 /(9.8 * Math.sin(-5));
        return test == stoppingDistance;
    }

    private void kill() {
        this.stoppingDistance = 0;
        this.safeSpeed.set(0);
    }

    public void setStoppingDistance() {
        double v = this.getSpeed(), g = 9.8, theta = -5;
        this.stoppingDistance = 1.5 * (v * v)/(2 * g * Math.sin(theta));
        if(!verifyDistance())
            kill();
    }

    public void setSafeSpeed(boolean mbo) {

        if(mbo){
            if((gps + distInBlk + stoppingDistance) >= authority.get()) {
                this.safeSpeed.set((double)(gps + distInBlk) / (double)authority.get() * speed.get());
            } else {
                this.safeSpeed.set(speed.get());
            }
        } else
            this.safeSpeed.set(-999);
    }

    public String getLocation(){
        return location.get();
    }

    public void setLocation(String location, boolean mbo) {
        this.location.set(formatGPS(location));
        setSafeSpeed(mbo);
        if(mbo) this.variance.set(Math.abs(speed.get() - safeSpeed.get()));
        else this.variance.set(-999);
    }

    public int getAuthority(){
        return authority.get();
    }

    public void setAuthorithy(double authority){ this.authority.set(len); }

    // NEEDED FOR AUTO-UPDATING OF UI
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    public SimpleDoubleProperty speedProperty() {
        return speed;
    }
    public SimpleDoubleProperty safeSpeedProperty() {
        return safeSpeed;
    }
    public SimpleStringProperty locationProperty() {
        return location;
    }
    public SimpleIntegerProperty authorityProperty() {
        return authority;
    }
    public SimpleDoubleProperty varianceProperty(){
        return variance;
    }
}