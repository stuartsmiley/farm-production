<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rainfall Totals</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.png"/>
<link media="all" rel="stylesheet" type="text/css" href="<c:url value='/css/farm.css'/>"/>
<style>
.axis path, .axis line{
    fill: none;
    stroke: black;
 }
.tick line{opacity: 0.2;}
</style>
<script src="<c:url value='/scripts/d3.js'/>"></script>
</head>
<body>
<!-- inspired by http://bl.ocks.org/jfreels/7030677 -->
<script>
var div = d3.select('body').append('div')

var margin = {top: 20, right: 20, bottom: 30, left: 50},
    width = 960 - margin.left - margin.right,
    height = 350 - margin.top - margin.bottom;

var p = d3.precisionFixed(0.5);
var formatMM = d3.format("." + p + "f");

var x = d3.scaleTime()
    .range([0, width]);

var y = d3.scaleLinear()
    .range([height, 0]);

var xAxis = d3.axisBottom(x)
       .tickSizeInner(-height);

var yAxis = d3.axisLeft(y)
    .tickFormat(formatMM)
    .tickSizeInner(-width);

var line = d3.line()
    .x(function(d) { return x(d['date']); })
    .y(function(d) { return y(d['mm']); });

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

d3.json("<c:url value='/api/rainfall/totals'/>", function(error, data) {
  data.forEach(function(d) {
    d['date'] = new Date(d['date']);
    d['mm'] = +d['mm'];
    d['cumulativeMm'] = +d['cumulativeMm'];
    d['year'] = +d['year'];
    d['month'] = +d['month'];
    d.value = +d.value;
  });
   var dataNested = d3.nest()
      .key(function (d) { return d.year })
      .entries(data)

    div.append('select')
        .attr('id','yearSelect')
        .on('change',yearChange)
      .selectAll('option')
        .data(dataNested).enter()
      .append('option')
        .attr('value',function (d) { return d.key })
        .text(function (d) { return d.key })

    div.append('select')
      .attr('id','typeSelect')
      .on('change',typeChange)
    .selectAll('option')
      .data(d3.keys(data[0]).filter(function (d) { return d === 'cumulativeMm' ||  d === 'mm' })).enter()
    .append('option')
      .attr('value',function (d) { return d })
      .text(function (d) { return d })

    var dataFiltered = dataNested.filter(function (d) { return d.key===d3.select('#yearSelect').property('value') })

    x.domain(d3.extent(dataFiltered[0].values, function(d) { return d['date']; }));
    y.domain(d3.extent(dataFiltered[0].values, function(d) { return d['mm']; })).nice();

    svg.append("g")
        .attr("class", "xAxis")
        .attr("transform", "translate(0," + height + ")")
        .call(xAxis);

    svg.append("g")
        .attr("class", "yAxis")
        .call(yAxis)
      .append("text")
        .attr('id','yAxisText')
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".71em")
        .style("text-anchor", "end")
        .text("Return");

    svg.append("path")
        .datum(dataFiltered[0].values)
        .attr("class", "line")
        .attr("d", line);

    svg.append('line')
        .attr('id','zeroLine')
        .attr('x1',x(d3.min(dataFiltered[0].values, function (d) { return d['date']; })))
        .attr('x2',x(d3.max(dataFiltered[0].values, function (d) { return d['date']; })))
        .attr('y1',y(0))
        .attr('y2',y(0))

    function yearChange() {
      var yearValue = d3.select('#yearSelect').property('value')
      var typeValue = d3.select('#typeSelect').property('value')
      var dataFiltered = dataNested.filter(function (d) { return d.key===yearValue })
      x.domain(d3.extent(dataFiltered[0].values, function (d) { return d['date']; }));
      y.domain(d3.extent(dataFiltered[0].values, function (d) { return d[typeValue]; })).nice();
      d3.select('.xAxis').transition().duration(1000).call(xAxis)
      d3.select('.yAxis').transition().duration(1000).call(yAxis)
      d3.select('.line').datum(dataFiltered[0].values).attr('d',line)
      d3.select('#zeroLine')
        .transition().duration(1000)
        .attr('x1',x(d3.min(dataFiltered[0].values, function (d) { return d['date']; })))
        .attr('x2',x(d3.max(dataFiltered[0].values, function (d) { return d['date']; })))
        .attr('y1',y(0))
        .attr('y2',y(0))
    }

    function typeChange() {
      var yearValue = d3.select('#yearSelect').property('value')
      var typeValue = d3.select('#typeSelect').property('value')
      line.y(function(d) { return y(d[typeValue]); })
      var dataFiltered = dataNested.filter(function (d) { return d.key===yearValue })
      x.domain(d3.extent(dataFiltered[0].values, function (d) { return d['date']; }));
      y.domain(d3.extent(dataFiltered[0].values, function (d) { return d[typeValue]; })).nice();
      d3.select('.xAxis').transition().duration(1000).call(xAxis)
      d3.select('.yAxis').transition().duration(1000).call(yAxis)
      d3.select('.line').datum(dataFiltered[0].values).transition().duration(1000).attr('d',line)
      d3.select('#yAxisText').text(typeValue)
      d3.select('#zeroLine')
        .transition().duration(1000)
        .attr('x1',x(d3.min(dataFiltered[0].values, function (d) { return d['date']; })))
        .attr('x2',x(d3.max(dataFiltered[0].values, function (d) { return d['date']; })))
        .attr('y1',y(0))
        .attr('y2',y(0))
    }
});
</script>
</body>
</html>
