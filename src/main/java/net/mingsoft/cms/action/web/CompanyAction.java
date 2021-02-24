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
import net.mingsoft.cms.model.Adpage;
import net.mingsoft.cms.model.CompanyStructure;
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.service.CompanyService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lizhongwei
 * @description 组织结构管理
 * @date 2019-06-03
 */
@Controller("webCompany")
@RequestMapping("/mcms/company")
@Api(value = "组织结构管理接口", description = "组织结构管理接口",tags="组织结构管理")
public class CompanyAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传路径
     */
    @Value("${ms.upload.path}")
    private String uploadFloderPath;

    @Autowired
    private CompanyService companyService;

    /**
     * 加载页面显示所有信息
     *
     * @param request
     * @return 返回页面显示数据
     */

    @PostMapping("/list")
    @ApiOperation(value = "组织结构管理列表", notes = "组织结构管理列表", response = CompanyStructure.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "sortOrder", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
    })
    @ResponseBody
    public Result list(@ModelAttribute @ApiIgnore CompanyStructure company) {
        try {
	    	BasicUtil.startPage();
	        List<CompanyStructure> companyList = companyService.listBySelective(company);
	        EUListBean list = new EUListBean(companyList, (int) BasicUtil.endPage(companyList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("CompanyAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
    }
    
	@ApiOperation(value = "组织结构详情", notes = "组织结构详情", response = CompanyStructure.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		try {
			CompanyStructure companyStructure = companyService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(companyStructure));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  companyStructure);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("CompanyAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}

}
