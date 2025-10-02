package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//UserEndpoints.java
//Created  to perform Create,Read,Update,Delete Requests the user API
// here we are creating endpoints not the test methods

public class UserEndpoints2 {
	
	//Method created for getting URL's from properties file
	//ResourceBundle is a special class
	static ResourceBundle getUrl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //Load properties file
				return  routes;
	}
	public static  Response createUser(User payload)
	{
	String post_url=getUrl().getString("post_user_url");
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
	   .when()
	    .post(post_url);
		
	return response;	
	}
	
	public static  Response ReadUser(String username)
	{
		String get_url=getUrl().getString("get_user_url");
	Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", username)
	   .when()
	    .get(get_url);
		
	return response;	
	
	}
	
	public static  Response Updateuser(User payload,String username)
	{
		String update_url=getUrl().getString("update_user_url");
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
	   .when()
	    .put(update_url);
		
	return response;	
	
	}
	
	public static  Response DeleteUser(String username)
	{
		String delete_url=getUrl().getString("delete_user_url");
	Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", username)

	   .when()
	    .delete(delete_url);
		
	return response;	
	
	}
	public static Response loginUser(String username,String password)
	{
		String Login_Url=getUrl().getString("Get_User_Login");
		Response response=given()
		 .accept(ContentType.JSON).pathParam("username", username).pathParam("password", password)
		.when().get(Login_Url);
		
	return response;	
	}
	public static Response Logout()
	{
		String LogoutUser=getUrl().getString("get_User_url_logout");
		Response response=given()
		.accept(ContentType.JSON)
		.when().get(LogoutUser);
		
		return response;
		
	}
	

}
