<@ms.html5>
    <@ms.nav title="招聘会管理"></@ms.nav>
    <@ms.searchForm name="searchForm" isvalidation=true>
        <@ms.text label="会议名"  name="name"  title="请输入职位会议名"  placeholder="请输入职位会议名" value=""   />
        <@ms.searchFormButton>
            <@ms.queryButton onclick="search()"/>
        </@ms.searchFormButton>
    </@ms.searchForm>
    <@ms.panel>
    <div id="toolbar">
        <@ms.panelNav>
            <@ms.buttonGroup>
                <@shiro.hasPermission name="recruitment:save"><@ms.panelNavBtnAdd title="" id="addRecruitmentBtn"/></@shiro.hasPermission>
                <@shiro.hasPermission name="recruitment:del"><@ms.panelNavBtnDel title="" id="delRecruitmentBtn"/></@shiro.hasPermission>
            </@ms.buttonGroup>
        </@ms.panelNav>
    </div>
    <table id="recruitmentList"
           data-show-refresh="true"
           data-show-columns="true"
           data-show-export="true"
           data-method="get"
           data-pagination="true"
           data-page-size="10"
           data-side-pagination="server">
    </table>
    </@ms.panel>

    <@ms.modal  modalName="delRecruitment" title="数据删除" >
        <@ms.modalBody>删除
            <@ms.modalButton>
            <!--模态框按钮组-->
                <@ms.button class="btn btn-danger rightDelete" value="删除"  id="deleteRecruitmentBtn"  />
            </@ms.modalButton>
        </@ms.modalBody>
    </@ms.modal>
</@ms.html5>

<script>
    $(function () {
        $("#recruitmentList").bootstrapTable({
            url: "${managerPath}/cms/recruitment/list.do",
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
                    <@shiro.hasPermission name="recruitment:update">
                        var url = "${managerPath}/cms/recruitment/form.do?id=" + row.id;
                        return "<a href=" + url + " target='_self'>" + value + "</a>";
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="recruitment:update">
                        return value;
                    </@shiro.lacksPermission>
                    }
                }, {
                    field: 'name',
                    title: '招聘会名称',
                    align: 'center',
                    width: '100'
                }, {
                    field: 'startDate',
                    title: '开始时间',
                    align: 'center',
                    width: '100'
                }, {
                    field: 'location',
                    title: '会议地点',
                    align: 'center',
                    width: '150'
                }, {
                    field: 'status',
                    title: '状态',
                    width: '30',
                    align: 'center',
                    formatter:function(value,row,index) {
                        if(value == 0){
                            return "未发布";
                        }else if(value == 1){
                            return "已发布";
                        }else if(value == 2){
                            return "已撤销";
                        }
                    }
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
    $("#addRecruitmentBtn").click(function () {
        location.href = "${managerPath}/cms/recruitment/form.do";
    })
    //删除按钮
    $("#delRecruitmentBtn").click(function () {
        //获取checkbox选中的数据
        var rows = $("#recruitmentList").bootstrapTable("getSelections");
        //没有选中checkbox
        if (rows.length <= 0) {
        <@ms.notify msg="请选择需要删除的记录" type="warning"/>
        } else {
            $(".delRecruitment").modal();
        }
    })

    $("#deleteRecruitmentBtn").click(function () {
        var rows = $("#recruitmentList").bootstrapTable("getSelections");
        $(this).text("正在删除...");
        $(this).attr("disabled", "true");
        $.ajax({
            type: "post",
            url: "${managerPath}/cms/recruitment/delete.do",
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
        var params = $('#recruitmentList').bootstrapTable('getOptions');
        params.queryParams = function (params) {
            $.extend(params, search);
            return params;
        }
        $("#recruitmentList").bootstrapTable('refresh', {query: $("form[name='searchForm']").serializeJSON()});
    }
</script>