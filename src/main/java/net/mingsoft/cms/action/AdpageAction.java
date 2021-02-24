package net.mingsoft.cms.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.StringUtil;
import net.mingsoft.cms.model.Adpage;
import net.mingsoft.cms.service.AdpageService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 轮播图管理
 * @author shjpgli
 * @date 2019-06-03
 *
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/adpage")
public class AdpageAction extends BaseAction {
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private AdpageService adpageService;
	
	/**
	 * 加载页面显示所有文章信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		// 返回路径
		return "/cms/adpage/index"; // 这里表示显示/manager/cms/adpage/adpage_list.ftl
	}
	
	/**
	 * 返回编辑界面peopleUser_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute Adpage adpage,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(adpage.getId() != null){
			Adpage temp_adpage = adpageService.selectByPrimaryKey(adpage.getId());	
			model.addAttribute("adpage",temp_adpage);
		}
		return "/cms/adpage/adpage_form";
	}

	
	
	@ApiOperation(value = "保存基础信息接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
		@ApiImplicitParam(name = "name", value = "名称", required = true,paramType="query"),
		@ApiImplicitParam(name = "url", value = "图片", required = true,paramType="query"),
		@ApiImplicitParam(name = "link", value = "链接", required = false,paramType="query"),
		@ApiImplicitParam(name = "sort", value = "排序", required = false,paramType="query"),
		@ApiImplicitParam(name = "showName", value = "是否显示", required = false,paramType="query"),
    })
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("adpage:save")
	public void save(@ModelAttribute @ApiIgnore Adpage adpage, HttpServletResponse response, HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(adpage))){
			this.outJson(response, null, false, checkInfo(adpage));
			return ;
		}
		adpage.setCreateTime(new Date());
		adpage.setUpdateTime(new Date());
		//adpage.setPeopleAppId(BasicUtil.getAppId());
		adpageService.insertSelective(adpage);
		this.outJson(response,true,"保存成功");
	}
	
	
	/**
	 * @param adpages 基础信息表实体
	 * <i>adpages参数包含字段信息参考：</i><br/>
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
	@RequiresPermissions("adpage:del")
	public void delete(@RequestBody List<Adpage > adpages,HttpServletResponse response, HttpServletRequest request) {
//		int[] ids = new int[adpages.size()];
//		//String[] ids = new String[adpages.size()];
//		for(int i = 0;i<adpages.size();i++){
//			ids[i] = adpages.get(i).getId();
//		}
//		FileUtil.del(adpages);
		int count = adpageService.deleteBatchBySelective(adpages);
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
		@ApiImplicitParam(name = "url", value = "图片", required = true,paramType="query"),
		@ApiImplicitParam(name = "link", value = "链接", required = false,paramType="query"),
		@ApiImplicitParam(name = "sort", value = "排序", required = false,paramType="query"),
		@ApiImplicitParam(name = "showName", value = "是否显示", required = false,paramType="query"),
    })
	@PostMapping("/update")
	@ResponseBody	 
	@RequiresPermissions("adpage:update")
	public void update(@ModelAttribute @ApiIgnore Adpage adpage, HttpServletResponse response,
			HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(adpage))){
			this.outJson(response, null, false, checkInfo(adpage));
			return ;
		}
		adpage.setUpdateTime(new Date());
		//adpage.setPeopleAppId(BasicUtil.getAppId());
		adpageService.updateByPrimaryKeySelective(adpage);
		
		this.outJson(response,true,"保存成功");
	}

	private String checkInfo(Adpage adpage) {
		if(!StringUtil.checkLength(adpage.getName()+"", 0, 100)){
			//this.outJson(response, null, false, getResString("err.length", this.getResString("pu.real.name"), "0", "50"));
			return getResString("err.length", this.getResString("adpage.name"), "0", "100");			
		}
		if(!StringUtil.checkLength(adpage.getUrl()+"", 0, 200)){
			return getResString("err.length", this.getResString("adpage.url"), "0", "200");			
		}
		if(!StringUtil.checkLength(adpage.getLink()+"", 0, 200)){
			
			return getResString("err.length", this.getResString("adpage..link"), "0", "200");			
		}
		if(!StringUtil.checkLength(adpage.getShowName()+"", 0, 1)){
			return getResString("err.length", this.getResString("adpage.showName"), "0", "1");			
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
		@ApiImplicitParam(name = "sort", value = "排序", required = false,paramType="query"),
		@ApiImplicitParam(name = "showName", value = "是否显示", required = false,paramType="query"),
    })
	@ResponseBody
	public EUListBean list(@ModelAttribute @ApiIgnore Adpage adpage,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model) {
		BasicUtil.startPage();
		List<Adpage> adpageList = adpageService.listBySelective(adpage);
		EUListBean list = new EUListBean(adpageList, (int) BasicUtil.endPage(adpageList).getTotal());
		return list;
	}

}
