package ctc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ctc.bean.Schedule;
import ctc.bean.Trace;

public class ExcelUtil {

	/**
	 * Read Excel
	 *
	 * @param fileName
	 */
	public static List<Schedule> readSchedule(String fileName) {

		List<Schedule> list = new ArrayList<Schedule>();

		boolean isE2007 = false;

		if (fileName.endsWith("xlsx"))
			isE2007 = true;
		org.apache.poi.ss.usermodel.Workbook wb = null;
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);

			if (isE2007)
				wb = new XSSFWorkbook(input);
			else
				wb = new HSSFWorkbook(input);
			if (wb != null && wb.getNumberOfSheets() > 0) {
				for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
					Sheet sheet = wb.getSheetAt(numSheet);
					if (sheet == null) {
						continue;
					}
					for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {

						Schedule schedule = new Schedule();

						Row row = sheet.getRow(rowNum);
						if (row == null) {
							continue;
						}

						Cell cell0 = row.getCell(0);// line
						if (isStrBlank(cell0.toString())) {
							continue;
						}
						cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
						schedule.setLine(cell0.toString()+rowNum);

						Cell cell1 = row.getCell(1);// Authority

						if (isStrBlank(cell1.toString())) {
							continue;
						}

						cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
						schedule.setAuthority(Integer.parseInt(cell1.toString()));

						Cell cell2 = row.getCell(2);// departure time

						if (isStrBlank(cell2.toString())) {
							continue;
						}

						cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
						schedule.setDeparturetime(cell2.toString());

						Cell cell3 = row.getCell(3);// AuthSequence

						if (isStrBlank(cell3.toString())) {
							continue;
						}

						cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
						schedule.setAuthsequence(cell3.toString());

						Cell cell4 = row.getCell(4);// SpeedSequence

						if (isStrBlank(cell4.toString())) {
							continue;
						}

						cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
						schedule.setSpeedsequence(cell4.toString());
						list.add(schedule);
					}

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// throw ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// throw e;
				}
			}
		}

		return list;
	}

	private static boolean isStrBlank(String str)
	{
		if(str == null || str.equals(""))
		{
			return true;
		}
		return false;
	}

	/**
	 * Read Trace
	 * @param fileName
	 * @return
	 */
	public static List<Trace> readTrace(String fileName,String line) {

		List<Trace> list = new ArrayList<Trace>();

		boolean isE2007 = false;

		if (fileName.endsWith("xlsx"))
			isE2007 = true;
		org.apache.poi.ss.usermodel.Workbook wb = null;
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);

			if (isE2007)
				wb = new XSSFWorkbook(input);
			else
				wb = new HSSFWorkbook(input);
			if (wb != null && wb.getNumberOfSheets() > 0) {
				int numSheet = 2;
				if("red".equals(line)){
					numSheet = 1;
				}
				//for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				Sheet sheet = wb.getSheetAt(numSheet);
				if (sheet == null) {
					return null;
				}
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {

					Trace trace = new Trace();

					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}

					Cell cell0 = row.getCell(0);// line
					if (cell0==null||isStrBlank(cell0.toString())) {
						continue;
					}
					cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
					trace.setLine(cell0.toString());

					Cell cell1 = row.getCell(1);// Section;

					if (isStrBlank(cell1.toString())) {
						continue;
					}

					cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
					trace.setSection(cell1.toString());

					Cell cell2 = row.getCell(2);// blocknumber

					if (isStrBlank(cell2.toString())) {
						continue;
					}

					cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
					trace.setBlocknumber(cell2.toString());

					Cell cell3 = row.getCell(3);// blocklength

					if (isStrBlank(cell3.toString())) {
						continue;
					}

					cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					trace.setBlocklength(cell3.getNumericCellValue());

					Cell cell4 = row.getCell(4);// blockgrade

					if (isStrBlank(cell4.toString())) {
						continue;
					}

					cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					trace.setBlockgrade(cell4.getNumericCellValue());


					Cell cell5 = row.getCell(5);// speedlimit

					if (isStrBlank(cell5.toString())) {
						continue;
					}

					cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					trace.setSpeedlimit(cell5.getNumericCellValue());

					list.add(trace);
				}

			}
			//}
		} catch (Exception ex) {
			ex.printStackTrace();
			// throw ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// throw e;
				}
			}
		}

		return list;
	}

	public static void main(String[] args) throws Exception {
		List<Trace> list = ExcelUtil.readTrace("Track_Layout_Vehicle_Data_vF1.xlsx", "green");
	}

}
