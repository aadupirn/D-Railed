package TrackModel.Model;

import TrainModel.Train;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import MBO.java.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by adzun_000 on 1/17/2017.
 */
public class TrackModel
{

    private List<Line> lines;
    private int trainToDispatch = 0;
    private int lineCount;

    // Rail:: right to left 0x123456
    // [1] -> Train Id
    // [2] -> Speed
    // [3] -> Speed
    // [4] -> Speed
    // [5] -> Authority
    // [6] -> Authority

    public TrackModel(){
        lines = new ArrayList<>();
    }

    public TrackModel(String trackLayout){
        lines = new ArrayList<>();
        importTrack(trackLayout);

        for(Line l : getLines()) {
            looseCoupling(l.getLine());
        }

    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void importTrack(String fileName){

        FileChooser fileChooser = new FileChooser();
        Stage fileSelect = new Stage();
        fileSelect.setTitle(fileName);
        File file = fileChooser.showOpenDialog(fileSelect);
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {

            String reader = null;
            String [] dataLine = null;

            while((reader = br.readLine()) != null)
            {

                dataLine = Arrays.stream(reader.split(",")).map(String::trim).toArray(String[]::new);

                // ignore column header lines
                if(dataLine[0].equals("Line") && dataLine[1].equals("Section")){
                    continue;
                }

                // test parsing each block
//                for(int i = 0; i <= 13; i++){
//                    System.out.println(i + ": " + dataLine[i]);
//                }

                Line updateLine = null;
                Section updateSection = null;
                Block updateBlock = null;

                String line = dataLine[0].toUpperCase();
                String section = dataLine[1].toUpperCase();
                Integer blockNumber = new Integer(dataLine[2]);
                Double blockLength = new Double(dataLine[3]);
                Double blockGrade = new Double(dataLine[4]);
                Double speedLimit = new Double(dataLine[5]);
                String infrastructure = dataLine[6].toUpperCase();
                Double blockElevation = new Double(dataLine[7]);
                Double blockCumulativeElevation = new Double(dataLine[8]);
                String switchInfo = dataLine[9].toUpperCase();
                String direction = dataLine[10].toUpperCase();
                String nextUpBlock = dataLine[11].toUpperCase();
                String nextDownBlock = dataLine[12].toUpperCase();
                String nextSwitchBlock = dataLine[13].toUpperCase();
                String beacon = dataLine[14].toUpperCase();
                Double temperature = generateTemperature();

                updateLine = new Line(line);
                updateSection = new Section(line, section);
                updateBlock = new Block(line, section, blockNumber);

                // check if the line exists in the model if it doesn't exist add the new line to the list of lines
                if(!existsLine(line))
                {
                    lineCount++;
                    updateLine = new Line(line);

                }
                else
                {
                    updateLine = getLine(line);
                }

                // check if the section exists in the line if it doesn't exist add the section to the lines list of sections
                if(!updateLine.existsSection(section))
                {
                    updateSection = new Section(line, section);

                }
                else
                {
                    updateSection = updateLine.getSection(section);

                }

                // check if the block exists in the section if it doesn't exist add the block to the sections list of blocks
                if(!updateSection.existsBlock(blockNumber)){

                    updateBlock = new Block(line, section, blockNumber);
                    updateBlock.setParameters(blockLength, blockGrade, speedLimit, blockElevation, blockCumulativeElevation, temperature, direction);

                    List<Object> infraSet = parseInfrastructure(updateLine, section, blockNumber, infrastructure, switchInfo);

                    // Decide if block is to or from the yard
                    if(infrastructure.contains("YARD")){
                        if(infrastructure.contains("TO")) {
                            updateBlock.setToYard();
                        }
                        if(infrastructure.contains("FROM")){
                            updateBlock.setFromYard();
                        }
                    }

                    // set beacon info if present
                    if(!beacon.equals("NULL"))
                        updateBlock.setBeacon(beacon);

                    // set next block numbers
                    updateBlock.setNextUpBlockNumber(new Integer(nextUpBlock));
                    updateBlock.setNextDownBlockNumber(new Integer(nextDownBlock));

                    // handle switch specific information
                    if(!nextSwitchBlock.equals("NULL")){
                        boolean dir = false;
                        if(nextSwitchBlock.split(";")[1].equals("UP")){
                            dir = true;
                        }else if(nextSwitchBlock.split(";")[1].equals("DOWN")){
                            dir = false;
                        }

                        updateBlock.setNextSwitch(new Integer(nextSwitchBlock.split(";")[0]), dir);
                    }else{
                        updateBlock.setNextSwitch(-1, false);
                    }

                    Station updateStation = null;
                    Switch updateSwitch = null;
                    Crossing updateCrossing = null;
                    String other = null;

                    if(infraSet != null) {
                        updateStation = (Station) infraSet.get(0);
                        updateSwitch = (Switch) infraSet.get(1);
                        updateCrossing = (Crossing) infraSet.get(2);
                        other = (String) infraSet.get(3);
                    }

                    // update other blocks connected to the switch
                    for(Switch sw : updateLine.getSwitches()){
                        for(Section se : updateLine.getSections()){
                            for(Block b : se.getBlocks()){
                                if(b.getSwitch() != null && updateSwitch != null && b.getSwitch().getSwitchNumber().equals(updateSwitch.getSwitchNumber())){
                                    b.setSwitch(updateSwitch); // update switch
                                }
                            }
                        }
                    }

                    updateBlock.setInfrastructure(updateStation, updateSwitch, updateCrossing, other);


                }else{ // the entry already exists in the track model
                    updateBlock = updateSection.getBlock(blockNumber);
                }

                if(!existsLine(line) && !updateLine.existsSection(section) && !updateSection.existsBlock(blockNumber))
                {
                    updateSection.addBlock(updateBlock);
                    updateLine.addSection(updateSection);
                    lines.add(updateLine);

                }
                else if(!updateLine.existsSection(section) && !updateSection.existsBlock(blockNumber))
                {
                    updateSection.addBlock(updateBlock);
                    updateLine.addSection(updateSection);

                }
                else if(!updateSection.existsBlock(blockNumber))
                {
                    updateSection.addBlock(updateBlock);

                }


                Line infraLine = getLine(updateLine.getLine());

                for(Section s : infraLine.getSections()){
                    for(Block b : s.getBlocks()){
                        if(b.getSwitch() != null)
                            infraLine.getSwitches().add(b.getSwitch());

                        if(b.getStation() != null)
                            infraLine.getStations().add(b.getStation());

                        if(b.getCrossing() != null)
                            infraLine.getCrossings().add(b.getCrossing());
                    }
                }


            }

        }catch(IOException ioe){
            System.out.println("ERROR: " + ioe.getMessage());
        }
    }

    ///////////////////////////////
    // Infrastructure Parsing    //
    ///////////////////////////////


    private List<Object> parseInfrastructure(Line updateLine, String section, Integer blockNumber, String infrastructure, String switchInfo) {

        List<Object> infraSet = new ArrayList<>();

        // if there is no infrastructure for this block
        if(infrastructure.equals("NULL")) {
            return null;
        }

        // 0.) Station infrastructure is found
        if(infrastructure.contains("STATION"))
        {
            infraSet.add(parseStation(section, infrastructure));

        }
        else
        {
            infraSet.add(null);
        }

        // 1.) Switch infrastructure is found
        if(infrastructure.contains("SWITCH"))
        {
            Switch updateSwitch = parseSwitch(updateLine, section, blockNumber, switchInfo, infrastructure);
            infraSet.add(updateSwitch);
        }
        else
        {
            infraSet.add(null);
        }

        // 2.) Crossing infrastructure is found
        if(infrastructure.contains("CROSSING"))
        {
            infraSet.add(new Crossing(blockNumber));

        }
        else
        {
            infraSet.add(null);
        }

        // 3.) Underground infrastructure is found
        if(infrastructure.contains("UNDERGROUND"))
        {
            infraSet.add("UNDERGROUND");

        }
        else
        {
            infraSet.add(null);
        }

        return infraSet;
    }

    private Switch parseSwitch(Line updateLine, String section, Integer blockNumber, String switchInfo, String infra) {

        Switch updateSwitch = null;

        // If a switch of the same switch number exists already this switch is added as the main of the switch
        if(updateLine.existsSwitch(new Integer(switchInfo.split(" ")[1].trim())))
        {
            updateSwitch = updateLine.getSwitch(new Integer(switchInfo.split(" ")[1]));

            if(infra.contains("TOP")){
                updateSwitch.setTop(blockNumber);
            }else if(infra.contains("BOTTOM")){
                updateSwitch.setBottom(blockNumber);
            }else if(infra.contains("MAIN")){
                updateSwitch.setMain(blockNumber);
            }

        } // If a switch doesn't exit create a new switch and set this block as the designated block of the switch
        else
        {
            updateSwitch = new Switch(new Integer(switchInfo.split(" ")[1]));

            if(infra.contains("TOP")){
                updateSwitch.setTop(blockNumber);
            }else if(infra.contains("BOTTOM")){
                updateSwitch.setBottom(blockNumber);
            }else if(infra.contains("MAIN")){
                updateSwitch.setMain(blockNumber);
            }


        }

        return updateSwitch;

    }

    private Station parseStation(String section, String infrastructure) {

        Station updateStation = null;

        // If infrastructure contains: STATION; <station-name> | OTHER INFRA
        if(infrastructure.contains("|"))
        {

            String[] station = infrastructure.split("| ");

            for(String s : station){
                if(s.contains("STATION")){
                    updateStation = new Station(s.split(";")[1].trim(), section);
                }
            }

        } // If infrastructure ONLY contains: STATION; <station-name>
        else
        {
            updateStation = new Station(infrastructure.split(";")[1].trim(), section);

        }

        return updateStation;
    }

    ////////////////
    // OTHER      //
    ////////////////


    private Double generateTemperature() {
        return new Double(new Random().nextInt(100));
    }

    private boolean existsLine(String line){

        for(Line l : lines){
            if(l.getLine().equals(line))
                return true;
        }

        return false;
    }

    private Line getLine(String line){
        for(Line l : lines){
            if(l.getLine().equals(line))
                return l;
        }

        return null;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public int dipatchTrain(String line, Train train){

        int err = -1;

        for(Section s : getLine(line).getSections()){
            for (Block b : s.getBlocks()) {
                if (b.getSwitch() != null && b.isFromYard()) {
                    getLine(line).placeTrain(b, train);
                    return b.getBlockNumber();
                }
            }
        }

        return err;
    }

    public Block findTrain(String line, int trainId){

        if(lines == null || lines.isEmpty()){
            System.out.println("Track model is not available. Please import a track first");
            return null;
        }

        Block block = null;

        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.getTrain() != null && b.getTrain().getId() == trainId){
                    return b;
                }
            }
        }

