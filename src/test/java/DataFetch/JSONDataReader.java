package DataFetch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataReader {
	
	
	public static List<HashMap<String, String>> dataFetch() throws IOException
	{
		String jsonDataString= Files.readString(Paths.get("/Users/pankajshukla/eclipse-workspace/Amazon/src/test/java/DataResources/setupData.json"));
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> dataHashMaps = objectMapper.readValue(jsonDataString, new TypeReference<List<HashMap<String,String>>>() {
		
		});
		
		return dataHashMaps;
	}

}
