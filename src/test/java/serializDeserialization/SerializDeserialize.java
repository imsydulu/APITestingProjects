package serializDeserialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializDeserialize {
	String data = null;
	@Test(priority = 1)
	public void serilaizationTest() {

		PojoAbstract pojo = new PojoAbstract();
		pojo.setName("sydulu");
		pojo.setJob("Tester");
		
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			data=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo); //serialization
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(data);
	}
	
	@Test(priority = 2, dependsOnMethods  = "serilaizationTest")
	public void deSerilaizationTest() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
		PojoAbstract	objpojo=mapper.readValue(data, PojoAbstract.class);
		System.out.println(objpojo.getJob()+"  "+objpojo.getName());
		
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
