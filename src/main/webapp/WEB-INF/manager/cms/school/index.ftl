<@ms.html5>
	<@ms.nav title="学校管理"></@ms.nav>
	<@ms.searchForm name="searchForm" isvalidation=true>
		<@ms.text label="名称"  name="name"  title="请输入学校名称"  placeholder="请输入学校名称" value=""   />
		<@ms.searchFormButton>
			<@ms.queryButton onclick="search()"/>								
		</@ms.searchFormButton>
	</@ms.searchForm>
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.buttonGroup>
					<@shiro.hasPermission name="school:save"><@ms.panelNavBtnAdd title="" id="addSchoolBtn"/></@shiro.hasPermission> 
					<@shiro.hasPermission name="school:del"><@ms.panelNavBtnDel title="" id="delSchoolBtn"/></@shiro.hasPermission> 
				</@ms.buttonGroup>
			</@ms.panelNav>
		</div>
		<table id="schoolList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="get" 
			data-pagination="true"
			data-page-size="10"
			data-side-pagination="server">
		</table>
	</@ms.panel>
	
	<@ms.modal  modalName="delSchool" title="数据删除" >
		<@ms.modalBody>删除
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button class="btn btn-danger rightDelete" value="删除"  id="deleteSchoolBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	$(function(){
		$("#schoolList").bootstrapTable({
			url:"${managerPath}/cms/school/list.do",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
	    	columns: [{ checkbox: true},
				    	{
				        	field: 'id',
				        	title: '编号',
				        	width:'10',
				        	align: 'center',
				        	formatter:function(value,row,index) {
				        		<@shiro.hasPermission name="school:update">	        
					        	var url = "${managerPath}/cms/school/form.do?id="+row.id;
				        		return "<a href=" +url+ " target='_self'>" + value + "</a>";
					    		</@shiro.hasPermission> 
					    		<@shiro.lacksPermission name="school:update">
					    			return value;
					    		</@shiro.lacksPermission>
				        	}
				    	},	{
				        	field: 'name',
				        	title: '名称',
                    		align: 'center',
				        	width:'60'
				    	},	{
				        	field: 'place',
				        	title: '地址',
				        	width:'230'
				    	},	{
				        	field: 'setupTime',
				        	title: '建立时间',
				        	width:'100'
				    	}	,	{
				        	field: 'createTime',
				        	title: '创建时间',
				        	width:'100',
				        	align: 'center'
				    	}	,	{
				        	field: 'updateTime',
				        	title: '更新时间',
				        	width: '100',
				        	align: 'center'
				    	}			]
	    })
	})
	
	
	//增加按钮
	$("#addSchoolBtn").click(function(){
		location.href ="${managerPath}/cms/school/form.do"; 
	})
	//删除按钮
	$("#delSchoolBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#schoolList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delSchool").modal();
		}
	})
	
	$("#deleteSchoolBtn").click(function(){
		var rows = $("#schoolList").bootstrapTable("getSelections");
		$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/cms/school/delete.do",
			data: JSON.stringify(rows),
			dataType: "json",
			contentType: "application/json",
			success:function(msg) {
				if(msg.result == true) {
					<@ms.notify msg= "删除成功" type= "success" />
				}else {
					<@ms.notify msg= "删除失败" type= "fail" />
				}
				location.reload();
			}
		})
	});
	//查询功能
	function search(){
		var search = $("form[name='searchForm']").serializeJSON();
        var params = $('#schoolList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#schoolList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
</script>