package TrackModel.Model;

/**
 * Created by adzun_000 on 1/20/2017.
 */
public class Switch {

    private Integer switchNumber;
    private Integer mainBlock;
    private Integer topBlock;
    private Integer bottomBlock;
    private SwitchState state;
    private Heater heater;
    private boolean manualSet;

    /***
     * Create a switch object with a switch number and an empty connection layout.
     *
     * @param switchNumber - sequence number or ID of the switch
     */
    public Switch(Integer switchNumber){
        this.switchNumber = switchNumber;
        this.state = SwitchState.TOP;

        this.mainBlock = null;
        this.topBlock = null;
        this.bottomBlock = null;
        this.heater = new Heater(""+switchNumber);
        this.manualSet = false;
    }

    /***
     * Create a switch object with a switch number and an initial main connection (initial connector).
     *
     * @param switchNumber - sequence number or ID of the switch
     * @param mainBlock - initial connecting block to the switch
     */
    public Switch(Integer switchNumber, Integer mainBlock){
        this.switchNumber = switchNumber;
        this.state = SwitchState.TOP;

        this.mainBlock = mainBlock;
        this.topBlock = null;
        this.bottomBlock = null;
        this.heater = new Heater(""+switchNumber);
        this.manualSet = false;
    }

    public boolean isManualSet() {
        return manualSet;
    }

    public void setManualSet(boolean manualSet) {
        this.manualSet = manualSet;
    }

    /***
     * @return the switches sequence number
     */
    public Integer getSwitchNumber() {
        return switchNumber;
    }

    /***
     * Manually set the sequence number of a switch
     *
     * @param switchNumber - sequence number to set
     */
    public void setSwitchNumber(Integer switchNumber) {
        this.switchNumber = switchNumber;
    }

    /***
     * @return the block connected to the initial state
     */
    public Integer getMain() {
        return mainBlock;
    }

    /***
     * Manually set the initial block of the switch
     *
     * @param main - block to set
     */
    public void setMain(Integer main) {
        this.mainBlock = main;
    }

    /***
     * @return the block connected to the top state
     */
    public Integer getTop() {
        return topBlock;
    }

    /***
     * Manually set the top state connected block
     *
     * @param top - the block to be set
     */
    public void setTop(Integer top) {
        this.topBlock = top;
    }

    /***
     * @return the block connected to the bottom state
     */
    public Integer getBottom() {
        return bottomBlock;
    }

    /***
     * Manually set the block connected to the bottom state
     *
     * @param bottom - the block to set
     */
    public void setBottom(Integer bottom) {
        this.bottomBlock = bottom;
    }

    /***
     * Sets the state of the switch to connect main to the appropriate
     * switch state
     *
     * @param state - switch state to set
     */
    public void setSwitchState(SwitchState state){
        this.state = state;
    }

    /***
     * Toggles the current switch state (manual testing)
     */
    public void toggleState(){
        if(this.state == SwitchState.TOP){
            this.state = SwitchState.BOTTOM; // main track to bottom track
        }else{
            this.state = SwitchState.TOP;   // main track to top track
        }
    }

    /***
     * @return read the current switch state of the switch
     */
    public SwitchState getState(){
        return this.state;
    }

    /***
     * @return the current switch info and
     */
    public String getSwitchInfo(){
        if(this.state == SwitchState.TOP)
        {
            return "SwitchNo: " + this.switchNumber + "| State: TOP | Main: " + this.mainBlock + "| Top: " + this.topBlock + "| Bottom: " + this.bottomBlock;
        }else{
            return "SwitchNo: " + this.switchNumber + "| State: BOTTOM | Main: " + this.mainBlock + "| Top: " + this.topBlock + "| Bottom: " + this.bottomBlock;
        }
    }

    public Heater getHeater() {
        return heater;
    }

    public void setHeater(Heater heater) {
        this.heater = heater;
    }

}
