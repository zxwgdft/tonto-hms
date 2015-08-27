$.modal("house",function(){
	var house={
		currentPage:1,
		searchDto:{}		
	}
	
	house.loupanSearchSelector=new tonto.loupan.Selector("#selectLoupanToSearchBtn",{allBtn:true,showBtn:true});
	house.loupanSearchSelector.addEventListener("checked",function(item){
		house.searchDto.loupanId=item.id;
	});
	house.searchDistrict = new tonto.district.District("#selectDistrictToSearchBtn",{single:false,showBtn:true});
	house.searchDistrict.addEventListener("checked", function(obj) {			
		house.searchDto.districtId=obj.key==-1?undefined:obj.key;
	});		
	
	$("#updateModal").on("hidden.bs.modal", function() {
	    $(this).removeData("bs.modal");
	});
	
	
	$("#searchTypeSelect").on("change",function(){
		if($("#searchTypeSelect").val()=="选择地区")
		{
			$("#searchDistrictDiv").show();
			$("#searchLoupanDiv").hide();	
		}
		else
		{
			$("#searchDistrictDiv").hide();
			$("#searchLoupanDiv").show();	
		}
	});
	$("#searchTypeSelect").change();
	
	
	$("#searchHouseBtn").on("click",function(){
		
		if($("#searchTypeSelect").val()=="选择地区")
		{
			house.searchDto.loupanId=undefined;
		}
		else
		{
			house.searchDto.districtId=undefined;
		}
		house.skip(1);
	});
	
	house.skip=function(pageNo) {
		house.currentPage = pageNo;
		$("#listDiv").loadContent("house/page/" + pageNo,house.searchDto);
	}
	
	$("#addHouseBtn").on("click",function(){
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
		},function(response){
			var msg="保存出错！";
			msg+=response&&response.msg||"";
			$.alertError(msg);
		});
	}
	house.saveUpdateHouse=function(){
		$("#updateForm").formAjaxSubmit(function(){
			$.alertSuccess("保存成功",function(){
				$("#updateModal").modal("hide");
				house.skip(house.currentPage);
			});	
		},function(response){
			var msg="保存出错！";
			msg+=response&&response.msg||"";
			$.alertError(msg);
			return;		
		});
	}
	
	house.deleteHouse=function(id)
	{
		$.alertConfirm("确定删除？",function(result){
			if(result)
			{
				$.get("delete/ajax/" + id, function(response) {
				response = response && JSON.parse(response);
				if (response) {
						if (response.status == -1) {
							$.alertError("删除出错！" + response.msg);
							return;
						}
	
						if (response.status == 1) {
							$.alertSuccess("删除成功!",function(){
								skip(currentPage);
							});						
							return;
						}
					}
					$.alertError("处理异常！");
				});
			}
		});
	}
	house.skip(1);
	return house;
});
