package TrainModel;

/**
 * Created by swaroopakkineni on 4/3/17.
 */
public class AC {

    protected final double maxTemp = 80;
    protected final double minTemp = 60;
    private final double maxTempCel = 26.667;
    private final double minTempCel = 15.556;
    private final double normalTemp = 21.1111;
    protected static double temperature;
    private boolean ac;
    private boolean heat;
    private static double k;

    public AC(){
        ac = false;
        heat = false;
        temperature = 70.0;
        k = 0.00150;
    }
    public AC(double kValue){
        ac = false;
        heat = false;
        temperature = 70.0;
        k = kValue;
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
        if(heat){
            tempCel = tempCel + ((maxTempCel - tempCel)* Math.exp(-k*.1));
            temperature = celToFah(tempCel);
        }

        else if(ac){
            tempCel = tempCel + ((minTempCel - tempCel)* Math.exp(-k*.1));
            temperature = celToFah(tempCel);
        }
        else{
            tempCel = tempCel + ((normalTemp - tempCel)* Math.exp(-k*.1));
            temperature = celToFah(tempCel);
        }

    }
    protected static Double getTemp(){

        return temperature;
    }
    protected static void setK(double newK){
        k = newK;
    }

    private Double fahrToCel(Double fah){
        return ((fah - 32)*.5555);
    }
    private Double celToFah(Double cel){
        return ((cel * 1.8)+32);
    }
}
