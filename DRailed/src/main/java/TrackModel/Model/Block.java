package TrackModel.Model;

/**
 * Created by andrew on 1/17/2017.
 */


import TrainModel.Train;

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
    private int nextUpBlockSwitchBottom;
    private int nextDownBlockNumber;
    private int nextDownBlockSwitchBottom;
    private Block nextUpBlock;
    private Block nextDownBlock;
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
        this.nextUpBlockSwitchBottom = -1;
        this.nextDownBlockNumber = -1;
        this.nextDownBlockSwitchBottom = -1;
        this.nextUpBlock = null;
        this.nextDownBlock = null;
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
        this.nextUpBlockSwitchBottom = -1;
        this.nextDownBlockNumber = -1;
        this.nextDownBlockSwitchBottom = -1;
        this.nextUpBlock = null;
        this.nextDownBlock = null;
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
        }else{
            trackState = "OPEN";
        }
    }

    public boolean isRailState() {
        return railState;
    }

    public void setRailState(boolean railState) {
        this.railState = railState;
    }

    public void toggleRailState(){
        this.railState = (!this.railState);
    }

    public boolean isCircuitState() {
        return circuitState;
    }

    public void setCircuitState(boolean circuitState) {
        this.circuitState = circuitState;
    }

    public void toggleCircuitState(){
        this.circuitState = (!this.circuitState);
    }

    public boolean isPowerState() {
        return powerState;
    }

    public void setPowerState(boolean powerState) {
        this.powerState = powerState;
    }

    public void togglePowerState(){
        this.powerState = (!this.powerState);
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

    /**
     *
     * @param direction - the direction it is traveling up = true; down = false
     * @return
     */
    public Block moveToNextBlock(boolean direction){

        // remove train from current block
        this.occupied = false;
        Block nextBlock = null;

        // if the direction of travel is UP and the track runs the same direction
        if(direction == true && nextUpBlock != null){

            // move train to next block
            nextUpBlock.setOccupied();
            nextBlock = nextUpBlock;

        // if the direction of travel is DOWN and the track runs the same direction
        }else if(direction == false && nextDownBlock != null){

            // move train to next block
            nextDownBlock.setOccupied();
            nextBlock = nextDownBlock;

        }

        return nextBlock;
    }

    public Block getNextBlock(boolean direction){

        Block nextBlock = null;

        if(direction){
            nextBlock = nextUpBlock;
        }else{
            nextBlock = nextDownBlock;
        }

        return nextBlock;
    }

    public boolean canMoveToBlock(boolean direction){

        if(direction){
            return nextUpBlockNumber != -1;
        }else{
            return nextDownBlockNumber != -1;
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

    public int getNextUpBlockNumber() {
        return nextUpBlockNumber;
    }

    public void setNextUpBlockNumber(int nextUpBlockNumber) {
        this.nextUpBlockNumber = nextUpBlockNumber;
    }

    public int getNextDownBlockNumber() {
        return nextDownBlockNumber;
    }

    public void setNextDownBlockNumber(int nextDownBlockNumber) {
        this.nextDownBlockNumber = nextDownBlockNumber;
    }

    public int getNextUpBlockSwitchBottom() {
        return nextUpBlockSwitchBottom;
    }

    public void setNextUpBlockSwitchBottom(int nextUpBlockSwitchBottom) {
        this.nextUpBlockSwitchBottom = nextUpBlockSwitchBottom;
    }

    public int getNextDownBlockSwitchBottom() {
        return nextDownBlockSwitchBottom;
    }

    public void setNextDownBlockSwitchBottom(int nextDownBlockSwitchBottom) {
        this.nextDownBlockSwitchBottom = nextDownBlockSwitchBottom;
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


