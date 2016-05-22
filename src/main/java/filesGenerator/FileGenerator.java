package filesGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import diagramGen.diagramGen.JSONArrayCreator;
import processors.AnalyseProcessorClasses;
import processors.GenerateLinkBetweenClassesProcessor;

/**
 * FileGenerator class<br>
 * Generate html, js files
 * 
 * @author sofianekabylino
 *
 */
public class FileGenerator {

	/**
	 * generate the html file
	 */
	public static void generateHtmlFile() {
		
		final String fileContent = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"utf-8\">\n"
				+ "<title>diagram</title>\n" + "<script type=\"text/javascript\" src=\"gojs/go.js\"></script>\n"
				+ "</head>\n" + "<body>\n" + "<div id=\"sample\">\n"
				+ "<div id=\"myDiagram\" style=\"background-color: whitesmoke; border: solid 1px black; width: 100%; height: 1000px\">\n"
				+ "</div>\n" + "<!-- la div de l'overview -->\n"

				+ "</div>\n"
				+ "<div id='myOverviewDiv' style='background-color: white; border: solid 1px black; height: 200px; width: 300px; padding-left: 20px;'>\n"
				+ "</div>\n" + "<script src=\"classDep.js\"></script>\n" + "</body>\n" + "</html>\n";

		createFile(fileContent, "packageDepHtml.html");

	}

