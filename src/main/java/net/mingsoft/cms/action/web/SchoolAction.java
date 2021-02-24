package net.mingsoft.cms.action.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.model.School;
import net.mingsoft.cms.service.SchoolService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 学校管理
 * @author shjpgli
 * @date 2019-06-03
 *
 */
@Controller("webSchool")
@RequestMapping("/mcms/school")
@Api(value = "学校管理接口", description = "学校管理接口",tags="学校管理")
public class SchoolAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private SchoolService schoolService;
	
	
	/**
	 * 加载页面显示所有信息
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	
	@PostMapping("/list")
	@ApiOperation(value = "学校列表", notes = "学校列表", response = School.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer", paramType="query" ),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer", paramType="query"),
		@ApiImplicitParam(name = "sortOrder", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
    })
	@ResponseBody
	public Result list(@ModelAttribute @ApiIgnore School school) {
		logger.info("SchoolAction-->list:school={}",school);
		try {
			BasicUtil.startPage();
			List<School> schoolList = schoolService.listBySelective(school);
			EUListBean list = new EUListBean(schoolList, (int) BasicUtil.endPage(schoolList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("SchoolAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
	}
	
	@ApiOperation(value = "学校详情", notes = "学校详情", response = School.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		logger.info("SchoolAction-->detail:id={}",id);
		try {
			School school = schoolService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(school));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  school);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("SchoolAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}

}
