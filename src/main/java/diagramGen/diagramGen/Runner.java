package diagramGen.diagramGen;

import processors.PackageProcess;
import processors.PackagesDependenciesProcess;
import spoon.Launcher;

public class Runner {

	public static void run(){
		final Launcher launcher = new Launcher();
    	final String[] arguments = { "-x", "-i", "/home/sofiane/Documents/Master/IAGL/OPL/jsoup-master/src/main/java" };
    	launcher.addProcessor(new PackageProcess());
    	launcher.addProcessor(new PackagesDependenciesProcess());
    	launcher.run(arguments);
	}
}
