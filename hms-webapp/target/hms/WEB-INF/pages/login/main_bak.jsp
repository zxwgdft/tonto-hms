<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>HMS</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="${path}/css/bootstrap/bootstrap-select.min.css" rel="stylesheet">
<link href="${path}/css/common.css" rel="stylesheet">
<script src="${path}/js/jquery/jquery.min.js"></script>
<script src="${path}/js/bootstrap/bootstrap.min.js"></script>
<script src="${path}/js/bootstrap/bootstrap-paginator.min.js"></script>
<script src="${path}/js/bootstrap/bootstrap-select.min.js"></script>
<script src="${path}/js/jquery/jquery.form.js"></script>
<script src="${path}/js/jquery/jquery.validate.min.js"></script>
<script src="${path}/js/jquery/jquery-validate-extend.js"></script>
<script src="${path}/js/jquery/jquery-common.js"></script>
<script src="${path}/js/district.js"></script>
<script src="${path}/js/district_ui.js"></script>
<script src="${path}/js/loupan_select_ui.js"></script>
<script type="text/javascript">
		var menu=[
		{
			name:"公司管理",
			src:"#",
			children:[
				{name:"员工管理",src:"#"},
				{name:"业绩考核",src:"#"},
				{name:"考勤管理",src:"#"}]
		},
		{
			name:"房源管理",
			src:"house/view",
			children:[
			{name:"房源查看",src:"house/view"},
			{name:"楼盘查看",src:"loupan/view"}]
		},{
			name:"客户管理",
			src:"#",
			children:[
			{name:"客户查看",src:"house/view"}]
		}];
		$(function(){
			for(var i=0;i<menu.length;i++)
			{
				var li=$("<li "+(i==0?"class='active'":"")+"></li>");
				var a=$("<a href='javascript:void(0)'>"+menu[i].name+"</a>");
				(function(t){
				a.on("click",function(){
					$("#contentDiv").load(t.src);
					tabFunction(t.name);
				})})(menu[i]);
				
				$("#titleDiv").append(li.append(a));
				$("#contentDiv").load("loupan/view");
			}
			
		});
		function tabFunction(t)
		{
			for(var i=0;i<menu.length;i++)
			{
				if(menu[i].name==t)
				{
					$("#menuDiv").empty();
					var d=$("<div class='list-group'></div>");
				
					var m=menu[i],c=m.children;
					for(var j=0;j<c.length;j++)
					{
						var a=$("<a href='javascript:void(0)' class='list-group-item"+(j==0?" active":"")+"'>"+c[j].name+"</a>");
						(function(t){
							$("#contentDiv").load(t.src);
						})(c[j]);
						d.append(a);
					}
					
					$("#menuDiv").append(d);
					return;
				}
			}
		}
	  
	</script>
</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">功能</a>
	</div>
<!-- 	<div>
		<ul class="nav navbar-nav" id="titleDiv">
		</ul>
	</div> -->
	<div class="navbar-text navbar-right" style="margin-right:0px">
		${user.nickName}</div>
	</nav>
	<div class="container" style="min-width:1366px;">
		<div class="col-md-2" id="menuDiv"></div>
		<div class="col-md-10 well" id="contentDiv">
			
		</div>
	</div>
</body>
</html>
