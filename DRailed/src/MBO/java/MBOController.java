package MBO.java;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class MBOController extends Application {
    private Stage primary;
    private TrainSchedule schedule;


    // TRAIN INFO TAB
    private Button trainScheduleButton;
    private ToggleButton mboToggle;
    private TableView trainTable;

    // TRAIN SCHEDULE DISPLAY TAB
    private TableView stationsTable;

    // WORKER SCHEDULE DISPLAY TAB
    private Button workerScheduleButton;
    private TableView workerTable;

    // PLANNER TAB
    private TextField passengerInput;
    private TextField conductorInput;
    private Button submitButton;

    // MURPHY TAB
    private ToggleButton murphyButton;

    // ACCESSORS
    public TrainSchedule getSchedule() { return schedule; }

    // MUTATORS
    public void setTrainInfo(){ }

    /*
    * Method in charge of setting up gettting the elements associated with the portions
    * of the UI that have actions associated with them.
    *-----
    * No inputs
    *-----
    * No returns
    */
    private void getUIElements(){
        trainScheduleButton = (Button) primary.getScene().lookup("#schedule_btn");
        mboToggle = (ToggleButton) primary.getScene().lookup("#mbo_toggle");
        trainTable = (TableView) primary.getScene().lookup("#train_info_table");

        stationsTable = (TableView) primary.getScene().lookup("#scheudle_table");

        workerScheduleButton = (Button) primary.getScene().lookup("#worker_schedule_btn");
        workerTable = (TableView) primary.getScene().lookup("#worker_schedule_table");

        passengerInput = (TextField) primary.getScene().lookup("#passengers");
        conductorInput = (TextField) primary.getScene().lookup("#conductors");
        submitButton = (Button) primary.getScene().lookup("#plan_btn");

        murphyButton = (ToggleButton) primary.getScene().lookup("#mbo_murphy_toggle");

        trainScheduleButton.setOnAction((ActionEvent a) -> {
            try {
                FileChooser fc = new FileChooser();
                fc.setTitle("Pick Train Schedule");
                File schedule = fc.showOpenDialog(primary);

                FileInputStream fileInputStream = new FileInputStream(schedule);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet worksheet = workbook.getSheet("Sheet1");
                XSSFRow row1 = worksheet.getRow(0);
                XSSFCell cellA1 = row1.getCell((short) 0);
                String a1Val = cellA1.getStringCellValue();
                XSSFCell cellB1 = row1.getCell((short) 1);
                String b1Val = cellB1.getStringCellValue();
                XSSFCell cellC1 = row1.getCell((short) 2);
                boolean c1Val = cellC1.getBooleanCellValue();
                XSSFCell cellD1 = row1.getCell((short) 3);
                Date d1Val = cellD1.getDateCellValue();

                System.out.println("A1: " + a1Val);
                System.out.println("B1: " + b1Val);
                System.out.println("C1: " + c1Val);
                System.out.println("D1: " + d1Val);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    // Button Actions

    public void chooseFile(){
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(new Stage());

        TrainSchedule scheudle = new TrainSchedule(file);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../UI/MBOUI.fxml"));          // Gets

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
