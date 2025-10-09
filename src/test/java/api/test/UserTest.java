package api.test;

import org.apache.logging.log4j.LogManager;   // ✅ Correct import
import org.apache.logging.log4j.Logger;       // ✅ Correct import
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;   // ✅ Use TestNG assert, not old JUnit

/*
 * UserTest.java
 * Test cases for User API: Create, Read, Update, Delete
 */

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;

	@BeforeClass   //before starting all the test methods 
	public void setup()
	{
		 faker=new Faker();
		 userPayload=new User();
		  
		 userPayload.setId(faker.idNumber().hashCode());
		 userPayload.setUsername(faker.name().username()); // unique username
		 userPayload.setFirstName(faker.name().firstName());
		 userPayload.setLastName(faker.name().lastName());
		 userPayload.setEmail(faker.internet().safeEmailAddress());
		 userPayload.setPassword(faker.internet().password(5,10));
		 userPayload.setPhone(faker.phoneNumber().cellPhone());	 
		 System.out.println("🔑 Test Username: " + userPayload.getUsername()); 
		 logger=LogManager.getLogger(this.getClass());
	

		}
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("***************Creating user******************");
		Response response=UserEndpoints.createUser(userPayload);
		response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testgetUserByName()
	{
		logger.info("**************Reading  use detailsr******************");
		Response response=UserEndpoints.ReadUser(userPayload.getUsername());
		response.then().log().all();
		System.out.println("USERNAME USED IN TEST: " + userPayload.getUsername());

		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
	     // update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail("updated_" + faker.internet().safeEmailAddress());
        
        logger.info("***************Updating user******************");
		Response response=UserEndpoints.Updateuser(this.userPayload, userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		 // check if updated
        Response checkResponse = UserEndpoints.ReadUser(this.userPayload.getUsername());
        checkResponse.then().log().all();
        
	}
	
	@Test(priority = 4)
	public void test_DeleteUser()
	{
		System.out.println("Deleting user: " + userPayload.getUsername());

		Response response=UserEndpoints.DeleteUser(this.userPayload.getUsername());
	    response.then().log().body().statusCode(200);
		
		//Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority = 4)
	public void test_DeleteUser1()
	{
		System.out.print("Added dummy");
	}
	
	

}
