package com.readexcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {


	
	
    public static Map<String, String> getExcelData(String sheetName, String TestCaseID) throws IOException {
    	// Testcase file path
    	String filePath=System.getProperty("user.dir")+"\\TestData\\EscalationMatrix2.xlsx";
    	DataFormatter formatter = new DataFormatter();
        Map<String, String> data = new HashMap<String, String>();

        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        
        // Capture the first row from sheet >> By Using Iterator class 
        Iterator<Row> rows = sheet.iterator();
        Row firstRow = rows.next();
        //Iterate on each cell from the first row
        Iterator<Cell> cell = firstRow.cellIterator();
        int k = 0;
        int column = 0;

        // Identify where the Test CaseID text is present in the first row
        while (cell.hasNext()) {
            Cell value = cell.next();
            if (value.getStringCellValue().equalsIgnoreCase("Team_Type")) {
                column = k;
            }
            k++;
        }

        // Capture the Actual Testcase ID row once the TestCaseID is identified
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getCell(column).getStringCellValue().equalsIgnoreCase(TestCaseID)) {
                // Once the row is captured, grab all the cell values present in that row
               Iterator<Cell> cv = row.iterator();
                int index = 0;
                while (cv.hasNext()) {
                    Cell value = cv.next();
                    Cell key = firstRow.getCell(index);
                    String keyID = formatter.formatCellValue(key).trim();
                    String keyValue = formatter.formatCellValue(value).trim();
                    data.put(keyID, keyValue);
                    index++;
                }
            }
        }

        return data;
        
    }

}
