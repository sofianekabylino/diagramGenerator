package diagramGen.diagramGen;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONArrayCreator {

	public static JSONArray jaPackages = new JSONArray();
	public static JSONArray jaDependencies = new JSONArray();
	
	public void createJsonArray(){
		
		for(String str : CommonStatic.mapPackage.keySet()){
			JSONObject jo = new JSONObject();
			jo.put("key", CommonStatic.mapPackage.get(str));
			jo.put("text", str);
			jaPackages.put(jo);
		}	
		for(List<Integer> lst : CommonStatic.mapDependencies){
			JSONObject jo = new JSONObject();
			jo.put("from", lst.get(0));
			jo.put("to", lst.get(1));
			jaDependencies.put(jo);
		}	
		
	}
}
