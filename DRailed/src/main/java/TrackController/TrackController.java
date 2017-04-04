package TrackController;

import java.io.File;
import java.io.IOException;
import TrackController.Classes.*;
import TrackController.UI.*;

/**
 * Created by Jonathan on 2/3/17.
 */
public class TrackController {

    //Class Objects
    private PLC myPLC;
    private Block[] Blocks;


    public TrackController() throws IOException {

        TrackControllerUI ui = new TrackControllerUI(this);
        Block A,B,C,D;
        A = new Block("A.1");
        B = new Block("A.2");
        C = new Block("B.1");
        D = new Block("B.2");
        Blocks = new Block[]{A,B,C,D};
        Switch sw = new Switch("1",B,C,D);
        Blocks[0].setOccupied(true);
        Blocks[1].setSwitchID1(sw);
        Blocks[2].setSwitchID1(sw);
        Blocks[2].setStationName("Test Station");
        Blocks[3].setSwitchID1(sw);
        Blocks[3].setHasCrossings(true);

    }

    public void setPLC(File file)
    {
        myPLC = new PLC(file,Blocks);
    }

}