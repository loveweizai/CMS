package net.mingsoft.job;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 定时任务测试中心
 * Created by dhl on 2017/8/22.
 */
@Component
public class QuartzTasks {

	/*****************************测试************************************/
	/**
	 * 创建触发器
	 * @param jobDetail
	 * @return
	 */
	@Bean(name = "userCronTriggerFactoryBean")
	public CronTriggerFactoryBean userCronTriggerFactoryBean(@Qualifier("userJobDetailFactoryBean") JobDetail jobDetail) {
		String cron = "0 0/1 * * * ?";
		return QuartzUtil.createCron(cron, jobDetail);
	}

	/**
	 * 加载job
	 * @return
	 */
	@Bean(name = "userJobDetailFactoryBean")
	public JobDetailFactoryBean userJobDetailFactoryBean() {
		String targetObject = "tasksUtil"; //类
		String targetMethod = "testQuartz";//方法
		String group = "taskGroup";//组
		return QuartzUtil.createJob(targetObject,targetMethod,group);
	}


	/***************************招聘信息********************************/
	/**
	 * 测试环境 5分钟一次
	 * 正式环境：半年执行一次,每年6.30和12月30执行一次
	*  更新数据
	* @param jobDetail
	* @return
	*/
	@Bean(name = "recInfoBean")
	public CronTriggerFactoryBean updateRecInfo(@Qualifier("recInfoJob") JobDetail jobDetail) {
		String cron = "0 0/5 * * * ?";
		//String cron = "0 0 0 30 6,12 ?";
		return QuartzUtil.createCron(cron, jobDetail);
	}
	/**
	* 加载job
	* @return
	*/
	@Bean(name = "recInfoJob")
	public JobDetailFactoryBean recInfoJob() {
		String targetObject = "tasksUtil"; //类
		String targetMethod = "recInfo";//方法
		String group = "taskGroup";//组
		return QuartzUtil.createJob(targetObject,targetMethod,group);
	}

	/***************************招聘信息********************************/
	/**
	 * 测试环境 5分钟一次
	 * 正式环境：1天一次
	 *  更新数据
	 * @param jobDetail
	 * @return
	 */
	@Bean(name = "orgViewJobTrigger")
	public CronTriggerFactoryBean updateUserLoanStatusJobTrigger(@Qualifier("orgViewJob") JobDetail jobDetail) {
		//String cron = "0 0/1 * * * ?";
		String cron = "0 0 1 * * ?";
		return QuartzUtil.createCron(cron, jobDetail);
	}
	/**
	 * 加载job
	 * @return
	 */
	@Bean(name = "orgViewJob")
	public JobDetailFactoryBean orgViewJob() {
		String targetObject = "tasksUtil"; //类
		String targetMethod = "orgView";//方法
		String group = "taskGroup";//组
		return QuartzUtil.createJob(targetObject,targetMethod,group);
	}

}
