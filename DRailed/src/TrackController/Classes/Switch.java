package TrackController.Classes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Switch
{
    private String ID;
    private String state; //0 = connected to SB1, 1 = connected to SB2
    private Block subBlock1;
    private Block subBlock2;
    private Block mainBlock;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getState() {
        if(runPLC(state))
            return subBlock1.getID();
        return subBlock2.getID();
    }

    public void setState(String state) {
        this.state = state;
    }

    public Block getSubBlock1() {
        return subBlock1;
    }

    public void setSubBlock1(Block subBlock1) {
        this.subBlock1 = subBlock1;
    }

    public Block getSubBlock2() {
        return subBlock2;
    }

    public void setSubBlock2(Block subBlock2) {
        this.subBlock2 = subBlock2;
    }

    public Block getMainBlock() {
        return mainBlock;
    }

    public void setMainBlock(Block mainBlock) {
        this.mainBlock = mainBlock;
    }

    public Switch(String setID, Block mBlock, Block SB1, Block SB2)
    {
        ID = setID;
        state = null;
        subBlock1 = SB1;
        subBlock2 = SB2;
        mainBlock = mBlock;
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
        result = in.replaceAll(subBlock2.getID() + ".occupied", Boolean.toString(subBlock2.isOccupied()));
        result = result.replaceAll(mainBlock.getID() + ".occupied", Boolean.toString(mainBlock.isOccupied()));
        result = result.replaceAll(subBlock1.getID() + ".occupied", Boolean.toString(subBlock1.isOccupied()));
        result = result.replaceAll(subBlock1.getID() + ".broken", Boolean.toString(subBlock1.isBroken()));
        result = result.replaceAll(subBlock2.getID() + ".broken", Boolean.toString(subBlock2.isBroken()));
        result = result.replaceAll(mainBlock.getID() + ".broken", Boolean.toString(mainBlock.isBroken()));
        result = result.replaceAll(subBlock1.getID() + ".closed", Boolean.toString(subBlock1.isClosed()));
        result = result.replaceAll(subBlock2.getID() + ".closed", Boolean.toString(subBlock2.isClosed()));
        result = result.replaceAll(mainBlock.getID() + ".closed", Boolean.toString(mainBlock.isClosed()));
        return(result);
    }
}