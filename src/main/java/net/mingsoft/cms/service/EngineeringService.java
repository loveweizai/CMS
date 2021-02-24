package net.mingsoft.cms.service;

import java.util.List;
import java.util.Map;

import net.mingsoft.cms.model.Engineering;

public interface EngineeringService {
    int deleteByPrimaryKey(Integer id);

    int insert(Engineering record);

    int insertSelective(Engineering record);

    Engineering selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Engineering record);

    int updateByPrimaryKey(Engineering record);

    List<Engineering> listBySelective(Engineering record);

    int deleteBatchBySelective(List<Engineering> record);
    
	int insertBatch(List<Engineering> record);
	
	Engineering selectBySelective(Engineering record);
	
	List<Map> listCityByProvinceId(Engineering record);
}