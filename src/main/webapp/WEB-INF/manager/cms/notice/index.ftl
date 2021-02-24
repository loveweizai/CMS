<@ms.html5>
	<@ms.nav title="公告管理"></@ms.nav>
	<@ms.searchForm name="searchForm" isvalidation=true>
		<@ms.text label="标题"  name="title"  title="请输标题"  placeholder="请输入标题" value=""   />	
		<@ms.text label="作者"  name="author"  title="请输标题"  placeholder="请输入标题" value=""   />					 			  
		<#assign type=[{"id":"","name":"全部"},{"id":"0","name":"普通通知"},{"id":"1","name":"社会招聘通知"},{"id":"1","name":"校园招聘通知"}]>
		<@ms.select label="类型" list=type listValue="name" listKey="id"    name="showName" value="" />
		<#assign status=[{"id":"","name":"全部"},{"id":"0","name":"有效"},{"id":"1","name":"无效"}]>`
		<@ms.select label="状态" list=status listValue="name" listKey="id"    name="status" value="" />
		<@ms.searchFormButton>
			<@ms.queryButton onclick="search()"/>								
		</@ms.searchFormButton>
	</@ms.searchForm>
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.buttonGroup>
					<@shiro.hasPermission name="notice:save"><@ms.panelNavBtnAdd title="" id="addNoticeBtn"/></@shiro.hasPermission> 
					<@shiro.hasPermission name="notice:del"><@ms.panelNavBtnDel title="" id="delNoticeBtn"/></@shiro.hasPermission> 
				</@ms.buttonGroup>
			</@ms.panelNav>
		</div>
		<table id="noticeList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="get" 
			data-pagination="true"
			data-page-size="10"
			data-side-pagination="server">
		</table>
	</@ms.panel>
	
	<@ms.modal  modalName="delNotice" title="数据删除" >
		<@ms.modalBody>删除
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button class="btn btn-danger rightDelete" value="删除"  id="deleteNoticeBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	$(function(){
		$("#noticeList").bootstrapTable({
			url:"${managerPath}/cms/notice/list.do",
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
				        		<@shiro.hasPermission name="notice:update">	        
					        	var url = "${managerPath}/cms/notice/form.do?id="+row.id;
				        		return "<a href=" +url+ " target='_self'>" + value + "</a>";
					    		</@shiro.hasPermission> 
					    		<@shiro.lacksPermission name="notice:update">
					    			return value;
					    		</@shiro.lacksPermission>
				        	}
				    	},	{
				        	field: 'title',
				        	title: '标题',
				        	width:'60'
				    	},	{
				        	field: 'author',
				        	title: '作者',
				        	width:'100'
				    	},	{
				        	field: 'releaseTime',
				        	title: '发布时间',
				        	width: '20',
				        	align: 'center'
				    	}	,	{
				        	field: 'count',
				        	title: '次数',
				        	width: '20',
				        	align: 'center'
				    	}	,	{
				        	field: 'comeFrom',
				        	title: '来源',
				        	width: '20',
				        	align: 'center'
				    	}	,	{
				        	field: 'type',
				        	title: '类型',
				        	width: '20',
				        	align: 'center',
				        	formatter:function(value,row,index) {
				        	//0：普通通知；1：社会招聘通知，2：校园招聘通知
				        		if(value == 0){
				        			return "普通通知";
				        		}else if(value == 1){
				        			return "社会招聘通知";
				        		}else if(value == 2){
				        			return "校园招聘通知";
				        		}
				        	}
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
	$("#addNoticeBtn").click(function(){
		location.href ="${managerPath}/cms/notice/form.do"; 
	})
	//删除按钮
	$("#delNoticeBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#noticeList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delNotice").modal();
		}
	})
	
	$("#deleteNoticeBtn").click(function(){
		var rows = $("#noticeList").bootstrapTable("getSelections");
		$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/cms/notice/delete.do",
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
        var params = $('#noticeList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#noticeList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
</script>