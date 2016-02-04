package diagramGen.diagramGen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommonStatic {

	public static Map<String, Integer> mapPackage = new HashMap<String, Integer>();
	
	public static List<List<Integer>> mapDependencies = new LinkedList<List<Integer>>();
	
//	static JSONArray ja = new JSONArray();
	
	static String nodeDataArray = "";
	
	static String linkDataArray = "";
	
	public static int key = 1;
}
