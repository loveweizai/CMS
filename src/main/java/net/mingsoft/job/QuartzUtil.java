package net.mingsoft.job;

import java.util.Map;

import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.google.common.collect.Maps;

/**
 * Created by dhl on 2017/8/22.
 */

public class QuartzUtil {


	public static CronTriggerFactoryBean createCron(String cron, JobDetail jobDetail) {
		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setCronExpression(cron);
		return factoryBean;
	}

	public static JobDetailFactoryBean createJob(String targetObject, String targetMethod, String group) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(QuartzConfiguration.class);
		factoryBean.setDurability(true);
		factoryBean.setRequestsRecovery(true);
		factoryBean.setGroup(group);
		Map<String, Object> map = Maps.newHashMap();
		map.put("targetObject", targetObject);
		map.put("targetMethod", targetMethod);
		factoryBean.setJobDataAsMap(map);
		return factoryBean;
	}


}
