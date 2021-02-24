package net.mingsoft.cms.service;

import java.util.List;

import net.mingsoft.cms.model.CityDict;

public interface CityDictService {
    int deleteByPrimaryKey(Integer id);

    int insert();

    int insertSelective(CityDict record);

    int updateByPrimaryKeySelective(CityDict record);

    int updateByPrimaryKey(CityDict record);

    List<CityDict> listBySelective(CityDict record);

    int deleteBatchBySelective(List<CityDict> record);
}