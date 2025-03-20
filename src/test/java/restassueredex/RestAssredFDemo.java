package restassueredex;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class RestAssredFDemo {

	@Test(enabled = false)
	public void testGetReq() {

		ValidatableResponse validateResp = RestAssured.given().when().get("https://reqres.in/api/users/2").then().log()
				.all().statusCode(200);

	}
	@Test
	public void getReqTest() {
		Response response = RestAssured.when().get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().prettyPrint());
	}

	@Test
	public void postReqTest() {

		ValidatableResponse postresponse = RestAssured.given().contentType(ContentType.JSON).body(pojoPalyload()).when()
				.post("https://reqres.in/api/users").then().log().all();
		// System.out.println(postresponse);

	}

	public HashMap<String, String> mapPayload() {

		/*
		 * { "name": "morpheus", "job": "leader" }
		 */

		HashMap<String, String> bodydata = new HashMap<String, String>();
		bodydata.put("name", "morpheus");
		bodydata.put("job", "leadr");
		return bodydata;

	}

	public String payloadJson() {
		JsonObject jsonobj = new JsonObject();
		jsonobj.addProperty("name", "morpheus");
		jsonobj.addProperty("job", "leader");
		return jsonobj.toString();
	}

	public Payloadclass pojoPalyload() {
		Payloadclass data = new Payloadclass();
		data.setName("sydulu");
		data.setJob("javatesting");
		return data;
	}

}
