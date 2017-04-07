package TrackModel.Model;

import javafx.concurrent.Task;

import java.util.Random;

/**
 * Created by adzun_000 on 1/21/2017.
 */
public class Heater {

    private String heaterId;
    private boolean active;
    private Double desiredTemp;
    private Double railTemp;
    private Double enviromentTemp;
    private Double heatRate = 2.0;

    /***
     *
     */
    public Heater(String heaterId){
        this.heaterId = heaterId;
        this.active = false;
        this.railTemp = generateRailTemp();
        this.enviromentTemp = this.railTemp;
        this.desiredTemp = generateRailTemp();
        startUpdateThread();
    }

    public Heater(String heaterNumber, double railTemp){
        this.heaterId = heaterNumber;
        this.active = false;
        this.railTemp = railTemp;
        this.enviromentTemp = this.railTemp;
        this.desiredTemp = generateRailTemp();
        startUpdateThread();
    }

    public String getHeaterNumber() {
        return heaterId;
    }

    public void setHeaterNumber(String heaterNumber) {
        this.heaterId = heaterNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /***
     * Toggle the heater on and off
     */
    public void toggleActive(){
        this.active = !this.active;
    }

    public double getDesiredTemp() {
        return desiredTemp;
    }

    public void setDesiredTemp(double desiredTemp) {
        this.desiredTemp = desiredTemp;
    }

    public double getRailTemp() {
        return railTemp;
    }

    public void setRailTemp(double railTemp) {
        this.railTemp = railTemp;
    }

    public double getHeatRate() {
        return heatRate;
    }

    public void setHeatRate(double heatRate) {
        this.heatRate = heatRate;
    }

    private Double generateRailTemp() {
        return new Double(new Random().nextInt(100));
    }

    public Double updateTemp(){
        if(this.active){
            heatOn();
        }else{
            heatOff();
        }

        return this.railTemp;
    }

    private void heatOn(){
        if(this.railTemp < this.desiredTemp){
            this.railTemp += this.heatRate;
        }
    }

    private void heatOff(){
        if(this.railTemp > this.enviromentTemp){
            this.railTemp -= this.heatRate;
        }
    }

    private void startUpdateThread(){
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (true) {
                    updateTemp();
                    Thread.sleep((int)getHeatRate() * 1000);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();


    }


}
