package api.Utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DataProviders {

    @DataProvider(name = "Data")
    @Parameters({"excelFile", "sheetName"})   // values passed from testng.xml
    public String[][] getAllData(String excelFile, String sheetName) throws Exception {
        String path = System.getProperty("user.dir") + "//testData//" + excelFile;
        XLUtility xl = new XLUtility(path);

        int rowCount = xl.getRowCount(sheetName);
        int colCount = xl.getCellCount(sheetName, 0);

        String[][] ApiData = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) { // skip header row
            for (int j = 0; j < colCount; j++) {
                ApiData[i - 1][j] = xl.getCellData(sheetName, i, j);
            }
        }
        return ApiData;
    }
    @DataProvider(name = "UserNames")
    @Parameters({"excelFile", "sheetName"})  
    public String[] getUserNames(String excelFile, String sheetName)  throws Exception {
    	  String path = System.getProperty("user.dir") + "//testData//" + excelFile;
          XLUtility xl = new XLUtility(path);

        String sheetName1 = "Sheet1";
        int rowCount = xl.getRowCount(sheetName1);
     
       String Apidata[]=new String[rowCount];

        for (int i = 1; i <= rowCount; i++) {   // skip header row (0)
             {
            	 Apidata[i - 1] = xl.getCellData(sheetName1, i,1);
            }
        }
        return Apidata;
}}
