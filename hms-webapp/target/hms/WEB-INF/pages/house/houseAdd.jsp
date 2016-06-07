<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">添加房源</h4>
</div>
<div class="modal-body">
	<form id="addForm" action="house/add/ajax" method="post"
		class="form-horizontal" role="form">
		<div class="form-group">
			<label for="addHouseName" class="col-sm-3 control-label">房源名称</label>
			<div class="col-sm-7">
				<input type="text" class="form-control required" id="addHouseName"
					name="houseName" placeholder="请输入房源名称" data-length=",50">
			</div>
		</div>
		<div class="form-group">
			<label for="addLoupanSelectorBtn" class="col-sm-3 control-label">楼盘</label>
			<div class="col-sm-7">
				<div class="input-group">
					<input type="text" id="addLoupanName" class="form-control" readonly/>
					<input type="hidden" id="addLoupanId" class="required form-control" name="loupanId" />
					<div class="input-group-btn btn-group">
		        		<button type="button" class="btn btn-info btn-select" id="addLoupanSelectorBtn">选择楼盘<span class="caret"></span></button>	        		
		        	</div>
		        </div>        
			</div>
		</div>
		<div class="form-group">
			<label for="addHouseholderName" class="col-sm-3 control-label">房主姓名</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="addHouseholderName" name="householderName"
					placeholder="请输入房主姓名" data-length=",20">
			</div>
		</div>
		<div class="form-group">
			<label for="addHouseholderPhone" class="col-sm-3 control-label">房子电话</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="addHouseholderPhone" name="householderPhone"
					placeholder="请输入房主电话" data-length=",20" data-type="phone">
			</div>
		</div>
		<div class="form-group">
			<label for="addHouseholderCellphone" class="col-sm-3 control-label">房子手机</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="addHouseholderCellphone" name="householderCellphone"
					placeholder="请输入房主手机" data-length=",20" data-type="cellphone">
			</div>
		</div>
		
		
	</form>
</div>
<div class="modal-footer" style="text-align:center">
	<button id="saveHouseBtn" type="button" class="btn btn-primary">保存</button>
	<button id="closeBtn" type="button" class="btn btn-default" 
		data-dismiss="modal">关闭</button>		
</div>

<script type="text/javascript">
var house=$.model("house");
house.loupanSelector=new tonto.loupan.Selector("#addLoupanSelectorBtn");
house.loupanSelector.addEventListener("checked",function(item){
	$("#addLoupanName").val(item.loupanName);
	$("#addLoupanId").val(item.id);
});
$("#addForm").createFormValidate({
	messages:{
		loupanId:{
			required:"请选择一个楼盘"
		}
	}
}); 
$("#saveHouseBtn").on("click",house.saveAdd);
</script>
