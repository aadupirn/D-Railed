package TrackController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

import TrackController.Classes.*;
import TrackController.UI.*;
import TrackModel.Model.*;
import TrainModel.Train;
import TrackModel.Track;

/**
 * Created by Jonathan on 2/3/17.
 */
public class TrackController {

    //Class Objects
    private PLC myPLC;
    private Track track;
    private int ID, startBlock, endBlock;
    private String line;
    private boolean trackComms, ctcComms, isLineMain;
    private Queue<String> messageQueue;
    private TrackControllerUI ui;


    public TrackController() throws IOException
    {
        trackComms = true;
        ctcComms = true;
        ui = new TrackControllerUI(this);
        track = new Track("greenLine.csv");
        line = "GREEN";
        this.startBlock=1;
        this.endBlock=152;

    }

    public void showUI()
    {
        ui.showUI();
    }
    public void hideUI()
    {
        ui.hideUI();
    }

    public TrackController(String line, int startBlock, int endBlock)
    {
        trackComms = true;
        ctcComms = true;
        this.line = line;
        this.startBlock=startBlock;
        this.endBlock=endBlock;

        if (line.equals("GREEN"))
        {
            if (endBlock==152)
                this.isLineMain=true;
            else
                this.isLineMain=false;
        }
        else
        {
            if (endBlock==77)
                this.isLineMain=true;
            else
                this.isLineMain=false;
        }

        ui = new TrackControllerUI(this);

    }


    public void setPLC(File file) {
        Block[] b = new Block[153];
        for (int i = startBlock; i < endBlock; i++)
        {
            b[i] = track.getBlock(this.line,i);
        }
        this.myPLC = new PLC(file, b);
        System.out.println("\n\nPLC Valid: " + myPLC.isValid());
    }

    public void setTrack(Track t) {
        this.track = t;
    }



    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setTrackComms(boolean trackComms) {
        this.trackComms = trackComms;
    }

    public boolean hasTrackComms() {
        return trackComms;
    }

    public void setCtcComms(boolean ctcComms) {
        this.ctcComms = ctcComms;
    }

    public boolean hasCtcComms() {
        return ctcComms;
    }

    public int getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(int startBlock) {
        this.startBlock = startBlock;
    }

    public int getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(int endBlock) {
        this.endBlock = endBlock;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public boolean isMainController() {
        return isLineMain;
    }

    public void setMainController(boolean lineMain) {
        isLineMain = lineMain;
    }

    public boolean hasBlock(String line, int id)
    {
        if (line.equals(this.line))
        {
            if (id < this.endBlock && id > this.startBlock)
                return true;
        }
        return false;
    }

    public void setSpeedAndAuthority(int trainID, double speed, int authority)
    {
        //TODO get this shit working, either set the speed and authority for a block or overwrite occupied blocks?
        //TODO try maybe having something call a function on the Train?
    }

    public void Update()
    {
        if (!messageQueue.isEmpty())
        {
            String message = messageQueue.remove();
        }

        ui.Update();
    }

    public void dispatchTrain(int start, int numberOfCarts, int newAuthority, Double newSpeed, int newID) throws Exception
    {
        double x = 0; //TODO delete me when he changes authority to int
        Train train = new Train(start,numberOfCarts,x, newSpeed, newID, this.track);
        if (isLineMain)
        {
            track.dispatchTrainOnTrack(this.line,train);
        }
    }

    public boolean getOccupancy(int blockID)
    {
        return track.getBlock(this.line,blockID).isOccupied();
    }

    public String[] getBlockInfo(int blockID)
    {
        String[] values = new String[4];
        System.out.println(blockID);
        Block b = track.getBlock(this.line,blockID);
        if (b.isOccupied())
            values[0] = "Occupied";
        else
            values[0] = "Not Occupied";
        try {
            if (b.getCrossing().isActive())
                values[1] = "On";
            else
                values[1] = "Off";
        } catch(NullPointerException e) {
            values[1] = "N/A";
        }
        try {
            if (b.getLight().isActive())
                values[2] = "Green";
            else
                values[2] = "Red";
        } catch(NullPointerException e) {
            values[2] = "N/A";
        }
        try {
            values[3] = b.getSwitch().getSwitchNumber().toString();
        } catch(NullPointerException e) {
            values[3] = "N/A";
        }
        return values;
    }

    public String[] getSwitchInfo(int switchID)
    {
        String[] values = new String[4];
        Block b;
        Switch s;
        int blockID = -1;
        for (int i = startBlock; i <= endBlock; i++)
        {
            b = track.getBlock(this.line,i);
            if (b.getSwitch() != null) {
                if (b.getSwitch().getSwitchNumber() == switchID) {
                    blockID = b.getBlockNumber();
                    break;
                }
            }
        }

        if (blockID > 0) {
            b = track.getBlock(this.line, blockID);
            s = b.getSwitch();
            values[0] = s.getMain().toString();
            values[1] = s.getTop().toString();
            values[2] = s.getBottom().toString();
            if (s.getSwitchInfo().equals(SwitchState.TOP))
                values[3] = s.getTop().toString();
            else
                values[3] = s.getTop().toString();
        }
        return values;
    }
}