<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">修改楼盘</h4>
</div>
<div class="modal-body">
<form id="updateForm" action="loupan/update/ajax" method="post"
	class="form-horizontal" role="form">
	<input type="hidden" name="id" value="${loupan.id}">
	<div class="form-group">
		<label for="loupanName" class="col-sm-3 control-label">楼盘名称</label>
		<div class="col-sm-7">
			<input type="text" class="form-control required" id="loupanName" value="${loupan.loupanName}"
				name="loupanName" placeholder="请输入楼盘名称" data-length=",50">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">楼盘地区</label>
		<div class="col-sm-7">
			<input type="hidden" id="updateDistrictCode" class="required" name="districtCode" value="${loupan.districtCode}" />
			<button type="button" class="btn btn-info btn-select" id="selectDistrictBtn">请选择地区<span class="caret"></span></button><br>								
		</div>
	</div>
	<div class="form-group">
		<label for="street" class="col-sm-3 control-label">街道名称</label>
		<div class="col-sm-7">
			<input type="text" class="form-control" id="street" name="street" value="${loupan.street}"
				placeholder="请输入街道名称" data-length=",50">
		</div>
	</div>	
</form>
</div>
<div class="modal-footer">
	<button id="saveUpdateLoupanBtn" class="btn btn-primary">保存</button>
	<button id="closeBtn" type="button" class="btn btn-default" 
		data-dismiss="modal">关闭</button>		
</div>
<script type="text/javascript">

var loupan=$.modal("loupan");
loupan.updateDistrict = new tonto.district.District("#selectDistrictBtn");
loupan.updateDistrict.toDistrict($("#updateDistrictCode").val());
loupan.updateDistrict.addEventListener("checked", function(obj){
	$("#updateDistrictCode").val(obj.key);
});	
$("#saveUpdateLoupanBtn").on("click",loupan.saveUpdateLoupan);
$("#updateForm").createFormValidate({
	messages:{
		districtCode:{
			required:"请选择一个区域"
		}
	}
});
</script>