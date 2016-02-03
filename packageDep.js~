// This variation on ForceDirectedLayout does not move any selected Nodes but does move all other nodes (vertexes).
function ContinuousForceDirectedLayout() {
go.ForceDirectedLayout.call(this);
this._isObserving = false;
}
go.Diagram.inherit(ContinuousForceDirectedLayout, go.ForceDirectedLayout);
/** @override */
ContinuousForceDirectedLayout.prototype.isFixed = function(v) {
return v.node.isSelected;
}
// optimization: reuse the ForceDirectedNetwork rather than re-create it each time
/** @override */
ContinuousForceDirectedLayout.prototype.doLayout = function(coll) {
if (!this._isObserving) {
this._isObserving = true;
// cacheing the network means we need to recreate it if nodes or links have been added or removed or relinked, so we need to track structural model changes to discard the saved network.
var lay = this;
this.diagram.model.addChangedListener(function (e) {
// modelChanges include a few cases that we don't actually care about, such as "nodeCategory" or "linkToPortId", but we'll go ahead and recreate the network anyway
if (e.modelChange !== "") lay.network = null;
});
}
var net = this.network;
if (net === null) {  // the first time, just create the network as normal
this.network = net = this.makeNetwork(coll);
} else {  // but on reuse we need to update the LayoutVertex.bounds for selected nodes
this.diagram.nodes.each(function (n) {
var v = net.findVertex(n);
if (v !== null) v.bounds = n.actualBounds;
});
}
// now perform the normal layout
go.ForceDirectedLayout.prototype.doLayout.call(this, coll);
// doLayout normally discards the LayoutNetwork by setting Layout.network to null; here we remember it for next time
this.network = net;
}
// end ContinuousForceDirectedLayout
function init() {
if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
var $ = go.GraphObject.make;  // for conciseness in defining templates
myDiagram =
$(go.Diagram, "myDiagram",  // must name or refer to the DIV HTML element
{
initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit
contentAlignment: go.Spot.Center,  // align document to the center of the viewport
layout:
$(ContinuousForceDirectedLayout,  // automatically spread nodes apart while dragging
{ defaultSpringLength: 30, defaultElectricalCharge: 100 }),
// do an extra layout at the end of a move
"SelectionMoved": function(e) { e.diagram.layout.invalidateLayout(); }
});
 // dragging a node invalidates the Diagram.layout, causing a layout during the drag
myDiagram.toolManager.draggingTool.doMouseMove = function() {
go.DraggingTool.prototype.doMouseMove.call(this);
 if (this.isActive) { this.diagram.layout.invalidateLayout(); }
}
// define each Node's appearance
myDiagram.nodeTemplate =
$(go.Node, "Auto",  // the whole node panel
// define the node's outer shape, which will surround the TextBlock
$(go.Shape, "Circle",
 { fill: "CornflowerBlue", stroke: "black", spot1: new go.Spot(0, 0, 5, 5), spot2: new go.Spot(1, 1, -5, -5) }),
 $(go.TextBlock,
{ font: "bold 10pt helvetica, bold arial, sans-serif", textAlign: "center", maxSize: new go.Size(100, NaN) },
new go.Binding("text", "text"))
);
// the rest of this app is the same as samples/conceptMap.html
// replace the default Link template in the linkTemplateMap
myDiagram.linkTemplate =
$(go.Link,  // the whole link panel
$(go.Shape,  // the link shape
{ stroke: "black" }),
$(go.Shape,  // the arrowhead
{ toArrow: "standard", stroke: null }),
$(go.Panel, "Auto",
$(go.Shape,  // the link shape
 { fill: $(go.Brush, "Radial", { 0: "rgb(240, 240, 240)", 0.3: "rgb(240, 240, 240)", 1: "rgba(240, 240, 240, 0)" }),
stroke: null }),
$(go.TextBlock,  // the label
{ textAlign: "center",
font: "10pt helvetica, arial, sans-serif",
 stroke: "#555555",
 margin: 4 },
new go.Binding("text", "text"))
)
 );
// create the model for the concept map
var nodeDataArray =[{"text":"MultiAgentsParticules.bille","key":1},{"text":"MultiAgentsParticules.hotPursuit","key":2},{"text":"MultiAgentsParticules.part1","key":3},{"text":"MultiAgentsParticules.wator","key":4},{"text":"MultiAgentsParticules","key":5},{"text":"","key":6},{"text":"java.util","key":7},{"text":"javafx.collections","key":8},{"text":"javafx.scene.layout","key":9},{"text":"javafx.event","key":10},{"text":"javafx.animation","key":11},{"text":"javafx.application","key":12},{"text":"javafx.scene.paint","key":13},{"text":"javafx.stage","key":14},{"text":"java.awt","key":15},{"text":"java.lang","key":16},{"text":"javafx.scene.shape","key":17},{"text":"javafx.scene","key":18},{"text":"junit.framework","key":19},{"text":"javax.swing","key":20},{"text":"javafx.util","key":21}] ;
 var linkDataArray = [
{ from: 1, to: 2, text: 'represent' },
{ from: 2, to: 3, text: 'is' },
{ from: 2, to: 4, text: 'is' },
{ from: 2, to: 5, text: 'is' },
{ from: 2, to: 6, text: 'includes' },
{ from: 2, to: 10, text: 'necessary for' },
{ from: 2, to: 12, text: 'necessary for' },
{ from: 4, to: 5, text: 'combine to form' },
{ from: 4, to: 6, text: 'include' },
{ from: 4, to: 7, text: 'are' },
{ from: 4, to: 8, text: 'are' },
{ from: 4, to: 9, text: 'are' },
{ from: 5, to: 9, text: 'are' },
{ from: 5, to: 11, text: 'may be' },
{ from: 7, to: 13, text: 'in' },
{ from: 7, to: 14, text: 'in' },
{ from: 7, to: 19, text: 'begin with' },
{ from: 8, to: 15, text: 'with' },
{ from: 8, to: 16, text: 'with' },
{ from: 9, to: 17, text: 'aids' },
{ from: 11, to: 18, text: 'show' },
{ from: 12, to: 19, text: 'begins with' },
{ from: 17, to: 18, text: 'needed to see' },
{ from: 18, to: 20, text: 'between' },
{ from: 18, to: 21, text: 'between' }];
myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
}
init();
