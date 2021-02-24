package net.mingsoft.cms.service.impl;

import cn.hutool.json.JSONUtil;
import net.mingsoft.cms.mapper.RecruitmentInfoMapper;
import net.mingsoft.cms.model.RecInfo;
import net.mingsoft.cms.model.RecruitmentInfo;
import net.mingsoft.cms.model.OrgView;
import net.mingsoft.cms.service.RecInfoService;
import net.mingsoft.cms.sqlserver.RecInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecInfoServiceImpl implements RecInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecInfoMapper recInfoMapper;

    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;

    @Override
    public int updateRecruitmentInfo() {
        // 更新招聘信息
        
        //先删除所有数据
        //recruitmentInfoMapper.deleteAll();
        int count = 0;
        RecInfo info = new RecInfo();
//        info.setZ0319("04");
        List<RecInfo> list = recInfoMapper.listBySelective(info);
        
        if (!list.isEmpty()) {
            for (RecInfo recInfo : list) {
                RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
                recruitmentInfo.setRecruitmentCode(recInfo.getZ0301());
                
                List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoMapper.listBySelective(recruitmentInfo);
                
                recruitmentInfo.setCompanyCode(recInfo.getZ0321());
                recruitmentInfo.setPosition(recInfo.getZ0351());
                recruitmentInfo.setDuty(recInfo.getZ0342());
                recruitmentInfo.setConditionText(recInfo.getZ0363());
                recruitmentInfo.setSalary(recInfo.getZ03AD());
                recruitmentInfo.setStatus(recInfo.getZ0319());
                recruitmentInfo.setNumber(recInfo.getZ0315());
                recruitmentInfo.setUpdateTime(new Date());
                recruitmentInfo.setRecruitmentType(recInfo.getZ0336());
                
                if(recruitmentInfoList.isEmpty()) {
                	recruitmentInfo.setCreateTime(new Date());
	                
	                int insert = recruitmentInfoMapper.insertSelective(recruitmentInfo);
	                logger.info("公司组织架构新增数据,orgViewB0110:{},orgView:{}",  JSONUtil.toJsonStr(recInfo));
	                if (insert > 0) {
	                    count++;
	                }
                }else {
                	int insert = recruitmentInfoMapper.updateByCode(recruitmentInfo);
	                logger.info("公司组织架构更新数据,orgViewB0110:{},orgView:{}",  JSONUtil.toJsonStr(recInfo));
	                if (insert > 0) {
	                    count++;
	                }
                }

            }
        }
        return count;
    }

}
