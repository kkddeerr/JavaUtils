package kdh.test;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JsonTest {
	
	String expectedVal = "{\"param1\":\"paramfir1\",\"param2\":\"paramfir2\",\"param3\":\"paramfir3\",\"param4\":\"paramfir4\"}";
	String json = "{\"param1\":\"paramfir1\",\"param2\":\"paramfir2\",\"param3\":\"paramfir3\",\"param4\":\"paramfir4\"}";
	String expectedStrval[] = {"paramfir1","paramfir2","paramfir3","paramfir4"};
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void ObjectToJsonFile() throws JsonGenerationException, JsonMappingException, IOException {
    	
    	Jobject jo2 = new Jobject("paramfir1","paramfir2","paramfir3","paramfir4");
    	//objectMapper.writeValue(new File("target/jo.json"), jo2);
    	objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("target/jo.json"), jo2);
    	File fjo = new File("target/jo.json");
    	BufferedReader frjo = new BufferedReader(new FileReader(fjo)); 
    	String s;
    	s = frjo.readLine();
    	frjo.close();
    	assertEquals(expectedVal, s);
	}
	
	@Test
	public void JsonToObjectFile() throws JsonParseException, JsonMappingException, IOException {
		Jobject jo3 = new Jobject();
		Jobject Expeactedjo = new Jobject("paramfir1","paramfir2","paramfir3","paramfir4");
		jo3 = objectMapper.readValue(json, Jobject.class);
		assertEquals(Expeactedjo.getParam1(), jo3.getParam1());
		assertEquals(Expeactedjo.getParam2(), jo3.getParam2());
		assertEquals(Expeactedjo.getParam3(), jo3.getParam3());
		assertEquals(Expeactedjo.getParam4(), jo3.getParam4());
	}
	
	@Test
	public void JsonToHaspMap() throws JsonParseException, JsonMappingException, IOException {
		HashMap<String,Object> jhm = new HashMap<String,Object>();
		jhm = objectMapper.readValue(json, new TypeReference<HashMap<String,Object>>(){});
		assertEquals(jhm.get("param1"),expectedStrval[0]);
		assertEquals(jhm.get("param2"),expectedStrval[1]);
		assertEquals(jhm.get("param3"),expectedStrval[2]);
		assertEquals(jhm.get("param4"),expectedStrval[3]);
	}
}
