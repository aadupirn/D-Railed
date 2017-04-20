package MBO.java;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Timer;

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
    private TextField authorityTestInput;
    private TextField locationTestInput;
    private Button trainInfoTestBtn;

    // Schedule Information Tab
    private TableView<RedTrainSchedule> redStationsTable;
    private TableView<GreenTrainSchedule> greenStationsTable;
    private Button scheduleTestBtn;
    private RadioButton redScheduleRadio;
    private RadioButton greenScheduleRadio;
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
        authorityTestInput = (TextField) primary.getScene().lookup("#authority_traininfo_test");
        locationTestInput = (TextField) primary.getScene().lookup("#location_traininfo_test");
        trainInfoTestBtn = (Button) primary.getScene().lookup("#submit_traininfo_btn");

        // Schedule Information Tab
        redStationsTable = (TableView<RedTrainSchedule>)  primary.getScene().lookup("#red_stations_tableview");
        greenStationsTable = (TableView<GreenTrainSchedule>)  primary.getScene().lookup("#green_stations_tableview");
        scheduleTestBtn = (Button) primary.getScene().lookup("#schedule_test_btn");
        redScheduleRadio = (RadioButton) primary.getScene().lookup("#red_sch_radio");
        greenScheduleRadio = (RadioButton) primary.getScene().lookup("#green_sch_radio");
        thruputInput = (TextField) primary.getScene().lookup("#thruput_txt");
        startInput = (TextField) primary.getScene().lookup("#starttime_txt");
        endInput = (TextField) primary.getScene().lookup("#endtime_txt");
        generateScheduleBtn = (Button) primary.getScene().lookup("#generate_btn");
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

    private void setScheudleColumns() {
        // Columns for Red Line
        TableColumn redId = new TableColumn("ID");
        TableColumn herronStation = new TableColumn("Herron Ave");
        TableColumn swissvilleStaion = new TableColumn("Swissville");
        TableColumn pennStation = new TableColumn("Penn Station");
        TableColumn steelPlazaStation = new TableColumn("Steel Plaza");
        TableColumn firstAveStation = new TableColumn("First Ave");
        TableColumn southHillsStation = new TableColumn("South Hills");
        TableColumn shadysideStation = new TableColumn("Shadyside");

        // Columns for Green Line
        TableColumn greenId = new TableColumn("ID");
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

        // Formatting
        redId.setStyle( "-fx-alignment: CENTER;");
        redId.setPrefWidth(50);
        herronStation.setStyle( "-fx-alignment: CENTER;");
        herronStation.setPrefWidth(125);
        swissvilleStaion.setStyle( "-fx-alignment: CENTER;");
        swissvilleStaion.setPrefWidth(125);
        pennStation.setStyle( "-fx-alignment: CENTER;");
        pennStation.setPrefWidth(125);
        steelPlazaStation.setStyle( "-fx-alignment: CENTER;");
        steelPlazaStation.setPrefWidth(125);
        firstAveStation.setStyle( "-fx-alignment: CENTER;");
        firstAveStation.setPrefWidth(125);
        southHillsStation.setStyle( "-fx-alignment: CENTER;");
        southHillsStation.setPrefWidth(125);
        shadysideStation.setStyle( "-fx-alignment: CENTER;");
        shadysideStation.setPrefWidth(125);

        greenId.setStyle( "-fx-alignment: CENTER;");
        greenId.setPrefWidth(50);
        glenBuryStationA.setStyle( "-fx-alignment: CENTER;");
        glenBuryStationA.setPrefWidth(125);
        dormontStationA.setStyle( "-fx-alignment: CENTER;");
        dormontStationA.setPrefWidth(125);
        mtLebanonStation.setStyle( "-fx-alignment: CENTER;");
        mtLebanonStation.setPrefWidth(125);
        poplarStation.setStyle( "-fx-alignment: CENTER;");
        poplarStation.setPrefWidth(125);
        castleShannon.setStyle( "-fx-alignment: CENTER;");
        castleShannon.setPrefWidth(125);
        dormonStationB.setStyle( "-fx-alignment: CENTER;");
        dormonStationB.setPrefWidth(125);
        glenburyStationB.setStyle( "-fx-alignment: CENTER;");
        glenburyStationB.setPrefWidth(125);
        overbrookStationA.setStyle( "-fx-alignment: CENTER;");
        overbrookStationA.setPrefWidth(125);
        inglewoodStationA.setStyle( "-fx-alignment: CENTER;");
        inglewoodStationA.setPrefWidth(125);
        centralStationA.setStyle( "-fx-alignment: CENTER;");
        centralStationA.setPrefWidth(125);
        pioneerStation.setStyle( "-fx-alignment: CENTER;");
        pioneerStation.setPrefWidth(125);
        edgebrookStation.setStyle( "-fx-alignment: CENTER;");
        edgebrookStation.setPrefWidth(125);
        universityStation.setStyle( "-fx-alignment: CENTER;");
        universityStation.setPrefWidth(125);
        whitedStation.setStyle( "-fx-alignment: CENTER;");
        whitedStation.setPrefWidth(125);
        southBankStation.setStyle( "-fx-alignment: CENTER;");
        southBankStation.setPrefWidth(125);
        centralStationB.setStyle( "-fx-alignment: CENTER;");
        centralStationB.setPrefWidth(125);
        inglewoodStationB.setStyle( "-fx-alignment: CENTER;");
        inglewoodStationB.setPrefWidth(125);
        overbrookStationB.setStyle( "-fx-alignment: CENTER;");
        overbrookStationB.setPrefWidth(125);


        // Mappings
        redId.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, Integer>("id"));
        herronStation.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, String>("herron"));
        swissvilleStaion.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, String>("swissville"));
        pennStation.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, String>("pennStation"));
        steelPlazaStation.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, String>("steelPlaza"));
        firstAveStation.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, String>("firstAve"));
        southHillsStation.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, String>("southHills"));
        shadysideStation.setCellValueFactory(new PropertyValueFactory<RedTrainSchedule, String>("shadyside"));

        greenId.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, Integer>("id"));
        glenBuryStationA.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("glenburyA"));
        dormontStationA.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("dormontA"));
        mtLebanonStation.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("mtLebanon"));
        poplarStation.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("poplar"));
        castleShannon.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("castleShannon"));
        dormonStationB.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("dormontB"));
        glenburyStationB.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("glenburyB"));
        overbrookStationA.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("overbrookA"));
        inglewoodStationA.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("inglewoodA"));
        centralStationA.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("centralA"));
        pioneerStation.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("pioneer"));
        edgebrookStation.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("edgebrook"));
        universityStation.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("university"));
        whitedStation.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("whited"));
        southBankStation.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("southBank"));
        centralStationB.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("centralB"));
        inglewoodStationB.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("inglewoodB"));
        overbrookStationB.setCellValueFactory(new PropertyValueFactory<GreenTrainSchedule, String>("overbrookB"));

        redStationsTable.setItems(scheduler.getRedTrainRows());
        redStationsTable.getColumns().addAll(redId, herronStation, swissvilleStaion, pennStation, steelPlazaStation,
                firstAveStation, southHillsStation, shadysideStation);

        greenStationsTable.setItems(scheduler.getGreenTrainRows());
        greenStationsTable.getColumns().addAll(greenId, glenBuryStationA, dormontStationA, mtLebanonStation, poplarStation,
                castleShannon, dormonStationB, glenburyStationB, overbrookStationA,
                inglewoodStationA, centralStationA, pioneerStation, edgebrookStation,
                universityStation, whitedStation, southBankStation, centralStationB,
                inglewoodStationB, overbrookStationB);
    }

    private void setButtonActions() {
        mboRadio.setOnAction((ActionEvent a) -> { mbo.activateMBO(); });
        fbRadio.setOnAction((ActionEvent a) -> { mbo.deactivateMBO(); });

        trainInfoTestBtn.setOnAction((ActionEvent a) -> {
            String line = ((RadioButton)redRadio.getToggleGroup().getSelectedToggle()).getText();
            int id = Integer.parseInt(idTestInput.getText());
            double speed = Double.parseDouble(speedTestInput.getText());
            int authority = Integer.parseInt(authorityTestInput.getText());
            String location = locationTestInput.getText();

            mbo.addTrain(id, line, speed, 0, 0, authority, location);

            ((RadioButton)redRadio.getToggleGroup().getSelectedToggle()).setSelected(false);
            idTestInput.clear();
            speedTestInput.clear();
            authorityTestInput.clear();
            locationTestInput.clear();
        });


        generateScheduleBtn.setOnAction((ActionEvent a) -> {
            if(thruputInput.getText() == null || startInput.getText() == null || endInput.getText() == null) return; // @TODO ERROR LOG
            int thruput = Integer.parseInt(thruputInput.getText());
            LocalTime start = LocalTime.of(Time.valueOf(startInput.getText()).getHours(), Time.valueOf(startInput.getText()).getMinutes(), Time.valueOf(startInput.getText()).getSeconds());
            LocalTime end = LocalTime.of(Time.valueOf(endInput.getText()).getHours(), Time.valueOf(startInput.getText()).getMinutes(), Time.valueOf(startInput.getText()).getSeconds());
            String line = ((RadioButton)redScheduleRadio.getToggleGroup().getSelectedToggle()).getText();

            scheduler.generateSchedule(line, thruput, start, end);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/MBO/MBOUI.fxml"));
        primary = primaryStage;
        primary.setTitle("MBO Interface");
        primary.setScene(new Scene(root));
        primary.show();
        scheduler = new Scheduler();
        mbo = new MBO();
        this.getUIElements();
        this.setButtonActions();
        this.setMboColumns();
        this.setScheudleColumns();
    }

    public static void main(String[] args) {
        launch(args);
    }
}