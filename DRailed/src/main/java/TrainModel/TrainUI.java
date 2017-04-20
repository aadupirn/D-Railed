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
    private int block;
    private double speed;
    private final double maxSpeed = 70.0;
    private double power;
    private double mass = 10000.0;
    private double grade = 0;
    private int authority;

    //train utilities;
    private boolean heat;
    private boolean ac;
    private double temperature;
    private boolean lights;

    //brakes
    private boolean eBrake;
    private boolean sBrake;

    //auto vs manual
    private boolean AutoVsManual = true;

    //doors
    private boolean leftDoors;
    private boolean rightDoors;

    //text
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
    private Text lightsText;
    private Text sBrakeText;
    private Text massText;
    private Text authorityText;
    private Text blockText;


    //buttons
    Button autoOrManualButton;
    Button sButton;
    Button eButton;


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
        authority = 0;

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
        speedLabel.setTextAlignment(TextAlignment.LEFT);
        speedLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(speedLabel, 4, 0);

        speedText = new Text();
        speedText.setText("0 mph");
        speedText.setWrappingWidth(colWidth * 1.5);
        speedText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(speedText, 5, 0);

        Label powerLabel = new Label("Power: ");
        powerLabel.setMinWidth(colWidth * 1.5);
        powerLabel.setTextAlignment(TextAlignment.LEFT);
        powerLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(powerLabel, 0, 1);

        powerText = new Text();
        powerText.setText("0 watt");
        powerText.setWrappingWidth(colWidth * 1.5);
        powerText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(powerText, 1, 1);

////////////////////////////////////////////////////////////// grade
        Label gradeLevel = new Label("Grade: ");
        gradeLevel.setMinWidth(colWidth * 1.5);
        gradeLevel.setTextAlignment(TextAlignment.LEFT);
        gradeLevel.setAlignment(Pos.CENTER_LEFT);
        grid.add(gradeLevel, 4, 1);

        gradeText = new Text();
        gradeText.setText("0 degrees");
        gradeText.setWrappingWidth(colWidth * 1.5);
        gradeText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(gradeText, 5, 1);

////////////////////////////////////////////////////////////// Temperature
        Label tempLevel = new Label("Temp: ");
        tempLevel.setMinWidth(colWidth * 1.5);
        tempLevel.setTextAlignment(TextAlignment.LEFT);
        tempLevel.setAlignment(Pos.CENTER_LEFT);
        grid.add(tempLevel, 0, 2);

        tempText = new Text();
        tempText.setText("0 degrees");
        tempText.setWrappingWidth(colWidth * 1.5);
        tempText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(tempText, 1, 2);

        Label acLevel = new Label("AC: ");
        acLevel.setMinWidth(colWidth * 1.5);
        acLevel.setTextAlignment(TextAlignment.LEFT);
        acLevel.setAlignment(Pos.CENTER_LEFT);
        grid.add(acLevel, 4, 2);

        acText = new Text();
        acText.setText("off");
        acText.setWrappingWidth(colWidth * 1.5);
        acText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(acText, 5, 2);
////////////////////////////////////////////
        Label heatLevel = new Label("Heat: ");
        heatLevel.setMinWidth(colWidth * 1.5);
        heatLevel.setTextAlignment(TextAlignment.LEFT);
        heatLevel.setAlignment(Pos.CENTER_LEFT);
        grid.add(heatLevel, 0, 3);

        heatText = new Text();
        heatText.setText("off");
        heatText.setWrappingWidth(colWidth * 1.5);
        heatText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(heatText, 1, 3);

        Label leftDoors = new Label("LeftDoors Status: ");
        leftDoors.setMinWidth(colWidth * 1.5);
        leftDoors.setTextAlignment(TextAlignment.LEFT);
        leftDoors.setAlignment(Pos.CENTER_LEFT);
        grid.add(leftDoors, 4, 3);

        leftDoorsText = new Text();
        leftDoorsText.setText("closed");
        leftDoorsText.setWrappingWidth(colWidth * 1.5);
        leftDoorsText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(leftDoorsText, 5, 3);
