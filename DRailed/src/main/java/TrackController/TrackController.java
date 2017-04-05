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
    private Block[] Blocks;
    private TrackModel model;
    private Track track;
    private int ID, startBlock, endBlock;
    private String line;
    private ArrayList<Block> blocks;
    private boolean trackComms, ctcComms, isLineMain;
    private Queue<Integer> messageQueue;
    private TrackControllerUI ui;


    public TrackController() throws IOException
    {
        trackComms = true;
        ctcComms = true;
        ui = new TrackControllerUI(this);

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

    public void Update()
    {
        if (!messageQueue.isEmpty())
        {
            Integer message = messageQueue.remove();

        }
    }

    public int dispatchTrain(Train train)
    {
        int returnVal = 0;
        if (isLineMain)
        {
            returnVal = track.dispatchTrainOnTrack(this.line,train);
        }
        return returnVal;
    }
}