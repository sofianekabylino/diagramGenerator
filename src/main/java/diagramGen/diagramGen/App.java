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
		
		for (String str : CommonStatic.mapPackage.keySet()) {
			System.out.println(CommonStatic.mapPackage.get(str)+" ** "+str);
		}
		
		JSONArrayCreator j = new JSONArrayCreator();
		j.createJsonArray();
		
		
		
		String htmlFile = "";
		String JsFile = "";

		HtmlFileGen.htmlFileGen();
		JsFileGen.jsFileGen();

		System.out.println(JSONArrayCreator.jaPackages);
		System.out.println(JSONArrayCreator.jaDependencies);
		BrowserLauncher.launchBrowser();

	}
}
