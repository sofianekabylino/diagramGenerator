package diagramGen.diagramGen;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import processors.PackageProcess;

public class JSONArrayCreator {

	public static JSONArray jaPackages = new JSONArray();
	
	public void createJsonArray(){
		
		Iterator<String> i = PackageProcess.lst.iterator();
		while (i.hasNext()) {
			Pack.mapPackage.put(Pack.key++, i.next());
		}
//    	for(Integer str : Pack.mapPackage.keySet()){
//    		System.out.println(str+" ** "+Pack.mapPackage.get(str));
//		}
		
		for(Integer str : Pack.mapPackage.keySet()){
			JSONObject jo = new JSONObject();
			jo.put("key", str);
			jo.put("text", Pack.mapPackage.get(str));
			jaPackages.put(jo);
		}		
		//System.out.println(jaPackages);
	}
}
