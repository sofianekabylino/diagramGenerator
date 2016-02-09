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
var nodedata = [{"methods":[],"name":"EndOfGame","key":1,"properties":[]},{"methods":[{"visibility":"public","name":"doIt","type":"void"}],"name":"AbstractWatorAgent","key":2,"properties":[{"visibility":"protected","name":"roundBeforeBreadingCurrent","type":"int"},{"visibility":"protected","name":"rounBeforeBreadingInit","type":"int"}]},{"methods":[{"visibility":"public","name":"directionNull","type":"boolean"},{"visibility":"public","name":"equals","type":"boolean"},{"visibility":"public","name":"isAlreadyVisited","type":"boolean"},{"visibility":"public","name":"tenterDeplacement","type":"boolean"},{"visibility":"public","name":"getX","type":"int"},{"visibility":"public","name":"getY","type":"int"},{"visibility":"public","name":"hashCode","type":"int"},{"visibility":"public","name":"generateColor","type":"java.awt.Color"},{"visibility":"public","name":"getCouleur","type":"java.awt.Color"},{"visibility":"public","name":"getId","type":"java.lang.String"},{"visibility":"public","name":"getDirection","type":"tp1_idl.Direction"},{"visibility":"public","name":"getEnv","type":"tp1_idl.Environnement"},{"visibility":"public","name":"deplacer","type":"void"},{"visibility":"public","name":"doIt","type":"void"},{"visibility":"public","name":"generateId","type":"void"},{"visibility":"public","name":"setAlreadyVisited","type":"void"},{"visibility":"public","name":"setDirection","type":"void"},{"visibility":"public","name":"setX","type":"void"},{"visibility":"public","name":"setY","type":"void"}],"name":"Agent","key":3,"properties":[{"visibility":"protected","name":"x","type":"int"},{"visibility":"protected","name":"y","type":"int"},{"visibility":"protected","name":"couleur","type":"java.awt.Color"},{"visibility":"private","name":"id","type":"java.lang.String"},{"visibility":"protected","name":"alreadyVisited","type":"boolean"}]},{"methods":[{"visibility":"public","name":"doIt","type":"void"}],"name":"Bille","key":4,"properties":[]},{"methods":[{"visibility":"public","name":"generateColor","type":"java.awt.Color"},{"visibility":"public","name":"doIt","type":"void"}],"name":"Fish","key":5,"properties":[]},{"methods":[{"visibility":"public","name":"checkCoordinates","type":"boolean"},{"visibility":"public","name":"generateColor","type":"java.awt.Color"},{"visibility":"private","name":"deplacerVersMinValue","type":"void"},{"visibility":"public","name":"doIt","type":"void"}],"name":"Ghost","key":6,"properties":[{"visibility":"private","name":"deceleration","type":"int"},{"visibility":"private","name":"counterDeceleration","type":"int"}]},{"methods":[{"visibility":"public","name":"generateColor","type":"java.awt.Color"},{"visibility":"public","name":"getNumber","type":"java.lang.Integer"},{"visibility":"public","name":"doIt","type":"void"},{"visibility":"public","name":"setNumber","type":"void"}],"name":"Number","key":7,"properties":[{"visibility":"private","name":"number","type":"java.lang.Integer"}]},{"methods":[{"visibility":"public","name":"checkCoordinates","type":"boolean"},{"visibility":"public","name":"estOccupe","type":"boolean"},{"visibility":"public","name":"generateColor","type":"java.awt.Color"},{"visibility":"public","name":"dijkstra","type":"void"},{"visibility":"public","name":"doIt","type":"void"},{"visibility":"private","name":"reset","type":"void"}],"name":"Pacman","key":8,"properties":[{"visibility":"public","name":"numbers","type":"int[][]"},{"visibility":"private","name":"deceleration","type":"int"},{"visibility":"private","name":"counterDeceleration","type":"int"}]},{"methods":[{"visibility":"public","name":"eat","type":"boolean"},{"visibility":"public","name":"eat","type":"boolean"},{"visibility":"public","name":"getRoundBeforeDyingCurrent","type":"int"},{"visibility":"public","name":"generateColor","type":"java.awt.Color"},{"visibility":"public","name":"doIt","type":"void"}],"name":"Shark","key":9,"properties":[{"visibility":"private","name":"roundBeforeDyingCurrent","type":"int"},{"visibility":"private","name":"roundBeforeDyingInit","type":"int"}]},{"methods":[{"visibility":"public","name":"generateColor","type":"java.awt.Color"},{"visibility":"public","name":"doIt","type":"void"}],"name":"Stone","key":10,"properties":[]},{"methods":[{"visibility":"public","name":"getAgents","type":"java.util.List<tp1_idl.models.Agent>"},{"visibility":"public","name":"getEnv","type":"tp1_idl.Environnement"},{"visibility":"public","name":"doAfterRound","type":"void"},{"visibility":"protected","name":"generateAgent","type":"void"},{"visibility":"public","name":"init","type":"void"},{"visibility":"public","name":"run","type":"void"},{"visibility":"public","name":"setEnv","type":"void"}],"name":"Simulateur","key":11,"properties":[{"visibility":"protected","name":"random","type":"java.util.Random"},{"visibility":"protected","name":"time","type":"int"},{"visibility":"protected","name":"equitable","type":"boolean"}]},{"methods":[{"visibility":"protected","name":"generateAgent","type":"void"}],"name":"SimulateurBille","key":12,"properties":[]},{"methods":[{"visibility":"public","name":"doAfterRound","type":"void"},{"visibility":"protected","name":"generateAgent","type":"void"}],"name":"SimulateurPacman","key":13,"properties":[{"visibility":"private","name":"NBR_STONE","type":"int"},{"visibility":"private","name":"DECELERATION1","type":"int"},{"visibility":"private","name":"DECELERATION2","type":"int"}]},{"methods":[{"visibility":"public","name":"doAfterRound","type":"void"},{"visibility":"protected","name":"generateAgent","type":"void"}],"name":"SimulateurWator","key":14,"properties":[{"visibility":"private","name":"repartitionRequinPoisson","type":"int"},{"visibility":"private","name":"roundBeforeBreadingShark","type":"int"},{"visibility":"private","name":"roundBeforeBreadingFish","type":"int"},{"visibility":"private","name":"roundBeforeDyingShark","type":"int"}]},{"methods":[{"visibility":"public","name":"getSimulateur","type":"tp1_idl.simulation.Simulateur"},{"visibility":"private","name":"paintAgents","type":"void"},{"visibility":"public","name":"paintComponent","type":"void"},{"visibility":"private","name":"paintGrid","type":"void"},{"visibility":"public","name":"setSimulateur","type":"void"}],"name":"MyPanel","key":15,"properties":[{"visibility":"private","name":"mGrid","type":"boolean"},{"visibility":"private","name":"mAgentSize","type":"int"},{"visibility":"private","name":"mWidth","type":"int"},{"visibility":"private","name":"mHeight","type":"int"}]},{"methods":[{"visibility":"public","name":"keyPressed","type":"void"},{"visibility":"public","name":"keyReleased","type":"void"},{"visibility":"public","name":"keyTyped","type":"void"},{"visibility":"public","name":"update","type":"void"}],"name":"VueGraphique","key":16,"properties":[{"visibility":"private","name":"TOP_OFFSET","type":"int"},{"visibility":"public","name":"inbox","type":"java.lang.String"}]},{"methods":[{"visibility":"public","name":"equals","type":"boolean"},{"visibility":"public","name":"getX","type":"int"},{"visibility":"public","name":"getY","type":"int"},{"visibility":"public","name":"hashCode","type":"int"},{"visibility":"public","name":"getDirectionPossible","type":"java.util.List<tp1_idl.Direction>"},{"visibility":"private","name":"initListe","type":"void"},{"visibility":"public","name":"setX","type":"void"},{"visibility":"public","name":"setY","type":"void"}],"name":"Direction","key":17,"properties":[{"visibility":"private","name":"x","type":"int"},{"visibility":"private","name":"y","type":"int"}]},{"methods":[{"visibility":"public","name":"estOccupe","type":"boolean"},{"visibility":"public","name":"isTorique","type":"boolean"},{"visibility":"public","name":"getHauteur","type":"int"},{"visibility":"public","name":"getLargeur","type":"int"},{"visibility":"public","name":"getNbrAgent","type":"int"},{"visibility":"public","name":"getNbrAgentOnEnv","type":"int"},{"visibility":"public","name":"getInbox","type":"java.lang.String"},{"visibility":"public","name":"getAgents","type":"java.util.ArrayList<tp1_idl.models.Agent>"},{"visibility":"public","name":"getAgent","type":"tp1_idl.models.Agent"},{"visibility":"public","name":"getEspace","type":"tp1_idl.models.Agent[][]"},{"visibility":"public","name":"deleteAgent","type":"void"},{"visibility":"public","name":"initVisiteFlag","type":"void"},{"visibility":"public","name":"setAgent","type":"void"},{"visibility":"public","name":"setInbox","type":"void"}],"name":"Environnement","key":18,"properties":[{"visibility":"protected","name":"nbrAgent","type":"int"},{"visibility":"protected","name":"largeur","type":"int"},{"visibility":"protected","name":"hauteur","type":"int"},{"visibility":"protected","name":"espace","type":"tp1_idl.models.Agent[][]"},{"visibility":"protected","name":"torique","type":"boolean"},{"visibility":"protected","name":"inbox","type":"java.lang.String"}]},{"methods":[{"visibility":"public","name":"main","type":"void"}],"name":"Main","key":19,"properties":[{"visibility":"public","name":"NBR_AGENT","type":"java.lang.Integer"},{"visibility":"public","name":"NBR_TOUR","type":"java.lang.Integer"},{"visibility":"public","name":"HAUTEUR","type":"java.lang.Integer"},{"visibility":"public","name":"LARGEUR","type":"java.lang.Integer"},{"visibility":"public","name":"TORIQUE","type":"boolean"},{"visibility":"public","name":"ATTENTE","type":"java.lang.Integer"},{"visibility":"public","name":"TAILLE_AGENT","type":"java.lang.Integer"},{"visibility":"public","name":"EQUITABLE","type":"boolean"},{"visibility":"public","name":"GRILLE","type":"boolean"},{"visibility":"public","name":"BREADING_SHARK","type":"java.lang.Integer"},{"visibility":"public","name":"BREADING_FISH","type":"java.lang.Integer"},{"visibility":"public","name":"DYING_SHARK","type":"java.lang.Integer"},{"visibility":"public","name":"REPARTITION_REQUIN_POISSON_POURCENT","type":"java.lang.Integer"},{"visibility":"public","name":"NBR_STONE","type":"java.lang.Integer"},{"visibility":"public","name":"DECELERATION","type":"java.lang.Integer"},{"visibility":"public","name":"NBR__AGENT_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"NBR_TOUR_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"LARGEUR_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"HAUTEUR_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"TAILLE_AGENT_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"ATTENTE_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"TORIQUE_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"EQUITABLE_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"GRILLE_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"NBR_ROUND_BEFORE_BREADING_SHARK_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"NBR_ROUND_BEFORE_DYING_SHARK_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"NBR_ROUND_BEFORE_BREADING_FISH_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"REPARTITION_REQUIN_POISSON_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"NBR_STONE_PARAMETER","type":"java.lang.String"},{"visibility":"public","name":"DECELERATION_PARAMETER","type":"java.lang.String"}]},{"methods":[],"name":"Statistique","key":20,"properties":[{"visibility":"public","name":"collision","type":"int"}]}] 
;
var linkdata = [{"from":2,"to":3,"relationship":"generalization"},{"from":3,"to":17,"text":"direction","relationship":"association"},{"from":3,"to":18,"text":"env","relationship":"association"},{"from":3,"to":17,"text":"directionInit","relationship":"association"},{"from":4,"to":3,"relationship":"generalization"},{"from":5,"to":2,"relationship":"generalization"},{"from":6,"to":3,"relationship":"generalization"},{"from":7,"to":3,"relationship":"generalization"},{"from":8,"to":3,"relationship":"generalization"},{"from":9,"to":2,"relationship":"generalization"},{"from":10,"to":3,"relationship":"generalization"},{"from":11,"to":18,"text":"env","relationship":"association"},{"from":11,"to":16,"text":"vue","relationship":"association"},{"from":12,"to":11,"relationship":"generalization"},{"from":13,"to":11,"relationship":"generalization"},{"from":14,"to":11,"relationship":"generalization"},{"from":15,"to":11,"text":"simulateur","relationship":"association"},{"from":16,"to":11,"text":"simulateur","relationship":"association"},{"from":16,"to":15,"text":"panel","relationship":"association"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":17,"to":17,"text":"1..*","relationship":"aggregation"},{"from":18,"to":3,"text":"1..*","relationship":"aggregation"}] 
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
