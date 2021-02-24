<@ms.html5>
	 <@ms.nav title="招聘信息管理" back=true>
    	<!--<@ms.saveButton  onclick="save()"/>-->
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="recruitmentInfoForm" isvalidation=true>
			<#setting number_format="#">
    		<@ms.hidden name="id" value="${(recruitmentInfo.id)?default('')}"/>
			<@ms.text label="单位编码" name="companyCode" value="${(recruitmentInfo.companyCode)?default('')}"  width="240px;"/>
			<@ms.text label="招聘职位名称" name="position" value="${(recruitmentInfo.position)?default('')}"  width="240px;"/>
			<@ms.text label="薪资待遇" name="salary" title="" size="5" width="240" value="${(recruitmentInfo.salary)?default('')}" />
			<@ms.text label="招聘人数" name="number" value="${(recruitmentInfo.number)?default('')}"  width="240px;"/>
			<@ms.text label="入职条件" name="status" value="${recruitmentInfo.status?default('')}"  width="240px;"/>
            <@ms.text name="conditionText" label="入职条件" value="${recruitmentInfo.conditionText?default('')}"/>
            <@ms.editor colSm="2" name="duty" label="岗位职责" content="${recruitmentInfo.duty?default('')}"/>
            
        </@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	var url = "${managerPath}/cms/recruitmentInfo/save.do";
	if($("input[name = 'id']").val() > 0){
		url = "${managerPath}/cms/recruitmentInfo/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#recruitmentInfoForm").data("bootstrapValidator").validate();
			var isValid = $("#recruitmentInfoForm").data("bootstrapValidator").isValid();
			if(!isValid) {
				<@ms.notify msg= "数据提交失败，请检查数据格式！" type= "warning" />
				return;
		}
		var btnWord =$(".btn-success").text();
		$(".btn-success").text(btnWord+"中...");
		$(".btn-success").prop("disabled",true);
		$.ajax({
			type:"post",
			dataType:"json",
			data:$("form[name = 'recruitmentInfoForm']").serialize(),
			url:url,
			success: function(data) {
				if(data.result) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/cms/recruitmentInfo/index.do";
				}
				else{
					alert(data.resultMsg);
					$(".btn-success").text(btnWord);
					$(".btn-success").prop("disabled",false);
				}
			}
		})
	}	
</script>
