<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered">
	<thead>
		<tr >
			<th style="text-align: center">#</th>
			<th style="text-align: center">客户姓名</th>
			<th style="text-align: center">手机</th>
			<th style="text-align: center">固定电话</th>
			<th style="text-align: center">地址</th>
			<th style="text-align: center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${customers}" var="customer" varStatus="status">
			<tr align="center">
				<td>${status.count}</td>
				<td>${customer.customerName}</td>
				<td>${customer.cellphone}</td>
				<td>${customer.phone}</td>
				<td>${customer.address}</td>
				<td><a href="javascript:customer.update(${customer.id})">修改</a> <a
					style="margin-left:10px"
					href="javascript:customer.remove(${customer.id})">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div align="center" id="pager"></div>
<script type="text/javascript">
		var customer=$.model("customer");

	 	customer.page_options = {
            	currentPage: ${pager.pageNum},
            	totalPages: ${pager.pages},
            	pageUrl: function(type, page, current){               
            		return "javascript:customer.skip("+page+")";
            	}
        }
		$("#pager").bootstrapPaginator(customer.page_options);
</script>