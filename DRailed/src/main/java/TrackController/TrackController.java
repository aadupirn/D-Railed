package TrackController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import TrackController.Classes.*;
import TrackController.UI.*;
import TrackModel.Model.*;
<<<<<<< HEAD

=======
import TrainModel.Train;
import TrackModel.Track;
import DTime.DTime;
>>>>>>> master

/**
 * Created by Jonathan on 2/3/17.
 */
public class TrackController {

    //Class Objects
    private PLC myPLC;
    private Block[] Blocks;
    private TrackModel model;
    private int ID, startBlock, endBlock;
    private String line;
    private ArrayList<Block> blocks;
    private boolean trackComms, ctcComms, isLineMain;
<<<<<<< HEAD
=======
    private Queue<String> messageQueue;
    private TrackControllerUI ui;
    private DTime dTime;
>>>>>>> master


    public TrackController(DTime iDTime) throws IOException
    {
        trackComms = true;
        dTime = iDTime;
        ctcComms = true;
        TrackControllerUI ui = new TrackControllerUI(this);

    }


    public void setPLC(File file) {
        this.myPLC = new PLC(file,Blocks);
        System.out.println("\n\nPLC Valid: " + myPLC.isValid());
    }

    public void setModel(TrackModel model) {
        this.model = model;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
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

    public boolean isLineMain() {
        return isLineMain;
    }

    public void setLineMain(boolean lineMain) {
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
<<<<<<< HEAD
=======

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

    public boolean dispatchTrain(int start, int numberOfCarts, int newAuthority, Double newSpeed, int newID) throws Exception
    {
        if (isLineMain)
        {
            Train train = new Train(start,numberOfCarts,newAuthority, newSpeed, newID, this.track);
            track.dispatchTrainOnTrack(this.line,train);
            dTime.addTC(train.GetTrainController());
            return true;
        }
        return false;
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
>>>>>>> master
}