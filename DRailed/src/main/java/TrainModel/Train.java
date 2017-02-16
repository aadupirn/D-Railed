package TrainModel;

/**
 * Created by swaroopakkineni on 2/14/17.
 */
public class Train {
    private TrainModelMain trainModel;
    TrainControllerTest trainController;
    private int block;
    private Double commandSpeed;
    private int id;


    public Train(){
        trainModel = new TrainModelMain();
        //trainController = new TrainController();
    }
    public Train(int blockLocation, int newID){
        block = blockLocation;
        trainModel = new TrainModelMain();
        id = newID;

        trainModel = new TrainModelMain();
        //trainController = new TrainController();
    }
    public Train(int blockLocation, int numberOfCarts, int newID){
        block = blockLocation;
        id = newID;
        trainModel = new TrainModelMain(numberOfCarts);
        //trainController = new TrainController();
    }
    public Train(int blockLocation, int numberOfCarts, Double newAuthority, Double newSpeed, int newID){
        block = blockLocation;
        id = newID;
        trainModel = new TrainModelMain(numberOfCarts, newAuthority, newSpeed);
        trainController = new TrainControllerTest();
    }

    private boolean receiveBeacon(String beacon){
        String[] beaconArray = beacon.split("");
        int beaconID = Integer.decode("0x" + beaconArray[0]);
        if(id == beaconID){
            Double beaconSpeed =  Integer.decode("0x" + beaconArray[1] ).doubleValue();
            Double beaconCommand =  Integer.decode("0x" + beaconArray[2] ).doubleValue();
            int beaconFailureStatus =  Integer.decode("0x" + beaconArray[3] );
            int beaconPassengerCount =  Integer.decode("0x" + beaconArray[4] + beaconArray[5]  );
            return setBeaconImputs(beaconSpeed, beaconCommand, beaconFailureStatus, beaconPassengerCount);
        }
        return false;
    }

    private boolean setBeaconImputs(Double beaconSpeed, Double beaconCommand, int beaconFailureStatus, int beaconPassengerCount) {

        trainModel.distanceCalc(trainController.setBeaconArguments(beaconSpeed, beaconCommand, beaconFailureStatus));
        trainModel.passengersLoading(beaconPassengerCount);
        return true;
    }

   /* private boolean setCommandSpeed(Double newCommandSpeed){
        commandSpeed = newCommandSpeed;
        Double power = TrainControllerTest.calculateAuthority(commandSpeed);
        calculateSpeed(power);
        return true;
    }*/

    private boolean calculateSpeed(Double power){
        //do some calculations
        return true;
    }
}
