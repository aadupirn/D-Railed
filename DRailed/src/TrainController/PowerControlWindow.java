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
import javafx.stage.Stage;

/**
 * Created by aadu on 2/14/17.
 */
public class PowerControlWindow {
	private final Stage stage = new Stage();

	private TrainController trainController;

	//Create Scene
	private Scene scene;

	//Class strings
	private String windowTitle = "Power Control";

	//Class integers
	private int windowWidth = 300;
	private int windowHight = 300;
	private int inset = 25;
	private int colWidth = 75;

	public PowerControlWindow(TrainController tc)
	{
		trainController = tc;

		stage.setTitle(windowTitle);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(inset, inset, inset, inset));
		grid.setVgap(inset);

		TextField kpField = new TextField();
		TextField kiField = new TextField();
		Text kpLabel = new Text("KP: ");
		Text kiLabel = new Text("KI: ");
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("CANCEL");


		grid.add(kpLabel, 0, 0);

		kpField.setMinWidth(colWidth);
		kpField.setText(String.valueOf(trainController.getKP()));
		grid.add(kpField, 1, 0);

		grid.add(kiLabel, 0, 1);

		kiField.setMinWidth(colWidth);
		kiField.setText(String.valueOf(trainController.getKI()));
		grid.add(kiField, 1, 1);

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

		Scene scene = new Scene(grid, windowWidth, windowHight);
		stage.setScene(scene);
		stage.show();

		okBtn.setOnAction((ActionEvent e) ->
		{
			try
			{
				trainController.setPowerVars(Double.valueOf(kpField.getText()), Double.valueOf(kiField.getText()));
				stage.close();
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

		//trainController.MakeAnnouncement("Test Announcement");
	}

}
