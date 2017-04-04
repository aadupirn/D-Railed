package TrainController;

import TrainModel.Train;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by aadu on 2/3/17.
 */
public class TrainController
{

	//region Class Variables
	private final Stage stage = new Stage();

	private TextArea notifications;

	//Create Scene
	private Scene scene;

	//Class strings
	private String windowTitle = "Train Controller";
	private String upcomingStation = "";
	private String route;
	private String notificationText = "";

	//Class integers
	private int windowWidth = 800;
	private int windowHight = 500;
	private int inset = 25;
	private int colWidth = 75;
	private int lightStatus;
	private int lDoorStatus;
	private int rDoorStatus;
	private int acStatus;
	private int heatStatus;
	private int movementStatus;
	private int locationStatus;
	private int trainID;
	private int currentBlockID;


	private double speed;
	private double power;
	private double kp = 1;
	private double ki = 1;
	private double temperature;
	private double powerLimit;
	private double desiredSpeed;

	private boolean eBrakeStatus;
	private boolean sBrakeStatus;

	private Text speedText;
	private Text powerText;

	private Train train;

	private LocationCalculator locationCalculator;
	private ControlCalculator controlCalculator;




	//endregion

	//region Constructor
	public TrainController(Train iTrain) throws IOException
	{
		train = iTrain;
		trainID = 1;
		route = "test";
		acStatus = 0;
		heatStatus = 0;
		lDoorStatus = 0;
		rDoorStatus = 0;
		lightStatus = 0;

		//region UI code
		stage.setTitle(windowTitle);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(inset, inset, inset, inset));
		grid.setVgap(10);

		//Row Index 0
		Label trainIDLabel = new Label("Train: ");
		trainIDLabel.setTextAlignment(TextAlignment.LEFT);
		trainIDLabel.setMinWidth(colWidth);
		trainIDLabel.setAlignment(Pos.CENTER_LEFT);
		grid.add(trainIDLabel, 0, 0);

		Text trainIDText = new Text(Integer.toString(trainID));
		trainIDText.setWrappingWidth(colWidth*2);
		trainIDText.setTextAlignment(TextAlignment.RIGHT);
		grid.add(trainIDText, 0, 0);

		Label speedLabel = new Label("Speed: ");
		speedLabel.setMinWidth(colWidth*1.5);
		speedLabel.setTextAlignment(TextAlignment.RIGHT);
		speedLabel.setAlignment(Pos.CENTER_RIGHT);
		grid.add(speedLabel, 3, 0);

		speedText = new Text();
		speedText.setText("0 mph");
		speedText.setWrappingWidth(colWidth*1.5);
		speedText.setTextAlignment(TextAlignment.LEFT);
		grid.add(speedText, 4, 0);

		final Button manualBtn = new Button("MANUAL");
		HBox hManualBtn = new HBox(0);
		hManualBtn.setMinWidth(colWidth*3);
		manualBtn.setMinWidth(colWidth*2);
		manualBtn.setAlignment(Pos.CENTER);
		hManualBtn.setAlignment(Pos.CENTER_RIGHT);
		hManualBtn.getChildren().add(manualBtn);
		grid.add(hManualBtn, 5, 0, 3, 1);

		//Row Index 1
		Label routeLabel = new Label("Route: ");
		routeLabel.setTextAlignment(TextAlignment.LEFT);
		routeLabel.setMinWidth(colWidth);
		routeLabel.setAlignment(Pos.CENTER_LEFT);
		grid.add(routeLabel, 0, 1);

		Text routeText = new Text();
		routeText.setWrappingWidth(colWidth*2);
		routeText.setText(route);
		routeText.setTextAlignment(TextAlignment.RIGHT);
		grid.add(routeText, 0, 1);

		Label powerLabel = new Label("Power: ");
		powerLabel.setMinWidth(colWidth*1.5);
		powerLabel.setTextAlignment(TextAlignment.RIGHT);
		powerLabel.setAlignment(Pos.CENTER_RIGHT);
		grid.add(powerLabel, 3, 1);

