package diagramGen.diagramGen;

import java.net.URISyntaxException;

import filesGenerator.HtmlFileGen;
import filesGenerator.JsFileGen;
import spoon.reflect.reference.CtTypeReference;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException, URISyntaxException {

		Runner.run();

		for (Integer str : Pack.mapDependencies.keySet()) {
			System.out.println(Pack.mapDependencies.get(str)+" *** TO ***"+Pack.mapDependencies.values().size());
		}
		
		JSONArrayCreator j = new JSONArrayCreator();
		j.createJsonArray();

		String htmlFile = "";
		String JsFile = "";

		HtmlFileGen.htmlFileGen();
		JsFileGen.jsFileGen();

//		BrowserLauncher.launchBrowser();

	}
}
