package api.test;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import api.Utilities.DataProviders;
import api.Utilities.ExtentReportManager;
import api.endpoints.Store_UserEndpoints;
import api.payload.Store_payload;
import io.restassured.response.Response;

//@Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
@Listeners(ExtentReportManager.class)
public class Test_Store_DD1 {
	@Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
   public void TestInventorybyUsingGet()
	{
		Response response=Store_UserEndpoints.GetInventory();
		Assert.assertEquals(response.getStatusCode(),200);
	}
   //@Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void Test_PlaceOrder_post(int id,int petId,int quantity,String shipDate,String status,boolean complete)
	{
	   ExtentReportManager.getTest().log(Status.INFO, "Creating order...");
		Store_payload payload=new Store_payload();
		payload.setId(id);
		payload.setPetId(petId);
		payload.setQuantity(quantity);
		payload.setShipDate(shipDate);
		payload.setStatus(status);
		payload.setComplete(complete);
		

        // log safely
		 if (ExtentReportManager.getTest() != null) {
	            ExtentReportManager.getTest().log(Status.INFO, "Placing order for PetId: " + petId);
	        }
		
		Response response=Store_UserEndpoints.Post_PlaceOrder(payload);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	//@Test(priority = 2,dataProvider = "UserNames",dataProviderClass=DataProviders.class)
	public void Test_purchaseOrderbyID(int id)
	{

        ExtentReportManager.getTest().log(Status.INFO, "Fetching order...");
		Response response=Store_UserEndpoints.GetOrderById(id);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	//@Test(priority = 2,dataProvider = "UserNames",dataProviderClass=DataProviders.class)
	public void Test_deleteByOrderId(int id)
	{
		Response response=Store_UserEndpoints.DeleteUsingOrderId(id);
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
