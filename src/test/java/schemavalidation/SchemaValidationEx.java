package schemavalidation;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidationEx {
	
	@Test
	public void schemaValidation() {
		
		RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.get("https://reqres.in/api/users")
		.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("reqresschema.json"));
	}

}
