package diagramGen.diagramGen;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class BrowserLauncher {

	public static void launchBrowser() {
		if (Desktop.isDesktopSupported()) {

			Desktop desktop = Desktop.getDesktop();

			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.open(new File("packageDepHtml.html"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
