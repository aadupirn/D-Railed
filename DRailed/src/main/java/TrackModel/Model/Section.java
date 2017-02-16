package TrackModel.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adzun_000 on 1/17/2017.
 */

/***
 * Sections represent related connected blocks of the track model
 *
 *  ex. section: A
 *      line:    Green
 *      block: {list-of-blocks}
 *      blockCount: 3
 *      totalLength: 300
 *
 */
public class Section {

    private String line;
    private String section;
    private List<Block> blocks;
    private Section nextSection;
    private Section previousSection;
    private int blockCount;
    private Double length;


    /***
     *
     *  Basic constructor of a Application.Track.Model.Section: specifies attributes that the section and related blocks
     *  are owned by.
     *
     * @param section - the section ID of the track
     * @param line - the line the section belongs to
     */
    public Section(String line, String section){
        this.section = section;
        this.line = line;
        blocks = new ArrayList<>();
        blockCount = 0;
        length = 0.0;
    }

    /***
     * @return the section ID for this track segment
     */
    public String getSection() {
        return section;
    }

    public Double getLength(){

        Double totalBlockLength = 0.0;

        for(Block b : blocks){
            totalBlockLength += b.getLength();
        }

        return totalBlockLength;
    }

    /***
     * Sets the ID of this section of track
     *
     * @param section - new section ID for this track
     */
    public void setSection(String section) {
        this.section = section;
    }

    /***
     * @return the line name (usually color) this section is owned by
     */
    public String getLine() {
        return line;
    }

    /***
     * Sets the line name (usually color) this section is owned by
     *
     * @param line - new line name of this section owner
     */
    public void setLine(String line) {
        this.line = line;
    }

    /***
     * @return a list of blocks that is contained in the section
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /***
     *  Sets the blocks for this section
     *
     * @param blocks - sets a new list of blocks
     */
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
        blockCount = blocks.size();
    }

    /***
     * Add a new block to the sections block list
     *
     * @param block - a Application.Track.Model.Block object belonging to this section
     */
    public void addBlock(Block block){
        blockCount++;
        blocks.add(block);
    }

    /***
     * Remove a block from the section
     *
     * @param block to remove
     * @return a true on success
     */
    public boolean removeBlock(Block block){
        this.blockCount--;
        return blocks.remove(block);
    }

    /***
     * Checks if the section contains a specific Application.Track.Model.Block already
     *
     * @param block - block to search for
     * @return true if the block was found within the section
     */
//    public boolean hasBlock(Integer block){
//        return blocks.contains(block);
//    }

    public boolean existsBlock(Integer block){
        for(Block b : blocks){
            if(b.getBlockNumber().equals(block))
                return true;
        }

        return false;
    }

    public Block getBlock(Integer block){
        for(Block b : blocks){
            if(b.getBlockNumber().equals(block))
                return b;
        }

        return null;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public int countBlocks(){
        int count = 0;

        for(Block b : blocks)
            count++;

        return count;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }
}
