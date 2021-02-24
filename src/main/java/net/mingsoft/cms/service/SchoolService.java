package net.mingsoft.cms.service;

import net.mingsoft.cms.model.School;

import java.util.List;

public interface SchoolService {
    int deleteByPrimaryKey(Integer id);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
    
    List<School> listBySelective(School record); 
    
    int deleteBatchBySelective(List<School> record);
}