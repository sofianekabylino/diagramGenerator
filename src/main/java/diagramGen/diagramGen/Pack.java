package diagramGen.diagramGen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;

public class Pack {

	public static Map<Integer, String> mapPackage = new HashMap<Integer, String>();
	
	public static Map<Integer, List<Integer>> mapDependencies = new HashMap<Integer, List<Integer>>();
	
//	static JSONArray ja = new JSONArray();
	
	static String nodeDataArray = "";
	
	static String linkDataArray = "";
	
	public static int key = 1;
}
