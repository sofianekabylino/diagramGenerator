package processors;

import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import diagramGen.diagramGen.CommonStatic;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;

public class AnalyseProcessorInterfaces extends AbstractProcessor<CtInterface> {

	public void process(CtInterface inter) {
		
		 //System.out.println("************" + inter.getSimpleName() + "*************");

			List<CtField> fields = inter.getFields();
			Set<CtMethod> methods = inter.getMethods();
			JSONObject json = new JSONObject();
			try {
				json.put("key", CommonStatic.compteurClass);
				json.put("name", inter.getSimpleName());
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
				
				AnalyseProcessorClasses.array.put(json);
				CommonStatic.mapClasses.put(inter.getReference(),CommonStatic.compteurClass);
				CommonStatic.compteurClass++;
			} catch (JSONException e) {
				e.printStackTrace();
			}	}

}
