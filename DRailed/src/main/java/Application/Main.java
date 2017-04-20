package Application;

import DTime.DTime;
import MBO.java.MBOController;
import TrackController.TrackController;
import TrackModel.Track;
import TrackModel.TrackModelGUI;
import TrainModel.Train;
import ctc.CTCMain;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import TrackController.TrackController;
import TrackModel.Track;
import DTime.DTime;
import TrainModel.Train;
import MBO.java.MBOController;
import ctc.CTCMain;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
	//Class strings
	private String applicationTitle = "D-Railed";

	//Class integers
	private int windowWidth = 300;
	private int windowHight = 300;
	private int inset = 25;
	private int colWidth = 75;
	private DTime dTime;

	@Override
	public void start(Stage primaryStage) throws Exception{
		dTime = new DTime();
		//Module initialization


		primaryStage.setTitle(applicationTitle);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(inset, inset, inset, inset));
		//grid.setHgap(10);
		grid.setVgap(10);

		final Button time1 = new Button("Speed 1x");
		time1.setMinWidth(75);
		HBox htime1 = new HBox(10);
		htime1.setAlignment(Pos.CENTER);
		htime1.getChildren().add(time1);
		htime1.setMinWidth(75);
		grid.add(htime1, 0, 0,2,1);


		final Button time10 = new Button("Speed 10x");
		time10.setMinWidth(75);
		HBox htime10 = new HBox(10);
		htime10.setAlignment(Pos.CENTER);
		htime10.getChildren().add(time10);
		htime10.setMinWidth(75);
		grid.add(htime10, 0, 1,2,1);

		final Button sysBtn = new Button("Run System");
		sysBtn.setAlignment(Pos.CENTER);
		sysBtn.setMinWidth(150);
		HBox hSysBtn = new HBox(10);
		hSysBtn.setAlignment(Pos.CENTER);
		hSysBtn.getChildren().add(sysBtn);
		hSysBtn.setMinWidth(150);
		grid.add(hSysBtn, 0, 2,1,2);

		Scene scene = new Scene(grid, windowWidth, windowHight);
		primaryStage.setScene(scene);
		primaryStage.show();

		//handle button press
		time1.setOnAction((ActionEvent e) -> {
			dTime.setMultiplier(1);
		});
		time10.setOnAction((ActionEvent e) -> {
			dTime.setMultiplier(10);
		});
		sysBtn.setOnAction((ActionEvent e) ->
		{
			MBOController MBOCtrl = new MBOController(dTime.getTimer());
			try {
				MBOCtrl.start(new Stage());
				dTime = new DTime();
				Track track = new Track();
				track.couple("GREEN", "TIGHT");
				// Initialize Track Controllers
				ArrayList<Integer> blocks1, plc1, blocks2, plc2;
				blocks1 = new ArrayList<>();
				plc1 = new ArrayList<>();
				blocks2 = new ArrayList<>();
				plc2 = new ArrayList<>();
				for (int i = 1; i <= 152; i++)
				{
					if (i <= 62 || i >= 117)
						blocks1.add(i);
					if (i <= 67 || i >= 118)
						plc1.add(i);
					if (i >= 63 && i <= 116)
						blocks2.add(i);
					if (i >= 58 && i <= 121)
						plc2.add(i);
				}

				TrackController tc1 = new TrackController("GREEN", blocks1, plc1, dTime, 1);
				TrackController tc2 = new TrackController("GREEN", blocks2, plc2, dTime, 2);
				TrackController tc3 = new TrackController(dTime, "RED");
				ArrayList<TrackController> controllers = new ArrayList<TrackController>();
				controllers.add(tc1);
				controllers.add(tc2);
				controllers.add(tc3);
				for (TrackController tc : controllers)
				{
					dTime.addTrackC(tc);
					tc.setTrack(track);
					tc.setControllers(controllers);
				}
				tc1.showUI();
				// End Track Controller Init
				dTime.setMBO(MBOCtrl.getMBO());
				TrackModelGUI trackModel = new TrackModelGUI(track);
				CTCMain ctc = new CTCMain();
				ctc.setTrackController(controllers);
				ctc.start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}