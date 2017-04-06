package MBO.java;

import com.sun.javafx.scene.control.TableColumnComparatorBase;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private TableView<TrainSchedule> trainScheduleTable = new TableView<>();
    private Button updateScheduleButton;
    private TextField scheduleIdTestInput;

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
    public MBO getMBO() { return mbo; }
    public Scheduler getScheduler() { return scheduler; };
    public TrainSchedule getSchedule() { return scheduler.getSchedule(); }

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

        trainScheduleTable = (TableView<TrainSchedule>) primary.getScene().lookup("#schedule_table");
        updateScheduleButton = (Button) primary.getScene().lookup("#schedule_btn");
        scheduleIdTestInput = (TextField) primary.getScene().lookup("#update_id_test_input");

        workerScheduleButton = (Button) primary.getScene().lookup("#worker_schedule_btn");
        workerTable = (TableView) primary.getScene().lookup("#worker_schedule_table");

        passengerInput = (TextField) primary.getScene().lookup("#passengers");
        conductorInput = (TextField) primary.getScene().lookup("#conductors");
        submitButton = (Button) primary.getScene().lookup("#plan_btn");
        resultPane = (DialogPane) primary.getScene().lookup("#result_pane");

        murphyButton = (ToggleButton) primary.getScene().lookup("#mbo_murphy_toggle");

        updateScheduleButton.setOnAction((ActionEvent a) -> {

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
            mbo.setSafeSpeed(id, safeSpeed);
            mbo.setSpeed(id, speed);
            mbo.setAuthority(id, authority);
            //mbo.setVariance(id, variance);
            mbo.setLocation(id, location);
        });

        submitButton.setOnAction((ActionEvent a) -> {
            resultPane.setContentText("SINGLE TRAIN DEPLOYMENT MODE");
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
        TableColumn trainId = new TableColumn("Train ID");
        trainId.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleIntegerProperty>("id"));

        TableColumn station2 = new TableColumn("Pioneer");
        station2.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station2"));

        TableColumn station9 = new TableColumn("Edgebrook");
        station9.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station9"));

        TableColumn station16 = new TableColumn("Station X");
        station16.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station16"));

        TableColumn station22 = new TableColumn("Whited");
        station22.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station22"));

        TableColumn station31 = new TableColumn("South Bank");
        station31.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station31"));

        TableColumn station39 = new TableColumn("Central");
        station39.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station39"));

        TableColumn station48 = new TableColumn("Inglewood");
        station48.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station48"));

        TableColumn station57 = new TableColumn("Overbrook");
        station57.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station57"));

        TableColumn station65 = new TableColumn("Glenbury");
        station65.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station65"));

        TableColumn station73 = new TableColumn("Dormont");
        station73.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station73"));

        TableColumn station77 = new TableColumn("Mt. Lebanon");
        station77.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station77"));

        TableColumn station88 = new TableColumn("Poplar");
        station88.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station88"));

        TableColumn station96 = new TableColumn("Castle Shannon");
        station96.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station96"));

        TableColumn station105 = new TableColumn("Dormont");
        station105.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station105"));

        TableColumn station114 = new TableColumn("Glenbury");
        station114.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station114"));

        TableColumn station123 = new TableColumn("Overbrook");
        station123.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station123"));

        TableColumn station132 = new TableColumn("Inglewood");
        station132.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station132"));

        TableColumn station141 = new TableColumn("Central");
        station141.setCellValueFactory(new PropertyValueFactory<TrainSchedule, SimpleStringProperty>("station141"));

        trainScheduleTable.setItems(scheduler.getTrainRows());
        trainScheduleTable.getColumns().addAll(trainId, station2, station9, station16, station22, station31, station39, station48, station57, station65, station73, station77, station88, station96, station105, station114, station123, station132, station141);
    }

    private void setWorkColumns() {
        TableColumn workerId = new TableColumn("Worker ID");
        workerId.setCellValueFactory(new PropertyValueFactory<WorkerSchedule, SimpleIntegerProperty>("id"));

        TableColumn schedule = new TableColumn("Schedule");
        schedule.setCellValueFactory(new PropertyValueFactory<WorkerSchedule, SimpleStringProperty>("schedule"));

        workerTable.setItems(scheduler.getWorkerRows());
        workerTable.getColumns().addAll(workerId, schedule);
    }

    private void updateTrainInfo() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/MBO/MBOUI.fxml"));
        primary = primaryStage;
        primary.setTitle("MBO Interface");
        primary.setScene(new Scene(root));
        primary.show();
        this.getUIElements();
        scheduler = new Scheduler(1);
        mbo = new MBO(1);
        setInfoColumns();
        setTrainColumns();
        setWorkColumns();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
