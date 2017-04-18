package TrackModel;

import TrackController.TrackController;
import TrackModel.Model.Block;
import TrackModel.Model.Heater;
import TrackModel.Model.Station;
import TrackModel.Model.SwitchState;
import TrainModel.Train;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by adzun_000 on 2/24/2017.
 */
public class TrackTests {

    /*@Test
    public void testSetSpeedAndAuth(){

    }*/

    @Test
    public void testBlockOccupied(){
        Track track = new Track();

        Train train = null;

        try {
            train = new Train(1);
        }catch(Exception ioe){
            System.out.println("Encountered IO Exception");
        }

        Block start = track.getFromYardBlock("GREEN");
        //Block start = track.getFromYardBlock("RED");

        start.setTrain(train);

        Block b = track.getBlock("GREEN", start.getBlockNumber());
        //Block b = track.getBlock("RED", start.getBlockNumber());

        assertEquals(true, b.isOccupied());
    }

    @Test
    public void testBlockUnoccupied(){
        Track track = new Track();
        Block b = track.getBlock("GREEN", 3);
        //Block b = track.getBlock("RED", 3);

        assertEquals(false, b.isOccupied());
    }

    @Test
    public void testToggleSwitch() {

        Track track = new Track();
        String state = track.toggleSwitch("GREEN", 1);
        //String state = track.toggleSwitch("RED", 12);

        assertEquals(SwitchState.BOTTOM.toString(), state);

    }

    @Test
    public void testToggleCrossing() {

        Track track = new Track();
        boolean state = track.toggleCrossing("GREEN", 1);
        //boolean state = track.toggleCrossing("RED", 47);

        assertEquals(true, state);

    }

    @Test
    public void testToggleLight() {

        Track track = new Track();
        boolean state = track.toggleLight("GREEN", 1);
        //boolean state = track.toggleLight("RED", 1);

        assertEquals(false, state);

    }

    @Test
    public void testSetSwitch(){

        Track track = new Track();
        String state = track.setSwitchState("GREEN", 1, true);
        //String state = track.setSwitchState("RED", 12, true);

        assertEquals(SwitchState.TOP.toString(), state);

    }

    @Test
    public void testSetCrossing(){

        Track track = new Track();
        boolean state = track.setCrossingState("GREEN", 19, true);
        //boolean state = track.setCrossingState("RED", 47, true);

        assertEquals(true, state);

    }

    @Test
    public void testSetLights(){

        Track track = new Track();
        boolean state = track.setLightState("GREEN", 1, true);
        //boolean state = track.setLightState("RED", 9, true);

        assertEquals(true, state);

    }

    @Test
    public void testRegulateTemperature(){

        Heater heat = new Heater("PIT", 34);

        heat.setDesiredTemp(54);
        heat.toggleActive();
        heat.setHeatRate(10);

        // heat to 54 degrees
        heat.updateTemp();
        heat.updateTemp();

        // rail temp should stay at desired temp of 54
        heat.updateTemp();

        assertEquals(54.0, heat.getRailTemp());

    }

    @Test
    public void testCorrectFile(){

        Track track = new Track();
        assertEquals(true, track != null);

    }

    @Test
    public void testImportTrack(){

        Track track = new Track();
        assertEquals(true, track != null);

    }

    @Test
    public void testStationDepart() {

        Station station = new Station("TEST", 12, "LEFT");
        station.depart();
        assertNotSame(12, station.getDeparting());

    }

    @Test
    public void testSetStationTimes(){

        Station station = new Station("TEST", 12, "LEFT");
        station.setTrainTimes("12:00-1:00");

        assertEquals("12:00-1:00", station.getTrainTimes());

    }

    @Test
    public void testCloseBlock(){

        Track track = new Track();

        track.getBlock("GREEN", 3).setTrackState("CLOSED");
        track.getBlock("RED", 3).setTrackState("CLOSED");

        assertEquals("CLOSED", track.getBlock("GREEN", 3).getTrackState());
        //assertEquals("CLOSED", track.getBlock("RED", 3).getTrackState());

    }

    @Test
    public void testBreakRail() {

        Track track = new Track();
        track.breakRail("GREEN",2);
        track.breakRail("RED",2);

        assertEquals(false, track.getBlock("GREEN", 2).isRailState());
        //assertEquals(false, track.getBlock("RED", 2).isRailState());
    }

    @Test
    public void testBreakCircuit() {

        Track track = new Track();
        track.breakCircuit("GREEN", 2);
        track.breakCircuit("RED", 2);

        assertEquals(false, track.getBlock("GREEN", 2).isCircuitState());
        //assertEquals(false, track.getBlock("RED", 2).isCircuitState());

    }

    @Test
    public void testBreakPower() {

        Track track = new Track();
        track.breakPower("GREEN",2);
        track.breakPower("RED",2);


        assertEquals(false, track.getBlock("GREEN", 2).isPowerState());
        //assertEquals(false, track.getBlock("RED", 2).isPowerState());

    }

    @Test
    public void testFixRail() {

        Track track = new Track();

        track.breakRail("GREEN", 2);
        track.fixRail("GREEN", 2);
        track.breakRail("RED", 2);
        track.fixRail("RED", 2);

        assertEquals(true, track.getBlock("GREEN", 2).isRailState());
        //assertEquals(true, track.getBlock("RED", 2).isRailState());

    }
    @Test
    public void testFixCircuit() {

        Track track = new Track();
        track.breakCircuit("GREEN", 2);
        track.fixCircuit("GREEN", 2);
        track.breakCircuit("RED", 2);
        track.fixCircuit("RED", 2);

        assertEquals(true, track.getBlock("GREEN", 2).isCircuitState());
        //assertEquals(true, track.getBlock("RED", 2).isCircuitState());

    }

    @Test
    public void testFixPower(){

        Track track = new Track();
        track.breakPower("GREEN", 2);
        track.fixPower("GREEN", 2);
        track.breakPower("RED", 2);
        track.fixPower("RED", 2);


        assertEquals(true, track.getBlock("GREEN", 2).isPowerState());
        //assertEquals(true, track.getBlock("RED", 2).isPowerState());

    }

    @Test
    public void testLinkedTrackModel(){

        Track track = new Track();
        Block startBlock = track.getFromYardBlock("GREEN");
        // Block startBlock = track.getFromYardBlock("RED");
        Block nextBlock = null;

        boolean dir = startBlock.canMoveToBlock(true);

        nextBlock = startBlock.getNextBlock(dir);

        assertEquals(63, nextBlock.getBlockNumber().intValue());
        //assertEquals(9, nextBlock.getBlockNumber().intValue());

    }

    @Test
    public void testBlockLookAhead(){

        Track track = new Track();
        Block startBlock = track.getFromYardBlock("GREEN");
        // Block startBlock = track.getFromYardBlock("RED");

        List<Block> blist = track.tm.lookAhead(startBlock, true, 5);

        Block lastBlock = blist.get(4);

        assertEquals(67, lastBlock.getBlockNumber().intValue());
        //assertEquals(5, lastBlock.getBlockNumber().intValue());

    }

}
