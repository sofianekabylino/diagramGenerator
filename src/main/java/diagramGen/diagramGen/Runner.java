package diagramGen.diagramGen;

import processors.AnalyseProcessorClasses;
import processors.AnalyseProcessorInterfaces;
import processors.GenerateLinkBetweenClassesProcessor;
import processors.PackageProcess;
import processors.PackagesDependenciesProcess;
import spoon.Launcher;

public class Runner {

	public static void run(){
		final Launcher launcher = new Launcher();
    	final String[] arguments = { "-x", "-i", "/home/sofiane/Documents/Master/IAGL/OPL/jsoup-master/src/main/java" };
    	launcher.addProcessor(new PackageProcess());
    	launcher.addProcessor(new PackagesDependenciesProcess());
    	launcher.addProcessor(new AnalyseProcessorClasses());
    	launcher.addProcessor(new AnalyseProcessorInterfaces());
    	launcher.addProcessor(new GenerateLinkBetweenClassesProcessor());
    	launcher.run(arguments);
    	
    	System.out.println(AnalyseProcessorClasses.array); // JSON avec les infos pour chaque classe
        System.out.println(GenerateLinkBetweenClassesProcessor.relationship); // JSON avec les liens entre les classes
	}
}
