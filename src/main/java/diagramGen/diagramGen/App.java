package diagramGen.diagramGen;

import java.net.URISyntaxException;

import filesGenerator.FileGenerator;

/**
 * Main class
 * @author sofianekabylino
 *
 */
public class App {
	
	/**
	 * main
	 * @param args
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws InterruptedException, URISyntaxException {

		Runner.run();
		
		final JSONArrayCreator j = new JSONArrayCreator();
		j.createJsonArray();

		// Génération des fichiers
		FileGenerator.generatePackageDependenciesJsFile();
		FileGenerator.generateClassDiagramJsFile();;
		FileGenerator.generateHtmlFile();

		BrowserLauncher.launchBrowser();

	}
}
