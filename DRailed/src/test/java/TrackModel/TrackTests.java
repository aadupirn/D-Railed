package TrackModel;

import TrackModel.Model.Block;
import TrackModel.Model.Heater;
import TrackModel.Model.Station;
import TrackModel.Model.SwitchState;
import TrainModel.Train;
import org.junit.Test;

import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by adzun_000 on 2/24/2017.
 */
public class TrackTests {

    @Test
    public void testSetSpeedAndAuthority() {

        Track track = new Track("greenTrackLayout.csv");
        String signal = track.setSpeedAndAuthority("GREEN",1, 24, 20, 0);
        assertEquals("0x118140", signal);

    }

    @Test
    public void testSetSpeedAndAuth_ExpectTrainMove(){

    }

    @Test
    public void testBlockOccupied(){
        Track track = new Track("greenTrackLayout.csv");
        Train train = new Train(1);

        int blockNumber = track.dispatchTrainOnTrack("GREEN", train);

        Block b = track.getBlock(blockNumber);

        assertEquals(true, b.isOccupied());
    }

    @Test
    public void testBlockUnoccupied(){
        Track track = new Track("greenTrackLayout.csv");
        Block b = track.getBlock(3);
        assertEquals(false, b.isOccupied());
    }

    @Test
    public void testToggleSwitch() {

        Track track = new Track("greenTrackLayout.csv");
        String state = track.toggleSwitch("GREEN", 1);

        assertEquals(SwitchState.BOTTOM.toString(), state);

    }

    @Test
    public void testToggleCrossing() {

        Track track = new Track("greenTrackLayout.csv");
        boolean state = track.toggleCrossing("GREEN", 1);

        assertEquals(true, state);

    }

    @Test
    public void testToggleLight() {

        Track track = new Track("greenTrackLayout.csv");
        boolean state = track.toggleLight("GREEN", 1);

        assertEquals(false, state);

    }

    @Test
    public void testSetSwitch(){

        Track track = new Track("greenTrackLayout.csv");
        String state = track.setSwitchState("GREEN", 1, true);

        assertEquals(SwitchState.TOP.toString(), state);

    }

    @Test
    public void testSetCrossing(){

        Track track = new Track("greenTrackLayout.csv");
        boolean state = track.setCrossingState("GREEN", 19, true);

        assertEquals(true, state);

    }

    @Test
    public void testSetLights(){

        Track track = new Track("greenTrackLayout.csv");
        boolean state = track.setLightState("GREEN", 1, true);

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

        Track track = new Track("greenTrackLayout.csv");
        assertEquals(true, track != null);

    }

    @Test
    public void testImportTrack(){

        Track track = new Track("greenTrackLayout.csv");
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

        Track track = new Track("greenTrackLayout.csv");

        track.getBlock(3).setTrackState("CLOSED");

        assertEquals("CLOSED", track.getBlock(3).getTrackState());

    }

    @Test
    public void breakRail() {

        Track track = new Track("greenTrackLayout.csv");
        track.breakRail(2);

        assertEquals(false, track.getBlock(2).isRailState());

    }

    @Test
    public void breakCircuit() {

        Track track = new Track("greenTrackLayout.csv");
        track.breakCircuit(2);

        assertEquals(false, track.getBlock(2).isCircuitState());

    }

    @Test
    public void breakPower() {

        Track track = new Track("greenTrackLayout.csv");
        track.breakPower(2);

        assertEquals(false, track.getBlock(2).isPowerState());

    }

    @Test
    public void fixRail() {

        Track track = new Track("greenTrackLayout.csv");
        track.breakRail(2);
        track.fixRail(2);

        assertEquals(true, track.getBlock(2).isRailState());

    }
    @Test
    public void fixCircuit() {

        Track track = new Track("greenTrackLayout.csv");
        track.breakCircuit(2);
        track.fixCircuit(2);

        assertEquals(true, track.getBlock(2).isCircuitState());

    }

    @Test
    public void fixPower(){

        Track track = new Track("greenTrackLayout.csv");
        track.breakPower(2);
        track.fixPower(2);

        assertEquals(true, track.getBlock(2).isPowerState());

    }

}
