package processors;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import diagramGen.diagramGen.CommonStatic;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.reference.CtTypeReference;

public class PackageProcess extends AbstractProcessor<CtPackage> {

	public static Set<String> lst = new HashSet<String>();

	public void process(CtPackage element) {

		String packag = element.getQualifiedName();
		if (!packag.equals("") && !CommonStatic.mapPackage.containsKey(packag)) {
			CommonStatic.mapPackage.put(packag, CommonStatic.key);
		}
		CommonStatic.key++;
		int k = CommonStatic.key;
		for (String str : CommonStatic.mapPackage.keySet()) {
			if (element.getSimpleName().contains(str)) {
				for (CtTypeReference t : element.getReferencedTypes()) {
					if (!t.isPrimitive() && t.getPackage() != null
							&& CommonStatic.mapPackage.containsKey(t.getPackage().getSimpleName())) {
						lst.add(t.getPackage().getSimpleName());
					}
				}
			}
		}
		CommonStatic.key = k;
		
		Iterator<String> i = PackageProcess.lst.iterator();
		while (i.hasNext()) {
			CommonStatic.mapPackage.put(i.next(), CommonStatic.key++);
		}
		
	}

}
