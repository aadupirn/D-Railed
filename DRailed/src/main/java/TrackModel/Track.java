package TrackModel;

import MBO.java.Train;
import TrackController.TrackController;
import TrackModel.Model.*;

import java.util.HashMap;

/**
 * Created by adzun_000 on 2/14/2017.
 */
public class Track {

    //TrackController tc = null;
    TrackModel tm = null;
    private HashMap<String, String> signals;

    public Track(){
        //tc = new TrackController();
        tm = new TrackModel();
        tm.importTrack("redTrackLayout.csv");
        tm.importTrack("greenTrackLayout.csv");
        initTrackSignals();
    }

    public Track(String trackLayout){
        //tc = new TrackController();
        tm = new TrackModel(trackLayout);
        initTrackSignals();

    }

    private void initTrackSignals() {
        signals = new HashMap<>();

        for(Line l :tm.getLines()){
            signals.put(l.getLine(), "0x000000");
        }
    }

    // @Track Controller: Sets safe speed and authority for a train on a rail
    public String setSpeedAndAuthority(String line, int trainId, int speed, int authority, int status){

        String railSignal = signals.get(line);
        railSignal = "0x" + Integer.toHexString(trainId) + Integer.toHexString(speed) + Integer.toHexString(authority) + Integer.toHexString(status);
        signals.put(line, railSignal);

        return railSignal;

    }

    // @CTC: Places the train on the appropriate block coming from the Yard
    public int dispatchTrainOnTrack(String line, Train train) {

        System.out.println("Track Dispatch");
        System.out.println("Line: " + line);
        System.out.println("Train: " + train);
        return tm.dipatchTrain(line, train);

    }

    public int findTrain(String line, int trainId){

        return tm.findTrain(line, trainId);

    }

    public String toggleSwitch(String line, int switchId){

        String state = "ERROR";

        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                for (Section s : l.getSections()) {
                    for (Block b : s.getBlocks()) {
                        if (b.getSwitch() != null && b.getSwitch().getSwitchNumber() == switchId) {
                            b.getSwitch().toggleState();
                            state = b.getSwitch().getState().toString();
                        }
                    }
                }
            }
        }

        return state;

    }

    public String setSwitchState(String line, int switchId, boolean state){

        String out = "ERROR";
        SwitchState switchState;

        if(state){
            switchState = SwitchState.TOP;
        }else{
            switchState = SwitchState.BOTTOM;
        }

        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                for (Section s : l.getSections()) {
                    for (Block b : s.getBlocks()) {
                        if (b.getSwitch() != null && b.getSwitch().getSwitchNumber() == switchId) {
                            b.getSwitch().setSwitchState(switchState);
                            out = b.getSwitch().getState().toString();
                        }
                    }
                }
            }
        }

        return out;
    }

    public boolean toggleCrossing(String line, int crossingId){

        boolean state = true;

        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                for (Section s : l.getSections()) {
                    for (Block b : s.getBlocks()) {
                        if (b.getCrossing() != null && b.getCrossing().getCrossingNumber() == crossingId) {
                            b.getCrossing().toggleActive();
                            state = b.getCrossing().isActive();
                        }
                    }
                }
            }
        }

        return state;

    }

    public boolean setCrossingState(String line, int crossingId, boolean state){

        boolean out = false;

        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                for (Section s : l.getSections()) {
                    for (Block b : s.getBlocks()) {
                        if (b.getCrossing() != null && b.getCrossing().getCrossingNumber() == crossingId) {
                            b.getCrossing().setActive(state);
                            out = b.getCrossing().isActive();
                        }
                    }
                }
            }
        }

        return out;

    }

    public boolean toggleLight(String line, int lightId){

        boolean state = true;

        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                for (Section s : l.getSections()) {
                    for (Block b : s.getBlocks()) {
                        if (b.getLight() != null && b.getLight().getLightNumber() == lightId) {
                            b.getLight().toggleActive();
                            state = b.getLight().isActive();
                        }
                    }
                }
            }
        }

        return state;

    }

    public boolean setLightState(String line, int lightId, boolean state){

        boolean out = false;

        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                for (Section s : l.getSections()) {
                    for (Block b : s.getBlocks()) {
                        if (b.getLight() != null && b.getLight().getLightNumber() == lightId) {
                            b.getLight().setActive(state);
                            out = b.getLight().isActive();
                        }
                    }
                }
            }
        }

        return out;

    }

    public void setStationTime(int stationId, String trainTime){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getStation() != null && b.getStation().getStationNumber() == stationId){
                        b.getStation().setTrainTimes(trainTime);
                    }
                }
            }
        }
    }

    public void breakRail(int blockId){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getBlockNumber() == blockId){
                        b.setRailState(false);
                    }
                }
            }
        }
    }

    public void breakPower(int blockId){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getBlockNumber() == blockId){
                        b.setPowerState(false);
                    }
                }
            }
        }
    }

    public void breakCircuit(int blockId){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getBlockNumber() == blockId){
                        b.setCircuitState(false);
                    }
                }
            }
        }
    }

    public void fixRail(int blockId){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getBlockNumber() == blockId){
                        b.setRailState(true);
                    }
                }
            }
        }
    }

    public void fixPower(int blockId){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getBlockNumber() == blockId){
                        b.setPowerState(true);
                    }
                }
            }
        }
    }

    public void fixCircuit(int blockId){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getBlockNumber() == blockId){
                        b.setCircuitState(true);
                    }
                }
            }
        }
    }

    public Block getBlock(int blockId){
        for(Line l : tm.getLines()){
            for(Section s : l.getSections()){
                for(Block b : s.getBlocks()){
                    if(b.getBlockNumber() == blockId){
                        return b;
                    }
                }
            }
        }

        return null;
    }


}

