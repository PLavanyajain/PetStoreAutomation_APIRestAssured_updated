package api.test;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.endpoints.Reqres_Endpoints;
import com.github.javafaker.Faker;

import api.payload.ReqresPayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReqresTest {
	Faker faker;
	ReqresPayload reqres;
	public Logger logger;

	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		reqres=new ReqresPayload();
		
		reqres.setName(faker.name().username());
		reqres.setJob("QA Automation Tester");	
		//reqres.setEmail(faker.internet().emailAddress());
//		String fakeEmail = faker.name().username() + "@reqres.in";
//		reqres.setEmail(fakeEmail);
		//reqres.setPassword(faker.internet().password());
		
		reqres.setEmail("eve.holt@reqres.in");
    	reqres.setPassword("cityslicka");
    	reqres.setName("Lavanyabhuvan Updated");
    	reqres.setJob("Senior Automation Engineer");
		
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void Test_Post_Reqres_001()
	{
		logger.info("*********&&******Creating user_testPost_Reqres_01*********&&*********");
		Response response=Reqres_Endpoints.Create_Post_reqres(reqres);
		response.then().log().all();
		
		 Assert.assertEquals(response.getStatusCode(), 201);
		 Assert.assertNotNull(response.getContentType());
		 Assert.assertNotNull(response.getStatusLine());
		 
		 String id = response.jsonPath().getString("id");
		 String createdAt = response.jsonPath().getString("createdAt");
		 
		// ✅ Verify response contains important fields
		 Assert.assertNotNull(response.jsonPath().getString("id"), "ID should not be null");
		 Assert.assertNotNull(response.jsonPath().getString("createdAt"), "createdAt should not be null");
		 
		 // ✅ Optional: check success line format
		 Assert.assertTrue(response.getStatusLine().contains("201"), "Status line should indicate 201 Created");
		 

               System.out.println("✅ ID: " + id);
               System.out.println("✅ CreatedAt: " + createdAt);
               logger.info("✅ ID: " + id);
               logger.info("✅ CreatedAt: " + createdAt);

    }
	@Test(priority = 1)
	public void Test_Get_Reqres_002()
	{
		logger.info("*********Reading user_Test_Get_Reqres_002****************");
		Response response=Reqres_Endpoints.get_reqresPage();
		response.then().log().all();
		
		String data= response.jsonPath().getString("data");
		System.out.println(data);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		  // Step 4: Parse the response JSON
        JsonPath json = response.jsonPath();
   
		// ✅ Validate top-level fields
        Assert.assertEquals((int) json.get("page"), 2, "Page number should be 2");
        Assert.assertEquals((int) json.get("per_page"), 6, "Per page should be 6");
        Assert.assertEquals((int) json.get("total_pages"), 2, "Total pages should be 2");
        
        
        // ✅ Validate data array
        List<Map<String, Object>> users = json.getList("data");
        Assert.assertEquals(users.size(), 6, "There should be 6 users on page 2");
     }
	
	@Test(priority = -1)
	public void Test_login_reqres_003()
	{
		logger.info("*********Reading _Test_login_reqres_003****************");

		Response response=Reqres_Endpoints.Create_Login_reqres(reqres);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertNotEquals(response.jsonPath().getString("token"),"Token should not be null");
	}
	@Test(priority = 1)
	public void Test_User_Update_Reqres_004()
	{
		
		logger.info("*********Updating user_Test_Get_Reqres_002****************");
		Response response=Reqres_Endpoints.Update_user_Reqres(reqres);
	
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertNotEquals(response.jsonPath().getString("updatedAt"),"Data should be updated");
		
	    String updatedName = response.jsonPath().getString("name");
	    String updatedJob = response.jsonPath().getString("job");
	    String updatedAt = response.jsonPath().getString("updatedAt");

	    Assert.assertEquals(updatedName, "Lavanyabhuvan Updated", "Name should be updated correctly");
	    Assert.assertEquals(updatedJob, "Senior Automation Engineer", "Job should be updated correctly");
	    Assert.assertNotNull(updatedAt, "updatedAt should not be null");

	    logger.info("✅ User updated successfully: " + updatedName + " | " + updatedJob);
	    logger.info("✅ UpdatedAt: " + updatedAt);
	}
	
	@Test(priority = 1)
	public void Test_Delete_user_reqres_005()
	{
		logger.info("*********Deleting Test_Delete_user_reqres_005****************");
		Response response=Reqres_Endpoints.Delete_User_reqres(reqres);
		
		Assert.assertEquals(response.getStatusCode(),204);
		Assert.assertTrue(response.getStatusLine().contains("204"));
		 Assert.assertEquals(response.getStatusCode(), 204, "Expected 205 No Content");

	}
}

