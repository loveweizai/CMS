package net.mingsoft.cms.util;

import com.alibaba.druid.util.DruidPasswordCallback;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
import java.util.Properties;
 
/**
 *
 * @author gourd
 *
 */
@Component
public class DbPasswordCallback extends DruidPasswordCallback {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String password = properties.getProperty("password");
		String publicKey = properties.getProperty("publicKey");
		if (StringUtils.isNotEmpty(password)) {
			try {
				String sourcePassword = AesHopeUtil.decryt(publicKey, password);
				setPassword(sourcePassword.toCharArray());
			} catch (Exception e) {
				setPassword(password.toCharArray());
			}
		}
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String password = "gourd123";
		String pwd = AesHopeUtil.encrypt("GOURD-HXNLYW-201314",password);
		System.out.println(pwd);
 
		String source = AesHopeUtil.decryt("GOURD-HXNLYW-201314",pwd);
		System.out.println(source);
	}
}