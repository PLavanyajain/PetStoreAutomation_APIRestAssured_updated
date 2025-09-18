package api.endpoints;
/*
 * Swagger URI -->https://petstore.swagger.io
 * Create user(Post) https://petstore.swagger.io/v2/user
 * Get user (Get) : https://petstore.swagger.io/v2/user/{username}
 * Update user(put) : https://petstore.swagger.io/v2/user/{username}
 * Delete user(Delete) : https://petstore.swagger.io/v2/user/{username}
 * 
 * https://petstore.swagger.io/v2/  -> Base url [common url in all the uri]
 * /user/{username} -> endpoints
 * 
 * Contains all API endpoints for User module.
 * --------------------------------------------------------
 * POST https://petstore.swagger.io/v2/pet
 * GET https://petstore.swagger.io/v2/pet/{petId}
 * PUT https://petstore.swagger.io/v2/pet
 * DELETE https://petstore.swagger.io/v2/pet/{petId}
---------------------------------------------------------
GET https://petstore.swagger.io/v2/store/inventory
POST https://petstore.swagger.io/v2/store/order
GET https://petstore.swagger.io/v2/store/order/{orderId}
DELETE https://petstore.swagger.io/v2/store/order/{orderId}

 */
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User Module
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//Pet Module
	
	public static String post_pet_url=base_url+"/pet";
	public static String get_pet_url=base_url+"/pet/{petId}";
	public static String update_pet_url=base_url+"/pet";
	public static String Delete_pet_url=base_url+"/pet/{petId}";
	
	//Store
	
	public static String post_store_url=base_url+"/store/order";
	public static String get_store_url=base_url+"/store/inventory";
	public static String get_Store_OrderId=base_url+"/store/order/{orderId}";
	public static String Delete_store_url=base_url+"/store/order/{orderId}";

}
