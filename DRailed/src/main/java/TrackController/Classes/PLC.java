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
        private String[][] green,red; //A string of conditions for each line
        private String input, output;
        private String[] inOut, getIn, getOut,switches;
        private Block[] greenBlocks, redBlocks;

        public PLC(File file)
        {
            isGood = true;
            green = new String[153][3]; // 0 is lights, 1 is crossings, 2 is stop
            red = new String[78][3]; // 0 is lights, 1 is crossings, 2 is stop
            switches = new String[13];
            boolean isGreen = true;
            int outputID = 0;

            try(BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                for(String line; (line = br.readLine()) != null; )
                {
                    System.out.println("Whole string: " + line + "\n");
                    if(!line.contains("="))
                    {
                        //check if it is the transition to red
                        if (line.contains("red"))
                            isGreen = false;
                        else
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
                            String outSwitch = getOut[0];
                            if(out.equals("crossing"))
                            {
                                try
                                {
                                    outputID = Integer.parseInt(id);
                                } catch (NumberFormatException e)
                                {
                                    System.out.println("ID not valid");
                                    isGood = false;
                                }
                                System.out.println("Setting " + outputID + " crossing to " + input);
                                if (isGreen)
                                    green[outputID][1] = input;
                                else
                                    red[outputID][1] = input;
                            }
                            else if(out.equals("lights"))
                            {
                                try
                                {
                                    outputID = Integer.parseInt(id);
                                } catch (NumberFormatException e)
                                {
                                    System.out.println("ID not valid");
                                    isGood = false;
                                }
                                System.out.println("Setting " + outputID + " lights to " + input);
                                if (isGreen)
                                    green[outputID][0] = input;
                                else
                                    red[outputID][0] = input;
                            }
                            else if(out.equals("stop"))
                            {
                                try
                                {
                                    outputID = Integer.parseInt(id);
                                } catch (NumberFormatException e)
                                {
                                    System.out.println("ID not valid");
                                    isGood = false;
                                }
                                System.out.println("Setting " + outputID + " stop to " + input);
                                if (isGreen)
                                    green[outputID][2] = input;
                                else
                                    red[outputID][2] = input;
                            }
                            else if(out.equals("switch"))
                            {
                                try
                                {
                                    outputID = Integer.parseInt(id);
                                } catch (NumberFormatException e)
                                {
                                    System.out.println("ID not valid");
                                    isGood = false;
                                }
                                switches[outputID] = input;
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
            if (switchID <= 6)
                return(runPLC(switches[switchID],"GREEN",switchID));
            return(runPLC(switches[switchID],"RED",switchID));
        }
        public boolean getCrossingState(String line, int blockID)
        {
            if (line.equals("GREEN"))
                return(runPLC(green[blockID][1],line,blockID));
            return(runPLC(red[blockID][1],line,blockID));
        }
        public boolean getStopTrain(String line, int blockID)
        {
            if (line.equals("GREEN"))
                return(runPLC(green[blockID][2],line,blockID));
            return(runPLC(red[blockID][2],line,blockID));
        }
        public boolean getLights(String line, int blockID)
        {
            if (line.equals("GREEN"))
                return(runPLC(green[blockID][0],line,blockID));
            return(runPLC(red[blockID][0],line,blockID));
        }

        public void setGreenBlocks(Block[] greenBlocks) {
            this.greenBlocks = greenBlocks;
        }

        public void setRedBlocks(Block[] redBlocks) {
            this.redBlocks = redBlocks;
        }

        public boolean isValid()
        {
            return(isGood);
        }

        private boolean runPLC(String input, String line, int id)
        {
            String boolIn = replaceAllInputs(input, id, line);
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
        private String replaceAllInputs(String in, int id, String line)
        {
            String result;
            if (line.equals("GREEN")) {
                result = in.replaceAll("this.occupied", Boolean.toString(greenBlocks[id].hasTrain()));
                for (int i = 1; i <= 152; i++) {
                    result = result.replaceAll(i + ".occupied", Boolean.toString(greenBlocks[i].hasTrain()));
                }
            }
            else
            {
                result = in.replaceAll("this.occupied", Boolean.toString(redBlocks[id].hasTrain()));
                for (int i = 1; i <= 77; i++) {
                    result = result.replaceAll(i + ".occupied", Boolean.toString(greenBlocks[i].hasTrain()));
                }
            }
            return(result);
        }
    }