function init() {
if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
var $ = go.GraphObject.make;  // for conciseness in defining templates
myDiagram =
$(go.Diagram, "myDiagram",  // must name or refer to the DIV HTML element
{
initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit
contentAlignment: go.Spot.Center,  // align document to the center of the viewport
layout:
$(go.ForceDirectedLayout,  // automatically spread nodes apart
{ defaultSpringLength: 500, defaultElectricalCharge: 100 })
});
// define each Node's appearance
myDiagram.nodeTemplate =
$(go.Node, "Auto",  // the whole node panel
// define the node's outer shape, which will surround the TextBlock
$(go.Shape, "Rectangle",
{ fill: $(go.Brush, "Linear", { 0: "rgb(254, 201, 0)", 1: "rgb(254, 162, 0)" }), stroke: "black" }),
$(go.TextBlock,
{ font: "bold 10pt helvetica, bold arial, sans-serif", margin: 4 },
new go.Binding("text", "text"))
);
// replace the default Link template in the linkTemplateMap
myDiagram.linkTemplate =
$(go.Link,  // the whole link panel
{ 
curve: go.Link.Bezier},
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
font: "10pt helvetica, arial, sans-seri",
stroke: "#555555",
margin: 4 },
new go.Binding("text", "text"))
)
);
// create the model for the concept map
var nodeDataArray =[{"text":"org.jsoup.nodes","key":17},{"text":"org.jsoup.safety","key":18},{"text":"org.jsoup.helper","key":19},{"text":"org.jsoup.parser","key":20},{"text":"org.jsoup.select","key":21},{"text":"org","key":8},{"text":"org.jsoup","key":22},{"text":"org.jsoup.examples","key":23}] ;
 var linkDataArray = [{"from":8,"to":23,"text":"import"},{"from":8,"to":21,"text":"import"},{"from":8,"to":19,"text":"import"},{"from":8,"to":17,"text":"import"},{"from":8,"to":22,"text":"import"},{"from":22,"to":23,"text":"import"},{"from":22,"to":21,"text":"import"},{"from":22,"to":19,"text":"import"},{"from":22,"to":17,"text":"import"},{"from":23,"to":21,"text":"import"},{"from":23,"to":19,"text":"import"},{"from":23,"to":17,"text":"import"},{"from":23,"to":22,"text":"import"},{"from":19,"to":20,"text":"import"},{"from":19,"to":17,"text":"import"},{"from":19,"to":21,"text":"import"},{"from":19,"to":22,"text":"import"},{"from":8,"to":20,"text":"import"},{"from":22,"to":20,"text":"import"},{"from":17,"to":20,"text":"import"},{"from":17,"to":19,"text":"import"},{"from":17,"to":21,"text":"import"},{"from":17,"to":22,"text":"import"},{"from":20,"to":17,"text":"import"},{"from":20,"to":21,"text":"import"},{"from":20,"to":19,"text":"import"},{"from":18,"to":21,"text":"import"},{"from":18,"to":17,"text":"import"},{"from":18,"to":19,"text":"import"},{"from":18,"to":20,"text":"import"},{"from":8,"to":18,"text":"import"},{"from":22,"to":18,"text":"import"},{"from":21,"to":17,"text":"import"},{"from":21,"to":20,"text":"import"},{"from":21,"to":19,"text":"import"}];
myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
}
init();
