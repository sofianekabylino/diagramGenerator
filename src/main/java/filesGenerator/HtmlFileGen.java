package filesGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlFileGen {

	public static void htmlFileGen() {
		String fileContent = "<!DOCTYPE html>\n"
		        + "<html>\n"
		          + "<head>\n"
		            + "<meta charset=\"utf-8\">\n"
		            + "<title>diagram</title>\n"
		            + "<script type=\"text/javascript\" src=\"gojs/go.js\"></script>\n"
		          + "</head>\n"
		            + "<body>\n"
		              + "<div id=\"sample\">\n"
		                + "<div id=\"myDiagram\" style=\"background-color: whitesmoke; border: solid 1px black; width: 100%; height: 700px\">\n"
		                + "</div>\n"
		                + "<!-- la div de l'overview -->\n"
					
		              + "</div>\n"
		              +"<div id='myOverviewDiv' style='background-color: white; border: solid 1px black; height: 200px; width: 300px; padding-left: 20px;'>\n"
		              +"</div>\n"
		              + "<script src=\"packageDep.js\"></script>\n"
		            + "</body>\n"
		        + "</html>\n";
		
		File f = new File ("packageDepHtml.html");
	    try
	    {
	        PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f)));
	            pw.println (fileContent);
	        pw.close();
	    }
	    catch (IOException exception)
	    {
	        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
	    }
		
	}
}
