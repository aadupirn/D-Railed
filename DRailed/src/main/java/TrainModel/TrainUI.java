package TrainModel;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by swaroopakkineni on 4/16/17.
 */
public class TrainUI {
    //Windows setup
    private final Stage stage = new Stage();
    private TextArea notifications;
    private Scene scene;
    private String title = "Train Model";
    private String notificationText = "";

    //window dimensions
    private int windowWidth = 800;
    private int windowHight = 500;
    private int inset = 25;
    private int colWidth = 75;

    //Automatic vs Manual
    private boolean manualController;

    //train id
    private int trainID;
    private double speed;
    private final double maxSpeed = 70.0;
    private double power;
    private double mass = 10000.0;
    private double grade = 0;

    //train utilities;
    private boolean heat;
    private boolean ac;
    private double temperature;
    private boolean lights;

    //brakes
    private boolean eBrake;
    private boolean sBrake;

    //doors
    private boolean leftDoors;
    private boolean rightDoors;

    private Text speedText;
    private Text speedRight;
    private Text powerText;
    private Text tempText;
    private Text trainIDText;
    private Text gradeText;
    private Text acText;
    private Text heatText;
    private Text leftDoorsText;
    private Text rightDoorsText;
    private Text eBrakeText;
    private Text sBrakeText;


    engine Engine = new engine();


    public TrainUI() {
        trainID = 0;
        manualController = false;

        speed = 0;
        power = 0;

        heat = false;
        ac = false;
        temperature = 0;
        lights = false;

        eBrake = false;
        sBrake = false;

        leftDoors = false;
        rightDoors = false;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(inset, inset, inset, inset));
        grid.setVgap(10);

        Label trainIDLabel = new Label("Train: ");
        trainIDLabel.setTextAlignment(TextAlignment.LEFT);
        trainIDLabel.setMinWidth(colWidth);
        trainIDLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(trainIDLabel, 0, 0);

        trainIDText = new Text(Integer.toString(trainID));
        trainIDText.setWrappingWidth(colWidth * 2);
        trainIDText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(trainIDText, 0, 0);

//////////////////////////////////////////////////////////////SPEED AND POWER
        Label speedLabel = new Label("Speed: ");
        speedLabel.setMinWidth(colWidth * 1.5);
        speedLabel.setTextAlignment(TextAlignment.RIGHT);
        speedLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(speedLabel, 3, 0);

        speedText = new Text();
        speedText.setText("0 mph");
        speedText.setWrappingWidth(colWidth * 1.5);
        speedText.setTextAlignment(TextAlignment.LEFT);
        grid.add(speedText, 4, 0);

        Label powerLabel = new Label("Power: ");
        powerLabel.setMinWidth(colWidth * 1.5);
        powerLabel.setTextAlignment(TextAlignment.RIGHT);
        powerLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(powerLabel, 3, 1);

        powerText = new Text();
        powerText.setText("0 watt");
        powerText.setWrappingWidth(colWidth * 1.5);
        powerText.setTextAlignment(TextAlignment.LEFT);
        grid.add(powerText, 4, 1);

////////////////////////////////////////////////////////////// grade
        Label gradeLevel = new Label("Grade: ");
        gradeLevel.setMinWidth(colWidth * 1.5);
        gradeLevel.setTextAlignment(TextAlignment.RIGHT);
        gradeLevel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(gradeLevel, 3, 2);

        gradeText = new Text();
        gradeText.setText("0 degrees");
        gradeText.setWrappingWidth(colWidth * 1.5);
        gradeText.setTextAlignment(TextAlignment.LEFT);
        grid.add(gradeText, 4, 2);

////////////////////////////////////////////////////////////// Temperature
        Label tempLevel = new Label("Temp: ");
        tempLevel.setMinWidth(colWidth * 1.5);
        tempLevel.setTextAlignment(TextAlignment.RIGHT);
        tempLevel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(tempLevel, 3, 3);

        tempText = new Text();
        tempText.setText("0 degrees");
        tempText.setWrappingWidth(colWidth * 1.5);
        tempText.setTextAlignment(TextAlignment.LEFT);
        grid.add(tempText, 4, 3);

        Label acLevel = new Label("AC: ");
        acLevel.setMinWidth(colWidth * 1.5);
        acLevel.setTextAlignment(TextAlignment.RIGHT);
        acLevel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(acLevel, 3, 4);

        acText = new Text();
        acText.setText("off");
        acText.setWrappingWidth(colWidth * 1.5);
        acText.setTextAlignment(TextAlignment.LEFT);
        grid.add(acText, 4, 4);

        Label heatLevel = new Label("Heat: ");
        heatLevel.setMinWidth(colWidth * 1.5);
        heatLevel.setTextAlignment(TextAlignment.RIGHT);
        heatLevel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(heatLevel, 3, 5);

