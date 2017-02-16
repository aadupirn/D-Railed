package TrackModel.Model;

/**
 * Created by adzun_000 on 1/20/2017.
 */
public class Crossing {

    private Integer crossingNumber;
    private boolean active;
    private Heater heater;

    /***
     * Create crossing with the signal set to false initially for a given
     */
    public Crossing(Integer crossingNumber){
        this.crossingNumber = crossingNumber;
        this.active = false;
        this.heater = new Heater(""+crossingNumber);
    }

    public Integer getCrossingNumber(){
        return crossingNumber;
    }

    /***
     * @return state of the railroad crossing system
     */
    public boolean isActive() {
        return active;
    }

    /***
     * Set the state of the crossing system to the designated state
     *
     * @param active - state to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /***
     * Toggle the current state of the crossing system
     */
    public void toggleActive(){
        this.active = !this.active;
    }

    public Heater getHeater() {
        return heater;
    }

    public void setHeater(Heater heater) {
        this.heater = heater;
    }
}
