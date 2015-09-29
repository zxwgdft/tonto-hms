<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../common/path.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录</title>
<jsp:include page="../common/head.jsp"></jsp:include>
</head>

<body>
	<div class="container">
		<div class="modal-dialog" style="width:500px;margin-top:200px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">登录</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="loginForm" action="login"
						method="post" role="form">
						<div class="form-group">
							<label for="username" class="col-md-3 control-label">用户名</label>
							<div class="col-md-7">
							<div class="input-group">
								<input type="text" id="username" name="username"
									placeholder="请输入用户名" class="form-control required"> <span
									class="input-group-addon"> <span
									class="glyphicon glyphicon-user"></span>
								</span>
							</div>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-md-3 control-label">用户名</label>
							<div class="col-md-7">
								<div class="input-group">
									<input type="password" id="password" name="password"
										placeholder="请输入密码" class="form-control required"> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-lock"></span>
									</span>
								</div>
							</div>
						</div>
					</form>
					<div align="center">
						<button class="btn btn-primary btn-lg" type="button"
							style="width:100px" onclick="login()">登录</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function login() {
		var $form = $("#loginForm");
		$form.ajaxSubmit({
			beforeSubmit : function() {
				return $form.valid();
			},
			success : function(response) {
				response = $.toJsonObject(response);
				if (response && response.status == 1) {
					window.location.href = "main";
					return;
				} else {
					var msg = response && response.msg || "登录失败";
					$.alertError(msg);
					return;
				}
			}
		});
	}
</script>
</html>
