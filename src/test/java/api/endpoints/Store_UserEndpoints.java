package api.endpoints;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import api.payload.Store_payload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Store_UserEndpoints {
	
	public static Response Post_PlaceOrder(Store_payload payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON).body(payload)
		.when().post(Routes.post_store_url);
		
		Assert.assertEquals(response.jsonPath().getString("status"),"placed");
		Assert.assertEquals(response.getStatusCode(), 200);
	return response;	
	}
	
	public static Response GetInventory()
	{
		Response response=given()
		.accept(ContentType.JSON)
		.when().get(Routes.get_store_url);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		return response;		
	}
	
	public static Response GetOrderById(int orderId)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON).pathParam("OrderID", orderId)
				.when().get(Routes.get_Store_OrderId);
				
				Assert.assertEquals(response.jsonPath().getString("status"),"placed");	
				Assert.assertEquals(response.getStatusCode(), 200);
		return response;	
	}
	public static Response DeleteUsingOrderId(int orderId)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON).pathParam("OrderID", orderId)
				.when().delete(Routes.get_Store_OrderId);
				
				Assert.assertEquals(response.jsonPath().getString("status"),"placed");	
				Assert.assertEquals(response.getStatusCode(), 200);
		return response;	
	}

}
