package querypathparam;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ParametersEx {
	
	@Test
	public void testPathParam() {
		// https://reqres.in/api/users/2
		
		RestAssured
		.given()
		.pathParam("path", "users")
		.pathParam("value", "2")
		.when()
		.get("https://reqres.in/api/{path}/{value}")
		.then().log().all();
	}
	
	@Test
	public void testQueryParam() {
		//https://reqres.in/api/users?page=2
		
		RestAssured
		.given()
		.pathParam("path", "users")
		.queryParam("page", 2)
		.when()
		.get("https://reqres.in/api/{path}")
		.then().log().all();
	}

}
