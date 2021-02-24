package net.mingsoft.cms.service;

import net.mingsoft.cms.model.Recruitment;

import java.util.List;

public interface RecruitmentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Recruitment record);

    int insertSelective(Recruitment record);

    Recruitment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recruitment record);

    int updateByPrimaryKey(Recruitment record);

    List<Recruitment> listBySelective(Recruitment record);

    int deleteBatchBySelective(List<Recruitment> record);
}