package TrackModel.Model;

import MBO.java.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adzun_000 on 1/20/2017.
 */
public class Line {

    // line ID and metrics
    private String line;
    private int sectionCount;
    private int switchCount;
    private int stationCount;
    private int crossingCount;
    private int totalLength;

    // line component lists
    private List<Train> trains;
    private List<Section> sections;
    private List<Switch> switches;
    private List<Station> stations;
    private List<Crossing> crossings;

    ////////////////////////
    // Application.Track.Model.Line Level         //
    ////////////////////////

    public Line(String line){
        this.line = line;
        trains = new ArrayList<>();
        sections = new ArrayList<>();
        switches = new ArrayList<>();
        stations = new ArrayList<>();
        crossings = new ArrayList<>();
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Double getTotalLength() {

        Double totalSectionLength = 0.0;

        for(Section s : sections){
            totalSectionLength += s.getLength();
        }

        return totalSectionLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    /////////////////////
    // Application.Track.Model.Section Level   //
    /////////////////////

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section){
        sectionCount++;
        this.sections.add(section);
    }

    public boolean removeSection(Section section){
        sectionCount--;
        return this.sections.remove(section);
    }

    public boolean hasSection(Section section){
        return this.sections.contains(section);
    }

    public boolean existsSection(String section){
        for(Section s : sections) {
            if(s.getSection().equals(section))
                return true;
        }

        return false;
    }

    public Section getSection(String section){
        for(Section s : sections) {
            if(s.getSection().equals(section))
                return s;
        }

        return null;

    }

    public int getSectionCount() {
        return sectionCount;
    }

    //////////////////////
    // Application.Track.Model.Block Level      //
    //////////////////////

//    public boolean hasBlock(String section, Integer block){
//        return this.sections.get(this.sections.indexOf(section)).hasBlock(block);
//    }

    //////////////////////
    // Application.Track.Model.Switch Level     //
    //////////////////////

    public List<Switch> getSwitches() {
        return switches;
    }

    public void setSwitches(List<Switch> switches) {
        this.switches = switches;
    }

    public void addSwitch(Switch aSwitch){
        switchCount++;
        this.switches.add(aSwitch);
    }

    public boolean hasSwitch(Switch aSwitch){
        return this.switches.contains(aSwitch);
    }

    public boolean existsSwitch(Integer switchNumber){
        if(switchNumber != null) {
            for (Switch s : switches) {
                if (s.getSwitchNumber().equals(switchNumber))
                    return true;
            }
        }

        return false;
    }

    public Switch getSwitch(Integer switchNumber){
        for(Switch s : switches) {
            if(s.getSwitchNumber().equals(switchNumber))
                return s;
        }

        return null;
    }


    ////////////////////////
    // Application.Track.Model.Station Level      //
    ////////////////////////

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public boolean existsStation(String stationName){
        for(Station s : stations) {
            if(s.getStationName().equals(stationName))
                return true;
        }

        return false;
    }
    public void addStation(Station station) {
        stationCount++;
        this.stations.add(station);
    }

    ////////////////////////
    // Application.Track.Model.Crossing Level     //
    ////////////////////////

    public List<Crossing> getCrossings() {
        return crossings;
    }

    public void setCrossings(ArrayList<Crossing> crossings) {
        this.crossings = crossings;
    }

    public boolean existsCrossing(Integer crossingNumber){
        for(Crossing c : crossings) {
            if(c.getCrossingNumber().equals(crossingNumber))
                return true;
        }

        return false;
    }

    public void addCrossing(Crossing crossing){
        crossingCount++;
        this.crossings.add(crossing);
    }

    public int countSections(){
        int count = 0;
        for(Section s : sections)
            count++;

        return count;
    }

    public int countBlocks(){
        int count = 0;
        for(Section s : sections){
            count += s.countBlocks();
        }
        return count;
    }

    public void placeTrain(String section, Integer blockNo, Train train){
        getSection(section).getBlock(blockNo).trainEnter(train);
        trains.add(train);
    }

}
