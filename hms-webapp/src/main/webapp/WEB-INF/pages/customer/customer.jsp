<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="container" style="width:100%">
	<form id="searchForm" action="page/1" method="post"
		class="form-horizontal" role="form">
			<div class="row">
				<div class="col-md-3">
			  
			    </div>
				<div class="col-md-5">
				</div>
				
				<div class="col-md-2">
					<button class="btn btn-block btn-primary btn-md" type="button" id="searchCustomerBtn">
						<span class="glyphicon glyphicon-search"></span> 查询
					</button>
				</div>
				<div class="col-md-2">
					<button class="btn btn-block btn-primary btn-md" type="button"
						id="addCustomerBtn">
						<span class="glyphicon glyphicon-plus"></span> 添加客户
					</button>
				</div>
			</div>	
	</form>
	<br>		
	
	<div id="listDiv"></div>	
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="updateModal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="addModal" >
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$.required("js/page/customer/customer.js");
</script>