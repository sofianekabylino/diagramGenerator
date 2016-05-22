package processors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import diagramGen.diagramGen.CommonStatic;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;

public class AnalyseProcessorClasses extends AbstractProcessor<CtClass> {

	public static JSONArray array = new JSONArray();
	public static Map<CtTypeReference, Integer> map = new HashMap<CtTypeReference, Integer>();

	public void process(CtClass classe) {
		if (!classe.isAnonymous()) {
			//System.out.println("************" + classe.getSimpleName() + "*************");

			List<CtField> fields = classe.getFields();
			Set<CtMethod> methods = classe.getMethods();
			JSONObject json = new JSONObject();
			try {
				json.put("key", CommonStatic.COMPTEUR_CLASS);
				json.put("name", classe.getSimpleName());
				JSONArray properties = new JSONArray();
				for (CtField field : fields) {
					JSONObject tmp = new JSONObject();
					tmp.put("name", field.getSimpleName());
					tmp.put("type", field.getType());
					tmp.put("visibility", field.getVisibility());
					properties.put(tmp);
				}

				json.put("properties", properties);

				JSONArray methodsJson = new JSONArray();
				for (CtMethod method : methods) {
					JSONObject tmp = new JSONObject();
					tmp.put("name", method.getSimpleName());
					tmp.put("type", method.getType());
					tmp.put("visibility", method.getVisibility());
					methodsJson.put(tmp);
				}

				json.put("methods", methodsJson);

				array.put(json);
				map.put(classe.getReference(), CommonStatic.COMPTEUR_CLASS);
				CommonStatic.COMPTEUR_CLASS++;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}