		powerText = new Text();
		powerText.setText("0 W");
		powerText.setWrappingWidth(colWidth*1.5);
		powerText.setTextAlignment(TextAlignment.LEFT);
		grid.add(powerText, 4, 1);

		final Button automaticBtn = new Button("AUTOMATIC");
		HBox hAutomaticBtn = new HBox(0);
		hAutomaticBtn.setMinWidth(colWidth*3);
		automaticBtn.setMinWidth(colWidth*2);
		automaticBtn.setAlignment(Pos.CENTER);
		hAutomaticBtn.setAlignment(Pos.CENTER_RIGHT);
		hAutomaticBtn.getChildren().add(automaticBtn);
		grid.add(hAutomaticBtn, 5, 1, 3, 1);

		//Row Index 2
		Label maStatusLabel = new Label("Control Status: ");
		maStatusLabel.setTextAlignment(TextAlignment.RIGHT);
		maStatusLabel.setMinWidth(colWidth * 1.5);
		maStatusLabel.setAlignment(Pos.CENTER_RIGHT);
		grid.add(maStatusLabel, 3, 2);

		Text controlStatus = new Text();
		controlStatus.setWrappingWidth(colWidth * 1.5);
		controlStatus.setText("TEMPSTATUS");
		controlStatus.setTextAlignment(TextAlignment.LEFT);
		grid.add(controlStatus, 4, 2);

		final Button emerBtn = new Button("EMERGENCY\nSTOP");
		emerBtn.setTextAlignment(TextAlignment.CENTER);
		HBox hEmerBtn = new HBox(0);
		hEmerBtn.setMinWidth(colWidth * 3);
		hEmerBtn.setMinHeight(colWidth);
		emerBtn.setMinWidth(colWidth*2);
		emerBtn.setMinHeight(colWidth);
		emerBtn.setAlignment(Pos.CENTER);
		hEmerBtn.setAlignment(Pos.TOP_RIGHT);
		hEmerBtn.getChildren().add(emerBtn);
		grid.add(hEmerBtn, 5, 2, 3, 2);

		//Row Index 3
		Label acLabel = new Label("Air Conditioning");
		acLabel.setTextAlignment(TextAlignment.LEFT);
		acLabel.setMinWidth(colWidth);
		acLabel.setAlignment(Pos.CENTER_LEFT);
		grid.add(acLabel, 0, 3);

		GridPane acGrid = new GridPane();

		final ToggleGroup acToggleGroup = new ToggleGroup();

		RadioButton acOn = new RadioButton("On");
		acOn.setToggleGroup(acToggleGroup);
		acOn.setMaxWidth(colWidth);
		acOn.setMinWidth(colWidth);
		acGrid.add(acOn, 0, 0);

		RadioButton acOff = new RadioButton("Off");
		acOff.setToggleGroup(acToggleGroup);
		acOff.setMaxWidth(colWidth);
		acOff.setSelected(true);
		acOff.setMinWidth(colWidth);
		acGrid.add(acOff, 0, 1);

		acGrid.setMinWidth(colWidth*2);
		grid.add(acGrid, 1, 3, 2, 1);

		notifications = new TextArea ("Notifications here");
		notifications.setMaxWidth(colWidth*3.5);
		notifications.setWrapText(true);
		notifications.setEditable(false);
		grid.add(notifications, 3, 3, 3, 4);

		//Row Index 4
		Label heatLabel = new Label("Heat");
		heatLabel.setTextAlignment(TextAlignment.LEFT);
		heatLabel.setMinWidth(colWidth);
		heatLabel.setAlignment(Pos.CENTER_LEFT);
		grid.add(heatLabel, 0, 4);

		GridPane heatGrid = new GridPane();

		final ToggleGroup heatToggleGroup = new ToggleGroup();

