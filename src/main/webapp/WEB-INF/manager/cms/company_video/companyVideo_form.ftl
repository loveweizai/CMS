<@ms.html5>
    <@ms.nav title="公司视频管理" back=true>
        <@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
        <@ms.form name="companyVideoForm" isvalidation=true>
            <#setting number_format="#">
            <@ms.hidden name="id" value="${(companyVideo.id)?default('')}"/>
            <@ms.text label="视频名称" name="videoName" value="${(companyVideo.videoName)?default('')}"  width="240px;" placeholder="请输入视频名称" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填","maxlength":"50","data-bv-stringlength-message":"名称长度不能超过五十个字符长度!"}/>
            <#if companyList?has_content>
				<@ms.select label="单位名称" name="companyCode" id="forumSelect"  list=companyList  listValue="name"   listKey="code"    value="${companyVideo.companyCode?default('')}" width="240" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"} />
			<#else>
				<@ms.select label="单位名称" name="companyCode" id="forumSelect" value="" list=[{"code":"-1","name":"请选择单位名称"}]  width="240" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
			</#if>
            <@ms.formRow colSm="2" label="PC端视频" width="400" >
                <@ms.uploadFile path="companyVideo/pc" inputName="videoUrl_temp" size="500"
                filetype="zip,rar,doc,xls,xlsx,ppt,pptx,docx,txt,pdf,mp4"
                msg="提示:公司宣传PC端视频,支持mp4格式，文件大小不能超过500MB" maxSize="500" callBack="setFile" isRename="true" 
                />
            </@ms.formRow>
            <@ms.text label="PC视频地址" width="500px;" name="videoUrl"  value="${companyVideo.videoUrl?default('')}" readonly="true"/>
            <@ms.formRow colSm="2" label="移动端视频" width="400" >
                <@ms.uploadFile path="companyVideo/mobile" inputName="mobileVideoUrl_temp" size="500"
                filetype="zip,rar,doc,xls,xlsx,ppt,pptx,docx,txt,pdf,mp4"
                msg="提示:公司宣传移动端视频,支持mp4格式，文件大小不能超过500MB" maxSize="500" callBack="setMobileFile" isRename="true" 
                />
            </@ms.formRow>
			<@ms.text label="移动端视频地址" width="500px;" name="mobileVideoUrl" value="${companyVideo.mobileVideoUrl?default('')}" readonly="true"/>
            <@ms.text name="introduce" label="简介"   title="" size="5" width="240" placeholder="请输入简介" value="${(companyVideo.introduce)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
        </@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
    var url = "${managerPath}/cms/companyVideo/save.do";
    if ($("input[name = 'id']").val() > 0) {
        url = "${managerPath}/cms/companyVideo/update.do";
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
        $("#companyVideoForm").data("bootstrapValidator").validate();
        var isValid = $("#companyVideoForm").data("bootstrapValidator").isValid();
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
            data: $("form[name = 'companyVideoForm']").serialize(),
            url: url,
            success: function (data) {
                if (data.result) {
                <@ms.notify msg="保存或更新成功" type= "success" />
                    location.href = "${managerPath}/cms/companyVideo/index.do";
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
