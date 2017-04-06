package MBO.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by joero on 2/6/2017.
 */
public class WorkerSchedule {
    private ObservableList<WorkerRow> workerRows = FXCollections.observableArrayList();
    public XSSFWorkbook schedule = new XSSFWorkbook();

    public WorkerSchedule(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            schedule = new XSSFWorkbook(fileIn);
            XSSFSheet worksheet = schedule.getSheet("Sheet1");
            for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++)
                this.createRow(worksheet.getRow(i));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRow(XSSFRow data) {
        WorkerRow workerRow = new WorkerRow(
                data.getCell(0).getStringCellValue(),
                data.getCell(1).getStringCellValue(),
                data.getCell(2).getStringCellValue());
        workerRows.add(workerRow);
    }

    public XSSFWorkbook getExcelFile(){
        return schedule;
    }
    public ObservableList<WorkerRow> getRows() { return workerRows; }
}
