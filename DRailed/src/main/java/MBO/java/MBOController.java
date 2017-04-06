package MBO.java;

import com.sun.javafx.scene.control.TableColumnComparatorBase;
import ctc.bean.Schedule;
import javafx.application.Application;
<<<<<<< HEAD
=======
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
>>>>>>> master
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
<<<<<<< HEAD
    private TableView<TrainRow> stationsTable = new TableView<TrainRow>();
=======
    private TableView<TrainSchedule> trainScheduleTable = new TableView<>();
>>>>>>> master
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
<<<<<<< HEAD
    public TrainSchedule getSchedule() { return trainSchedule; }

    // MUTATORS
    public void setTrainInfo(int id, String loc){

    }
=======
    public MBO getMBO() { return mbo; }
    public Scheduler getScheduler() { return scheduler; };
    public TrainSchedule getSchedule() { return scheduler.getSchedule(); }
>>>>>>> master

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
<<<<<<< HEAD

        stationsTable = (TableView<TrainRow>) primary.getScene().lookup("#schedule_table");
=======
        idTestInput = (TextField) primary.getScene().lookup("#id_test_input");
        speedTestInput = (TextField) primary.getScene().lookup("#speed_test_input");
        safeSpeedTestInput = (TextField) primary.getScene().lookup("#safespeed_test_input");
        authorityTestInput = (TextField) primary.getScene().lookup("#authority_test_input");
        varianceTestInput = (TextField) primary.getScene().lookup("#variance_test_input");
        locationTestInput = (TextField) primary.getScene().lookup("#location_test_input");

        trainScheduleTable= (TableView<TrainSchedule>) primary.getScene().lookup("#schedule_table");
>>>>>>> master
        trainScheduleButton = (Button) primary.getScene().lookup("#schedule_btn");

        workerScheduleButton = (Button) primary.getScene().lookup("#worker_schedule_btn");
        workerTable = (TableView) primary.getScene().lookup("#worker_schedule_table");

        passengerInput = (TextField) primary.getScene().lookup("#passengers");
        conductorInput = (TextField) primary.getScene().lookup("#conductors");
        submitButton = (Button) primary.getScene().lookup("#plan_btn");
        resultPane = (DialogPane) primary.getScene().lookup("#result_pane");

        murphyButton = (ToggleButton) primary.getScene().lookup("#mbo_murphy_toggle");

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
            trainInfo = new TrainInfo(schedule);

            setInfoColumns();
            primary.show();
        });

        submitButton.setOnAction((ActionEvent a) -> {
            Double passengerNo = Double.parseDouble(passengerInput.getText());
            Double conductorNo = Double.parseDouble(conductorInput.getText());

<<<<<<< HEAD
            if(passengerNo/conductorNo > 1000)
                resultPane.setContentText("FAIL");
            else
                resultPane.setContentText("SUCCESS");

=======
            resultPane.setContentText("SINGLE TRAIN DEPLOYMENT MODE");

            scheduler = new Scheduler(1);
            //scheduler.generateSchedule();

            mbo = new MBO(1);

            setInfoColumns();
            setTrainColumns();
>>>>>>> master
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

        infoTable.setItems(trainInfo.getRows());
        infoTable.getColumns().addAll(trainId, safeSpeed, speed, variance, authority, block, gps);
    }

    private void setTrainColumns() {
        TableColumn trainId = new TableColumn("Train ID");
<<<<<<< HEAD
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
=======
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

        trainScheduleTable.setItems(scheduler.getRows());
        trainScheduleTable.getColumns().addAll(trainId, station2, station9, station16, station22, station31, station39, station48, station57, station65, station73, station77, station88, station96, station105, station114, station123, station132, station141);
>>>>>>> master
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
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("/TrackModel/MBOUI.fxml"));          // Gets

        Screen mainScreen = Screen.getPrimary();
        Rectangle2D screenBounds = mainScreen.getVisualBounds();
=======
        Parent root = FXMLLoader.load(getClass().getResource("/MBO/MBOUI.fxml"));          // Gets
>>>>>>> master
        primary = primaryStage;
        primary.setTitle("MBO Interface");
        primary.setScene(new Scene(root));
        primary.show();
        this.getUIElements();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
