package net.mingsoft.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.mingsoft.cms.model.CityDict;

/**
 * 
 * CityDictMapper数据库操作接口类
 * 
 **/

public interface CityDictMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CityDict  selectByPrimaryKey(@Param("id") String id);

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
	int insert(CityDict record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CityDict record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CityDict record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CityDict record);

	List<CityDict> listBySelective(CityDict record);

    int deleteBatchBySelective(List<CityDict> record);
}