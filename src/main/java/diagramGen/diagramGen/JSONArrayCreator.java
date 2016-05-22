package diagramGen.diagramGen;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONArrayCreator {

	public static JSONArray jaPackages = new JSONArray();
	public static JSONArray jaDependencies = new JSONArray();
	
	public void createJsonArray(){
		
		for(String str : CommonStatic.MAP_PACKAGE.keySet()){
			JSONObject jo = new JSONObject();
			jo.put("key", CommonStatic.MAP_PACKAGE.get(str));
			jo.put("text", str);
			jaPackages.put(jo);
		}	
		for(List<Integer> lst : CommonStatic.MAP_DEPENDENCIES){
			JSONObject jo = new JSONObject();
			jo.put("from", lst.get(0));
			jo.put("to", lst.get(1));
			jaDependencies.put(jo);
		}	
		
	}
}
