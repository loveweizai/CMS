<@ms.html5>
    <@ms.nav title="招聘管理"></@ms.nav>
    <@ms.searchForm name="searchForm" isvalidation=true>
        <@ms.text label="职位"  name="position"  title="请输入职位名称"  placeholder="请输入职位名称" value=""   />
        <@ms.searchFormButton>
            <@ms.queryButton onclick="search()"/>
        </@ms.searchFormButton>
    </@ms.searchForm>
    <@ms.panel>
    <div id="toolbar">
        <@ms.panelNav>
				<@ms.buttonGroup>
            <#--<@shiro.hasPermission name="recruitmentInfo:save"><@ms.panelNavBtnAdd title="" id="addCompanyBtn"/></@shiro.hasPermission>-->
            <#--<@shiro.hasPermission name="recruitmentInfo:del"><@ms.panelNavBtnDel title="" id="delCompanyBtn"/></@shiro.hasPermission>-->
        </@ms.buttonGroup>
			</@ms.panelNav>
    </div>
    <table id="recruitmentInfoList"
           data-show-refresh="true"
           data-show-columns="true"
           data-show-export="true"
           data-method="get"
           data-pagination="true"
           data-page-size="10"
           data-side-pagination="server">
    </table>
    </@ms.panel>

    <@ms.modal  modalName="delCompany" title="数据删除" >
        <@ms.modalBody>删除
            <@ms.modalButton>
            <!--模态框按钮组-->
                <@ms.button class="btn btn-danger rightDelete" value="删除"  id="deleteCompanyBtn"  />
            </@ms.modalButton>
        </@ms.modalBody>
    </@ms.modal>
</@ms.html5>

<script>
    $(function () {
        $("#recruitmentInfoList").bootstrapTable({
            url: "${managerPath}/cms/recruitmentInfo/list.do",
            contentType: "application/x-www-form-urlencoded",
            queryParamsType: "undefined",
            toolbar: "#toolbar",
            columns: [{checkbox: true},
                {
                    field: 'id',
                    title: '编号',
                    width: '10',
                    align: 'center',
                    formatter: function (value, row, index) {
                    <@shiro.hasPermission name="recruitmentInfo:update">
                        var url = "${managerPath}/cms/recruitmentInfo/form.do?id=" + row.id;
                        return "<a href=" + url + " target='_self'>" + value + "</a>";
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="recruitmentInfo:update">
                        return value;
                    </@shiro.lacksPermission>
                    }
                }, {
                    field: 'position',
                    title: '职位名称',
                    align: 'center',
                    width: '100'
                }, {
                    field: 'recruitmentType',
                    title: '招聘类型',
                    align: 'center',
                    width: '100',
                    formatter:function(value,row,index) {
				        	//01：校园招聘，02：社会招聘
				        		if(value == '01'){
				        			return "校园招聘";
				        		}else if(value == '02'){
				        			return "社会招聘";
				        		}
				        	}
                }, {
                    field: 'code',
                    title: '用工单位',
                    align: 'center',
                    width: '100'
                }, {
                    field: 'duty',
                    title: '岗位职责',
                    align: 'center',
                    width: '150'
                }, {
                    field: 'condition',
                    title: '入职条件',
                    align: 'center',
                    width: '150'
                }, {
                    field: 'salary',
                    title: '薪资待遇',
                    align: 'center',
                    width: '150'
                }, {
                    field: 'number',
                    title: '招聘人数',
                    align: 'center',
                    width: '30'
                }, {
                    field: 'status',
                    title: '记录状态',
                    align: 'center',
                    width: '30'
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    width: '100',
                    align: 'center'
                }, {
                    field: 'updateTime',
                    title: '更新时间',
                    width: '100',
                    align: 'center'
                }]
        })
    })


    //增加按钮
    $("#addCompanyBtn").click(function () {
        location.href = "${managerPath}/cms/recruitmentInfo/form.do";
    })
    //删除按钮
    $("#delCompanyBtn").click(function () {
        //获取checkbox选中的数据
        var rows = $("#recruitmentInfoList").bootstrapTable("getSelections");
        //没有选中checkbox
        if (rows.length <= 0) {
        <@ms.notify msg="请选择需要删除的记录" type="warning"/>
        } else {
            $(".delCompany").modal();
        }
    })

    $("#deleteCompanyBtn").click(function () {
        var rows = $("#recruitmentInfoList").bootstrapTable("getSelections");
        $(this).text("正在删除...");
        $(this).attr("disabled", "true");
        $.ajax({
            type: "post",
            url: "${managerPath}/cms/recruitmentInfo/delete.do",
            data: JSON.stringify(rows),
            dataType: "json",
            contentType: "application/json",
            success: function (msg) {
                if (msg.result == true) {
                <@ms.notify msg= "删除成功" type= "success" />
                } else {
                <@ms.notify msg= "删除失败" type= "fail" />
                }
                location.reload();
            }
        })
    });
    //查询功能
    function search() {
        var search = $("form[name='searchForm']").serializeJSON();
        var params = $('#recruitmentInfoList').bootstrapTable('getOptions');
        params.queryParams = function (params) {
            $.extend(params, search);
            return params;
        }
        $("#recruitmentInfoList").bootstrapTable('refresh', {query: $("form[name='searchForm']").serializeJSON()});
    }
</script>