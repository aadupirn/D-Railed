package TrainModel;

/**
 * Created by swaroopakkineni on 2/14/17.
 */
public class Train {
    private TrainModelMain trainModel;
    //TrainController trainController;
    private int block;
    private Double commandSpeed;
    private int id;


    public Train(){
        trainModel = new TrainModelMain();
        //trainController = new TrainController();
    }
    public Train(int blockLocation, int newID){
        block = blockLocation;
        trainModel = new TrainModelMain();
        id = newID;

        trainModel = new TrainModelMain();
        //trainController = new TrainController();
    }
    public Train(int blockLocation, int numberOfCarts, int newID){
        block = blockLocation;
        id = newID;
        trainModel = new TrainModelMain(numberOfCarts);
        //trainController = new TrainController();
    }
    public Train(int blockLocation, int numberOfCarts, Double newAuthority, Double newSpeed, int newID){
        block = blockLocation;
        id = newID;
        trainModel = new TrainModelMain(numberOfCarts, newAuthority, newSpeed);
        //trainController = new TrainController();
    }

    private boolean setCommandSpeed(Double newCommandSpeed){
        commandSpeed = newCommandSpeed;
        //power = trainController.calculateAuthority(commandSpeed);
        //calculateSpeed(power);
        return true;
    }

    private boolean calculateSpeed(Double power){
        //do some calculations
        return true;
    }
}
