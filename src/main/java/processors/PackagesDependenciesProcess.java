package processors;

import java.util.LinkedList;
import java.util.List;

import diagramGen.diagramGen.Pack;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.reference.CtTypeReference;

public class PackagesDependenciesProcess extends AbstractProcessor<CtPackage> {

	static List<Integer> lstDep = new LinkedList<Integer>();
	public void process(CtPackage element) {
		
		String packag = element.getSimpleName();
		
		for (Integer str : Pack.mapPackage.keySet()) {
		if (packag.contains(Pack.mapPackage.get(str))) {
			for (CtTypeReference t : element.getReferencedTypes()) {
				if (!t.isPrimitive() && t.getPackage() != null) {
					for (Integer i : Pack.mapPackage.keySet()) {
						if (Pack.mapPackage.containsValue(t.getPackage().getSimpleName())) {
							lstDep.add(i);
					    }
					}
				}
			}
			Pack.mapDependencies.put(str, lstDep);
//			System.out.println(Pack.mapDependencies.size());
		}
		}
	}

	
}
