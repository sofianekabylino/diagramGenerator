package processors;

import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import diagramGen.diagramGen.CommonStatic;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.reference.CtTypeReference;

public class ClassRelationsProcessor extends AbstractProcessor<CtClass> {

	public static JSONArray relationship = new JSONArray();
	public static boolean[][] alreadyFound;

	public void process(CtClass classe) {

		Map<CtTypeReference, Integer> map = CommonStatic.mapClasses;
		Set<CtTypeReference> key = map.keySet();
		alreadyFound = new boolean[CommonStatic.compteurClass][CommonStatic.compteurClass];
		// création des liens pour les extends
		if (classe.getSuperclass() != null) {
			if (map.get(classe.getSuperclass()) != null) {
				if (!alreadyFound[map.get(classe.getReference())][map.get(classe.getSuperclass())]) {

					JSONObject object = new JSONObject();
					try {
						object.put("from", map.get(classe.getReference()));
						object.put("to", map.get(classe.getSuperclass()));
						object.put("relationship", "generalization");

						relationship.put(object);
						alreadyFound[map.get(classe.getReference())][map.get(classe.getSuperclass())] = true;
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}