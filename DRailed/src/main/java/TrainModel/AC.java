package TrainModel;

/**
 * Created by swaroopakkineni on 4/3/17.
 */
public class AC {

    protected static double temperature;

    public AC(){
        temperature = 70.0;
    }
    protected void acOn(){
        //decrement temperature;
        temperature--;

    }
    protected void acOff(){
        //increment temperature
        temperature++;
    }
    protected void heatOn(){
        temperature++;
    }
    protected void heatOff(){
        //decrement temperature;
        temperature--;
    }
    protected static Double getTemp(){
        return temperature;
    }
}
