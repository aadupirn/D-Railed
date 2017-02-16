package TrackController.Classes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Block
{
    private String ID;
    private Switch switchID1;
    private Switch switchID2;
    private String stationName;
    private String crossings;
    private String lights;
    private boolean occupied;
    private boolean broken;
    private boolean closed;
    private boolean hasCrossings;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Switch getSwitchID1() {
        return switchID1;
    }

    public void setSwitchID1(Switch switchID1) {
        this.switchID1 = switchID1;
    }

    public Switch getSwitchID2() {
        return switchID2;
    }
    public String getSwitchID2Name() {
        if(switchID2 == null)
            return "N/A";
        return switchID2.getID();
    }
    public String getSwitchID1Name() {
        if(switchID1 == null)
            return "N/A";
        return switchID1.getID();
    }

    public void setSwitchID2(Switch switchID2) {
        this.switchID2 = switchID2;
    }

    public String getStationName() {
        if(stationName == null)
            return "N/A";
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCrossings() {
        if (this.hasCrossings)
        {
            if(runPLC(crossings))
                return "On";
            else
                return "Off";
        }
        else
            return "N/A";
    }

    public void setCrossings(String crossings) {
        this.crossings = crossings;
    }

    public String getLights() {
        if(runPLC(lights))
            return "Red";
        return "Green";
    }

    public void setLights(String lights) {
        this.lights = lights;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isHasCrossings() {
        return hasCrossings;
    }

    public void setHasCrossings(boolean hasCrossings) {
        this.hasCrossings = hasCrossings;
    }

    public boolean isBlock(String testID)
    {
        if(this.ID.equals(testID))
            return(true);
        else
            return(false);
    }

    public String getOpenStatus()
    {
        if(closed)
        {
            return "Closed";
        }
        if(occupied)
        {
            return "Occupied";
        }
        if(broken)
        {
            return "Broken";
        }
        return "Open";
    }

    public Block(String setID)
    {
        ID = setID;
        switchID1 = null;
        switchID2 = null;
        stationName = null;
        lights = null;
        crossings = null;
        occupied = false;
        broken = false;
        hasCrossings = false;
        closed = false;

    }

    private boolean runPLC(String input)
    {
        String boolIn = replaceAllInputs(input);
        boolean result;
        try {

            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine se = sem.getEngineByName("JavaScript");
            result = (boolean)se.eval(boolIn);
            return(result);

        } catch (ScriptException e) {

            System.out.println("Invalid Expression");
            e.printStackTrace();
            return(false);

        }
    }
    private String replaceAllInputs(String in)
    {
        String result;
        result = in.replaceAll("this.occupied", Boolean.toString(this.occupied));
        result = result.replaceAll("this.broken", Boolean.toString(this.broken));
        result = result.replaceAll("this.closed", Boolean.toString(this.closed));
        result = result.replaceAll(getID()+".occupied", Boolean.toString(this.occupied));
        result = result.replaceAll(getID()+".broken", Boolean.toString(this.broken));
        result = result.replaceAll(getID()+".closed", Boolean.toString(this.closed));
        return(result);
    }

}