package MBO.java;

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
    private final SimpleDoubleProperty speed;
    private final SimpleDoubleProperty safeSpeed;
    private final SimpleStringProperty location;
    private final SimpleIntegerProperty authority;
    private final SimpleDoubleProperty variance;

    public TrainInfo(int id, String line, double speed, double safeSpeed, String location, int authority, double variance){
        this.id  = new SimpleIntegerProperty(id);
        this.line = line;
        this.speed = new SimpleDoubleProperty(speed);
        this.safeSpeed = new SimpleDoubleProperty(safeSpeed);
        this.location = new SimpleStringProperty(location);
        this.authority = new SimpleIntegerProperty(authority);
        this.variance = new SimpleDoubleProperty(variance);
    }

    public int getId(){ return id.get(); }

    public void setId(int id){ this.id.set(id); }

    public String getLine() { return line;}

    public void setLine(String line) { this.line = line;}

    public double getSpeed(){
        return speed.get();
    }

    public void setSpeed(double speed){
        this.speed.set(speed);
        this.setVariance(this.getSafeSpeed() - speed);
    }

    public double getSafeSpeed(){
        return safeSpeed.doubleValue();
    }

    public void setSafeSpeed(double speed) { this.safeSpeed.set(50); }

    public String getLocation(){
        return location.get();
    }

    public void setLocation(String location){ this.location.set(location); }

    public int getAuthority(){
        return authority.get();
    }

    public void setAuthorithy(int authority){ this.authority.set(authority); }

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
