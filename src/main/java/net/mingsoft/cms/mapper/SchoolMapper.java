package net.mingsoft.cms.mapper;

import net.mingsoft.cms.model.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * SchoolMapper数据库操作接口类
 * 
 **/

public interface SchoolMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	School  selectByPrimaryKey(@Param("id") Integer id);

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
	int insert(School record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(School record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(School record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(School record);

	List<School> listBySelective(School record);

	int deleteBatchBySelective(List<School> record);
}