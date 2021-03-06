package processors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import diagramGen.diagramGen.CommonStatic;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class PackageProcess extends AbstractProcessor<CtPackage> {

	public static Set<String> lst = new HashSet<String>();

	public void process(CtPackage element) {

		//System.out.println(element.getQualifiedName()+" ** "+element.getElements(new TypeFilter<CtPackage>(CtPackage.class)).size()); //element.getElements(new TypeFilter<CtClass<?>>(CtClass.class)).forEach(c -> System.out.println(c.getQualifiedName()));
		// element.getElements(new TypeFilter<CtClass<?>>(CtClass.class)).forEach(c -> System.out.println(c.getQualifiedName()));
		if (!(element.getElements(new TypeFilter<CtPackage>(CtPackage.class)).size() > 1) && (element.getElements(new TypeFilter<CtClass<?>>(CtClass.class)).size() > 0)) {
			String packag = element.getQualifiedName();
			if (!packag.equals("") && !CommonStatic.MAP_PACKAGE.containsKey(packag)) {
				CommonStatic.MAP_PACKAGE.put(packag, CommonStatic.COMPTEUR_PACKAGE);
			}

			CommonStatic.COMPTEUR_PACKAGE++;
			int k = CommonStatic.COMPTEUR_PACKAGE;
			for (String str : CommonStatic.MAP_PACKAGE.keySet()) {
				if (element.getSimpleName().contains(str)) {
					for (CtTypeReference t : element.getReferencedTypes()) {
						if (!t.isPrimitive() && t.getPackage() != null
								&& CommonStatic.MAP_PACKAGE.containsKey(t.getPackage().getSimpleName())) {
							lst.add(t.getPackage().getSimpleName());
						}
					}
				}
			}
			CommonStatic.COMPTEUR_PACKAGE = k;

			Iterator<String> i = PackageProcess.lst.iterator();
			while (i.hasNext()) {
				CommonStatic.MAP_PACKAGE.put(i.next(), CommonStatic.COMPTEUR_PACKAGE++);
			}
		}
	}
}
