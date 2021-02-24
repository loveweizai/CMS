package net.mingsoft.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.json.JSONUtil;
import net.mingsoft.cms.mapper.CompanyStructureMapper;
import net.mingsoft.cms.model.CompanyStructure;
import net.mingsoft.cms.model.OrgView;
import net.mingsoft.cms.service.OrgViewService;
import net.mingsoft.cms.sqlserver.OrgViewMapper;

@Service
public class OrgViewServiceImpl implements OrgViewService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrgViewMapper orgViewMapper;
	
	@Autowired
	private CompanyStructureMapper companyStructureMapper;
	
	@Override
	public int updateCompanyInfo() {
		// TODO Auto-generated method stub
		int count = 0;
		List<OrgView> list = orgViewMapper.listBySelective(new OrgView());
		if(!list.isEmpty()) {
			for(OrgView orgView:list) {
				CompanyStructure companyStructure = new CompanyStructure();
				companyStructure.setCode(orgView.getB0110_0());
				
				List<CompanyStructure> compayList = companyStructureMapper.listBySelective(companyStructure);
				if(compayList.isEmpty()) {
					companyStructure.setName(orgView.getB0110());
					companyStructure.setCreateTime(new Date());
//					companyStructure.setUpdateTime(new Date());
					int insertCount = companyStructureMapper.insertSelective(companyStructure);
					logger.info("公司组织架构插入数据,orgViewB0110_0:{},orgView:{}",orgView.getB0110_0(),JSONUtil.toJsonStr(orgView));
					if(insertCount>0) {
						count ++;
					}
					
				}else {
					companyStructure.setName(orgView.getB0110());
//					companyStructure.setUpdateTime(new Date());
					int updateCount = companyStructureMapper.updateByPrimaryKeySelective(companyStructure);
					logger.info("公司组织架构更新数据,orgViewB0110_0:{},orgView:{}",orgView.getB0110_0(),JSONUtil.toJsonStr(orgView));
					if(updateCount>0) {
						count ++;
					}
				}
				
			}
		}
		return count;
	}

}
