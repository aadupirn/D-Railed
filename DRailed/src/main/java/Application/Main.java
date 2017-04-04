package Application;

import TrackModel.UI.TrackModelGUI;
import TrainController.TrainController;
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
import DTime.DTime;
import TrainModel.TrainModel;
import TrainModel.Train;
import MBO.java.MBOController;
import ctc.MainFrame;

import java.io.IOException;

public class Main extends Application {
    //Class strings
    private String applicationTitle = "D-Railed";

    //Class integers
    private int windowWidth = 300;
    private int windowHight = 300;
    private int inset = 25;
    private int colWidth = 75;

    @Override
    public void start(Stage primaryStage) throws Exception{
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

		Scene scene = new Scene(grid, windowWidth, windowHight);
		primaryStage.setScene(scene);
		primaryStage.show();

        //handle button press
		ctcBtn.setOnAction((ActionEvent e) ->
		{
			try{
				MainFrame ctc = new MainFrame();
				ctc.setVisible(true);
				ctc.deleteFile();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		trackControllerBtn.setOnAction((ActionEvent e) ->
		{
			try {
				TrackController trackController = new TrackController();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

        trackModelBtn.setOnAction((ActionEvent e) ->
        {
            try {
                TrackModelGUI trackModel = new TrackModelGUI();
            } catch (IOException e1) {
                e1.printStackTrace();
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
				DTime dt = new DTime(t.GetTrainController());
				dt.Run();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
		});

		mboBtn.setOnAction((ActionEvent e) ->
		{
			MBOController MBO = new MBOController();
			try {
				MBO.start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
    }






    public static void main(String[] args) {
        launch(args);
    }
}
