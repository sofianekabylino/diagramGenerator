function init() {
    var $ = go.GraphObject.make;
    myDiagram =
$(go.Diagram, 'myDiagram',  // must name or refer to the DIV HTML element
{
initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit
contentAlignment: go.Spot.Center,  // align document to the center of the viewport
layout:
$(go.ForceDirectedLayout,  // automatically spread nodes apart
{ defaultSpringLength: 30, defaultElectricalCharge: 100 })
});
// define each Node's appearance
myDiagram.nodeTemplate =
$(go.Node, 'Auto',  // the whole node panel
// define the node's outer shape, which will surround the TextBlock
$(go.Shape, 'Rectangle',
{ fill: $(go.Brush, 'Linear', { 0: 'rgb(254, 201, 0)', 1: 'rgb(254, 162, 0)' }), stroke: 'black' }),
$(go.TextBlock,
{ font: 'bold 10pt helvetica, bold arial, sans-serif', margin: 4 },
new go.Binding('text', 'text'))
);
    // show visibility or access as a single character at the beginning of each property or method
    function convertVisibility(v) {
      switch (v) {
        case 'public': return '+';
        case 'private': return '-';
        case 'protected': return '#';
        case 'package': return '~';
        default: return v;
      }
    }
    // the item template for properties
    var propertyTemplate =
      $(go.Panel, 'Horizontal',
        // property visibility/access
        $(go.TextBlock,
{ isMultiline: false, editable: false, width: 12 },
new go.Binding('text', 'visibility', convertVisibility)),
// property name, underlined if scope=='class' to indicate static property
$(go.TextBlock,
{ isMultiline: false, editable: true },
new go.Binding('text', 'name').makeTwoWay(),
new go.Binding('isUnderline', 'scope', function(s) { return s[0] === 'c' })),
// property type, if known
$(go.TextBlock, '',
new go.Binding('text', 'type', function(t) { return (t ? ': ' : ''); })),
$(go.TextBlock,
{ isMultiline: false, editable: true },
new go.Binding('text', 'type').makeTwoWay()),
// property default value, if any
$(go.TextBlock,
{ isMultiline: false, editable: false },
new go.Binding('text', 'default', function(s) { return s ? ' = ' + s : ''; }))
);
// the item template for methods
var methodTemplate =
$(go.Panel, 'Horizontal',
// method visibility/access
$(go.TextBlock,
{ isMultiline: false, editable: false, width: 12 },
new go.Binding('text', 'visibility', convertVisibility)),
// method name, underlined if scope=='class' to indicate static method
$(go.TextBlock,
{ isMultiline: false, editable: true },
new go.Binding('text', 'name').makeTwoWay(),
new go.Binding('isUnderline', 'scope', function(s) { return s[0] === 'c' })),
// method parameters
$(go.TextBlock, '()',
// this does not permit adding/editing/removing of parameters via inplace edits
new go.Binding('text', 'parameters', function(parr) {
var s = '(';
for (var i = 0; i < parr.length; i++) {
var param = parr[i];
if (i > 0) s += ', ';
s += param.name + ': ' + param.type;
}
return s + ')';
})),
// method return type, if any
$(go.TextBlock, '',
new go.Binding('text', 'type', function(t) { return (t ? ': ' : ''); })),
$(go.TextBlock,
{ isMultiline: false, editable: true },
new go.Binding('text', 'type').makeTwoWay())
);
// this simple template does not have any buttons to permit adding or
// removing properties or methods, but it could!
myDiagram.nodeTemplate =
$(go.Node, 'Auto',
{
locationSpot: go.Spot.Center,
fromSpot: go.Spot.AllSides,
toSpot: go.Spot.AllSides
},
$(go.Shape, { fill: 'lightyellow' }),
$(go.Panel, 'Table',
{ defaultRowSeparatorStroke: 'black' },
// header
$(go.TextBlock,
{
row: 0, margin: 3, alignment: go.Spot.Center,
font: 'bold 12pt sans-serif',
isMultiline: false, editable: true
},
new go.Binding('text', 'name').makeTwoWay()),
// properties
$(go.Panel, 'Vertical',
new go.Binding('itemArray', 'properties'),
{
row: 1, margin: 3, alignment: go.Spot.Left,
defaultAlignment: go.Spot.Left,
itemTemplate: propertyTemplate
}
),
// methods
$(go.Panel, 'Vertical',
new go.Binding('itemArray', 'methods'),
{
row: 2, margin: 3, alignment: go.Spot.Left,
defaultAlignment: go.Spot.Left,
itemTemplate: methodTemplate
}
))
);
function convertIsTreeLink(r) {
return r === 'generalization';
}
function convertFromArrow(r) {
switch (r) {
case 'generalization': return '';
default: return '';
}
}
function convertToArrow(r) {
switch (r) {
case 'generalization': return 'Triangle';
//case 'aggregation': return 'StretchedDiamond';
case 'aggregation': return 'OpenTriangle';
case 'association': return 'OpenTriangle';
case 'implements': return 'OpenTriangle';
default: return '';
}
}
function convertLink(r){
switch (r) {
case 'implements' : return [5,10];
}
}
myDiagram.linkTemplate =
$(go.Link,
{ routing: go.Link.AvoidsNodes },
new go.Binding('isLayoutPositioned', 'relationship', convertIsTreeLink),
$(go.Shape,{stroke:'black'},
new go.Binding('strokeDashArray' , 'relationship', convertLink)),
$(go.Shape, { scale: 1.3, fill: 'white' },
new go.Binding('fromArrow', 'relationship', convertFromArrow)),
$(go.Shape, { scale: 1.3, fill: 'white' },
new go.Binding('toArrow', 'relationship', convertToArrow)),
$(go.TextBlock,
{ segmentIndex: -1, segmentOffset: new go.Point(NaN, NaN),
segmentOrientation: go.Link.OrientUpright },
new go.Binding('text', 'text'))

);
// setup a few example class nodes and relationships
var nodedata = [{"methods":[{"visibility":"public","name":"getB","type":"int"},{"visibility":"public","name":"getG","type":"int"},{"visibility":"public","name":"getR","type":"int"},{"visibility":"public","name":"doIt","type":"void"},{"visibility":"protected","name":"generateColor","type":"void"}],"name":"Bille","key":1,"properties":[]},{"methods":[{"visibility":"public","name":"getCodeDirection","type":"double"},{"visibility":"public","name":"doIt","type":"void"},{"visibility":"public","name":"setCodeDirection","type":"void"}],"name":"Hunted","key":2,"properties":[{"visibility":"private","name":"codeDirection","type":"double"}]},{"methods":[],"name":"Hunter","key":3,"properties":[]},{"methods":[{"visibility":"public","name":"possibilities","type":"java.util.List<MultiAgentsParticules.DirectionEnum>"},{"visibility":"public","name":"doIt","type":"void"}],"name":"Fish","key":4,"properties":[{"visibility":"private","name":"compteurReproduction","type":"int"}]},{"methods":[{"visibility":"public","name":"doIt","type":"void"},{"visibility":"public","name":"generatePossibilities","type":"void"},{"visibility":"public","name":"setNbCycleDeath","type":"void"}],"name":"Shark","key":5,"properties":[{"visibility":"private","name":"nbCycleDeath","type":"int"},{"visibility":"private","name":"compteurNbCycleDeath","type":"int"},{"visibility":"private","name":"compteurReproduction","type":"int"},{"visibility":"private","name":"nbShark","type":"int"},{"visibility":"private","name":"starv","type":"int"}]},{"methods":[{"visibility":"public","name":"generateInitDirection","type":"MultiAgentsParticules.DirectionEnum"},{"visibility":"public","name":"getEnvironnement","type":"MultiAgentsParticules.Environnement"},{"visibility":"public","name":"getType","type":"MultiAgentsParticules.TypeOfAgentEnum"},{"visibility":"public","name":"isTaken","type":"boolean"},{"visibility":"public","name":"getB","type":"int"},{"visibility":"public","name":"getG","type":"int"},{"visibility":"public","name":"getId","type":"int"},{"visibility":"public","name":"getPositionX","type":"int"},{"visibility":"public","name":"getPositionY","type":"int"},{"visibility":"public","name":"getR","type":"int"},{"visibility":"public","name":"getColor","type":"java.awt.Color"},{"visibility":"public","name":"toString","type":"java.lang.String"},{"visibility":"public","name":"deplacement","type":"void"},{"visibility":"public","name":"doIt","type":"void"},{"visibility":"public","name":"setColor","type":"void"},{"visibility":"public","name":"setEnvironnement","type":"void"},{"visibility":"public","name":"setId","type":"void"},{"visibility":"public","name":"setNbCycleReproduction","type":"void"},{"visibility":"public","name":"setPositionX","type":"void"},{"visibility":"public","name":"setPositionY","type":"void"},{"visibility":"public","name":"setType","type":"void"}],"name":"Agent","key":6,"properties":[{"visibility":"protected","name":"id","type":"int"},{"visibility":"protected","name":"positionX","type":"int"},{"visibility":"protected","name":"positionY","type":"int"},{"visibility":"protected","name":"couleur","type":"java.awt.Color"},{"visibility":"protected","name":"r","type":"int"},{"visibility":"protected","name":"g","type":"int"},{"visibility":"protected","name":"b","type":"int"},{"visibility":"protected","name":"initialisation","type":"boolean"},{"visibility":"protected","name":"color","type":"java.awt.Color"},{"visibility":"protected","name":"nbCycleReproduction","type":"int"}]},{"methods":[{"visibility":"public","name":"getEspace","type":"MultiAgentsParticules.Agent[][]"},{"visibility":"public","name":"getHeight","type":"int"},{"visibility":"public","name":"getWidth","type":"int"},{"visibility":"public","name":"init","type":"void"},{"visibility":"public","name":"setHeight","type":"void"},{"visibility":"public","name":"setWidth","type":"void"}],"name":"Environnement","key":7,"properties":[{"visibility":"private","name":"espace","type":"MultiAgentsParticules.Agent[][]"},{"visibility":"private","name":"height","type":"int"},{"visibility":"private","name":"width","type":"int"}]},{"methods":[{"visibility":"public","name":"getSma","type":"MultiAgentsParticules.SMA"},{"visibility":"public","name":"main","type":"void"},{"visibility":"public","name":"setSma","type":"void"}],"name":"Main","key":8,"properties":[]},{"methods":[],"name":"MapAgent","key":9,"properties":[]},{"methods":[{"visibility":"public","name":"getEnvironnement","type":"MultiAgentsParticules.Environnement"},{"visibility":"public","name":"getAgents","type":"java.util.List<MultiAgentsParticules.Agent>"},{"visibility":"public","name":"initBille","type":"void"},{"visibility":"public","name":"initFishShark","type":"void"},{"visibility":"public","name":"nbTypeOfAgent","type":"void"},{"visibility":"public","name":"run","type":"void"}],"name":"SMA","key":10,"properties":[]},{"methods":[{"visibility":"public","name":"clean","type":"void"},{"visibility":"public","name":"fillCell","type":"void"},{"visibility":"protected","name":"paintComponent","type":"void"},{"visibility":"public","name":"repaint","type":"void"}],"name":"Grid","key":11,"properties":[{"visibility":"private","name":"fillCells","type":"java.util.List<java.awt.Point>"},{"visibility":"private","name":"width","type":"int"},{"visibility":"private","name":"height","type":"int"},{"visibility":"private","name":"mapColors","type":"java.util.Map<java.awt.Point, java.awt.Color>"}]},{"methods":[{"visibility":"public","name":"launch","type":"void"},{"visibility":"public","name":"update","type":"void"}],"name":"View","key":12,"properties":[{"visibility":"private","name":"frame","type":"javax.swing.JFrame"}]},{"methods":[{"visibility":"public","name":"launch","type":"void"},{"visibility":"public","name":"show","type":"void"},{"visibility":"public","name":"start","type":"void"},{"visibility":"public","name":"update","type":"void"}],"name":"ViewJFX","key":13,"properties":[{"visibility":"private","name":"canvas","type":"javafx.scene.layout.Pane"},{"visibility":"private","name":"scene","type":"javafx.scene.Scene"},{"visibility":"public","name":"circle","type":"java.util.List<javafx.scene.shape.Circle>"},{"visibility":"public","name":"lstCircle","type":"javafx.collections.ObservableList<javafx.scene.shape.Circle>"}]},{"methods":[],"name":"DirectionEnum","key":14,"properties":[{"name":"NORTH"},{"name":"NORTH_EAST"},{"name":"EAST"},{"name":"SOUTH_EAST"},{"name":"SOUTH"},{"name":"SOUTH_WEST"},{"name":"WEST"},{"name":"NORTH_WEST"},{"name":"NONE"}]},{"methods":[],"name":"TypeOfAgentEnum","key":15,"properties":[{"name":"BILLE"},{"name":"FISH"},{"name":"SHARK"},{"name":"HUNTED"}]}] 
;
var linkdata = [{"from":1,"to":6,"relationship":"generalization"},{"from":2,"to":6,"relationship":"generalization"},{"from":4,"to":6,"relationship":"generalization"},{"from":5,"to":6,"relationship":"generalization"},{"from":5,"to":14,"text":"1..*","relationship":"aggregation"},{"from":5,"to":14,"text":"1..*","relationship":"aggregation"},{"from":6,"to":14,"text":"direction","relationship":"association"},{"from":6,"to":7,"text":"environnement","relationship":"association"},{"from":6,"to":15,"text":"type","relationship":"association"},{"from":8,"to":10,"text":"sma","relationship":"association"},{"from":9,"to":6,"text":"1..*","relationship":"aggregation"},{"from":10,"to":7,"text":"environnement","relationship":"association"},{"from":10,"to":6,"text":"1..*","relationship":"aggregation"},{"from":10,"to":12,"text":"view","relationship":"association"},{"from":12,"to":10,"text":"sma","relationship":"association"},{"from":12,"to":11,"text":"grid","relationship":"association"},{"from":13,"to":10,"text":"sma","relationship":"association"}] 
;

myDiagram.model = $(go.GraphLinksModel,
{
copiesArrays: true,
copiesArrayObjects: true,
nodeDataArray: nodedata,
linkDataArray: linkdata
});
var myOverview = $(go.Overview, 'myOverviewDiv',{ observed: myDiagram });}
init();
