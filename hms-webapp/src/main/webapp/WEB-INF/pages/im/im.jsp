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
<script src="${path}/js/common/model.js"></script>
<script src="${path}/js/common/district.js"></script>
<script src="${path}/js/common/district_ui.js"></script>
<script src="${path}/js/common/loupan_select_ui.js"></script>
<script src="${path}/js/im/strophe.js"></script>
<script src="${path}/js/im/easemob.im-1.1.1.js"></script>
<script src="${path}/js/im/easemob.im.shim.js"></script>
<script src="${path}/js/im/im.js"></script>



<script type="text/javascript">
	function openIM(){
		tonto.im.login($("#userAccount").val());
	
	}
</script>
</head>

<body>
	<div>
		<input type="text" id="userAccount">
		<button onclick="openIM()">点击</button>
	</div>
</body>
</html>
