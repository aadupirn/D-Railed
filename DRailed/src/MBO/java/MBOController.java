package MBO.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class MBOController extends Application {
	private Stage primary;


	// TRAIN INFO TAB
	private Button trainScheduleButton;
	private ToggleButton mboToggle;
	private TableView trainTable;

	// TRAIN SCHEDULE DISPLAY TAB
	private TableView stationsTable;

	// WORKER SCHEDULE DISPLAY TAB
	private Button workerScheduleButton;
	private TableView workerTable;

	// PLANNER TAB
	private TextField passengerInput;
	private TextField conductorInput;
	private Button submitButton;

	// MURPHY TAB
	private ToggleButton murphyButton;


	// MUTATORS
	public void setTrainInfo(){ }

	/*
	* Method in charge of setting up gettting the elements associated with the portions
	* of the UI that have actions associated with them.
	*-----
	* No inputs
	*-----
	* No returns
	*/
	private void getUIElements(){
		trainScheduleButton = (Button) primary.getScene().lookup("#schedule_btn");
		mboToggle = (ToggleButton) primary.getScene().lookup("#mbo_toggle");
		trainTable = (TableView) primary.getScene().lookup("#train_info_table");

		stationsTable = (TableView) primary.getScene().lookup("#scheudle_table");

		workerScheduleButton = (Button) primary.getScene().lookup("#worker_schedule_btn");
		workerTable = (TableView) primary.getScene().lookup("#worker_schedule_table");

		passengerInput = (TextField) primary.getScene().lookup("#passengers");
		conductorInput = (TextField) primary.getScene().lookup("#conductors");
		submitButton = (Button) primary.getScene().lookup("#plan_btn");

		murphyButton = (ToggleButton) primary.getScene().lookup("#mbo_murphy_toggle");
	}

	// Button Actions



	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("../UI/MBOUI.fxml"));          // Gets

		Screen mainScreen = Screen.getPrimary();
		Rectangle2D screenBounds = mainScreen.getVisualBounds();
		primary = primaryStage;

		primary.setTitle("MBO Interface");
		primary.setScene(new Scene(root, screenBounds.getWidth(), screenBounds.getHeight()));

		primary.show();

		this.getUIElements();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
