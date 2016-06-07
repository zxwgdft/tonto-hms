<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="container" style="width:100%">
	<div class="row">
		<label class="col-md-1 form-control-static text-right" for="searchloupanName">楼盘名称</label>
		<div class="col-md-4">				
			<input class="form-control" id="searchloupanName" type="text"
				placeholder="请输入楼盘名称" />
		</div>
		<label class="col-md-1 form-control-static text-right" for="searchDistrictCode">楼盘地区</label>		
		<div id="searchDistrictDiv" class="col-md-2">
			<input type="hidden" id="searchDistrictCode">
			<button type="button" class="btn btn-info btn-select" id="selectDistrictToSearchBtn">全部&nbsp;<span class="caret"></span></button>
		</div>
		<div class="col-md-2">
			<button class="btn btn-block btn-primary btn-md" id="searchLoupanBtn">
				<span class="glyphicon glyphicon-search"></span> 查询
			</button>
		</div>
		<div class="col-md-2">
			<button class="btn btn-block btn-primary btn-md"
				id="addLoupanBtn">
				<span class="glyphicon glyphicon-plus"></span> 添加楼盘
			</button>
		</div>
		
	</div>
	<br>		
	
	<div id="listDiv"></div>	
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="updateModal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
<script type="text/javascript">
	$.required("js/page/loupan/loupan.js");
</script>