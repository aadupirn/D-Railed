package TrackController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import TrackController.Classes.*;
import TrackController.UI.*;
import TrackModel.Model.*;
import TrainModel.Train;
import TrackModel.Track;
import DTime.DTime;

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
    private LinkedBlockingDeque<ThreeBaudMessage> messageQueue;
    private TrackControllerUI ui;
    private DTime dTime;


    public TrackController(DTime iDTime) throws IOException
    {
        trackComms = true;
        dTime = iDTime;
        ctcComms = true;
        track = new Track("greenLine.csv");
        line = "GREEN";
        isLineMain = true;
        messageQueue = new LinkedBlockingDeque<>();
        this.startBlock=1;
        this.endBlock=152;

        // lastly, make the UI
        ui = new TrackControllerUI(this);

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


    public boolean plcLoaded()
    {
        if (myPLC==null)
            return false;
        if(!myPLC.isValid())
            return false;
        return true;
    }
    public void setPLC(File file) {
        Block[] b = new Block[153];
        for (int i = startBlock; i <= endBlock; i++)
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
            if (id <= this.endBlock && id >= this.startBlock)
                return true;
            if ((id == 151 || id == 152) && isLineMain)
                return true;
        }
        return false;
    }

    public void setSpeedAndAuthority(int trainID, double speed, int authority) //TODO make sure to do this before any PLC outputs, so PLC can make it zero again
    {
            ThreeBaudMessage message = new ThreeBaudMessage();
            message.setTrainID((char)trainID);
            message.setSpeed((char)speed);
            message.setAuthority((char)authority);
            messageQueue.add(message);
    }

    private void sendSpeedAndAuthority(ThreeBaudMessage message)
    {
        Block block;
        double speed = (double)message.getSpeed();
        for(int i = startBlock; i < endBlock; i++)
        {
            block = track.getBlock(this.line,i);
            if (block.isOccupied())
            {
                if (speed > block.getSpeedLimit()) {
                    speed = block.getSpeedLimit();
                    message.setSpeed((char)speed);
                }
                block.setMessage(message);
            }
        }
    }

    public boolean getPLCSwitch(int switchID)
    {
       return  myPLC.getSwitchState(switchID);
    }

    public boolean getPLCCrossing(int blockID)
    {
        return myPLC.getCrossingState(blockID);
    }

    public boolean getPLCLight(int blockID)
    {
        return myPLC.getLights(blockID);
    }

    public void Update()
    {
        if (!messageQueue.isEmpty())
        {
            sendSpeedAndAuthority(messageQueue.remove());
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
            if (s.getState().equals(SwitchState.TOP))
                values[3] = s.getTop().toString();
            else
                values[3] = s.getBottom().toString();
        }
        return values;
    }

    public String getAllBlockOccupancies()
    {
        StringBuilder returnString = new StringBuilder("");
        Block block;
        for (int i = startBlock; i <= endBlock; i++)
        {
            block = track.getBlock(this.line,i);
            returnString.append("Block: " + block.getBlockNumber() + "\n");
            returnString.append("Occupancy: " + Boolean.toString(block.isOccupied()) + "\n");
            returnString.append("--------------\n");
        }
        return(returnString.toString());
    }

    public String getAllSwitchStates()
    {
        StringBuilder returnString = new StringBuilder("");
        ArrayList<Integer> switches = new ArrayList<Integer>();
        Block block;
        Switch sw;
        for (int i = startBlock; i <= endBlock; i++)
        {
            block = track.getBlock(this.line,i);
            if ((sw = block.getSwitch()) != null && !switches.contains(sw.getSwitchNumber()))
            {
                returnString.append("Switch: " + sw.getSwitchNumber() + "\n");
                returnString.append("Main Block: " + sw.getMain()+"\n");
                if (sw.getState()==SwitchState.TOP)
                    returnString.append("Connected: " + sw.getTop() + "\n");
                else
                    returnString.append("Connected: " + sw.getBottom() + "\n");
                returnString.append("--------------\n");
                switches.add(sw.getSwitchNumber());
            }
        }
        return returnString.toString();
    }
}