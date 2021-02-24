package net.mingsoft.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.mingsoft.cms.model.Engineering;

/**
 * 
 * EngineeringMapper数据库操作接口类
 * 
 **/

public interface EngineeringMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Engineering  selectByPrimaryKey(@Param("id") Integer id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Integer id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(Engineering record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(Engineering record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(Engineering record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(Engineering record);

	List<Engineering> listBySelective(Engineering record);

	int deleteBatchBySelective(List<Engineering> record);
	
	int insertBatch(List<Engineering> record);

	Engineering selectBySelective(Engineering record);

	List<Map> listCityByProvinceId(Engineering record);
}