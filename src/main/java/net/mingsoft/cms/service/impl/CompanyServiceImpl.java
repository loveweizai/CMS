package net.mingsoft.cms.service.impl;

import net.mingsoft.cms.mapper.CompanyStructureMapper;
import net.mingsoft.cms.model.CompanyStructure;
import net.mingsoft.cms.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyStructureMapper companyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return companyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CompanyStructure record) {
        // TODO Auto-generated method stub
        return companyMapper.insert(record);
    }

    @Override
    public int insertSelective(CompanyStructure record) {
        // TODO Auto-generated method stub
        return companyMapper.insertSelective(record);
    }

    @Override
    public CompanyStructure selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return companyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyStructure record) {
        // TODO Auto-generated method stub
        return companyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyStructure record) {
        // TODO Auto-generated method stub
        return companyMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CompanyStructure> listBySelective(CompanyStructure record) {
        // TODO Auto-generated method stub
        return companyMapper.listBySelective(record);
    }

    @Override
    public int deleteBatchBySelective(List<CompanyStructure> record) {
        // TODO Auto-generated method stub
        return companyMapper.deleteBatchBySelective(record);
    }
}