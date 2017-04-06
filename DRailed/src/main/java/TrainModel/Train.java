package TrainModel;
import TrainController.TrainController;

import java.io.IOException;
import java.util.Random;
import TrackModel.Track;

/**
 * Created by swaroopakkineni on 2/14/17.
 */
public class Train {
    private TrainController trainController;
    private TrainModel trainModel;
    private Track track;
    private int startingBlock;
    private Double commandSpeed;
    private int id;
    private double currentSpeed;
    private double mass;
    private double grade;
    private int authority;

    private engine Engine;
    private AC ac;
    private boolean leftDoors;
    private boolean rightDoors;
    private boolean lights;
    private static boolean ebrake;
    private static boolean sbrake;

    int unloading;

    public Train() throws IOException, Exception {
        System.out.println("train created");
       // trainModel = new TrainModel();
        Engine = new engine();
        ac = new AC();
        leftDoors = false;
        rightDoors = false;
        lights = false;
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;
        startingBlock = 152;
        track = new Track();

        trainController = new TrainController(this, this.track);
        //trainModel = new TrainModel();
    }

    // @ANDREW created for track model testing DON'T UNCOMMENT THE TRAIN MODEL AND TRAIN CONTROLLER THIS
    // IS ONLY USED BY ME FOR TESTING AT THE MOMENT
    public Train(int newId) throws IOException, Exception {

        Engine = new engine();
        ac = new AC();
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;
        startingBlock = 152;


        //trainModel = new TrainModel();
        this.id = id;
        //trainController = new TrainController(this);
        //trainModel = new TrainModel();
        this.unloading = generateUnloading();
    }

    public Train(int startingBlock, int newID) throws IOException, Exception {
        Engine = new engine();
        ac = new AC();
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;

        startingBlock = startingBlock;
        id = newID;
        track = new Track();
        trainController = new TrainController(this, this.track);
        //trainModel = new TrainModel();
      //  trainModel = new TrainModel();

    }
    public Train(int startingBlock, int numberOfCarts, int newID) throws IOException, Exception {
        Engine = new engine();
        ac = new AC();
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;

        startingBlock = startingBlock;
        id = newID;
        track = new Track();
        trainController = new TrainController(this, track);
       // trainModel = new TrainModel();
        //trainModel = new TrainModel();
        //trainController = new TrainController();
    }
    public Train(int startingBlock, int numberOfCarts, int newAuthority, Double newSpeed, int newID, Track track) throws IOException, Exception {
        Engine = new engine();
        ac = new AC();
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;

        startingBlock = startingBlock;
        id = newID;
        trainController = new TrainController(this, track);
       // trainModel = new TrainModel();
        //trainModel = new TrainModel();
    }

    public int getId(){
        return id;
    }
    public TrainController GetTrainController(){
      return trainController;
    }
    /*private boolean SetBeacon(String beacon){
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
    */


    private boolean setBeaconImputs(Double beaconSpeed, Double beaconCommand, int beaconFailureStatus, int beaconPassengerCount) {

        //trainModel.distanceCalc(trainController.setBeaconArguments(beaconSpeed, beaconCommand, beaconFailureStatus));
        //trainModel.passengersLoading(beaconPassengerCount);
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
        //System.out.println("Current Speed: "+currentSpeed);
        return true;
    }
//AC Status
    public void SetAcOn(){
        ac.acOn();
    }
    public void SetAcOFF(){
        ac.acOff();
    }
    public void SetHeatOn(){
        ac.heatOn();
    }
    public void SetHeatOFF(){
        ac.heatOff();
    }
    public double getTemperature(){ return ac.getTemp();}


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

    public double GetCurrentSpeed(){
        return currentSpeed;
    }
    public int GetAuthority(){
        return authority;
    }
    public int GetStartingBlock(){
        return startingBlock;
    }
    public void SetPowerCommand(Double pwrCMD){
        commandSpeed = pwrCMD;
    }
    public double GetPowerCommand(){ return commandSpeed;}
    public void Update(){
        ac.changeTemp();
        calculateSpeed(commandSpeed);
        //System.out.println("TEmperature is " + ac.getTemp());
        //System.out.println("Speed is " + );

    }
    public boolean setEbrake(boolean bool){
        ebrake = bool;
        engine.setEbrake(ebrake);
        return true;
    }
    public boolean GetEbrake(){
        return ebrake;
    }
    public boolean SetSbrake(boolean bool){
        sbrake = bool;
        engine.setSbrake(sbrake);

        return true;
    }
    public boolean GetSbrake(){
        return sbrake;
    }


    public boolean SetLeftDoors(boolean bool){
        leftDoors = bool;
        return leftDoors;
    }
    public boolean GetLeftDoorsStatus(){
        return leftDoors;
    }

    public boolean SetRightDoors(boolean bool){
        rightDoors = bool;
        return rightDoors;
    }

    public boolean GetRightDoorsStatus(){
        return rightDoors;
    }



    public boolean SetLights(boolean lightsStatus){
        lights = lightsStatus;
        return true;
    }
    public boolean GetLights(){
        return lights;
    }

    public boolean setGrade(Double newGrade){
        grade = newGrade;
        return true;
    }
    public double getGrade(){
        return grade;
    }
}


