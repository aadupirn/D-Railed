package TrainModel;

/**
 * Created by swaroopakkineni on 4/3/17.
 */
public class AC {

    protected final double maxTemp = 75;
    protected final double minTemp = 65;
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
        if(!(temperature >= maxTemp || temperature <= minTemp)){
            if(heat)
                temperature += .1;
            else if(ac)
                temperature -= .1;
            else if(ac && heat){
                temperature -= .1;
                heat = false;
            }
        }

    }
    protected static Double getTemp(){
        return temperature;
    }
}
