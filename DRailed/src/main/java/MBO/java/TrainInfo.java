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
public class TrainInfo {
    private ObservableList<InfoRow> infoRows = FXCollections.observableArrayList();
    public XSSFWorkbook schedule = new XSSFWorkbook();

    public TrainInfo(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            schedule = new XSSFWorkbook(fileIn);
            XSSFSheet worksheet = schedule.getSheet("Sheet1");
            for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++)
                this.createInfoRow(worksheet.getRow(i));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createInfoRow(XSSFRow data) {
        InfoRow infoRow = new InfoRow(
                data.getCell(0).getStringCellValue(),
                data.getCell(1).getStringCellValue(),
                data.getCell(2).getStringCellValue(),
                data.getCell(3).getStringCellValue(),
                data.getCell(4).getStringCellValue(),
                data.getCell(5).getStringCellValue(),
                data.getCell(6).getStringCellValue());
        infoRows.add(infoRow);
    }

    public void addRow(InfoRow data) {
        infoRows.add(data);
    }

    public InfoRow findRow(String id) {
        for(InfoRow data : infoRows){
            if(data.getTrainId().equals(id))
                return data;
        }
        return null;
    }

    public XSSFWorkbook getExcelFile(){
        return schedule;
    }
    public ObservableList<InfoRow> getRows() { return infoRows; }
}
