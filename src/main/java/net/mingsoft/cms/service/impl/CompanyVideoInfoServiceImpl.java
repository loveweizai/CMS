package net.mingsoft.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mingsoft.cms.mapper.CompanyVideoInfoMapper;
import net.mingsoft.cms.model.CompanyVideoInfo;
import net.mingsoft.cms.service.CompanyVideoInfoService;

@Service
public class CompanyVideoInfoServiceImpl implements CompanyVideoInfoService {

    @Autowired
    private CompanyVideoInfoMapper companyVideoInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CompanyVideoInfo record) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(CompanyVideoInfo record) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.insertSelective(record);
    }

    @Override
    public CompanyVideoInfo selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyVideoInfo record) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyVideoInfo record) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CompanyVideoInfo> listBySelective(CompanyVideoInfo record) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.listBySelective(record);
    }

    @Override
    public int deleteBatchBySelective(List<CompanyVideoInfo> record) {
        // TODO Auto-generated method stub
        return companyVideoInfoMapper.deleteBatchBySelective(record);
    }
}