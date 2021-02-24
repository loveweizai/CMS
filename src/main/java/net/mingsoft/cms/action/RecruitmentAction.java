package net.mingsoft.cms.action;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.StringUtil;
import net.mingsoft.cms.model.Recruitment;
import net.mingsoft.cms.service.RecruitmentService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @description 招聘会管理
 * @author lizhongwei
 * @date 2019-06-03
 *
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/recruitment")
public class RecruitmentAction extends BaseAction {
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private RecruitmentService recruitmentService;
	
	/**
	 * 加载页面显示所有学生信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		// 返回路径
		return "/cms/recruitment/index"; // 这里表示显示/manager/cms/recruitment/recruitment_list.ftl
	}
	
	/**
	 * 返回编辑界面_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute Recruitment recruitment,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(recruitment.getId() != null){
			Recruitment temp_recruitment = recruitmentService.selectByPrimaryKey(recruitment.getId());	
			model.addAttribute("recruitment",temp_recruitment);
		}
		return "/cms/recruitment/recruitment_form";
	}

	
	
	@ApiOperation(value = "保存基础信息接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
		@ApiImplicitParam(name = "name", value = "招聘会名称", required = true,paramType="query"),
		@ApiImplicitParam(name = "startDate", value = "时间", required = true,paramType="query"),
		@ApiImplicitParam(name = "location", value = "地点", required = true,paramType="query"),
		@ApiImplicitParam(name = "status", value = "状态", required = true,paramType="query"),
		@ApiImplicitParam(name = "pictureUrl", value = "标题图片", required = true,paramType="query"),
		@ApiImplicitParam(name = "introduce", value = "介绍", required = true,paramType="query"),
    })
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("recruitment:save")
	public void save(@ModelAttribute @ApiIgnore Recruitment recruitment, HttpServletResponse response, HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(recruitment))){
			this.outJson(response, null, false, checkInfo(recruitment));
			return ;
		}
		recruitment.setCreateTime(new Date());
		recruitment.setUpdateTime(new Date());
		recruitmentService.insertSelective(recruitment);
		this.outJson(response,true,"保存成功");
	}
	
	
	/**
	 * @param recruitments 基础信息表实体
	 * <i>recruitments参数包含字段信息参考：</i><br/>
	 * id:多个id直接用逗号隔开,例如id=1,2,3,4
	 * 批量删除用户基础信息表
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@ApiOperation(value = "批量删除接口")
	@PostMapping("/delete")
	@ResponseBody
	@RequiresPermissions("recruitment:del")
	public void delete(@RequestBody List<Recruitment > recruitments,HttpServletResponse response, HttpServletRequest request) {
		int count = recruitmentService.deleteBatchBySelective(recruitments);
		if(count<=0) {
			this.outJson(response, false,"删除失败");
			return ;
		}
		this.outJson(response, true,"删除成功");
	}
	
	@ApiOperation(value = "修改基础信息接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
			@ApiImplicitParam(name = "name", value = "招聘会名称", required = true,paramType="query"),
			@ApiImplicitParam(name = "startDate", value = "时间", required = true,paramType="query"),
			@ApiImplicitParam(name = "location", value = "地点", required = true,paramType="query"),
			@ApiImplicitParam(name = "status", value = "状态", required = true,paramType="query"),
			@ApiImplicitParam(name = "pictureUrl", value = "标题图片", required = true,paramType="query"),
			@ApiImplicitParam(name = "introduce", value = "介绍", required = true,paramType="query"),
    })
	@PostMapping("/update")
	@ResponseBody	 
	@RequiresPermissions("recruitment:update")
	public void update(@ModelAttribute @ApiIgnore Recruitment recruitment, HttpServletResponse response,
			HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(recruitment))){
			this.outJson(response, null, false, checkInfo(recruitment));
			return ;
		}
		recruitment.setUpdateTime(new Date());
		recruitmentService.updateByPrimaryKeySelective(recruitment);
		
		this.outJson(response,true,"保存成功");
	}

	private String checkInfo(Recruitment recruitment) {
//		if(!StringUtil.checkLength(recruitment.getName()+"", 0, 120)){
//			return getResString("err.length", this.getResString("recruitment.name"), "0", "120");
//		}
//		if(!StringUtil.checkLength(recruitment.getLocation()+"", 0, 120)){
//			return getResString("err.length", this.getResString("recruitment.location"), "0", "120");
//		}
//		if(!StringUtil.checkLength(recruitment.getStatus()+"", 0, 100)){
//			return getResString("err.length", this.getResString("recruitment.major"), "0", "100");
//		}
//		if(!StringUtil.checkLength(recruitment.getPosition()+"", 0, 100)){
//			return getResString("err.length", this.getResString("recruitment.position"), "0", "100");
//		}
//		if(!StringUtil.checkLength(recruitment.getMotto() +"", 0, 125)){
//			return getResString("err.length", this.getResString("recruitment.motto"), "0", "125");
//		}
		return null;
	}	
	
	/**
	 * 加载页面显示所有信息
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	
	@GetMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "名称", required = false,paramType="query"),
    })
	@ResponseBody
	public EUListBean list(@ModelAttribute @ApiIgnore Recruitment recruitment,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model) {
		BasicUtil.startPage();
		List<Recruitment> recruitmentList = recruitmentService.listBySelective(recruitment);
		EUListBean list = new EUListBean(recruitmentList, (int) BasicUtil.endPage(recruitmentList).getTotal());
		return list;
	}

}
