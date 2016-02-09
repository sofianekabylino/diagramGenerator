package diagramGen.diagramGen;

import processors.AnalyseProcessorClasses;
import processors.AnalyseProcessorInterfaces;
import processors.GenerateLinkBetweenClassesProcessor;
import processors.PackageProcess;
import processors.PackagesDependenciesProcess;
import spoon.Launcher;

public class Runner {

	/**
	 * jsoup :
	 * /home/sofiane/Documents/Master/IAGL/OPL/jsoup-master/src/main/java
	 * commons-math :
	 * /home/sofiane/Documents/Master/IAGL/OPL/commons-math-master/src/main/java
	 * joda-time :
	 * /home/sofiane/Documents/Master/IAGL/OPL/joda-time-master/src/main/java
	 */
	public static void run() {
		final Launcher launcher = new Launcher();
		final String[] arguments = { "-x", "-i", "/home/sofiane/Documents/Master/IAGL/OPL/simulateur_billes-master/src/tp1_idl" };
		launcher.addProcessor(new PackageProcess());
		launcher.addProcessor(new PackagesDependenciesProcess());
		launcher.addProcessor(new AnalyseProcessorClasses());
		launcher.addProcessor(new AnalyseProcessorInterfaces());
		launcher.addProcessor(new GenerateLinkBetweenClassesProcessor());
		launcher.run(arguments);

		// System.out.println(AnalyseProcessorClasses.array); // JSON avec les
		// infos pour chaque classe
		// System.out.println(GenerateLinkBetweenClassesProcessor.relationship);
		// // JSON avec les liens entre les classes
	}
}
