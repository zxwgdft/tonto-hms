<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">添加房源</h4>
</div>
<div class="modal-body">
	<form id="updateForm" action="house/update/ajax" method="post"
		class="form-horizontal" role="form">
		<input type="hidden" name="id" value="${house.id}">
		<div class="form-group">
			<label for="updateHouseName" class="col-sm-3 control-label">房源名称</label>
			<div class="col-sm-7">
				<input type="text" class="form-control required" id="updateHouseName" value="${house.houseName}"
					name="houseName" placeholder="请输入房源名称" data-length=",50">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">楼盘</label>
			<div class="col-sm-7">
				<div class="input-group">
					<input type="text" id="updateLoupanName" class="form-control" value="${house.loupan.loupanName}" readonly/>
					<input type="hidden" id="updateLoupanId" class="required form-control" name="loupanId"  value="${house.loupanId}"/>
					<div class="input-group-btn btn-group">
		        		<button type="button" class="btn btn-info btn-select" id="updateLoupanSelectorBtn" onclick="selectLoupan()">请选择区域<span class="caret"></span></button>	        		
		        	</div>
		        </div>        
			</div>
		</div>
		<div class="form-group">
			<label for="updateHouseholderName" class="col-sm-3 control-label">房主姓名</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="updateHouseholderName" name="householderName" value="${house.householderName}"
					placeholder="请输入房主姓名" data-length=",20">
			</div>
		</div>
		<div class="form-group">
			<label for="updateHouseholderPhone" class="col-sm-3 control-label">房子电话</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="updateHouseholderPhone" name="householderPhone" value="${house.householderPhone}"
					placeholder="请输入房主电话" data-length=",20" data-type="phone">
			</div>
		</div>
		<div class="form-group">
			<label for="updateHouseholderCellphone" class="col-sm-3 control-label">房子手机</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="updateHouseholderCellphone" name="householderCellphone" value="${house.householderCellphone}"
					placeholder="请输入房主手机" data-length=",20" data-type="cellphone">
			</div>
		</div>		
	</form>
</div>
<div class="modal-footer" style="text-align:center">
	<button id="saveUpdateHouseBtn" type="button" class="btn btn-primary">保存</button>
	<button id="closeBtn" type="button" class="btn btn-default" 
		data-dismiss="modal">关闭</button>		
</div>

<script type="text/javascript">
var house=$.modal("house");
house.loupanSelector=new tonto.loupan.Selector("#updateLoupanSelectorBtn");
house.loupanSelector.addEventListener("checked",function(item){
	$("#updateLoupanName").val(item.loupanName);
	$("#updateLoupanId").val(item.id);
});
$("#updateForm").createFormValidate({
	messages:{
		loupanId:{
			required:"请选择一个楼盘"
		}
	}
}); 
$("#saveUpdateHouseBtn").on("click",house.saveUpdateHouse);
</script>
