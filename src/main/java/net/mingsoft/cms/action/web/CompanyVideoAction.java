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
import net.mingsoft.cms.model.CompanyVideoInfo;
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.service.CompanyVideoInfoService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lizhongwei
 * @description 组织结构管理
 * @date 2019-06-03
 */
@Controller("webCompanyVideo")
@RequestMapping("/mcms/companyVideo")
@Api(value = "视频管理接口", description = "视频管理接口",tags="视频管理")
public class CompanyVideoAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传路径
     */
    @Value("${ms.upload.path}")
    private String uploadFloderPath;

    @Autowired
    private CompanyVideoInfoService companyVideoInfoService;

    /**
     * 加载页面显示所有信息
     *
     * @param request
     * @return 返回页面显示数据
     */

    @PostMapping("/list")
    @ApiOperation(value = "视频管理列表", notes = "视频管理列表", response = CompanyVideoInfo.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "companyCode", value = "单位编码", required = false,dataType = "String",paramType="query"),
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "sortOrder", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
    })
    @ResponseBody
    public Result list(@ModelAttribute @ApiIgnore CompanyVideoInfo companyVideoInfo) {
        try {
	    	BasicUtil.startPage();
	        List<CompanyVideoInfo> companyList = companyVideoInfoService.listBySelective(companyVideoInfo);
	        EUListBean list = new EUListBean(companyList, (int) BasicUtil.endPage(companyList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("CompanyVideoAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
    }
    
	@ApiOperation(value = "视频详情", notes = "视频详情", response = CompanyVideoInfo.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		try {
			CompanyVideoInfo companyStructure = companyVideoInfoService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(companyStructure));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  companyStructure);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("CompanyVideoAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}

}
