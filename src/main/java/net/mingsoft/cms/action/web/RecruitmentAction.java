package net.mingsoft.cms.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.cms.model.Notice;
import net.mingsoft.cms.model.Recruitment;
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.service.RecruitmentService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 招聘会管理
 * @author lizhongwei
 * @date 2019-06-03
 *
 */
@Controller("webRecruitment")
@RequestMapping("/mcms/recruitment")
@Api(value = "招聘会管理接口", description = "招聘会管理接口",tags="招聘会管理")
public class RecruitmentAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private RecruitmentService recruitmentService;
	

	/**
	 * 加载页面显示所有信息
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	@ApiOperation(value = "招聘会管理列表", notes = "招聘会管理列表", response = Recruitment.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "sortOrder", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
    })
	@PostMapping("/list")
	@ResponseBody
	public Result list(@ModelAttribute @ApiIgnore Recruitment recruitment) {
		try {
			BasicUtil.startPage();
			List<Recruitment> recruitmentList = recruitmentService.listBySelective(recruitment);
			EUListBean list = new EUListBean(recruitmentList, (int) BasicUtil.endPage(recruitmentList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("RecruitmentAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
	}

	@ApiOperation(value = "招聘会详情", notes = "招聘会详情", response = Recruitment.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		try {
			Recruitment recruitment = recruitmentService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(recruitment));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  recruitment);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("RecruitmentAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}
}
