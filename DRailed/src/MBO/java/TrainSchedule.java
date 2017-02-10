package MBO.java;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

/**
 * Created by joero on 2/6/2017.
 */
public class TrainSchedule {
    public TrainSchedule() {

    }

    public TrainSchedule(File file){

    }

    public XSSFWorkbook getExcelFile(){
        return new XSSFWorkbook();
    }
}
