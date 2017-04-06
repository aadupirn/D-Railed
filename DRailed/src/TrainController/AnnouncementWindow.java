package TrainController;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by aadu on 2/14/17.
 */
public class AnnouncementWindow {
	private final Stage stage = new Stage();

	private TrainController trainController;

	//Create Scene
	private Scene scene;

	//Class strings
	private String windowTitle = "Announcement Window";

	//Class integers
	private int windowWidth = 300;
	private int windowHight = 300;
	private int inset = 25;
	private int colWidth = 75;

	public AnnouncementWindow(TrainController tc)
	{

		trainController = tc;

		stage.setTitle(windowTitle);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(inset, inset, inset, inset));
		grid.setVgap(inset/2);

		TextArea announcementField = new TextArea();
		Text announcementLabel = new Text();
		Button makeAnnouncement = new Button("OK");
		Button cancelAnnouncement = new Button("CANCEL");
		announcementLabel.setText("Enter Announcement");

		announcementLabel.setWrappingWidth(colWidth*2);
		announcementLabel.setTextAlignment(TextAlignment.CENTER);
		grid.add(announcementLabel, 0, 0, 2, 1);

		announcementField.setText("Example Text");
		announcementField.setMinWidth(colWidth*3);
		announcementField.setMinHeight(colWidth*2);
		announcementField.setWrapText(true);
		grid.add(announcementField, 0, 1, 2, 1);

		HBox hMakeAnnouncement = new HBox();
		hMakeAnnouncement.setAlignment(Pos.CENTER_RIGHT);
		hMakeAnnouncement.setMinWidth(colWidth);
		hMakeAnnouncement.getChildren().add(makeAnnouncement);


		HBox hCancelAnnouncement = new HBox();
		hCancelAnnouncement.setAlignment(Pos.CENTER);
		hCancelAnnouncement.setMinWidth(colWidth);
		hCancelAnnouncement.getChildren().add(cancelAnnouncement);

		grid.add(hMakeAnnouncement, 0, 2);
		grid.add(hCancelAnnouncement, 1, 2);

		Scene scene = new Scene(grid, windowWidth, windowHight);
		stage.setScene(scene);
		stage.show();

		//button handlers
		makeAnnouncement.setOnAction((ActionEvent e) ->
		{
			try
			{
				trainController.MakeAnnouncement(announcementField.getText());
				stage.close();
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		cancelAnnouncement.setOnAction((ActionEvent e) ->
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


}
