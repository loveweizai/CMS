<@ms.html5>
	 <@ms.nav title="学校管理" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="schoolForm" isvalidation=true>
			<#setting number_format="#">
    		<@ms.hidden name="id" value="${(school.id)?default('')}"/>
			<@ms.text label="名称" name="name" value="${(school.name)?default('')}"  width="240px;" placeholder="请输入名称" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填","maxlength":"50","data-bv-stringlength-message":"名称长度不能超过五十个字符长度!"}/>
			<@ms.text name="place" label="学校地址"   title="" size="5" width="240" placeholder="请输入学校地址" value="${(school.place)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
			<@ms.date name="setupTime" label="修建时间" value="${(school.setupTime?default(.now))?string('yyyy-MM-dd')}" single=true readonly="readonly" width="300" validation={"required":"true", "data-bv-notempty-message":"必填项目"} placeholder="点击该框选择时间段"  />
        	<div class="form-group ms-form-group"><div class="col-sm-2"></div><div class="col-sm-9 ms-from-group-input ms-form-input">
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	var url = "${managerPath}/cms/school/save.do";
	if($("input[name = 'id']").val() > 0){
		url = "${managerPath}/cms/school/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#schoolForm").data("bootstrapValidator").validate();
			var isValid = $("#schoolForm").data("bootstrapValidator").isValid();
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
			data:$("form[name = 'schoolForm']").serialize(),
			url:url,
			success: function(data) {
				if(data.result) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/cms/school/index.do";
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
