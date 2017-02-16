package TrackModel;

import TrackController.TrackController;
import TrackModel.Model.TrackModel;

/**
 * Created by adzun_000 on 2/14/2017.
 */
public class Track {

    //TrackController tc = null;
    TrackModel tm = null;
    private String railSignal = "0x000000";
    private String trainSignal = "0x000000";

    public Track(){
        //tc = new TrackController();
        tm = new TrackModel();
    }

    public Track(String trackLayout){
        //tc = new TrackController();
        tm = new TrackModel(trackLayout);
    }

    public void setSpeedAndAuthority(int trainId, double speed, int authority, int status){

        String trainIdHex = Integer.toHexString(trainId);
        String setPointSpeed = Integer.toHexString(new Double(speed).intValue());
        String authorityBounds = Integer.toHexString(authority);
        String statusHex = Integer.toHexString(status);

        railSignal = "0x" + trainIdHex + setPointSpeed + authorityBounds + statusHex + "00";

    }



}

