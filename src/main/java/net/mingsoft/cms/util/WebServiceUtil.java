package net.mingsoft.cms.util;

import org.apache.axis.client.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.util.StringUtil;

public class WebServiceUtil {

	private static Logger logger = LoggerFactory.getLogger(WebServiceUtil.class);
	
	public static String getProjectInfoResultByWebService(String url,String operationName,String param) {
		// TODO Auto-generated method stub
		try {  
			logger.info("getProjectInfoResultByWebService start url:{},operationName:{},param:{}",url,operationName,param);
            //String projectWebServiceUrl = "http://10.60.230.141:8888/slsd8j/services/projectinfoService?wsdl";  
            // 直接引用远程的wsdl文件  
            // 以下都是套路  
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();  
            Call call = (Call) service.createCall();  
            call.setTargetEndpointAddress(url);  
            call.setOperationName(operationName);// WSDL里面描述的接口名称  "getProjectList"
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型  
            String result = null;
            if(StringUtil.isEmpty(param)) {
            	result = (String) call.invoke(new Object[] {  }); 
            }else {
            	result = (String) call.invoke(new Object[] { param }); 
            }
            // 给方法传递参数，并且调用方法  
            logger.info("getProjectInfoResultByWebService result is param:{},result:{}",param,result);
            return result;
        } catch (Exception e) {  
        	logger.error("getProjectInfoResultByWebService 请求接口异常：", e);
        }
		return null;
	}
	
	
	public static void main(String[] args) {
//		try {  
            String url = "http://10.60.230.141:8888/slsd8j/services/projectinfoService?wsdl";  
            String operationName = "getAreaList";
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
		System.out.println(WebServiceUtil.getProjectInfoResultByWebService(url, operationName, null));
	}
}
