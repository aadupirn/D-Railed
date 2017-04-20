package MBO.java;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public TrainInfo(int id, String line, double speed, double safeSpeed, String location, double authority, double variance){
        this.id  = new SimpleIntegerProperty(id);
        this.line = line;
        this.block = "init";
        this.speed = new SimpleDoubleProperty(speed);
        this.safeSpeed = new SimpleDoubleProperty(safeSpeed);
        this.location = new SimpleStringProperty(location);
        this.authority = new SimpleIntegerProperty((int) authority);
        this.variance = new SimpleDoubleProperty(variance);
        this.stoppingDistance = 0;
        this.gps = 1;
    }

    private String formatGPS(String location) {
        String data[] = location.split(":");
        if(!block.equals(data[1]))
            gps += distInBlk;

        block = data[1];
        distInBlk = Integer.parseInt(data[2]);
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

    public void setStoppingDistance() {
        double v = this.getSpeed(), g = 9.8, theta = -5;
        this.stoppingDistance = 1.5 * (v * v)/(2 * g * Math.sin(theta));
    }

    public void setSafeSpeed(boolean mbo) {

        if(mbo){
            if((gps + distInBlk + stoppingDistance) >= authority.get())
                this.safeSpeed.set((gps + distInBlk)/authority.get() * speed.get());
            else
                this.safeSpeed.set(speed.get());
        } else
            this.safeSpeed.set(-1);
    }

    public String getLocation(){
        return location.get();
    }

    public void setLocation(String location, boolean mbo) {
        this.location.set(formatGPS(location));
        setSafeSpeed(mbo);
    }

    public int getAuthority(){
        return authority.get();
    }

    public void setAuthorithy(double authority){ this.authority.set((int) authority); }

    public double getVariance(){
        return variance.get();
    }

    public void setVariance(double variance){
        this.variance.set(variance);
    }

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