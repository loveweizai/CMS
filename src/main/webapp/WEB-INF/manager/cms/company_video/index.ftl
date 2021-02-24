<@ms.html5>
    <@ms.nav title="单位管理"></@ms.nav>
    <@ms.searchForm name="searchForm" isvalidation=true>
        <@ms.text label="视频名称"  name="videoName"  title="请输入视频名称"  placeholder="请输入视频名称" value=""   />
        <#if companyList?has_content>
            <@ms.select label="单位名称" default="全部" name="companyCode" id="forumSelect"  list=companyList  listValue="name"   listKey="code"    value="${companyVideo.companyCode?default('')}" width="240"  />
        <#else>
            <@ms.select label="单位名称" name="companyCode" id="forumSelect" value="" list=[{"code":"-1","name":"请选择单位名称"}]  width="240" />
        </#if>
        <@ms.searchFormButton>
            <@ms.queryButton onclick="search()"/>
        </@ms.searchFormButton>
    </@ms.searchForm>
    <@ms.panel>
    <div id="toolbar">
        <@ms.panelNav>
				<@ms.buttonGroup>
            <@shiro.hasPermission name="companyVideo:save"><@ms.panelNavBtnAdd title="" id="addCompanyVideoBtn"/></@shiro.hasPermission>
            <@shiro.hasPermission name="companyVideo:del"><@ms.panelNavBtnDel title="" id="delCompanyVideoBtn"/></@shiro.hasPermission>
        </@ms.buttonGroup>
			</@ms.panelNav>
    </div>
    <table id="companyVideoList"
           data-show-refresh="true"
           data-show-columns="true"
           data-show-export="true"
           data-method="get"
           data-pagination="true"
           data-page-size="10"
           data-side-pagination="server">
    </table>
    </@ms.panel>

    <@ms.modal  modalName="delCompanyVideo" title="数据删除" >
        <@ms.modalBody>删除
            <@ms.modalButton>
            <!--模态框按钮组-->
                <@ms.button class="btn btn-danger rightDelete" value="删除"  id="deleteCompanyVideoBtn"  />
            </@ms.modalButton>
        </@ms.modalBody>
    </@ms.modal>
</@ms.html5>

<script>
    $(function () {
        $("#companyVideoList").bootstrapTable({
            url: "${managerPath}/cms/companyVideo/list.do",
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
                    <@shiro.hasPermission name="companyVideo:update">
                        var url = "${managerPath}/cms/companyVideo/form.do?id=" + row.id;
                        return "<a href=" + url + " target='_self'>" + value + "</a>";
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="companyVideo:update">
                        return value;
                    </@shiro.lacksPermission>
                    }
                }, {
                    field: 'videoName',
                    title: '视频名称',
                    align: 'center',
                    width: '100'
                }, {
                    field: 'companyCode',
                    title: '单位编码',
                    align: 'center',
                    width: '50'
                }, {
                    field: 'companyName',
                    title: '单位名称',
                    align: 'center',
                    width: '80'
                }, {
                    field: 'videoUrl',
                    title: 'PC端视频地址',
                    align: 'center',
                    width: '50'
                }, {
                    field: 'mobileVideoUrl',
                    title: '移动端视频地址',
                    align: 'center',
                    width: '50'
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
                    title: '更新时间',
                    width: '100',
                    align: 'center'
                }]
        })
    })


    //增加按钮
    $("#addCompanyVideoBtn").click(function () {
        location.href = "${managerPath}/cms/companyVideo/form.do";
    })
    //删除按钮
    $("#delCompanyVideoBtn").click(function () {
        //获取checkbox选中的数据
        var rows = $("#companyVideoList").bootstrapTable("getSelections");
        //没有选中checkbox
        if (rows.length <= 0) {
        <@ms.notify msg="请选择需要删除的记录" type="warning"/>
        } else {
            $(".delCompanyVideo").modal();
        }
    })

    $("#deleteCompanyVideoBtn").click(function () {
        var rows = $("#companyVideoList").bootstrapTable("getSelections");
        $(this).text("正在删除...");
        $(this).attr("disabled", "true");
        $.ajax({
            type: "post",
            url: "${managerPath}/cms/companyVideo/delete.do",
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
        var params = $('#companyVideoList').bootstrapTable('getOptions');
        params.queryParams = function (params) {
            $.extend(params, search);
            return params;
        }
        $("#companyVideoList").bootstrapTable('refresh', {query: $("form[name='searchForm']").serializeJSON()});
    }
</script>