package diagramGen.diagramGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import processors.AnalyseProcessorClasses;
import processors.GenerateLinkBetweenClassesProcessor;
import spoon.reflect.reference.CtTypeReference;

public class AmeliorationJSONs {

	public static JSONArray last = new JSONArray();

	public static void supprimerClasseSeul() throws JSONException {
		Set<CtTypeReference> key = CommonStatic.mapClasses.keySet();
		List<Integer> aSupprimer = new ArrayList<Integer>();

		for (CtTypeReference k : key) {
			int val = CommonStatic.mapClasses.get(k);
			if (!GenerateLinkBetweenClassesProcessor.listeClassesUtilisees.contains(val)) {
				//System.out.println("On peut enlever la classe : " + k);
				aSupprimer.add(CommonStatic.mapClasses.get(k));
			}
		}

		// on supprimer dans l'objet JSON les classes qui n'ont aucun lien
		for (int i : aSupprimer) {
			for (int j = 0; j < AnalyseProcessorClasses.array.length(); j++) {
				if ((Integer)AnalyseProcessorClasses.array.getJSONObject(j).get("key") == i) {
					//System.out.println( i + " " + AnalyseProcessorClasses.array.getJSONObject(j).get("key"));
					AnalyseProcessorClasses.array.remove(j);
				}
			}
		}
	}
}
