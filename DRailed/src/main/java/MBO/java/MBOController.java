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

    // Train Information Tab
    private TableView<TrainInfo> redInfoTable;
    private TableView<TrainInfo> greenInfoTable;
    private ToggleButton mboToggle;
    private RadioButton redRadio;
    private RadioButton greenRadio;
    private TextField idTestInput;
    private TextField speedTestInput;
    private TextField safeSpeedTestInput;
    private TextField varianceTestInput;
    private TextField authorityTestInput;
    private TextField locationTestInput;
    private Button trainInfoTestBtn;
    private Button murphyBtn;

    // Schedule Information Taab
    private TableView<TrainSchedule> redStationsTable;
    private TableView<TrainSchedule> greenStationsTable;
    private TextField idScheduleTestInput;
    private TextField locattionScheduleTestInput;
    private Button scheduleTestBtn;
    private TextField workersInput;
    private TextField thruputInput;
    private TextField startInput;
    private TextField endInput;
    private Button generateScheduleBtn;

    // Conductor Schedule Tab
    private Button workerScheduleButton;
    private TableView workerTable;

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
        // Train Information Tab
        redInfoTable = (TableView<TrainInfo>)  primary.getScene().lookup("#red_traininfo_tableview");
        greenInfoTable = (TableView<TrainInfo>)  primary.getScene().lookup("#green_traininfo_tableview");
        mboToggle = (ToggleButton) primary.getScene().lookup("#mbo_btn");
        redRadio = (RadioButton) primary.getScene().lookup("#red_radio");
        greenRadio = (RadioButton) primary.getScene().lookup("#green_radio");
        idTestInput = (TextField) primary.getScene().lookup("#id_traininfo_test");
        speedTestInput = (TextField) primary.getScene().lookup("#speed_traininfo_test");
        safeSpeedTestInput = (TextField) primary.getScene().lookup("#safespeed_traininfo_test");
        varianceTestInput = (TextField) primary.getScene().lookup("#variance_traininfo_test");
        authorityTestInput = (TextField) primary.getScene().lookup("#authority_traininfo_test");
        locationTestInput = (TextField) primary.getScene().lookup("#location_traininfo_test");
        trainInfoTestBtn = (Button) primary.getScene().lookup("#submit_traininfo_btn");
        murphyBtn = (Button) primary.getScene().lookup("#murphy_btn");

        // Schedule Information Tab
        redStationsTable = (TableView<TrainSchedule>)  primary.getScene().lookup("#red_stations_tableview");
        greenStationsTable = (TableView<TrainSchedule>)  primary.getScene().lookup("#green_stations_tableview");
        idScheduleTestInput = (TextField) primary.getScene().lookup("#id_schedule_test");
        locattionScheduleTestInput = (TextField) primary.getScene().lookup("#location_schedule_test");
        scheduleTestBtn = (Button) primary.getScene().lookup("#schedule_test_btn");
        workersInput = (TextField) primary.getScene().lookup("#worker_count_txt");
        thruputInput = (TextField) primary.getScene().lookup("#thruput_txt");
        startInput = (TextField) primary.getScene().lookup("#starttime_txt");
        endInput = (TextField) primary.getScene().lookup("#endtime_txt");
        generateScheduleBtn = (Button) primary.getScene().lookup("#generate_btn");


        // Conductor Schedule Button

        // Button Actions
        mboToggle.setOnAction((ActionEvent a) -> {

        });

        trainInfoTestBtn.setOnAction((ActionEvent a) -> {

        });

        murphyBtn.setOnAction((ActionEvent a) -> {

        });

        scheduleTestBtn.setOnAction((ActionEvent a) -> {

        });

        generateScheduleBtn.setOnAction((ActionEvent a) -> {

        });
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
