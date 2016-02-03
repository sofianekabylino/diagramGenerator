package processors;
import java.util.HashSet;
import java.util.Set;

import diagramGen.diagramGen.Pack;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.reference.CtTypeReference;

public class PackageProcess extends AbstractProcessor<CtPackage> {

	public static Set<String> lst = new HashSet<String>();

	public void process(CtPackage element) {

		String packag = element.getQualifiedName();
		if (!Pack.mapPackage.containsValue(packag)) {
			Pack.mapPackage.put(Pack.key, packag);
		}
		Pack.key++;
		int k = Pack.key;
		for (Integer str : Pack.mapPackage.keySet()) {
			if (element.getSimpleName().contains(Pack.mapPackage.get(str))) {
				for (CtTypeReference t : element.getReferencedTypes()) {
					if (!t.isPrimitive() && t.getPackage() != null
							&& !Pack.mapPackage.containsValue(t.getPackage().getSimpleName())) {
						lst.add(t.getPackage().getSimpleName());
					}
				}
			}
		}
		Pack.key = k;
	}

}
