package TrackModel.Model;

/**
 * Created by adzun_000 on 2/7/2017.
 */
public class Light {

    Integer lightId;
    boolean active;

    public Light(){
        this.lightId = null;
        this.active = false;
    }

    public Light(Integer lightId){
        this.lightId = lightId;
        this.active = true;
    }

    public Light(Integer lightId, boolean status){
        this.lightId = lightId;
        this.active = status;
    }

    public Integer getLightId() {
        return lightId;
    }

    public void setLightId(Integer lightId) {
        this.lightId = lightId;
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
