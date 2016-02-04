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

		for (String str : CommonStatic.mapPackage.keySet()) {

			if (packag.contains(str)) {

				for (CtTypeReference t : element.getReferencedTypes()) {

					if (!t.isPrimitive() && t.getPackage() != null
							&& CommonStatic.mapPackage.containsKey(t.getPackage().getSimpleName())) {

						if (!str.equals(t.getPackage().getSimpleName())) {
							List<Integer> lstDep = new LinkedList<Integer>();
							lstDep.add(CommonStatic.mapPackage.get(str));
							lstDep.add(CommonStatic.mapPackage.get(t.getPackage().getSimpleName()));
							if (!CommonStatic.mapDependencies.contains(lstDep)) {
								CommonStatic.mapDependencies.add(lstDep);
							}

						}
					}
				}
			}
		}
	}
}
