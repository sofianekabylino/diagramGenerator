package processors;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import diagramGen.diagramGen.CommonStatic;
import diagramGen.diagramGen.JSONArrayCreator;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.reference.CtTypeReference;

public class PackagesDependenciesProcess extends AbstractProcessor<CtPackage> {

	public void process(CtPackage element) {

		String packag = element.getQualifiedName();

		for (String str : CommonStatic.MAP_PACKAGE.keySet()) {
			if (packag.contains(str)) {
				// liste des type réferencés par chaque classe
				for (CtTypeReference t : element.getReferencedTypes()) {
					if (!t.isPrimitive() && t.getPackage() != null
							&& CommonStatic.MAP_PACKAGE.containsKey(t.getPackage().getSimpleName())) {
						if (!str.equals(t.getPackage().getSimpleName())) {
							List<Integer> lstDep = new LinkedList<Integer>();
							lstDep.add(CommonStatic.MAP_PACKAGE.get(str));
							lstDep.add(CommonStatic.MAP_PACKAGE.get(t.getPackage().getSimpleName()));
							if (!CommonStatic.MAP_DEPENDENCIES.contains(lstDep)) {
								CommonStatic.MAP_DEPENDENCIES.add(lstDep);
							}
						}
					}
				}
			}
		}
	}
}
