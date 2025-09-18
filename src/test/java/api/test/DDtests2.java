package api.test;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.Utilities.DataProviders;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import api.Utilities.*;
@Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
public class DDtests2 {
	 @Parameters("excelFile") // receives value from testng.xml
	    @DataProvider(name = "UserData")
	    public Object[][] getUserData(String excelFile) throws Exception {
	        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/" + excelFile;
	        return ExcelUtils.getData(path, "Users");
	 }
	        
	public void TestPostUser(String userID,String userName,String Fname,String Lname,String Emailid,String pwd,String ph)
	{
		User userpayload =new User(); // pojo 
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstName(Lname);
		userpayload.setLastName(Lname);
		userpayload.setEmail(Emailid);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response response=UserEndpoints2.createUser(userpayload);
		//response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
			
	}
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass=DataProviders.class)
	public void DeleteUserByName(String username)
	{
		Response response=UserEndpoints2.DeleteUser(username);
	    response.then().log().body().statusCode(200);
	    
	 
	}
	
	

}
