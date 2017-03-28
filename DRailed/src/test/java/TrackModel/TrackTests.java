package TrackModel;

import TrackModel.Model.Station;
import TrainModel.Train;
import org.junit.Test;

import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by adzun_000 on 2/24/2017.
 */
public class TrackTests {

    @Test
    public void testingExample() {
        System.out.println("Hello Test Example");
        assertEquals(1, 1);
    }

    @Test
    public void testSetSpeedAndAuthority() {

        Track track = new Track("greenTrackLayout.csv");
        String signal = track.setSpeedAndAuthority("GREEN",1, 24, 20, 0);
        System.out.println(signal);
        assertEquals("0x118140", signal);

    }

    @Test
    public void testToggleSwitch() {

        Track track = new Track("greenTrackLayout.csv");
        track.toggleSwitch("GREEN", 1);

    }

    @Test
    public void testToggleCrossing() {

        Track track = new Track("greenTrackLayout.csv");
        track.toggleCrossing("GREEN", 1);

    }

    @Test
    public void testToggleLight() {

        Track track = new Track("greenTrackLayout.csv");
        track.toggleLight("GREEN", 1);

    }

    @Test
    public void testStationDepart() {

        Station station = new Station("TEST", 12, "LEFT");
        station.depart();
        assertNotSame(12, station.getDeparting());

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

    @Test
    public void testSetStationTimes(){

        Station station = new Station("TEST", 12, "LEFT");
        station.setTrainTimes("A[12:00]-D[1:30]");
        assertEquals("A[12:00]-D[1:30]", station.getTrainTimes());

    }

    @Test
    public void testProcessPLC(){

    }

}
