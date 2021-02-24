<@ms.html5>
	 <@ms.nav title="学生管理" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="studentForm" isvalidation=true>
			<#setting number_format="#">
    		<@ms.hidden name="id" value="${(student.id)?default('')}"/>
			<@ms.text label="姓名" name="name" value="${(student.name)?default('')}"  width="240px;" placeholder="请输入姓名" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填","maxlength":"50","data-bv-stringlength-message":"名称长度不能超过五十个字符长度!"}/>
			<@ms.text name="age" label="年龄"   title="" size="5" width="240" placeholder="请输入年龄" value="${(student.age)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
			<#assign showNameList=[{"id":"1","name":"男"},{"id":"2","name":"女"}]>
			<@ms.radio name="sex" label="性别"  list=showNameList listKey="id" listValue="name" value="${(student.sex)?default('1')}"/>
			<@ms.formRow colSm="2" label="头像" width="400" >
				<@ms.uploadImg path="student" inputName="portrait" size="1" msg="提示:缩略图,支持jpg,JPG,jpeg,PNG,gif,png格式"  imgs="${student.portrait?default('')}"  />
			</@ms.formRow>
			<#if schoolList?has_content>
				<@ms.select label="学校" name="schoolId" id="forumSelect"  list=schoolList  listValue="name"   listKey="id"    value="${student.schoolId?default('')}" width="240" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"} />
			<#else>
				<@ms.select label="学校" name="schoolId" id="forumSelect" value="" list=[{"id":"-1","name":"请选择学校"}]  width="240" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
			</#if>

			<@ms.date id="createTime" name="createTime" time=true label="毕业时间" single=true readonly="readonly" width="300" value="${(article.createTime?default(.now))?string('YYYY-MM-dd HH:mm:ss')}" validation={"required":"true", "data-bv-notempty-message":"必填项目"} placeholder="点击该框选择时间段"  />
			<@ms.text name="major" label="专业"   title="" size="5" width="240" placeholder="请输入专业" value="${(student.major)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
            <@ms.text name="position" label="职位"   title="" size="5" width="240" placeholder="请输入职位" value="${(student.position)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
            <@ms.text name="motto" label="心得"   title="" size="5" width="240" placeholder="请输入心得" value="${(student.motto)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
            <@ms.editor colSm="2" name="introduction" label="简介" content="${student.introduction?default('')}"/>
        </@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
    //重写时间控件
    $('#createTime').daterangepicker({
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
    $('#createTime').on('apply.daterangepicker', function(ev, picker) {
        $('#createTime').parents("form:first").data('bootstrapValidator').revalidateField('createTime');
    });

	var url = "${managerPath}/cms/student/save.do";
	if($("input[name = 'id']").val() > 0){
		url = "${managerPath}/cms/student/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#studentForm").data("bootstrapValidator").validate();
			var isValid = $("#studentForm").data("bootstrapValidator").isValid();
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
			data:$("form[name = 'studentForm']").serialize(),
			url:url,
			success: function(data) {
				if(data.result) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/cms/student/index.do";
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
