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
import net.mingsoft.cms.model.RecruitmentInfo;
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.service.RecruitmentInfoService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lizhongwei
 * @description 招聘信息管理
 * @date 2019-06-03
 */
@Controller("webRecruitmentInfo")
@RequestMapping("/mcms/recruitmentInfo")
@Api(value = "招聘信息管理接口", description = "招聘信息管理接口")
public class RecruitmentInfoAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传路径
     */
    @Value("${ms.upload.path}")
    private String uploadFloderPath;

    @Autowired
    private RecruitmentInfoService recruitmentInfoService;



    /**
     * 加载页面显示所有信息
     *
     * @param request
     * @return 返回页面显示数据
     */

    @PostMapping("/list")
    @ApiOperation(value = "招聘信息管理列表", notes = "招聘信息管理列表", response = RecruitmentInfo.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer",paramType="query"),
		@ApiImplicitParam(name = "sortOrder", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
		@ApiImplicitParam(name = "position", value = "职位名称", required = false, paramType = "query"),
        @ApiImplicitParam(name = "companyCode", value = "单位ID", required = false, paramType = "query"),
    })
    @ResponseBody
    public Result list(@ModelAttribute @ApiIgnore RecruitmentInfo recruitmentInfo) {
    	logger.info("RecruitmentInfoAction-->list startParam:{}",recruitmentInfo);
        try {
	    	BasicUtil.startPage();
	        List<RecruitmentInfo> recruitmentList = recruitmentInfoService.listBySelective(recruitmentInfo);
	        EUListBean list = new EUListBean(recruitmentList, (int) BasicUtil.endPage(recruitmentList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("RecruitmentInfoAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
    }

    @ApiOperation(value = "招聘信息详情", notes = "招聘信息详情", response = RecruitmentInfo.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		try {
			RecruitmentInfo recruitmentInfo = recruitmentInfoService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(recruitmentInfo));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  recruitmentInfo);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("RecruitmentInfoAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}
}
