package net.mingsoft.cms.service;

import net.mingsoft.cms.model.RecruitmentInfo;

import java.util.List;

public interface RecruitmentInfoService {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitmentInfo record);

    int insertSelective(RecruitmentInfo record);

    RecruitmentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitmentInfo record);

    int updateByPrimaryKey(RecruitmentInfo record);

    List<RecruitmentInfo> listBySelective(RecruitmentInfo record);

    int deleteBatchBySelective(List<RecruitmentInfo> record);
}