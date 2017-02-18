package TrackModel.Model;

/**
 * Created by andrew on 1/17/2017.
 */

import MBO.java.Train;

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
    private String direction;

    // Infrastructure and Functionality
    private Train train;
    private boolean hasTrain;
    private Switch aSwitch;
    private Station station;
    private Crossing crossing;
    private String other;

    private Light light;

    // System State
    private String trackState;
    private boolean railState;
    private boolean circuitState;
    private boolean powerState;

    // Connected Blocks
    private Block nextBlock;
    private Block previousBlock;

    // To Train: Send Power To Train
    private Double setPointSpeed;
    private Double authority;

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

        this.hasTrain = false;

        // status
        this.powerState = false;
        this.railState = false;
        this.circuitState = false;
        this.trackState = "CLOSED";
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
        this.light = new Light(blockNumber);

        this.hasTrain = false;

        // status
        this.powerState = true;
        this.railState = true;
        this.circuitState = true;
        this.trackState = "OPEN";
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
        this.light = new Light(this.blockNumber);
    }

    public void trainEnter(Train newTrain){
        this.train = newTrain;
        this.hasTrain = true;
    }

    public Train getTrain(){
        return this.train;
    }

    public void trainExit(){
        this.train = null;
        this.hasTrain = false;
    }

    public boolean hasTrain(){
        return this.hasTrain;
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

    public Double getSetPointSpeed() {
        return setPointSpeed;
    }

    public void setSetPointSpeed(Double setPointSpeed) {
        this.setPointSpeed = setPointSpeed;
    }

    public Double getAuthority() {
        return authority;
    }

    public void setAuthority(Double authority) {
        this.authority = authority;
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
}


