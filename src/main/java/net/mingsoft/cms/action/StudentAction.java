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
import net.mingsoft.cms.model.School;
import net.mingsoft.cms.model.Student;
import net.mingsoft.cms.service.SchoolService;
import net.mingsoft.cms.service.StudentService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 学生管理
 * @author lizhongwei
 * @date 2019-06-03
 *
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/student")
public class StudentAction extends BaseAction {
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SchoolService schoolService;
	
	/**
	 * 加载页面显示所有学生信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
		// 返回路径
		return "/cms/student/index"; // 这里表示显示/manager/cms/student/student_list.ftl
	}
	
	/**
	 * 返回编辑界面peopleUser_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute Student student,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(student.getId() != null){
			Student temp_student = studentService.selectByPrimaryKey(student.getId());	
			model.addAttribute("student",temp_student);
		}
		List<School> schoolList = schoolService.listBySelective(null);
		model.addAttribute("schoolList",schoolList);
		return "/cms/student/student_form";
	}

	
	
	@ApiOperation(value = "保存基础信息接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "编号", required = true,paramType="query"),
		@ApiImplicitParam(name = "name", value = "名称", required = true,paramType="query"),
		@ApiImplicitParam(name = "age", value = "年龄", required = true,paramType="query",dataType = "Integer"),
		@ApiImplicitParam(name = "sex", value = "性别", required = true,paramType="query"),
		@ApiImplicitParam(name = "major", value = "专业", required = true,paramType="query"),
		@ApiImplicitParam(name = "position", value = "职位", required = true,paramType="query"),
		@ApiImplicitParam(name = "motto", value = "心得", required = true,paramType="query"),
		@ApiImplicitParam(name = "schoolId", value = "学校ID", required = true,paramType="query"),
    })
	@PostMapping("/save")
	@ResponseBody
	@RequiresPermissions("student:save")
	public void save(@ModelAttribute @ApiIgnore Student student, HttpServletResponse response, HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(student))){
			this.outJson(response, null, false, checkInfo(student));
			return ;
		}
//		student.setCreateTime(new Date());
		student.setUpdateTime(new Date());
		studentService.insertSelective(student);
		this.outJson(response,true,"保存成功");
	}
	
	
	/**
	 * @param students 基础信息表实体
	 * <i>students参数包含字段信息参考：</i><br/>
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
	@RequiresPermissions("student:del")
	public void delete(@RequestBody List<Student > students,HttpServletResponse response, HttpServletRequest request) {
		int count = studentService.deleteBatchBySelective(students);
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
			@ApiImplicitParam(name = "age", value = "年龄", required = true,paramType="query",dataType = "Integer"),
			@ApiImplicitParam(name = "sex", value = "性别", required = true,paramType="query"),
			@ApiImplicitParam(name = "major", value = "专业", required = true,paramType="query"),
			@ApiImplicitParam(name = "position", value = "职位", required = true,paramType="query"),
			@ApiImplicitParam(name = "motto", value = "心得", required = true,paramType="query"),
			@ApiImplicitParam(name = "schoolId", value = "学校ID", required = true,paramType="query"),
    })
	@PostMapping("/update")
	@ResponseBody	 
	@RequiresPermissions("student:update")
	public void update(@ModelAttribute @ApiIgnore Student student, HttpServletResponse response,
			HttpServletRequest request) {
		if(!StringUtils.isEmpty(checkInfo(student))){
			this.outJson(response, null, false, checkInfo(student));
			return ;
		}
		student.setUpdateTime(new Date());
		studentService.updateByPrimaryKeySelective(student);
		
		this.outJson(response,true,"保存成功");
	}

	private String checkInfo(Student student) {
		if(!StringUtil.checkLength(student.getName()+"", 0, 20)){
			return getResString("err.length", this.getResString("student.name"), "0", "20");
		}
		if(!StringUtil.checkLength(student.getSex()+"", 0, 1)){
			return getResString("err.length", this.getResString("student.sex"), "0", "1");
		}
		if(!StringUtil.checkLength(student.getMajor()+"", 0, 100)){
			return getResString("err.length", this.getResString("student.major"), "0", "100");
		}
		if(!StringUtil.checkLength(student.getPosition()+"", 0, 100)){
			return getResString("err.length", this.getResString("student.position"), "0", "100");
		}
		if(!StringUtil.checkLength(student.getMotto() +"", 0, 125)){
			return getResString("err.length", this.getResString("student.motto"), "0", "125");
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
		@ApiImplicitParam(name = "sex", value = "性别", required = false,paramType="query"),
//		@ApiImplicitParam(name = "schoolId", value = "学校", required = false,paramType="query"),
    })
	@ResponseBody
	public EUListBean list(@ModelAttribute @ApiIgnore Student student,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model) {
		BasicUtil.startPage();
		List<Student> studentList = studentService.listBySelective(student);
		EUListBean list = new EUListBean(studentList, (int) BasicUtil.endPage(studentList).getTotal());
		return list;
	}

}
