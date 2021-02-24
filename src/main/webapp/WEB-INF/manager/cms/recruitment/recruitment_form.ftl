<@ms.html5>
	 <@ms.nav title="招聘会管理" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="recruitmentForm" isvalidation=true>
			<#setting number_format="#">
    		<@ms.hidden name="id" value="${(recruitment.id)?default('')}"/>
			<@ms.text label="招聘会名称" name="name" value="${(recruitment.name)?default('')}"  width="240px;" placeholder="请输入招聘会名称" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填","maxlength":"50","data-bv-stringlength-message":"名称长度不能超过五十个字符长度!"}/>
			<@ms.date name="startDate" label="开始时间" value="${(recruitment.startDate?default(.now))?string('yyyy-MM-dd HH:mm:ss')}" single=true readonly="readonly" width="300" validation={"required":"true", "data-bv-notempty-message":"必填项目"} placeholder="点击该框选择时间"  />
			<@ms.text name="location" label="会议地址"   title="" size="5" width="240" placeholder="请输入会议地址" value="${(recruitment.location)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
			<#assign showNameList=[{"id":"0","name":"未发布"},{"id":"1","name":"已发布"},{"id":"2","name":"已撤销"}]>
			<@ms.radio name="status" label="状态"  list=showNameList listKey="id" listValue="name" value="${(recruitment.status)?default('1')}"/>
			<@ms.formRow colSm="2" label="标题图片" width="400" >
				<@ms.uploadImg path="recruitment" inputName="pictureUrl" size="1" msg="提示:缩略图,支持jpg格式"  imgs="${recruitment.pictureUrl?default('')}"  />
			</@ms.formRow>
            <@ms.editor colSm="2" name="introduce" label="介绍" content="${recruitment.introduce?default('')}"/>
        </@ms.form>
    </@ms.panel>
</@ms.html5>

<script>
    //重写时间控件
    $('#startDate').daterangepicker({
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
	var url = "${managerPath}/cms/recruitment/save.do";
	if($("input[name = 'id']").val() > 0){
		url = "${managerPath}/cms/recruitment/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#recruitmentForm").data("bootstrapValidator").validate();
			var isValid = $("#recruitmentForm").data("bootstrapValidator").isValid();
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
			data:$("form[name = 'recruitmentForm']").serialize(),
			url:url,
			success: function(data) {
				if(data.result) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/cms/recruitment/index.do";
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
