package net.mingsoft.cms.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mingsoft.ueditor.MsUeditorActionEnter;

import io.swagger.annotations.ApiOperation;
import net.mingsoft.base.action.BaseAction;

@Controller
@RequestMapping("msUeditor")
public class MsUeditorController extends BaseAction{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.distPath}")
	private String distPath;
	
	@Value("${ms.upload.path}")
	private String path;
	
	@ApiOperation(value = "处理ueditor请求上传文件")
	@RequestMapping("/jsp/msController")
	@ResponseBody
	public void msController(HttpServletRequest req, HttpServletResponse res) {
		
		String result = null;
		String actionType = req.getParameter("action");
		if("config".equals(actionType)) {
			result = new MsUeditorActionEnter( req, distPath,req.getParameter("jsonConfig")).exec() ;
		}else {
			result = new MsUeditorActionEnter( req, distPath,req.getParameter("jsonConfig")).exec() ;
			Map map = (Map) JSON.parse(result);
			map.put("url", path+map.get("url"));
			result = JSON.toJSONString(map);
		}
		logger.info("MsUeditorController-->result:"+result);
		this.outJson(res,result);
	}
	
}
