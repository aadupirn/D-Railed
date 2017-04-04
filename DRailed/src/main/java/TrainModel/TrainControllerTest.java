package TrainModel;

/**
 * Created by swaroopakkineni on 2/16/17.
 */
public class TrainControllerTest {

    Double speed;
    Double authority;

    public TrainControllerTest(){}

    public Double calculateTrainPower(Double speed){
        //Run some calculation here
        return 0.0;
    }

    public Double setBeaconArguments(Double beaconSpeed, Double beaconAuthority, int beaconTrackFailureStatus) {
        if(beaconTrackFailureStatus == 1){
            speed = beaconSpeed;
            authority = beaconAuthority;
            return calculateTrainPower(speed);
        }
        return -1.0;//fail case
    }
}
