<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">添加楼盘</h4>
</div>
<div class="modal-body">
	<form id="addForm" action="loupan/add/ajax" method="post"
		class="form-horizontal" role="form">
		<div class="form-group">
			<label for="loupanName" class="col-sm-3 control-label">楼盘名称</label>
			<div class="col-sm-7">
				<input type="text" class="form-control required" id="loupanName"
					name="loupanName" placeholder="请输入楼盘名称" data-length=",50">
			</div>
		</div>
		<div class="form-group">
			<label for="selectDistrictBtn" class="col-sm-3 control-label">楼盘地区</label>
			<div class="col-sm-7">
				<input type="hidden" id="addDistrictCode" class="required" name="districtCode" />
				<button type="button" class="btn btn-info btn-select" id="selectDistrictBtn">请选择地区<span class="caret"></span></button><br>			
			</div>
		</div>
		<div class="form-group">
			<label for="street" class="col-sm-3 control-label">街道名称</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="street" name="street"
					placeholder="请输入街道名称" data-length=",50">
			</div>
		</div>
	</form>
</div>
<div class="modal-footer" style="text-align:center">
	<button id="saveLoupanBtn" class="btn btn-primary">保存</button>
	<button id="closeBtn" type="button" class="btn btn-default" 
		data-dismiss="modal">关闭</button>		
</div>
<script type="text/javascript">
var loupan=$.modal("loupan");						
loupan.addDistrict = new tonto.district.District("#selectDistrictBtn");
loupan.addDistrict.addEventListener("checked", function(obj){
	$("#addDistrictCode").val(obj.key);
});
$("#saveLoupanBtn").on("click",loupan.saveLoupan);
$("#addForm").createFormValidate({
	messages:{
		districtCode:{
			required:"请选择一个区域"
		}
	}
});
</script>