////////////////////////////////////////////

        Label rightDoors = new Label("rightDoors Status: ");
        rightDoors.setMinWidth(colWidth * 1.5);
        rightDoors.setTextAlignment(TextAlignment.LEFT);
        rightDoors.setAlignment(Pos.CENTER_LEFT);
        grid.add(rightDoors, 0, 4);

        rightDoorsText = new Text();
        rightDoorsText.setText("closed");
        rightDoorsText.setWrappingWidth(colWidth * 1.5);
        rightDoorsText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(rightDoorsText, 1, 4);

        Label ebrake = new Label("Ebrake Status: ");
        ebrake.setMinWidth(colWidth * 1.5);
        ebrake.setTextAlignment(TextAlignment.LEFT);
        ebrake.setAlignment(Pos.CENTER_LEFT);
        grid.add(ebrake, 4, 4);

        eBrakeText = new Text();
        eBrakeText.setText("off");
        eBrakeText.setWrappingWidth(colWidth * 1.5);
        eBrakeText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(eBrakeText, 5, 4);
////////////////////////////////////////////

        Label sbrake = new Label("sbrake Status: ");
        sbrake.setMinWidth(colWidth * 1.5);
        sbrake.setTextAlignment(TextAlignment.LEFT);
        sbrake.setAlignment(Pos.CENTER_LEFT);
        grid.add(sbrake, 0, 5);

        sBrakeText = new Text();
        sBrakeText.setText("off");
        sBrakeText.setWrappingWidth(colWidth * 1.5);
        sBrakeText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(sBrakeText, 1, 5);

        Label massLabel = new Label("Weight: ");
        massLabel.setMinWidth(colWidth * 1.5);
        massLabel.setTextAlignment(TextAlignment.LEFT);
        massLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(massLabel, 4, 5);

        massText = new Text();
        massText.setText(" 0 lbs");
        massText.setWrappingWidth(colWidth * 1.5);
        massText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(massText, 5, 5);
////////////////////////////////////////////

        Label lightsLabel = new Label("Lights Status: ");
        lightsLabel.setMinWidth(colWidth * 1.5);
        lightsLabel.setTextAlignment(TextAlignment.LEFT);
        lightsLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(lightsLabel, 0, 6);

        lightsText = new Text();
        lightsText.setText(" off");
        lightsText.setWrappingWidth(colWidth * 1.5);
        lightsText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(lightsText, 1, 6);

        Label authorityLabel = new Label("Authority: ");
        authorityLabel.setMinWidth(colWidth * 1.5);
        authorityLabel.setTextAlignment(TextAlignment.LEFT);
        authorityLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(authorityLabel, 4, 6);

        authorityText = new Text();
        authorityText.setText(" 0");
        authorityText.setWrappingWidth(colWidth * 1.5);
        authorityText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(authorityText, 5, 6);

        Label blockLabel = new Label("Block: ");
        blockLabel.setMinWidth(colWidth * 1.5);
        blockLabel.setTextAlignment(TextAlignment.LEFT);
        blockLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(blockLabel, 0, 7);

        blockText = new Text();
        blockText.setText(" 0");
        blockText.setWrappingWidth(colWidth * 1.5);
        blockText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(blockText, 1, 7);

