package net.mingsoft.job;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import net.mingsoft.MSApplication;

/**
 * Created by dhl on 2017/8/22.
 */
@Configuration
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzConfiguration extends QuartzJobBean {

	// 计划任务所在类
	private String targetObject;

	// 具体需要执行的计划任务
	private String targetMethod;

	@Value("${quartz.properties:#{null}}")
	private String quartzFile;
	/**
	 * 设置属性
	 *
	 * @return
	 * @throws IOException
	 */
	private Properties quartzProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(MSApplication.class.getResourceAsStream(quartzFile));
		prop.put("quartz.scheduler.instanceName", "quartzTimedTask");
		prop.put("org.quartz.scheduler.instanceId", "AUTO");
		prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
		prop.put("org.quartz.scheduler.jmx.export", "true");

		prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
		prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
		prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		prop.put("org.quartz.jobStore.isClustered", "true");

		prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
		prop.put("org.quartz.jobStore.dataSource", "myDS");
		prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
		prop.put("org.quartz.jobStore.misfireThreshold", "120000");
		prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
		prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE");

		prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		prop.put("org.quartz.threadPool.threadCount", "10");
		prop.put("org.quartz.threadPool.threadPriority", "5");
		prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

		prop.put("org.quartz.plugin.triggHistory.class", "org.quartz.plugins.history.LoggingJobHistoryPlugin");
		prop.put("org.quartz.plugin.shutdownhook.class", "org.quartz.plugins.management.ShutdownHookPlugin");
		prop.put("org.quartz.plugin.shutdownhook.cleanShutdown", "true");
		
		return prop;
	}

//	@Bean
//	public SchedulerFactoryBean userSchedulerFactoryBean(Trigger... triggers) throws IOException {
//		SchedulerFactoryBean factory = new SchedulerFactoryBean();
//		// this allows to update triggers in DB when updating settings in config file:
//		//用于quartz集群,QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
//		factory.setOverwriteExistingJobs(true);
//		//用于quartz集群,加载quartz数据源
//		//factory.setDataSource(dataSource);
//		//QuartzScheduler 延时启动，应用启动完10秒后 QuartzScheduler 再启动
//		factory.setStartupDelay(10);
//		//用于quartz集群,加载quartz数据源配置
//		factory.setQuartzProperties(quartzProperties());
//		factory.setAutoStartup(true);
//		factory.setApplicationContextSchedulerContextKey("applicationContext");
//		//注册触发器
//		factory.setTriggers(triggers);//直接使用配置文件
//		return factory;
//	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Object otargetObject = SpringContextHolder.getBean(targetObject);
			Method m = null;
			try {
				m = otargetObject.getClass().getMethod(targetMethod);
				m.invoke(otargetObject);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	public String getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

}
