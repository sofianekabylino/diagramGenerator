package processors;

import java.util.HashMap;
import java.util.HashSet;
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
import spoon.reflect.reference.CtTypeReference;

public class GenerateLinkBetweenClassesProcessor extends AbstractProcessor<CtClass> {

	public static JSONArray relationship = new JSONArray();
	public static boolean[][] alreadyFound;
	public static Set<Integer> listeClassesUtilisees = new HashSet<Integer>();
	
	@Override
	public void process(CtClass classe) {
		Map<CtTypeReference, Integer> map = AnalyseProcessorClasses.map;
		alreadyFound = new boolean[CommonStatic.compteurClass][CommonStatic.compteurClass];
		
		// On regarde les super classes
		if (classe.getSuperclass() != null) {

			if (map.get(classe.getSuperclass()) != null) {
				if (!alreadyFound[map.get(classe.getReference())][map.get(classe.getSuperclass())]) {
					listeClassesUtilisees.add(map.get(classe.getReference()));
					listeClassesUtilisees.add(map.get(map.get(classe.getSuperclass())));

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
		// On regarde les super interfaces
		if (classe.getSuperInterfaces() != null) {

			for (CtTypeReference ref : classe.getSuperInterfaces()) {
				if (map.get(ref) != null) {
					listeClassesUtilisees.add(map.get(classe.getReference()));
					listeClassesUtilisees.add(map.get(ref));

					/*System.out.println("**********");
					System.out.println(classe.getReference());
					System.out.println(ref);
					System.out.println("**********");*/
					if (!alreadyFound[map.get(classe.getReference())][map.get(ref)]) {

						JSONObject object = new JSONObject();
						try {
							object.put("from", map.get(classe.getReference()));
							object.put("to", map.get(ref));
							object.put("relationship", "implements");

							relationship.put(object);
							alreadyFound[map.get(classe.getReference())][map.get(ref)] = true;
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		// On regarde les attributs
		List<CtField> fields = classe.getFields();
		for (CtField attribut : fields) {
			if (attribut.getType() != null) {
				String type = attribut.getType().getSimpleName();
				// on regarde si c'est une collection
				if (type.equals("Map") || type.equals("List") || type.equals("Set") || type.equals("Collection")) {
					for (CtTypeReference r : attribut.getType().getActualTypeArguments()) {
						if (map.get(r) != null) {
							listeClassesUtilisees.add(map.get(classe.getReference()));
							listeClassesUtilisees.add(map.get(r));
							
							// on supprime l'attribut dans la classe
							//AnalyseProcessorClasses.array.;
							for (int i = 0; i < AnalyseProcessorClasses.array.length(); i++) {
								try {
									//System.out.println(AnalyseProcessorClasses.array.getJSONObject(i).get("key") + " " +  map.get(classe.getReference()));

									if ((Integer)AnalyseProcessorClasses.array.getJSONObject(i).get("key") == map.get(classe.getReference()).intValue()) {
										for(int j = 0 ; j < ((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")).length() ; j++){
											//System.out.println(((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")));

											if(((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")).getJSONObject(j).get("type") == attribut.getType()){
												((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")).remove(j);
											}
										}
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							JSONObject object = new JSONObject();
							try {
								object.put("from", map.get(classe.getReference()));
								object.put("to", map.get(r));
								object.put("relationship", "aggregation");
								object.put("text", "1..*");
								relationship.put(object);
								alreadyFound[map.get(classe.getReference())][map.get(r)] = true;
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}

				} else {
					if (map.get(attribut.getType()) != null) {
						listeClassesUtilisees.add(map.get(classe.getReference()));
						listeClassesUtilisees.add(map.get(attribut.getType()));

						// on supprime l'attribut dans la classe
						for (int i = 0; i < AnalyseProcessorClasses.array.length(); i++) {
							try {
								//System.out.println(AnalyseProcessorClasses.array.getJSONObject(i).get("key") + " " +  map.get(classe.getReference()));

								if ((Integer)AnalyseProcessorClasses.array.getJSONObject(i).get("key") == map.get(classe.getReference()).intValue()) {
									for(int j = 0 ; j < ((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")).length() ; j++){
										//System.out.println(((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")));

										if(((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")).getJSONObject(j).get("type") == attribut.getType()){
											((JSONArray)AnalyseProcessorClasses.array.getJSONObject(i).get("properties")).remove(j);
										}
									}
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						JSONObject object = new JSONObject();
						try {
							object.put("from", map.get(classe.getReference()));
							object.put("to", map.get(attribut.getType()));
							object.put("relationship", "association");
							object.put("text", attribut.getSimpleName());
							relationship.put(object);
							alreadyFound[map.get(classe.getReference())][map.get(attribut.getType())] = true;
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
