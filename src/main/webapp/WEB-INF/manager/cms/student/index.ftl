<@ms.html5>
    <@ms.nav title="学生管理"></@ms.nav>
    <@ms.searchForm name="searchForm" isvalidation=true>
        <@ms.text label="姓名"  name="name"  title="请输入学生姓名"  placeholder="请输入学生姓名" value=""   />
        <#--<@ms.text label="年龄"  name="name"  title="请输入年龄"  placeholder="请输入年龄" value=""   />-->
        <#assign status=[{"id":"","name":"全部"},{"id":"1","name":"男"},{"id":"2","name":"女"}]>
        <@ms.select label="性别" list=status listValue="name" listKey="id"    name="sex" value="" />
        <@ms.searchFormButton>
            <@ms.queryButton onclick="search()"/>
        </@ms.searchFormButton>
    </@ms.searchForm>
    <@ms.panel>
    <div id="toolbar">
        <@ms.panelNav>
				<@ms.buttonGroup>
            <@shiro.hasPermission name="student:save"><@ms.panelNavBtnAdd title="" id="addStudentBtn"/></@shiro.hasPermission>
            <@shiro.hasPermission name="student:del"><@ms.panelNavBtnDel title="" id="delStudentBtn"/></@shiro.hasPermission>
        </@ms.buttonGroup>
			</@ms.panelNav>
    </div>
    <table id="studentList"
           data-show-refresh="true"
           data-show-columns="true"
           data-show-export="true"
           data-method="get"
           data-pagination="true"
           data-page-size="10"
           data-side-pagination="server">
    </table>
    </@ms.panel>

    <@ms.modal  modalName="delStudent" title="数据删除" >
        <@ms.modalBody>删除
            <@ms.modalButton>
            <!--模态框按钮组-->
                <@ms.button class="btn btn-danger rightDelete" value="删除"  id="deleteStudentBtn"  />
            </@ms.modalButton>
        </@ms.modalBody>
    </@ms.modal>
</@ms.html5>

<script>
    $(function () {
        $("#studentList").bootstrapTable({
            url: "${managerPath}/cms/student/list.do",
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
                    <@shiro.hasPermission name="student:update">
                        var url = "${managerPath}/cms/student/form.do?id=" + row.id;
                        return "<a href=" + url + " target='_self'>" + value + "</a>";
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="student:update">
                        return value;
                    </@shiro.lacksPermission>
                    }
                }, {
                    field: 'name',
                    title: '姓名',
                    align: 'center',
                    width: '60'
                }, {
                    field: 'sex',
                    title: '类型',
                    width: '20',
                    align: 'center',
                    formatter:function(value,row,index) {
                        if(value == 1){
                            return "男";
                        }else if(value == 2){
                            return "女";
                        }
                    }
                }, {
                    field: 'age',
                    title: '年龄',
                    align: 'center',
                    width: '50'
                }, {
                    field: 'major',
                    title: '专业',
                    align: 'center',
                    width: '150'
                }, {
                    field: 'motto',
                    title: '心得',
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
    $("#addStudentBtn").click(function () {
        location.href = "${managerPath}/cms/student/form.do";
    })
    //删除按钮
    $("#delStudentBtn").click(function () {
        //获取checkbox选中的数据
        var rows = $("#studentList").bootstrapTable("getSelections");
        //没有选中checkbox
        if (rows.length <= 0) {
        <@ms.notify msg="请选择需要删除的记录" type="warning"/>
        } else {
            $(".delStudent").modal();
        }
    })

    $("#deleteStudentBtn").click(function () {
        var rows = $("#studentList").bootstrapTable("getSelections");
        $(this).text("正在删除...");
        $(this).attr("disabled", "true");
        $.ajax({
            type: "post",
            url: "${managerPath}/cms/student/delete.do",
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
        var params = $('#studentList').bootstrapTable('getOptions');
        params.queryParams = function (params) {
            $.extend(params, search);
            return params;
        }
        $("#studentList").bootstrapTable('refresh', {query: $("form[name='searchForm']").serializeJSON()});
    }
</script>