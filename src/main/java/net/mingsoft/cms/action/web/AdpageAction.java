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
import net.mingsoft.cms.model.Adpage;
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.service.AdpageService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 轮播图管理
 * @author shjpgli
 * @date 2019-06-03
 *
 */
@Controller("webAdpage")
@RequestMapping("/mcms/adpage")
@Api(value = "轮播图管理接口", description = "轮播图管理接口",tags="轮播图管理")
public class AdpageAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private AdpageService adpageService;
	
	
	/**
	 * 加载页面显示所有信息
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	
	@PostMapping("/list")
	@ApiOperation(value = "轮播图列表", notes = "轮播图列表", response = Adpage.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer", paramType="query" ),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer", paramType="query"),
		@ApiImplicitParam(name = "sortOrder", value = "排序：asc：正序，desc：倒序,", required = false,paramType="query"),
    })
	@ResponseBody
	public Result list(@ModelAttribute @ApiIgnore Adpage adpage) {
		try {
			BasicUtil.startPage();
			List<Adpage> adpageList = adpageService.listBySelective(adpage);
			EUListBean list = new EUListBean(adpageList, (int) BasicUtil.endPage(adpageList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("AdpageAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
	}
	
	@ApiOperation(value = "轮播图详情", notes = "轮播图详情", response = Adpage.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		try {
			Adpage adpage = adpageService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(adpage));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  adpage);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("AdpageAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}

}
