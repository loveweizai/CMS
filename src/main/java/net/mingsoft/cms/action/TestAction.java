package net.mingsoft.cms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mingsoft.basic.action.BaseAction;
import net.mingsoft.cms.service.CityDictService;
import net.mingsoft.cms.service.OrgViewService;
import net.mingsoft.cms.service.ProjectInfoService;
import net.mingsoft.cms.service.RecInfoService;

/**
 * @description 轮播图管理
 * @author shjpgli
 * @date 2019-06-03
 *
 */
@Controller
@RequestMapping("/${ms.manager.path}/cms/test")
public class TestAction extends BaseAction {
	
	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;
	
	@Autowired
	private OrgViewService orgViewService;
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private CityDictService cityDictService;
	
	@Autowired
	private RecInfoService recInfoService;
	
	
	/**
	 * 加载页面显示所有文章信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/orgViewStart")
	public void orgViewStart(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		int count = orgViewService.updateCompanyInfo();
//		try {
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            scheduler.start();
//
//            // define the job and tie it to our HelloJob class
//            JobDetail job = JobBuilder.newJob(TasksUtil.class)
//                    .withIdentity("job1", "group1")
//                    .build();
//
//            // 每5秒运行一次job
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .startNow()
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInSeconds(5)
//                            .repeatForever())
//                    .build();
//
//            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job, trigger);
//            TimeUnit.SECONDS.sleep(10);
//            scheduler.shutdown();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		return ;
	}
	
	
	/**
	 * 加载页面显示所有文章信息
	 * 
	 * @param request
	 * @return 返回文章页面显示地址
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/projectInfoStart")
	public void projectInfoStart(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		int count = projectInfoService.saveProjectInfo();
//		try {
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            scheduler.start();
//
//            // define the job and tie it to our HelloJob class
//            JobDetail job = JobBuilder.newJob(TasksUtil.class)
//                    .withIdentity("job1", "group1")
//                    .build();
//
//            // 每5秒运行一次job
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .startNow()
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInSeconds(5)
//                            .repeatForever())
//                    .build();
//
//            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job, trigger);
//            TimeUnit.SECONDS.sleep(10);
//            scheduler.shutdown();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		return ;
	}
	
	@RequestMapping("/cityDict")
	public void cityDict(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		int count = cityDictService.insert();
	}
	
	@RequestMapping("/recruitmentInfo")
	public void recruitmentInfo(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		int count = recInfoService.updateRecruitmentInfo();
	}
	@RequestMapping("/recruitment")
	public void recruitment(HttpServletRequest request, ModelMap mode, HttpServletResponse response) {
		int count = recInfoService.updateRecruitmentInfo();
	}
	
}
