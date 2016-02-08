package diagramGen.diagramGen;

import java.net.URISyntaxException;

import filesGenerator.HtmlFileGen;
import filesGenerator.JsClassDiagramFileGen;
import filesGenerator.JsFileGen;
import spoon.reflect.reference.CtTypeReference;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException, URISyntaxException {

		Runner.run();
		
		JSONArrayCreator j = new JSONArrayCreator();
		j.createJsonArray();
		
		
		
		String htmlFile = "";
		String JsFile = "";

		// Génération des fichiers
		JsFileGen.jsFileGen();
		JsClassDiagramFileGen.jsClassDiagramFileGen();
		HtmlFileGen.htmlFileGen();

//		System.out.println(JSONArrayCreator.jaPackages);
//		System.out.println(JSONArrayCreator.jaDependencies);
		BrowserLauncher.launchBrowser();

	}
}
