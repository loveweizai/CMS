package net.mingsoft.job;

import net.mingsoft.cms.service.RecInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.mingsoft.cms.service.OrgViewService;

/**
 * Created by dhl on 2017/8/22.
 */
@Component
public class TasksUtil {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrgViewService orgViewService;

	@Autowired
	private RecInfoService recInfoService;

	public void testQuartz() {
		logger.info("test start-----------每天一次的扫描测试任务开始啦！");
		logger.info("test end ======");
	}

	/**
	 * 公司架构管理；
	 *
	 * 
	 */
	public void orgView() {
		logger.info("orgView-----------每天一次的扫描公司架构管理任务开始啦！");

		try {
			// OrgViewService orgViewService =
			// SpringContextHolder.getBean("orgViewServiceImpl");
			int count = orgViewService.updateCompanyInfo();
			logger.info("orgView  ====== count:{}", count);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("error   Loan  ====== 异常batchUpdateUser", e);
		}

		logger.info("orgView end ======");
	}

	/**
	 * 公司架构管理；
	 *
	 *
	 */
	public void recInfo() {
		logger.info("recInfo-----------每天一次的扫描招聘信息任务开始啦！");

		try {
			// OrgViewService orgViewService =
			// SpringContextHolder.getBean("orgViewServiceImpl");
			int count = recInfoService.updateRecruitmentInfo();
			logger.info("orgView  ====== count:{}", count);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("error   Loan  ====== 异常batchUpdateUser", e);
		}

		logger.info("recInfo end ======");
	}

}
