
package TrainModel;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TrainModel {
    private final Stage primaryStage = new Stage();

    //Class strings
    private String applicationTitle = "Train Model";

    //Class integers
    private int windowWidth = 775;
    private int windowHight = 475;
    private int inset = 25;
    private int colWidth = 75;

    public TrainModel() throws Exception
    {
        primaryStage.setTitle(applicationTitle);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER); //sets the value of alignment, CENETER is the center both vertically and horizonataally
        grid.setPadding(new Insets(inset, inset, inset, inset));
        //grid.setHgap(10);
        grid.setVgap(10);

        //Row 0
        Label trainIDLabel = new Label("Train name: "); //THis is the static label
        trainIDLabel.setTextAlignment(TextAlignment.LEFT);
        trainIDLabel.setMinWidth(colWidth);
        trainIDLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(trainIDLabel, 0, 0);

        Text trainIDText = new Text();  // will be changing
        trainIDText.setWrappingWidth(colWidth*2);
        trainIDText.setText("Test GUI");
        trainIDText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(trainIDText, 0, 0);

        Label trackLabel = new Label("Track number: ");
        trackLabel.setMinWidth(colWidth*1.5);
        trackLabel.setTextAlignment(TextAlignment.RIGHT);
        trackLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(trackLabel, 3, 0);

        Text trackLabelText = new Text();
        trackLabelText.setText(" Track 0");
        trackLabelText.setWrappingWidth(colWidth*1.5);
        trackLabelText.setTextAlignment(TextAlignment.LEFT);
        grid.add(trackLabelText, 4, 0);

        //Label label = new Label("Not clicked");
        Button autoOrManualButton = new Button("Automatic");
        HBox autoOrManualButtonHbox = new HBox(0);
        autoOrManualButton.setOnAction(value ->  {
            if(autoOrManualButton.getText().equals("Manual")) {//label.getText().equals("Not clicked"))
                autoOrManualButton.setText("Automatic");
                System.out.println("Automatic");
            }
                //label.setText("Clicked!");
            else {
                autoOrManualButton.setText("Manual");
                System.out.println("Manual");
            }
            //label.setText("Not clicked!");
        });
        autoOrManualButton.setMinWidth(colWidth*3);
        autoOrManualButton.setMinWidth(colWidth*2);
        autoOrManualButton.setAlignment(Pos.CENTER);
        autoOrManualButtonHbox.setAlignment(Pos.CENTER_RIGHT);
        autoOrManualButtonHbox.getChildren().add(autoOrManualButton);
        grid.add(autoOrManualButton, 5, 0, 3, 1);

        //Row 1
        Label speedLabel = new Label();//new Label("Speed : ");
        speedLabel.setTextAlignment(TextAlignment.LEFT);
        speedLabel.setMinWidth(colWidth);
        speedLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(speedLabel, 0, 1);
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
        Text speedLabelText = new Text();
        speedLabelText.setWrappingWidth(colWidth*2);
        speedLabelText.setText(" 0 mph");
        speedLabelText.setTextAlignment(TextAlignment.CENTER);
        grid.add(speedLabelText, 0, 1);
////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
        Label blockNumber = new Label("BlockNumber : ");
        blockNumber.setMinWidth(colWidth*1.5);
        blockNumber.setTextAlignment(TextAlignment.CENTER);
        blockNumber.setAlignment(Pos.CENTER);
        grid.add(blockNumber, 3, 1);

        Text blockNumberText = new Text();
        blockNumberText.setText(" 586");
        blockNumberText.setWrappingWidth(colWidth*1.5);
        blockNumberText.setTextAlignment(TextAlignment.LEFT);
        grid.add(blockNumberText, 4, 1);

        Label blockPower = new Label("Block Power : ");
        blockPower.setMinWidth(colWidth*1);
        blockPower.setTextAlignment(TextAlignment.RIGHT);
        blockPower.setAlignment(Pos.CENTER_RIGHT);
        grid.add(blockPower, 6, 1);

        Text blockPowerText = new Text();
        blockPowerText.setText(" 9000 Watts");
        blockPowerText.setWrappingWidth(colWidth*1.22);
        blockPowerText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(blockPowerText, 7, 1);

        //Row 2
        Label distanceLabel = new Label("Distance to Authority : ");
        distanceLabel.setTextAlignment(TextAlignment.LEFT);
        distanceLabel.setMinWidth(colWidth * 1.5);
        distanceLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(distanceLabel, 0, 2);  // Column, Row

        Text distanceLabelText = new Text();
        distanceLabelText.setWrappingWidth(colWidth * 1.5);
        distanceLabelText.setText("5000 M");
        distanceLabelText.setTextAlignment(TextAlignment.CENTER);
        grid.add(distanceLabelText, 1, 2);

        Label temperuateLabel = new Label("Temperature : ");
        temperuateLabel.setMinWidth(colWidth*1.5);
        temperuateLabel.setTextAlignment(TextAlignment.CENTER);
        temperuateLabel.setAlignment(Pos.CENTER);
        grid.add(temperuateLabel, 3, 2);

        Text temperuateLabelText = new Text();
        temperuateLabelText.setText(" 28 C");
        temperuateLabelText.setWrappingWidth(colWidth*1.5);
        temperuateLabelText.setTextAlignment(TextAlignment.LEFT);
        grid.add(temperuateLabelText, 4, 2);

        Button acButton = new Button("Air Conditioning On");
        HBox acButtonHbox = new HBox(0);
        acButton.setOnAction(value ->  {
            if(acButton.getText().equals("Air Conditioning On"))//label.getText().equals("Not clicked"))
                acButton.setText("Air Conditioning Off");
                //label.setText("Clicked!");
            else
                acButton.setText("Air Conditioning On");
            //label.setText("Not clicked!");
        });
        acButton.setMinWidth(colWidth*3);
        acButton.setMinWidth(colWidth*2);
        acButton.setAlignment(Pos.CENTER);
        acButtonHbox.setAlignment(Pos.CENTER_RIGHT);
        acButtonHbox.getChildren().add(acButton);
        grid.add(acButton, 5, 2, 3, 1);

        //Row Index 3
        Label doorLabel = new Label("Door Status : ");
        doorLabel.setTextAlignment(TextAlignment.LEFT);
        doorLabel.setMinWidth(colWidth * 1.5);
        doorLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(doorLabel, 0, 3);  // Column, Row

        Text doorLabelText = new Text();
        doorLabelText.setWrappingWidth(colWidth * 1.5);
        doorLabelText.setText("Closed");
        doorLabelText.setTextAlignment(TextAlignment.CENTER);
        grid.add(doorLabelText, 1, 3);

        Label lightsLabel = new Label("Lights : ");
        lightsLabel.setMinWidth(colWidth*1.5);
        lightsLabel.setTextAlignment(TextAlignment.CENTER);
        lightsLabel.setAlignment(Pos.CENTER);
        grid.add(lightsLabel, 3, 3);

        Text lightsLabelText = new Text();
        lightsLabelText.setText(" On");
        lightsLabelText.setWrappingWidth(colWidth*1.5);
        lightsLabelText.setTextAlignment(TextAlignment.LEFT);
        grid.add(lightsLabelText, 4, 3);

        Label beaconLabel = new Label("Beacon Status : ");
        beaconLabel.setMinWidth(colWidth*1);
        beaconLabel.setTextAlignment(TextAlignment.RIGHT);
        beaconLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(beaconLabel, 6, 3);

        Text beaconLabelText = new Text();
        beaconLabelText.setText("Active");
        beaconLabelText.setWrappingWidth(colWidth*1.22);
        beaconLabelText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(beaconLabelText, 7, 3);

        //Row Index 4
        Label cartsLabel = new Label("Numb. of Carts : ");
        cartsLabel.setTextAlignment(TextAlignment.LEFT);
        cartsLabel.setMinWidth(colWidth * 1.5);
        cartsLabel.setAlignment(Pos.CENTER_LEFT);
        grid.add(cartsLabel, 0, 4);  // Column, Row

        Text cartsLabelText = new Text();
        cartsLabelText.setWrappingWidth(colWidth * 1.5);
        cartsLabelText.setText("4");
        cartsLabelText.setTextAlignment(TextAlignment.CENTER);
        grid.add(cartsLabelText, 1, 4);

        Label passengersLabel = new Label("Numb. of Passengers : ");
        passengersLabel.setMinWidth(colWidth*1.5);
        passengersLabel.setTextAlignment(TextAlignment.CENTER);
        passengersLabel.setAlignment(Pos.CENTER);
        grid.add(passengersLabel, 3, 4);

        Text passengersLabelText = new Text();
        passengersLabelText.setText(" 250");
        passengersLabelText.setWrappingWidth(colWidth*1.5);
        passengersLabelText.setTextAlignment(TextAlignment.LEFT);
        grid.add(passengersLabelText, 4, 4);

        Label weightLabel = new Label("Current Weight : ");
        weightLabel.setMinWidth(colWidth*1);
        weightLabel.setTextAlignment(TextAlignment.RIGHT);
        weightLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(weightLabel, 6, 4);

        Text weightLabelText = new Text();
        weightLabelText.setText("400000 kg");
        weightLabelText.setWrappingWidth(colWidth*1.22);
        weightLabelText.setTextAlignment(TextAlignment.RIGHT);
        grid.add(weightLabelText, 7, 4);

        //Row 5
        Button emergencyButton = new Button("Emergency Brake On");
        HBox emergencyButtonHbox = new HBox(0);
        emergencyButton.setOnAction(value ->  {
            if(acButton.getText().equals("Emergency Brake On"))//label.getText().equals("Not clicked"))
                acButton.setText("Emergency Brake Off");
                //label.setText("Clicked!");
            else
                acButton.setText("Emergency Brake On");
            //label.setText("Not clicked!");
        });
        emergencyButtonHbox.setMinWidth(colWidth*9.5);
        emergencyButton.setMinWidth(colWidth*9.5);
        emergencyButton.setAlignment(Pos.CENTER);
        emergencyButtonHbox.setAlignment(Pos.CENTER_RIGHT);
        emergencyButtonHbox.getChildren().add(emergencyButton);
        grid.add(emergencyButton, 0, 5, 7, 1);


        Scene scene = new Scene(grid, windowWidth, windowHight);
        primaryStage.setScene(scene);
        primaryStage.show();

        TrainModelMain trainModel = new TrainModelMain();
        int i = 1000;
        while(i > 0){
            Double velocity = trainModel.TimeCalc();
            grid.getChildren().remove(speedLabelText);
            speedLabelText = new Text();
            speedLabelText.setWrappingWidth(colWidth*2);
            speedLabelText.setText(" " + velocity );
            speedLabelText.setTextAlignment(TextAlignment.CENTER);
            i--;
            grid.add(speedLabelText, 0, 1);
        }
    }
}