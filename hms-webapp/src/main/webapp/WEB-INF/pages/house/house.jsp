<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="container" style="width:100%">
	<form id="searchForm" action="page/1" method="post"
		class="form-horizontal" role="form">
			<div class="row">
				<div class="col-md-3">
			     	<select id="searchTypeSelect" class="selectpicker">
						<option>选择地区</option>
						<option>选择楼盘</option>
					</select>
			    </div>
				<div id="searchDistrictDiv" class="col-md-5">
					<button type="button" class="btn btn-info btn-select" id="selectDistrictToSearchBtn">全部<span class="caret"></span></button>	        											
				</div>
				<div id="searchLoupanDiv" style="display:none" class="col-md-5 btn-group">
					<button type="button" class="btn btn-info btn-select" id="selectLoupanToSearchBtn">全部<span class="caret"></span></button>	        						
				</div>
				<div class="col-md-2">
					<button class="btn btn-block btn-primary btn-md" type="button" id="searchHouseBtn">
						<span class="glyphicon glyphicon-search"></span> 查询
					</button>
				</div>
				<div class="col-md-2">
					<button class="btn btn-block btn-primary btn-md" type="button"
						id="addHouseBtn">
						<span class="glyphicon glyphicon-plus"></span> 添加房源
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
	$.required("js/page/house/house.js");
</script>