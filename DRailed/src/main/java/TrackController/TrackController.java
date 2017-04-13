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
    private int ID;
    private String line;
    private boolean trackComms, ctcComms, isLineMain;
    private LinkedBlockingDeque<ThreeBaudMessage> messageQueue;
    private TrackControllerUI ui;
    private DTime dTime;
    private ArrayList<Integer> blocks, plcBlocks;


    public TrackController(DTime iDTime) throws IOException
    {
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> plc = new ArrayList<>();
        for(int i = 1; i <= 152; i++)
        {
            b.add(i);
            plc.add(i);
        }

        Init("GREEN",b,plc,iDTime, 1);
    }



    public TrackController(String line, ArrayList<Integer> b, ArrayList<Integer> plc, DTime iDTime, int id)
    {
        Init(line,b,plc,iDTime, id);
    }

    private void Init(String line, ArrayList<Integer> b, ArrayList<Integer> plc, DTime iDTime, int id)
    {
        trackComms = true;
        ctcComms = true;
        dTime = iDTime;
        this.line = line;
        ID = id;
        blocks = b;
        messageQueue = new LinkedBlockingDeque<>();

        if (line.equals("GREEN"))
        {
            track = new Track("greenLine.csv");
            if (b.contains(152))
                this.isLineMain=true;
            else
                this.isLineMain=false;
        }
        else
        {
            track = new Track("redLine.csv");
            if (b.contains(77))
                this.isLineMain=true;
            else
                this.isLineMain=false;
        }

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
        for (int i:blocks)
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
            for (int i:blocks)
            {
                if (i==id)
                    return true;
            }
        }

        return false;
    }

    public boolean hasSwitch(String line, int id)
    {
        Switch sw;
        if (line.equals(this.line))
        {
            for (int i:blocks)
            {
                sw = track.getBlock(line,i).getSwitch();
                if(sw != null)
                {
                    if (sw.getSwitchNumber() == id)
                        return true;
                }
            }
        }
        return false;
    }

    private Switch getSwitch(int id)
    {
        Switch sw;
        for (int i:blocks)
        {
            sw = track.getBlock(line,i).getSwitch();
            if(sw != null)
            {
                if (sw.getSwitchNumber() == id)
                    return sw;
            }
        }
        return null;
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
        for (int i:blocks)
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

    public void plcStopTrains()
    {
        Block b;
        for (int i:blocks)
        {
            b = track.getBlock(line,i);
            if (b != null) {
                if (myPLC.getStopTrain(i) && b.isOccupied()) {
                    b = track.getBlock(line, i);
                    b.setMessage(new ThreeBaudMessage((char) 255, (char) 0, (char) 0));
                }
            }
        }
    }

    public void Update()
    {
        Block b;
        for (int i:blocks)
        {
            b = track.getBlock(this.line, i);
            b.clearMessage();
        }

        if (!messageQueue.isEmpty())
        {
            sendSpeedAndAuthority(messageQueue.remove());
        }
        if (plcLoaded())
            plcStopTrains();
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
        if (hasBlock(this.line,blockID)) {
            Block b = track.getBlock(this.line, blockID);
            if (b.isOccupied())
                values[0] = "Occupied";
            else
                values[0] = "Not Occupied";
            try {
                if (b.getCrossing().isActive())
                    values[1] = "On";
                else
                    values[1] = "Off";
            } catch (NullPointerException e) {
                values[1] = "N/A";
            }
            try {
                if (b.getLight().isActive())
                    values[2] = "Green";
                else
                    values[2] = "Red";
            } catch (NullPointerException e) {
                values[2] = "N/A";
            }
            try {
                values[3] = b.getSwitch().getSwitchNumber().toString();
            } catch (NullPointerException e) {
                values[3] = "N/A";
            }
        }
        else
        {
            for (int i=0; i < values.length; i++)
            {
                values[i] = "N/A";
            }
        }
        return values;
    }

    public String[] getSwitchInfo(int switchID)
    {
        String[] values = new String[4];
        Switch s;
        if ((s = getSwitch(switchID))!=null)
        {
            values[0] = s.getMain().toString();
            values[1] = s.getTop().toString();
            values[2] = s.getBottom().toString();
            if (s.getState().equals(SwitchState.TOP))
                values[3] = s.getTop().toString();
            else
                values[3] = s.getBottom().toString();
        }
        else
        {
            for (int i=0; i < values.length; i++)
            {
                values[i] = "N/A";
            }
        }
        return values;
    }

    public String getAllBlockOccupancies()
    {
        StringBuilder returnString = new StringBuilder("");
        Block block;
        for (int i:blocks)
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
        for (int i:blocks)
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