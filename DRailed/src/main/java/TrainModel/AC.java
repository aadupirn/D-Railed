package TrainModel;

/**
 * Created by swaroopakkineni on 4/3/17.
 */
public class AC {

    protected final double maxTemp = 80;
    protected final double minTemp = 60;
    private final double maxTempCel = fahrToCel(maxTemp);
    private final double minTempCel = fahrToCel(minTemp);
    protected static double temperature;
    private boolean ac;
    private boolean heat;

    public AC(){
        ac = false;
        heat = false;
        temperature = 70.0;
    }
    protected void acOn(){
        //decrement temperature;
        ac = true;
        //temperature--;

    }
    protected void acOff(){
        //increment temperature
        ac = false;
        //temperature++;
    }
    protected void heatOn(){
        heat = true;
        temperature++;
    }
    protected void heatOff(){
        //decrement temperature;
        heat = false;
        temperature--;
    }
    protected void changeTemp(){
        double tempCel = fahrToCel(temperature);
        if(heat)
            tempCel = tempCel + (maxTemp - tempCel)* Math.exp(6.9*.1);
        else if(ac)
            tempCel = tempCel + (minTemp - tempCel)* Math.exp(6.9*.1);
        else;
        temperature = celToFah(tempCel);
    }
    protected static Double getTemp(){
        return temperature;
    }

    private Double fahrToCel(Double fah){
        return ((fah -32)*5/9);
    }
    private Double celToFah(Double cel){
        return ((cel *5/9)+32);
    }
}
