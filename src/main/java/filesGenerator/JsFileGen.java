package filesGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import diagramGen.diagramGen.JSONArrayCreator;

public class JsFileGen {

  public static void jsFileGen(){
    
    String fileContent = "function init() {\n"
    	    +"if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this\n"
    	    +"var $ = go.GraphObject.make;  // for conciseness in defining templates\n"
    	    +"myDiagram =\n"
    	      +"$(go.Diagram, \"myDiagram\",  // must name or refer to the DIV HTML element\n"
    	        +"{\n"
    	          +"initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit\n"
    	          +"contentAlignment: go.Spot.Center,  // align document to the center of the viewport\n"
    	          +"layout:\n"
    	            +"$(go.ForceDirectedLayout,  // automatically spread nodes apart\n"
    	              +"{ defaultSpringLength: 500, defaultElectricalCharge: 100 })\n"
    	        +"});\n"
    	    +"// define each Node's appearance\n"
    	    +"myDiagram.nodeTemplate =\n"
    	      +"$(go.Node, \"Auto\",  // the whole node panel\n"
    	        +"// define the node's outer shape, which will surround the TextBlock\n"
    	        +"$(go.Shape, \"Rectangle\",\n"
    	          +"{ fill: $(go.Brush, \"Linear\", { 0: \"rgb(254, 201, 0)\", 1: \"rgb(254, 162, 0)\" }), stroke: \"black\" }),\n"
    	        +"$(go.TextBlock,\n"
    	          +"{ font: \"bold 10pt helvetica, bold arial, sans-serif\", margin: 4 },\n"
    	          +"new go.Binding(\"text\", \"text\"))\n"
    	      +");\n"
    	    +"// replace the default Link template in the linkTemplateMap\n"
    	    +"myDiagram.linkTemplate =\n"
    	      +"$(go.Link,  // the whole link panel\n"
    	      + "{ \n"
  	        	+ "curve: go.Link.Bezier},\n"
    	        +"$(go.Shape,  // the link shape\n"
    	          +"{ stroke: \"black\" }),\n"
    	        +"$(go.Shape,  // the arrowhead\n"
    	          +"{ toArrow: \"standard\", stroke: null }),\n"
    	        +"$(go.Panel, \"Auto\",\n"
    	          +"$(go.Shape,  // the link shape\n"
    	            +"{ fill: $(go.Brush, \"Radial\", { 0: \"rgb(240, 240, 240)\", 0.3: \"rgb(240, 240, 240)\", 1: \"rgba(240, 240, 240, 0)\" }),\n"
    	              +"stroke: null }),\n"
    	          +"$(go.TextBlock,  // the label\n"
    	            +"{ textAlign: \"center\",\n"
    	              +"font: \"10pt helvetica, arial, sans-seri\",\n"
    	              +"stroke: \"#555555\",\n"
    	              +"margin: 4 },\n"
    	            +"new go.Binding(\"text\", \"text\"))\n"
    	        +")\n"
    	        + ");\n"
    	      
    +"// create the model for the concept map\n"
    +"var nodeDataArray ="+JSONArrayCreator.jaPackages+" ;\n"
   +" var linkDataArray = "+JSONArrayCreator.jaDependencies+";\n"
   +"myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);\n"
  +"}\n"
+"init();";
    
    File f = new File ("packageDep.js");
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

