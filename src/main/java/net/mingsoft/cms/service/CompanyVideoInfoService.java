package net.mingsoft.cms.service;

import net.mingsoft.cms.model.CompanyVideoInfo;

import java.util.List;

public interface CompanyVideoInfoService {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyVideoInfo record);

    int insertSelective(CompanyVideoInfo record);

    CompanyVideoInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyVideoInfo record);

    int updateByPrimaryKey(CompanyVideoInfo record);

    List<CompanyVideoInfo> listBySelective(CompanyVideoInfo record);

    int deleteBatchBySelective(List<CompanyVideoInfo> record);
}