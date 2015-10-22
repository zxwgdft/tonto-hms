<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered">
	<thead>
		<tr >
			<th style="text-align: center">#</th>
			<th style="text-align: center">楼盘名称</th>
			<th style="text-align: center">所在城市</th>
			<th style="text-align: center">所在街道</th>
			<th style="text-align: center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${loupans}" var="loupan" varStatus="status">
			<tr align="center">
				<td>${status.count}</td>
				<td>${loupan.loupanName}</td>
				<td>${loupan.fullDistrict}</td>
				<td>${loupan.street}</td>
				<td><a href="javascript:loupan.update(${loupan.id})">修改</a> <a
					style="margin-left:10px"
					href="javascript:loupan.remove(${loupan.id})">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div align="center" id="pager"></div>
<script type="text/javascript">
		var loupan=$.model("loupan");
	 	loupan.page_options = {
            	currentPage: ${pager.pageNum},
            	totalPages: ${pager.pages},
            	pageUrl: function(type, page, current){               
            		return "javascript:loupan.skip("+page+")";
            	}
        }
		$("#pager").bootstrapPaginator(loupan.page_options);
</script>