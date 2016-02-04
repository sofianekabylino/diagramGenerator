package diagramGen.diagramGen;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import processors.PackageProcess;

public class JSONArrayCreator {

	public static JSONArray jaPackages = new JSONArray();
	public static JSONArray jaDependencies = new JSONArray();
	
	public void createJsonArray(){
		
//		Iterator<String> i = PackageProcess.lst.iterator();
//		while (i.hasNext()) {
//			CommonStatic.mapPackage.put(i.next(), CommonStatic.key++);
//		}
//    	for(Integer str : Pack.mapPackage.keySet()){
//    		System.out.println(str+" ** "+Pack.mapPackage.get(str));
//		}
		
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
			jo.put("text", "import");
			jaDependencies.put(jo);
		}	
		
	}
}