	/**
	 * generate the class diagram js file
	 */
	public static void generateClassDiagramJsFile() {

		final String fileContent = "function init() {\n" + "    var $ = go.GraphObject.make;\n" + "    myDiagram =\n"
				+ "$(go.Diagram, 'myDiagram',  // must name or refer to the DIV HTML element\n" + "{\n"
				+ "initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit\n"
				+ "contentAlignment: go.Spot.Center,  // align document to the center of the viewport\n" + "layout:\n"
				+ "$(go.ForceDirectedLayout,  // automatically spread nodes apart\n"
				+ "{ defaultSpringLength: 30, defaultElectricalCharge: 100 })\n" + "});\n"
				+ "// define each Node's appearance\n" + "myDiagram.nodeTemplate =\n"
				+ "$(go.Node, 'Auto',  // the whole node panel\n"
				+ "// define the node's outer shape, which will surround the TextBlock\n" + "$(go.Shape, 'Rectangle',\n"
				+ "{ fill: $(go.Brush, 'Linear', { 0: 'rgb(254, 201, 0)', 1: 'rgb(254, 162, 0)' }), stroke: 'black' }),\n"
				+ "$(go.TextBlock,\n" + "{ font: 'bold 10pt helvetica, bold arial, sans-serif', margin: 4 },\n"
				+ "new go.Binding('text', 'text'))\n" + ");\n"
				+ "    // show visibility or access as a single character at the beginning of each property or method\n"
				+ "    function convertVisibility(v) {\n" + "      switch (v) {\n"
				+ "        case 'public': return '+';\n" + "        case 'private': return '-';\n"
				+ "        case 'protected': return '#';\n" + "        case 'package': return '~';\n"
				+ "        default: return v;\n" + "      }\n" + "    }\n" + "    // the item template for properties\n"
				+ "    var propertyTemplate =\n" + "      $(go.Panel, 'Horizontal',\n"
				+ "        // property visibility/access\n" + "        $(go.TextBlock,\n"
				+ "{ isMultiline: false, editable: false, width: 12 },\n"
				+ "new go.Binding('text', 'visibility', convertVisibility)),\n"
				+ "// property name, underlined if scope=='class' to indicate static property\n" + "$(go.TextBlock,\n"
				+ "{ isMultiline: false, editable: true },\n" + "new go.Binding('text', 'name').makeTwoWay(),\n"
				+ "new go.Binding('isUnderline', 'scope', function(s) { return s[0] === 'c' })),\n"
				+ "// property type, if known\n" + "$(go.TextBlock, '',\n"
				+ "new go.Binding('text', 'type', function(t) { return (t ? ': ' : ''); })),\n" + "$(go.TextBlock,\n"
				+ "{ isMultiline: false, editable: true },\n" + "new go.Binding('text', 'type').makeTwoWay()),\n"
				+ "// property default value, if any\n" + "$(go.TextBlock,\n"
				+ "{ isMultiline: false, editable: false },\n"
				+ "new go.Binding('text', 'default', function(s) { return s ? ' = ' + s : ''; }))\n" + ");\n"
				+ "// the item template for methods\n" + "var methodTemplate =\n" + "$(go.Panel, 'Horizontal',\n"
				+ "// method visibility/access\n" + "$(go.TextBlock,\n"
				+ "{ isMultiline: false, editable: false, width: 12 },\n"
				+ "new go.Binding('text', 'visibility', convertVisibility)),\n"
				+ "// method name, underlined if scope=='class' to indicate static method\n" + "$(go.TextBlock,\n"
				+ "{ isMultiline: false, editable: true },\n" + "new go.Binding('text', 'name').makeTwoWay(),\n"
				+ "new go.Binding('isUnderline', 'scope', function(s) { return s[0] === 'c' })),\n"
				+ "// method parameters\n" + "$(go.TextBlock, '()',\n"
				+ "// this does not permit adding/editing/removing of parameters via inplace edits\n"
				+ "new go.Binding('text', 'parameters', function(parr) {\n" + "var s = '(';\n"
				+ "for (var i = 0; i < parr.length; i++) {\n" + "var param = parr[i];\n" + "if (i > 0) s += ', ';\n"
				+ "s += param.name + ': ' + param.type;\n" + "}\n" + "return s + ')';\n" + "})),\n"
				+ "// method return type, if any\n" + "$(go.TextBlock, '',\n"
				+ "new go.Binding('text', 'type', function(t) { return (t ? ': ' : ''); })),\n" + "$(go.TextBlock,\n"
				+ "{ isMultiline: false, editable: true },\n" + "new go.Binding('text', 'type').makeTwoWay())\n"
				+ ");\n" + "// this simple template does not have any buttons to permit adding or\n"
				+ "// removing properties or methods, but it could!\n" + "myDiagram.nodeTemplate =\n"
				+ "$(go.Node, 'Auto',\n" + "{\n" + "locationSpot: go.Spot.Center,\n" + "fromSpot: go.Spot.AllSides,\n"
				+ "toSpot: go.Spot.AllSides\n" + "},\n" + "$(go.Shape, { fill: 'lightyellow' }),\n"
				+ "$(go.Panel, 'Table',\n" + "{ defaultRowSeparatorStroke: 'black' },\n" + "// header\n"
				+ "$(go.TextBlock,\n" + "{\n" + "row: 0, margin: 3, alignment: go.Spot.Center,\n"
				+ "font: 'bold 12pt sans-serif',\n" + "isMultiline: false, editable: true\n" + "},\n"
				+ "new go.Binding('text', 'name').makeTwoWay()),\n" + "// properties\n" + "$(go.Panel, 'Vertical',\n"
				+ "new go.Binding('itemArray', 'properties'),\n" + "{\n"
				+ "row: 1, margin: 3, alignment: go.Spot.Left,\n" + "defaultAlignment: go.Spot.Left,\n"
				+ "itemTemplate: propertyTemplate\n" + "}\n" + "),\n" + "// methods\n" + "$(go.Panel, 'Vertical',\n"
				+ "new go.Binding('itemArray', 'methods'),\n" + "{\n" + "row: 2, margin: 3, alignment: go.Spot.Left,\n"
				+ "defaultAlignment: go.Spot.Left,\n" + "itemTemplate: methodTemplate\n" + "}\n" + "))\n" + ");\n"
				+ "function convertIsTreeLink(r) {\n" + "return r === 'generalization';\n" + "}\n"
				+ "function convertFromArrow(r) {\n" + "switch (r) {\n" + "case 'generalization': return '';\n"
				+ "default: return '';\n" + "}\n" + "}\n" + "function convertToArrow(r) {\n" + "switch (r) {\n"
				+ "case 'generalization': return 'Triangle';\n" + "//case 'aggregation': return 'StretchedDiamond';\n"
				+ "case 'aggregation': return 'OpenTriangle';\n" + "case 'association': return 'OpenTriangle';\n"
				+ "case 'implements': return 'OpenTriangle';\n" + "default: return '';\n" + "}\n" + "}\n"

				+ "function convertLink(r){\n" + "switch (r) {\n" + "case 'implements' : return [5,10];\n" + "}\n"
				+ "}\n"

				+ "myDiagram.linkTemplate =\n" + "$(go.Link,\n" + "{ routing: go.Link.AvoidsNodes },\n"
				+ "new go.Binding('isLayoutPositioned', 'relationship', convertIsTreeLink),\n"
				+ "$(go.Shape,{stroke:'black'},\n"
				+ "new go.Binding('strokeDashArray' , 'relationship', convertLink)),\n"
				+ "$(go.Shape, { scale: 1.3, fill: 'white' },\n"
				+ "new go.Binding('fromArrow', 'relationship', convertFromArrow)),\n"
				+ "$(go.Shape, { scale: 1.3, fill: 'white' },\n"
				+ "new go.Binding('toArrow', 'relationship', convertToArrow)),\n" + "$(go.TextBlock,\n"
				+ "{ segmentIndex: -1, segmentOffset: new go.Point(NaN, NaN),\n"
				+ "segmentOrientation: go.Link.OrientUpright },\n" + "new go.Binding('text', 'text'))\n" + "\n" + ");\n"
				+ "// setup a few example class nodes and relationships\n" + "var nodedata = "
				+ AnalyseProcessorClasses.array + " \n" + ";\n"

				+ "var linkdata = " + GenerateLinkBetweenClassesProcessor.relationship + " \n" + ";\n" + "\n"
				+ "myDiagram.model = $(go.GraphLinksModel,\n" + "{\n" + "copiesArrays: true,\n"
				+ "copiesArrayObjects: true,\n" + "nodeDataArray: nodedata,\n" + "linkDataArray: linkdata\n" + "});\n"
				+ "var myOverview = $(go.Overview, 'myOverviewDiv',{ observed: myDiagram });" + "}\n" + "init();";
		// Ecriture du fichier Js
		createFile(fileContent, "classDep.js");

	}

