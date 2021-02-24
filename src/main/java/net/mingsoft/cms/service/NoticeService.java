package net.mingsoft.cms.service;

import java.util.List;

import net.mingsoft.cms.model.Notice;

public interface NoticeService {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
    
    List<Notice> listBySelective(Notice record); 
    
    int deleteBatchBySelective(List<Notice> record);
}