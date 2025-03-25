package jsonparsing;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonData {
	
	@Test
	public void parseTheData() {
		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("https://reqres.in/api/users");
		
		System.out.println(response.jsonPath().get("total").toString());
		System.out.println(response.jsonPath().get("data[0].first_name").toString());
		System.out.println(response.jsonPath().get("per_page").toString());
		
		JSONObject jsonobj = new JSONObject(response.body().asPrettyString());
		
		JSONArray json_arr = jsonobj.getJSONArray("data");
		for (int i = 0; i < json_arr.length(); i++) {
			String email=json_arr.getJSONObject(i).get("email").toString();
			
			System.out.println(email);
		}

	}
	
	@Test
	public void validateTheJsonData() {
		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("https://reqres.in/api/users");
		
		boolean status = false;
		
		JSONObject jsonobj = new JSONObject(response.asPrettyString());
		JSONArray json_arr = jsonobj.getJSONArray("data");
		for (int i = 0; i < json_arr.length(); i++) {
			String first_name=json_arr.getJSONObject(i).get("first_name").toString();
			if (first_name.equals("Emma")) {
				status=true;
				break;
			}
			
		}
		assertTrue(status);

	}

}
