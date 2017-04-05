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
public class TrainSchedule {
    private ObservableList<TrainRow> trainRows = FXCollections.observableArrayList();

    public TrainSchedule(File file) {

    }

    private void createTrainRow(XSSFRow data) {
        TrainRow trainRow = new TrainRow(
                data.getCell(0).getStringCellValue(),
                data.getCell(1).getStringCellValue(),
                data.getCell(2).getStringCellValue(),
                data.getCell(3).getStringCellValue(),
                data.getCell(4).getStringCellValue(),
                data.getCell(5).getStringCellValue(),
                data.getCell(6).getStringCellValue(),
                data.getCell(7).getStringCellValue(),
                data.getCell(8).getStringCellValue());
        trainRows.add(trainRow);
    }

    public XSSFWorkbook getExcelFile(){
        return null;
    }
    public ObservableList<TrainRow> getRows() { return trainRows; }
}
