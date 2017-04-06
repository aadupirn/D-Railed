package MBO.java;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MBOController extends Application {
    private Scheduler scheduler;
    private MBO mbo;

    private Stage primary;


    // TRAIN INFO TAB
    private Button testInfoButton;
    private ToggleButton mboToggle;
    private TableView<TrainInfo> infoTable = new TableView<>();
    private TextField idTestInput;
    private TextField speedTestInput;
    private TextField safeSpeedTestInput;
    private TextField authorityTestInput;
    private TextField varianceTestInput;
    private TextField locationTestInput;

    // TRAIN SCHEDULE DISPLAY TAB
    private TableView<TrainInfo> stationsTable = new TableView<>();
    private Button trainScheduleButton;

    // WORKER SCHEDULE DISPLAY TAB
    private Button workerScheduleButton;
    private TableView workerTable;

    // PLANNER TAB
    private TextField passengerInput;
    private TextField conductorInput;
    private Button submitButton;
    private DialogPane resultPane;

    // MURPHY TAB
    private ToggleButton murphyButton;

    // ACCESSORS
    //public Stack<Time> getSchedule() { return scheduler.getSchedule(); }
    public MBO getMBO() { return mbo; }

    /*
    * Method in charge of setting up gettting the elements associated with the portions
    * of the UI that have actions associated with them.
    *-----
    * No inputs
    *-----
    * No returns
    */
    private void getUIElements(){

        mboToggle = (ToggleButton) primary.getScene().lookup("#mbo_toggle");
        infoTable = (TableView<TrainInfo>) primary.getScene().lookup("#train_info_table");
        testInfoButton = (Button) primary.getScene().lookup("#test_info_btn");
        idTestInput = (TextField) primary.getScene().lookup("#id_test_input");
        speedTestInput = (TextField) primary.getScene().lookup("#speed_test_input");
        safeSpeedTestInput = (TextField) primary.getScene().lookup("#safespeed_test_input");
        authorityTestInput = (TextField) primary.getScene().lookup("#authority_test_input");
        varianceTestInput = (TextField) primary.getScene().lookup("#variance_test_input");
        locationTestInput = (TextField) primary.getScene().lookup("#location_test_input");

        stationsTable = (TableView<TrainInfo>) primary.getScene().lookup("#schedule_table");
        trainScheduleButton = (Button) primary.getScene().lookup("#schedule_btn");

        workerScheduleButton = (Button) primary.getScene().lookup("#worker_schedule_btn");
        workerTable = (TableView) primary.getScene().lookup("#worker_schedule_table");

        passengerInput = (TextField) primary.getScene().lookup("#passengers");
        conductorInput = (TextField) primary.getScene().lookup("#conductors");
        submitButton = (Button) primary.getScene().lookup("#plan_btn");
        resultPane = (DialogPane) primary.getScene().lookup("#result_pane");

        murphyButton = (ToggleButton) primary.getScene().lookup("#mbo_murphy_toggle");

        stationsTable.setEditable(true);

        trainScheduleButton.setOnAction((ActionEvent a) -> {

        });

        workerScheduleButton.setOnAction((ActionEvent a) -> {

        });

        testInfoButton.setOnAction((ActionEvent a) -> {
            int id = Integer.parseInt(idTestInput.getText());
            double speed = Double.parseDouble(speedTestInput.getText());
            double safeSpeed = Double.parseDouble(safeSpeedTestInput.getText());
            int authority = Integer.parseInt(authorityTestInput.getText());
            double variance = Double.parseDouble(varianceTestInput.getText());
            String location = locationTestInput.getText();
            mbo.setSpeed(id, speed);
            mbo.setSafeSpeed(id, safeSpeed);
            mbo.setAuthority(id, authority);
            mbo.setVariance(id, variance);
            mbo.setLocation(id, location);
        });

        submitButton.setOnAction((ActionEvent a) -> {
            Double passengerNo = Double.parseDouble(passengerInput.getText());
            Double conductorNo = Double.parseDouble(conductorInput.getText());

            resultPane.setContentText("SINGLE TRAIN DEPLOYMENT MODE");

            scheduler = new Scheduler(1);
            //scheduler.generateSchedule();

            mbo = new MBO(1);

            setInfoColumns();

        });
    }

    private void setInfoColumns() {
        TableColumn trainId = new TableColumn("Train ID");
        trainId.setCellValueFactory(new PropertyValueFactory<TrainInfo, SimpleIntegerProperty>("id"));

        TableColumn speed = new TableColumn("Speed");
        speed.setCellValueFactory(new PropertyValueFactory<TrainInfo, SimpleDoubleProperty>("speed"));

        TableColumn safeSpeed = new TableColumn("Safe Speed");
        safeSpeed.setCellValueFactory(new PropertyValueFactory<TrainInfo, SimpleDoubleProperty>("safeSpeed"));

        TableColumn variance = new TableColumn("Variance");
        variance.setCellValueFactory(new PropertyValueFactory<TrainInfo, SimpleDoubleProperty>("variance"));

        TableColumn authority = new TableColumn("Authority");
        authority.setCellValueFactory(new PropertyValueFactory<TrainInfo, SimpleIntegerProperty>("authority"));

        TableColumn gps = new TableColumn("GPS");
        gps.setCellValueFactory(new PropertyValueFactory<TrainInfo, String>("location"));

        infoTable.setItems(mbo.getRows());
        infoTable.getColumns().addAll(trainId, speed, safeSpeed, variance, authority, gps);
    }

    private void setTrainColumns() {

    }

    private void setWorkColumns() {

    }

    private void updateTrainInfo() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/MBO/MBOUI.fxml"));          // Gets

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
