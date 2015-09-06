$.modal("customer",function(){
	var customer={
		currentPage:1,
		pageSize:20,
		searchUrl:"",
		searchDto:{}		
	}
	
	$("#updateModal").on("hidden.bs.modal", function() {
	    $(this).removeData("bs.modal");
	});
	
	
	$("#whoseCustomerSelect").on("change",function(){
		if($("#whoseCustomerSelect").val()=="自己的客户")
		{			
			customer.searchUrl="customer/self/page/";
		}
		else
		{
			customer.searchUrl="customer/all/page/";
		}
	});
	
	$("#whoseCustomerSelect").change();
	
	
	$("#searchCustomerBtn").on("click",function(){
		customer.skip(1);
	});
	
	customer.skip=function(pageNo) {
		customer.currentPage = pageNo;
		$("#listDiv").loadContent(customer.searchUrl + customer.pageSize+"/" + pageNo,customer.searchDto);
	}
	//----------------------
	//这
	$("#addCustomerBtn").on("click",function(){
		$("#addModal").modal({
			backdrop:"static",
			show:true,
			remote:"house/toAdd"
		});
	});
	
	
	house.updateHouse=function(id){
		$("#updateModal").modal({
			backdrop:"static",
			show:true,
			remote:"house/toUpdate/" + id
		});
	};
	
	house.saveHouse=function(){
		$("#addForm").formAjaxSubmit(function(){
			$.alertSuccess("保存成功",function(){
				$("#addModal").modal("hide");
				house.skip(house.currentPage);
			});			
		});
	}
	house.saveUpdateHouse=function(){
		$("#updateForm").formAjaxSubmit(function(){
			$.alertSuccess("保存成功",function(){
				$("#updateModal").modal("hide");
				house.skip(house.currentPage);
			});	
		});
	}
	
	house.deleteHouse=function(id)
	{
		$.alertConfirm("确定删除？",function(result){
			if(result)
			{
				$.getRequest("delete/ajax/" + id, function(response) {
						$.alertSuccess("删除成功!",function(){
							house.skip(house.currentPage);
						});						
				});
			}
		});
	}
	house.skip(1);
	return house;
});
