package diagramGen.diagramGen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import spoon.reflect.reference.CtTypeReference;

public class CommonStatic {

	public static Map<String, Integer> MAP_PACKAGE = new HashMap<String, Integer>();
	
	public static List<List<Integer>> MAP_DEPENDENCIES = new LinkedList<List<Integer>>();
	
	@SuppressWarnings("rawtypes")
	public static Map<CtTypeReference, Integer> MAP_CLASSES = new HashMap<CtTypeReference, Integer>();
	
	public static int COMPTEUR_PACKAGE = 1;
	
	public static int COMPTEUR_CLASS = 1;
}
