<@ms.html5>
	<@ms.nav title="轮播图管理"></@ms.nav>
	<@ms.searchForm name="searchForm" isvalidation=true>
		<@ms.text label="名称"  name="name"  title="请输入图片名称"  placeholder="请输入图片名称" value=""   />			 			  
		<#assign status=[{"id":"","name":"全部"},{"id":"0","name":"显示"},{"id":"1","name":"不显示"}]>
		<@ms.select label="是否显示" list=status listValue="name" listKey="id"    name="showName" value="" />
		<#assign status=[{"id":"","name":"全部"},{"id":"0","name":"有效"},{"id":"1","name":"无效"}]>
		<@ms.select label="状态" list=status listValue="name" listKey="id"    name="status" value="" />
		<@ms.searchFormButton>
			<@ms.queryButton onclick="search()"/>								
		</@ms.searchFormButton>
	</@ms.searchForm>
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.buttonGroup>
					<@shiro.hasPermission name="adpage:save"><@ms.panelNavBtnAdd title="" id="addAdpageBtn"/></@shiro.hasPermission> 
					<@shiro.hasPermission name="adpage:del"><@ms.panelNavBtnDel title="" id="delAdpageBtn"/></@shiro.hasPermission> 
				</@ms.buttonGroup>
			</@ms.panelNav>
		</div>
		<table id="adpageList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="get" 
			data-pagination="true"
			data-page-size="10"
			data-side-pagination="server">
		</table>
	</@ms.panel>
	
	<@ms.modal  modalName="delAdpage" title="数据删除" >
		<@ms.modalBody>删除
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button class="btn btn-danger rightDelete" value="删除"  id="deleteAdpageBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	$(function(){
		$("#adpageList").bootstrapTable({
			url:"${managerPath}/cms/adpage/list.do",
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
				        		<@shiro.hasPermission name="adpage:update">	        
					        	var url = "${managerPath}/cms/adpage/form.do?id="+row.id;
				        		return "<a href=" +url+ " target='_self'>" + value + "</a>";
					    		</@shiro.hasPermission> 
					    		<@shiro.lacksPermission name="adpage:update">
					    			return value;
					    		</@shiro.lacksPermission>
				        	}
				    	},	{
				        	field: 'name',
				        	title: '名称',
				        	width:'60'
				    	},	{
				        	field: 'url',
				        	title: '图片地址',
				        	width:'100'
				    	},	{
				        	field: 'link',
				        	title: '外部链接',
				        	width:'130'
				    	}	,	{
				        	field: 'sort',
				        	title: '排序',
				        	width: '20',
				        	align: 'center'
				    	}	,	{
				        	field: 'createTime',
				        	title: '创建时间',
				        	width:'120',
				        	align: 'center'
				    	}	,	{
				        	field: 'updateTime',
				        	title: '更新时间',
				        	width: '120',
				        	align: 'center'
				    	}	,	{
				        	field: 'status',
				        	title: '状态',
				        	width:'50',
				        	align: 'center',
				        	formatter:function(value,row,index) {
				        		if(value == 0){
				        			return "有效";
				        		}else{
				        			return "无效";
				        		}
				        	}
				    	}			]
	    })
	})
	
	
	//增加按钮
	$("#addAdpageBtn").click(function(){
		location.href ="${managerPath}/cms/adpage/form.do"; 
	})
	//删除按钮
	$("#delAdpageBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#adpageList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delAdpage").modal();
		}
	})
	
	$("#deleteAdpageBtn").click(function(){
		var rows = $("#adpageList").bootstrapTable("getSelections");
		$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/cms/adpage/delete.do",
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
        var params = $('#adpageList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#adpageList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
</script>