<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>
<c:choose>
	<c:when test="${list== null || fn:length(list) == 0}">
		<c:forEach items="${houses}" var="house" varStatus="status">
			<div class="row" style="border-bottom:1px solid #333333;margin-top:10px;">
				<div class="col-md-2">
					<img alt="" width="100px" src="<%=path%>/image/house.png">
				</div>
				<div class="col-md-10">
					<div class="row">
						<label class="col-md-2">房屋名称:</label>
						<label class="col-md-3">${house.houseName}</label>
						<label class="col-md-2">户主姓名:</label>
						<label class="col-md-3">${house.householderName}</label>
					</div>
					<div class="row">
							<label class="col-md-2">户主电话:</label>
							<label class="col-md-3">${house.householderPhone}</label>
							<label class="col-md-2">所在地区:</label>
							<label class="col-md-3">${house.fullDistrictName}</label>
					</div>
					<div class="row">
							<label class="col-md-2">楼盘名称:</label>
							<label class="col-md-3">${house.loupanName}</label>
					</div>
					<p align="right"><a href="javascript:house.update(${house.id})">修改</a> 
					<a style="margin-left:10px" href="javascript:house.remove(${house.id})">删除</a></p>			
				</div>				
			</div>
		</c:forEach>
		
		<div align="center" id="housePager"></div>
		<script type="text/javascript">
				var house=$.model("house");
			 	house.page_options = {
		            	currentPage: ${pager.pageNum},
		            	totalPages: ${pager.pages},
		            	pageUrl: function(type, page, current){               
		            		return "javascript:house.skip("+page+")";
		            	}
		        }
				$("#housePager").bootstrapPaginator(house.page_options);
		</script>
	</c:when>
	<c:otherwise>
		<div class="no-data-container">没有楼盘信息</div>
		
	</c:otherwise>
</c:choose>