////////////////Buttons

        autoOrManualButton = new Button("Automatic");
        HBox autoOrManualButtonHbox = new HBox(0);
        autoOrManualButton.setOnAction(value ->  {
            if(autoOrManualButton.getText().equals("Manual")) {//label.getText().equals("Not clicked"))
                autoOrManualButton.setText("Automatic");
                System.out.println("Automatic");
                AutoVsManual = true;

            }
            //label.setText("Clicked!");
            else {
                autoOrManualButton.setText("Manual");
                System.out.println("Manual");
                AutoVsManual = false;
            }
            //label.setText("Not clicked!");
        });
        autoOrManualButton.setMinWidth(colWidth*3);
        autoOrManualButton.setMinWidth(colWidth*2);
        autoOrManualButton.setAlignment(Pos.CENTER);
        autoOrManualButtonHbox.setAlignment(Pos.CENTER_RIGHT);
        autoOrManualButtonHbox.getChildren().add(autoOrManualButton);
        grid.add(autoOrManualButton, 0, 8, 2, 1);

        sButton = new Button("Sbrake off");
        HBox sBrakeHbox = new HBox(0);
        sButton.setOnAction(value ->  {
            if(sButton.getText().equals("SBrake off")) {//label.getText().equals("Not clicked"))
                sButton.setText("Sbrake on");
                System.out.println("SBrake onn");
                sBrake = true;

            }
            //label.setText("Clicked!");
            else {
                sButton.setText("SBrake off");
                System.out.println("SBrake off");
                sBrake = false;
            }
            //label.setText("Not clicked!");
        });
        sButton.setMinWidth(colWidth*3);
        sButton.setMinWidth(colWidth*2);
        sButton.setAlignment(Pos.CENTER);
        sBrakeHbox.setAlignment(Pos.CENTER_RIGHT);
        sBrakeHbox.getChildren().add(sButton);
        grid.add(sButton, 2, 8, 2, 1);

        eButton = new Button("ebrake off");
        HBox eBrakeHbox = new HBox(0);
        eButton.setOnAction(value ->  {
            if(eButton.getText().equals("eBrake off")) {//label.getText().equals("Not clicked"))
                eButton.setText("ebrake on");
                System.out.println("eBrake onn");
                eBrake = true;

            }
            //label.setText("Clicked!");
            else {
                eButton.setText("eBrake off");
                System.out.println("eBrake off");
                eBrake = false;
            }
            //label.setText("Not clicked!");
        });
        eButton.setMinWidth(colWidth*3);
        eButton.setMinWidth(colWidth*2);
        eButton.setAlignment(Pos.CENTER);
        eBrakeHbox.setAlignment(Pos.CENTER_RIGHT);
        eBrakeHbox.getChildren().add(eButton);
        grid.add(eButton, 5, 8, 2, 1);
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
        speedText.setText(String.format( "%.2f", 2.23694 * speed )  + " mph");
    }

    protected void updateId(int newId){
        trainID = newId;
        trainIDText.setText(String.format( "%d", trainID ) );
    }
    protected void updatePower(double newPower){
        power = newPower/1000;
        powerText.setText(String.format( "%.2f", power ) + "k watt" );
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

    protected void updateMass(double newMass){
        mass = newMass;
        massText.setText(String.format( "%4.3f", mass ) + " lbs" );

    }

    protected void updateLights(boolean newLights){
        lights = newLights;
        if(lights)
            lightsText.setText( " on" );
        else
            lightsText.setText( " off" );
    }

    protected void updateAuthority(int newAuthority){
        authority = newAuthority;
        authorityText.setText(String.format( "%d", authority )  );
    }

    protected void updateBlock(int newBlock){
        block = newBlock;
        blockText.setText(String.format( "%d", block )  );
    }

    /*
    Getters block
        used for updated train in Manual mode
     */
    protected boolean getAutoVsManual(){ return AutoVsManual;}
    protected boolean getSBrake(){ return sBrake;}
    protected boolean getEBrake(){ return eBrake;}
    protected double getMass(){ return mass;}
    protected boolean getLights(){ return lights;}
    protected double getAuthority() { return authority;}
    protected double getBlock() { return block;}
    protected boolean getRightRDoors() { return rightDoors;}
    protected boolean getLeftDoors() { return leftDoors;}
    protected boolean getAc() { return ac;}
    protected boolean getHeat() { return heat;}
    protected double getTemperature() { return temperature;}
    protected double getGrade() { return grade;}
    protected double getSpeed() { return speed;}
}
