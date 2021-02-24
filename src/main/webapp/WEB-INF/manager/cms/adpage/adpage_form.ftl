<@ms.html5>
	 <@ms.nav title="轮播图管理" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="adpageForm" isvalidation=true>
			<#setting number_format="#">
    		<@ms.hidden name="id" value="${(adpage.id)?default('')}"/>
			<@ms.text label="名称" name="name" value="${(adpage.name)?default('')}"  width="240px;" placeholder="请输入名称" validation={"data-bv-stringlength":"true","maxlength":"50","data-bv-stringlength-message":"名称长度不能超过五十个字符长度!"}/>
			<@ms.formRow colSm="2" label="图片" width="400" >
				<@ms.uploadImg path="adpage" inputName="url" size="1" msg="提示:文章缩略图,支持jpg格式"  imgs="${adpage.url?default('')}"  />
			</@ms.formRow>
			<@ms.text name="link" label="外部链接"   title="" size="5" width="240" placeholder="请输入外部链接" value="${(adpage.link)?default('')}" validation={"required":"true","data-bv-stringlength":"true","data-bv-notempty-message":"必填"}/>
			<@ms.text name="sort" label="排列顺序" placeholder="请输入排列顺序" title="" size="5" width="240" value="${(adpage.sort)?default('')}" validation={"maxlength":"5","data-bv-stringlength":"true","data-bv-stringlength-max":"5","data-bv-stringlength-message":"排序顺序只能输入数字并且最多5位","data-bv-regexp":"true", "data-bv-regexp-regexp":'^[0-9]{1,5}',"data-bv-regexp-message":"排序顺序格式错误"}/>
			<#assign showNameList=[{"id":"1","name":"显示"},{"id":"2","name":"不显示"}]>
			<@ms.radio name="showName" label="名称是否显示"  list=showNameList listKey="id" listValue="name" value="${(adpage.showName)?default('1')}"/>
			
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	var url = "${managerPath}/cms/adpage/save.do";
	if($("input[name = 'id']").val() > 0){
		url = "${managerPath}/cms/adpage/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#adpageForm").data("bootstrapValidator").validate();
			var isValid = $("#adpageForm").data("bootstrapValidator").isValid();
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
			data:$("form[name = 'adpageForm']").serialize(),
			url:url,
			success: function(data) {
				if(data.result) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/cms/adpage/index.do";
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
