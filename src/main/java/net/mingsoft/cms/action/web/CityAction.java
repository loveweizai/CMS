package net.mingsoft.cms.action.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.cms.model.Adpage;
import net.mingsoft.cms.model.CityDict;
import net.mingsoft.cms.model.Result;
import net.mingsoft.cms.service.CityDictService;
import net.mingsoft.cms.util.Constant;
import net.mingsoft.cms.util.ResultUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description 轮播图管理
 * @author shjpgli
 * @date 2019-06-03
 *
 */
@Controller("webCity")
@RequestMapping("/mcms/city")
@Api(value = "城市字典接口", description = "城市字典接口",tags="城市字典")
public class CityAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private CityDictService cityDictService;
	
	
	/**
	 * 加载页面显示所有信息
	 * 
	 * @param request
	 * @return 返回页面显示数据
	 */
	
	@PostMapping("/list")
	@ApiOperation(value = "轮播图列表", notes = "轮播图列表", response = Adpage.class)
	@ApiImplicitParams({//sortOrder=asc&pageSize=10&pageNumber=1
		@ApiImplicitParam(name = "id", value = "编号", required = false,dataType = "Integer", paramType="query" ),
		@ApiImplicitParam(name = "code", value = "子编码", required = false,dataType = "Integer", paramType="query"),
		@ApiImplicitParam(name = "parentCode", value = "父编码", required = false,paramType="query"),
    })
	@ResponseBody
	public Result list(@ModelAttribute @ApiIgnore CityDict cityDict) {
		try {
			//BasicUtil.startPage();
			List<CityDict> cityList = cityDictService.listBySelective(cityDict);
			//EUListBean list = new EUListBean(adpageList, (int) BasicUtil.endPage(adpageList).getTotal());
			return ResultUtils.result(Constant.CODE_SUCCESS, "查询列表成功", cityList);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("CityAction-->list:查询列表异常:", e);
			return ResultUtils.error(Constant.CODE_ERROR, "查询列表异常");
		}
	}
	

}
