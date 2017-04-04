package TrackController.Classes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import TrackModel.Model.*;


public class PLC
    {
        private boolean isGood;
        private String[][] plcInputs; //A string of conditions for each line
        private String input, output;
        private String[] inOut, getIn, getOut,switches;
        private Block[] blocks;

        public PLC(File file, Block[] blocks)
        {
            isGood = true;
            plcInputs = new String[153][3]; // 0 is lights, 1 is crossings, 2 is stop
            switches = new String[13];
            this.blocks = blocks;
            int outputID = 0;

            try(BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                for(String line; (line = br.readLine()) != null; )
                {
                    System.out.println("\nWhole string: " + line);
                    if(!line.contains("="))
                    {
                        isGood = false;
                    }
                    else
                    {
                        inOut = line.split("=");

                        if (inOut.length != 2)
                        {
                            isGood = false;
                        }
                        else
                        {
                            output = inOut[0].trim();
                            input = inOut[1].trim();

                            System.out.println("output: " + output + "\n" + "input: " + input);
                            getOut = output.split("\\.");
                            String out = getOut[getOut.length-1];
                            String id = getOut[0];
                            if(out.equals("crossing"))
                            {
                                if (!id.equals("this")) {
                                    try {
                                        outputID = Integer.parseInt(id);
                                        System.out.println("Setting " + outputID + " crossing to " + input);
                                        plcInputs[outputID][1] = input;
                                    } catch (NumberFormatException e) {
                                        System.out.println("ID not valid");
                                        isGood = false;
                                    }
                                }
                                else
                                {
                                    for (int i = 0; i < 153; i++)
                                        plcInputs[i][1] = input;
                                }
                            }
                            else if(out.equals("lights"))
                            {
                                if (!id.equals("this")) {
                                    try {
                                        outputID = Integer.parseInt(id);
                                        System.out.println("Setting " + outputID + " lights to " + input);
                                        plcInputs[outputID][0] = input;
                                    } catch (NumberFormatException e) {
                                        System.out.println("ID not valid");
                                        isGood = false;
                                    }
                                }
                                else
                                {
                                    for (int i = 0; i < 153; i++)
                                        plcInputs[i][0] = input;
                                }
                            }
                            else if(out.equals("stop"))
                            {
                                if (!id.equals("this")) {
                                    try {
                                        outputID = Integer.parseInt(id);
                                        System.out.println("Setting " + outputID + " stop to " + input);
                                        plcInputs[outputID][2] = input;
                                    } catch (NumberFormatException e) {
                                        System.out.println("ID not valid");
                                        isGood = false;
                                    }
                                }
                                else
                                {
                                    for (int i = 0; i < 153; i++)
                                        plcInputs[i][2] = input;
                                }
                            }
                            else if(out.equals("switch"))
                            {
                                try
                                {
                                    outputID = Integer.parseInt(id);
                                    System.out.println("Setting " + outputID + " switch to " + input);
                                    switches[outputID] = input;
                                } catch (NumberFormatException e)
                                {
                                    System.out.println("ID not valid");
                                    isGood = false;
                                }
                            }
                            else
                            {
                                System.out.println("Invalid paramater");
                                isGood = false;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("There is an exception: " + e.toString());
            }
        }

        public boolean getSwitchState(int switchID)
        {
            return(runPLC(switches[switchID],switchID));
        }
        public boolean getCrossingState(int blockID)
        {
            return(runPLC(plcInputs[blockID][1],blockID));
        }
        public boolean getStopTrain(int blockID)
        {
            return(runPLC(plcInputs[blockID][2],blockID));
        }
        public boolean getLights(int blockID)
        {
            return(runPLC(plcInputs[blockID][0],blockID));
        }

        public void setBlocks(Block[] Blocks) {
            this.blocks = Blocks;
        }

        public boolean isValid()
        {
            return(isGood);
        }

        private boolean runPLC(String input, int id)
        {
            String boolIn = replaceAllInputs(input, id);
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
        private String replaceAllInputs(String in, int id)
        {
            String result;
            result = in.replaceAll("this.occupied", Boolean.toString(blocks[id].isOccupied()));
            for (int i = 1; i <= blocks.length; i++) {
                result = result.replaceAll(blocks[i].getBlockNumber() + ".occupied", Boolean.toString(blocks[i].isOccupied()));
                result = result.replaceAll(blocks[i].getBlockNumber() + ".nextoccupied", Boolean.toString(blocks[i].isOccupied())); //TODO replace with next block!!
            }
            if (result.contains(".occupied")) //The other track controller has this block
            {
                System.out.println("Haven't handled yet");
            }
            return(result);
        }
    }