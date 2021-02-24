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
import net.mingsoft.cms.model.Notice;
import net.mingsoft.cms.service.NoticeService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 公告管理
 * @author shjpgli
 * @date 2019-06-03
 *
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/notice")
public class NoticeAction extends BaseAction {
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	

	@Autowired
	private NoticeService noticeService;
	
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
		return "/cms/notice/index"; // 这里表示显示/manager/cms/notice/notice_list.ftl
	}
	
	/**
	 * 返回编辑界面peopleUser_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute Notice notice,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(notice.getId() != null){
			Notice temp_notice = noticeService.selectByPrimaryKey(notice.getId());	
			model.addAttribute("notice",temp_notice);
		}
		return "/cms/notice/notice_form";
	}

	
	
	@ApiOperation(value = "保存基础信息接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "title", value = "通知公告标题", required = true,paramType="query"),
		@ApiImplicitParam(name = "author", value = "作者", required = true,paramType="query"),
		@ApiImplicitParam(name = "content", value = "通知公告内容", required = true,paramType="query"),
		@ApiImplicitParam(name = "comeFrom", value = "通告来源", required = false,paramType="query"),
		@ApiImplicitParam(name = "status", value = "通告状态 0.有效，1.无效", required = false,paramType="query"),
		@ApiImplicitParam(name = "releaseTime", value = "发布时间", required = false,paramType="query"),
		@ApiImplicitParam(name = "type", value = "通知类型，0：普通通知；1：社会招聘通知，2：校园招聘通知", required = false,paramType="query"),
    })
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("notice:save")
	public void save(@ModelAttribute @ApiIgnore Notice notice, HttpServletResponse response, HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(notice))){
			this.outJson(response, null, false, checkInfo(notice));
			return ;
		}
		notice.setCreateTime(new Date());
		notice.setUpdateTime(new Date());
		//notice.setPeopleAppId(BasicUtil.getAppId());
		noticeService.insertSelective(notice);
		this.outJson(response,true,"保存成功");
	}
	
	
	/**
	 * @param notices 基础信息表实体
	 * <i>notices参数包含字段信息参考：</i><br/>
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
	@RequiresPermissions("notice:del")
	public void delete(@RequestBody List<Notice > notices,HttpServletResponse response, HttpServletRequest request) {
//		int[] ids = new int[notices.size()];
//		//String[] ids = new String[notices.size()];
//		for(int i = 0;i<notices.size();i++){
//			ids[i] = notices.get(i).getId();
//		}
//		FileUtil.del(notices);
		//noticeService.deleteByPrimaryKey(id);
		int count = noticeService.deleteBatchBySelective(notices);
		if(count<=0) {
			this.outJson(response, false,"删除失败");
			return ;
		}
		this.outJson(response, true,"删除成功");
	}
	
	@ApiOperation(value = "修改基础信息接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
		@ApiImplicitParam(name = "title", value = "通知公告标题", required = true,paramType="query"),
		@ApiImplicitParam(name = "author", value = "作者", required = true,paramType="query"),
		@ApiImplicitParam(name = "content", value = "通知公告内容", required = true,paramType="query"),
		@ApiImplicitParam(name = "comeFrom", value = "通告来源", required = false,paramType="query"),
		@ApiImplicitParam(name = "releaseTime", value = "发布时间", required = false,paramType="query"),
		@ApiImplicitParam(name = "type", value = "通知类型，0：普通通知；1：社会招聘通知，2：校园招聘通知", required = false,paramType="query"),
    })
	@PostMapping("/update")
	@ResponseBody	 
	@RequiresPermissions("notice:update")
	public void update(@ModelAttribute @ApiIgnore Notice notice, HttpServletResponse response,
			HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(notice))){
			this.outJson(response, null, false, checkInfo(notice));
			return ;
		}
		notice.setUpdateTime(new Date());
		//notice.setPeopleAppId(BasicUtil.getAppId());
		noticeService.updateByPrimaryKeySelective(notice);
		
		this.outJson(response,true,"保存成功");
	}

	private String checkInfo(Notice notice) {
		if(!StringUtil.checkLength(notice.getTitle()+"", 0, 100)){
			//this.outJson(response, null, false, getResString("err.length", this.getResString("pu.real.name"), "0", "50"));
			return getResString("err.length", this.getResString("notice.title"), "0", "100");			
		}
		if(!StringUtil.checkLength(notice.getAuthor()+"", 0, 200)){
			return getResString("err.length", this.getResString("notice.author"), "0", "30");			
		}
		if(!StringUtil.checkLength(notice.getComeFrom()+"", 0, 200)){
			
			return getResString("err.length", this.getResString("notice.comeFrom"), "0", "30");			
		}
		if(!StringUtil.checkLength(notice.getType()+"", 0, 1)){
			return getResString("err.length", this.getResString("notice.type"), "0", "1");			
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
	@ResponseBody
	public EUListBean list(@ModelAttribute @ApiIgnore Notice notice,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model) {
		BasicUtil.startPage();
		List<Notice> noticeList = noticeService.listBySelective(notice);
		EUListBean list = new EUListBean(noticeList, (int) BasicUtil.endPage(noticeList).getTotal());
		return list;
	}

}
