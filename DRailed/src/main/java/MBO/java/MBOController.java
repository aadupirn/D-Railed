package MBO.java;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Timer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MBOController extends Application {

    public MBOController(Timer timer){
        this.timer = timer;
    }

    private Scheduler scheduler;
    private MBO mbo;
    private Timer timer;

    private Stage primary;

    // Train Information Tab
    private TableView<TrainInfo> redInfoTable;
    private TableView<TrainInfo> greenInfoTable;
    private RadioButton mboRadio;
    private RadioButton fbRadio;
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

    // Schedule Information Tab
    private TableView<RedTrainSchedule> redStationsTable;
    private TableView<GreenTrainSchedule> greenStationsTable;
    private TextField idScheduleTestInput;
    private TextField locattionScheduleTestInput;
    private Button scheduleTestBtn;
    private RadioButton redScheduleRadio;
    private RadioButton greenScheduleRadio;
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
        mboRadio = (RadioButton) primary.getScene().lookup("#mbo_radio");
        fbRadio = (RadioButton) primary.getScene().lookup("#fb_radio");
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
        redStationsTable = (TableView<RedTrainSchedule>)  primary.getScene().lookup("#red_stations_tableview");
        greenStationsTable = (TableView<GreenTrainSchedule>)  primary.getScene().lookup("#green_stations_tableview");
        idScheduleTestInput = (TextField) primary.getScene().lookup("#id_schedule_test");
        locattionScheduleTestInput = (TextField) primary.getScene().lookup("#location_schedule_test");
        scheduleTestBtn = (Button) primary.getScene().lookup("#schedule_test_btn");
        redScheduleRadio = (RadioButton) primary.getScene().lookup("#red_sch_radio");
        greenScheduleRadio = (RadioButton) primary.getScene().lookup("#green_sch_radio");
        workersInput = (TextField) primary.getScene().lookup("#worker_count_txt");
        thruputInput = (TextField) primary.getScene().lookup("#thruput_txt");
        startInput = (TextField) primary.getScene().lookup("#starttime_txt");
        endInput = (TextField) primary.getScene().lookup("#endtime_txt");
        generateScheduleBtn = (Button) primary.getScene().lookup("#generate_btn");


        // Conductor Schedule Button

        // Button Actions

    }

    private void setMboColumns() {
        // Columns for Red Line
        TableColumn redId = new TableColumn("ID");
        TableColumn redSpeed = new TableColumn("Speed");
        TableColumn redSafeSpeed = new TableColumn("Safe Speed");
        TableColumn redVariance = new TableColumn("Variance");
        TableColumn redAuthority = new TableColumn("Authority");
        TableColumn redLocation = new TableColumn("Location");


        // Columns for Green Line
        TableColumn greenId = new TableColumn("ID");
        TableColumn greenSpeed = new TableColumn("Speed");
        TableColumn greenSafeSpeed = new TableColumn("Safe Speed");
        TableColumn greenVariance = new TableColumn("Variance");
        TableColumn greenAuthority = new TableColumn("Authority");
        TableColumn greenLocation = new TableColumn("Location");

        // Mappings for the appropriate column updates tot he specific values in the TrainInfo class
        redId.setCellValueFactory(new PropertyValueFactory<TrainInfo, Integer>("id"));
        redSpeed.setCellValueFactory(new PropertyValueFactory<TrainInfo, Double>("speed"));
        redSafeSpeed.setCellValueFactory(new PropertyValueFactory<TrainInfo, Double>("safeSpeed"));
        redVariance.setCellValueFactory(new PropertyValueFactory<TrainInfo, Double>("variance"));
        redAuthority.setCellValueFactory(new PropertyValueFactory<TrainInfo, Integer>("authority"));
        redLocation.setCellValueFactory(new PropertyValueFactory<TrainInfo, String>("location"));

        greenId.setCellValueFactory(new PropertyValueFactory<TrainInfo, Integer>("id"));
        greenSpeed.setCellValueFactory(new PropertyValueFactory<TrainInfo, Double>("speed"));
        greenSafeSpeed.setCellValueFactory(new PropertyValueFactory<TrainInfo, Double>("safeSpeed"));
        greenVariance.setCellValueFactory(new PropertyValueFactory<TrainInfo, Double>("variance"));
        greenAuthority.setCellValueFactory(new PropertyValueFactory<TrainInfo, Integer>("authority"));
        greenLocation.setCellValueFactory(new PropertyValueFactory<TrainInfo, String>("location"));

        redInfoTable.setItems(mbo.getRedRows());
        redInfoTable.getColumns().addAll(redId, redSpeed, redSafeSpeed, redVariance, redAuthority, redLocation);

        greenInfoTable.setItems(mbo.getGreenRows());
        greenInfoTable.getColumns().addAll(greenId, greenSpeed, greenSafeSpeed, greenVariance, greenAuthority, greenLocation);
    }

    private void setButtonActions() {
        mboRadio.setOnAction((ActionEvent a) -> { mbo.activateMBO(); });
        fbRadio.setOnAction((ActionEvent a) -> { mbo.deactivateMBO(); });

        trainInfoTestBtn.setOnAction((ActionEvent a) -> {
            String line = ((RadioButton)redRadio.getToggleGroup().getSelectedToggle()).getText();
            int id = Integer.parseInt(idTestInput.getText());
            double speed = Double.parseDouble(speedTestInput.getText());
            double safeSpeed = Double.parseDouble(safeSpeedTestInput.getText());
            double variance = Double.parseDouble(varianceTestInput.getText());
            int authority = Integer.parseInt(authorityTestInput.getText());
            String location = locationTestInput.getText();

            mbo.addTrain(id, line, speed, safeSpeed, variance, authority, location);

            ((RadioButton)redRadio.getToggleGroup().getSelectedToggle()).setSelected(false);
            idTestInput.clear();
            speedTestInput.clear();
            safeSpeedTestInput.clear();
            varianceTestInput.clear();
            authorityTestInput.clear();
            locationTestInput.clear();
        });

        murphyBtn.setOnAction((ActionEvent a) -> { mbo.toggleMurphy(); });

        scheduleTestBtn.setOnAction((ActionEvent a) -> {
            if(idScheduleTestInput.getText() == null) return; //@TODO ERROR LOG

            //scheduler.updateSchedule(Integer.parseInt(idScheduleTestInput.getText()));
        });

        generateScheduleBtn.setOnAction((ActionEvent a) -> {
            if(thruputInput.getText() == null || startInput.getText() == null || endInput.getText() == null) return; // @TODO ERROR LOG
            int thruput = Integer.parseInt(thruputInput.getText());
            LocalTime start = LocalTime.of(Time.valueOf(startInput.getText()).getHours(), Time.valueOf(startInput.getText()).getMinutes(), Time.valueOf(startInput.getText()).getSeconds());
            LocalTime end = LocalTime.of(Time.valueOf(startInput.getText()).getHours(), Time.valueOf(startInput.getText()).getMinutes(), Time.valueOf(startInput.getText()).getSeconds());
            String line = ((RadioButton)redScheduleRadio.getToggleGroup().getSelectedToggle()).getText();

            scheduler.generateSchedule(line, thruput, start, end);
        });
    }

    private void setScheudleColumns() {
        // Columns for Red Line
        TableColumn herronStation = new TableColumn("Herron Ave");
        TableColumn swissvilleStaion = new TableColumn("Swissville");
        TableColumn pennStation = new TableColumn("Penn Station");
        TableColumn steelPlazaStation = new TableColumn("Steel Plaza");
        TableColumn firstAveStation = new TableColumn("First Ave");
        TableColumn southHillsStation = new TableColumn("South Hills");
        TableColumn shadysideStation = new TableColumn("Shadyside");

        // Columns for Green Line
        TableColumn glenBuryStationA = new TableColumn("Glenbury");
        TableColumn dormontStationA = new TableColumn("Dormont");
        TableColumn mtLebanonStation = new TableColumn("Mt. Lebanon");
        TableColumn poplarStation= new TableColumn("Poplar");
        TableColumn castleShannon = new TableColumn("Castle");
        TableColumn dormonStationB = new TableColumn("Dormont");
        TableColumn glenburyStationB = new TableColumn("Glenbury");
        TableColumn overbrookStationA = new TableColumn("Overbrook");
        TableColumn inglewoodStationA = new TableColumn("Inglewood");
        TableColumn centralStationA = new TableColumn("Central");
        TableColumn pioneerStation = new TableColumn("Pioneer");
        TableColumn edgebrookStation = new TableColumn("Edgebrook");
        TableColumn universityStation = new TableColumn("Univ. Pitt");
        TableColumn whitedStation = new TableColumn("Whited");
        TableColumn southBankStation = new TableColumn("South Bank");
        TableColumn centralStationB = new TableColumn("Central");
        TableColumn inglewoodStationB = new TableColumn("Inglewood");
        TableColumn overbrookStationB = new TableColumn("Overbrook");

        redStationsTable.setItems(scheduler.getRedTrainRows());
        redStationsTable.getColumns().addAll(herronStation, swissvilleStaion, pennStation, steelPlazaStation,
                firstAveStation, southHillsStation, shadysideStation);

        greenStationsTable.setItems(scheduler.getGreenTrainRows());
        greenStationsTable.getColumns().addAll(glenBuryStationA, dormontStationA, mtLebanonStation, poplarStation,
                castleShannon, dormonStationB, glenburyStationB, overbrookStationA,
                inglewoodStationA, centralStationA, pioneerStation, edgebrookStation,
                universityStation, whitedStation, southBankStation, centralStationB,
                inglewoodStationB, overbrookStationB);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/MBO/MBOUI.fxml"));
        primary = primaryStage;
        primary.setTitle("MBO Interface");
        primary.setScene(new Scene(root));
        primary.show();
        scheduler = new Scheduler(timer);
        mbo = new MBO(1);
        this.getUIElements();
        this.setButtonActions();
        this.setMboColumns();
        this.setScheudleColumns();
    }

    public static void main(String[] args) {
        launch(args);
    }
}