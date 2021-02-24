<@ms.html5>
    <@ms.nav title="单位管理"></@ms.nav>
    <@ms.searchForm name="searchForm" isvalidation=true>
        <@ms.text label="单位名称"  name="name"  title="请输入单位名称"  placeholder="请输入单位名称" value=""   />
        <@ms.text label="单位编码"  name="code"  title="请输入单位编码"  placeholder="请输入单位编码" value=""   />
        <@ms.searchFormButton>
            <@ms.queryButton onclick="search()"/>
        </@ms.searchFormButton>
    </@ms.searchForm>
    <@ms.panel>
    <div id="toolbar">
        <@ms.panelNav>
				<@ms.buttonGroup>
            <@shiro.hasPermission name="company:save"><@ms.panelNavBtnAdd title="" id="addCompanyBtn"/></@shiro.hasPermission>
            <@shiro.hasPermission name="company:del"><@ms.panelNavBtnDel title="" id="delCompanyBtn"/></@shiro.hasPermission>
        </@ms.buttonGroup>
			</@ms.panelNav>
    </div>
    <table id="companyList"
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
        $("#companyList").bootstrapTable({
            url: "${managerPath}/cms/company/list.do",
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
                    <@shiro.hasPermission name="company:update">
                        var url = "${managerPath}/cms/company/form.do?id=" + row.id;
                        return "<a href=" + url + " target='_self'>" + value + "</a>";
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="company:update">
                        return value;
                    </@shiro.lacksPermission>
                    }
                }, {
                    field: 'name',
                    title: '单位名称',
                    align: 'center',
                    width: '100'
                }, {
                    field: 'code',
                    title: '单位编码',
                    align: 'center',
                    width: '50'
                }, {
                    field: 'address',
                    title: '单位地址',
                    align: 'center',
                    width: '150'
                }, {
                    field: 'introduce',
                    title: '简介',
                    align: 'center',
                    width: '150'
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    width: '100',
                    align: 'center'
                }, {
                    field: 'updateTime',
                    title: '排序时间',
                    width: '100',
                    align: 'center'
                }]
        })
    })


    //增加按钮
    $("#addCompanyBtn").click(function () {
        location.href = "${managerPath}/cms/company/form.do";
    })
    //删除按钮
    $("#delCompanyBtn").click(function () {
        //获取checkbox选中的数据
        var rows = $("#companyList").bootstrapTable("getSelections");
        //没有选中checkbox
        if (rows.length <= 0) {
        <@ms.notify msg="请选择需要删除的记录" type="warning"/>
        } else {
            $(".delCompany").modal();
        }
    })

    $("#deleteCompanyBtn").click(function () {
        var rows = $("#companyList").bootstrapTable("getSelections");
        $(this).text("正在删除...");
        $(this).attr("disabled", "true");
        $.ajax({
            type: "post",
            url: "${managerPath}/cms/company/delete.do",
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
        var params = $('#companyList').bootstrapTable('getOptions');
        params.queryParams = function (params) {
            $.extend(params, search);
            return params;
        }
        $("#companyList").bootstrapTable('refresh', {query: $("form[name='searchForm']").serializeJSON()});
    }
</script>