		RadioButton heatOn = new RadioButton("On");
		heatOn.setToggleGroup(heatToggleGroup);
		heatOn.setSelected(false);
		heatOn.setMaxWidth(colWidth);
		heatOn.setMinWidth(colWidth);
		heatGrid.add(heatOn, 0, 0);

		RadioButton heatOff = new RadioButton("Off");
		heatOff.setToggleGroup(heatToggleGroup);
		heatOff.setSelected(true);
		heatOff.setAlignment(Pos.CENTER_LEFT);
		heatOff.setMaxWidth(colWidth);
		heatOff.setMinWidth(colWidth);
		heatGrid.add(heatOff, 0, 1);

		heatGrid.setMinWidth(colWidth*2);
		grid.add(heatGrid, 1, 4, 2, 1);

		final Button brakeBtn = new Button("SERVICE\nBRAKE");
		brakeBtn.setTextAlignment(TextAlignment.CENTER);
		HBox hBrakeBtn = new HBox(0);
		hBrakeBtn.setMinWidth(colWidth * 3);
		hBrakeBtn.setMinHeight(colWidth);
		brakeBtn.setMinWidth(colWidth*2);
		brakeBtn.setMinHeight(colWidth);
		brakeBtn.setAlignment(Pos.CENTER);
		hBrakeBtn.setAlignment(Pos.TOP_RIGHT);
		hBrakeBtn.getChildren().add(brakeBtn);
		grid.add(hBrakeBtn, 5, 4, 3, 2);


		//Row Index 5
		Label lightsLabel = new Label("Lights");
		lightsLabel.setTextAlignment(TextAlignment.LEFT);
		lightsLabel.setMinWidth(colWidth);
		lightsLabel.setAlignment(Pos.CENTER_LEFT);
		grid.add(lightsLabel, 0, 5);

		GridPane lightsGrid = new GridPane();

		final ToggleGroup lightsToggleGroup = new ToggleGroup();

		RadioButton lightsOn = new RadioButton("On");
		lightsOn.setToggleGroup(lightsToggleGroup);
		lightsOn.setSelected(false);
		lightsOn.setMaxWidth(colWidth);
		lightsOn.setMinWidth(colWidth);
		lightsGrid.add(lightsOn, 0, 0);

		RadioButton lightsOff = new RadioButton("Off");
		lightsOff.setToggleGroup(lightsToggleGroup);
		lightsOff.setSelected(true);
		lightsOff.setAlignment(Pos.CENTER_LEFT);
		lightsOff.setMaxWidth(colWidth);
		lightsOff.setMinWidth(colWidth);
		lightsGrid.add(lightsOff, 0, 1);

		lightsGrid.setMinWidth(colWidth*2);
		grid.add(lightsGrid, 1, 5, 2, 1);

		//Row Index 6
		Label lDoorLabel = new Label("Left Door");
		lDoorLabel.setTextAlignment(TextAlignment.LEFT);
		lDoorLabel.setMinWidth(colWidth);
		lDoorLabel.setAlignment(Pos.CENTER_LEFT);
		grid.add(lDoorLabel, 0, 6);

		GridPane lDoorGrid = new GridPane();

		final ToggleGroup lDoorToggleGroup = new ToggleGroup();

		RadioButton lDoorOpen = new RadioButton("Open");
		lDoorOpen.setToggleGroup(lDoorToggleGroup);
		lDoorOpen.setSelected(false);
		lDoorOpen.setMaxWidth(colWidth);
		lDoorOpen.setMinWidth(colWidth);
		lDoorGrid.add(lDoorOpen, 0, 0);

		RadioButton lDoorClosed = new RadioButton("Closed");
		lDoorClosed.setToggleGroup(lDoorToggleGroup);
		lDoorClosed.setSelected(true);
		lDoorClosed.setAlignment(Pos.CENTER_LEFT);
		lDoorClosed.setMaxWidth(colWidth);
		lDoorClosed.setMinWidth(colWidth);
		lDoorGrid.add(lDoorClosed, 0, 1);

