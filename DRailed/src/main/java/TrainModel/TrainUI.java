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
}