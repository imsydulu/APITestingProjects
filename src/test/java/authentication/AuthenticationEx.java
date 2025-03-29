package authentication;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class AuthenticationEx {
	
	//basicAuthenticationTest
		@Test
		public void basicAuthenticationTest() {
			RestAssured.given()
			.auth().basic("postman", "password")
			.when()
			.get("https://www.postman-echo.com/basic-auth")
			.then().log().all();
		}
		
		//digestAuthenticationTest
		@Test
		public void digestAuthenticationTest() {
			RestAssured.given()
			.auth().digest("postman", "password")
			.when()
			.get("https://www.postman-echo.com/digest-auth")
			.then()
			.statusCode(200)
			.log().all();
		}
		//preemptiveAuthenticationTest
		@Test
		public void preemptiveAuthenticationTest() {
			RestAssured.given()
			.auth().preemptive().basic("postman", "password")
			.when()
			.get("https://www.postman-echo.com/basic-auth")
			.then()
			.statusCode(200)
			.log().all();
		}
		//bearer token test
		@Test
		public void bearerTokenTest() {
			String token = " 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";
			String resp = RestAssured.given()
			.headers("Authorization","Bearer "+token)
			.when().get("https://api.github.com/user/repos")
			.then()
			.statusCode(200)
			.extract().asPrettyString();
			System.out.println(resp);
			
		}
		//oauth2.0 test
		@Test
		public void oauthTest() {
			RestAssured.given()
			.auth().oauth2("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")
			.when()
			.get("https://api.github.com/user/repos")
			.then().statusCode(200)
			.extract().asString();
		}
		

}