        return block;
    }

    public Switch findSwitch(String line, int switchNo){

        if(lines == null || lines.isEmpty()){
            System.out.println("Track model is not available. Please import a track first");
            return null;
        }

        Switch aSwitch = null;

        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.getSwitch() != null || b.getSwitch().getSwitchNumber().equals(switchNo)){
                    aSwitch = b.getSwitch();
                }
            }
        }

        return aSwitch;

    }

    public Crossing findCrossing(String line, int crossingNo){

        if(lines == null || lines.isEmpty()){
            System.out.println("Track model is not available. Please import a track first");
            return null;
        }

        Crossing crossing = null;

        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.getCrossing() != null || b.getCrossing().getCrossingNumber().equals(crossingNo)){
                    crossing = b.getCrossing();
                }
            }
        }

        return crossing;

    }

    public Station findStation(String line, String stationName){

        if(lines == null || lines.isEmpty()){
            System.out.println("Track model is not available. Please import a track first");
            return null;
        }

        Station station = null;

        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.getStation() != null || b.getStation().getStationName().equals(stationName)){
                    station = b.getStation();
                }
            }
        }

        return station;

    }

    public Light findLight(String line, int lightNo){

        if(lines == null || lines.isEmpty()){
            System.out.println("Track model is not available. Please import a track first");
            return null;
        }

        Light light = null;

        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.getLight() != null || b.getLight().getLightNumber() == lightNo){
                    light = b.getLight();
                }
            }
        }

        return light;
    }

    public void tightCoupling(String lineName) {

        Line line = getLine(lineName);

        for(Section s : line.getSections()) {
            for(Block b : s.getBlocks()) {

                if (b.getDirection().contains("BI")) {

                    b.setNextDownBlock(line.getBlock(b.getNextDownBlockNumber()));
                    b.setNextUpBlock(line.getBlock(b.getNextUpBlockNumber()));

                    if(b.getSwitch() != null && b.getNextSwitchBlockNumber() != -1){
                        b.setNextSwitchBlock(line.getBlock(b.getNextSwitchBlockNumber()));
                        b.setNextSwitchRedirect(b.getNextSwitchRedirect());
                    }

                } else if (b.getDirection().contains("UP")) {

                    b.setNextUpBlock(line.getBlock(b.getNextUpBlockNumber()));

                    if(b.getSwitch() != null && b.getNextSwitchBlockNumber() != -1){
                        b.setNextSwitchBlock(line.getBlock(b.getNextSwitchBlockNumber()));
                        b.setNextSwitchRedirect(b.getNextSwitchRedirect());
                    }

                } else if (b.getDirection().contains("DOWN")) {

                    b.setNextDownBlock(line.getBlock(b.getNextDownBlockNumber()));

                    if(b.getSwitch() != null && b.getNextSwitchBlockNumber() != -1){
                        b.setNextSwitchBlock(line.getBlock(b.getNextSwitchBlockNumber()));
                        b.setNextSwitchRedirect(b.getNextSwitchRedirect());
                    }

                }
            }
        }
    }

    public List<Block> lookAhead(Block currentBlock, boolean dir, int numBlocks){

        List<Block> blocksAhead = new ArrayList<Block>();

        for(int i = 0; i < numBlocks; i++) {
            boolean newDir = currentBlock.canMoveToBlock(dir);

            if (newDir == dir) {
                // UP
                if (newDir == true) {
                    if (currentBlock.getNextUpBlockNumber() == -1) {
                        blocksAhead.add(currentBlock.getNextSwitchBlock());
                    } else {
                        blocksAhead.add(currentBlock.getNextUpBlock());
                    }
                    //DOWN
                } else {
                    if (currentBlock.getNextDownBlockNumber() == -1) {
                        blocksAhead.add(currentBlock.getNextSwitchBlock());
                    } else {
                        blocksAhead.add(currentBlock.getNextDownBlock());
                    }
                }
            } else {
                // UP
                if (newDir == true) {
                    if (currentBlock.getNextUpBlockNumber() == -1) {
                        blocksAhead.add(currentBlock.getNextSwitchBlock());
                    } else {
                        blocksAhead.add(currentBlock.getNextUpBlock());
                    }
                    //DOWN
                } else {
                    if (currentBlock.getNextDownBlockNumber() == -1) {
                        blocksAhead.add(currentBlock.getNextSwitchBlock());
                    } else {
                        blocksAhead.add(currentBlock.getNextDownBlock());
                    }
                }
            }

            currentBlock = blocksAhead.get(i);
            System.out.println(currentBlock);

        }

        return blocksAhead;

    }

    public void looseCoupling(String inLine){

        Line line = getLine(inLine);

        for(Section s : line.getSections()) {
            for(Block b : s.getBlocks()) {

                if (b.getDirection().contains("BI")) {

                    b.setNextDownBlock(line.getBlock(b.getNextDownBlockNumber()));
                    b.setNextUpBlock(line.getBlock(b.getNextUpBlockNumber()));

                } else if (b.getDirection().contains("UP")) {

                    b.setNextUpBlock(line.getBlock(b.getNextUpBlockNumber()));

                } else if (b.getDirection().contains("DOWN")) {

                    b.setNextDownBlock(line.getBlock(b.getNextDownBlockNumber()));

                }
            }
        }
    }

    public Line getALine(String line){
        return getLine(line);
    }

    public Block getBlock(String line, int blockId){
        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.getBlockNumber().intValue() == blockId){
                    return b;
                }
            }
        }

        return null;
    }

    public Block getFromYardBlock(String line){
        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.isFromYard()){
                    return b;
                }
            }
        }

        return null;
    }

    public Block getToYardBlock(String line){
        for(Section s : getLine(line).getSections()){
            for(Block b : s.getBlocks()){
                if(b.isToYard()){
                    return b;
                }
            }
        }

        return null;
    }

}