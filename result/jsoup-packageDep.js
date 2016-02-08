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
{ defaultSpringLength: 200, defaultElectricalCharge: 100 })
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
{ stroke: 'black', strokeDashArray: [5,10]  }),
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
margin: 4 })
)
);
// create the model for the concept map
var nodeDataArray =[{"text":"org.jsoup.nodes","key":3},{"text":"org.jsoup.safety","key":5},{"text":"org.jsoup.helper","key":2},{"text":"org.jsoup.parser","key":4},{"text":"org.jsoup.select","key":6},{"text":"org.jsoup.examples","key":1}] ;
 var linkDataArray = [{"from":1,"to":6},{"from":1,"to":2},{"from":1,"to":3},{"from":2,"to":4},{"from":2,"to":3},{"from":2,"to":6},{"from":3,"to":4},{"from":3,"to":2},{"from":3,"to":6},{"from":4,"to":3},{"from":4,"to":6},{"from":4,"to":2},{"from":5,"to":6},{"from":5,"to":3},{"from":5,"to":2},{"from":5,"to":4},{"from":6,"to":3},{"from":6,"to":4},{"from":6,"to":2}];
myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
var myOverview = $(go.Overview, 'myOverviewDiv',{ observed: myDiagram });}
init();
