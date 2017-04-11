package TrackModel.Model;

/**
 * Created by andrew on 1/17/2017.
 */


import TrainModel.Train;

import static java.lang.Math.abs;

/***
 * Blocks represent track infrastructure and physical attributes. They serve as pieces and the
 * building "blocks" of the overall track model.
 *
 *  ex. block: 1
 *      section: A
 *      line: Green
 *      length: 100 m
 *      grade: 0x5 %
 *      speedLimit: 55 km/hr
 *      elevation: 1.0 m
 *      cumulative elevation: 1.5 m
 *      temperature: 60 F
 *      direction: BI
 *      trainData: Train 1
 *      train: true
 *
 */

public class Block {

    // Application.Track.Model.Block Identifiers
    private String line;
    private String section;
    private Integer blockNumber;

    // Application.Track.Model.Block Configurable Parameters
    private Double length;
    private Double grade;
    private Double speedLimit;
    private Double elevation;
    private Double cumulativeElevation;
    private Double temperature;

    // Infrastructure and Functionality
    private Train train;
    private boolean occupied;
    private Switch aSwitch;
    private Station station;
    private Crossing crossing;
    private String other;

    private Light light;
    private Beacon beacon;

    // System State
    private String trackState;
    private boolean railState;
    private boolean circuitState;
    private boolean powerState;

    // authority and speed
    private int authority;
    private Double speed;

    // Connected Blocks
    private String direction;
    private int nextUpBlockNumber;
    private int nextDownBlockNumber;
    private int nextSwitchBlockNumber;
    private Block nextUpBlock;
    private Block nextDownBlock;
    private Block nextSwitchBlock;
    private boolean nextSwitchBlockDir; //true=UP false=DOWN
    private boolean fromYard;
    private boolean toYard;

    public Block(){
        // basic info
        this.line = null;
        this.section = null;
        this.blockNumber = null;
        this.length = null;
        this.speedLimit = null;
        this.grade = null;
        this.elevation = null;
        this.cumulativeElevation = null;
        this.direction = null;
        this.temperature = null;

        // infrastructure
        this.train = null;
        this.aSwitch = null;
        this.crossing = null;
        this.station = null;
        this.light = null;

        // train
        this.occupied = false;
        this.speed = 0.0;
        this.authority = 0;

        // status
        this.powerState = false;
        this.railState = false;
        this.circuitState = false;
        this.trackState = "CLOSED";

        // block connection
        this.nextUpBlockNumber = -1;
        this.nextDownBlockNumber = -1;
        this.nextSwitchBlockNumber = -1;
        this.nextUpBlock = null;
        this.nextDownBlock = null;
        this.nextSwitchBlock = null;
        this.nextSwitchBlockDir = false;
        this.fromYard = false;
        this.toYard = false;

    }

    public Block(String line, String section, Integer blockNumber){
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;

        // infrastructure
        this.train = null;
        this.aSwitch = null;
        this.crossing = null;
        this.station = null;
        this.light = null;

        // train
        this.occupied = false;
        this.speed = 0.0;
        this.authority = 0;

        // status
        this.powerState = true;
        this.railState = true;
        this.circuitState = true;
        this.trackState = "OPEN";

        // block connection
        this.nextUpBlockNumber = -1;
        this.nextDownBlockNumber = -1;
        this.nextSwitchBlockNumber = -1;
        this.nextUpBlock = null;
        this.nextDownBlock = null;
        this.nextSwitchBlock = null;
        this.nextSwitchBlockDir = false;
        this.fromYard = false;
        this.toYard = false;

    }

    public void setParameters(Double length, Double grade, Double speedLimit, Double elevation, Double cumElevation, Double temperature, String direction){
        this.length = length;
        this.grade = grade;
        this.speedLimit = new Double(Math.round((speedLimit*1000.0)/3600.0)); // (kilometer/hour * meters/kilometer) * hour/second
        this.elevation = elevation;
        this.cumulativeElevation = cumElevation;
        this.temperature = temperature;
        this.direction = direction;
    }

    public void setInfrastructure(Station station, Switch aSwitch, Crossing crossing, String other){
        this.station = station;
        this.aSwitch = aSwitch;
        this.crossing = crossing;
        this.other = other;

        if(station != null || aSwitch != null){
            this.light = new Light(this.blockNumber);
        }
    }

