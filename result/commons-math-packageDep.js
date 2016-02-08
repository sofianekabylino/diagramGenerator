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
var nodeDataArray =[{"text":"org.apache.commons.math4.geometry.euclidean.oned","key":16},{"text":"org.apache.commons.math4.transform","key":46},{"text":"org.apache.commons.math4.stat.interval","key":43},{"text":"org.apache.commons.math4.geometry.enclosing","key":15},{"text":"org.apache.commons.math4.ml.neuralnet.oned","key":25},{"text":"org.apache.commons.math4.fitting.leastsquares","key":12},{"text":"org.apache.commons.math4.fraction","key":13},{"text":"org.apache.commons.math4.stat.inference","key":42},{"text":"org.apache.commons.math4.stat.correlation","key":38},{"text":"org.apache.commons.math4.geometry.euclidean.threed","key":17},{"text":"org.apache.commons.math4.optim.linear","key":31},{"text":"org.apache.commons.math4.geometry.spherical.twod","key":21},{"text":"org.apache.commons.math4.util","key":47},{"text":"org.apache.commons.math4.geometry.euclidean.twod.hull","key":18},{"text":"org.apache.commons.math4.optim.nonlinear.scalar.gradient","key":32},{"text":"org.apache.commons.math4.ode.events","key":28},{"text":"org.apache.commons.math4.analysis.differentiation","key":1},{"text":"org.apache.commons.math4.stat.regression","key":45},{"text":"org.apache.commons.math4.analysis.function","key":2},{"text":"org.apache.commons.math4.analysis.integration.gauss","key":3},{"text":"org.apache.commons.math4.stat.descriptive.rank","key":40},{"text":"org.apache.commons.math4.special","key":37},{"text":"org.apache.commons.math4.geometry.partitioning","key":19},{"text":"org.apache.commons.math4.ml.distance","key":24},{"text":"org.apache.commons.math4.ml.clustering.evaluation","key":23},{"text":"org.apache.commons.math4.optim.univariate","key":34},{"text":"org.apache.commons.math4.dfp","key":8},{"text":"org.apache.commons.math4.ode.sampling","key":30},{"text":"org.apache.commons.math4.filter","key":11},{"text":"org.apache.commons.math4.random","key":36},{"text":"org.apache.commons.math4.exception.util","key":10},{"text":"org.apache.commons.math4.ode.nonstiff","key":29},{"text":"org.apache.commons.math4.optim.nonlinear.scalar.noderiv","key":33},{"text":"org.apache.commons.math4.analysis.interpolation","key":4},{"text":"org.apache.commons.math4.distribution.fitting","key":9},{"text":"org.apache.commons.math4.ml.neuralnet.sofm.util","key":26},{"text":"org.apache.commons.math4.stat.ranking","key":44},{"text":"org.apache.commons.math4.analysis.polynomials","key":5},{"text":"org.apache.commons.math4.complex","key":7},{"text":"org.apache.commons.math4.geometry.spherical.oned","key":20},{"text":"org.apache.commons.math4.stat.descriptive.moment","key":39},{"text":"org.apache.commons.math4.genetics","key":14},{"text":"org.apache.commons.math4.primes","key":35},{"text":"org.apache.commons.math4.stat.descriptive.summary","key":41},{"text":"org.apache.commons.math4.analysis.solvers","key":6},{"text":"org.apache.commons.math4.linear","key":22},{"text":"org.apache.commons.math4.ml.neuralnet.twod.util","key":27}] ;
 var linkDataArray = [{"from":1,"to":47},{"from":2,"to":47},{"from":2,"to":1},{"from":3,"to":47},{"from":3,"to":10},{"from":4,"to":47},{"from":4,"to":1},{"from":4,"to":10},{"from":4,"to":5},{"from":4,"to":36},{"from":5,"to":47},{"from":5,"to":10},{"from":5,"to":13},{"from":5,"to":1},{"from":6,"to":47},{"from":6,"to":10},{"from":6,"to":7},{"from":6,"to":1},{"from":6,"to":5},{"from":7,"to":47},{"from":7,"to":10},{"from":8,"to":47},{"from":9,"to":47},{"from":9,"to":22},{"from":9,"to":10},{"from":9,"to":38},{"from":11,"to":22},{"from":11,"to":47},{"from":12,"to":47},{"from":12,"to":22},{"from":12,"to":10},{"from":13,"to":47},{"from":13,"to":10},{"from":14,"to":47},{"from":14,"to":36},{"from":14,"to":10},{"from":16,"to":19},{"from":16,"to":47},{"from":16,"to":10},{"from":17,"to":16},{"from":17,"to":19},{"from":17,"to":47},{"from":17,"to":10},{"from":17,"to":15},{"from":17,"to":13},{"from":18,"to":47},{"from":18,"to":10},{"from":18,"to":19},{"from":19,"to":47},{"from":19,"to":10},{"from":20,"to":47},{"from":20,"to":19},{"from":20,"to":10},{"from":21,"to":20},{"from":21,"to":19},{"from":21,"to":47},{"from":21,"to":15},{"from":21,"to":17},{"from":21,"to":10},{"from":22,"to":7},{"from":22,"to":47},{"from":22,"to":10},{"from":22,"to":2},{"from":22,"to":13},{"from":23,"to":24},{"from":23,"to":39},{"from":24,"to":47},{"from":26,"to":2},{"from":26,"to":47},{"from":27,"to":47},{"from":27,"to":24},{"from":28,"to":47},{"from":28,"to":6},{"from":28,"to":30},{"from":29,"to":22},{"from":29,"to":30},{"from":29,"to":47},{"from":29,"to":10},{"from":29,"to":13},{"from":29,"to":6},{"from":29,"to":28},{"from":30,"to":47},{"from":30,"to":22},{"from":31,"to":47},{"from":31,"to":22},{"from":31,"to":10},{"from":32,"to":10},{"from":32,"to":34},{"from":33,"to":47},{"from":33,"to":22},{"from":33,"to":36},{"from":33,"to":10},{"from":33,"to":34},{"from":34,"to":47},{"from":34,"to":36},{"from":34,"to":10},{"from":35,"to":47},{"from":35,"to":10},{"from":36,"to":22},{"from":36,"to":47},{"from":36,"to":10},{"from":37,"to":47},{"from":37,"to":10},{"from":38,"to":39},{"from":38,"to":47},{"from":38,"to":10},{"from":38,"to":22},{"from":38,"to":44},{"from":38,"to":45},{"from":39,"to":41},{"from":39,"to":47},{"from":39,"to":10},{"from":39,"to":22},{"from":40,"to":47},{"from":40,"to":10},{"from":40,"to":4},{"from":40,"to":44},{"from":41,"to":47},{"from":42,"to":47},{"from":42,"to":36},{"from":42,"to":13},{"from":42,"to":10},{"from":42,"to":22},{"from":42,"to":44},{"from":43,"to":47},{"from":43,"to":10},{"from":44,"to":36},{"from":44,"to":47},{"from":45,"to":47},{"from":45,"to":22},{"from":45,"to":10},{"from":45,"to":39},{"from":46,"to":47},{"from":46,"to":10},{"from":46,"to":7},{"from":47,"to":36},{"from":47,"to":37},{"from":47,"to":10}];
myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
var myOverview = $(go.Overview, 'myOverviewDiv',{ observed: myDiagram });}
init();
