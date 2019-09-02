package kdh.test;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonGenerationException, JsonMappingException, IOException
    {
    	ObjectMapper objectMapper = new ObjectMapper();
    	Jobject jo2 = new Jobject("paramfir1","paramfir2","paramfir3","paramfir4");
    	try { 
    	objectMapper.writeValue(new File("target/jo.json"), jo2);
    	
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("fail");
    	}
        
    }
}