		lDoorGrid.setMinWidth(colWidth*2);
		grid.add(lDoorGrid, 1, 6, 2, 1);

		GridPane speedGrid = new GridPane();

		Button incSpeed = new Button("+");
		HBox hIncSpeed = new HBox();
		hIncSpeed.setAlignment(Pos.CENTER);
		hIncSpeed.getChildren().add(incSpeed);
		speedGrid.add(hIncSpeed, 0, 0);

		Text speed = new Text("XX mph");
		speed.setTextAlignment(TextAlignment.CENTER);
		speedGrid.add(speed, 0, 1);

		Button decSpeed = new Button("-");
		HBox hDecSpeed = new HBox();
		hDecSpeed.setAlignment(Pos.CENTER);
		hDecSpeed.getChildren().add(decSpeed);
		speedGrid.add(hDecSpeed, 0, 2);
		speedGrid.setMinWidth(colWidth*3);
		speedGrid.setAlignment(Pos.CENTER);
		grid.add(speedGrid, 5, 6, 3, 1);


		//Row Index 7
		Label rDoorLabel = new Label("Right Door");
		rDoorLabel.setTextAlignment(TextAlignment.LEFT);
		rDoorLabel.setMinWidth(colWidth);
		rDoorLabel.setAlignment(Pos.CENTER_LEFT);
		grid.add(rDoorLabel, 0, 7);

		GridPane rDoorGrid = new GridPane();

		final ToggleGroup rDoorToggleGroup = new ToggleGroup();

		RadioButton rDoorOpen = new RadioButton("Open");
		rDoorOpen.setToggleGroup(rDoorToggleGroup);
		rDoorOpen.setSelected(false);
		rDoorOpen.setMaxWidth(colWidth);
		rDoorOpen.setMinWidth(colWidth);
		rDoorGrid.add(rDoorOpen, 0, 0);

		RadioButton rDoorClosed = new RadioButton("Closed");
		rDoorClosed.setToggleGroup(rDoorToggleGroup);
		rDoorClosed.setSelected(true);
		rDoorClosed.setAlignment(Pos.CENTER_LEFT);
		rDoorClosed.setMaxWidth(colWidth);
		rDoorClosed.setMinWidth(colWidth);
		rDoorGrid.add(rDoorClosed, 0, 1);

		rDoorGrid.setMinWidth(colWidth*2);
		grid.add(rDoorGrid, 1, 7, 2, 1);

		Text movementStatus = new Text();
		movementStatus.setWrappingWidth(colWidth*3);
		movementStatus.setText("MOVEMENTSTATUS");
		movementStatus.setTextAlignment(TextAlignment.CENTER);
		grid.add(movementStatus, 3, 7, 2, 1);

		Button makeAnnouncementBtn = new Button("Make Announcement");
		HBox hMakeAnnouncementBtn = new HBox();
		makeAnnouncementBtn.setTextAlignment(TextAlignment.CENTER);
		hMakeAnnouncementBtn.setAlignment(Pos.CENTER_RIGHT);
		hMakeAnnouncementBtn.setMinWidth(colWidth*2);
		makeAnnouncementBtn.setMinWidth(colWidth*2);
		hMakeAnnouncementBtn.getChildren().add(makeAnnouncementBtn);
		grid.add(hMakeAnnouncementBtn, 5, 7, 3, 1);

		// Row 8
		Button testBtn = new Button("Test Train Controller");
		HBox hTextBtn = new HBox();
		testBtn.setTextAlignment(TextAlignment.CENTER);
		hTextBtn.setAlignment(Pos.CENTER);
		hTextBtn.setMinWidth(colWidth*2);
		hTextBtn.getChildren().add(testBtn);
		grid.add(hTextBtn, 3, 8, 2, 1);

