package net.mingsoft.cms.action;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.StringUtil;
import net.mingsoft.cms.model.School;
import net.mingsoft.cms.service.SchoolService;

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
 * @description 学校管理
 * @author lizhongwei
 * @date 2019-06-03
 *
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/school")
public class SchoolAction extends BaseAction {
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private SchoolService schoolService;
	
	/**
	 * 加载页面显示所有学校信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		// 返回路径
		return "/cms/school/index"; // 这里表示显示/manager/cms/school/school_list.ftl
	}
	
	/**
	 * 返回编辑界面peopleUser_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute School school,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(school.getId() != null){
			School temp_school = schoolService.selectByPrimaryKey(school.getId());	
			model.addAttribute("school",temp_school);
		}
		return "/cms/school/school_form";
	}

	
	
	@ApiOperation(value = "保存基础信息接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
		@ApiImplicitParam(name = "name", value = "名称", required = true,paramType="query"),
		@ApiImplicitParam(name = "place", value = "地点", required = true,paramType="query"),
			@ApiImplicitParam(name = "setupTime", value = "建立时间", required = true,paramType="query"),
    })
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("school:save")
	public void save(@ModelAttribute @ApiIgnore School school, HttpServletResponse response, HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(school))){
			this.outJson(response, null, false, checkInfo(school));
			return ;
		}
		school.setCreateTime(new Date());
		school.setUpdateTime(new Date());
		//school.setPeopleAppId(BasicUtil.getAppId());
		schoolService.insertSelective(school);
		this.outJson(response,true,"保存成功");
	}
	
	
	/**
	 * @param schools 基础信息表实体
	 * <i>schools参数包含字段信息参考：</i><br/>
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
	@RequiresPermissions("school:del")
	public void delete(@RequestBody List<School > schools,HttpServletResponse response, HttpServletRequest request) {
		int count = schoolService.deleteBatchBySelective(schools);
		if(count<=0) {
			this.outJson(response, false,"删除失败");
			return ;
		}
		this.outJson(response, true,"删除成功");
	}
	
	@ApiOperation(value = "修改基础信息接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
			@ApiImplicitParam(name = "name", value = "名称", required = true,paramType="query"),
			@ApiImplicitParam(name = "place", value = "地点", required = true,paramType="query"),
			@ApiImplicitParam(name = "setupTime", value = "建立时间", required = true,paramType="query"),
    })
	@PostMapping("/update")
	@ResponseBody	 
	@RequiresPermissions("school:update")
	public void update(@ModelAttribute @ApiIgnore School school, HttpServletResponse response,
			HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(school))){
			this.outJson(response, null, false, checkInfo(school));
			return ;
		}
		school.setUpdateTime(new Date());
		schoolService.updateByPrimaryKeySelective(school);
		
		this.outJson(response,true,"保存成功");
	}

	private String checkInfo(School school) {
		if(!StringUtil.checkLength(school.getName()+"", 0, 100)){
			return getResString("err.length", this.getResString("school.name"), "0", "100");
		}
		if(!StringUtil.checkLength(school.getPlace()+"", 0, 120)){
			return getResString("err.length", this.getResString("school.place"), "0", "120");
		}
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
	public EUListBean list(@ModelAttribute @ApiIgnore School school,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model) {
		BasicUtil.startPage();
		List<School> schoolList = schoolService.listBySelective(school);
		EUListBean list = new EUListBean(schoolList, (int) BasicUtil.endPage(schoolList).getTotal());
		return list;
	}

}
