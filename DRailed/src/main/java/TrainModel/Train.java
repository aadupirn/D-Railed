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
    private TrainUI ui;
    private Track track;
    private int block;
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
    private static int people;
    private int numberOfCarts = 1;
    private boolean acBool;
    private boolean heatBool;
    private double temperature = 70.0;


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
        commandSpeed = 0.0;
        grade = 0.0;


        mass = 10000;

        block = 152;
        track = new Track();
        people = 0;
        //trainController = new TrainController(this, this.track);
        ui = new TrainUI();
        Update();

    }

    // @ANDREW created for track model testing DON'T UNCOMMENT THE TRAIN MODEL AND TRAIN CONTROLLER THIS
    // IS ONLY USED BY ME FOR TESTING AT THE MOMENT
    public Train(int newId) throws IOException, Exception {

        Engine = new engine();
        ac = new AC();

        leftDoors = false;
        rightDoors = false;

        lights = false;

        ebrake = false;
        sbrake = false;

        currentSpeed = 0;
        mass = 10000;

        block = 152;
        track = new Track();
        people = 0;
        trainController = new TrainController(this, this.track);
        ui = new TrainUI();
        Update();
    }

    public Train(int startingBlock, int newID) throws IOException, Exception {
        Engine = new engine();
        ac = new AC();
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;
        people = 0;


        block = startingBlock;
        id = newID;
        track = new Track();
        trainController = new TrainController(this, this.track);
       // ui = new TrainModel(newID);
        Update();
    }

    public Train(int startingBlock, int numberOfCarts, int newID) throws IOException, Exception {
        Engine = new engine();
        ac = new AC();
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;
        people = 0;

        block = startingBlock;
        id = newID;
        track = new Track();
        trainController = new TrainController(this, track);
        this.numberOfCarts = numberOfCarts;
        Update();
    }
    public Train(int startingBlock, int numberOfCarts, int newAuthority, Double newSpeed, int newID, Track track) throws IOException, Exception {
        Engine = new engine();
        ac = new AC();
        ebrake = false;
        sbrake = false;
        currentSpeed = 0;
        mass = 10000;
        grade = 0;
        people = new Random().nextInt(222);
        commandSpeed = 0.0;

        block = startingBlock;
        id = newID;
        trainController = new TrainController(this, track);
        ui = new TrainUI();
        this.numberOfCarts = numberOfCarts;
        Update();
    }

    /*
      Update Block
          This block updates the Train Model and the UI.
    */
    public void Update(){
        ac.changeTemp();
        calculateSpeed(commandSpeed);
        updateUI(); // comment out
    }
    public void updateUI(){
        //TrainModel == UI
        ui.updateId(id);
        ui.updateSpeed(currentSpeed);
        ui.updatePower(commandSpeed);
        ui.updateGrade(grade);
        ui.updateTemperature(temperature);
        ui.updateAC(acBool);
        ui.updateHeat(heatBool);
        ui.updateLeftDoors(leftDoors);
        ui.updateRightDoors(leftDoors);
        ui.updateEBrake(ebrake);
        ui.updateSBrake(sbrake);
        ui.updateMass(mass);
        ui.updateLights(lights);
        ui.updateAuthority(authority);
        ui.updateBlock(10);
    }

    public TrainController GetTrainController(){
        return trainController;
    }
    /*
      ID Block
          This block gets ID, ID is set during constructor
    */
    public int getId(){
        return id;
    }

    /*
     Speed Block
         This block gets ID, ID is set during constructor
    */
    protected boolean calculateSpeed(Double power){
        //do some calculations
        currentSpeed =  Engine.calculateSpeed(mass, commandSpeed, currentSpeed, grade);
        //System.out.println("Current Speed: "+currentSpeed);
        return true;
    }
    public double GetCurrentSpeed(){
        return currentSpeed;
    }

    /*
        Temperature Block
            This block has setters and getters Heat, AC, and Temperature
     */
    public void SetAcOn(){
        acBool = true;
        ac.acOn();
    }
    public void SetAcOFF(){
        acBool = false;
        ac.acOff();
    }
    public void SetHeatOn(){
        heatBool = true;
        ac.heatOn();
    }
    public void SetHeatOFF(){
        heatBool = false;
        ac.heatOff();
    }
    public double getTemperature(){
        temperature = ac.getTemp();
        return temperature;
    }


    /*
        Weight Block
            This block has setters and getters for people and updates weight of system
    */
    public void weightUpdate(){
        mass = (10000*(numberOfCarts + 2) + (people * 150));
    }
    public void setPeople(int people){
        this.people = people;
    }
    public int getPeople(){
        return people;
    }
    public int unload(){
       int peepsLeaving = new Random().nextInt(people);
        people -= peepsLeaving;
        weightUpdate();
        return peepsLeaving;
    }
    public void load(double load) {
        weightUpdate();
        people += load;
    }

    /*
        Controller Setters and Getters
            This block has setters and getters for things the controllers does
    */
    public int GetAuthority(){
        return authority;
    }
    public void SetAuthority(int newAuthority){
         authority = newAuthority;
    }

    public int GetBlock(){
        return block;
    }
    public int GetStartingBlock(){
        return block;
    }
    private void setBLock(int newBlock){
        block = newBlock;
    }
    public void SetPowerCommand(Double pwrCMD){
        commandSpeed = pwrCMD;
    }
    public double GetPowerCommand(){ return commandSpeed;}

    /*
        Brake Block
            This block has setters and getters for the the emergency and service brake
    */
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

    /*
        Grade Block
            This block has setters and getters for the the left and right doors
    */
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


    /*
        Lights Block
            This block has setters and getters for the the lights
     */
    public boolean SetLights(boolean lightsStatus){
        lights = lightsStatus;
        return true;
    }
    public boolean GetLights(){
        return lights;
    }

    /*
        Grade Block
            This block has setters and getters for the grade
    */
    public boolean setGrade(Double newGrade){
        grade = newGrade;
        return true;
    }
    public double getGrade(){
        return grade;
    }


    private void setGravity(double newGrav){
        engine.setGravity(newGrav);
    }
    private void setFriction(double newFriction){
        engine.setGravity(newFriction);
    }

    private void setK(double newK){
        ac.setK( newK);
    }

}

/* Unused Code


    private boolean SetBeacon(String beacon){
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
        //trainModel.passengersLoading(beaconPassengerCount);
        return true;
    }

   /private boolean setCommandSpeed(Double newCommandSpeed){
        commandSpeed = newCommandSpeed;
        Double power = TrainControllerTest.calculateAuthority(commandSpeed);
        calculateSpeed(power);
        return true;
    }

private double calculateSpeed(double mass, double powerCommand, double currentSpeed,
                                double grade){
Calculates speed

     // @ANDREW also used in TrackModel tests
   private int generateUnloading() {
        return new Random().nextInt(222 - 74) + 74;
    }

    public int getUnloading(){
        return unloading;
    }


Unused Code */


