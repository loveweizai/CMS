package net.mingsoft.cms.service;

import net.mingsoft.cms.model.CompanyStructure;

import java.util.List;

public interface CompanyService {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyStructure record);

    int insertSelective(CompanyStructure record);

    CompanyStructure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyStructure record);

    int updateByPrimaryKey(CompanyStructure record);

    List<CompanyStructure> listBySelective(CompanyStructure record);

    int deleteBatchBySelective(List<CompanyStructure> record);
}