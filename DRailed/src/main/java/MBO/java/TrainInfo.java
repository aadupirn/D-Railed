package MBO.java;

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
    private int id;
    private double speed;
    private double safeSpeed;
    private String location;
    private int authority;
    private double variance;

    public TrainInfo(int id, double speed, double safeSpeed, String location, int authority, double variance){
        this.id = id;
        this.speed = speed;
        this.safeSpeed = safeSpeed;
        this.location = location;
        this.authority = authority;
        this.variance = variance;
    }

    public int getId(){
        return id;
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getSafeSpeed(){
        return safeSpeed;
    }

    public double calculateSafeSpeed(){
        return 0;
    }

    public String updateLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public int getAuthority(){
        return authority;
    }

    public void setAuthorithy(int authorithy){
        this.authority = authority;
    }

    public double getVariance(){
        return variance;
    }

    public void setVariance(double variance){
        this.variance = variance;
    }
}
