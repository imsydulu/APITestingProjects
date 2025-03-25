package validation;

import java.util.Map;
import java.util.Set;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ValidationEx {
	// https://reqres.in/api/users/2
	//https://reqres.in/api/users?page=2
	// https://reqres.in/
	@Test
	public void getCookies() {
		Response response = RestAssured.given()
		.when()
		.get("https://www.google.com/");
		
		//String cookie=response.getCookie("AEC");
		//System.out.println(cookie);
		Map<String, String> allcookies = response.getCookies();
		Set<String> keyset = allcookies.keySet();
		for (String key : keyset) {
			System.out.println("Cookie_Name ==> "+key+"  Cookie_Value ==> "+allcookies.get(key));
		}
	}
	
	@Test
	public void getHeaders() {
		Response resp=RestAssured.given()
		.when()
		.get("https://www.google.com/");
		
		Headers allheaders = resp.getHeaders();
		for (Header header : allheaders) {
			System.out.println("Header Name==> "+header.getName()+" Header Value==> "+header.getValue());
		}
	}
	
	
	@Test
	public void getReqValidation() {
		ValidatableResponse resp = RestAssured.when().get("https://reqres.in/api/users/2")
		.then()
		.statusCode(200)
		.contentType("application/json");
		
		//validatuing 
		resp.body("data.id", Matchers.equalTo(2));
		resp.body("data.email", Matchers.equalTo("janet.weaver@reqres.in"));
		resp.body("data.first_name", Matchers.equalTo("Janet"));
		resp.body("data.last_name", Matchers.equalTo("Weaver"));
		resp.body("support.url", Matchers.equalTo("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"));
		
	}
	@Test
	public void validateCookies() {
		RestAssured.when().get("https://www.google.com/").then()
		.cookie("AEC")
		.header("Content-Type", Matchers.equalToIgnoringCase("text/html; charset=ISO-8859-1"))
		.cookie("AEC", Matchers.startsWith("AVcja2"))
		.cookie("NID", Matchers.startsWith("522"));
		
		System.out.println("test passed");
	}

	
	//response
	
	/*
	  "data": { "id": 2, "email": "janet.weaver@reqres.in", "first_name": "Janet",
	  "last_name": "Weaver", "avatar": "https://reqres.in/img/faces/2-image.jpg" },
	  "support": { "url":
	  "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
	  "text":
	  "Tired of writing endless social media content? Let Content Caddy generate it for you."
	  }
	  */
	 
}
