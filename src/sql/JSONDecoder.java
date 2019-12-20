package sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


public class JSONDecoder {
	BufferedReader reader;
	String filePath = "Reddit2007-10.json";
	
	public ArrayList<JSONObject> getJSONObjetcs(String fileName) throws IOException {
		File file = new File(fileName);
		String path = file.getAbsolutePath();
		reader = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		
		ArrayList<JSONObject> jsonObjs = new ArrayList<JSONObject>();
		
		while(line != null) {
			// and then read next line
			jsonObjs.add(encodeToJSON(line));
			line = reader.readLine();
		}
		
		return jsonObjs;
		
	}

	private JSONObject encodeToJSON(String line) {
	
		JSONObject value = new JSONObject(line);
		
		return value;
				 
	}
	
	
}