        heatText = new Text();
        heatText.setText("off");
        heatText.setWrappingWidth(colWidth * 1.5);
        heatText.setTextAlignment(TextAlignment.LEFT);
        grid.add(heatText, 4, 5);

        Label leftDoors = new Label("LeftDoors Status: ");
        leftDoors.setMinWidth(colWidth * 1.5);
        leftDoors.setTextAlignment(TextAlignment.RIGHT);
        leftDoors.setAlignment(Pos.CENTER_RIGHT);
        grid.add(leftDoors, 3, 6);

        leftDoorsText = new Text();
        leftDoorsText.setText("closed");
        leftDoorsText.setWrappingWidth(colWidth * 1.5);
        leftDoorsText.setTextAlignment(TextAlignment.LEFT);
        grid.add(leftDoorsText, 4, 6);

        Label rightDoors = new Label("rightDoors Status: ");
        rightDoors.setMinWidth(colWidth * 1.5);
        rightDoors.setTextAlignment(TextAlignment.RIGHT);
        rightDoors.setAlignment(Pos.CENTER_RIGHT);
        grid.add(rightDoors, 3, 7);

        rightDoorsText = new Text();
        rightDoorsText.setText("closed");
        rightDoorsText.setWrappingWidth(colWidth * 1.5);
        rightDoorsText.setTextAlignment(TextAlignment.LEFT);
        grid.add(rightDoorsText, 4, 7);

        Label ebrake = new Label("Ebrake Status: ");
        ebrake.setMinWidth(colWidth * 1.5);
        ebrake.setTextAlignment(TextAlignment.RIGHT);
        ebrake.setAlignment(Pos.CENTER_RIGHT);
        grid.add(ebrake, 3, 8);

        eBrakeText = new Text();
        eBrakeText.setText("off");
        eBrakeText.setWrappingWidth(colWidth * 1.5);
        eBrakeText.setTextAlignment(TextAlignment.LEFT);
        grid.add(eBrakeText, 4, 8);

        Label sbrake = new Label("sbrake Status: ");
        sbrake.setMinWidth(colWidth * 1.5);
        sbrake.setTextAlignment(TextAlignment.RIGHT);
        sbrake.setAlignment(Pos.CENTER_RIGHT);
        grid.add(sbrake, 3, 9);

        sBrakeText = new Text();
        sBrakeText.setText("off");
        sBrakeText.setWrappingWidth(colWidth * 1.5);
        sBrakeText.setTextAlignment(TextAlignment.LEFT);
        grid.add(sBrakeText, 4, 9);


//////////////////////////////////////////////////////////////Manual and Automatic
        Scene scene = new Scene(grid, windowWidth, windowHight);
        stage.setScene(scene);
        stage.show();
    }
    /*
    update Block, all code below updates the ui
     */
    protected void updateSpeed(double newSpeed){
        speed = newSpeed;
        speedText.setText(String.format( "%4.2f", speed )  + " mph");
    }

    protected void updateId(int newId){
        trainID = newId;
        trainIDText.setText(String.format( "%d", trainID ) );
    }
    protected void updatePower(double newPower){
        power = newPower;
        powerText.setText(String.format( "%4.3f", power ) + " watt" );
    }

    protected void updateGrade(double newGrade){
        grade = newGrade;
        gradeText.setText(String.format( "%4.3f", grade ) + " degrees" );
    }

    protected void updateTemperature(double newTemp){
        temperature = newTemp;
        tempText.setText(String.format( "%4.3f", temperature ) + " F" );
    }

    protected void updateAC(boolean newAC){
        ac = newAC;
        if(ac)
            acText.setText( " on" );
        else
            acText.setText( " off" );
    }

    protected void updateHeat(boolean newHeat){
        heat = newHeat;
        if(heat)
            heatText.setText( " on" );
        else
            heatText.setText( " off" );
    }

    protected void updateLeftDoors(boolean newLeftDoors){
        leftDoors = newLeftDoors;
        if(leftDoors)
            leftDoorsText.setText( " open" );
        else
            leftDoorsText.setText( " closed" );
    }

    protected void updateRightDoors(boolean newRightDoors){
        rightDoors = newRightDoors;
        if(rightDoors)
            rightDoorsText.setText( " open" );
        else
            rightDoorsText.setText( " closed" );
    }

    protected void updateEBrake(boolean newEbrake){
        eBrake = newEbrake;
        if(eBrake)
            eBrakeText.setText( " on" );
        else
            eBrakeText.setText( " off" );
    }

    protected void updateSBrake(boolean newSbrake){
        sBrake = newSbrake;
        if(sBrake)
            sBrakeText.setText( " on" );
        else
            sBrakeText.setText( " off" );
    }

}
