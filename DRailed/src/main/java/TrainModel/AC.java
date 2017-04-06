package TrainModel;

/**
 * Created by swaroopakkineni on 4/3/17.
 */
public class AC {

<<<<<<< HEAD
=======
    protected final double maxTemp = 80;
    protected final double minTemp = 60;
    private final double maxTempCel = 26.667;
    private final double minTempCel = 15.556;
    private final double normalTemp = 21.1111;
>>>>>>> master
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
<<<<<<< HEAD
=======
    protected void changeTemp(){
        double tempCel = fahrToCel(temperature);
        if(heat){
            tempCel = tempCel + ((maxTempCel - tempCel)* Math.exp(-11*.1));
            temperature = celToFah(tempCel);
        }

        else if(ac){
            tempCel = tempCel + ((minTempCel - tempCel)* Math.exp(-11*.1));
            temperature = celToFah(tempCel);
        }
        else{
            tempCel = tempCel + ((normalTemp - tempCel)* Math.exp(-11*.1));
            temperature = celToFah(tempCel);
        }

    }
>>>>>>> master
    protected static Double getTemp(){
        return temperature;
    }
}
