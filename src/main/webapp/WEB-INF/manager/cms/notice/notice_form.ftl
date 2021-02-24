<@ms.html5>
	 <@ms.nav title="公告管理" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="noticeForm" isvalidation=true>
			<#setting number_format="#">
    		<@ms.hidden name="id" value="${(notice.id)?default('')}"/>
			<@ms.text label="标题" name="title" value="${(notice.title)?default('')}"  width="240px;" placeholder="请输入标题" validation={"data-bv-stringlength":"true","maxlength":"50","data-bv-stringlength-message":"标题长度不能超过五十个字符长度!"}/>
			<@ms.text name="author" label="作者"   title="" size="5" width="240" placeholder="请输入作者" value="${(notice.author)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
			<@ms.text name="comeFrom" label="来源" placeholder="请输入来源" title="" size="5" width="240" value="${(notice.comeFrom)?default('')}" validation={"maxlength":"5","data-bv-stringlength":"true","data-bv-stringlength-max":"50","data-bv-stringlength-message":"来源长度不能超过五十个字符长度"}/>
			<#assign typeList=[{"id":"0","name":"普通通知"},{"id":"1","name":"社会招聘通知"},{"id":"2","name":"校园招聘通知"}]>
			<@ms.radio name="type" label="类型"  list=typeList listKey="id" listValue="name" value="${(notice.type)?default('0')}"/>
			<#assign statusList=[{"id":"0","name":"有效"},{"id":"1","name":"无效"}]>
			<@ms.radio name="status" label="状态"  list=statusList listKey="id" listValue="name" value="${(notice.status)?default('1')}"/>
			<@ms.hidden id="basicDateTime" name="basicDateTime" value=""/>
            <@ms.date id="releaseTime" name="releaseTime" time=true label="发布时间" single=true readonly="readonly" width="300" value="${(notice.releaseTime?default(.now))?string('yyyy-MM-dd HH:mm:ss')}" validation={"required":"true", "data-bv-notempty-message":"必填项目"} placeholder="点击该框选择时间段"  />
			<@ms.editor colSm="2" name="content" label="文章内容" content="${notice.content?default('')}"/>	
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
//重写时间控件
$('#releaseTime').daterangepicker({
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
$('#releaseTime').on('apply.daterangepicker', function(ev, picker) {
		$('#releaseTime').parents("form:first").data('bootstrapValidator').revalidateField('releaseTime');
});

	var url = "${managerPath}/cms/notice/save.do";
	if($("input[name = 'id']").val() > 0){
		url = "${managerPath}/cms/notice/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#noticeForm").data("bootstrapValidator").validate();
			var isValid = $("#noticeForm").data("bootstrapValidator").isValid();
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
			data:$("form[name = 'noticeForm']").serialize(),
			url:url,
			success: function(data) {
				if(data.result) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/cms/notice/index.do";
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
