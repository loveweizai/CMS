package net.mingsoft.cms.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import net.mingsoft.cms.model.Engineering;
import net.mingsoft.cms.model.ProjectInfo;
import net.mingsoft.cms.service.EngineeringService;
import net.mingsoft.cms.service.ProjectInfoService;
import net.mingsoft.cms.util.WebServiceUtil;

@Service
public class ProjectInfoServiceImpl implements ProjectInfoService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${project.webService.url}")
	private String projectWebServiceUrl;
	
	@Value("${project.webService.getProjectList}")
	private String getProjectList;
	
	@Autowired
	private EngineeringService engineeringService;
	
	/**
	 * {
	"area": "巧家县",
	"address": "金沙江白鹤滩",
	"engineeringunit": "白鹤滩施工局",
	"name": "白鹤滩水电站（白鹤滩施工局）",
	"projectsummary": "白鹤滩水电站位于金沙江下游四川省宁南县和云南省巧家县，距巧家县城45km，距昆明约260km，至重庆、成都、贵阳均在400km左右，至华东地区上海的直线距离为1850km。电站上接乌东德梯级，下邻溪洛渡梯级，距溪洛渡水电站195km。\r\n白鹤滩水电站的开发任务以发电为主，电站正常蓄水位为825.0m，水库总库容206.27亿m3。枢纽工程主要由混凝土双曲拱坝、二道坝及水垫塘、泄洪洞、引水发电系统等建筑物组成。混凝土双曲拱坝坝顶高程834.0m，最大坝高289.0m，坝身布置有6孔泄洪表孔和7孔泄洪深孔；泄洪洞共 3条，均布置在左岸；电站总装机容量16000MW，左、右岸地下厂房各布置 8台单机容量1000MW 的水轮发电机组。 \r\n本合同工程主要包括高程834～600m坝基及拱肩槽边坡工程（含右岸中上部坝肩处理工程、建基面处理工程），右岸坝基（肩）帷幕灌浆洞、截渗洞、排水洞工程，下游834～600m高程水垫塘及上部边坡开挖及支护工程，右岸进水口834～734m高程边坡开挖及支护工程，右岸马脖子780~734m高程边坡开挖及支护工程，上游围堰工程。\r\n",
	"province": "云南省",
	"enddate": "2016-02-29",
	"begindate": "2014-02-01",
	"city": "昭通市",
	"country": "中国"
}
	 */
	@Override
	public List<Engineering> getProjectInfo() {
		List<Engineering> engineeringList = this.getProjectInfoByJson();
		return engineeringList;
	}
	
	@Override
	public int saveProjectInfo() {
		List<Engineering> engineeringList = this.getProjectInfoByJson();
		int sum = 0;
		for(Engineering engineering:engineeringList) {
			Engineering en = new Engineering();
			en.setEngineerNo(engineering.getEngineerNo());
			try {
				en = engineeringService.selectBySelective(en);
				if(en == null) {
					int count = engineeringService.insertSelective(engineering);
					if(count>0) {
						sum++;
					}
				}else {
					if(StringUtil.isEmpty(en.getLastDate()) || !en.getLastDate().equals(engineering.getLastDate())) {
						engineering.setId(en.getId());
						int count = engineeringService.updateByPrimaryKeySelective(engineering);
						if(count>0) {
							sum++;
						}
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("插入或更新异常：{}",e);
			}
			
		}
		return sum;
		//return engineeringService.insertBatch(engineeringList);
	}

	public List<Engineering> getProjectInfoByJson() {
		String result = WebServiceUtil.getProjectInfoResultByWebService(projectWebServiceUrl,getProjectList,null);
		List<Engineering> engineeringList = new ArrayList<>();
		if(StringUtil.isNotEmpty(result)) {
			JSONArray jsonArray = JSONUtil.parseArray(result);
			for(Object obj:jsonArray) {
		        JSONObject jsonObject=JSONUtil.parseObj(obj);
		        ProjectInfo projectInfo=JSONUtil.toBean(jsonObject, ProjectInfo.class);
				Engineering engineering = new Engineering();
				try {
					engineering.setEngineerName(projectInfo.getName());
					engineering.setAreaId(projectInfo.getAreaid());
					engineering.setCityId(projectInfo.getCityid());
					engineering.setProvinceId(projectInfo.getProvinceid());
					engineering.setCountryId(projectInfo.getCountryid());
					engineering.setEngineerNo(projectInfo.getId());
					engineering.setLastDate(projectInfo.getLastdate());
					engineering.setAreaName(projectInfo.getArea());
					engineering.setCityName(projectInfo.getCity());
					projectInfo.setProvince(projectInfo.getProvince());
					engineering.setLocation(projectInfo.getAddress());
					engineering.setCountry(projectInfo.getCountry());
					engineering.setSurvey(projectInfo.getProjectsummary());
					engineering.setEndDate(DateUtils.parseDate(projectInfo.getEnddate(), "yyyy-MM-dd"));
					engineering.setStartDate(DateUtils.parseDate(projectInfo.getBegindate(), "yyyy-MM-dd"));
					engineering.setUnit(projectInfo.getEngineeringunit());
					engineering.setCreateTime(new Date());
					engineering.setUpdateTime(new Date());
					engineeringList.add(engineering);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					logger.error("ParseException date :{ }",e);
				}
			}
		}
		return engineeringList;
	}
	
//	public static void main(String[] args) {
//		try {  
//            String endpoint = "http://10.60.230.141:8888/slsd8j/services/projectinfoService?wsdl";  
//            // 直接引用远程的wsdl文件  
//            // 以下都是套路  
//            Service service = new Service();  
//            Call call = (Call) service.createCall();  
//            call.setTargetEndpointAddress(endpoint);  
//            call.setOperationName("getProjectList");// WSDL里面描述的接口名称  
////            call.addParameter("userName",  
////                    org.apache.axis.encoding.XMLType.XSD_DATE,  
////                    javax.xml.rpc.ParameterMode.IN);// 接口的参数  
//            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型  
//            String temp = "测试人员";  
//            String result = (String) call.invoke(new Object[] {  });  
//            // 给方法传递参数，并且调用方法  
//            System.out.println("result is " + result);  
//        } catch (Exception e) {  
//            System.err.println(e);  
//        }  
//		ProjectInfoServiceImpl info = new ProjectInfoServiceImpl();
//		System.out.println(info.getProjectInfo());
//	}

}
