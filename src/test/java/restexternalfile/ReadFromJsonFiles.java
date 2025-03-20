package restexternalfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ReadFromJsonFiles {

	public String getJsonFile() {
		File json_file = new File("./jsonfile.json");
		FileReader reader;
		JSONTokener jtokener;
		JSONObject jobj = null;
		try {
			reader = new FileReader(json_file);
			jtokener = new JSONTokener(reader);
			jobj = new JSONObject(jtokener);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return jobj.toString();

	}
	
	@Test
	public void postReqTest() {
		RestAssured
		.given()
		.contentType(ContentType.JSON)
		.body(getJsonFile())
		.post("https://reqres.in/api/users")
		.then().log().all();
	}

}
