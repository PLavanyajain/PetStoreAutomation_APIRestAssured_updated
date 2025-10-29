package api.endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import api.payload.ReqresPayload;

public class Reqres_Endpoints {
	
//	static ResourceBundle getUrl()
//	{
//		ResourceBundle routes=ResourceBundle.getBundle("routes");  //Load properties file
//				return  routes;
//	}
	
	
	public static Response Create_Post_reqres(ReqresPayload payload)
	{
    Response response= given().contentType(ContentType.JSON)
    		.body(payload).header("x-api-key", "reqres-free-v1")
     .when().post(Routes.post_ReqRes_users);
    
    Assert.assertEquals(response.getStatusCode(), 201);
    
    return response;
		
	}
	
	public static Response get_reqresPage()
	{
		Response response=given().header("x-api-key", "reqres-free-v1")
		.queryParam("page", 2)
		.when().get(Routes.GET_ReqRes_page);
		
		return response;

	}

	

}
