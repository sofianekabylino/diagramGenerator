package diagramGen.diagramGen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import spoon.reflect.reference.CtTypeReference;

public class CommonStatic {

	public static Map<String, Integer> mapPackage = new HashMap<String, Integer>();
	
	public static List<List<Integer>> mapDependencies = new LinkedList<List<Integer>>();
	
	public static Map<CtTypeReference, Integer> mapClasses = new HashMap<CtTypeReference, Integer>();
	
	public static int compteurPackage = 1;
	
	public static int compteurClass = 1;
}
