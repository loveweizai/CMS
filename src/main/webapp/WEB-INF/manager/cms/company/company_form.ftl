<@ms.html5>
    <@ms.nav title="单位管理" back=true>
        <@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
        <@ms.form name="companyForm" isvalidation=true>
            <#setting number_format="#">
            <@ms.hidden name="id" value="${(company.id)?default('')}"/>
            <@ms.text label="单位名称" name="name" value="${(company.name)?default('')}"  width="240px;" placeholder="请输入单位名称" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填","maxlength":"50","data-bv-stringlength-message":"名称长度不能超过五十个字符长度!"}/>
            <@ms.text label="单位编码" name="code" value="${(company.code)?default('')}"  width="240px;" placeholder="请输入单位编码" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填","maxlength":"50","data-bv-stringlength-message":"编码长度不能超过五十个字符长度!"}/>
            <@ms.text name="address" label="单位地址"   title="" size="5" width="240" placeholder="请输入单位地址" value="${(company.address)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
            <@ms.date id="updateTime" name="updateTime" time=true label="排序时间" single=true readonly="readonly" width="300" value="${(company.updateTime?default(.now))?string('YYYY-MM-dd HH:mm:ss')}"  placeholder="点击该框选择时间段"  />
            <@ms.formRow colSm="2" label="形象照" width="400" >
                <@ms.uploadImg path="company" inputName="imageUrl" size="1" msg="提示:缩略图,支持jpg,JPG,jpeg,PNG,gif,png格式"  imgs="${company.imageUrl?default('')}"  />
            </@ms.formRow>

            <@ms.formRow colSm="2" label="PC端视频" width="400" >
                <@ms.uploadFile path="company" inputName="videoUrl_temp" size="500"
                filetype="mp4"
                msg="提示:PC端公司宣传视频,支持mp4格式，文件大小不能超过500MB" maxSize="500" callBack="setFile" isRename="true"
                />
            </@ms.formRow>
            <@ms.text label="PC视频地址" width="500px;" name="videoUrl"  value="${company.videoUrl?default('')}" readonly="true"/>
            <@ms.formRow colSm="2" label="移动端视频" width="400" >
                <@ms.uploadFile path="company" inputName="mobileVideoUrl_temp" size="500"
                filetype="mp4"
                msg="提示:移动端公司宣传视频,支持mp4格式，文件大小不能超过500MB" maxSize="500" callBack="setMobileFile" isRename="true"
                />
            </@ms.formRow>

            <@ms.text label="移动端视频地址" width="500px;" name="mobileVideoUrl" value="${company.mobileVideoUrl?default('')}" readonly="true" />
            <@ms.text name="introduce" label="简介"   title="" size="5" width="240" placeholder="请输入简介" value="${(company.introduce)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
            <@ms.editor colSm="2" name="detail" label="详情" content="${company.detail?default('')}"/>
        </@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
    //重写时间控件
    $('#updateTime').daterangepicker({
        format:'YYYY-MM-DD HH:mm:ss',
        singleDatePicker: true,
        showDropdowns: true,
        timePickerIncrement: 1,
        timePicker: true,
        timePicker12Hour: true,
        startDate: moment().hours(0).minutes(0).seconds(0),
        showDropdowns: true,
        showWeekNumbers: true,
    });
    $('#updateTime').on('apply.daterangepicker', function(ev, picker) {
        $('#updateTime').parents("form:first").data('bootstrapValidator').revalidateField('updateTime');
    });

    var url = "${managerPath}/cms/company/save.do";
    if ($("input[name = 'id']").val() > 0) {
        url = "${managerPath}/cms/company/update.do";
        $(".btn-success").text("更新");
    }
    function setFile(file) {
        if (file < 0) {
        <@ms.notify msg="上传失败!" type= "success" />
        } else {
        <@ms.notify msg="上传成功!" type= "success" />
            $("input[name='videoUrl']").val(file);
        }
    }
    function setMobileFile(file) {
        if (file < 0) {
        <@ms.notify msg="上传失败!" type= "success" />
        } else {
        <@ms.notify msg="上传成功!" type= "success" />
            $("input[name='mobileVideoUrl']").val(file);
        }
    }
    //编辑按钮onclick
    function save() {
        $("#companyForm").data("bootstrapValidator").validate();
        var isValid = $("#companyForm").data("bootstrapValidator").isValid();
        if (!isValid) {
        <@ms.notify msg= "数据提交失败，请检查数据格式！" type= "warning" />
            return;
        }
        var btnWord = $(".btn-success").text();
        $(".btn-success").text(btnWord + "中...");
        $(".btn-success").prop("disabled", true);
        $.ajax({
            type: "post",
            dataType: "json",
            data: $("form[name = 'companyForm']").serialize(),
            url: url,
            success: function (data) {
                if (data.result) {
                <@ms.notify msg="保存或更新成功" type= "success" />
                    location.href = "${managerPath}/cms/company/index.do";
                }
                else {
                    alert(data.resultMsg);
                    $(".btn-success").text(btnWord);
                    $(".btn-success").prop("disabled", false);
                }
            }
        })
    }
</script>
