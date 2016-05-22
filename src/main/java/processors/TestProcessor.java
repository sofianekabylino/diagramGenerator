package processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

public class TestProcessor extends AbstractProcessor<CtClass>{

	@Override
	public void process(CtClass element) {

		System.out.println(element.getQualifiedName()+" ** "+element.getElements(new TypeFilter<CtMethod>((CtMethod.class)))); //element.getElements(new TypeFilter<CtClass<?>>(CtClass.class)).forEach(c -> System.out.println(c.getQualifiedName()));
		
		
		
	}

}
