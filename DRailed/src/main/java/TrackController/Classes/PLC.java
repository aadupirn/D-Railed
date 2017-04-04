package TrackController.Classes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class PLC
    {
        private boolean isGood;
        private ArrayList<String> green,red; //A string of conditions for each line
        private String input, output;
        private String[] inOut, getIn, getOut;

        public PLC(File file, Block[] Blocks)
        {
            isGood = true;
            green = new ArrayList<>();
            red = new ArrayList<>();

            try(BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                for(String line; (line = br.readLine()) != null; )
                {
                    System.out.println("Whole string: " + line + "\n");
                    if(!line.contains("="))
                    {
                        isGood = false;
                    }
                    else {
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
                            String outBlock = getOut[0] + "." + getOut[1];
                            String outSwitch = getOut[0];
                            for(Block b : Blocks)
                            {
                                if(out.equals("crossing") && (b.getID().equals(outBlock) || outSwitch.equals("this")))
                                {
                                    System.out.println("Setting " + b.getID() + " crossing to " + input);
                                    b.setCrossings(input);
                                }
                                if(out.equals("lights") && (b.getID().equals(outBlock) || outSwitch.equals("this")))
                                {
                                    System.out.println("Setting " + b.getID() + " lights to " + input);
                                    b.setLights(input);
                                }
                                if(out.equals("switches"))
                                {
                                    if(b.getSwitchID1() != null)
                                    {
                                        if(b.getSwitchID1().getID().equals(outSwitch)) {
                                            b.getSwitchID1().setState(input);
                                        }
                                    }
                                    if(b.getSwitchID2() != null)
                                    {
                                        if(b.getSwitchID2().getID().equals(outSwitch)) {
                                            b.getSwitchID2().setState(input);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("There is an exception: " + e.toString());
            }
        }

        private boolean OnlyVariable(String test)
        {
            return(true);
        }

        public boolean isValid()
        {
            return(isGood);
        }
    }