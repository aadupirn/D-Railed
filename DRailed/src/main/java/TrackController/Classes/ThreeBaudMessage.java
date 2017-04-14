package TrackController.Classes;

/**
 * Created by Jonathan on 4/10/2017.
 */
public class ThreeBaudMessage {

    private char trainID, speed, authority;
    public ThreeBaudMessage()
    {
        trainID = 255;
        speed = 0;
        authority = 0;
    }

    public ThreeBaudMessage(char id, char speed, char authority)
    {
        this.trainID=id;
        this.speed=speed;
        this.authority=authority;
    }

    public char getTrainID() {
        return trainID;
    }

    public void setTrainID(char trainID) {
        this.trainID = trainID;
    }

    public char getSpeed() {
        return speed;
    }

    public void setSpeed(char speed) {
        this.speed = speed;
    }

    public char getAuthority() {
        return authority;
    }

    public void setAuthority(char authority) {
        this.authority = authority;
    }
}
