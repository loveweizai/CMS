package net.mingsoft.cms.mapper;

import java.util.List;

import net.mingsoft.cms.model.Notice;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
    
    List<Notice> listBySelective(Notice record);
    
    int deleteBatchBySelective(List<Notice> record);
}