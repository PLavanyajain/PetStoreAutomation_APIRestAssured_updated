package api.endpoints;
import static io.restassured.RestAssured.given;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//UserEndpoints.java
//Created  to perform Create,Read,Update,Delete Requests the user API
// here we are creating endpoints not the test methods

public class UserEndpoints {
	public static  Response createUser(User payload)
	{
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
	   .when()
	    .post(Routes.post_url);
		
	return response;	
	}
	
	public static  Response ReadUser(String username)
	{
	Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", username)
	   .when()
	    .get(Routes.get_url);
		
	return response;	
	
	}
	
	public static  Response Updateuser(User payload,String username)
	{
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
	   .when()
	    .put(Routes.update_url);
		
	return response;	
	
	}
	
	public static  Response DeleteUser(String username)
	{
	Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", username)

	   .when()
	    .delete(Routes.delete_url);
		
	return response;	
	
	}
	

}
