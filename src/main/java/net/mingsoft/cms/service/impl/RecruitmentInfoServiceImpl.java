package net.mingsoft.cms.service.impl;

import net.mingsoft.cms.mapper.RecruitmentInfoMapper;
import net.mingsoft.cms.model.RecruitmentInfo;
import net.mingsoft.cms.service.RecruitmentInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {

    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RecruitmentInfo record) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(RecruitmentInfo record) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.insertSelective(record);
    }

    @Override
    public RecruitmentInfo selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RecruitmentInfo record) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RecruitmentInfo record) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<RecruitmentInfo> listBySelective(RecruitmentInfo record) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.listBySelective(record);
    }

    @Override
    public int deleteBatchBySelective(List<RecruitmentInfo> record) {
        // TODO Auto-generated method stub
        return recruitmentInfoMapper.deleteBatchBySelective(record);
    }
}