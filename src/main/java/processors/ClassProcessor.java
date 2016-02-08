package processors;

import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import diagramGen.diagramGen.CommonStatic;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;

public class ClassProcessor extends AbstractProcessor<CtClass> {
	public static JSONArray array = new JSONArray();
	

	public void process(CtClass classe) {
		// System.out.println("************" + classe.getSimpleName() +
		// "*************");

		List<CtField> fields = classe.getFields();
		for (CtField field : fields) {
			// System.out.println(field.getType() + " " +
			// field.getSimpleName());
		}
		Set<CtMethod> methods = classe.getMethods();

		for (CtMethod method : methods) {
			// System.out.println(method.getSimpleName());
		}
		JSONObject json = new JSONObject();
		try {
			json.put("key", CommonStatic.compteurClass);
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
			CommonStatic.mapClasses.put(classe.getReference(), CommonStatic.compteurClass);
			CommonStatic.compteurClass++;
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}