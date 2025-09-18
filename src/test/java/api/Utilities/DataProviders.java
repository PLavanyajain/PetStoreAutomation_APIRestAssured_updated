package api.Utilities;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws Exception {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        XLUtility xl = new XLUtility(path);

        String sheetName = "Sheet1";
        int rowCount = xl.getRowCount(sheetName);
        int colCount = xl.getCellCount(sheetName, 0);
        
        String[][] ApiData = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {   // skip header row (0)
            for (int j = 0; j < colCount; j++) {
            	ApiData[i - 1][j] = xl.getCellData(sheetName, i, j);
            }
        }
        return ApiData;
    }
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws Exception {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        XLUtility xl = new XLUtility(path);

        String sheetName = "Sheet1";
        int rowCount = xl.getRowCount(sheetName);
     
       String Apidata[]=new String[rowCount];

        for (int i = 1; i <= rowCount; i++) {   // skip header row (0)
             {
            	 Apidata[i - 1] = xl.getCellData(sheetName, i,1);
            }
        }
        return Apidata;
}}
