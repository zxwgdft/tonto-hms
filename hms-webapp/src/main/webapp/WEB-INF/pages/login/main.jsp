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
<script src="${path}/js/jquery/jquery.form.js"></script>
<script src="${path}/js/jquery/jquery.validate.min.js"></script>
<script src="${path}/js/jquery/jquery-validate-extend.js"></script>
<script src="${path}/js/jquery/jquery-common.js"></script>
<script src="${path}/js/bootstrap/bootstrap.min.js"></script>
<script src="${path}/js/bootstrap/bootstrap-paginator.min.js"></script>
<script src="${path}/js/bootstrap/bootstrap-select.min.js"></script>
<script src="${path}/js/common/district.js"></script>
<script src="${path}/js/common/district_ui.js"></script>
<script src="${path}/js/common/loupan_select_ui.js"></script>
<script type="text/javascript">
	function main_tab_function(src){
		$("#mainContentDiv").loadContent(src);
	}
</script>
</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<h4>HMS</h4>
	</div>
	<div class="navbar-text navbar-right" style="margin-right:0px">
		${user.nickName}</div>
	</nav>
	<div class="container" style="min-width:1366px;">
		<div class="col-md-2">
			<div class="list-group">
				<a href="javascript:main_tab_function('house/view')" class="list-group-item">房源查看</a>
				<a href="javascript:main_tab_function('loupan/view')" class="list-group-item">楼盘查看</a>
			</div>
		</div>
		<div class="col-md-10 well" id="mainContentDiv">
			
		</div>
	</div>
</body>
</html>
