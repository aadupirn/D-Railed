package TrackModel;

//import MBO.java.Train;
import TrackController.TrackController;
import TrackModel.Model.*;
import TrainModel.Train;

import java.util.HashMap;

/**
 * Created by adzun_000 on 2/14/2017.
 */
public class Track {

    TrackController tc = null;
    TrackModel tm = null;

    public Track(){
        tm = new TrackModel();
        tm.importTrack("greenLine.csv");
    }

    public Track(String trackLayout){
        tm = new TrackModel(trackLayout);
    }

    public void setTrackController(TrackController tc){
        this.tc = tc;
    }

    public TrackController getTrackController(){
        return tc;
    }

    // @Track Controller: Sets safe speed and authority for a train on a rail
    public boolean setSpeedAndAuthority(String line, int blockId, double speed, int authority){
        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                l.getBlock(blockId).setSpeedAndAuthority(speed, authority);
                return true;
            }
        }

        return false;
    }

    public double readSpeed(Line line, int blockId){
        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)){
                l.getBlock(blockId).readSpeed();
            }
        }

        return -1;
    }

    public int readAuthority(Line line, int blockId){
        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)){
                return l.getBlock(blockId).readAuthority();
            }
        }

        return -1;
    }

    // @CTC: Places the train on the appropriate block coming from the Yard
    public int dispatchTrainOnTrack(String line, TrainModel.Train train) {

        Block block = getBlock(line, tm.dipatchTrain(line, train));

        if(block.getSwitch() != null){
            setSwitchState(block.getLine(), block.getSwitch().getSwitchNumber(), getTrackController().getPLCSwitch(block.getSwitch().getSwitchNumber()));
        }

        return block.getBlockNumber();

    }

    public Block getNextBlock(String line, Block currentBlock, boolean direction, Train train){

        Block nextBlock = tm.getBlock(line, currentBlock.getBlockNumber().intValue()).moveToNextBlock(train, direction);

        // PLC update old block
        if(currentBlock.getSwitch() != null){
            setSwitchState(line, currentBlock.getSwitch().getSwitchNumber(), getTrackController().getPLCSwitch(currentBlock.getSwitch().getSwitchNumber()));
        }
        if(currentBlock.getCrossing() != null){
            setCrossingState(line, currentBlock.getBlockNumber(), getTrackController().getPLCCrossing(currentBlock.getBlockNumber()));
        }
        if(currentBlock.getLight() != null){
            setLightState(line, currentBlock.getBlockNumber(), getTrackController().getPLCLight(currentBlock.getBlockNumber()));
        }

        // PLC update new block
        if(nextBlock.getSwitch() != null){
            setSwitchState(nextBlock.getLine(), nextBlock.getSwitch().getSwitchNumber(), getTrackController().getPLCSwitch(nextBlock.getSwitch().getSwitchNumber()));
        }
        if(nextBlock.getCrossing() != null){
            setCrossingState(nextBlock.getLine(), nextBlock.getBlockNumber(), getTrackController().getPLCCrossing(nextBlock.getBlockNumber()));
        }
        if(nextBlock.getLight() != null){
            setLightState(nextBlock.getLine(), nextBlock.getBlockNumber(), getTrackController().getPLCLight(nextBlock.getBlockNumber()));
        }

        tm.getBlock(currentBlock.getLine(), currentBlock.getBlockNumber()).setUnoccupied();
        tm.getBlock(nextBlock.getLine(), nextBlock.getBlockNumber()).setOccupied();

        return nextBlock;
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

    public boolean suggestTrainDirection(Block block, boolean direction, Switch aSwitch){

        Block up = block.getNextUpBlock();
        Block upBottom = tm.getBlock(block.getLine(), block.getNextUpBlockSwitchBottom());
        Block down = block.getNextDownBlock();
        Block downButtom = tm.getBlock(block.getLine(), block.getNextDownBlockSwitchBottom());

        if(aSwitch != null){
            if(aSwitch.getState().equals(SwitchState.TOP)) {
                if(direction == true && up.getDirection().contains("BI")){

                } else if (direction == true && up.getDirection().contains("UP")) {

                } else if (direction == false && down.getDirection().contains("BI")) {

                } else if(direction == false && down.getDirection().contains("DOWN")){

                }
            }else if(aSwitch.getState().equals(SwitchState.BOTTOM)){

            }
        }else{
            if(direction == true){

            }else if(direction == true){

            }else if(direction == false){

            }else if(direction == false){

            }
        }

        return false;
    }

    public Block getBlock(String line, int blockId){
        for(Line l : tm.getLines()){
            if(l.getLine().equals(line)) {
                for (Section s : l.getSections()) {
                    for (Block b : s.getBlocks()) {
                        if (b.getBlockNumber() == blockId) {
                            return b;
                        }
                    }
                }
            }
        }

        return null;
    }

    public Block getFromYardBlock(String line){
        return tm.getFromYardBlock(line);
    }

    public Block getToYardBlock(String line){
        return tm.getToYardBlock(line);
    }

    public void couple(String line, String setting){
        if(setting.equals("LOOSE")) {
            tm.looseCoupling(line);
        }else if(setting.equals("TIGHT")){
            tm.tightCoupling(line);
        }
    }

}

