package TrackModel.Model;

/**
 * Created by adzun_000 on 2/24/2017.
 */
public class Beacon {

    int beaconNumber;
    String message;

    public Beacon(int beaconNumber, String message){
        this.beaconNumber = beaconNumber;
        this.message = message;
    }

    public int getBeaconNumber(){
        return this.beaconNumber;
    }

    public String readMessage(){
        return this.message;
    }

}