    public Train getTrain(){
        return this.train;
    }

    public void setTrain(Train train){
        this.occupied = true;
        this.train = train;
    }

    public boolean isOccupied(){
        return this.occupied;
    }

    public void setBeacon(String message){
        this.beacon = new Beacon(blockNumber, message);
    }

    public Beacon getBeacon(){
        return this.beacon;
    }

    public Integer getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Double getLength(){
        return this.length;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Double speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getCumulativeElevation() {
        return cumulativeElevation;
    }

    public void setCumulativeElevation(Double cumulativeElevation) {
        this.cumulativeElevation = cumulativeElevation;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTrackState() {
        return trackState;
    }

    public void setTrackState(String trackState) {
        this.trackState = trackState;
    }

    public void toggleTrackState(){
        if(trackState.equals("OPEN")){
            trackState = "CLOSED";
            this.occupied = true;
        }else{
            trackState = "OPEN";
            this.occupied = false;
        }
    }

    public boolean isRailState() {
        return railState;
    }

    public void setRailState(boolean railState) {
        this.railState = railState;
        if(railState == false){
            this.occupied = true;
            this.trackState = "CLOSED";
        }else{
            this.occupied = false;
            this.trackState = "OPEN";
        }
    }

    public void toggleRailState(){
        this.railState = (!this.railState);
        if(this.railState == false){
            this.occupied = true;
            this.trackState = "CLOSED";
        }else{
            this.occupied = false;
            this.trackState = "OPEN";
        }
    }

    public boolean isCircuitState() {
        return circuitState;
    }

    public void setCircuitState(boolean circuitState) {
        this.circuitState = circuitState;
        if(this.circuitState == false){
            this.occupied = true;
            this.trackState = "CLOSED";
        }else{
            this.occupied = false;
            this.trackState = "OPEN";
        }
    }

    public void toggleCircuitState(){
        this.circuitState = (!this.circuitState);
        if(this.circuitState == false){
            this.occupied = true;
            this.trackState = "CLOSED";
        }else{
            this.occupied = false;
            this.trackState = "OPEN";
        }
    }

    public boolean isPowerState() {
        return powerState;
    }

    public void setPowerState(boolean powerState) {
        this.powerState = powerState;
        if(this.powerState == false){
            this.occupied = true;
            this.trackState = "CLOSED";
        }else{
            this.occupied = false;
            this.trackState = "OPEN";
        }
    }

    public void togglePowerState(){
        this.powerState = (!this.powerState);
        if(this.powerState == false){
            this.occupied = true;
            this.trackState = "CLOSED";
        }else{
            this.occupied = false;
            this.trackState = "OPEN";
        }
    }

    public Switch getSwitch() {
        return aSwitch;
    }

    public void setSwitch(Switch aSwitch) {
        this.aSwitch = aSwitch;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Crossing getCrossing() {
        return crossing;
    }

    public void setCrossing(Crossing crossing) {
        this.crossing = crossing;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public boolean getLightStatus(){
        return this.light.isActive();
    }

    public void toggleLight(){
        this.light.toggleActive();
    }

    public String toString(){
        return section + blockNumber;
    }

    public Block moveToNextBlock(Train train, boolean direction) {

        // remove train from current block
        this.occupied = false;
        this.train = null;

        // calculation based
        int authority = train.GetAuthority();
        int cur = this.getBlockNumber();
        int next = this.getNextUpBlockNumber();
        int nswitch = this.getNextSwitchBlockNumber();

        Block nextBlock = null;

        // if the direction of travel is UP and the track runs the same direction
        if (direction == true && nextUpBlock == null && nextSwitchBlock != null){
            System.out.println("Up And Switch");
            nextBlock = nextSwitchBlock;

        } else if (direction == false && nextDownBlock == null && nextSwitchBlock != null){
            System.out.println("Down And Switch");
            nextBlock = nextSwitchBlock;

        }if (direction == true && nextUpBlock != null) {

            if(nextSwitchBlock != null){
                int cndiff = Math.abs(authority - next);
                int csdiff = Math.abs(authority - nswitch);

                if(cndiff < csdiff){
                    nextBlock = nextUpBlock;
                    System.out.println("Switch And Up");
                }else {
                    nextBlock = nextSwitchBlock;
                    System.out.println("Switch And Switch [Up]");
                }
            }else{
                // move train to next block
                System.out.println("Up");
                nextBlock = nextUpBlock;
            }



            // if the direction of travel is DOWN and the track runs the same direction
        } else if (direction == false && nextDownBlock != null) {

            if(nextSwitchBlock != null){
                int cndiff = Math.abs(authority - next);
                int csdiff = Math.abs(authority - nswitch);

                if(cndiff < csdiff) {
                    nextBlock = nextDownBlock;
                    System.out.println("Switch And Down");
                }else{
                    nextBlock = nextSwitchBlock;
                    System.out.println("Switch And Switch [Down]");
                }

            }else{
                // move train to next block
                System.out.println("Down");
                nextBlock = nextDownBlock;
            }


        }

        nextBlock.setOccupied();
        nextBlock.setTrain(train);

        return nextBlock;
    }

    public Block getNextBlock(boolean direction){

        Block nextBlock = null;

        if(direction){
            if(nextUpBlock == null){
                nextBlock = nextSwitchBlock;
            }
            nextBlock = nextUpBlock;
        }else{
            if(nextDownBlock == null){
                nextBlock = nextSwitchBlock;
            }

            nextBlock = nextDownBlock;
        }

        return nextBlock;
    }

    public boolean canMoveToBlock(boolean direction){

        System.out.println("DIRECTION:" + direction);

        if(direction == true){
            if (nextUpBlockNumber != -1){
                System.out.println("GO UP");
                return true;
            }else{
                System.out.println("GO DOWN");
                return false;
            }
        }else if(direction == false){
            if (nextDownBlockNumber != -1){
                System.out.println("GO DOWN");
                return false;
            }else{
                System.out.println("GO UP");
                return true;
            }
        }else{
            return direction;
        }

    }

    public Block getNextUpBlock() {
        if(nextUpBlockNumber == -1) {
            System.out.println("This block is UNI directional and a train can only travel DOWN");
        }
        return nextUpBlock;
    }

    public void setNextUpBlock(Block nextUpBlock) {
        this.nextUpBlock = nextUpBlock;
    }

    public Block getNextDownBlock() {
        if(nextUpBlockNumber == -1) {
            System.out.println("This block is UNI directional and a train can only travel UP");
        }
        return nextDownBlock;
    }

    public void setNextDownBlock(Block nextDownBlock) {
        this.nextDownBlock = nextDownBlock;
    }

    public void setNextSwitchBlock(Block nextSwitchBlock){
        this.nextSwitchBlock = nextSwitchBlock;
    }

    public Block getNextSwitchBlock(){
        return this.nextSwitchBlock;
    }

    public void setSpeedAndAuthority(Double speed, int authority){
        this.speed = speed;
        this.authority = authority;
    }

    public Double readSpeed(){
        return this.speed;
    }

    public int readAuthority(){
        return this.authority;
    }

    public void setOccupied(){
        this.occupied = true;
    }

    public void setUnoccupied(){
        this.occupied = false;
    }

    public int getNextUpBlockNumber() {
        return this.nextUpBlockNumber;
    }

    public void setNextUpBlockNumber(int nextUpBlockNumber) {
        this.nextUpBlockNumber = nextUpBlockNumber;
    }

    public int getNextDownBlockNumber() {
        return this.nextDownBlockNumber;
    }

    public void setNextDownBlockNumber(int nextDownBlockNumber) {
        this.nextDownBlockNumber = nextDownBlockNumber;
    }

    public int getNextSwitchBlockNumber() {
        return this.nextSwitchBlockNumber;
    }

    public boolean getNextSwitchRedirect(){return this.nextSwitchBlockDir; }

    public void setNextSwitch(int nextSwitchBlockNumber, boolean dir){
        this.nextSwitchBlockNumber = nextSwitchBlockNumber;
        this.nextSwitchBlockDir = dir;
    }

    public void setFromYard(){
        this.fromYard = true;
    }

    public void setToYard(){
        this.toYard = true;
    }

    public boolean isFromYard(){
        return this.fromYard;
    }

    public boolean isToYard(){
        return this.toYard;
    }
}