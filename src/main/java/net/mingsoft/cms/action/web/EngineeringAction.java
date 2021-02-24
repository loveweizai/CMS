package net.mingsoft.cms.action.web;

import java.util.List;
import java.util.Map;

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
import net.mingsoft.cms.model.Engineering;
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.service.EngineeringService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 项目工程管理
 * @author lizhongwei
 * @date 2019-06-03
 *
 */
@Controller("webEngineering")
@RequestMapping("/mcms/engineering")
@Api(value = "项目工程管理接口", description = "项目工程管理接口")
public class EngineeringAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private EngineeringService engineeringService;

	/**
	 * 加载页面显示所有信息
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	
	@PostMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "名称", required = false,paramType="query"),
		@ApiImplicitParam(name = "provinceId", value = "省id", required = false,paramType="query"),
		@ApiImplicitParam(name = "cityId", value = "市id", required = false,paramType="query"),
		@ApiImplicitParam(name = "areaId", value = "区id", required = false,paramType="query"),
		@ApiImplicitParam(name = "countryId", value = "国家id", required = false,paramType="query"),
		@ApiImplicitParam(name = "pageNumber", value = "页数", required = false,dataType = "Integer", paramType="query" ),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", required = false,dataType = "Integer", paramType="query"),
    })
	@ApiOperation(value = "项目工程管理列表", notes = "项目工程管理列表", response = Engineering.class)
	@ResponseBody
	public Result list(@ModelAttribute @ApiIgnore Engineering engineering) {
		try {
			BasicUtil.startPage();
			List<Engineering> engineeringList = engineeringService.listBySelective(engineering);
			EUListBean list = new EUListBean(engineeringList, (int) BasicUtil.endPage(engineeringList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", list);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("EngineeringAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
	}

	@ApiOperation(value = "项目工程详情", notes = "项目工程详情", response = Engineering.class)
	@GetMapping("/detail/{id}")
	@ResponseBody
	public Result detail(@PathVariable Integer id) {
		try {
			Engineering engineering = engineeringService.selectByPrimaryKey(id);
			//this.outJson(response, JSONObject.toJSONString(engineering));
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询详情成功",  engineering);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("EngineeringAction-->detail:查询详情异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询详情异常");
		}
	}
	
	/**
	 * 根据省查询有项目的市
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	
	@PostMapping("/listByProvince")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "provinceId", value = "省id", required = true,paramType="query"),
    })
	@ApiOperation(value = "根据省查询有项目的市", notes = "项目工程管理列表", response = Map.class)
	@ResponseBody
	public Result listByProvince(@ModelAttribute @ApiIgnore Engineering engineering) {
		try {
			logger.info("EngineeringAction-->listByProvince: provinceId={}", engineering.getProvinceId());
			List<Map> cityList = engineeringService.listCityByProvinceId(engineering);
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", cityList);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("EngineeringAction-->listByProvince:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
	}
}
