package utilities.dataRelated;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;

public class JsonManager {
	// -----Property-----//
	private String filePath = "";

	// -----Constructor ---//
	public JsonManager(String fp) {
		filePath = fp;
	}

	// -----Methods------//
	// step 1: open the json file and read its content
	// step 2: return the content as a string
	private String jsonToString() {
		String stringform = null;
		try {
			JSONParser parser = new JSONParser();
			Object object = parser.parse(new FileReader(filePath));
			JSONObject jsonObj = (JSONObject) object;
			stringform = (String) jsonObj.toJSONString();
			return stringform;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringform;
	}

	/**
	 * Enter your jason path query to extract the user related data
	 *
	 * @param query
	 * @return string
	 */
	public String data(String query) {
		String jsonStr = jsonToString();
		return JsonPath.read(jsonStr, query);
	}

}
