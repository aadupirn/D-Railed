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

		final Button ctcBtn = new Button("CTC");
		ctcBtn.setMinWidth(150);
		HBox hCtcBtn = new HBox(10);
		hCtcBtn.setAlignment(Pos.CENTER);
		hCtcBtn.getChildren().add(ctcBtn);
		hCtcBtn.setMinWidth(150);
		grid.add(hCtcBtn, 0, 0);

		final Button trackControllerBtn = new Button("Track Controller");
		trackControllerBtn.setMinWidth(150);
		HBox hTrackControllerBtn = new HBox(10);
		hTrackControllerBtn.setAlignment(Pos.CENTER);
		hTrackControllerBtn.getChildren().add(trackControllerBtn);
		hTrackControllerBtn.setMinWidth(150);
		grid.add(hTrackControllerBtn, 0, 1);

		final Button trackModelBtn = new Button("Track Model");
		trackModelBtn.setMinWidth(150);
		HBox hTrackModelBtn = new HBox(10);
		hTrackModelBtn.setAlignment(Pos.CENTER);
		hTrackModelBtn.getChildren().add(trackModelBtn);
		hTrackModelBtn.setMinWidth(150);
		grid.add(hTrackModelBtn, 0, 2);

		final Button trainModelBtn = new Button("Train Model");
		trainModelBtn.setMinWidth(150);
		HBox hTrainModelBtn = new HBox(10);
		hTrainModelBtn.setAlignment(Pos.CENTER);
		hTrainModelBtn.getChildren().add(trainModelBtn);
		hTrainModelBtn.setMinWidth(150);
		grid.add(hTrainModelBtn, 0, 3);

		final Button trainControllerBtn = new Button("Train Controller");
		trainControllerBtn.setMinWidth(150);
		HBox hTrainControllerBtn = new HBox(10);
		hTrainControllerBtn.setAlignment(Pos.CENTER);
		hTrainControllerBtn.getChildren().add(trainControllerBtn);
		hTrainControllerBtn.setMinWidth(150);
		grid.add(hTrainControllerBtn, 0, 4);

		final Button mboBtn = new Button("MBO");
		mboBtn.setMinWidth(150);
		HBox hMboBtn = new HBox(10);
		hMboBtn.setAlignment(Pos.CENTER);
		hMboBtn.getChildren().add(mboBtn);
		hMboBtn.setMinWidth(150);
		grid.add(hMboBtn, 0, 5);

		final Button sysBtn = new Button("System Prototype");
		sysBtn.setMinWidth(150);
		HBox hSysBtn = new HBox(10);
		hSysBtn.setAlignment(Pos.CENTER);
		hSysBtn.getChildren().add(sysBtn);
		hSysBtn.setMinWidth(150);
		grid.add(hSysBtn, 0, 6);

		Scene scene = new Scene(grid, windowWidth, windowHight);
		primaryStage.setScene(scene);
		primaryStage.show();

		//handle button press
		ctcBtn.setOnAction((ActionEvent e) ->
		{
			try{
				CTCMain ctc = new CTCMain();
				ctc.start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		trackControllerBtn.setOnAction((ActionEvent e) ->
		{
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

        trackModelBtn.setOnAction((ActionEvent e) ->
        {
            try {
				Track track = new Track();
				TrackModelGUI tgui = new TrackModelGUI(track);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            catch (Exception e2) {
            	//lol
			}
        });

		trainModelBtn.setOnAction((ActionEvent e) ->
		{
			try {
				Train train = new Train();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		trainControllerBtn.setOnAction((ActionEvent e) ->
		{
			try {
				Train t = new Train();
				DTime dt = new DTime();
				dt.addTC(t.GetTrainController());
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		mboBtn.setOnAction((ActionEvent a) -> {
			MBOController mboCtrl = new MBOController(dTime.getTimer());
			try 				{ mboCtrl.start(new Stage());  }
			catch (Exception e) { e.printStackTrace(); }
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