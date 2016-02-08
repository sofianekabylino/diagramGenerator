package diagramGen.diagramGen;

import processors.ClassProcessor;
import processors.ClassRelationsProcessor;
import processors.PackageProcess;
import processors.PackagesDependenciesProcess;
import spoon.Launcher;

public class Runner {

	public static void run(){
		final Launcher launcher = new Launcher();
    	final String[] arguments = { "-x", "-i", "/home/sofiane/Documents/Master/IAGL/OPL/jsoup-master/src/main/java" };
    	launcher.addProcessor(new PackageProcess());
    	launcher.addProcessor(new PackagesDependenciesProcess());
    	launcher.addProcessor(new ClassProcessor());
    	launcher.addProcessor(new ClassRelationsProcessor());
    	launcher.run(arguments);
    	
    	System.out.println(ClassProcessor.array); // JSON avec les infos pour chaque classe
        System.out.println(ClassRelationsProcessor.relationship); // JSON avec les liens entre les classes
	}
}
