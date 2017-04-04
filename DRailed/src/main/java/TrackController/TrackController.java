package TrackController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import TrackController.Classes.*;
import TrackController.UI.*;
import TrackModel.Model.*;


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
    private boolean trackComms, ctcComms;


    public TrackController() throws IOException
    {
        trackComms = true;
        ctcComms = true;
        TrackControllerUI ui = new TrackControllerUI(this);

    }


    public void setPLC(File file) {
        this.myPLC = new PLC(file,Blocks);
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

    public void setCtcComms(boolean ctcComms) {
        this.ctcComms = ctcComms;
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
    public boolean hasBlock(String line, int id)
    {
        if (line.equals(this.line))
        {
            if (id < this.endBlock && id > this.startBlock)
                return true;
        }
        return false;
    }
}