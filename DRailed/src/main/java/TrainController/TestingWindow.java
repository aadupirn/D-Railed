package TrainController;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by aadu on 2/14/17.
 */
public class TestingWindow {
	private final Stage stage = new Stage();

	private TrainController trainController;

	//Create Scene
	private Scene scene;

	//Class strings
	private String windowTitle = "Testing Window";

	private Text powerCommand;

	private double previousError = 0;

	private double previousUK = 0;

	//Class integers
	private int windowWidth = 300;
	private int windowHight = 300;
	private int inset = 25;
	private int colWidth = 75;

	private TextField actualSpeedField;
	private TextField inputSpeedField;

	public TestingWindow(TrainController tc)
	{
		trainController = tc;

		stage.setTitle(windowTitle);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(inset, inset, inset, inset));
		grid.setVgap(inset);

		actualSpeedField = new TextField();
		inputSpeedField = new TextField();
		Text actualLabel = new Text("Actual Speed (mi/hr): ");
		Text inputLabel = new Text("Desired Speed (mi/hr): ");
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("CANCEL");

		powerCommand = new Text("Power Command: ");

		grid.add(actualLabel, 0, 0);

		actualSpeedField.setMinWidth(colWidth);
		grid.add(actualSpeedField, 1, 0);

		grid.add(inputLabel, 0, 1);

		inputSpeedField.setMinWidth(colWidth);
		grid.add(inputSpeedField, 1, 1);

		HBox hOKBtn = new HBox();
		hOKBtn.setAlignment(Pos.CENTER);
		hOKBtn.setMinWidth(colWidth);
		hOKBtn.getChildren().add(okBtn);
		grid.add(hOKBtn, 0, 2);

		HBox hCancelBtn = new HBox();
		hCancelBtn.setAlignment(Pos.CENTER);
		hCancelBtn.setMinWidth(colWidth);
		hCancelBtn.getChildren().add(cancelBtn);
		grid.add(hCancelBtn, 1, 2);

		powerCommand = new Text("Power Command: ");
		powerCommand.setTextAlignment(TextAlignment.CENTER);
		powerCommand.setWrappingWidth(colWidth*3);
		grid.add(powerCommand, 0, 3, 2, 1);

		Scene scene = new Scene(grid, windowWidth, windowHight);
		stage.setScene(scene);
		stage.show();

		okBtn.setOnAction((ActionEvent e) ->
		{
			try
			{
				Simulate(trainController.getKP(), trainController.getKI());
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		cancelBtn.setOnAction((ActionEvent e) ->
		{
			try
			{
				stage.close();
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});
	}

	private void Simulate(double kp, double ki)
	{
		double desiredSpeed = Double.valueOf(inputSpeedField.getText());
		double actualSpeed = Double.valueOf(actualSpeedField.getText());
		double difference = desiredSpeed - actualSpeed;
		double UK = previousUK + (.5)*(difference+previousError);
		double command = (kp*difference) + (ki*UK);
		powerCommand.setText("Power Command: " + command + " W");
		trainController.SetPowerText(String.valueOf(command));
		trainController.SetSpeedText(String.valueOf(actualSpeed));

		previousError = difference;
		previousUK = UK;
	}


}
