package MBO.java;

import javafx.application.Application;
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
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class MBOController extends Application {
    private Stage primary;
    private TrainSchedule trainSchedule;
    private WorkerSchedule workSchedule;
    private TrainInfo trainInfo;


    // TRAIN INFO TAB
    private Button testInfoButton;
    private ToggleButton mboToggle;
    private TableView<InfoRow> infoTable = new TableView<InfoRow>();

    // TRAIN SCHEDULE DISPLAY TAB
    private TableView<TrainRow> stationsTable = new TableView<TrainRow>();
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
    public TrainSchedule getSchedule() { return trainSchedule; }

    // MUTATORS
    public void setTrainInfo(int id, String loc){

    }

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
        infoTable = (TableView<InfoRow>) primary.getScene().lookup("#train_info_table");
        testInfoButton = (Button) primary.getScene().lookup("#test_info_btn");

        stationsTable = (TableView<TrainRow>) primary.getScene().lookup("#schedule_table");
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
            FileChooser fc = new FileChooser();
            fc.setTitle("Pick Train Schedule");
            File schedule = fc.showOpenDialog(primary);
            trainSchedule = new TrainSchedule(schedule);

            setTrainColumns();
            primary.show();
        });

        workerScheduleButton.setOnAction((ActionEvent a) -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Pick Worker Schedule");
            File schedule = fc.showOpenDialog(primary);
            workSchedule = new WorkerSchedule(schedule);

            setWorkColumns();
            primary.show();
        });

        testInfoButton.setOnAction((ActionEvent a) -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Pick Worker Schedule");
            File schedule = fc.showOpenDialog(primary);
            //trainInfo = new TrainInfo(schedule);

            setInfoColumns();
            primary.show();
        });

        submitButton.setOnAction((ActionEvent a) -> {
            Double passengerNo = Double.parseDouble(passengerInput.getText());
            Double conductorNo = Double.parseDouble(conductorInput.getText());

            if(passengerNo/conductorNo > 1000)
                resultPane.setContentText("FAIL");
            else
                resultPane.setContentText("SUCCESS");

        });
    }

    private void setInfoColumns() {
        TableColumn trainId = new TableColumn("Train ID");
        trainId.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("trainId"));

        TableColumn safeSpeed = new TableColumn("Safe Speed");
        safeSpeed.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("safeSpeed"));

        TableColumn speed = new TableColumn("Speed");
        speed.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("speed"));

        TableColumn variance = new TableColumn("Variance");
        variance.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("variance"));

        TableColumn authority = new TableColumn("Authority");
        authority.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("authority"));

        TableColumn block = new TableColumn("Block");
        block.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("block"));

        TableColumn gps = new TableColumn("GPS");
        gps.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("gps"));

        //infoTable.setItems(trainInfo.getRows());
        infoTable.getColumns().addAll(trainId, safeSpeed, speed, variance, authority, block, gps);
    }

    private void setTrainColumns() {
        TableColumn trainId = new TableColumn("Train ID");
        trainId.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("trainId"));

        TableColumn station1 = new TableColumn("Station 1");
        station1.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station1"));

        TableColumn station2 = new TableColumn("Station 2");
        station2.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station2"));

        TableColumn station3 = new TableColumn("Station 3");
        station3.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station3"));

        TableColumn station4 = new TableColumn("Station 4");
        station4.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station4"));

        TableColumn station5 = new TableColumn("Station 5");
        station5.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station5"));

        TableColumn station6 = new TableColumn("Station 6");
        station6.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station6"));

        TableColumn station7 = new TableColumn("Station 7");
        station7.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station7"));

        TableColumn station8 = new TableColumn("Station 8");
        station8.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("station8"));

        stationsTable.setItems(trainSchedule.getRows());
        stationsTable.getColumns().addAll(trainId, station1, station2, station3, station4, station5, station6, station7, station8);
    }

    private void setWorkColumns() {
        TableColumn workerId = new TableColumn("Worker Id");
        workerId.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("workerId"));

        TableColumn name = new TableColumn("Worker Name");
        name.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("name"));

        TableColumn textSchedule= new TableColumn("Schedule");
        textSchedule.setCellValueFactory(new PropertyValueFactory<TrainRow, String>("schedule"));

        workerTable.setItems(workSchedule.getRows());
        workerTable.getColumns().addAll(workerId, name, textSchedule);
    }

    private void updateTrainInfo() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/TrackModel/MBOUI.fxml"));          // Gets

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
