package net.mingsoft.cms.action;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.cms.model.Engineering;
import net.mingsoft.cms.service.EngineeringService;

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
 * @description 项目工程管理
 * @author lizhongwei
 * @date 2019-06-03
 *
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/engineering")
public class EngineeringAction extends BaseAction {
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private EngineeringService engineeringService;
	
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
		return "/cms/engineering/index"; // 这里表示显示/manager/cms/engineering/engineering_list.ftl
	}
	
	/**
	 * 返回编辑界面_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute Engineering engineering,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(engineering.getId() != null){
			Engineering temp_engineering = engineeringService.selectByPrimaryKey(engineering.getId());	
			model.addAttribute("engineering",temp_engineering);
		}
		return "/cms/engineering/engineering_form";
	}

	
	
	/*@ApiOperation(value = "保存基础信息接口")
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
	@RequiresPermissions("engineering:save")
	public void save(@ModelAttribute @ApiIgnore Engineering engineering, HttpServletResponse response, HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(engineering))){
			this.outJson(response, null, false, checkInfo(engineering));
			return ;
		}
		engineering.setCreateTime(new Date());
		engineering.setUpdateTime(new Date());
		engineeringService.insertSelective(engineering);
		this.outJson(response,true,"保存成功");
	}*/
	
	
	/**
	 * @param engineerings 基础信息表实体
	 * <i>engineerings参数包含字段信息参考：</i><br/>
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
	@RequiresPermissions("engineering:del")
	public void delete(@RequestBody List<Engineering > engineerings,HttpServletResponse response, HttpServletRequest request) {
		int count = engineeringService.deleteBatchBySelective(engineerings);
		if(count<=0) {
			this.outJson(response, false,"删除失败");
			return ;
		}
		this.outJson(response, true,"删除成功");
	}
	
	@ApiOperation(value = "修改基础信息接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
			@ApiImplicitParam(name = "name", value = "工程名称", required = true,paramType="query"),
			@ApiImplicitParam(name = "location", value = "工程地点", required = true,paramType="query"),
			@ApiImplicitParam(name = "country", value = "所属国家", required = true,paramType="query"),
			@ApiImplicitParam(name = "provinceId", value = "所属省份", required = true,paramType="query"),
			@ApiImplicitParam(name = "cityId", value = "所属市", required = true,paramType="query"),
			@ApiImplicitParam(name = "areaId", value = "所属区", required = true,paramType="query"),
			@ApiImplicitParam(name = "winningTime", value = "中标时间", required = true,paramType="query"),
			@ApiImplicitParam(name = "survey", value = "工程概况", required = true,paramType="query"),
			@ApiImplicitParam(name = "startDate", value = "工程时间起", required = true,paramType="query"),
			@ApiImplicitParam(name = "endDate", value = "工程时间止", required = true,paramType="query"),
			@ApiImplicitParam(name = "unit", value = "工程履约单位", required = true,paramType="query"),
    })
	@PostMapping("/update")
	@ResponseBody	 
	@RequiresPermissions("engineering:update")
	public void update(@ModelAttribute @ApiIgnore Engineering engineering, HttpServletResponse response,
			HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(engineering))){
			this.outJson(response, null, false, checkInfo(engineering));
			return ;
		}
		engineering.setUpdateTime(new Date());
		engineeringService.updateByPrimaryKeySelective(engineering);
		
		this.outJson(response,true,"保存成功");
	}

	private String checkInfo(Engineering engineering) {
//		if(!StringUtil.checkLength(engineering.getName()+"", 0, 120)){
//			return getResString("err.length", this.getResString("engineering.name"), "0", "120");
//		}
//		if(!StringUtil.checkLength(engineering.getLocation()+"", 0, 120)){
//			return getResString("err.length", this.getResString("engineering.location"), "0", "120");
//		}
//		if(!StringUtil.checkLength(engineering.getStatus()+"", 0, 100)){
//			return getResString("err.length", this.getResString("engineering.major"), "0", "100");
//		}
//		if(!StringUtil.checkLength(engineering.getPosition()+"", 0, 100)){
//			return getResString("err.length", this.getResString("engineering.position"), "0", "100");
//		}
//		if(!StringUtil.checkLength(engineering.getMotto() +"", 0, 125)){
//			return getResString("err.length", this.getResString("engineering.motto"), "0", "125");
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
	public EUListBean list(@ModelAttribute @ApiIgnore Engineering engineering,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model) {
		BasicUtil.startPage();
		List<Engineering> engineeringList = engineeringService.listBySelective(engineering);
		EUListBean list = new EUListBean(engineeringList, (int) BasicUtil.endPage(engineeringList).getTotal());
		return list;
	}

}
