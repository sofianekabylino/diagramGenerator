package filesGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import diagramGen.diagramGen.JSONArrayCreator;

public class JsFileGen {

  public static void jsFileGen(){
    
    String fileContent = "// This variation on ForceDirectedLayout does not move any selected Nodes but does move all other nodes (vertexes).\n"+
  "function ContinuousForceDirectedLayout() {\n"
    +"go.ForceDirectedLayout.call(this);\n"
    +"this._isObserving = false;\n"
  +"}\n"
  +"go.Diagram.inherit(ContinuousForceDirectedLayout, go.ForceDirectedLayout);\n"
  +"/** @override */\n"
  +"ContinuousForceDirectedLayout.prototype.isFixed = function(v) {\n"
    +"return v.node.isSelected;\n"
  +"}\n"
  +"// optimization: reuse the ForceDirectedNetwork rather than re-create it each time\n"
  +"/** @override */\n"
  +"ContinuousForceDirectedLayout.prototype.doLayout = function(coll) {\n"
    +"if (!this._isObserving) {\n"
      +"this._isObserving = true;\n"
      +"// cacheing the network means we need to recreate it if nodes or links have been added or removed or relinked, so we need to track structural model changes to discard the saved network.\n"
      +"var lay = this;\n"
      +"this.diagram.model.addChangedListener(function (e) {\n"
        +"// modelChanges include a few cases that we don't actually care about, such as \"nodeCategory\" or \"linkToPortId\", but we'll go ahead and recreate the network anyway\n"
        +"if (e.modelChange !== \"\") lay.network = null;\n"
      +"});\n"
    +"}\n"
    +"var net = this.network;\n"
    +"if (net === null) {  // the first time, just create the network as normal\n"
      +"this.network = net = this.makeNetwork(coll);\n"
    +"} else {  // but on reuse we need to update the LayoutVertex.bounds for selected nodes\n"
      +"this.diagram.nodes.each(function (n) {\n"
        +"var v = net.findVertex(n);\n"
        +"if (v !== null) v.bounds = n.actualBounds;\n"
      +"});\n"
    +"}\n"
    +"// now perform the normal layout\n"
    +"go.ForceDirectedLayout.prototype.doLayout.call(this, coll);\n"
    +"// doLayout normally discards the LayoutNetwork by setting Layout.network to null; here we remember it for next time\n"
    +"this.network = net;\n"
  +"}\n"
  +"// end ContinuousForceDirectedLayout\n"
  +"function init() {\n"
    +"if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this\n"
    +"var $ = go.GraphObject.make;  // for conciseness in defining templates\n"
    +"myDiagram =\n"
      +"$(go.Diagram, \"myDiagram\",  // must name or refer to the DIV HTML element\n"
        +"{\n"
          +"initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit\n"
          +"contentAlignment: go.Spot.Center,  // align document to the center of the viewport\n"
          +"layout:\n"
           +"$(ContinuousForceDirectedLayout,  // automatically spread nodes apart while dragging\n"
              +"{ defaultSpringLength: 30, defaultElectricalCharge: 100 }),\n"
          +"// do an extra layout at the end of a move\n"
          +"\"SelectionMoved\": function(e) { e.diagram.layout.invalidateLayout(); }\n"
        +"});\n"
   +" // dragging a node invalidates the Diagram.layout, causing a layout during the drag\n"
    +"myDiagram.toolManager.draggingTool.doMouseMove = function() {\n"
      +"go.DraggingTool.prototype.doMouseMove.call(this);\n"
     +" if (this.isActive) { this.diagram.layout.invalidateLayout(); }\n"
    +"}\n"
    +"// define each Node's appearance\n"
    +"myDiagram.nodeTemplate =\n"
      +"$(go.Node, \"Auto\",  // the whole node panel\n"
        +"// define the node's outer shape, which will surround the TextBlock\n"
        +"$(go.Shape, \"Circle\",\n"
         +" { fill: \"CornflowerBlue\", stroke: \"black\", spot1: new go.Spot(0, 0, 5, 5), spot2: new go.Spot(1, 1, -5, -5) }),\n"
       +" $(go.TextBlock,\n"
          +"{ font: \"bold 10pt helvetica, bold arial, sans-serif\", textAlign: \"center\", maxSize: new go.Size(100, NaN) },\n"
          +"new go.Binding(\"text\", \"text\"))\n"
      +");\n"
    +"// the rest of this app is the same as samples/conceptMap.html\n"
    +"// replace the default Link template in the linkTemplateMap\n"
    +"myDiagram.linkTemplate =\n"
      +"$(go.Link,  // the whole link panel\n"
        +"$(go.Shape,  // the link shape\n"
          +"{ stroke: \"black\" }),\n"
        +"$(go.Shape,  // the arrowhead\n"
          +"{ toArrow: \"standard\", stroke: null }),\n"
        +"$(go.Panel, \"Auto\",\n"
          +"$(go.Shape,  // the link shape\n"
           +" { fill: $(go.Brush, \"Radial\", { 0: \"rgb(240, 240, 240)\", 0.3: \"rgb(240, 240, 240)\", 1: \"rgba(240, 240, 240, 0)\" }),\n"
              +"stroke: null }),\n"
          +"$(go.TextBlock,  // the label\n"
            +"{ textAlign: \"center\",\n"
              +"font: \"10pt helvetica, arial, sans-serif\",\n"
             +" stroke: \"#555555\",\n"
             +" margin: 4 },\n"
            +"new go.Binding(\"text\", \"text\"))\n"
        +")\n"
     +" );\n"
    +"// create the model for the concept map\n"
    +"var nodeDataArray ="+JSONArrayCreator.jaPackages+" ;\n"
   +" var linkDataArray = [\n"
      +"{ from: 1, to: 2, text: 'represent' },\n"
      +"{ from: 2, to: 3, text: 'is' },\n"
      +"{ from: 2, to: 4, text: 'is' },\n"
      +"{ from: 2, to: 5, text: 'is' },\n"
      +"{ from: 2, to: 6, text: 'includes' },\n"
      +"{ from: 2, to: 10, text: 'necessary for' },\n"
      +"{ from: 2, to: 12, text: 'necessary for' },\n"
      +"{ from: 4, to: 5, text: 'combine to form' },\n"
      +"{ from: 4, to: 6, text: 'include' },\n"
      +"{ from: 4, to: 7, text: 'are' },\n"
      +"{ from: 4, to: 8, text: 'are' },\n"
      +"{ from: 4, to: 9, text: 'are' },\n"
      +"{ from: 5, to: 9, text: 'are' },\n"
      +"{ from: 5, to: 11, text: 'may be' },\n"
      +"{ from: 7, to: 13, text: 'in' },\n"
      +"{ from: 7, to: 14, text: 'in' },\n"
      +"{ from: 7, to: 19, text: 'begin with' },\n"
      +"{ from: 8, to: 15, text: 'with' },\n"
      +"{ from: 8, to: 16, text: 'with' },\n"
      +"{ from: 9, to: 17, text: 'aids' },\n"
      +"{ from: 11, to: 18, text: 'show' },\n"
      +"{ from: 12, to: 19, text: 'begins with' },\n"
      +"{ from: 17, to: 18, text: 'needed to see' },\n"
      +"{ from: 18, to: 20, text: 'between' },\n"
      +"{ from: 18, to: 21, text: 'between' }"
    +"];\n"
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