	/**
	 * generate the package dependencies js file
	 */
	public static void generatePackageDependenciesJsFile() {

		final String fileContent = "function init() {\n"
				+ "if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this\n"
				+ "var $ = go.GraphObject.make;  // for conciseness in defining templates\n" + "myDiagram =\n"
				+ "$(go.Diagram, \"myDiagram\",  // must name or refer to the DIV HTML element\n" + "{\n"
				+ "initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit\n"
				+ "contentAlignment: go.Spot.Center,  // align document to the center of the viewport\n" + "layout:\n"
				+ "$(go.ForceDirectedLayout,  // automatically spread nodes apart\n"
				+ "{ defaultSpringLength: 200, defaultElectricalCharge: 100 })\n" + "});\n"
				+ "// define each Node's appearance\n" + "myDiagram.nodeTemplate =\n"
				+ "$(go.Node, \"Auto\",  // the whole node panel\n"
				+ "// define the node's outer shape, which will surround the TextBlock\n"
				+ "$(go.Shape, \"Rectangle\",\n"
				+ "{ fill: $(go.Brush, \"Linear\", { 0: \"rgb(254, 201, 0)\", 1: \"rgb(254, 162, 0)\" }), stroke: \"black\" }),\n"
				+ "$(go.TextBlock,\n" + "{ font: \"bold 10pt helvetica, bold arial, sans-serif\", margin: 4 },\n"
				+ "new go.Binding(\"text\", \"text\"))\n" + ");\n"
				+ "// replace the default Link template in the linkTemplateMap\n" + "myDiagram.linkTemplate =\n"
				+ "$(go.Link,  // the whole link panel\n" + "{ \n" + "curve: go.Link.Bezier},\n"
				+ "$(go.Shape,  // the link shape\n" + "{ stroke: 'black', strokeDashArray: [5,10]  }),\n"
				+ "$(go.Shape,  // the arrowhead\n" + "{ toArrow: \"standard\", stroke: null }),\n"
				+ "$(go.Panel, \"Auto\",\n" + "$(go.Shape,  // the link shape\n"
				+ "{ fill: $(go.Brush, \"Radial\", { 0: \"rgb(240, 240, 240)\", 0.3: \"rgb(240, 240, 240)\", 1: \"rgba(240, 240, 240, 0)\" }),\n"
				+ "stroke: null }),\n" + "$(go.TextBlock,  // the label\n" + "{ textAlign: \"center\",\n"
				+ "font: \"10pt helvetica, arial, sans-seri\",\n" + "stroke: \"#555555\",\n" + "margin: 4 })\n" + ")\n"
				+ ");\n"

				+ "// create the model for the concept map\n" + "var nodeDataArray =" + JSONArrayCreator.jaPackages
				+ " ;\n" + " var linkDataArray = " + JSONArrayCreator.jaDependencies + ";\n"
				+ "myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);\n"
				+ "var myOverview = $(go.Overview, 'myOverviewDiv',{ observed: myDiagram });" + "}\n" + "init();";
		// Ecriture du fichier Js
		createFile(fileContent, "packageDep.js");

	}
	
	/**
	 * generate file
	 * @param fileContent
	 */
	private static void createFile(final String fileContent, final String fileName) {
		File f = new File(fileName);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			pw.println(fileContent);
			pw.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : " + exception.getMessage());
		}
	}
}
