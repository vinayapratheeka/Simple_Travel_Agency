package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
    public static Object[][] getData() {
    		String path = System.getProperty("user.dir") + "/src/test/resources/testingdata.xlsx";
    		Object[][] data = null;
    		try {
	        FileInputStream fis = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fis);
	        XSSFSheet sheet = wb.getSheet("Sheet1");
	        int rows = sheet.getLastRowNum();
	        int cols = sheet.getRow(0).getLastCellNum();
	        data = new Object[rows][cols];
	        DataFormatter formatter = new DataFormatter();
	        for (int i = 1; i <= rows; i++) {
	            for (int j = 0; j < cols; j++) {
	            	data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
	            }
	        }
	        wb.close();
	        fis.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    			}
    		return data;
    }
}
    