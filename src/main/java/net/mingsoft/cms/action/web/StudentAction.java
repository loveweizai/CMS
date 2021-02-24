package net.mingsoft.cms.action.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import net.mingsoft.cms.model.Student;
import net.mingsoft.cms.service.StudentService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 青年校友管理
 * @author shjpgli
 * @date 2019-06-03
 *
 */
@Controller("webStudent")
@RequestMapping("/mcms/student")
@Api(value = "青年校友管理接口", description = "青年校友管理接口")
public class StudentAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentService studentService;
	
	
	/**
	 * 加载页面显示所有信息
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	
	@PostMapping("/list")
	@ApiOperation(value = "青年校友列表", notes = "青年校友列表", response = Student.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "schoolName", value = "学校名称", required = false,dataType = "String", paramType="query" ),
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer", paramType="query" ),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer", paramType="query"),
		@ApiImplicitParam(name = "sortOrder", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
    })
	@ResponseBody
	public Result list(@ModelAttribute @ApiIgnore Student student) {
		logger.info("StudentAction-->list:student={}",student);
		try {
			BasicUtil.startPage();
			List<Student> studentList = studentService.listBySelective(student);
			EUListBean list = new EUListBean(studentList, (int) BasicUtil.endPage(studentList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("StudentAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
	}
	
	@ApiOperation(value = "青年校友详情", notes = "青年校友详情", response = Student.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		logger.info("StudentAction-->detail:id={}",id);
		try {
			Student student = studentService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(student));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  student);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("StudentAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}

}