		Button powerControlBtn = new Button("Power Control");
		HBox hPowerControlBtn = new HBox();
		powerControlBtn.setTextAlignment(TextAlignment.CENTER);
		hPowerControlBtn.setAlignment(Pos.CENTER_RIGHT);
		hPowerControlBtn.setMinWidth(colWidth*2);
		hPowerControlBtn.getChildren().add(powerControlBtn);
		grid.add(hPowerControlBtn, 5, 8, 3, 1);

		//endregion

		//region Button Handlers
		makeAnnouncementBtn.setOnAction((ActionEvent e) ->
		{
			try
			{
				AnnouncementWindow announcementWindow = new AnnouncementWindow(this);
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		testBtn.setOnAction((ActionEvent e) ->
		{
			try
			{
				TestingWindow testingWindow = new TestingWindow(this);
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		// Button Handlers
		powerControlBtn.setOnAction((ActionEvent e) ->
		{
			try
			{
				PowerControlWindow powerControlWindow = new PowerControlWindow(this);
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		//endregion

		//region RadioButtonHandlers
		acToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
			{
				RadioButton toggled = (RadioButton)acToggleGroup.getSelectedToggle();
				if(toggled.getText().equals("On"))
				{
					acStatus = 1;
				}
				else if(toggled.getText().equals("Off"))
				{
					acStatus = 0;
				}
			}
		});

		heatToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
			{
				RadioButton toggled = (RadioButton)heatToggleGroup.getSelectedToggle();
				if(toggled.getText().equals("On"))
				{
					heatStatus = 1;
				}
				else if(toggled.getText().equals("Off"))
				{
					heatStatus = 0;
				}
			}
		});

		lDoorToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
			{
				RadioButton toggled = (RadioButton)lDoorToggleGroup.getSelectedToggle();
				if(toggled.getText().equals("On"))
				{
					lDoorStatus = 1;
				}
				else if(toggled.getText().equals("Off"))
				{
					lDoorStatus = 0;
				}
			}
		});

		rDoorToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
			{
				RadioButton toggled = (RadioButton)rDoorToggleGroup.getSelectedToggle();
				if(toggled.getText().equals("On"))
				{
					rDoorStatus = 1;
				}
				else if(toggled.getText().equals("Off"))
				{
					rDoorStatus = 0;
				}
			}
		});

		rDoorToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
			{
				RadioButton toggled = (RadioButton)rDoorToggleGroup.getSelectedToggle();
				if(toggled.getText().equals("On"))
				{
					rDoorStatus = 1;
				}
				else if(toggled.getText().equals("Off"))
				{
					rDoorStatus = 0;
				}
			}
		});

		lightsToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
			{
				RadioButton toggled = (RadioButton)lightsToggleGroup.getSelectedToggle();
				if(toggled.getText().equals("On"))
				{
					lightStatus = 1;
				}
				else if(toggled.getText().equals("Off"))
				{
					lightStatus = 0;
				}
			}
		});
		//endregion



		Scene scene = new Scene(grid, windowWidth, windowHight);
		stage.setScene(scene);
		stage.show();
	}

	//endregion

	//region Public Methods

	public void SetPowerText(String in)
	{
		powerText.setText(in + " W");
	}

	public void SetSpeedText(String in)
	{
		powerText.setText(in + " mph");
	}

	public void MakeAnnouncement(String announcement)
	{
		String newNotification;
		if(notifications.getText().equals("Notifications here"))
		{
			newNotification = "";
		}
		else
		{
			newNotification = notifications.getText();
		}

		notifications.setText(newNotification + "ANN: " + announcement + "\n");
	}

	public void setPowerVars(double kpIn, double kiIn)
	{
		kp = kpIn;
		ki = kiIn;
	}

	public double getKP()
	{
		return kp;
	}

	public double getKI()
	{
		return ki;
	}

	public void Update()
	{
		System.out.println("Update");
	}

	//endregion

	//region Private Methods

	//endregion


}
