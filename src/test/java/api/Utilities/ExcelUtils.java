package api.Utilities;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
    public static Object[][] getData(String filePath, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);//Opens the Excel file.
        XSSFWorkbook workbook = new XSSFWorkbook(fis);//Loads the workbook into memory.
        XSSFSheet sheet = workbook.getSheet(sheetName);//Selects the specific sheet by name.

        int rowCount = sheet.getPhysicalNumberOfRows();//rowCount → Total number of non-empty rows in the sheet.

        int colCount = sheet.getRow(0).getLastCellNum(); //colCount → Number of cells/columns in the first row (usually header row).
   

        Object[][] data = new Object[rowCount - 1][colCount]; //Create 2D Object Array
        													// Excludes the header row, so rowCount - 1.
                              // The array stores all the data from Excel to pass to TestNG or tests.

                              //Object is used because Excel can have String, numeric, or boolean values.

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();//Outer loop → iterates rows starting from 1 (skip header).

                                        //Inner loop → iterates all columns in that row.

                                       //sheet.getRow(i).getCell(j).toString() → converts cell value to String, stores in array.

                                             //data[i - 1][j] → fills the array starting from 0 index.
            }
        }
        workbook.close();
        fis.close();
        return data;
    }
}
