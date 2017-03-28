package TrackModel.Model;

/**
 * Created by adzun_000 on 2/7/2017.
 */
public class Light {

    Integer lightNumber;
    boolean active;

    public Light(){
        this.lightNumber = null;
        this.active = false;
    }

    public Light(Integer lightNumber){
        this.lightNumber = lightNumber;
        this.active = true;
    }

    public Light(Integer lightNumber, boolean status){
        this.lightNumber = lightNumber;
        this.active = status;
    }

    public Integer getLightNumber() {
        return lightNumber;
    }

    public void setLightNumber(Integer lightNumber) {
        this.lightNumber = lightNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean status) {
        this.active = status;
    }

    public void toggleActive(){
        this.active = (!this.active);
    }
}
