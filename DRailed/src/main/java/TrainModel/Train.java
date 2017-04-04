package TrainModel;
import TrainController.TrainController;

import java.io.IOException;
import java.util.Random;

/**
 * Created by swaroopakkineni on 2/14/17.
 */
public class Train {
    private TrainModelMain trainModel;
    private TrainController trainController;
    private int block;
    private Double commandSpeed;
    private int id;
    private double currentSpeed;
    private double mass;
    private double grade;

    private engine Engine;
    private AC ac;

    int unloading;

    public Train() throws IOException {
        Engine = new engine();
        ac = new AC();

        trainModel = new TrainModelMain();
        trainController = new TrainController(this);
    }

    // @ANDREW created for track model testing
    public Train(int newId) throws IOException {
        trainController = new TrainController(this);
        Engine = new engine();
        ac = new AC();

        trainModel = new TrainModelMain();
        this.id = id;
        this.unloading = generateUnloading();
    }

    public Train(int blockLocation, int newID) throws IOException {
       trainController = new TrainController(this);
        Engine = new engine();
        ac = new AC();

        block = blockLocation;
        trainModel = new TrainModelMain();
        id = newID;

        trainModel = new TrainModelMain();
    }
    public Train(int blockLocation, int numberOfCarts, int newID) throws IOException {
        trainController = new TrainController(this);
        Engine = new engine();
        ac = new AC();

        block = blockLocation;
        id = newID;
        trainModel = new TrainModelMain(numberOfCarts);
        //trainController = new TrainController();
    }
    public Train(int blockLocation, int numberOfCarts, Double newAuthority, Double newSpeed, int newID) throws IOException {
        Engine = new engine();
        ac = new AC();
        trainController = new TrainController(this);


        block = blockLocation;
        id = newID;
        trainModel = new TrainModelMain(numberOfCarts, newAuthority, newSpeed);
    }

    public int getId(){
        return id;
    }
    public TrainController GetTrainController(){
      return trainController;
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

        //trainModel.distanceCalc(trainController.setBeaconArguments(beaconSpeed, beaconCommand, beaconFailureStatus));
        trainModel.passengersLoading(beaconPassengerCount);
        return true;
    }

   /* private boolean setCommandSpeed(Double newCommandSpeed){
        commandSpeed = newCommandSpeed;
        Double power = TrainControllerTest.calculateAuthority(commandSpeed);
        calculateSpeed(power);
        return true;
    }*/
/*
private double calculateSpeed(double mass, double powerCommand, double currentSpeed,
                                double grade){
Calculates speed
 */
    protected boolean calculateSpeed(Double power){
        //do some calculations
        currentSpeed =  Engine.calculateSpeed(mass, commandSpeed, currentSpeed, grade);
        System.out.println(currentSpeed);
        return true;
    }

    private void acOn(){
        ac.acOn();
    }

    private void acOFF(){
        ac.acOff();
    }
    private void heatOn(){
        ac.heatOn();
    }

    private void heatOFF(){
        ac.heatOff();
    }
    private void getGrade(){
        // grade = trainController.getGrade();
    }

    // @ANDREW also used in TrackModel tests
    private int generateUnloading() {
        return new Random().nextInt(222 - 74) + 74;
    }

    public int getUnloading(){
        return unloading;
    }

    public void unload(){
        this.unloading = generateUnloading();
    }

}
