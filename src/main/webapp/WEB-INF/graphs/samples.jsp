<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>samples</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.png"/>
<link media="all" rel="stylesheet" type="text/css" href="<c:url value='/css/farm.css'/>"/>
<script src="<c:url value='/scripts/d3.js'/>"></script>
</head>
<c:url var="dataUrl" value="/api/samples/"/>
<body>
<h1>Select Goat</h1>
<div class="row">
  <div class="column leftColumn">
    <h2>Goats</h2>
    <ul class="selection">
      <c:forEach items="${requestScope.goats}" var="goat">
        <li id="<c:out value='${goat.id}'/>"><c:out value="${goat.id}"/> <c:out value="${goat.nombre}"/></li>
      </c:forEach>
    </ul>
    <form>
    <fieldset class="stacked">
      <legend>Period</legend>
      <input type="radio" name="period" id="1mo" value="1" /> <label for="1mo">1 month</label>
      <input type="radio" name="period" id="3mo" value="3" checked/> <label for="3mo">3 months</label>
      <input type="radio" name="period" id="6mo" value="6"/> <label for="6mo">6 months</label>
      <input type="radio" name="period" id="12mo" value="12"/> <label for="12mo">12 months</label>
      <input type="radio" name="period" id="24mo" value="24"/> <label for="24mo">24 months</label>
    </fieldset>
    <button type="button" class="btn" onclick="refreshGraph();return false">Refresh</button>
    </form>
  </div>
  <div id="stu" class="column rightColumn">
  </div>
</div>
<script>
// https://bl.ocks.org/basilesimon/29efb0e0a43dde81985c20d9a862e34e
// try updating instead of redoing the whole thing?
// http://bl.ocks.org/d3noob/7030f35b72de721622b8
// set the dimensions and margins of the graph
var margin = {top: 20, right: 20, bottom: 30, left: 50},
    width = 1000 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

// set the ranges
var x = d3.scaleTime().range([0, width]);
var y = d3.scaleLinear().range([height, 0]);

// define the line
var valueline = d3.line()
    .x(function(d) { return x(d.date); })
    .y(function(d) { return y(d.liters); });

function draw(data, am, pm) {

  var svg = d3.select("#stu").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform",
          "translate(" + margin.left + "," + margin.top + ")");

  // Scale the range of the data
  x.domain(d3.extent(data, function(d) { return d.date; }));
  y.domain([0, d3.max(data, function(d) { return d.liters; })]);

  // Add the valueline path for the AM data.
  if (am.length > 0) {
    svg.append("path")
      .data([am])
      .attr("class", "am line")
      .attr("d", valueline);
    svg.append("text")
      .attr("transform", "translate(" + (width+3) + "," + y(am[am.length-1].liters) + ")")
      .attr("dy", ".5em")
      .attr("text-anchor", "end")
      .style("fill", "#e9a3c9")
      .text("AM");
  }

  if (pm.length > 0) {
    svg.append("path")
        .data([pm])
        .attr("class", "pm line")
        .attr("d", valueline);

    svg.append("text")
          .attr("transform", "translate(" + (width+3) + "," + y(pm[pm.length-1].liters) + ")")
          .attr("dy", ".5em")
          .attr("text-anchor", "end")
          .style("fill", "#a1d76a")
          .text("PM");
  }

  // Add the X Axis
  svg.append("g")
      .attr("transform", "translate(0," + height + ")")
      .call(d3.axisBottom(x));

  // Add the Y Axis
  svg.append("g")
      .call(d3.axisLeft(y));
}

function cleanup() {
  d3.select('.graphed').classed('graphed', false);
  d3.select('svg').remove();
}

var whatever = function() {
  var goatId = this.id;
  cleanup();
  d3.select(this).attr('class', 'graphed');
  var months = d3.select('input[name="period"]:checked').attr('value');
  graph(goatId, months);
}

function graph(goatId, months) {
    d3.json("${dataUrl}" + goatId + "/" + months,  function(error, data) {
       if (error) throw error;
       // trigger render
       var am = [],
       pm = [];
       for (var i = 0; i < data.length; i++) {
           if (new Date(data[i].date).getHours() < 13) {
               am.push(data[i]);
           } else {
               pm.push(data[i]);
           }
       }
       draw(data, am, pm);
    });
}
d3.selectAll('li')
  .on('click', whatever);

function refreshGraph() {
  if (d3.select('.graphed').size() > 0) {
    var goatId = d3.select('.graphed').attr('id');
    var months = d3.select('input[name="period"]:checked').attr('value');
    d3.select('svg').remove();
    graph(goatId, months);
  } else {
     alert("Please select a goat first");
  }
  return false;
}
</script>
</body>
</html>