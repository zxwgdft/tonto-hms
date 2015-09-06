$.modal("loupan",function(){
	var loupan={
		currentPage:1,
		searchDto:{}
	}

	loupan.searchDistrict = new tonto.district.District("#selectDistrictToSearchBtn",
			{single:false,showBtn:true});
	loupan.searchDistrict.addEventListener("checked", function(obj) {
		$("#searchDistrictCode").val(obj.key==-1?"":obj.key);
	});	
	
	$("#updateModal").on("hidden.bs.modal", function() {
	    $(this).removeData("bs.modal");
	});
		
	$("#searchLoupanBtn").on("click",function(){
		var searchDto={};
		var lname=$.trim($("#searchloupanName").val());
		var code=$("#searchDistrictCode").val();
		if(lname)
			searchDto.loupanName=lname;
		if(code)
			searchDto.districtCode=code*1;
		loupan.searchDto=searchDto;
		loupan.skip(1);
	});	

	loupan.skip=function(pageNo) {
		loupan.currentPage = pageNo;
		$("#listDiv").loadContent("loupan/page/" + pageNo,loupan.searchDto);
	}
	
	loupan.addLoupan=function() {
		$("#addModal").modal({
			backdrop:"static",
			show:true,
			remote:"loupan/toAdd"
		}); 
	}
	$("#addLoupanBtn").on("click",loupan.addLoupan);
	

	loupan.saveLoupan=function(){
		$("#addForm").formAjaxSubmit(function(){
			$.alertSuccess("保存成功",function(){
				$("#addModal").modal("hide");
				loupan.skip(loupan.currentPage);
			});			
		});
	}
		
	
	loupan.updateLoupan=function(id) {
		$("#updateModal").modal({
			backdrop:"static",
			show:true,
			remote:"loupan/toUpdate/" + id
		});
	}
	
	loupan.saveUpdateLoupan=function(){
		$("#updateForm").formAjaxSubmit(function(){
			$.alertSuccess("保存成功",function(){
				$("#updateModal").modal("hide");
				loupan.skip(loupan.currentPage);
			});			
		});
	}
	
	loupan.deleteLoupan=function(id) {
		$.alertConfirm("确定删除？",function(result){
			if(result)
			{
				$.getRequest("loupan/delete/ajax/" + id, function(response) {
					if (response.status == 1) {
						$.alertSuccess("删除成功!",function(){
							loupan.skip(loupan.currentPage);
						});						
						return;
					}
				});
			}
		});
	}
	loupan.skip(1);
	return loupan;
});